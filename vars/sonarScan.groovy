def call() {
    withSonarQubeEnv() {
        def config = readProperties file: 'resources/sonar.properties'
        def sonarScannerHome = tool '221-scanner'
        //def sonarScannerHome = '/srv/sonar-scanner'
        def sonarCmd = "${sonarScannerHome}/bin/sonar-scanner"
        
        def cmdArgs = [
            "-Dsonar.projectKey=${config.SONAR_PROJECT_KEY}",
            "-Dsonar.projectName=${config.SONAR_PROJECT_NAME}",
            "-Dsonar.projectVersion=${env.BUILD_NUMBER}",
            "-Dsonar.sources=${config.SONAR_SOURCES ?: '.'}",
            "-Dsonar.sourceEncoding=UTF-8",
            "-Dsonar.branch.name=${env.BRANCH_NAME}",
            "-Dsonar.exclusions=${config.SONAR_EXCLUSIONS}"
        ]
        
        //// 添加额外参数
        //if (config.SONAR_EXTRA_ARGS) {
        //    cmdArgs += config.SONAR_EXTRA_ARGS.split(' ')
        //}
        
        // 执行扫描
        sh "${sonarCmd} ${cmdArgs.join(' ')}"
    }
}