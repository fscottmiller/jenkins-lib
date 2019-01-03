package com.qaas.pipeline

class JenkinsConfig implements Serializable {
	public static JenkinsConfig config
	protected Map dataMap = [:]
	
	public JenkinsConfig(Map dataMap) {
		this.dataMap = dataMap
		config = this
	}
	
	public static String getLanguage() {
		return config['language']
	}
	
	public static String getEnvironmentType(String environment) {
		return config['environments'][environment]
	}
	
	public static String[] getEnvironmentStages(String environment) {
		return config['environments'][environment]
	}
	
	public static String[] getEmailList() {
		return config['email']
	}
}