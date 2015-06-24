package com.telran;

import com.telran.pages.GrafsPage;
import com.telran.pages.LoginPage;
import com.telran.pages.MainPage;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by Yura on 19.06.2015.
 */
public class GraphsTest {

    public WebDriver driver;
    public WebDriverWait wait;
    public LoginPage loginPage;
    public MainPage mainPage;
    public GrafsPage grafsPage;
    private boolean acceptNextAlert = true;


    @BeforeClass
    public void setup() {
        this.driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 5);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        mainPage = PageFactory.initElements(driver, MainPage.class);
        grafsPage = PageFactory.initElements(driver, GrafsPage.class);


        try {
            loginPage.openLoginPage()
                    .waitUntilLoginPageIsLoaded()
                    .login("jakoff+444@gmail.com", "111111");
            mainPage.waitUntilMainPageIsLoaded()
                    .clikToSeeMoreGraphsButton();
            grafsPage.waitUntilGrafsPageIsLoaded();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"smoke", "positive"})
    public void MedicineLink() {
        grafsPage.clikOnMedicineLink()
                .isGraphLoaded("Medicine");
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
