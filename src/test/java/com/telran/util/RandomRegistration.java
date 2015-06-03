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

import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;

/**
 * Created by Marina on 5/20/2015.
 */
public class RandomRegistration {
    public WebDriver driver;
    public WebDriverWait wait;
    RegistrationPage registrationPage;
    ProfilePage profilePage;

    private static void writeRandomDoublesToFile(List<String> content, String filePath) throws IOException {
        String newline = System.getProperty("line.separator");
        BufferedWriter writer = null;
        try {
            // String email = TestUtils.randomEmail();

            //TestUtils.writeEmailToFile(email, filePath);
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

    public static String fillRandomFile() {
        List<String> randoms = new ArrayList<String>();
        try {


            for (int i = 0; i < 2; i++) {
                StringBuilder sb = new StringBuilder();
                sb.append("gen");
                sb.append(randomNumeric(5));
                sb.append("@efo.com");
                randoms.add(sb.toString());
            }
            writeRandomDoublesToFile(randoms, "util/RandomForTest.txt");
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            return randoms.get(0);
        }
    }

    @Test
    public void testExample() {
        String email = TestUtils.randomEmail();

        // register
        // assert 1
        // assert 2
        // assert 3

        TestUtils.writeEmailToFile(email, "path");
    }

}







