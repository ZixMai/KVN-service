package ru.itpark.zix.kvnshop.integration.dto;

import lombok.Data;

import java.util.List;

@Data
public class TariffSearchResponse {
    private List<Tariff> tariffs;
}
