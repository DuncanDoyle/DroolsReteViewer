package com.sample;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
 * This is a sample class to launch a rule.
 */
public class DroolsTest {

	public static final void main(String[] args) {
		KieSession kSession = null;
		try {
			// load up the knowledge base
			KieServices ks = KieServices.Factory.get();
			KieContainer kContainer = ks.getKieClasspathContainer();
			kSession = kContainer.newKieSession("ksession-rules");

			// go !
			Message message = new Message();
			message.setMessage("Hello World");
			message.setStatus(Message.HELLO);
			kSession.insert(message);
			kSession.fireAllRules();
			/*
			 * Give the timer some time to activate our timer rule. Stupid solution, but we're not building a system here, this is just to
			 * demonstrate an issue in the Rete Tree viewer :-)
			 */
			Thread.sleep(15000);
			kSession.fireAllRules();
		} catch (Throwable t) {
			t.printStackTrace();
		} finally {
			if (kSession != null) {
				kSession.dispose();
			}
		}
	}

	public static class Message {

		public static final int HELLO = 0;
		public static final int GOODBYE = 1;

		private String message;

		private int status;

		public String getMessage() {
			return this.message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public int getStatus() {
			return this.status;
		}

		public void setStatus(int status) {
			this.status = status;
		}

	}

}
