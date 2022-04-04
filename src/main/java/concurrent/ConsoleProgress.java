package concurrent;
import java.lang.Runnable;

public class ConsoleProgress implements Runnable{

    @Override

    public void run() {
        String[] chars = {"\\", "|", "/"};
        int index = 0;
        while (!Thread.currentThread().isInterrupted()) {
            if (index == 3) {
                index = 0;
            }
                try {
                    Thread.sleep(500);
                    System.out.print("\r Loading...| " + chars[index++]);
                } catch (InterruptedException e) {
                    e.printStackTrace();
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
