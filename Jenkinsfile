pipeline {
    agent none 
    stages {
        stage('Unit Test') {
            agent none
            steps {
                withEnv(['TEST_DB_HOST=192.168.33.10']) {
                    sh 'sudo docker run -d -p 27017:27017 --name=testmongo mongo'
                    sh './gradlew check'
                    sh 'docker kill testmongo'
                    sh 'docker rm -v testmongo'
                }
                //upload mgp
                withEnv(['MGP=192.168.33.10:8080']) {
                    sh '''curl -i -X POST \
                            -H "Content-Type: multipart/form-data" \
                            -F "file=@./mpd.json" \
                            http://$MGP/api/upload'''
                }
                //upload pact dsl to pact broker
                withEnv(['PACT_URL=http://192.168.33.10:8880/']) {
                    sh './gradlew pactPublish'
                }
            }
        }
        stage('Build') {
            agent none
            steps {
                sh 'sudo sh ./build.sh ${BUILD_ID}'
            }
        }
        stage('ServiceTest'){
            build 'movie-service-test'
        }
        stage('UAT'){
            build 'movie-uat'
        }
        stage('Deploy') {
            sh 'sudo docker run -p 8761:8761 -d movie-order:latest'
        }
    }
}