import com.qaas.pipeline.JenkinsConfig

def call() {
	new JenkinsConfig(readYaml(file: "jenkins-config.yml"))
}
