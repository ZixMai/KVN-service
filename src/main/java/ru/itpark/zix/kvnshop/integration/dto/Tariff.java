package ru.itpark.zix.kvnshop.integration.dto;

import lombok.Data;

import java.util.List;

@Data
public class Tariff {
    private Integer id;
    private String hosterId;
    private String hosterName;
    private String hosterUrl;
    private String hosterLogo;
    private String hosterScreenshot;
    private String name;
    private Integer cpuCount;
    private String cpuFreqGhz;
    private String ramSizeGb;
    private Integer diskSizeGb;
    private String diskType;
    private String virtualization;
    private String netTrafficTb;
    private Integer netSpeedMbps;
    private Boolean hasIpv6;
    private Boolean hasTestPeriod;
    private Boolean paymentDaily;
    private List<String> countries;
    private List<String> oss;
    private List<String> paymentOptions;
    private String cost1MonthRub;
    private String cost3MonthRub;
    private String cost6MonthRub;
    private String cost12MonthRub;
    private String cost1MonthUsd;
    private String cost1MonthEur;


    public String toMessage() {
        return """
            🖥 *%s* — %s
            ⚙️ *Характеристики:*
            • CPU: %s ядер × %s ГГц
            • RAM: %s ГБ
            • Диск: %s ГБ (%s)
            • Сеть: %s Мбит/с
            • Трафик: %s
            • IPv6: %s
            • Виртуализация: %s

            🌍 *Страны:* %s
            🐧 *ОС:* %s

            💳 *Оплата:* %s
            %s%s
            💰 *Цена (₽):*
            • 1 мес: %s ₽
            • 3 мес: %s ₽
            • 6 мес: %s ₽
            • 12 мес: %s ₽

            🔗 [Перейти к тарифу](%s)
            """.formatted(
                hosterName,
                name,
                cpuCount, cpuFreqGhz,
                ramSizeGb,
                diskSizeGb, diskType,
                netSpeedMbps,
                formatTraffic(netTrafficTb),
                hasIpv6 ? "✅" : "❌",
                virtualization,
                String.join(", ", countries),
                String.join(", ", oss),
                String.join(", ", paymentOptions),
                hasTestPeriod ? "🎁 *Есть тестовый период*\n" : "",
                paymentDaily ? "📅 *Есть посуточная оплата*\n" : "",
                cost1MonthRub,
                cost3MonthRub,
                cost6MonthRub,
                cost12MonthRub,
                hosterUrl
        );
    }

    private String formatTraffic(String traffic) {
        return "inf".equalsIgnoreCase(traffic) ? "∞ безлимит" : traffic + " ТБ";
    }
}