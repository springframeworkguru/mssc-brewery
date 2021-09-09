package guru.springframework.msscbrewery.web.model;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Curso Spring. Tarea 1.
 * 09/09/2021
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDto {
	
	private UUID id;
	private String name;

}
