package guru.springframework.msscbrewery.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;

import guru.springframework.msscbrewery.model.Beer;
import guru.springframework.msscbrewery.model.BeerDto;
import guru.springframework.msscbrewery.model.BeerStyleEnum;

class BeerTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testBeanUtils() {
		for (int i = 0; i < 1000; i++) {
			Beer beer = getValidBeer();

			BeerDto dto = new BeerDto();
			BeanUtils.copyProperties(beer, dto);
			assertEquals(beer.getId(), dto.getId());

			beer = new Beer();
			BeanUtils.copyProperties(dto, beer);
			assertEquals(dto.getId(), beer.getId());
		}
	}

	private Beer getValidBeer() {
		return Beer.builder().id(UUID.randomUUID()).upc(10L)
				.beerStyle(BeerStyleEnum.PIELSEN).build();
	}

	@Test
	void testModelMapper() {
		for (int i = 0; i < 1000; i++) {
			Beer beer = getValidBeer();

			ModelMapper mapper = new ModelMapper();
			BeerDto dto = mapper.map(beer, BeerDto.class);
			assertEquals(beer.getId(), dto.getId());

			Beer to = mapper.map(dto, Beer.class);
			assertEquals(dto.getId(), to.getId());
		}
	}

}
