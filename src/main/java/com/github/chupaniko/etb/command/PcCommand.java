package com.github.chupaniko.etb.command;

import com.github.chupaniko.etb.bot.ExchanceTelegramBot;
import com.github.chupaniko.etb.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Arrays;
import java.util.Objects;

public class PcCommand implements Command {
    private final SendBotMessageService sendBotMessageService;
    public static final String[] Msgs = {
            "Конструктор проектов. Для изменения какого-либо поля конструктора введите номер этого поля (числовое значение).",
            "I ЭТАП - Идея\n\n<b>1) Придумайте идею проекта и опишите её.</b>",
            "<b>2) К какой сфере относится Ваш проект?</b>",
            "II ЭТАП - Анализ идеи\n\n<b>3) Какую потребность (проблему) человека решает Ваш проект?</b>",
            "<b>4) Какая целевая аудитория проекта?</b>",
            "<b>5) Какие основные конкуренты есть на рынке?</b>",
            "<b>6) Какие решения проблемы есть на рынке?</b>",
            "<b>7) Каковы сильные и слабые стороны компаний-конкурентов?</b>",
            "<b>8) Чем Ваш продукт (услуга) лучше продуктов конкурентов?</b>",
            "<b>9) Если у Вас нет конкурентов, то чем уникален Ваш проект?</b>",
            "III ЭТАП - Проектная команда\n\n<b>10) Нужны ли Вам сокомандники?</b> Введите число, соответствующее пункту из перечня\n\n1 - Да\n2 - Нет",
            "<b>11) Если у Вас есть команда, то напишите ФИО и обязанности каждого участника команды.</b>",
            "<b>12) Есть ли у Вас сотрудничество с проектами, предприятиями, ВУЗами?</b> Введите число, соответствующее пункту из перечня\n\n1 - Да\n2 - Нет\n3 - На стадии обсуждения",
            "IV ЭТАП - Продвижение проекта\n\n<b>13) Проанализируйте все возможные риски и составьте SWOT-анализ, опишите результаты.</b>",
            "<b>14) Какие риски могут повлиять на развитие Вашего проекта?</b>",
            "<b>15) Как Вы будете преодолевать риски?</b>",
            "<b>16) Проведите проблемное интервью и опишите его результаты.</b>",
            "<b>17) Какие каналы продвижения Вы будете использовать?</b>",
            "<b>18) Какова география Вашего проекта?</b>",
            "V ЭТАП - Коммерческая часть проекта\n\n<b>19) Какова Ваша бизнес-модель?</b> Введите число, соответствующее пункту из перечня\n\n" +
                    "1 - B2C (Business to Client)\n" +
                    "2 - B2B (Business to Business)\n" +
                    "3 - B2G (Business to Government)",
            "<b>20) Проведите анализ рынка и подсчитайте TAM, SAM, SOM. Опишите результаты, а также объясните логику подсчётов.</b>",
            "<b>21) Опишите пути монетизации Вашего проекта.</b>",
            "<b>22) Опишите основные источники доходов проекта.</b>",
            "<b>23) Опишите основные источники расходов проекта.</b>",
            "<b>24) Какие материальные затраты необходимы проекту для запуска?</b>",
            "<b>25) Через какой срок после запуска Вы планируете выйти на точку безубыточности?</b>",
            "<b>26) Как будут осуществлены первые продажи и дальнейший спрос на Ваш продукт?</b>",
            "VI ЭТАП - Заключительный\n\n<b>27) Придумайте название проекта.</b>",
            "<b>28) Опишите кратко проект в 2-3 предложениях</b>",
            "<b>29) Опишите конечный продукт.</b>",
            "<b>30) Напишите ключевые хештеги проекта.</b>"
    };
    public String[] answers = new String[30];

    public PcCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
        Arrays.fill(answers, "");
    }

    @Override
    public void execute(Update update, int fieldConstructor, String message) {
        if (!Objects.equals(message, ""))
            answers[fieldConstructor - 2] = message;
        try {
            sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), Msgs[fieldConstructor]);
        } catch (Exception e) {
            sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), getOnePager());
        }
    }

    public String getOnePager() {
        String result = "";
        for (int i = 0; i < answers.length; i++) {
            if (i == 10) {
                switch (answers[i]) {
                    case "1":
                        result += Msgs[i + 1] + "\n\n<b>Ответ:</b>\n" + "Да" + "\n\n";
                        break;
                    case "2":
                        result += Msgs[i + 1] + "\n\n<b>Ответ:</b>\n" + "Нет" + "\n\n";
                        break;
                    default:
                        result += Msgs[i + 1] + "\n\n<b>Ответ:</b>\n" + answers[i] + "\n\n";
                }
            } else if (i == 12) {
                switch (answers[i]) {
                    case "1":
                        result += Msgs[i + 1] + "\n\n<b>Ответ:</b>\n" + "Да" + "\n\n";
                        break;
                    case "2":
                        result += Msgs[i + 1] + "\n\n<b>Ответ:</b>\n" + "Нет" + "\n\n";
                        break;
                    case "3":
                        result += Msgs[i + 1] + "\n\n<b>Ответ:</b>\n" + "На стадии обсуждения" + "\n\n";
                        break;
                    default:
                        result += Msgs[i + 1] + "\n\n<b>Ответ:</b>\n" + answers[i] + "\n\n";
                }
            } else if (i == 19) {
                switch (answers[i]) {
                    case "1":
                        result += Msgs[i + 1] + "\n\n<b>Ответ:</b>\n" + "B2C" + "\n\n";
                        break;
                    case "2":
                        result += Msgs[i + 1] + "\n\n<b>Ответ:</b>\n" + "B2B" + "\n\n";
                        break;
                    case "3":
                        result += Msgs[i + 1] + "\n\n<b>Ответ:</b>\n" + "B2G" + "\n\n";
                        break;
                    default:
                        result += Msgs[i + 1] + "\n\n<b>Ответ:</b>\n" + answers[i] + "\n\n";
                }
            } else
                result += Msgs[i + 1] + "\n\n<b>Ответ:</b>\n" + answers[i] + "\n\n";
        }
        result = result.replace("<b>", "").replace("</b>", "");
        return result;
    }
}
