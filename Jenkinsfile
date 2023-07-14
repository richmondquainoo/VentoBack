pipeline {
    agent any

    tools{
        maven 'maven_3.9.2'
    }

    stages{
        stage('Build Maven'){
            steps{
                checkout scmGit(branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/richmondquainoo/VentoBack']])
                sh 'mvn clean install'
            }
        }
        stage('Build docker image'){
            steps{
                script{
                    sh 'docker build -t nanaamfohquain/app .'

                }
            }
        }
         stage('Push docker image to hub'){
             steps{
                 script{
                     withCredentials([string(credentialsId: 'dockerhub-pwd', variable: 'dockerHub')]) {
                         sh 'docker login -u nanaamfohquain -p ${dockerHub}'

                         sh 'docker push nanaamfohquain/app:latest'

                     }
                 }
             }
         }
    }


}