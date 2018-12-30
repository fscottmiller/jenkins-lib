#!/usr/bin/env groovy

def call(String repo, String language) {
    echo "Preparing environment..."
	//checkout repo

    //
	// switch(projectLanguage) {
		// case 'ruby':
			// runner = load('lib_groovy/ruby.groovy')
			// break
		// case 'junit':
			// runner = load('lib_groovy/junit.groovy')
			// break
		// case 'specflow':
			// runner = load('lib_groovy/specflow.groovy')
			// break
		// default:
			// error('Project language from jenkins-config.yml is not correct/supported')
			// break
	// }
	// runner.prepareSlaves(projectName, projectRepository, projectBranch, projectEnvironment)
}