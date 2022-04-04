package concurrent;
import java.lang.Runnable;

public class ConsoleProgress implements Runnable{

    @Override

    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(500);
                System.out.print("\r Loading...|." + "\\");
                Thread.sleep(500);
                System.out.print("\r Loading...|." + "|");
                Thread.sleep(500);
                System.out.print("\r Loading...|." + "/");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        Thread.sleep(1550);
        progress.interrupt();
    }
}
