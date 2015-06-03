package com.telran.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;

/**
 * Created by Marina on 6/3/2015.
 */
public class TestUtils {

    /**
     * Generates random email
     */
    public static String randomEmail() {
        StringBuilder sb = new StringBuilder();
        sb.append("gen");
        sb.append(randomNumeric(5));
        sb.append("@efo.com");

        return sb.toString();
    }

    /**
     * Writes email to the specified file
     *
     * @param email    - an email to write
     * @param filePath - a path to file
     */
    private static void writeEmailToFile(String email, String filePath) {
        String newline = System.getProperty("line.separator");
        BufferedWriter writer = null;
        try {

            File f = new File(filePath);
            writer = new BufferedWriter(new FileWriter(f, true));
            int i = 1;
            writer.write(email + newline);
            i++;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
