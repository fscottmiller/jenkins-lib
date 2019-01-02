#!/usr/bin/env groovy

def call(String repository, String branch, String dir=".") {
    echo "${branch}"
    echo "${repository}"
    checkout([
        $class: 'GitSCM',
        branches: [[name: "*/${branch}"]],
        doGenerateSubmoduleConfigurations: false,
        userRemoteConfigs: [[url: "${repository}"]],
        extensions: [[$class: 'RelativeTargetDirectory', relateiveTargetDir: dir]]
    ])
}