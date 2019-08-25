package by.telegrambot.webservice.telegram;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
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
    private final static String HELP = "/help";
    private final static String BOTNAME = "Pettyzebot";
    private final static String BOTTOKEN = "807113567:AAGFycxZgS2-Wc8KzmBexVnkljly9Tp_hL4";
    private final static String COMMANDS = "Чтобы посмотреть описание места /place/{место}" + "" +
            "Чтобы посмотреть города /showcities" + "" +
            "Чтобы посмотреть места /showplaces" + "" +
            "Чтобы посмотреть места в городе /placesbycity/{город}";
    private final RestTemplate restTemplate = new RestTemplate();
    private ResponseEntity<ArrayList> response;


    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        if (message.hasText()) {
            pickMessage(message);
        }
    }

    //Удобнее было бы сделать через базу данных, где хранится ключ значение на каждую команду, но т.к. проект небольшой, поэтому "нахардкодил" через switchcase
    private void pickMessage(Message message) {
        String text = message.getText().toLowerCase();
        switch (text) {
            case SHOWCITIES:
                response = restTemplate.getForEntity("http://localhost:8080/city/allcities", ArrayList.class);
                if (response.getStatusCodeValue() == 200) {
                    sendMessage(message, parseJsonObject(response));
                }
                break;
            case SHOWPLACES:
                response = restTemplate.getForEntity("http://localhost:8080/cityinfo/allplaces", ArrayList.class);
                if (response.getStatusCodeValue() == 200) {
                    sendMessage(message, parseJsonObject(response));
                }
                break;
            case HELP:
                sendMessage(message, COMMANDS);
                break;
            default:
                if (text.startsWith("/placesbycity")) {
                    response = restTemplate.getForEntity("http://localhost:8080/cityinfo" + text, ArrayList.class);
                    if (response.getStatusCodeValue() == 200) {
                        sendMessage(message, parseJsonObject(response));
                    }
                } else if (text.startsWith("/place")) {
                    ResponseEntity<String> respons = restTemplate.getForEntity("http://localhost:8080/cityinfo" + text, String.class);
                    if (respons.getStatusCodeValue() == 200) {
                        sendMessage(message, respons.getBody());
                    }
                }
        }
    }

    private void sendMessage(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId());
        sendMessage.setText(text);
        sendMessage.setReplyToMessageId(message.getMessageId());
        try {
            setButton(sendMessage);
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private String parseJsonObject(ResponseEntity response) {
        JSONObject jsonObject = new JSONObject(response);
        JSONArray myresponse = jsonObject.getJSONArray("body");
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < myresponse.length(); i++) {
            stringBuilder.append(myresponse.getJSONObject(i).get("name") + "\n");
        }
        return stringBuilder.toString();
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
        keyboardRow.add(new KeyboardButton((HELP)));
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
