package com.example.dkmh;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.example.dkmh.controller.ApiProductController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
//@WebMvcTest(ApiProductController.class)
@AutoConfigureMockMvc
class DkmhApplicationTests {
	@Autowired
	MockMvc mvc;

	@WithMockUser(roles = "USER")
	@Test
	void contextLoads() throws Exception {
		this.mvc.perform(post("http://localhost:8080/product/saveProduct")).andExpect(status().isOk());
	}

}
