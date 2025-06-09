@Library('shared-library') _

pipeline {
    agent { label 'docker平台' }
    stages {
        stage ('SonarQube Analysis') {
            steps {
                 script { 
                    checkout scm
                    sonarScan(loadConfig)
                 }
            }
        }
    }
}