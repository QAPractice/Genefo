package com.telran.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

/**
 * Created by Л on 5/19/2015.
 */
public class MyProfilesPage extends Page {
    public MyProfilesPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public MyProfilesPage openMyProfilesPage() {
        driver.get(PAGE_URL);
        return this;
    }

    @FindBy(xpath = "//*[@class=\"btn-add-profile\"]/i")
    WebElement addPlusButton;

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