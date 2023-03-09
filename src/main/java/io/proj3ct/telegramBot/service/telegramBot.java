package io.proj3ct.telegramBot.service;


import io.proj3ct.telegramBot.Helper;
import io.proj3ct.telegramBot.config.BotConfig;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.ErrorManager;

@Component
public class telegramBot extends TelegramLongPollingBot {

    @Override
    public void onUpdateReceived(Update update) {

        if(update.hasMessage() && update.getMessage().hasText()){
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            switch (messageText){
                case "/start":
                    startCommandReceived(chatId, update.getMessage().getChat().getFirstName());
                    break;
                case "/monday":
                    mondayCommand(chatId);
                    break;
                case "/tuesday":
                    tuesdayCommand(chatId);
                    break;
                case "/wednesday":
                    wednesdayCommand(chatId);
                    break;
                case "/thursday":
                    thursdayCommand(chatId);
                    break;
                case "/friday":
                    fridayCommand(chatId);
                    break;


            }

        }
    }
    private void startCommandReceived(long chatId, String name){

        String answer = "Привет " + name + ", добро пожаловать в бот группы 1-МД-16\n" +
                "Чтобы узнать расписание то следуйте инструкции\n" +
                "===============================================\n" +
                "Инструкция:\n" +
                "Пример:\n" +
                "/monday -- расписание на понедельник,\n" +
                "/tuesday -- расписание на вторник\n" +
                "/wednesday -- расписание на среду\n" +
                "/thursday -- расписание на четверг\n" +
                "/friday -- расписание на пятницу\n" +
                "после </> нужно указать день недели и вы получите соотвествующие расписание\n" +
                "На этом все удачи вам в учебе :)";

        sendMessage(chatId,answer);
    }
    private void mondayCommand(long chatId){
        Boolean isWeekOdd = Helper.isCurrentWeekOdd();
        String answer = "";
        if(isWeekOdd) {
            answer = "Расписание на Понедельник\n" +
                    "Адрес -- Большая Морская 18\n" +
                    "\n" +
                    "Время 15:20 - 16:45\n" +
                    "1. Физра \n" +
                    "Аудитория -- Спортзал\n" +
                    "\n" +
                    "Время 16:55 - 18:20\n" +
                    "2. Физика(лаб)\n" +
                    "Аудитория -- 334 ";
        }else {
            answer = "Week is even";
        }
        sendMessage(chatId,answer);
    }

    private void tuesdayCommand(long chatId){
        String answer = "Расписание на Вторник\n" +
                "Адрес: Вознесенский проспект 54\n" +
                "\n" +
                "Время: 8:30 - 9:55\n" +
                "1. Философия (прак)\n" +
                "Аудитория -- 406\n" +
                "\n" +
                "Время: 10:05 - 11:30\n" +
                "2. Философия (лекц)\n" +
                "Аудитория -- 406\n" +
                "\n" +
                "Время: 11:40 - 13:05\n" +
                "3. Математика (лекц)\n" +
                "Аудитория -- 406";

        sendMessage(chatId,answer);
    }
    private void wednesdayCommand(long chatId){
        String answer = "Расписание на Среду\n" +
                "Адрес -- Большая Морская 18\n" +
                "\n" +
                "Время: 10:05 - 11:30\n" +
                "1. Иностранный язык\n" +
                "Аудитория -- 412\n" +
                "\n" +
                "Время: 13:00\n" +
                "2. Дистант\n" +
                "Аудитория -- дом";

        sendMessage(chatId,answer);
    }
    private void thursdayCommand(long chatId){
        String answer = "Расписание на Четверг\n" +
                "Адрес -- Вознесенский проспект 54\n" +
                "\n" +
                "Время: 10:05 - 11:30\n" +
                "1. Основы системного анализа (лекц)\n" +
                "Аудитория -- 401\n" +
                "\n" +
                "Время: 11:40 - 13:05\n" +
                "2. Математика (прак)\n" +
                "Аудитория -- 443\n" +
                "\n" +
                "Время: 13:45 - 15:10\n" +
                "3. Администрирование компьютерных систем (прак)\n" +
                "Аудитория -- 458\n" +
                "\n" +
                "Время: 15:20 - 16:45\n" +
                "4. Социология (лекц)\n" +
                "Аудитория -- 441\n" +
                "\n" +
                "Время: 16:55 - 18:20\n" +
                "5. Социология (прак)\n" +
                "Аудитория -- 441";

        sendMessage(chatId,answer);
    }
    public void fridayCommand(long chatId){
        String answer = "Расписание на пятницу\n" +
                "Спать!!";

        sendMessage(chatId,answer);
    }

    private void sendMessage(long chatId, String textToSend){
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(textToSend);

        try{
            execute(message);
        }
        catch (TelegramApiException e){
              throw new RuntimeException(e);
        }
    }
    final BotConfig config;

    public telegramBot(BotConfig config){

        this.config = config;
        List<BotCommand> listofCommands = new ArrayList<>();
        listofCommands.add(new BotCommand("/monday", "get schedule for this day"));
        listofCommands.add(new BotCommand("/tuesday", "get schedule for this day"));
        listofCommands.add(new BotCommand("/wednesday", "get schedule for this day"));
        listofCommands.add(new BotCommand("/thursday", "get schedule for this day"));
        listofCommands.add(new BotCommand("/friday", "get schedule for this day"));
        listofCommands.add(new BotCommand("/saturday", "get schedule for this day"));
        listofCommands.add(new BotCommand("/sunday", "get schedule for this day"));
        try {
            this.execute(new SetMyCommands(listofCommands, new BotCommandScopeDefault(), null));
        } catch (TelegramApiException e) {

        }

    }
    @Override
    public String getBotUsername() {
        return config.getBotName();
    }

    @Override
    public String getBotToken(){
        return config.getToken();
    }
}
