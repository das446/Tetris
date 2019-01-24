import java.awt.Event;
import java.util.Observable;

class TickThread extends Thread {
    int tickTime;
    Boolean tick = true;

    

    public TickThread(int t) {
        tickTime = t;
    }

    public void run() {
        while (true) {
            Main.OnTick();
            try {
                sleep(tickTime);
            } catch (InterruptedException e) {

            }
        }
    }

    
}