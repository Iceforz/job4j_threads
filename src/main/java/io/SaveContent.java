package io;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class SaveContent {

    public synchronized void saveContent(String content, File file) {
        try (BufferedOutputStream o = new BufferedOutputStream(new FileOutputStream(file))) {
            for (int i = 0; i < content.length(); i += 1) {
               o.write(content.charAt(i));
            }

        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}