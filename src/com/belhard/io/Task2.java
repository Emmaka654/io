package com.belhard.io;

import java.io.*;
import java.nio.file.*;
import java.time.LocalDateTime;

public class Task2 {
    public static void main(String[] args) throws IOException {
        File source = new File("resources\\in\\hometask_files");
        File destination = new File("resources\\out");
        createDirectoryIfNotExsist(destination);

        copyFiles(source, destination);
    }

    private static void copyFiles(File source, File destination) throws IOException {
        File[] list = source.listFiles();
        for (File file : list) {
            String fileName = file.getName();
            if (fileName.endsWith(".jpeg") || fileName.endsWith(".jpg")) {
                String pathsrcfile = file.getPath();
                String pathdestfile = destination.toString() + "\\" + file.getName();
                String target = pathsrcfile.replace(pathsrcfile, pathdestfile);


                try (PrintWriter out = new PrintWriter(new FileWriter("resources\\out\\info.txt", true))) {
                    out.println(LocalDateTime.now() + " " + fileName + " " + file.length());
                    Files.copy(Paths.get(pathsrcfile), Paths.get(target), StandardCopyOption.REPLACE_EXISTING);


                } catch (IOException e) {
                    System.out.println("Error");
                }
            }
        }
    }

    private static void createDirectoryIfNotExsist(File destination) {
        if (!destination.exists()) {
            destination.mkdirs();
        }
    }

}

