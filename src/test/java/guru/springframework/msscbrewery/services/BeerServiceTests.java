package guru.springframework.msscbrewery.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import guru.springframework.msscbrewery.repository.BeerRepository;
import guru.springframework.msscbrewery.web.mapper.BeerMapper;
import guru.springframework.msscbrewery.web.model.Beer;
import guru.springframework.msscbrewery.web.model.BeerDto;
import guru.springframework.msscbrewery.web.model.BeerStyleEnum;

/**
 * This class runs Unit Tests against BeerService. 
 * Application context is not loaded.
 * 
 * @author Bruno
 *
 */
@ExtendWith(MockitoExtension.class)
public class BeerServiceTests {

	UUID beerId;

	@InjectMocks
	BeerServiceImpl beerService;

	@Mock
	BeerRepository beerRepository;

	@BeforeEach
	void setUpEach() {
		beerId = UUID.randomUUID();
	}

	@Test
	void testDeleteBeerIllegalArgumentException() {
		assertThrows(IllegalArgumentException.class,
				() -> beerService.deleteBeer(null));
	}

	@Test
	void testDeleteBeer() {
		beerService.deleteBeer(beerId);
		verify(beerRepository).deleteById(beerId);
	}

	@Test
	void testGetBeerByIdIllegalArgumentException() {
		assertThrows(IllegalArgumentException.class,
				() -> beerService.getBeerById(null));
	}

	@Test
	void testGetBeerByIdNoSuchElementException() {
		when(beerRepository.findById(beerId))
				.thenReturn(Optional.ofNullable(null));

		assertThrows(NoSuchElementException.class,
				() -> beerService.getBeerById(beerId));
	}

	@Test
	void testGetBeerById() {
		when(beerRepository.findById(beerId))
				.thenReturn(Optional.of(generateBeer(beerId)));

		BeerDto dto = beerService.getBeerById(beerId);
		assertEquals(beerId, dto.getId(), "Id");
	}

	@Test
	void testList() {
		when(beerRepository.findAll()).thenReturn(Collections.emptyList());

		Collection<Beer> beers = beerService.list();
		verify(beerRepository).findAll();
		assertEquals(beers, Collections.emptyList());
	}

	@Test
	void testSaveNewBeer() {
		BeerDto dto = generateBeerDto();
		Beer beerToSave = BeerMapper.INSTANCE.toEntity(dto);
		Beer savedBeer = generateBeer(beerId);
		lenient().when(beerRepository.save(beerToSave)).thenReturn(savedBeer);

		UUID newId = beerService.saveNewBeer(dto);
		verify(beerRepository).save(beerToSave);
		assertEquals(beerId, newId, "Same Id");
	}

	@Test
	void updateExistingBeerIllegalArgumentException() {
		assertThrows(IllegalArgumentException.class,
				() -> beerService.updateExistingBeer(null, generateBeerDto()));
	}

	@Test
	void updateExistingBeerNoSuchElementException() {
		when(beerRepository.findById(beerId))
				.thenThrow(NoSuchElementException.class);

		assertThrows(NoSuchElementException.class,
				() -> beerService.updateExistingBeer(beerId, null));
	}

	@Test
	void updateExistingBeer() {
		Beer beerToFind = generateBeer(beerId);
		lenient().when(beerRepository.findById(beerId))
				.thenReturn(Optional.of(beerToFind));

		BeerDto dto = generateBeerDto();
		beerService.updateExistingBeer(beerId, dto);

		verify(beerRepository).findById(beerId);
		verify(beerRepository).save(beerToFind);
	}

	private Beer generateBeer(UUID id) {
		if (id == null) {
			id = UUID.randomUUID();
		}
		return Beer.builder()
				.id(id)
				.beerName("Test Beer2")
				.beerStyle(BeerStyleEnum.PIELSEN)
				.quantityToBrew(200)
				.minOnHand(10)
				.price(new BigDecimal("7.5"))
				.upc(13L)
				.build();
	}

	private BeerDto generateBeerDto() {
		return BeerDto.builder()
				.beerName("Test Beer")
				.beerStyle("PIELSEN")
				.quantityToBrew(200)
				.minOnHand(10)
				.price(new BigDecimal("7.5"))
				.upc(13L)
				.build();
	}

}
