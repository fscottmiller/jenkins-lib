import com.qaas.pipeline.JenkinsConfig

def call() {
	JenkinsConfig.init(readYaml(file: "jenkins-config.yml"))
}
