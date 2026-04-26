package ru.itpark.zix.kvnshop.bot.commands;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.chat.Chat;
import org.telegram.telegrambots.meta.generics.TelegramClient;

@Component
public class StartCommand extends BotCommand {

	public StartCommand() {
		super("start", "Запустить бота");
	}

	@Override
	public void execute(TelegramClient client, User user, Chat chat, String[] arguments) {
		String text = "Привет, " + user.getFirstName() + "!";

		try {
			client.execute(SendMessage.builder()
					.chatId(chat.getId())
					.text(text)
					.build());
		} catch (Exception e) {
            throw new IllegalStateException(
                    "Не удалось отправить ответ для команды /%s".formatted(getCommandIdentifier()), e);
        }
	}
}
