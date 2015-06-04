package com.telran.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class TestUtils {

    private static String FILE_PATH_PAT="src\\test\\resources\\registered_e-mails_pat.txt";
    private static String FILE_PATH_DOC="src\\test\\resources\\registered_e-mails_doc.txt";
    private static String FILE_PATH_LOG="src\\test\\resources\\log.txt";
    private static String SPEC_SYMBOLS="~!@#$%^&*()_+}{|\":?><|\\,./;'\\[]=-`.";
    private static StringBuilder logTests;
    private static BufferedWriter writeLogToFile;

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

    public static void addTestToLog()  {

        String funcName=Thread.currentThread().getStackTrace()[2].getMethodName();
        String str="";
        try {
            if(logTests==null){
                writeLogToFile = new BufferedWriter(new FileWriter(FILE_PATH_LOG));
                logTests= new StringBuilder();
                logTests.append("-------------------------------------------------------------------\n\n\n");
                logTests.append("Started NEW test at " + (new Date()).toString() + "\n");
                logTests.append("Test name: "+funcName+"\n");
                writeLogToFile.write(logTests.toString());
                writeLogToFile.flush();
            }
            else {
                str="Test name: "+funcName+"\n";
                logTests.append(str);
                writeLogToFile.write(str);
                writeLogToFile.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void logPrint() {
        try {
            String str="-------------------------------------------------------------------\n";

            writeLogToFile.write(str);
            System.out.println(logTests.toString());
            System.out.println(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                writeLogToFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean isSpecSymbolsInString(String str) {
        for (int i = 0; i < SPEC_SYMBOLS.length(); i++) {
            if (str.contains(SPEC_SYMBOLS.substring(i, i + 1)))
                return true;
        }
        return false;
    }
}
