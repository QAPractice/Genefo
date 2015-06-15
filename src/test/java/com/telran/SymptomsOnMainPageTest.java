package com.telran;

import com.telran.pages.LoginPage;
import com.telran.pages.MainPage;
import com.telran.pages.SymptomsOnMainPage;
import com.telran.util.TestUtils;
import com.telran.util.WEB_DRIVER;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by tanyagaus on 6/10/15.
 */
public class SymptomsOnMainPageTest{
    public WebDriver driver;
    SymptomsOnMainPage symptomsOnMainPage ;
    public MainPage mainPage;
    public LoginPage loginPage;


    @BeforeClass
    public void setup(){
        this.driver = TestUtils.chooseDriver(WEB_DRIVER.FireFox);

        mainPage = PageFactory.initElements(driver,MainPage.class);
        loginPage = PageFactory.initElements(driver,LoginPage.class);

        symptomsOnMainPage = PageFactory.initElements(driver, SymptomsOnMainPage.class);
       // symptomsOnMainPage.giveMeItem(2).click();

    }

    @Test(groups={"smoke","positive"})
    public void myTest1(){

    }

    @AfterClass(alwaysRun=true)
    public void quiteWindow(){

        this.driver.quit();
    }

}


