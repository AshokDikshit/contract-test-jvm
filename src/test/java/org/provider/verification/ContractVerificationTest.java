package org.provider.verification;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;

import au.com.dius.pact.provider.junit5.HttpsTestTarget;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider;
import au.com.dius.pact.provider.junitsupport.Provider;
import au.com.dius.pact.provider.junitsupport.State;
import au.com.dius.pact.provider.junitsupport.loader.PactBroker;
import au.com.dius.pact.provider.junitsupport.loader.PactBrokerAuth;
import au.com.dius.pact.provider.junitsupport.loader.PactFolder;

@Provider("ReqResInUsersAPI")
//@PactBroker(url = "http://localhost:9292"
////        ,authentication = @PactBrokerAuth(username = "pact_workshop", password = "pact_workshop")
//)
@PactFolder(value = "target/pacts")
public class ContractVerificationTest {

	@BeforeEach
	void before(PactVerificationContext context) {
//		System.setProperty("pact.verifier.publishResults", "true"); // It can be enabled/disabled from CLI "-Dpact.verifier.publishResults=true"
		context.setTarget(new HttpsTestTarget("reqres.in", 443));
	}

//	@State({ "users api exist" })
//	public void allUsers() {
//	}

	@TestTemplate
	@ExtendWith(PactVerificationInvocationContextProvider.class)
	void pactVerificationTestTemplate(PactVerificationContext context) {
		context.verifyInteraction();
	}

}