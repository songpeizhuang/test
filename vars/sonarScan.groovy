def call() {
    withSonarQubeEnv('210-sonarqube') {
        def sonarScannerHome = tool '221-scanner'
        //def sonarScannerHome = '/srv/sonar-scanner'
        def sonarCmd = "${sonarScannerHome}/bin/sonar-scanner"
        try {
            def branchConfigFile = "resources/branch-${env.BRANCH_NAME.replaceAll('/', '_')}.properties"
            def generalConfigFile = "resources/sonar-project.properties"
            if (resourceExists(branchConfigFile)) {
                branchConfig = readProperties file: resource(branchConfigFile)
            } elif (resourceExists(generalConfigFile)) {
                branchConfig = readProperties file: resource(generalConfigFile)
			}
			sh "${sonarCmd} -Dproject.settings=${branchConfig}"
        } catch (e) {
            echo "⚠️ 分支配置加载失败: ${e.message}"
        }
    }
}

// 检查资源是否存在
def resourceExists(String path) {
    return getClass().getResourceAsStream("/${path}") != null
}