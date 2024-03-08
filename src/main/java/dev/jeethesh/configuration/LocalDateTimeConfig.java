package dev.jeethesh.configuration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Configuration
public class LocalDateTimeConfig {
	
	 @Bean
	    public ObjectMapper objectMapper() {
	        ObjectMapper objectMapper = new ObjectMapper();
	        SimpleModule module = new SimpleModule();
	        module.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer());
	        module.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer());
	        objectMapper.registerModule(module);
	        objectMapper.registerModule(new JavaTimeModule());
	        return objectMapper;
	    }

	    @Bean
	    public DateTimeFormatter dateTimeFormatter() {
	        return DateTimeFormatter.ISO_LOCAL_DATE_TIME;
	    }
}