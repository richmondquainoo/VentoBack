pipeline {
    agent any

    tools{
        maven 'maven_3.9.2'
    }

    stages{

        stage('Build Maven'){

            steps{
                slackSend(channel: '#project', message: 'Build Maven Started - Vento app')
                checkout scmGit(branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/richmondquainoo/VentoBack']])
                sh 'mvn clean install'
            }
        }
        stage('Build docker image'){
            steps{
                slackSend(channel: '#project', message: 'Build docker image - Vento app')
                script{
                    sh 'docker build -t nanaamfohquain/app .'
                }
            }
            post {
                failure {
                  slackSend channel: "#project", message: "Build Failed - Vento app"
            }
        }
         stage('Push docker image to hub'){
             steps{
                 slackSend(channel: '#project', message: 'Pushing docker image to dockerhub- Vento app')
                 script{
                     withCredentials([string(credentialsId: 'DockerHubCred', variable: 'dockerHubPass')]){
                              sh 'docker login -u nanaamfohquain -p ${dockerHubPass}'

                              sh 'docker push nanaamfohquain/app:latest'
                     }
                 }
             }
             post {
                 failure {
                   slackSend channel: "#project", message: "Build Failed for image push - Vento app"
             }
         }
    }


}