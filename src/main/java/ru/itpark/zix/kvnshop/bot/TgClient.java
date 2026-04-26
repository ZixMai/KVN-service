package ru.itpark.zix.kvnshop.bot;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.meta.api.methods.GetMe;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;
import ru.itpark.zix.kvnshop.config.TgConfig;

@Component
public class TgClient {
    public TelegramClient client;

    public TgClient(TgConfig config) {
        this.client = new OkHttpTelegramClient(config.getToken());
    }

    public String getBotUsername() {
        try {
            String botUsername = client.execute(GetMe.builder().build()).getUserName();
            if (botUsername == null || botUsername.isBlank()) {
                throw new IllegalStateException("Не удалось получить username бота через getMe");
            }
            return botUsername;
        } catch (TelegramApiException e) {
            throw new IllegalStateException("Не удалось получить username бота через Telegram getMe", e);
        }
    }
}
