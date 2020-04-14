package com.asyouwish.CriteriaAndSpecification;

import com.asyouwish.CriteriaAndSpecification.dto.SearchDTO;
import com.asyouwish.CriteriaAndSpecification.entity.Customer;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.commons.io.IOUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CriteriaAndSpecificationApplicationTests {

	@LocalServerPort
	private Integer port;

	@Autowired
	ObjectMapper objectMapper;

	@Before
	public void setUp() {
		RestAssured.port = port;
		RestAssured.basePath = "/criteriaandspecification";
	}

	@Test
	public void testsearchCustomersOK() throws IOException {

		SearchDTO requestBody = objectMapper.readValue(
				IOUtils.toString(this.getClass().getResourceAsStream("/json/customer.json"), StandardCharsets.UTF_8),
				SearchDTO.class);

		Response response = RestAssured.given().contentType("application/json")
				.body(requestBody).post("/criteria/searchCustomer");

		Assert.assertEquals(HttpStatus.OK.value(),response.statusCode());
		List<Customer> customerListResponse = objectMapper.readValue(response.asString(), new TypeReference<List<Customer>>() {
		});
		Assert.assertEquals(customerListResponse.size(), 2);

	}

	@Test
	public void testsearchCustomersNoContent() throws IOException {

		SearchDTO requestBody = objectMapper.readValue(
				IOUtils.toString(this.getClass().getResourceAsStream("/json/customer.json"), StandardCharsets.UTF_8),
				SearchDTO.class);

		requestBody.setSearchInput("qwerty");
		requestBody.setSortProperties(Collections.emptyList());
		Response response = RestAssured.given().contentType("application/json")
				.body(requestBody).post("/criteria/searchCustomer");

		Assert.assertEquals(HttpStatus.NO_CONTENT.value(),response.statusCode());
	}

}
