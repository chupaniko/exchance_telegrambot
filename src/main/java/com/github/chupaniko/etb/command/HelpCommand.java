package com.github.chupaniko.etb.command;

import com.github.chupaniko.etb.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.github.chupaniko.etb.command.CommandName.*;

public class HelpCommand implements Command {

    private final SendBotMessageService sendBotMessageService;

    public static final String HELP_MESSAGE = String.format("<b>Доступные команды</b>\n\n"
                    + "%s - начать работу со мной\n\n"
                    + "%s - приостановить работу со мной\n\n"
                    + "%s - получить помощь в работе со мной\n\n"
                    + "%s -  сконструировать проект\n\n" +
                    "%s - получить описание проекта\n\n",
            START.getCommandName(), STOP.getCommandName(), HELP.getCommandName(), PROJECT_CONSTRUCTOR.getCommandName(), ONEPAGER_PROJECT.getCommandName());

    public HelpCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update, int fieldConstructor, String message) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), HELP_MESSAGE);
    }
}
