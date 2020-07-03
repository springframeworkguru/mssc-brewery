package guru.springframework.msscbrewery.web.model;

import lombok.*;

import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {
    private UUID id;
    private String name;
}
