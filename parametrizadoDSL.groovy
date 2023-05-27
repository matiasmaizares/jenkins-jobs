job('ejemplo2-job-DSL'){
 	description('job dsl de ejemplo')
  	scm{
     	git('https://github.com/macloujulian/jenkins.job.parametrizado.git', 'main')  { node ->
      		node / gitConfigName('matiasmaizares')
        	node / gitConfigEmail('matiasmaizares@gmail.com')
      	}
    }
  	parameters {
    	stringParam('nombre', defaultValue='julian', description='Parametro de cadena para el job boolenao')
 		choiceParam('planeta',['mercurio','venus','tierra','jupiter'])
      	booleanParam('agente',false)
    }
  	triggers{
    	cron('H/7 * * * *')
  	}
  	steps{
    	shell("bash jobscript.sh")
  	}
  	publishers{
    	mailer('matiasmaizares2017@gmail.com', true, true)
      	slackNotifier {
          notifyAborted(true)
          notifyEveryFailure(true)
          notifyNotBuilt(false)
          notifyUnstable(false)
          notifyBackToNormal(true)
          notifySuccess(false)
          notifyRepeatedFailure(false)
          startNotification(false)
          includeTestSummary(false)
          includeCustomMessage(false)
          customMessage(null)
          sendAs(null)
          commitInfoChoice('NONE')
          teamDomain(null)
          authToken(null)
        }
      
    }
}
