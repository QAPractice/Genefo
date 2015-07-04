package com.telran;

import com.telran.pages.DocProfInfPage;
import com.telran.pages.LoginPage;
import com.telran.pages.MainPage;
import com.telran.pages.ProfileDoctorPage;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.telran.util.TestUtils;
import com.telran.util.WEB_DRIVER;

import java.util.concurrent.TimeUnit;

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
        //this.driver = TestUtils.chooseDriver(WEB_DRIVER.Chrome);
        this.driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 5);
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        mainPage = PageFactory.initElements(driver, MainPage.class);
        profileDoctorPage = PageFactory.initElements(driver, ProfileDoctorPage.class);
        docProfInfPage = PageFactory.initElements(driver, DocProfInfPage.class);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        try {
            loginPage.login("osh_il+4@yahoo.com", "111111");
            mainPage.waitUntilMainPageIsLoaded();
            mainPage.selectMyAccount();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @BeforeMethod
    public void beforeMethodSetUp() {
        try {
            if(profileDoctorPage.isOnProfileDoctorPage() == false) {
                docProfInfPage.clickOnDoneButton();
            }
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
                    .fillAreasField("Canavan")
                    .clickOnTooltipAreas()
                    .clickOnAddAreasButton()
                    .fillWorkPlacesNameField("assuta")
                    .fillWorkPlacesLocationField("a")
                    .clickOnTooltipWP()
                    .clickOnAddWorkPlacesButton()
                    .clickOnDoneButton();
            Assert.assertTrue(profileDoctorPage.isOnProfileDoctorPage(), "Profile HCP Page doesn't open");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"positive"})
    public void AddWorkPlaceInf() {
        try {
            docProfInfPage
                    .fillWorkPlacesNameField("Ikhilov")
                    .fillWorkPlacesLocationField("J")
                    .clickOnTooltipWP()
                    .clickOnAddWorkPlacesButton()
                    .clickOnDoneButton();
            Assert.assertTrue(profileDoctorPage.isOnProfileDoctorPage(),"Profile HCP Page doesn't open");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"smoke", "negative"})
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
            Assert.assertTrue(profileDoctorPage.isOnProfileDoctorPage(),"Profile HCP Page doesn't open");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"smoke", "negative"})
     public void AddEmptySpecialties() {
        try {
            docProfInfPage
                    .fillSpecialtiesField("");
            Assert.assertTrue(docProfInfPage.isAddSpecButtonExists()==false,"The Add Specialties Button Enable");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"smoke", "negative"})
    public void AddDelSpecialties() {
        try {
            docProfInfPage
                    .fillSpecialtiesField("abcd")
                    .clickOnAddSpecialtiesButton()
                    .clickOnDelSpecButton()
                    .clickOnConfSpecButton();
            Assert.assertTrue(docProfInfPage.isSpecExists(),"The specialty exists");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"negative"})
    public void AddEmptyLocationWPandFillNameWPFields() {
        try {
            docProfInfPage
                    .fillWorkPlacesNameField("Assuta")
                    .fillWorkPlacesLocationField("");
            Assert.assertTrue(docProfInfPage.isAddWorkPlacesDisButtonExists(),"The button add work place is clickable");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"negative"})
    public void AddEmptyLocationWPandNameWPFields() {
        try {
            docProfInfPage
                    .fillWorkPlacesNameField("")
                    .fillWorkPlacesLocationField("");
            Assert.assertTrue(docProfInfPage.isAddWorkPlacesDisButtonExists(),"The button add work place is clickable");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"positive"})
    public void DeleteLocationWPandNameWP() {
        try {
            sleep();
            docProfInfPage
                    .fillWorkPlacesNameField("Assuta");
            //sleep();
            docProfInfPage
                    .fillWorkPlacesLocationField("t")
                    .clickOnTooltipWP()
                    .clickOnAddWorkPlacesButton()
                    .clickOnDelWorkPlacesButton()
                    .clickOnConfWorkPlacesButton();
            Assert.assertTrue(docProfInfPage.isLocationExists(),"Location is not disappear");
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


    private void sleep (){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
