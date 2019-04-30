package com.example;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.text.DateFormat;
import java.util.List;

/**
 * Created by ARUN on 10/12/2017.
 */
@Configuration
public class Config extends WebMvcConfigurerAdapter {
    public ObjectMapper demoAppObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.registerModule(new Jdk8Module());
        objectMapper.registerModule(new JodaModule());
        DateFormat dateFormat = new ISO8601DateFormat();
        objectMapper.setDateFormat(dateFormat);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_ABSENT);
        // TODO: temp - remove to fail on unknown property - we need to migrate or move api if required
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);

        return objectMapper;
    }
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        final MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(demoAppObjectMapper());
        converters.add(converter);
        super.configureMessageConverters(converters);
    }
}
