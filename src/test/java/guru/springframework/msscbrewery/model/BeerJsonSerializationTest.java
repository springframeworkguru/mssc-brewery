package guru.springframework.msscbrewery.model;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.json.JacksonTester;

import com.fasterxml.jackson.databind.ObjectMapper;

public class BeerJsonSerializationTest {

	JacksonTester<Beer> jsonBeer;

	public BeerJsonSerializationTest() {
		JacksonTester.initFields(this, new ObjectMapper());
	}

	@Test
	void testSerialization() throws IOException {
		Beer beer = Beer.builder()
				.id(UUID.randomUUID())
				.beerName("Pale Ale")
				.beerStyle(BeerStyleEnum.PURO_MALTE)
				.price(new BigDecimal("10.5"))
				.quantityToBrew(10)
				.minOnHand(1)
				.upc(5L)
				.lastModifiedDate(LocalDateTime.now())
				.createdDate(LocalDateTime.of(1989, 04, 22, 22, 00))
				.brewDateTime(OffsetDateTime.now())
				.build();
		String json = jsonBeer.write(beer).getJson();
		System.out.println(json);
	}

}
