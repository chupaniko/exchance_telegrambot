package com.github.chupaniko.etb.command;

import com.github.chupaniko.etb.service.SendBotMessageService;
import com.google.common.collect.ImmutableMap;

import static com.github.chupaniko.etb.command.CommandName.*;
import static com.google.common.collect.ImmutableClassToInstanceMap.builder;

public class CommandContainer {

    private final ImmutableMap<String, Command> commandMap;
    private final Command unknownCommand;

    public CommandContainer(SendBotMessageService sendBotMessageService) {
        commandMap = ImmutableMap.<String, Command>builder()
                .put(START.getCommandName(), new StartCommand(sendBotMessageService))
                .put(STOP.getCommandName(), new StopCommand(sendBotMessageService))
                .put(HELP.getCommandName(), new HelpCommand(sendBotMessageService))
                .put(NO.getCommandName(), new NoCommand(sendBotMessageService))
                .build();
        unknownCommand = new UnknownCommand(sendBotMessageService);
    }
    public Command retrieveCommand(String commandIdentifier) {
        return commandMap.getOrDefault(commandIdentifier, unknownCommand);
    }
}
