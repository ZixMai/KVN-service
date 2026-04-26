package ru.itpark.zix.kvnshop.bot.middlewares;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.message.Message;
import ru.itpark.zix.kvnshop.domain.User;
import ru.itpark.zix.kvnshop.service.UserService;

@AllArgsConstructor
@Component
public class UserInitMiddleware {
    private final UserService userService;

    public void process(Message message) {
        var newUser = User.builder()
                .tgId(message.getFrom().getId())
                .username(message.getFrom().getUserName())
                .build();
        userService.upsert(newUser);
    }
}

