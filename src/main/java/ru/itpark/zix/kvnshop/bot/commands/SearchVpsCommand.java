package ru.itpark.zix.kvnshop.bot.commands;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.chat.Chat;
import org.telegram.telegrambots.meta.generics.TelegramClient;
import ru.itpark.zix.kvnshop.integration.VpsSearchFeignHttpClient;
import ru.itpark.zix.kvnshop.integration.dto.Tariff;
import ru.itpark.zix.kvnshop.integration.dto.TariffSearchRequest;

@Component
public class SearchVpsCommand extends BotCommand {
    private final VpsSearchFeignHttpClient vpsSearchFeignHttpClient;

    public SearchVpsCommand(VpsSearchFeignHttpClient vpsSearchFeignHttpClient) {
        super("vps_search", "Искать VPS");
        this.vpsSearchFeignHttpClient = vpsSearchFeignHttpClient;
    }

    @Override
    public void execute(TelegramClient client, User user, Chat chat, String[] arguments) {
        var vpsResponse = vpsSearchFeignHttpClient.searchVps(
                10,
                0,
                TariffSearchRequest.builder()
                        .cpuCountGt(4)
                        .ramSizeGbGt(7D)
                        .diskSizeGbGt(100)
                        .build());

        var text = !vpsResponse.getTariffs().isEmpty() ?
                "Нашли серверы: \n" + String.join(
                        "\n\n",
                        vpsResponse
                                .getTariffs()
                                .stream()
                                .map(Tariff::toMessage)
                                .toList()
                ) : "Серверы не найдены";

        try {
            client.execute(SendMessage.builder()
                    .chatId(chat.getId())
                    .text(text.substring(0, Math.min(text.length(), 4096)))
                    .build());
        } catch (Exception e) {
            throw new IllegalStateException(
                    "Не удалось отправить ответ для команды /%s".formatted(getCommandIdentifier()), e);
        }
    }
}
