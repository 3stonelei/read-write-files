package com.github.hcsp.io;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileAccessor {
    public static List<String> readFile1(File file) throws IOException {
        List<String> list = new ArrayList<>();
        FileInputStream inputStream = new FileInputStream(file);
        StringBuilder stringBuilder = new StringBuilder();
        int num;
        while ((num = inputStream.read()) != -1) {
            if (num == '\n') {
                list.add(stringBuilder.toString());
                stringBuilder.delete(0, stringBuilder.length());
            } else {
                stringBuilder.append((char) num);
            }
        }
        return list;
    }

    public static List<String> readFile2(File file) throws IOException {
        return Files.readAllLines(file.toPath());
    }

    public static List<String> readFile3(File file) throws IOException {
        List<String> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = reader.readLine()) != null) {
            list.add(line);
        }
        return list;
    }

    public static List<String> readFile4(File file) throws IOException {
        return FileUtils.readLines(file, Charset.defaultCharset());
    }

    public static void writeLinesToFile1(List<String> lines, File file) throws IOException {
        Files.write(file.toPath(), lines);
    }

    public static void writeLinesToFile2(List<String> lines, File file) throws IOException {
        FileOutputStream os = new FileOutputStream(file);
        for (String line : lines) {
            byte[] bytes = line.getBytes();
            os.write(bytes);
            os.write('\n');
        }
    }

    public static void writeLinesToFile3(List<String> lines, File file) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
        for (String line : lines) {
            bufferedWriter.write(line);
            bufferedWriter.write('\n');
        }
        bufferedWriter.flush();
    }

    private static void writeLinesToFile4(List<String> lines, File file) throws IOException {
        FileUtils.writeLines(file, lines);
    }

    public static void main(String[] args) throws IOException {
        File projectDir = new File(System.getProperty("basedir", System.getProperty("user.dir")));
        File testFile = new File(projectDir, "target/test.txt");
        List<String> lines = Arrays.asList("AAA", "BBB", "CCC");
        writeLinesToFile1(lines, testFile);
        writeLinesToFile2(lines, testFile);
        writeLinesToFile3(lines, testFile);
        writeLinesToFile4(lines, testFile);

        System.out.println(readFile1(testFile));
        System.out.println(readFile2(testFile));
        System.out.println(readFile3(testFile));
        System.out.println(readFile4(testFile));
    }


}
