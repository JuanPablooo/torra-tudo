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
                sh "##############################################START-GIT-CLONE##############################################"
                sh "########START-INFORMATIONS########"
                sh "echo branch:[${branch}] tag:[${tag}] project_name:[${project_name}] "
                sh "echo java -version"
                sh "cat ./settings.xml"
                sh "########END-INFORMATIONS########"
                checkout([$class: 'GitSCM', branches: [[name: "*/${branch}"]], extensions: [], userRemoteConfigs: [[url: 'https://github.com/JuanPablooo/torra-tudo']]])
                sh "##############################################END-GIT-CLONE##############################################"
            }
        }
        stage("Maven Test") {
            steps {
                sh "##############################################START-MAVEN-TEST##############################################"
                configFileProvider([configFile(fileId: 'settings-torra', variable: 'MAVEN_SETTINGS_XML')]) {
                    sh "mvn test -f ${project_name} -U -s $MAVEN_SETTINGS_XML "
                    // sh 'mvn -U --batch-mode -s $MAVEN_SETTINGS_XML clean install -P foo'
                }
                sh "##############################################END-MAVEN-TEST##############################################"
            }
        }

        stage("Maven Build") {
            steps {
                sh "##############################################START-MAVEN-BUILD##############################################"
                sh "mvn clean install -Dmaven.test.skip=true -f ${project_name} -U -s $MAVEN_SETTINGS_XML "
                sh "##############################################END-MAVEN-BUILD##############################################"
            }

        }

       stage("Docker Build") {
            steps{
                script {
                    sh "##############################################START-DOCKER-BUILD##############################################"
                    sh "docker build -t ${project_name} ./${project_name}/"
                    sh "docker tag ${project_name} ${docker_hub_profile}/torra-${project_name}:${tag}"
                    sh "echo imagename: ${docker_hub_profile}/torra-${project_name}:${tag}"
                    sh "##############################################END-DOCKER-BUILD##############################################"
                }
            }
        }
       stage("Docker Push") {
            steps{
                script {
                    sh "##############################################START-DOCKER-PUSH##############################################"
                    withCredentials([string(credentialsId: 'dockerhubpwp', variable: 'dockerhubpwp')]) {
                        sh "docker login -u ${docker_hub_profile} -p ${dockerhubpwp}"
                        sh "docker push ${docker_hub_profile}/torra-${project_name}:${tag}"
                    }
                    sh "##############################################END-DOCKER-PUSH##############################################"
                }
            }
        }
        stage("K8S Deploy") {
            steps {
                script {
                    sh "##############################################START-K8S-DEPLOY##############################################"
                    sh "pwd ls"
                    dir("./${project_name}/k8s") {
                        sh "echo replacing tag [ ${tag} ] image ..."
                        sh "sed -i 's,VERSION_IMAGE_TO_BE_REPLACED,${tag},' ./deployment.yaml"
                        sh "cat ./deployment.yaml"
                        withKubeConfig([credentialsId : 'k8sconfigpwd']) {
                            sh "kubectl apply -f ./deployment.yaml "
                        }
                    }
                    sh "##############################################END-K8S-DEPLOY##############################################"

                }
            }
        }

    }

}