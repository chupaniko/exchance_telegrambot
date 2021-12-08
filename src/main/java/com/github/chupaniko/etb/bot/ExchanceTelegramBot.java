package com.github.chupaniko.etb.bot;

import com.github.chupaniko.etb.command.*;
import com.github.chupaniko.etb.service.SendBotMessageServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.regex.Pattern;

@Component
public class ExchanceTelegramBot extends TelegramLongPollingBot {

    public static String COMMAND_PREFIX = "/";

    public boolean[] commandId = {false, false, false, false, false, false, false};
    public int[] fieldConstructor = {0, 0, 0};
    private PcCommand pcCommand;
    private VcCommand vcCommand;
    private RcCommand rcCommand;
    private StartCommand startCommand;
    private StopCommand stopCommand;
    private HelpCommand helpCommand;
    private NoCommand noCommand;

    private int user;

    private String command;
    private int fieldConstructor_;
    private boolean fieldUpdate = false;

    @Value("${bot.username}")
    private String username;

    @Value("${bot.token}")
    private String token;

    //private final CommandContainer commandContainer;
    private final SendBotMessageServiceImpl sendBotMessageService;

    public ExchanceTelegramBot() {
        //this.commandContainer = new CommandContainer(new SendBotMessageServiceImpl(this));
        this.sendBotMessageService = new SendBotMessageServiceImpl(this);
    }

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String message = update.getMessage().getText().trim();
            String chatId = update.getMessage().getChatId().toString();

            Pattern digit_pattern = Pattern.compile("\\d+");

            if (message.startsWith(COMMAND_PREFIX)) {
                command = message.split(" ")[0].toLowerCase();
                //commandContainer.retrieveCommand(commandIdentifier).execute(update);
                switch (command) {
                    case "/project_constructor":
                        pcCommand = new PcCommand(sendBotMessageService);
                        fieldConstructor[0] = 0;
                        pcCommand.execute(update, fieldConstructor[0]++, "");
                        pcCommand.execute(update, fieldConstructor[0]++, "");
                        break;
                    case "/vacancy_constructor":
                        commandId[1] = true;
                        //vcCommand = new VcCommand(sendBotMessageService);
                        break;
                    case "/resume_constructor":
                        commandId[2] = true;
                        break;
                    case "/start":
                        commandId[3] = true;
                        startCommand = new StartCommand(sendBotMessageService);
                        startCommand.execute(update, 0, "");
                        break;
                    case "/stop":
                        commandId[4] = true;
                        stopCommand = new StopCommand(sendBotMessageService);
                        stopCommand.execute(update, 0, "");
                        break;
                    case "/help":
                        commandId[5] = true;
                        helpCommand = new HelpCommand(sendBotMessageService);
                        helpCommand.execute(update, 0, "");
                        break;
                    case "/get_project_description":
                        SendMessage sm = new SendMessage();
                        sm.setChatId(chatId);
                        sm.setText(pcCommand.getOnePager());
                        try {
                            execute(sm);
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    default:
                        break;
                }
            } else if (digit_pattern.matcher(message).matches()) {
                switch (command) {
                    case "/project_constructor":
                        if (fieldUpdate) {
                            pcCommand.answers[fieldConstructor[0] - 1] = message;
                            fieldUpdate = false;
                            SendMessage sm = new SendMessage();
                            sm.setChatId(chatId);
                            sm.setText(pcCommand.getOnePager());
                            try {
                                execute(sm);
                            } catch (TelegramApiException e) {
                                e.printStackTrace();
                            }
                        }
                        if (fieldConstructor[0] == 11
                                || fieldConstructor[0] == 13
                                || fieldConstructor[0] == 20) {
                            pcCommand.execute(update, fieldConstructor[0]++, message);
                        } else {
                            int k = Integer.parseInt(message);
                            if (k > 0 && k <= 30) {
                                fieldUpdate = true;
                                fieldConstructor[0] = k;
                                pcCommand.execute(update, fieldConstructor[0], "");
                                /*SendMessage sm = new SendMessage();
                                sm.setChatId(chatId);
                                sm.setText(pcCommand.getOnePager());
                                try {
                                    execute(sm);
                                } catch (TelegramApiException e) {
                                    e.printStackTrace();
                                }*/
                                /*fieldConstructor[0] = 0;
                                command = "";*/
                            }
                        }
                        break;
                    case "/vacancy_constructor":
                        commandId[1] = true;
                        //vcCommand = new VcCommand(sendBotMessageService);
                        break;
                    case "/resume_constructor":
                        commandId[2] = true;
                        break;
                    case "/start":
                        commandId[3] = true;
                        startCommand = new StartCommand(sendBotMessageService);
                        startCommand.execute(update, 0, "");
                        break;
                    case "/stop":
                        commandId[4] = true;
                        stopCommand = new StopCommand(sendBotMessageService);
                        stopCommand.execute(update, 0, "");
                        break;
                    case "/help":
                        commandId[5] = true;
                        helpCommand = new HelpCommand(sendBotMessageService);
                        helpCommand.execute(update, 0, "");
                        break;
                    default:
                        break;
                }
            } else {
                switch (command) {
                    case "/project_constructor":
                        //pcCommand.Answers[fieldConstructor[0]] = message;

                        if (fieldConstructor[0] > 31) {
                            /*SendMessage sm = new SendMessage();
                            sm.setChatId(chatId);
                            sm.setText(pcCommand.getOnePager());
                            try {
                                execute(sm);
                            } catch (TelegramApiException e) {
                                e.printStackTrace();
                            }*/
                            fieldConstructor[0] = 0;
                            command = "";
                        } else if (fieldUpdate) {
                            //pcCommand.execute(update, fieldConstructor[0], message);
                            pcCommand.answers[fieldConstructor[0] - 1] = message;
                            fieldUpdate = false;
                            SendMessage sm = new SendMessage();
                            sm.setChatId(chatId);
                            sm.setText(pcCommand.getOnePager());
                            try {
                                execute(sm);
                            } catch (TelegramApiException e) {
                                e.printStackTrace();
                            }
                        } else
                            pcCommand.execute(update, fieldConstructor[0]++, message);
                        break;
                    case "/vacancy_constructor":
                        commandId[1] = true;
                        //vcCommand = new VcCommand(sendBotMessageService);
                        break;
                    case "/resume_constructor":
                        commandId[2] = true;
                        break;
                    case "/start":
                        commandId[3] = true;
                        startCommand = new StartCommand(sendBotMessageService);
                        startCommand.execute(update, 0, "");
                        break;
                    case "/stop":
                        commandId[4] = true;
                        stopCommand = new StopCommand(sendBotMessageService);
                        stopCommand.execute(update, 0, "");
                        break;
                    case "/help":
                        commandId[5] = true;
                        helpCommand = new HelpCommand(sendBotMessageService);
                        helpCommand.execute(update, 0, "");
                        break;
                    default:
                        break;
                }
            }
            //noCommand = new NoCommand(sendBotMessageService);
            //noCommand.execute(update, 0);
        }

            /*SendMessage sm = new SendMessage();
            sm.setChatId(chatId);
            sm.setText(message);

            try {
                execute(sm);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }*/
    }
}

