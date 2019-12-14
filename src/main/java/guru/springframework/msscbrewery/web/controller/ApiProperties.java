package guru.springframework.msscbrewery.web.controller;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

import lombok.Data;

@Data
@ApplicationScope
@ConfigurationProperties(prefix = "sfg.brewery.api")
@Component
public class ApiProperties {
	
	private String beerPath;
	
	private String customerPath;

}
