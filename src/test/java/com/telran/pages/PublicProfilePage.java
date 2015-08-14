package com.telran.pages;

import com.telran.LogLog4j;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.io.IOException;

/**
 * Created by Л , Iakov Volf on 6/2/2015.
 */
public class PublicProfilePage extends Page{
    private static Logger Log = Logger.getLogger(LogLog4j.class.getName());

    //Titles
    @FindBy(xpath = "//div[@class='col-md-5']/div[@class='panel panel-default']")
    @CacheLookup
    WebElement publicProfilePanel;
    @FindBy(xpath = "//div[@class='panel panel-default']/div[@class='panel-body'][@ng-click='removeNetwork()']")
    @CacheLookup
    WebElement unFollowPanel;
    //Buttons
    @FindBy(xpath = "//div[@class='col-md-7 ng-isolate-scope']/div[1]//span[@ class='fa-stack']")
    @CacheLookup
    WebElement plusFollowButton;
    @FindBy(xpath = "//div[@class='col-md-5']//*[7]//span[@class='fa-stack']")
    @CacheLookup
    WebElement minusFollowButton;
    @FindBy(xpath = "//*[@class='navbar-header']/a")
    @CacheLookup
    WebElement homeTitle;
    @FindBy(xpath = "//div[@class='panel-heading']/div[@class='profile_selector_name ng-binding']")
    @CacheLookup
    WebElement publicProfileName;
    @FindBy(xpath = "//div[@class='profilePic']")
    @CacheLookup
    WebElement profilePicture;

    public PublicProfilePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        PropertyConfigurator.configure("log4j.properties");
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
    }

    public void waitUntilProfilePageIsLoaded() {
        try {
            waitUntilElementIsLoaded(profilePicture);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean isOnPublicProfilePage() {
        Log.info("Wait until public profile load");
        waitUntilProfilePageIsLoaded();
        return exists(profilePicture);
    }
    public PublicProfilePage addFollow(){
        Log.info("Add to follow");
        clickElement(plusFollowButton);
        return this;
    }
    public boolean isUnFollowPanelOnPage() {
        Log.info("Assert that unfollow panel is on the page");
        return exists(unFollowPanel);
    }

    public void clickOnHome(){
        Log.info("Home page click");
        clickElement(homeTitle);
    }

    public String getPublicProfileName(){
        Log.info("Public profile name memorizing");
        return publicProfileName.getText();
    }
    public PublicProfilePage removeFollow(){
        Log.info("Click unfollow profile");
        clickElement(minusFollowButton);
        return this;
    }
    public boolean plusFollowPanel() {
        Log.info("Assert that follow panel appeared");
        return exists(plusFollowButton);
    }
}
