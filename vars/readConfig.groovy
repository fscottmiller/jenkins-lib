//import com.qaas.pipeline

def call() {
	JenkinsConfig.new("jenkins-config.yml")
	println("Hello, World!")
}