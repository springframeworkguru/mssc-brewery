package guru.springframework.msscbrewery.model;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.json.JacksonTester;

import com.fasterxml.jackson.databind.ObjectMapper;

public class BeerJsonDeserializationTest {

	JacksonTester<Beer> jsonBeer;

	public BeerJsonDeserializationTest() {
		JacksonTester.initFields(this, new ObjectMapper());
	}

	@Test
	void testDeserialization() throws IOException {
		String json = "{\"id\":\"b4eaf878-e25e-4b59-a233-acde1e2bc256\",\"version\":null,\"createdDate\":\"1989-04-22T22:00:00\",\"lastModifiedDate\":\"2020-01-03T23:48:46.1735763\",\"brewDateTime\":\"2020-01-03T23:48:46.1735763-04:00\",\"beerName\":\"Pale Ale\",\"beerStyle\":\"PURO_MALTE\",\"upc\":5,\"price\":10.5,\"minOnHand\":1,\"quantityToBrew\":10}";
		Beer beer = jsonBeer.parse(json).getObject();
		System.out.println(beer);
	}

}
