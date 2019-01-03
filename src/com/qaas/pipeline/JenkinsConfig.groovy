package com.qaas.pipeline

class JenkinsConfig implements Serializable {
	protected static Map dataMap = [:]
	
	public JenkinsConfig(Map dataMap) {
		this.dataMap = dataMap
	}
	
	public static String getLanguage() {
		return dataMap['language']
	}
	
	public static String getEnvironmentType(String environment) {
		return dataMap['environments'][environment]
	}
	
	public static String[] getEnvironmentStages(String environment) {
		return dataMap['environments'][environment]
	}
	
	public static String[] getEmailList() {
		return dataMap['email']
	}
}