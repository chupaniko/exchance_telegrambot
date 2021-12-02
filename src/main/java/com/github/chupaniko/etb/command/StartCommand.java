package com.github.chupaniko.etb.command;

import com.github.chupaniko.etb.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class StartCommand implements Command{
    private final SendBotMessageService sendBotMessageService;

    public final String START_MESSAGE = "Здравствуйте! Я конструктор проектов Exchance.\nМои команды\n/start\n/stop\n/help\n" +
            "/project_constructor\n" +
            "/get_project_description";

    public StartCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update, int fieldConstructor, String message) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), START_MESSAGE);
    }
}
