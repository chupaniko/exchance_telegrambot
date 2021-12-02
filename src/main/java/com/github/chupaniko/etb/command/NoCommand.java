package com.github.chupaniko.etb.command;

import com.github.chupaniko.etb.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class NoCommand implements Command {

    private final SendBotMessageService sendBotMessageService;

    public static final String NO_MESSAGE = "Я поддерживаю команды, начинающиеся со слеша(/).\n"
            + "Чтобы посмотреть список команд введите /help";

    public NoCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update, int fieldConstructor, String message) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), NO_MESSAGE);
    }
}
