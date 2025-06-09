def call() {
    withSonarQubeEnv('210-sonarqube') {
        def sonarScannerHome = tool '221-scanner'
        //def sonarScannerHome = '/srv/sonar-scanner'
        def sonarCmd = "${sonarScannerHome}/bin/sonar-scanner"
        def branchConfig = null
        try {
            def branchConfigFile = "resources/branch-${env.BRANCH_NAME.replaceAll('/', '_')}.properties"
            def generalConfigFile = "resources/sonar-project.properties"
            if (fileExists(branchConfigFile)) {
                branchConfig = readProperties file: "${branchConfigFile}"
            } else if (fileExists(generalConfigFile)) {
                branchConfig = readProperties file: "${generalConfigFile}"
                sh "${sonarCmd} -Dproject.settings=${branchConfig}"
            }
        } catch (e) {
            echo "⚠️ 分支配置加载失败: ${e.message}"
        }
        //if (branchConfig != null && !branchConfig.toString().trim().isEmpty()) {
            // 变量非空（包括非空字符串）
        //    sh "${sonarCmd} -Dproject.settings=${branchConfig}"
        //} else {
        //    echo "变量branchConfig为空值"
        //}
    }
}