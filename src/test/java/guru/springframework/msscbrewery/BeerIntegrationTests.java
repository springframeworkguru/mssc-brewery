package guru.springframework.msscbrewery;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import guru.springframework.msscbrewery.model.BeerDto;

/**
 * This simulates end-to-end tests by calling the API via HTTP. Application
 * context is fully initialized.
 * 
 * @author Bruno
 *
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BeerIntegrationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private WebApplicationContext context;

	MockMvc mockMvc;
	JacksonTester<BeerDto> jsonBeer;
	JacksonTester<List<BeerDto>> jsonBeers;
	static String beerLocation;
	static UUID beerId;
	static Integer beerCount;

	@BeforeEach
	private void setUp() {
		JacksonTester.initFields(this, new ObjectMapper());
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	@Order(0)
	@DisplayName("Step 0 - Listing existing beers")
	public void listBeers() throws Exception {
		String beersAsJson = mockMvc.perform(
				get("http://localhost:{port}/api/v2/beers", port))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andReturn().getResponse().getContentAsString();

		List<BeerDto> beers = jsonBeers.parseObject(beersAsJson);

		beerCount = beers.size();
	}

	@Test
	@Order(1)
	@DisplayName("Step 1 - Creating a new Beer")
	public void postBeer() throws Exception {
		BeerDto dto = givenValidBeerDto();
		MockHttpServletResponse response = mockMvc.perform(
				post("http://localhost:{port}/api/v2/beers", port)
						.contentType(MediaType.APPLICATION_JSON)
						.content(jsonBeer.write(dto).getJson()))
				// then
				.andExpect(status().isCreated())
				.andExpect(header().exists("Location"))
				.andReturn().getResponse();

		beerLocation = response.getHeader("Location");
	}

	@Test
	@Order(2)
	@DisplayName("Step 2 - Getting the new Beer")
	public void getBeer() throws Exception {
		String beerAsJson = mockMvc.perform(get(beerLocation))
				.andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString();
		BeerDto dto = jsonBeer.parse(beerAsJson).getObject();

		beerId = dto.getId();
	}

	@Test
	@Order(3)
	@DisplayName("Step 3 - Listing existing beers")
	public void listBeers2() throws Exception {
		String beersAsJson = mockMvc.perform(
				get("http://localhost:{port}/api/v2/beers", port))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andReturn().getResponse().getContentAsString();

		List<BeerDto> beers = jsonBeers.parseObject(beersAsJson);

		assertEquals(beerCount + 1, beers.size());
	}

	private BeerDto givenValidBeerDto() {
		return BeerDto.builder()
				.beerName("postBeer")
				.beerStyle("PIELSEN")
				.quantityToBrew(200)
				.minOnHand(10)
				.build();
	}

}
