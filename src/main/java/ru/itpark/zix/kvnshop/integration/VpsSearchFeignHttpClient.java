package ru.itpark.zix.kvnshop.integration;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import ru.itpark.zix.kvnshop.integration.dto.TariffSearchRequest;
import ru.itpark.zix.kvnshop.integration.dto.TariffSearchResponse;

import java.util.Optional;

@FeignClient(
        name = "vps-search",
        url = "${spring.cloud.openfeign.client.config.vps-search.url}",
        configuration = {VpsSearchFeignHttpRequestInterceptor.class}
)
public interface VpsSearchFeignHttpClient {
    @PostMapping("/public/api/v1/tariffs/search")
    TariffSearchResponse searchVps(
        @RequestParam int limit,
        @RequestParam int cursor,
        @RequestBody TariffSearchRequest request
    );
}
