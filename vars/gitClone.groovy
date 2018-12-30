#!/usr/bin/env groovy

def call(string branch, string credentials, string repository) {
    checkout([
        $class: 'GitSCM',
        branches: [[name: "*/${branch}"]],
        doGenerateSubmoduleConfigurations: false,
        userRemoteConfigs: [[credentialsId: "{credentials}", url: "${repository}"]]
    ])
}