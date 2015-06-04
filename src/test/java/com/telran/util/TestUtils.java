package com.telran.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

public class TestUtils {

    private static String FILE_PATH_PAT="src\\test\\resources\\registered_e-mails_pat.txt";
    private static String FILE_PATH_DOC="src\\test\\resources\\registered_e-mails_doc.txt";
    /**
     * Generates random email
     */
    public static String getRandomEmail() {
        StringBuilder sb = new StringBuilder();
        sb.append("gen");
        sb.append((new Date()).getTime());
        sb.append("@efo.com");
        return sb.toString();
    }

    /**
     * Writes email to the specified file
     *
     * @param email    - an email to write
     */
    public static void writeEmailToFileForPatient(String email) {
        String newline = System.getProperty("line.separator");
        BufferedWriter writer = null;
        try {

            File f = new File(FILE_PATH_PAT);
            writer = new BufferedWriter(new FileWriter(f, true));
            writer.write(email + newline);

        } catch (Exception e) {
            System.out.println("Error in writing file class TestUtil.java: " + e.getMessage());

        } finally {
            if (writer != null) {
                try {
                    writer.close();
                    System.out.println("Success!\n e-mail: "+email+" \n was added to file: "+FILE_PATH_PAT);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void writeEmailToFileForDoctor(String email) {
        String newline = System.getProperty("line.separator");
        BufferedWriter writer = null;
        try {

            File f = new File(FILE_PATH_DOC);
            writer = new BufferedWriter(new FileWriter(f, true));
            writer.write(email + newline);

        } catch (Exception e) {
            System.out.println("Error in writing file class TestUtil.java: " + e.getMessage());

        } finally {
            if (writer != null) {
                try {
                    writer.close();
                    System.out.println("Success!\n e-mail: "+email+" \n was added to file: "+FILE_PATH_DOC);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
