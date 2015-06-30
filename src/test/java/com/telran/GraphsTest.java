package com.telran;

import com.telran.pages.GrafsPage;
import com.telran.pages.LoginPage;
import com.telran.pages.MainPage;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
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
            Assert.assertTrue(grafsPage.isGrafsPage());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"smoke", "positive"})
    public void MedicineLink() {
        grafsPage.clikOnMedicineLink()
                .isGraphLoaded("Medicine");
    }

    @Test(groups = {"smoke", "positive"})
    public void Facilities() {
        grafsPage.clickOnFacilities();
        Assert.assertTrue(grafsPage.isGraphLoaded("Facilities"), "Facilites Graf not found");

    }

    @Test(groups = {"smoke", "positive"})
    public void MedicineEffectiveness() {
        grafsPage.clikOnMedicineEffectivenessLink();
        Assert.assertTrue(grafsPage.isGraphLoaded("MedicineEffectivenessLink"), "MedicineEffectiveness Graf not found");

    }

    @Test(groups = {"smoke", "positive"})
    public void MedicineReasons() {
        grafsPage.clikOnMedicineReasonsLink();
        Assert.assertTrue(grafsPage.isGraphLoaded("MedicineReasonsLink"), "MedicineReasons Graf not found");

    }

    @Test(groups = {"smoke", "positive"})
    public void ToiletingMilestones() {
        grafsPage.clikOnToiletingMilestonesLink();
        Assert.assertTrue(grafsPage.isGraphLoaded("ToiletingMilestonesLink"), "ToiletingMilestones Graf not found");

    }

    @Test(groups = {"smoke", "positive"})
    public void MovementMilestones() {
        grafsPage.clikOnMovementMilestonesLink();
        Assert.assertTrue(grafsPage.isGraphLoaded("MovementMilestones"), "MovementMilestones Graf not found");

    }

    @Test(groups = {"smoke", "positive"})
    public void LanguageMilestones() {
        grafsPage.clikOnLanguageMilestonesLink();
        Assert.assertTrue(grafsPage.isGraphLoaded("LanguageMilestones"), "MedicineReasons Graf not found");

    }

    @Test(groups = {"smoke", "positive"})
    public void AlternativeMedicine() {
        grafsPage.clikOnAlternativeMedicineLink();
        Assert.assertTrue(grafsPage.isGraphLoaded("AlternativeMedicine"), "AlternativeMedicine Graf not found");

    }

    @Test(groups = {"smoke", "positive"})
    public void Nutrition() {
        grafsPage.clikOnNutritionLink();
        Assert.assertTrue(grafsPage.isGraphLoaded("Nutrition"), "Nutrition Graf not found");

    }

    @Test(groups = {"smoke", "positive"})
    public void Equipment() {
        grafsPage.clikOnEquipmentLink();
        Assert.assertTrue(grafsPage.isGraphLoaded("Equipment"), "Equipment Graf not found");

    }

    @Test(groups = {"smoke", "positive"})
    public void Therapy() {
        grafsPage.clikOnTherapyLink();
        Assert.assertTrue(grafsPage.isGraphLoaded("Therapy"), "Therapy Graf not found");

    }

    @Test(groups = {"smoke", "positive"})
    public void WhatWorksBest() {
        grafsPage.clikOnWhatWorksBestLink();
        Assert.assertTrue(grafsPage.isGraphLoaded("WhatWorksBest"), "WhatWorksBest Graf not found");

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
