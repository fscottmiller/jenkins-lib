#!/usr/bin/env groovy

def call(String branch, String repository) {
    echo "${branch}"
    echo "${repository}"
    checkout([
        $class: 'GitSCM',
        branches: [[name: "*/${branch}"]],
        doGenerateSubmoduleConfigurations: false,
        userRemoteConfigs: [[url: "${repository}"]]
    ])
}