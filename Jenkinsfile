pipeline {
    agent any

    environment {
        DOCKER_CREDENTIALS = credentials('docker-repo-credential')
        DOCKER_USERNAME = "${DOCKER_CREDENTIALS_USR}"
        GITHUB_TOKEN = credentials('github-access-token')
        SSH_CREDENTIALS = credentials('flex-server-pem')
        REMOTE_USER = credentials('remote-user')
        BASTION_HOST = credentials('bastion-host')
        REMOTE_HOST = credentials('dev-investment-host')
        SLACK_CHANNEL = '#backend-jenkins'
        IMAGE_NAME = "${DOCKER_USERNAME}/flex-be-investment"
        IMAGE_TAG = "${BUILD_NUMBER}"
    }

    stages {
        stage('Checkout') {
            steps {
                script {
                    checkout scm
                }
            }
        }

        stage('Build') {
            steps {
                script {
                    slackSend(channel: SLACK_CHANNEL, message: "🏗️ INVESTMENT Build #${env.BUILD_NUMBER} is starting...")
                    sh 'chmod +x gradlew'
                    sh './gradlew clean assemble -x test'
                }
            }
            post {
                success {
                    echo 'Gradle build success'
                    slackSend(channel: SLACK_CHANNEL, message: "✅ INVESTMENT build SUCCEED for Build #${env.BUILD_NUMBER}.")
                }
                failure {
                    echo 'Gradle build failed'
                    slackSend(channel: SLACK_CHANNEL, message: "⛔️ INVESTMENT build FAILED for Build #${env.BUILD_NUMBER}.")
                }
            }
        }

        stage('Docker Build & Push') {
            steps {
                script {
                    def dockerImage = docker.build("${IMAGE_NAME}:${IMAGE_TAG}")
                    docker.withRegistry('https://registry.hub.docker.com', 'docker-repo-credential') {
                        dockerImage.push()
                        dockerImage.push('latest')
                    }
                    slackSend(channel: SLACK_CHANNEL, message: "🐳 INVESTMENT Docker image built and pushed for Build #${env.BUILD_NUMBER}.")
                }
            }
        }

        stage('Deploy to Remote Server') {
            steps {
                sshagent(credentials: ['flex-server-pem']) {
                    script {
                        sh """
                            ssh -J ${REMOTE_USER}@${BASTION_HOST} ${REMOTE_USER}@${REMOTE_HOST} '
                                set -e

                                # 환경 변수 설정
                                export IMAGE_TAG=${IMAGE_TAG}

                                docker compose -f docker-compose-investment.yml up -d --no-deps investment-service

                                # Docker Compose 파일에 IMAGE_TAG 적용
                                sed -i "s|image: ${IMAGE_NAME}:.*|image: ${IMAGE_NAME}:${IMAGE_TAG}|" docker-compose-investment.yml


                                docker compose -f docker-compose-investment.yml pull
                                docker compose -f docker-compose-investment.yml up -d
                                docker compose -f docker-compose-investment.yml ps
                            '
                        """
                        slackSend(channel: SLACK_CHANNEL, message: "🚀 INVESTMENT Deployment SUCCEED for Build #${env.BUILD_NUMBER}.")
                    }
                }
            }
            post {
                success {
                    echo "Deployment completed successfully."
                }
                failure {
                    slackSend(channel: SLACK_CHANNEL, message: "⛔️ INVESTMENT Deployment FAILED for Build #${env.BUILD_NUMBER}.")
                }
            }
        }
    }
}