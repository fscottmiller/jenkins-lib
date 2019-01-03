import com.qaas.pipeline.JenkinsConfig

def call() {
	new com.qaas.pipeline.JenkinsConfig(readYaml(file: "config.yml"))
}