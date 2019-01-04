#!/usr/bin/env groovy

def call(repository, branch) {
    gitClone(repository, branch)
	readConfig()
}

def test() {
	
}