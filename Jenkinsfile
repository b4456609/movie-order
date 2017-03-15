node {
    stage('Build') {
        sh 'sudo sh ./build.sh ${BUILD_ID}'
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