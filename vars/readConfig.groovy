//import com.qaas.pipeline

def call() {
	new com.qaas.pipeline.JenkinsConfig(readYaml(file: "config.yml"))
	println("Hello, World!")
}