pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                echo 'Checkout'
                checkout([$class: 'GitSCM', branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/marcosab10/fastrack']]])
            }
        }
        stage('Build') {
            steps {
                echo 'Build'
                sh 'make clean'
            }
        }
        stage('Tests') {
            steps {
                echo 'Tests'
            }
        }
        stage('Release') {
            steps {
                echo 'Release'
            }
        }
    }
    post { 
        always { 
            echo 'I will always say Hello again!'
        }
    }
}