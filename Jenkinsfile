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
 					 -Dsonar.projectKey=javaexpressproject  \
 					 -Dsonar.host.url=http://3.71.78.75:9000 \
  					 -Dsonar.login=9bc131dfbf179c0fad4a02588e0fe9e882a7af32
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
        
    	stage("kubernetes deployment"){
    		steps {
                sh 'kubectl apply -f k8s-spring-boot-deployment.yml'
            }
    	} 
    }
}
