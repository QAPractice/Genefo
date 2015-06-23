package com.telran;

import com.telran.pages.ContactUSPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by Л on 6/2/2015.
 */
public class ContactUSTest {
    public WebDriver driver;
    public WebDriverWait wait;

    ContactUSPage contactUSPage;
    private boolean acceptNextAlert = true;

    @BeforeClass
    public void setup() {
        this.driver = new InternetExplorerDriver();
        wait = new WebDriverWait(driver, 5);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        contactUSPage = PageFactory.initElements(driver, ContactUSPage.class);

        try {
            contactUSPage.openContactPage();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"smoke", "positive"})
    public void SendMessageTest() {
        contactUSPage
                .fillEmailField("jakoff+33@gmail.com")
                .fillFirstNameField("Patient")
                .fillMessageField("QA test")
                .clickOnSendButton();
        assertTrue("No confirmation Message", contactUSPage.messageSentSuccesifully());

    }

    @Test(groups = {"smoke", "negative"})
    public void SendMessageWithoutEmailTest() throws InterruptedException {
        contactUSPage
                .fillEmailField("jakoff+33@gmail.com")
                .clickOnNameField();
        assertTrue("Sent Button is not disabled", contactUSPage.sendButtonDisabled());
        contactUSPage.fillFirstNameField("Patient");
        assertTrue("Sent Button is disabled", contactUSPage.sendButtonEnaabled());
        contactUSPage.fillMessageField("QA test")
                .clickOnSendButton();
        assertTrue("No confirmation Message", contactUSPage.messageSentSuccesifully());

    }

    @AfterClass(alwaysRun = true)
    public void teardown() {
        this.driver.quit();
    }
}