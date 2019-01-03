package com.qaas.pipeline

class JenkinsConfig implements Serializable {
	public JenkinsConfig config
	
	protected Map dataMap = [:]
	
	public JenkinsConfig(Map dataMap) {
		this.dataMap = dataMap
		config = this
	}
}