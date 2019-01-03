//import com.qaas.pipeline

def call() {
	new com.qaas.pipeline.JenkinsConfig("jenkins-config.yml")
	println("Hello, World!")
}