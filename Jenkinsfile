pipeline {
    agent any

    tools {
        maven 'maven'
    }

    stages {

        stage('git clone') {

            steps {
                checkout scm;
            }
        }

        stage('maven build') {
            steps {
                sh "mvn clean package -DskipTests=true"
            }
        }


        stage('unit test') {
            steps {
                script {
                    sh 'mvn surefire:test'
                    junit '**/target/surefire-reports/TEST-*.xml'
                }
            }
        }

        // Integration Test 추가 예정

        stage('build docker image & deploy docker hub') {
            steps {
                script {
                    sh "docker build -t ${DOCKER_IMAGE} ."
                    sh "docker push ${DOCKER_IMAGE}"
                }
            }
        }

        stage('deploy') {
            steps {
                script {
                    sh "ssh -p ${DEPLOY_SERVER_PORT} ${DEPLOY_SERVER_IP} ${DEPLOY_SCRIPT_PATH}"
                }
            }
        }
    }
}
