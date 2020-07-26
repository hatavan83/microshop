package dev.hata.microshop.item.client.config;

import dev.hata.microshop.item.client.service.ItemServiceClient;
import dev.hata.microshop.item.client.service.impl.ItemServiceClientImpl;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import javax.validation.constraints.NotBlank;

@Configuration
@EnableConfigurationProperties(ItemClientAutoConfig.ItemServiceProperties.class)
public class ItemClientAutoConfig {

    @Getter
    @Setter
    @ConfigurationProperties("spring.application.item-service")
    public class ItemServiceProperties {
        @NotBlank
        private String url;
    }

    @Bean
    @ConditionalOnProperty(prefix = "spring.application.item-service", name = "url")
    public ItemServiceClient itemService(ItemServiceProperties config) {
        return new ItemServiceClientImpl(WebClient.builder()
                .baseUrl(config.getUrl())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build());
    }
}
