#!/usr/bin/env groovy

def call(String branch, String credentials="", String repository) {
    checkout([
        $class: 'GitSCM',
        branches: [[name: "*/${branch}"]],
        doGenerateSubmoduleConfigurations: false,
        userRemoteConfigs: [[credentialsId: "{credentials}", url: "${repository}"]]
    ])
}