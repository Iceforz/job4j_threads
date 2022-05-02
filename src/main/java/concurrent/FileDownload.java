package concurrent;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class FileDownload {
    public static void main(String[] args) throws Exception {
        String file = "https://proof.ovh.net/files/10Mb.dat";
        try (BufferedInputStream in = new BufferedInputStream(new URL(file).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream("download10Mb")) {
            byte[] dataBuffer = new byte[1048576];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1048576)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                //Thread.sleep(1000);
                System.out.println(bytesRead);
                System.out.println(System.currentTimeMillis());

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
