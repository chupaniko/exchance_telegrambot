package com.github.chupaniko.etb.command;

import com.github.chupaniko.etb.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class StopCommand implements Command {

     private final SendBotMessageService sendBotMessageService;

     public static final String STOP_MESSAGE = "Работа приостановлена.\nДля возобновления работы введите /start";

     public StopCommand(SendBotMessageService sendBotMessageService) {
          this.sendBotMessageService = sendBotMessageService;
     }

     @Override
     public void execute(Update update, int fieldConstructor, String message) {
          sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), STOP_MESSAGE);
     }
}
