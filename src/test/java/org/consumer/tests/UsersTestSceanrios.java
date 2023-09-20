package org.consumer.tests;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.apache.hc.client5.http.fluent.Request;
import org.apache.hc.core5.http.HttpResponse;
import org.consumer.interactions.UsersInteractions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.PactSpecVersion;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;

@ExtendWith(PactConsumerTestExt.class)
@PactTestFor(providerName = "ReqResInUsersAPI")
public class UsersTestSceanrios {
	
	public UsersInteractions usersInteractions = new UsersInteractions();
	
	@Pact(consumer = "TestUsersAPI")
	public RequestResponsePact getSingleUsers(PactDslWithProvider builder) {
		return usersInteractions.singleUsers(builder);
	}
	
	@Pact(consumer = "TestUsersAPI")
	public RequestResponsePact getAllUsers(PactDslWithProvider builder) {
		return usersInteractions.allUsers(builder);
	}
	
	@Test
	@PactTestFor(pactMethod = "getSingleUsers", pactVersion = PactSpecVersion.V3)
	void testPactSingle(MockServer mockServer) throws IOException {
		HttpResponse httpResponse = Request.get(mockServer.getUrl() + "/api/users/2").execute().returnResponse();
		assertEquals(httpResponse.getCode(), 200);
	}
	
	@Test
	@PactTestFor(pactMethod = "getAllUsers", pactVersion = PactSpecVersion.V3)
	void testPactAll(MockServer mockServer, MockServer mockServer2) throws IOException {
		HttpResponse httpResponse = Request.get(mockServer.getUrl() + "/api/users").execute().returnResponse();
		assertEquals(httpResponse.getCode(), 200);
	}
	
}
