pipeline {
    agent any

    tools {
        maven "maven default"
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'master', 
                    url: 'https://github.com/ualhmis2026-hmis-lovers/observenestenuevoroboshSesion07'
            }
        }
        
        stage('SonarQube Analysis') {
            steps {
                // Inyecta la configuración del servidor SonarQube definida en Jenkins
                withSonarQubeEnv('SonarQube-Jgl') {
                    sh 'mvn sonar:sonar'
                }
            }
        }

        stage("Quality Gate") {
            steps {
                // Espera hasta 5 minutos a que SonarQube devuelva el resultado del análisis
                timeout(time: 5, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }

        stage('Build & Test') {
            steps {
                sh "mvn clean package"
            }
            post {
                always {
                    // Publica resultados de tests unitarios incluso si fallan
                    junit testResults: '**/target/surefire-reports/TEST-*.xml', allowEmptyResults: true
                }
                success {
                    // Publica cobertura de JaCoCo solo si el build fue exitoso
                    jacoco execPattern: 'target/jacoco.exec', classPattern: 'target/classes'
                }
            }
        }

        stage('Analysis') {
            steps {
                // Ejecuta el sitio de Maven y el chequeo de dependencias
                sh "mvn site -Ddependency-check.failBuildOnCVSS=11 -DossindexAnalyzerEnabled=false"
            }
            post {
                always {
                    // Publica todos los informes de estática aunque haya warnings (que ponen el build en Inestable)
                    dependencyCheckPublisher pattern: 'target/site/dependency-check-report.xml'
                    recordIssues enabledForFailure: true, tool: checkStyle()
                    recordIssues enabledForFailure: true, tool: pmdParser()
                    recordIssues enabledForFailure: true, tool: cpd()
                    recordIssues enabledForFailure: true, tool: spotBugs()
                }
            }
        }

        stage('Documentation') {
            steps {
                sh "mvn javadoc:javadoc javadoc:aggregate" 
            }
            post {
                always {
                    // Se usa always porque si el estado es UNSTABLE por el stage anterior,
                    // el bloque 'success' no se ejecutaría y perderías el acceso a la doc.
                    javadoc javadocDir: 'target/site/apidocs', keepAll: true
                    
                    publishHTML(target: [
                        allowAntFiles: false, 
                        alwaysLinkToLastBuild: true, 
                        keepAll: true, 
                        reportDir: 'target/site', 
                        reportFiles: 'index.html', 
                        reportName: 'Maven Site'
                    ])
                }
            }
        }
    }
}