package com.telran.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.io.*;
import java.nio.file.Paths;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class TestUtils {

    private static String FILE_PATH_PAT="src\\test\\resources\\registered_e-mails_pat.txt";
    private static String FILE_PATH_DOC="src\\test\\resources\\registered_e-mails_doc.txt";
    private static String FILE_PATH_LOG="src\\test\\resources\\log.txt";
    private static String SPEC_SYMBOLS="~!@#$%^&*()_+}{|\":?><|\\,./;'\\[]=-`.";
    private static StringBuilder logTests;
    private static BufferedWriter writeLogToFile;


    public static String getRandomEmail(boolean isDoctor) {
        //Generate random email
        String randomEmail = getRandomEmail();
        Set<String> fileContent;
        if (isDoctor) {
            // get the contents of doctors file
            fileContent = readFileToSet(FILE_PATH_DOC);
        } else {
            // get the contents of doctors file
            fileContent = readFileToSet(FILE_PATH_PAT);
        }
        // check the generated email is not already in file. \if not - return generated email
        if (!fileContent.contains(randomEmail)) {
            return randomEmail;
        }
        //Email already in file. try again  by recursive call
        else {
            return getRandomEmail(isDoctor);

        }
    }

    /**
     * Read the specified file to HashSet
     *
     * @param filePath
     * @return
     */
    private static Set<String> readFileToSet(String filePath) {
        BufferedReader reader = null;
        String line = null;
        Set<String> fileLines = new HashSet<String>();
        try {
            reader = new BufferedReader(new FileReader(filePath));
            while ((line = reader.readLine()) != null) {
                fileLines.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return fileLines;
    }

    /**
     * Generates random email
     */
    private static String getRandomEmail() {
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
    public static WebDriver chooseDriver(WEB_DRIVER mydriver){
        WebDriver driver=null;
        switch (mydriver){
            case FireFox:
//                File file = new File("C://Users//E.Frumker//AppData//Local//Mozilla Firefox//firefox.exe");
//                System.setProperty("webdriver.FireFox.bin",  "C:/Users/E.Frumker/AppData/Local/Mozilla Firefox/firefox.exe");
//                File file = new File("%PROGRAMFILES%\\Mozilla Firefox\\firefox.exe");
//                FirefoxBinary binary = new FirefoxBinary(file);
//                FirefoxProfile profile = new FirefoxProfile();
//                driver = new FirefoxDriver(binary, profile);
                driver = new FirefoxDriver();
                break;
            case Chrome:
                System.setProperty("webdriver.chrome.driver",  Paths.get("").toAbsolutePath().toString() + "\\WEB_Drivers\\chromedriver.exe");
                driver = new ChromeDriver();
                break;

            case InternetExplorer:
                System.setProperty("webdriver.ie.driver", Paths.get("").toAbsolutePath().toString() + "\\WEB_Drivers\\IEDriverServer_32.exe");
                driver = new InternetExplorerDriver();
                break;
            default:
                break;

        }

        return driver;

    }
    public static void setSystemVar(){

        System.setProperty("webdriver.chrome.driver",  Paths.get("").toAbsolutePath().toString() + "\\WEB_Drivers\\chromedriver.exe");
        System.setProperty("webdriver.ie.driver", Paths.get("").toAbsolutePath().toString() + "\\WEB_Drivers\\IEDriverServer_32.exe");
    }
}
