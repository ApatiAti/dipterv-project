package hu.startup;


import org.apache.log4j.Logger;
import javax.annotation.PostConstruct;



public class StartUp{ 
//public class StartUp implements ApplicationListener<ContextRefreshedEvent> {

	private static final Logger logger = Logger.getLogger(StartUp.class);

	@PostConstruct
	public void startVoteTimers() {
		logger.info("Startup bean lefutott");
	}

}