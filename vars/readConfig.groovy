//import com.qaas.pipeline

def call() {
	new com.qaas.pipeline.JenkinsConfig(readYaml(file: "jenkins-config.yml"))
	println("Hello, World!")
}