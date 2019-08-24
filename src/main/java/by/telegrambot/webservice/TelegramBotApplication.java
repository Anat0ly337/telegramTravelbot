package by.telegrambot.webservice;

import by.telegrambot.webservice.telegram.TravelBot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

@SpringBootApplication
public class TelegramBotApplication {

    public static void main(String[] args) {
        SpringApplication.run(TelegramBotApplication.class, args);
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new TravelBot());
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }
}

