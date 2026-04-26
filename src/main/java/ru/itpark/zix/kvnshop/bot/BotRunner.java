package ru.itpark.zix.kvnshop.bot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.longpolling.BotSession;
import org.telegram.telegrambots.longpolling.TelegramBotsLongPollingApplication;
import ru.itpark.zix.kvnshop.config.TgConfig;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class BotRunner implements SmartLifecycle {
    private final List<BotSession> botSessions = new ArrayList<>();
    private final TelegramBotsLongPollingApplication tgApp;
    private volatile boolean running;
    private final TgConfig tgConfig;
    private final BotKVN botKVN;

    public BotRunner(TgConfig tgConfig, BotKVN botKVN) {
        this.tgConfig = tgConfig;
        this.botKVN = botKVN;
        this.tgApp = new TelegramBotsLongPollingApplication();
    }

    @Override
    public synchronized void start() {
        if (running) {
            return;
        }

        try {
            Thread.sleep(1500);
            botSessions.add(tgApp.registerBot(tgConfig.getToken(), botKVN));
            running = true;
            log.info("Бот запущен!");
        } catch (Exception e) {
            log.error("Ошибка при инициализации бота: {}", e.getMessage(), e);
        }
    }

    @Override
    public synchronized void stop() {
        botSessions.forEach(BotSession::stop);
        botSessions.clear();
        running = false;
        try {
            tgApp.close();
        } catch (Exception e) {
            log.warn("Ошибка при закрытии tgApp: {}", e.getMessage());
        }

        log.info("Telegram bot stopped");
    }

    @Override
    public void stop(Runnable callback) {
        stop();
        callback.run();
    }

    @Override
    public boolean isRunning() {
        return running;
    }

    @Override
    public boolean isAutoStartup() {
        return true;
    }
}
