import com.qaas.pipeline.JenkinsConfig

def call() {
	new JenkinsConfig(readYaml(file: "config.yml"))
}
