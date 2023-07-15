pipeline {
    agent any

    tools{
        maven 'maven_3.9.2'
    }

    stages{
        stage('Initialize'){
           steps{
                slackSend(channel: '#project', message: 'Build Initialization')
            }
        }
        stage('Build Maven'){
            steps{
                slackSend(channel: '#project', color: 'good', message: 'Build Maven Started -VentoApp')
                checkout scmGit(branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/richmondquainoo/VentoBack']])
                sh 'mvn clean install'
            }
        }
        stage('Build docker image'){
            steps{
                slackSend(channel: '#project', color: 'good', message: 'Build Docker image -VentoApp')
                script{
                    sh 'docker build -t nanaamfohquain/app .'

                }
            }
        }
         stage('Push docker image to hub'){
             steps{
                 slackSend(channel: '#project', color: 'good', message: 'Build Docker Push Image -VentoApp')
                 script{
                     withCredentials([string(credentialsId: 'DockerHubCred', variable: 'dockerHubPass')]){
                              sh 'docker login -u nanaamfohquain -p ${dockerHubPass}'

                              sh 'docker push nanaamfohquain/app:latest'
                     }
                 }
             }
         }
          stage('Deploy to Kubernetes'){
              steps{
                  slackSend(channel: '#project', color: 'good', message: 'Deploy to kubernetes -VentoApp')
                  script{
                      kubernetesDeploy (configs: 'k8s-deployment.yaml', kubeconfigId: 'KubernetesPwd')
                      }
                  }
              }
          }
    }