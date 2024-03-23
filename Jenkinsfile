def docker_hub_profile = "juanpablooo"
def tag = env.BUILD_ID
def project_name = "${project_name}"
def branch = "${branch}"

pipeline {
    agent any
    tools {
        maven "maven"
    }
    stages {
        stage("Git clone") {
            steps {
                sh "echo branch is ${branch}"
                checkout([$class: 'GitSCM', branches: [[name: "*/${branch}"]], extensions: [], userRemoteConfigs: [[url: 'https://github.com/JuanPablooo/torra-tudo']]])
            }
        }
        stage("Maven Test") {
            steps {
                sh "mvn test -f ${project_name} -U"
            }
        }

        stage("Maven Build") {
            steps {
                sh "mvn clean install -Dmaven.test.skip=true -f ${project_name} -U"
            }

        }

       stage("Docker Build") {
            steps{
                script {
                    sh "docker build -t ${project_name} ./${project_name}/"
                    sh "docker tag ${project_name} ${docker_hub_profile}/torra-${project_name}:${tag}"
                    sh "echo imagename: ${docker_hub_profile}/torra-${project_name}:${tag}"
                }
            }
        }
       stage("Docker Push") {
            steps{
                script {
                    withCredentials([string(credentialsId: 'dockerhubpwp', variable: 'dockerhubpwp')]) {
                        sh "docker login -u ${docker_hub_profile} -p ${dockerhubpwp}"
                        sh "docker push ${docker_hub_profile}/torra-${project_name}:${tag}"
                    }
                }
            }
        }
        stage("K8S Deploy") {
            steps {
                script{
                    sh "pwd ls"
                    dir("./${project_name}/k8s") {
                        sh "echo replacing tag [ ${tag} ] image ..."
                        sh "sed -i 's,VERSION_IMAGE_TO_BE_REPLACED,${tag},' ./deployment.yaml"
                        sh "cat ./deployment.yaml"
                        withKubeConfig([credentialsId : 'k8sconfigpwd']) {
                            sh "kubectl apply -f ./deployment.yaml "
                        }
                    }

                }
            }
        }

    }

}