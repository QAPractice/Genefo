package com.telran;

import com.telran.pages.LoginPage;
import com.telran.pages.MainPage;
import com.telran.pages.MedicineOnMainPage;
import com.telran.pages.UpperSentPostTabOnMainPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;
import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by Marina on 6/1/2015.
 */
public class MedicineOnMainPageTest {
    public WebDriver driver;
    public WebDriverWait wait;
    public LoginPage loginPage;                         // Pages that we use in our tests
    public MainPage mainPage;
    public MedicineOnMainPage medicineOnMainPage;
    public UpperSentPostTabOnMainPage upperSentPostTabOnMainPage;
    private boolean acceptNextAlert = true;

    @BeforeClass
    public void setup() {
        this.driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 5);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        mainPage = PageFactory.initElements(driver, MainPage.class);
        medicineOnMainPage = PageFactory.initElements(driver, MedicineOnMainPage.class);
        upperSentPostTabOnMainPage = PageFactory.initElements(driver, UpperSentPostTabOnMainPage.class);

        try {
            loginPage.login("stritenko@gmail.com", "111111");
            try {
                sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            assertTrue(mainPage.isOnMainPage());
            mainPage.waitUntilMainPageIsLoaded()
                    .openMedicinePanel();
            medicineOnMainPage.isOnMedicinePanel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void sendMedicineWithShortMedNameReasonTest() {
        String text = "take with food or milk";

        try {
            medicineOnMainPage
                    .fillExistingNameOfMedicine("adv", "advil")
                    .fillExistingReasonForMedicine("hea", "headache")
                    .clickOnAllStarsTogether()
                    .rateThreeStars()             //Click on the third star
                    .typeTellUsMore(text)
                    .clickOnPostButton();
            sleep(3000);


            assertTrue(upperSentPostTabOnMainPage.verifyTextFromSentPost(text));


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void sendMedicineWithFullMedNameReasonTest() {
        String text = "take with food or milk";

        try {
            medicineOnMainPage
                    .fillNewNameOfMedicine("Aspirin")
                    .fillNewReasonForMedicine("pruritus")
                    .clickOnAllStarsTogether()
                    .rateThreeStars()             //Click on the third star
                    .typeTellUsMore(text)
                    .clickOnPostButton();
            sleep(3000);


            assertTrue(upperSentPostTabOnMainPage.verifyTextFromSentPost(text));


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @AfterClass(alwaysRun = true)
    public void teardown() {
        this.driver.quit();
    }


}
