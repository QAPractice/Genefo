package com.telran;

import com.telran.pages.DocProfInfPage;
import com.telran.pages.LoginPage;
import com.telran.pages.MainPage;
import com.telran.pages.ProfileDoctorPage;
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
 * Created by Oleg on 05.06.2015.
 */
public class DocProfInfTest {

    public WebDriver driver;
    public WebDriverWait wait;
    public LoginPage loginPage;
    public MainPage mainPage;
    public ProfileDoctorPage profileDoctorPage;
    public DocProfInfPage docProfInfPage;
    private boolean acceptNextAlert = true;

    @BeforeClass
    public void setup() {
        this.driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 5);
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        mainPage = PageFactory.initElements(driver, MainPage.class);
        profileDoctorPage = PageFactory.initElements(driver, ProfileDoctorPage.class);
        docProfInfPage = PageFactory.initElements(driver, DocProfInfPage.class);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        try {
            loginPage.login("osh_il+4@yahoo.com","111111");
            mainPage.waitUntilMainPageIsLoaded();
            mainPage.selectMyAccount();
            profileDoctorPage.waitUntilProfileDoctorPageIsLoaded();
            profileDoctorPage.clickOnHealInf();
            docProfInfPage.waitUntilDocProfInfPageIsLoaded();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"smoke", "positive"})
    public void EditProfInfSuccess() {

        try {
            docProfInfPage
                    .fillSpecialtiesField("Oncology")
                    .clickOnAddSpecialtiesButton()
                    .fillSubspecialtiesField("insomnia")
                    .clickOnAddSubspecialtiesButton()
                    .fillTitlesField("doctor")
                    .clickOnAddTitlesButton()
                    .fillAreasField("Canavan disease")
                    .clickOnAddAreasButton()
                    .fillWorkPlacesNameField("assuta")
                    .fillWorkPlacesLocationField("Tel Aviv-Yafo, Israel")
                    .clickOnAddWorkPlacesButton()
                    .clickOnDoneButton();
            assertTrue(profileDoctorPage.isOnProfileDoctorPage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"smoke", "positive"})
    public void AddEmptyFields() {

        try {
            docProfInfPage
                    .fillSpecialtiesField("")
                    .fillSubspecialtiesField("")
                    .fillTitlesField("")
                    .fillAreasField("")
                    .fillWorkPlacesNameField("")
                    .fillWorkPlacesLocationField("")
                    .clickOnDoneButton();
            assertTrue(profileDoctorPage.isOnProfileDoctorPage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"smoke", "negative"})
    public void AddEmptySpecialtiesFields() {

        try {
            docProfInfPage
                    .fillSpecialtiesField("")
                    .clickOnAddSpecialtiesDisButton();
            assertTrue(docProfInfPage.isOnDocProfInfPage());
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
