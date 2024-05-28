pipeline {
   agent any 
   tools {
        maven "MAVEN"
     
    }
    stages {
		stage('Checkout') { 
            steps {
                git 'https://github.com/javaexpresschannel/springboot_docker_k8s_sonar.git'
            }
        }
        stage('Compile') { 
            steps {
                sh "mvn clean compile"
            }
        }
        stage('Build') { 
            
            steps {
                sh "mvn package"
            }
        }        
        stage('sonar') { 
            steps {
                sh '''
               mvn sonar:sonar \
 					 -Dsonar.projectKey=javaexpress-sonar \
 					 -Dsonar.host.url=http://3.70.96.109:9000 \
  					 -Dsonar.login=d2c8fec69fd0f3fdf398986c57732db42aeff861
                '''
            }
        } 
        stage('Docker Build'){
            steps {
                sh 'docker build -t javaexpress/springboot_docker_k8s_sonar:latest .'
            }
        }   
        stage('Docker Login'){
            
            steps {
                 withCredentials([string(credentialsId: 'DOCKER_HUB_PASSWORD', variable: 'PASSWORD')]) {
        			sh 'docker login -u javaexpress -p $PASSWORD'
   		 		}
            }                
        }
        stage('Docker Push'){
            steps {
                sh 'docker push javaexpress/springboot_docker_k8s_sonar:latest'
            }
        } 
        stage('Archving') { 
            steps {
                 archiveArtifacts '**/target/*.jar'
            }
        }
    	stage("kubernetes deployment"){
    		steps {
                sh 'kubectl apply -f k8s-spring-boot-deployment.yml'
            }
    	} 
    }
}
