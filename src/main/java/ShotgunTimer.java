import java.util.Timer;
import java.util.TimerTask;

public class ShotgunTimer {
    Timer timer;

    public ShotgunTimer(int seconds) {
        timer = new Timer();
        timer.schedule(new RemindTask(), seconds*1000);
    }

    class RemindTask extends TimerTask {
        public void run() {
            System.out.println("Time's up!");
            timer.cancel();
        }
    }

}