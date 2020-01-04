package guru.springframework.msscbrewery.bootstrap;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import guru.springframework.msscbrewery.model.Beer;
import guru.springframework.msscbrewery.model.BeerStyleEnum;
import guru.springframework.msscbrewery.repository.BeerRepository;
import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class BeerLoader implements CommandLineRunner {

	@Autowired
	private BeerRepository beerRepository;

	@Override
	public void run(String... args) throws Exception {
		this.loadBeerObjects();
	}

	private void loadBeerObjects() {
		if (beerRepository.count() == 0) {
			beerRepository.save(Beer.builder()
					.beerName("Jimbo's Ale")
					.beerStyle(BeerStyleEnum.PIELSEN)
					.quantityToBrew(200)
					.minOnHand(12)
					.upc(10101L)
					.price(new BigDecimal(12.95))
					.build());

			beerRepository.save(Beer.builder()
					.beerName("Igarapé Amazônico")
					.beerStyle(BeerStyleEnum.PURO_MALTE)
					.quantityToBrew(100)
					.minOnHand(6)
					.upc(20202L)
					.price(new BigDecimal(6.75))
					.build());

			log.debug("Loaded {} Beers into Database", 2);
		}
	}

}
