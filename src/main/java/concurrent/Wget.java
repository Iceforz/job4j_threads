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

    public Wget(String url,  String fileName, int speed) {
        this.url = url;
        this.fileName = fileName;
        this.speed = speed;
    }

    @Override
    public void run() {

        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
            FileOutputStream fileOutputStream = new FileOutputStream(fileName)) {
                byte[] dataBuffer = new byte[speed];
                int bytesRead;
                long start = System.currentTimeMillis();
                int downloadDAta = 0;
            while ((bytesRead = in.read(dataBuffer, 0 ,speed)) !=-1) {
                    fileOutputStream.write(dataBuffer, 0, bytesRead);
                    downloadDAta += bytesRead;
                    if (downloadDAta >= speed) {
                        long finish = System.currentTimeMillis();
                        long time = finish - start;
                        if (time < 1000) {
                            Thread.sleep(1000 - time);
                        }
                    }
                    downloadDAta = 0;
                            start = System.currentTimeMillis();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    private static class ValidateArgs {
        private static void validate(String[] args) {
            if (args.length != 3) {
                throw new IllegalArgumentException("Некоректный ввод аргументов");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ValidateArgs.validate(args);
        String url = args[0];
         String filename = args[1];
        int speed = Integer.parseInt(args[2]) * 1048576;
        Thread wget = new Thread(new Wget(url, filename, speed));
        wget.start();
        wget.join();
    }
}