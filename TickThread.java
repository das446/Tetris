import java.awt.Event;
import java.util.Observable;
import java.util.function.*;

class TickThread extends Thread {
    int tickTime;
    Boolean tick = true;
    Consumer f;

    

    public TickThread(Consumer f,int t) {
        tickTime = t;
        this.f = f;
    }

    public void run() {
        while (true) {
            f.accept(null);
            try {
                sleep(tickTime);
            } catch (InterruptedException e) {

            }
        }
    }

    
}