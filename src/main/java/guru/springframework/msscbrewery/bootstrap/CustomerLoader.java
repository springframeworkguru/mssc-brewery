package guru.springframework.msscbrewery.bootstrap;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import guru.springframework.msscbrewery.domain.Customer;
import guru.springframework.msscbrewery.repository.CustomerRepository;
import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class CustomerLoader implements CommandLineRunner {

	private static final String[] NAMES = new String[] {
			"Pernilongo do Agreste",
			"Mutuca do Igarapé",
			"Macaco da Árvore",
			"Hipopótamo do Lago",
			"Coco do Coqueiro",
			"Planta do Jardim",
			"Código do Desenvolvedor",
			"Tutorial da Web",
			"Pergunta do Fórum",
			"Celular do Geek",
			"Livro do Estudioso",
			"Catraca do Busão",
			"Ingresso do Cinema"
	};

	@Autowired
	private CustomerRepository repository;

	@Override
	public void run(String... args) throws Exception {
		this.loadCustomers();
	}

	private void loadCustomers() {
		if (repository.count() == 0) {
			for (String name : NAMES) {
				repository.save(Customer.builder()
						.id(UUID.randomUUID())
						.name(name)
						.build());
			}

			log.debug("Loaded {} Customers into Database", NAMES.length);
		}

	}

}
