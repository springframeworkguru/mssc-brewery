package guru.springframework.msscbrewery.web.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

import guru.springframework.msscbrewery.web.model.Beer;
import guru.springframework.msscbrewery.web.model.BeerDto;

public class BeerMapper {

	public static final BeerMapper INSTANCE = new BeerMapper();

	private final ModelMapper mapper;

	public BeerMapper() {
		mapper = new ModelMapper();

		configureEntityToDto();
		configureDtoToEntity();
	}

	private void configureDtoToEntity() {
		TypeMap<BeerDto, Beer> typeMap = mapper.createTypeMap(BeerDto.class,
				Beer.class);
		typeMap.addMappings(mapper -> {
			mapper.skip(Beer::setId);
		});
	}

	private void configureEntityToDto() {
	}

	public BeerDto toDto(Beer beer) {
		return mapper.map(beer, BeerDto.class);
	}

	public Beer toEntity(BeerDto dto) {
		return mapper.map(dto, Beer.class);
	}

	public void copyProperties(BeerDto dto, Beer beer) {
		mapper.map(dto, beer);
	}

	public void copyProperties(Beer beer, BeerDto dto) {
		mapper.map(beer, dto);
	}

}
