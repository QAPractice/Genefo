package com.telran.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

/**
 * Created by Л on 5/19/2015.
 */
public class MyProfilesPage extends Page {

    //Labels
    @FindBy(xpath = "//*[@class='ng-binding' and contains(.,'HCP Account')]")
    WebElement MyProfilesLable;

    //Button
    @FindBy(xpath = "//div[@class='panel-body']//div[@class='btn-add-profile']/i")
    WebElement addPlusButton;
    public MyProfilesPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public MyProfilesPage openMyProfilesPage() {
        driver.get(PAGE_URL);
        return this;
    }

        public void waitUntilMyProfilesPageIsLoaded() {
        try {
            waitUntilElementIsLoaded(addPlusButton);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean isOnMyProfilesPage() {
        waitUntilMyProfilesPageIsLoaded();
        return exists(addPlusButton);
    }

    public void clickToPlus() {
        clickElement(addPlusButton);
    }


}