package guru.springframework.msscbrewery;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import guru.springframework.msscbrewery.web.model.BeerDto;

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
	static String beerLocation;
	static UUID beerId;

	@BeforeEach
	private void setUp() {
		JacksonTester.initFields(this, new ObjectMapper());
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
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

	private BeerDto givenValidBeerDto() {
		return BeerDto.builder()
				.beerName("postBeer")
				.beerStyle("PIELSEN")
				.quantityToBrew(200)
				.minOnHand(10)
				.build();
	}

}
