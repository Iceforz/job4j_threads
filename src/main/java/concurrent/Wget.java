package concurrent;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.Runnable;
import java.net.URL;

public class Wget implements Runnable {
    private final String url;
    private final String fileName;
    private final int speed;

    public Wget(String url, String fileName, int speed) {
        this.url = url;
        this.fileName = fileName;
        this.speed = speed;
    }

    @Override
    public void run() {
        try (BufferedInputStream in = new BufferedInputStream(new URL(this.url).openStream());
            FileOutputStream fileOutputStream = new FileOutputStream(fileName)) {
                byte[] dataBuffer = new byte[1024];
                int bytesRead;
                long start = System.currentTimeMillis();
            while ((bytesRead = in.read(dataBuffer, 0 ,1024)) !=-1) {
                    fileOutputStream.write(dataBuffer, 0, bytesRead);
                    if (bytesRead >= speed) {
                        long finish = System.currentTimeMillis();
                        long time = finish - start;
                        if (time < 1000) {
                            Thread.sleep(1000 - time);
                            start = System.currentTimeMillis();
                        }
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    public static void main(String[] args) throws InterruptedException {
        String url = args[0];
        String filename = args[1];
        int speed = Integer.parseInt(args[2]);
        Thread wget = new Thread(new Wget(url, filename, speed));
        wget.start();
        wget.join();
    }
}