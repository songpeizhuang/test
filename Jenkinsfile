@Library('shared-library') _

pipeline {
    agent { label '' }
    stage ('SonarQube Analysis') {
        steps {
             script { 
                checkout scm
                sonarScan(loadConfig)
             }
        }
    }
}