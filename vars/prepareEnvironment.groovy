#!/usr/bin/env groovy

def call(language) {
    echo "Preparing environment..."
	switch(language) {
		case 'ruby':
			echo 'Provisioning ruby env...'
			break
		case 'junit':
			echo 'Provisioning java env...'
			break
		case 'specflow':
			echo 'Provisioning c# env...'
			break
		default:
			error('Project language from config.yml is not yet supported')
			break
	}
}