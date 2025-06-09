@Library('shared-library') _

pipeline {
    agent { label 'docker平台' }
    stages {
        stage('SCM') {
            checkout scm
        }
        stage ('SonarQube Analysis') {
            steps {
                 script { 
                    sonarScan()
                 }
            }
        }
    }
}