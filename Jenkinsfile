pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git branch: 'master', url: 'https://github.com/sjuu07/TestAutomationPOCNew.git'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean compile'
            }
        }

        stage('Run Tests') {
            steps {
                bat 'mvn test'
            }
        }

        stage('Publish Test Results') {
            steps {
                junit '**/target/surefire-reports/*.xml'  // For JUnit or TestNG test reports
            }
        }
    }

    post {
        always {
            echo 'Pipeline completed!'
        }
        failure {
            mail to: 'your-email@example.com',
                 subject: "Pipeline Failed: ${currentBuild.fullDisplayName}",
                 body: "Something went wrong. Check Jenkins for details."
        }
    }
}
