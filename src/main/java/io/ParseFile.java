package io;

import java.io.*;
import java.util.function.Predicate;

public class ParseFile {
    private final File file;

    public ParseFile(File file) {
        this.file = file;
    }

    public String getContent(Predicate<Integer> filter) throws IOException {
        return new GetContent().getContent(filter, file);
    }

    public void saveContent(String content) throws IOException {
        new SaveContent().saveContent(content, file);
    }
}