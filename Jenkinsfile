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
                dir("ms-order-project/") {
                    sh 'mvn clean'
                    sh 'mvn install -Dmaven.test.skip=true'
                } 
            }
        }
        stage('Tests') {
            steps {
                echo 'Tests'
                 dir("ms-order-project/") {
                    sh 'mvn test'
                } 
            }
        }
        stage('Release') {
            steps {
                echo 'Release'
                 dir("ms-order-project/") {
                    sh 'mvn package -Dmaven.test.skip=true'
                } 
            }
        }
    }
    post { 
        always { 
            echo 'I will always say Hello again!'
        }
    }
}