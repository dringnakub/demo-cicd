pipeline {
  agent any
  stages {
    stage('install dependency') {
      steps {
        sh 'cd frontEnd && npm install'
      }
    }

    stage('run unit test') {
      parallel {
        stage('code analysis frontend') {
          steps {
            sh 'cd frontEnd && npm run test'
          }
        }
        stage('code analysis backend') {
          steps {
            sh 'cd backEnd && ./mvnw clean test'
          }
        }
      }
    }
  }
}