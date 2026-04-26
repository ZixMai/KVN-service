package ru.itpark.zix.kvnshop.config;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("tg-config")
@Data
@ToString
public class TgConfig {
    String token;
}
