pipeline {
    agent any
    environment {
         PATH = "/usr/lib/jvm/jdk-17/bin:$PATH"
         JAVA_HOME="/usr/lib/jvm/jdk-17/"
         registry = "mousse2023/repo"
		 registryCredential = 'jenkinsdockerhub'
		 dockerImage = ''
    }
        stages {
            stage('recuperation du projet'){
                steps {
                     git branch: 'master',
                     credentialsId: 'jenkinsgithub',
                     url :'git@github.com:mousseta/springwebservice.git'
                }
            }
            stage("Compile") {
            steps {
                sh "./gradlew compileJava"
            }
        }
        stage("test") {
            steps {
                sh "./gradlew test"
            }
        }
        stage('SonarQube analysis') {
            steps {
                 withSonarQubeEnv('sonarqube') {
                    sh "./gradlew sonarqube"
                  }
                 }
        }
        stage("Quality gate") {
              steps {
                  waitForQualityGate abortPipeline: true
                   }
        }
         stage("package") {
            steps {
                sh "./gradlew build"
            }
        }
        stage("Docker build") {
			steps{
				script {
					sh "docker build -t 172.17.0.3:5000/imagespring ."
				}
			}
		}

        stage("Docker push") {
			steps{
				script {

					sh "docker push 172.17.0.3:5000/imagespring"
				}
			}
        }
        }
}







