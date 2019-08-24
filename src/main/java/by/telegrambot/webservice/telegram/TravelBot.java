package by.telegrambot.webservice.telegram;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import java.util.ArrayList;
import java.util.List;

public class TravelBot extends TelegramLongPollingBot {
    private final static String SHOWCITIES = "/showcities";
    private final static String SHOWPLACES = "/showplaces";
    private final static String BOTNAME = "Pettyzebot";
    private final static String BOTTOKEN = "807113567:AAGFycxZgS2-Wc8KzmBexVnkljly9Tp_hL4";

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        if (message.hasText()) {
            SendMessage sendMessage = new SendMessage().setChatId(message.getChatId()).setText("dsadsasd");
            try {
                setButton(sendMessage);
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    private void sendMessage(Message message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId());
        sendMessage.setText(message.getText());
        sendMessage.setReplyToMessageId(message.getMessageId());
        try {
            setButton(sendMessage);
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void pickMessage(Message message) {
        String text = message.getText().toLowerCase();
        switch (text) {
            case SHOWCITIES:

                break;
            case SHOWPLACES:

                break;
            default:
        }
    }

    private void setButton(SendMessage sendMessage) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.add(new KeyboardButton(SHOWCITIES));
        keyboardRow.add(new KeyboardButton(SHOWPLACES));
        keyboardRowList.add(keyboardRow);
        replyKeyboardMarkup.setKeyboard(keyboardRowList);
    }

    @Override
    public String getBotUsername() {
        return BOTNAME;
    }

    @Override
    public String getBotToken() {
        return BOTTOKEN;
    }
}
