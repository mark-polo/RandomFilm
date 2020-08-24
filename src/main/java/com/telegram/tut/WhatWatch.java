package com.telegram.tut;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.toIntExact;

public class WhatWatch  extends TelegramLongPollingBot {

    public void sendMsg (Message mess , String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(mess.getChatId().toString());
        sendMessage.setReplyToMessageId(mess.getMessageId());
        sendMessage.setText(text);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    public void setButtons (SendMessage sendM) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        List<InlineKeyboardButton> row = new ArrayList<>();
        row.add(new InlineKeyboardButton().setText("IMDB").setCallbackData("i_am_going").setUrl("https://www.imdb.com/title/" + FilmGlobalVariables.IMDB_ID));
        row.add(new InlineKeyboardButton().setText("Search in internet").setCallbackData("i_am_pass").setUrl("https://www.google.com/search?newwindow=1&hl=ru&source=hp&ei=dOc_X4DiDc3zgAbbz4WQCA&q=" + FilmGlobalVariables.FILM_NAME + "&oq="  + FilmGlobalVariables.FILM_NAME + "&gs_lcp=CgZwc3ktYWIQAzIFCC4QkwIyAggAMgIIADICCAAyAggAMgIIADICCAAyAggAMgIIADICCABKBQgEEgExSgUIBxIBMUoFCAgSATFQg8YIWIPGCGDwzwhoAHAAeACAAR2IAR2SAQExmAEAoAECoAEBqgEHZ3dzLXdpeg&sclient=psy-ab&ved=0ahUKEwiA7NTLzazrAhXNOcAKHdtnAYIQ4dUDCAc&uact=5"));
        rows.add(row);
        inlineKeyboardMarkup.setKeyboard(rows);
        sendM.setReplyMarkup(inlineKeyboardMarkup);
    }

    @Override
    public void onUpdateReceived(Update update) {

        if(update.hasMessage() && update.getMessage().hasText()) {
            IMDBModel model = new IMDBModel();
            Message message = update.getMessage();
            long chat_id = update.getMessage().getChatId();
            SendMessage send = new SendMessage().setChatId(chat_id);

            FilmGlobalVariables.USER_ID = chat_id;  //// for MongoDb

            switch (message.getText()) {
                case "/help":
                    sendMsg(message , "Can i help you");
                    break;
                case "whatwatch" :
                    sendMsg(message , "Choose what do you want watching. Film or tv show");
                    break;
                case "film" :
                case "Film" :
                case "фільм" :
                case "Фільм" :
                case "стрічка" :
                case "Стрічка" :
                case "映画" :   /// japan
                case "电影" :  /// china
                    try {
                        send.setText(Film.film(model));
                        setButtons(send);
                        execute(send);
                    } catch (TelegramApiException | IOException e) {
                        e.printStackTrace();
                        sendMsg(message , "Try again");
                    }
                    break;
                case "tv show" :
                    sendMsg(message , "Sherlock"); /// tv show function
                    break;
                default:
                    sendMsg(message , "Nothing");
            }

        } else if (update.hasCallbackQuery()) {
            String call_data = update.getCallbackQuery().getData();
            long message_id = update.getCallbackQuery().getMessage().getMessageId();
            long chat = update.getCallbackQuery().getMessage().getChatId();

            if(call_data.equals("i_am_going")) {
                EditMessageText edit = new EditMessageText()
                        .setChatId(chat)
                        .setMessageId(toIntExact(message_id))
                        .setText("Cong");

                try {
                    execute(edit);
                    //FilmMongoDb.check(toIntExact(chat), "watched" , FilmGlobalVariables.FILM_NAME);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            } else if(call_data.equals("i_am_pass")) {
                EditMessageText edit = new EditMessageText()
                        .setChatId(chat)
                        .setMessageId(toIntExact(message_id))
                        .setText("Watch now");

                try {
                    execute(edit);
                    //FilmMongoDb.check(toIntExact(chat), "not watched" , FilmGlobalVariables.FILM_NAME);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }

        }

    }

    @Override
    public String getBotUsername() { return "whatWatchBot_bot by mrm"; }

    @Override
    public String getBotToken() { return "1257508030:AAFzB7WhPLdBpNuddAyD_eEz4sS1JxejSSs"; }
}