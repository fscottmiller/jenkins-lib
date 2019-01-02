#!/usr/bin/env groovy

def call(language, fnTags, env) {
    echo "Executing tests..."
    
    // fnTags = [fnTags]
    
	def envTag = env
	def nodeLabel = language
	def invalidTag = 'ToDo'
    
    echo "printing fnTags"
    echo fnTags
    
    //convert string back to list
    // fnTags = Eval.me("fnTags")
    fnTags = fnTags[1..-2].tokenize(',')
    echo fnTags
    
    
    echo fnTags
    
    def functionTags = fnTags

	
	//for each functional tag specified in jenkins-config.yml for given environemnt
	for(functionTag in functionTags) {
		//creates a jenkins stage
		stage(functionTag) {
			//tagging logic for the stage
			def tagLogic = "@${envTag} and @${functionTag} and not @${invalidTag}"
			def isFailed = false
			//list of feature files to be run
			def features = []
			//hash of builds to be executed
			def builds = [:]
			
			//populates the features list with the dry-run results
			node(nodeLabel) {
				def dryrunSuccess = sh(script: "cucumber --dry-run --tags '${tagLogic}' --format json --out dry-run.json", returnStdout: true)
				for(feature in readJSON(file: 'dry-run.json')) {
					features << feature.uri
				}
			}
			
			def stageResult = "Pipeline executing features matching tags: '${tagLogic}'"
			//for each feature in the features list
			for(feature in features) {
				def featureFile = feature
				def featureName = feature.substring(feature.lastIndexOf('/') + 1, feature.lastIndexOf('.'))
				//unique name for feature
				def runName = "${featureName}-${projectEnvironment}-${functionTag}"
				
				//populates the builds hash with a build for each feature
				builds[featureName] = {
					run on 'nodelabel'
					node(nodeLabel) {
						//runs the cucumber tests and stores results
						def buildSuccess = sh(script: "cucumber --tags '${tagLogic}' '${featureFile}' --format json --out 'reports/${runName}.json' TEST_ENV=${projectEnvironment}", returnStatus: true) == 0
						
						//preparing report JSON for ELK consumption
						try {
							def reportJSON = readJSON(file: "reports/${runName}.json")
							reportJSON[0]['name'] += " [Tags: ${tagLogic}]"
							reportJSON[0]['timestamp'] = new Date().toTimestamp().toString()
							writeJSON(file: "reports/${runName}.json", json: reportJSON)
							sh("echo.>>reports/${runName}.json")
							//copies report JSON back to master
							archiveArtifacts(artifacts: 'reports/*.json')
						} catch(java.io.FileNotFoundException e1) {
							buildSuccess = false;
							println("Report JSON for ${featureName} did not get generated")
						}
						
						//printing pass/fail for feature
						if(buildSuccess) {
							stageResult += "\r\n SUCCESS: ${featureFile}"
						} else {
							isFailed = true;
							stageResult += "\r\n FAILURE: ${featureFile}"
						}
					}
				}			
			}
			//sends the build hash to be executed
			parallel(builds)
			
			if(isFailed) {
				currentBuild.setResult('FAILURE')
				error(stageResult)
			} else {
				println(stageResult)
			}
		}
		
	}
}