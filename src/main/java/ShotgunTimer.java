import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;
import static java.time.temporal.ChronoUnit.SECONDS;

public class ShotgunTimer extends ShotgunVpered{
    Timer timer;
    boolean status = true;
    public ShotgunTimer(int seconds) {
        timer = new Timer();
        timer.schedule(new RemindTask(), seconds*1000);
    }

    class RemindTask extends TimerTask {
        public void run() {
            status = false;
            System.out.println("Time is up!");
            timer.cancel();
        }
    }

}