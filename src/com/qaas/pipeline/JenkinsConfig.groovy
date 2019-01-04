package com.qaas.pipeline

class JenkinsConfig implements Serializable {
	protected static Map dataMap = [:]
	
	public JenkinsConfig(Map dataMap) {
		this.dataMap = dataMap
	}
	
	public static String getLanguage() {
		return dataMap['language'].toString()
	}
	
	public static String getEnvironmentType(String environment) {
		return dataMap['environments'][environment].toString()
	}
	
	public static String[] getEnvironmentStages(String environment) {
		String[] out = new String[dataMap['environments'][environment].size()];
		for (int i = 0; i < out.length; i++) {
			out[i] = dataMap['environments'][environment][i].toString()
		}
		return out
	}
	
	public static String[] getEmailList() {
		return dataMap['email']
	}
}