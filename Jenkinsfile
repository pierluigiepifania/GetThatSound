pipeline{
	agent any

	environment {
		scannerHome = tool 'sqrscanner'
	}
	stages {
		stage ('stage 1') {
			steps {
				echo 'Hello World'
			}
		}
		stage ('SonarQube Analysis') {
			steps {
				script {
					def scanner = tool 'sqrscanner'
					withSonarQubeEnv ('server-sonarqube-gts') {
						sh "${scannerHome}/bin/sonar-scanner -X"
					}
				}
			}
		}
	}
}
