pipeline {
    agent any

    environment {
        TAG = "${env.BUILD_NUMBER}"
    }

    stages {
        stage("Build") {
            steps {
                echo "Building.."
                withMaven(maven: 'maven') {
                    sh "mvn clean package"
                }
            }
        }
        stage("docker") {
            steps {
                sh 'docker-compose build'
                sh 'docker-compose push'
            }
        }

        stage("deploy") {
            steps {
                script {
                    if (sh(script: 'docker service ls', returnStdout: true).contains("demo-app")) {
                        sh "docker service update --image registry:5000/demo-app:${env.BUILD_NUMBER} demo-app"
                    } else {
                        sh "docker service create --name demo-app --constraint=node.role==worker --publish published=80,target=9000 registry:5000/demo-app:${env.BUILD_NUMBER}"
                    }
                }

            }
        }
    }

    post {
        cleanup {
            deleteDir()
        }
    }
}