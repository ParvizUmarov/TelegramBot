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
                    case "/saturday":
                    saturdayCommand(chatId);
                    break;
                    case "/sunday":
                    sundayCommand(chatId);
                    break;



            }

        }
    }
    private void startCommandReceived(long chatId, String name){

        String answer = "Привет " + name + ", \nДобро пожаловать в бот группы 1-МД-16\n" +
                "Чтобы узнать расписание то следуйте инструкции\n"+"\n" +
                "==================================\n" + "\n"+
                "Инструкция:\n" +
                "После </> нужно указать день недели и вы получите соотвествующие расписание\n" +
                "/monday -- расписание на понедельник,\n" +
                "/tuesday -- расписание на вторник\n" +
                "/wednesday -- расписание на среду\n" +
                "/thursday -- расписание на четверг\n" +
                "/friday -- расписание на пятницу\n"+
                "/saturday -- расписание на субботу\n"+
                "/sunday -- расписания на воскресенье\n"+"\n" +
                "==================================\n"+"\n"+
                "На этом все удачи вам в учебе :)";

        sendMessage(chatId,answer);
    }
    private void mondayCommand(long chatId){
        Boolean isWeekOdd = Helper.isCurrentWeekOdd();
        String answer = "";
        if(isWeekOdd) {
            answer = "Расписания на Понедельник\n" +
                    "Адрес -- Большая Морская 18\n" +
                    "\n" +
                    "Время 15:20 - 16:45\n" +
                    "1. Физра \n" +
                    "Аудитория -- Спортзал\n" +
                    "\n" +
                    "Время 16:55 - 18:20\n" +
                    "2. Физика(лаб)\n" +
                    "Аудитория -- 334 \n";
        }else {
            answer = "Расписания на Понедельник*\n" +
                    "Адрес -- Большая Морская 18\n" +
                    "\n" +
                    "Время 13:45 - 15:10\n" +
                    "1. Физика(лекц)\n" +
                    "Аудитория -- 333\n" +
                    "\n" +
                    "Время 15:20 - 16:45\n" +
                    "2. Физра \n" +
                    "Аудитория -- Спортзал\n" +
                    "\n" +
                    "Время 16:55 - 18:20\n" +
                    "3. Физика(прак)\n" +
                    "Аудитория -- 332 ";;
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
        Boolean isWeekOdd = Helper.isCurrentWeekOdd();
        String answer = "";

        if(isWeekOdd) {
            answer = "Расписания на Среду\n" +
                    "Адрес -- Большая Морская 18\n" +
                    "\n" +
                    "Время: 10:05 - 11:30\n" +
                    "1. Иностранный язык\n" +
                    "Аудитория -- 412\n" +
                    "\n" +
                    "Время: 13:00\n" +
                    "2. Информационные технологии компьютерных системах (дистант)\n" +
                    "Аудитория -- дом\n";
        }else{
            answer = "Расписания на Среду\n" +
                    "Адрес -- Большая Морская 18\n" +
                    "\n" +
                    "Время: 10:05 - 11:30\n" +
                    "1. Иностранный язык\n" +
                    "Аудитория -- 412\n" +
                    "\n" +
                    "Время: 13:00\n" +
                    "2. Информационные технологии в\n" +
                    "компьютерных системах (дистант)\n" +
                    "Аудитория -- дом\n";
        }

        sendMessage(chatId,answer);
    }
    private void thursdayCommand(long chatId){
        Boolean isWeekOdd = Helper.isCurrentWeekOdd();
        String answer = "";

        if(isWeekOdd) {
            answer = "Расписания на Четверг\n" +
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
                    "Аудитория -- 441\n";
        }else{
            answer = "Расписания на Четверг\n" +
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
                    "3. Администрирование компьютерных систем (лекц)\n" +
                    "Аудитория -- 458\n" +
                    "\n" +
                    "Время: 15:20 - 16:55\n" +
                    "4. Администрирование компьютерных систем (прак) \n" +
                    "Аудитория -- 458";
        }

        sendMessage(chatId,answer);
    }
    public void fridayCommand(long chatId){
        String answer = "Расписание на пятницу\n" +
                "Спать!!";

        sendMessage(chatId,answer);
    }
 public void saturdayCommand(long chatId){
        String answer = "Выходной день :)";

        sendMessage(chatId,answer);
    }
    public void sundayCommand(long chatId){
        String answer = "Выходной день :)";

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
