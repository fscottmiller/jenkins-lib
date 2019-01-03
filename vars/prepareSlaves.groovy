#!/usr/bin/env groovy

def call() {
    echo "Preparing slaves..."
	def configData = readYaml(file: 'test_dir/jenkins-config.yml')
	def nodeLabel = configData['language']
	def builds = [:]
	def onlineCounter = 0
	// for each jenkins slave
	for(computer in Jenkins.instance.computers) {
		// if computer has label matching 'nodeLabel'
		if (computer.isOnline() && computer.getAssignedLabels().findAll({it.name == nodeLabel}).size() > 0) {
			onlineCounter++
				
			// for each executor in slave
			for (int executorNum = 0; executorNum < computer.getNumExecutors(); executorNum++) {
				def computerName = computer.getName()
				// creates build corresponding to slave and executor number
				builds["${computerName}_${executorNum}"] = {
					// build on specified node
					// logical error: if 2 pipelines are running at the same time, both use an agent with 
							 // 2+ executors, and pipeline A's test job is not finished before this
					 		 // step takes place, this will prepare the same executor twice
					node(computerName) {
						checkout([
							 $class: 'GitSCM',
							 branches: [[name: "*/${projectBranch}"]],
							 doGenerateSubmoduleConfigurations: false,
							 userRemoteConfigs: [[credentialsId: 'GitConnector_QATInc', url: "${projectRepository}"]]
						])
						sh('mkdir -p reports')
					}
				}
			}
		}
	}
	if (onlineCounter > 0) {
		parallel(builds)
	} else {
		error("No online nodes matching label: [${nodeLabel}]")
	}
}
