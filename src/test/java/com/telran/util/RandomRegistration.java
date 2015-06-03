package com.telran.util;

import com.telran.pages.ProfilePage;
import com.telran.pages.RegistrationPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.RandomStringUtils.random;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;

/**
 * Created by Marina on 5/20/2015.
 */
public class RandomRegistrationTest {
    public WebDriver driver;
    public WebDriverWait wait;
    RegistrationPage registrationPage;
    ProfilePage profilePage;

    private static void writeRandomDoublesToFile(List<String> content, String filePath) throws IOException {
        String newline = System.getProperty("line.separator");
        BufferedWriter writer = null;
        try {

            File f = new File(filePath);
            writer = new BufferedWriter(new FileWriter(f, true));
            int i = 1;
            for (String s : content) {
                writer.write(Integer.toString(i) + "" +
                        "" + s + newline);
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }


    public void fillRandomFile() {
        List<String> randoms = new ArrayList<String>();
        try {


            for (int i = 0; i < 10; i++) {
                StringBuilder sb = new StringBuilder();
                sb.append("gen");
                sb.append(randomNumeric(5));
                sb.append("@efo.com");
                randoms.add(sb.toString());
            }
            writeRandomDoublesToFile(randoms, "C:\\Users\\Marina\\Documents\\Random\\RandomForTest.txt");
        } catch (Exception e) {
            e.printStackTrace();

        }
        finally {
            randoms.get(0);
        }
    }
}







