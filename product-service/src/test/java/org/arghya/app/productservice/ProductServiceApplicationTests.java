package org.arghya.app.productservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.arghya.app.productservice.controller.ProductController;
import org.arghya.app.productservice.dto.ProductRequest;
import org.arghya.app.productservice.repository.ProductRepository;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;

import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class ProductServiceApplicationTests {

	@Container
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.2");
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;
	/*@InjectMocks
	ProductController productController;*/
	@Autowired
	private ProductRepository productRepository;

	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
		dynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
	}

	/*@BeforeEach
	public void initService() {
		mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
	}*/

	@Test
	public void shouldCreateProduct() throws Exception {
		ProductRequest productRequest = getProductRequest();
		mockMvc.perform(MockMvcRequestBuilders.post("/online-shopping/product")
		       .contentType(MediaType.APPLICATION_JSON)
		       .content(objectMapper.writeValueAsString(productRequest)))
               .andExpect(status().isCreated());
		Assert.assertEquals(1, productRepository.findAll().size());
	}


	private ProductRequest getProductRequest() {
		return ProductRequest.builder()
				.name("laptop")
				.description("dell laptop")
				.price(new BigDecimal(50000.00))
				.build();
	}

}
