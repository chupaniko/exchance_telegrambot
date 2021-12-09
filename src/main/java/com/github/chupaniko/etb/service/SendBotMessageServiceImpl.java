package com.github.chupaniko.etb.service;

import com.github.chupaniko.etb.bot.ExchanceTelegramBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
public class SendBotMessageServiceImpl implements SendBotMessageService {

    private final ExchanceTelegramBot exchanceTelegramBot;

    @Autowired
    public SendBotMessageServiceImpl(ExchanceTelegramBot exchanceTelegramBot) {
        this.exchanceTelegramBot = exchanceTelegramBot;
    }

    @Override
    public void sendMessage(String chatId, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.enableHtml(true);
        sendMessage.setText(message + "test");

        try {
            exchanceTelegramBot.execute(sendMessage);
        } catch (TelegramApiException e) {
            //todo add logging to the project.
            e.printStackTrace();
        }
    }
}
