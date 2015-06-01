package com.telran;

import com.telran.pages.HomePage;
import com.telran.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


public class EditAccountTest {

    public WebDriver driver;
    public WebDriverWait wait;
    public HomePage homePage;
    public LoginPage loginPage;

    @BeforeClass
    public void setup(){

        File file = new File("C://Users//E.Frumker//AppData//Local//Mozilla Firefox//firefox.exe");
        FirefoxBinary binary = new FirefoxBinary(file);
        FirefoxProfile profile = new FirefoxProfile();
        this.driver = new FirefoxDriver(binary,profile);
        wait = new WebDriverWait(driver,5);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        homePage = PageFactory.initElements(driver,HomePage.class);
        loginPage = PageFactory.initElements(driver,LoginPage.class);
        loginPage.openLoginPage();
        loginPage.login("lev.magazinnik@gmail.com","123qwee");
    }
    // Google.docs: Edit#1
    // Condition:   Create account with valid information. 1.Click the button"Settings" in the upper right corner.
    // TEST: 1.The button is clickable and opened the drop-down menu.(My account, My profiles, Logout).
    @Test
    public void createNewAccount(){
        assertEquals(driver.getTitle(),"");
    }

//    @AfterClass(alwaysRun=true)
//    public void quiteWindow(){
//        this.driver.quit();
//    }
}
