import java.awt.Event;
import java.util.Observable;

class TickThread extends Thread {
    int tickTime;
    boolean tick = true;

    public TickThread(int t) {
        tickTime = t;
    }

    public void run() {
        while (tick) {
            GameWindow.OnTick();
            try {
                sleep(tickTime);
            } catch (InterruptedException e) {

            }
        }
    }

    public void Pause() {
        tick = false;
    }

    public void SpeedUp() {
        tickTime -= 50;
    }

}