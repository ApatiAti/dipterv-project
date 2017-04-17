package hu.startup;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.annotation.PostConstruct;



public class StartUp{ 
//public class StartUp implements ApplicationListener<ContextRefreshedEvent> {

	private static final Logger logger = LoggerFactory.getLogger(StartUp.class);

	@PostConstruct
	public void startVoteTimers() {
		logger.info("Startup bean lefutott");
	}

}