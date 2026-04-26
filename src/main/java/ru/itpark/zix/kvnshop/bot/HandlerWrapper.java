package ru.itpark.zix.kvnshop.bot;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.IBotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.message.Message;
import org.telegram.telegrambots.meta.generics.TelegramClient;
import ru.itpark.zix.kvnshop.bot.middlewares.UserInitMiddleware;

@Slf4j
@AllArgsConstructor
public class HandlerWrapper implements IBotCommand {
    private static final String DEFAULT_ERROR_TEXT = "Команда временно недоступна. Попробуйте позже.";

    private final IBotCommand delegate;
    private final UserInitMiddleware userInitMiddleware;

    @Override
    public String getCommandIdentifier() {
        return delegate.getCommandIdentifier();
    }

    @Override
    public String getDescription() {
        return delegate.getDescription();
    }

    @Override
    public void processMessage(TelegramClient client, Message message, String[] arguments) {
        userInitMiddleware.process(message);

        try {
            delegate.processMessage(client, message, arguments);
        } catch (Exception e) {
            log.error("Ошибка в обработчике команды /{}", getCommandIdentifier(), e);
            sendFallback(client, message);
        }
    }

    private void sendFallback(TelegramClient client, Message message) {
        try {
            client.execute(SendMessage.builder()
                    .chatId(message.getChatId())
                    .text(DEFAULT_ERROR_TEXT)
                    .build());
        } catch (Exception sendError) {
            log.error("Не удалось отправить fallback-ответ для /{}", getCommandIdentifier(), sendError);
        }
    }
}


