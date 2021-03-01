import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class ShotgunVpered extends TelegramLongPollingBot {


    public String getBotUsername() {
        return "ShotGunVpered_bot";
    }

    public String getBotToken() {
        return "1696012204:AAFXgFZUGJ2LQfk9DDEM_Pu-Vl9KHvfQNGk";
    }

    public void onUpdateReceived(Update update) {

        String command = update.getMessage().getText();
        LocalDateTime dateTime = LocalDateTime.now();
        SendMessage message1 = new SendMessage();
        SendMessage message2 = new SendMessage();
        boolean status = false;
        String currentShotgunUserName;

        if (command.equals("/shotgun@ShotGunVperedBot") || command.equals("/shotgun")){

            ShotgunTimer SGTimer = new ShotgunTimer(5);


        }

        if (command.equals("/status@ShotGunVperedBot") || command.equals("/status")) {
            System.out.println(update.getMessage().getText());
            message1.setText(String.valueOf(dateTime));
            message2.setText("@" + update.getMessage().getFrom().getUserName());
        }
        if (command.equals("/cancel@ShotGunVperedBot") || command.equals("/cancel")) {
            System.out.println(update.getMessage().getText());
            message1.setText("Almas sidr");
        }


        message1.setChatId(String.valueOf(update.getMessage().getChatId()));
        message2.setChatId(String.valueOf(update.getMessage().getChatId()));

        try {
            execute(message1);
            execute(message2);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }
}
