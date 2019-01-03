package com.qaas.pipeline

import codehaus.groovy.

class JenkinsConfig implements Serializable {
	public static JenkinsConfig config
	
	protected Map dataMap = [:]
	
	public JenkinsConfig(String fileLocation) {
		dataMap = readYaml(file: fileLocation)
		config = this
	}
}