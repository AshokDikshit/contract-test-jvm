package org.consumer.interactions;

import au.com.dius.pact.consumer.dsl.DslPart;
import au.com.dius.pact.consumer.dsl.PactDslJsonArray;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.core.model.RequestResponsePact;

public class UsersInteractions {
	DslPart pactSingleUser = new PactDslJsonBody().integerType("id").stringType("email").stringType("first_name")
			.stringType("last_name").stringType("avatar");

	DslPart pactAllUsersJson = new PactDslJsonBody().integerType("page").integerType("per_page").integerType("total")
			.integerType("total_pages").minArrayLike("data", 1, pactSingleUser).close();

	public RequestResponsePact allUsers(PactDslWithProvider builder) {
		return builder
//				.given("<providerStates>") //if this is enabled, this value must be passed in @State() annotation in Verification test.
				.uponReceiving("get all users")
				.path("/api/users")
				.willRespondWith()
				.status(200)
				.body(pactAllUsersJson)
				.toPact();
	}

	public RequestResponsePact singleUsers(PactDslWithProvider builder) {
		return builder
//				.given("<providerStates>") //if this is enabled, this value must be passed in @State() annotation in Verification test.
				.uponReceiving("get single user")
				.path("/api/users/2")
				.willRespondWith()
				.status(200)
				.body(new PactDslJsonBody()
						.object("data", pactSingleUser)
						)
				.toPact();
	}

}
