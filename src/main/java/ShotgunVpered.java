import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import static java.time.temporal.ChronoUnit.SECONDS;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class ShotgunVpered extends TelegramLongPollingBot {
    private boolean status = false;
    private LocalDateTime currentDateTime;
    private LocalDateTime reservedDateTime;
    private String currentShotgunUserName = "empty";
    private Long chatID;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy");

    public void Cancelling(){
        status = false;
        SendMessage messageCancel = new SendMessage();
        messageCancel.setText("Отменено");
    }

    public String getBotUsername() {
        return "ShotGunVpered_bot";
    }

    public String getBotToken() {
        return "1696012204:AAFXgFZUGJ2LQfk9DDEM_Pu-Vl9KHvfQNGk";
    }

    public void onUpdateReceived(Update update) {
        String command = update.getMessage().getText();
        String user = update.getMessage().getFrom().getUserName();
        chatID = update.getMessage().getChatId();

        LocalDateTime currentDateTime = LocalDateTime.now();


        if (command.equals("/shotgun@ShotGunVperedBot") || command.equals("/shotgun")){
            SendMessage messageShotgun = new SendMessage();
            if (!status) {
                status = true;
                currentShotgunUserName = user;

                reservedDateTime = LocalDateTime.now();
                messageShotgun.setText(String.valueOf(currentDateTime.format(formatter)) + "\nПоздоавляю Shotgun твой @"  + currentShotgunUserName);
                messageShotgun.setChatId(String.valueOf(chatID));

                try {
                    execute(messageShotgun);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }else{
                messageShotgun.setText(String.valueOf(currentDateTime.format(formatter)) + "\nShotgun забронировал @" + currentShotgunUserName);
                messageShotgun.setChatId(String.valueOf(chatID));

                try {
                    execute(messageShotgun);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }

        if (command.equals("/status@ShotGunVperedBot") || command.equals("/status")) {
            SendMessage messageStatus = new SendMessage();

            if (status) {
                int timeDiff = (int) SECONDS.between(reservedDateTime, currentDateTime);
                new ShotgunTimer(5);
                messageStatus.setText(String.valueOf(currentDateTime.format(formatter)) + "\nShotgun у @" + currentShotgunUserName
                            + "\n" + String.valueOf(timeDiff) + " sec");
                messageStatus.setChatId(String.valueOf(chatID));
                try {
                    execute(messageStatus);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }else{
                messageStatus.setText(String.valueOf(currentDateTime.format(formatter)) + "\nShotgun свободный");
                messageStatus.setChatId(String.valueOf(chatID));
                try {
                    execute(messageStatus);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }
        if (command.equals("/cancel@ShotGunVperedBot") || command.equals("/cancel")) {
            SendMessage messageCancel = new SendMessage();
            if (user.equals(currentShotgunUserName) || user.equals("oscaridze")) { Cancelling(); }
            else{ messageCancel.setText(String.valueOf(currentDateTime.format(formatter)) + "\nТы не можешь отменить Shotgun!"); }
            messageCancel.setChatId(String.valueOf(chatID));
            try {
                execute(messageCancel);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
}
