package hu.config;

import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.InitializationError;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

public class MySpringJUnit4ClassRunner extends SpringJUnit4ClassRunner {

	private MyTestClassListener InstanceSetupListener;

	public MySpringJUnit4ClassRunner(Class<?> clazz) throws InitializationError  {
	        super(clazz);
	    }

	@Override
	protected Object createTest() throws Exception {
		Object test = super.createTest();
		// Mivel ez a metődust többször is meghívhatja a JUnit ezért elérjük, hogy csak egyszer fusson le.
		if (test instanceof MyTestClassListener && InstanceSetupListener == null) {
			InstanceSetupListener = (MyTestClassListener) test;
			InstanceSetupListener.beforeClassSetup();
		}
		return test;
	}

	@Override
	public void run(RunNotifier notifier) {
		super.run(notifier);
		if (InstanceSetupListener != null){
			InstanceSetupListener.afterClassSetup();
		}
	}
}
