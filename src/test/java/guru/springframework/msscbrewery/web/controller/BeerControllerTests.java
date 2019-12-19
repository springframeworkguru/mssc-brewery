package guru.springframework.msscbrewery.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import guru.springframework.msscbrewery.services.BeerService;
import guru.springframework.msscbrewery.web.model.BeerDto;

@ExtendWith(MockitoExtension.class)
//@SpringBootTest
public class BeerControllerTests {

	private MockMvc mockMvc;

	@Mock
	private BeerService service;

	@InjectMocks
	private BeerControllerV2 beerController;

	private JacksonTester<BeerDto> jsonBeer;

	@BeforeEach
	private void setUp() {
		JacksonTester.initFields(this, new ObjectMapper());
		mockMvc = MockMvcBuilders.standaloneSetup(beerController).build();
	}

	@Test
	public void getBeersByMockMvc() throws Exception {
		mockMvc.perform(get("/api/v2/beers")).andExpect(status().isOk());
	}

}
