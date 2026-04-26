package ru.itpark.zix.kvnshop.integration;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.json.JsonMapper;
import feign.RequestInterceptor;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.springframework.context.annotation.Bean;

public class VpsSearchFeignHttpRequestInterceptor {
    @Bean
    public Decoder feignDecoder() {
        ObjectMapper mapper = JsonMapper.builder()
                .propertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .build();

        return new JacksonDecoder(mapper);
    }

    @Bean
    public Encoder feignEncoder() {
        ObjectMapper mapper = JsonMapper.builder()
                .propertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)
                .defaultPropertyInclusion(
                        JsonInclude.Value.construct(
                                JsonInclude.Include.NON_NULL,
                                JsonInclude.Include.NON_NULL
                        )
                )
                .build();

        return new JacksonEncoder(mapper);
    }
    @Bean
    public RequestInterceptor vpnSearchFeignRequestInterceptor() {
        return template -> template.header(
                "Content-Type", "application/json"
        );
    }
}
