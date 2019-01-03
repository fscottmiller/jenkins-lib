package com.qaas.pipeline

import codehaus.groovy.

class JenkinsConfig {
	public static JenkinsConfig config
	
	Map dataMap = [:]
	
	public JenkinsConfig(String fileLocation) {
		dataMap = readYaml(file: fileLocation)
		config = this
	}
}