package com.telran;

import com.telran.pages.LoginPage;
import com.telran.pages.MainPage;
import com.telran.pages.MilestoneOnMainPage_New;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;
import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by Zizi, Christina and Mariya on 5/30/2015.
 */
public class MilestoneOnMainPageTest_New {

    public WebDriver driver;
    public WebDriverWait wait;
    public LoginPage loginPage;                         // Pages that we use in our tests
    public MainPage mainPage;
    public MilestoneOnMainPage_New milestoneOnMainPage;
    public String  age;
    public String month;
    public String year;
    public String post;
    public String type;
    public String milestone;


    @BeforeClass
    public void setup() {
        this.driver = new InternetExplorerDriver();
        wait = new WebDriverWait(driver, 5);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        mainPage = PageFactory.initElements(driver, MainPage.class);
        milestoneOnMainPage = PageFactory.initElements(driver, MilestoneOnMainPage_New.class);

        try {
            loginPage.login("mili@mail.ru", "111111");
            assertTrue(mainPage.isOnMainPage());
            mainPage.waitUntilMainPageIsLoaded()
                    .openMilestonePanel();
            milestoneOnMainPage.waitUntilIsLoaded("Developmental Milestone");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    @Test(groups={"smoke","positive"})
    public void SendLanguagePostTest() {
        type = "Language";
        milestone = "Smiles";
        year="2";
        month="3";
        age=year+" years "+month+" months";
        post="Post1";
        try {
            milestoneOnMainPage
                    .clickWebElement(type)
                    .clickWebElement("Drop down list")
                    .clickWebElement(milestone)
                    .clickWebElement("Field years")
                    .setElementText("Field years", year)
                    .clickWebElement("Field months")
                    .setElementText("Field months",month)
                    .clickWebElement("Field post")
                    .setElementText("Field post",post)
                    .clickWebElement("Button POST")
                    .waitUntilIsLoaded("New post");

            sleep(3000);
            assertTrue(milestoneOnMainPage.verifyTextBoolean("New post==>type", type));
            assertTrue(milestoneOnMainPage.verifyTextBoolean("New post==>milestone", milestone));
            assertTrue(milestoneOnMainPage.verifyTextBoolean("New post==>age", age));
            assertTrue(milestoneOnMainPage.verifyTextBoolean("New post==>post", post));


        } catch (Exception e) {
            System.out.print("SendLanguagePostTest is error");
            e.printStackTrace();
        }
    }

    @AfterClass(alwaysRun = true)
    public void teardown () {
        this.driver.quit();
    }

}













