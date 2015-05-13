package com.telran;

import com.telran.pages.ProfilePage;
import com.telran.pages.RegistrationPage;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by Iakov Volf, Oleg on 5/4/2015.
 */
public class RegistrationTest {

    public WebDriver driver;
    public WebDriverWait wait;
    RegistrationPage registrationPage;
    ProfilePage profilePage;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @BeforeClass(alwaysRun = true)
    public void setup() {
        this.driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 5);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        registrationPage = PageFactory.initElements(driver, RegistrationPage.class);

        try {
            registrationPage
                    .openRegistrationPage()
                    .waitUntilRegPageIsLoaded();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void RegTestSuccess() {

        try {
            registrationPage
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen").fillPasswordField("111111")
                    .fillEmailField("us000998@genefo.com")
                    .fillConditionField("Alstrom")
                    .clickToCheckBox18()
                    .clickToCheckBoxAgree()
                    .clickToSubmit();
            assertTrue(profilePage.isOnProfilePage());

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @AfterClass(alwaysRun = true)
    public void teardown() {
        this.driver.quit();
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }

}

