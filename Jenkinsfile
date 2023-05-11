pipeline{
    environment{
        DOCKERHUB_CREDENTIALS = credentials('dockerhub')
	}
agent any
	stages{
		stage("Git  pull")
		{
			steps
			{ 
			git url:'https://github.com/tanishkasinghal/Microservices_SPE_Major.git',branch:'master'
			}
		}
		stage("Mavenn Build")
		{
			steps {
                dir('ServiceRegistry') {
                    echo "Building package for Service Registry"
                    sh "mvn clean install"
                }

                dir('ApiGateway') {
                    echo "Building package for Api Gateway"
                    sh "mvn clean install"
                }

                dir('EmployeeService') {
                    echo "Building package for Employee Service"
                    sh "mvn clean install"
                }

                dir('DepartmentService') {
                    echo "Building package for Department Service"
                    sh "mvn clean install"
                }

                dir('LeaveApplicationService') {
                    echo "Building package for Leave Service"
                    sh "mvn clean install"
                }
            }
		}
        stage("Build Image")
		{
			steps
			{ 
                dir('ServiceRegistry') {
                    echo "Building image for Service Registry"
                    sh "docker build -t tanishka23/serviceregistry ."
                }

                dir('ApiGateway') {
                    echo "Building image for Api Gateway"
                    sh "docker build -t tanishka23/apigateway ."
                }

                dir('EmployeeService') {
                    echo "Building image for Employee Service"
                    sh "docker build -t tanishka23/employeeservice ."
                }

                dir('DepartmentService') {
                    echo "Building image for Department Service"
                    sh "docker build -t tanishka23/departmentservice ."
                }

                dir('LeaveApplicationService') {
                    echo "Building image for Leave Service"
                    sh "docker build -t tanishka23/leaveservice ."
                }
                dir('front-end') {
                    echo "Building image for Front End"
                    sh "docker build -t tanishka23/frontend ."
                }
			}
		}
        stage('Login into DockerHub') {
            steps {
                sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
            }
        }
        stage('Push to DockerHub') {
            steps {
                sh 'docker push tanishka23/serviceregistry'
                sh 'docker push tanishka23/apigateway'
                sh 'docker push tanishka23/employeeservice'
                sh 'docker push tanishka23/departmentservice'
                sh 'docker push tanishka23/leaveservice'
                sh 'docker push tanishka23/frontend'
            }
        }
        stage('Delete Docker Image from Local'){
            steps {
                sh 'docker rmi tanishka23/serviceregistry'
                sh 'docker rmi tanishka23/apigateway'
                sh 'docker rmi tanishka23/employeeservice'
                sh 'docker rmi tanishka23/departmentservice'
                sh 'docker rmi tanishka23/leaveservice'
                sh 'docker rmi tanishka23/frontend'
            }
        }
        stage("Ansible Deploy"){
            steps{
                sh "ansible-playbook -i inventory playbook.yml"
            }
        }
	}
	
}