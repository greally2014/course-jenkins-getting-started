pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/greally2014/jgsu-spring-petclinic.git'
            }
        }
        stage('Build') {
            steps {
                bat "./mvnw clean package"
            }
            post {
                always {
                    junit '*target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.jar'
                }
            }
        }
    }
}
