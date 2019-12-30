package guru.springframework.msscbrewery.web.controller;

import static org.hamcrest.CoreMatchers.endsWith;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import guru.springframework.msscbrewery.services.BeerService;
import guru.springframework.msscbrewery.web.model.BeerDto;

/**
 * This class runs Unit Tests against BeerController. Application context is not
 * loaded.
 * 
 * @author Bruno
 *
 */
@ExtendWith(MockitoExtension.class)
// @SpringBootTest
public class BeerControllerTests {

	private MockMvc mockMvc;
	private JacksonTester<BeerDto> jsonBeer;

	@Mock
	private BeerService service;

	@InjectMocks
	private BeerControllerV2 controller;

	@BeforeEach
	private void setUp() {
		JacksonTester.initFields(this, new ObjectMapper());
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void getBeersByMockMvc() throws Exception {
		mockMvc.perform(get("/api/v2/beers")).andExpect(status().isOk());
	}

	@Test
	void getBeerByIdMockMvc() {
		UUID beerId = UUID.randomUUID();
		BeerDto beer = givenValidBeerDto();

		when(service.getBeerById(beerId)).thenReturn(beer);

		BeerDto dto = controller.getBeer(beerId);
		assertEquals(beer, dto);
	}

	@Test
	public void postBeerTest() throws Exception {
		// given
		UUID id = UUID.randomUUID();
		BeerDto dto = givenValidBeerDto();
		String validBeerJson = jsonBeer.write(dto)
				.getJson();
		when(service.saveNewBeer(dto)).thenReturn(id);

		// when
		mockMvc.perform(post("/api/v2/beers")
				.contentType(MediaType.APPLICATION_JSON)
				.content(validBeerJson))
				// then
				.andExpect(header().string("Location", endsWith(id.toString())))
				.andReturn().getResponse();

		verify(service).saveNewBeer(dto);
	}

	@Test
	public void putBeerTest() throws Exception {
		// given
		UUID id = UUID.randomUUID();
		BeerDto validBeerDto = givenValidBeerDto();
		String validBeerJson = jsonBeer.write(validBeerDto)
				.getJson();

		// when
		ResultActions result = mockMvc
				.perform(put("/api/v2/beers/{id}", id)
						.contentType(MediaType.APPLICATION_JSON)
						.content(validBeerJson));

		// then
		result.andExpect(status().isNoContent())
				.andReturn().getResponse();
		verify(service).updateExistingBeer(id, validBeerDto);
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
