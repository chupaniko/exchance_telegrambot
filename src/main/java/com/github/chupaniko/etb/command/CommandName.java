package com.github.chupaniko.etb.command;

public enum CommandName {

    PROJECT_CONSTRUCTOR("/project_constructor"),
    VACANCY_CONSTRUCTOR("/vacancy_constructor"),
    RESUME_CONSTRUCTOR("/resume_constructor"),
    ONEPAGER_PROJECT("/get_project_description"),
    START("/start"),
    STOP("/stop"),
    HELP("/help"),
    NO("no command");

    private final String commandName;

    CommandName(String commandName) {
        this.commandName = commandName;
    }

    public String getCommandName() {
        return commandName;
    }
}
