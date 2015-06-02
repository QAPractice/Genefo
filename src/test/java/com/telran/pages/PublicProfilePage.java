package com.telran.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

/**
 * Created by Л on 6/2/2015.
 */
public class PublicProfilePage extends Page{

    public PublicProfilePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    //Titles
    @FindBy(xpath = "")
    WebElement publicProfilePanel;

    @FindBy(xpath = "")
    WebElement unFollowPanel;

    //Buttons
    @FindBy(xpath = "")
    WebElement plusFollowButton;

    @FindBy(xpath = "//*[contains(text(),'MY HOME')]")
    WebElement homeTitle;

    @FindBy(xpath = "")
    WebElement publicProfileName;

    public void waitUntilProfilePageIsLoaded() {
        try {
            waitUntilElementIsLoaded(publicProfilePanel);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean isOnPublicProfilePage() {
        waitUntilProfilePageIsLoaded();
        return exists(publicProfilePanel);
    }
    public PublicProfilePage addFollow(){
        clickElement(plusFollowButton);
        return this;
    }
    public boolean isUnFollowPanelOnPage() {
        return exists(unFollowPanel);
    }
    public void clickOnHome(){
        clickElement(homeTitle);
    }
    public String getPublicProfileName(){
        return publicProfileName.getText();
    }
}
