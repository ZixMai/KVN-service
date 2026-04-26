package ru.itpark.zix.kvnshop.integration.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

import java.util.List;

@Data
@Builder
@Setter
public class TariffSearchRequest {

    @JsonProperty("cpu_count__gt")
    public Integer cpuCountGt;

    @JsonProperty("ram_size_gb__gt")
    public Double ramSizeGbGt;

    @JsonProperty("disk_size_gb__gt")
    public Integer diskSizeGbGt;

    @JsonProperty("disk_type")
    public String diskType;

    @JsonProperty("virtualization")
    public String virtualization;

    @JsonProperty("has_ipv6")
    public Boolean hasIpv6;

    @JsonProperty("has_test_period")
    public Boolean hasTestPeriod;

    @JsonProperty("payment_daily")
    public Boolean paymentDaily;

    @JsonProperty("countries__in")
    public List<String> countriesIn;

    @JsonProperty("oss__in")
    public List<String> ossIn;

    @JsonProperty("payment_options__in")
    public List<String> paymentOptionsIn;

    @JsonProperty("cost_1_month_rub__gt")
    public Double cost1MonthRubGt;

    @JsonProperty("cost_1_month_rub__lte")
    public Double cost1MonthRubLte;

    @JsonProperty("hoster_id")
    public String hosterId;

    @JsonProperty("id")
    public Integer id;

    @JsonProperty("order_by")
    public List<String> orderBy;
}
