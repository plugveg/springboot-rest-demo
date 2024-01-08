package org.setsuma.springboot.demorest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class JacksonConfig {

	@Bean
	public Jackson2ObjectMapperBuilder configureObjectMapper() {
	    Jackson2ObjectMapperBuilder oMapper = new Jackson2ObjectMapperBuilder();
	    oMapper.failOnUnknownProperties(false);
	    return oMapper;
	}
}
