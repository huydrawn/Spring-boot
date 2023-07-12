package com.example.dkmh;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class MaximumSessionsTests {
	@Autowired
	private MockMvc mvc;

	@Test
	void loginOnSecondLoginThenPreventLogin() throws Exception {
		MvcResult mvcResult = this.mvc.perform(formLogin()).andExpect(SecurityMockMvcResultMatchers.authenticated())
				.andReturn();

		MockHttpSession firstLoginSession = (MockHttpSession) mvcResult.getRequest().getSession();

		this.mvc.perform(MockMvcRequestBuilders.get("/").session(firstLoginSession))
				.andExpect(SecurityMockMvcResultMatchers.authenticated());

		// second login is prevented
		this.mvc.perform(formLogin()).andExpect(SecurityMockMvcResultMatchers.unauthenticated());

		// first session is still valid
		this.mvc.perform(MockMvcRequestBuilders.get("/").session(firstLoginSession))
				.andExpect(SecurityMockMvcResultMatchers.authenticated());
	}

}