package ru.itpark.zix.kvnshop.bot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.extensions.bots.commandbot.CommandLongPollingTelegramBot;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.IBotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.itpark.zix.kvnshop.bot.middlewares.UserInitMiddleware;

import java.util.List;

@Slf4j
@Component
public class BotKVN extends CommandLongPollingTelegramBot {
    private final TgClient telegramClient;
    private final UserInitMiddleware userInitMiddleware;

    private BotKVN(TgClient telegramClient,
                   UserInitMiddleware userInitMiddleware,
                   List<IBotCommand> botCommands) {
        super(telegramClient.client, true, telegramClient::getBotUsername);

        this.telegramClient = telegramClient;
        this.userInitMiddleware = userInitMiddleware;

        botCommands.forEach(this::registerSafe);
    }

    private void registerSafe(IBotCommand command) {
        register(new HandlerWrapper(command, userInitMiddleware));
    }

    @Override
    public void processNonCommandUpdate(Update update) {
        if (!update.hasMessage() || !update.getMessage().hasText()) {
            return;
        }

        userInitMiddleware.process(update.getMessage());
        String text = update.getMessage().getText();
        if (text == null || text.isBlank()) {
            return;
        }

        if (text.startsWith("/")) {
            try {
                telegramClient.client.execute(SendMessage.builder()
                        .chatId(update.getMessage().getChatId())
                        .text("Попался сотрудник РКН! Неизвестная команда")
                        .build());
            } catch (Exception e) {
                log.error("Ошибка при отправке сообщения: {}", e.getMessage(), e);
            }
        }

//        Концепт реализации модели разговора
//        var userConversationState = "{user: goy}";
//        getRegisteredCommands()
//                .stream()
//                .filter(command -> command.getCommandIdentifier().equals(userConversationState.command))
//                .findFirst()
//                .orElseThrow()
//                .processMessage(telegramClient, update.getMessage(), new String[]{userConversationState});

    }
}
