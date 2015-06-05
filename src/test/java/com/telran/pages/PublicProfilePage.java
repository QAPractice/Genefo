package com.telran.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

/**
 * Created by Л , Iakov Volf on 6/2/2015.
 */
public class PublicProfilePage extends Page{

    //Titles
    @FindBy(xpath = "//div[@class='col-md-5']/div[@class='panel panel-default']")
    WebElement publicProfilePanel;
    @FindBy(xpath = "//div[@class='panel panel-default']/div[@class='panel-body'][@ng-click='removeNetwork()']")
    WebElement unFollowPanel;
    //Buttons
    @FindBy(xpath = "//div[@ng-click='addNetwork()']/span[@class='fa-stack']")
    WebElement plusFollowButton;
    @FindBy(xpath = "//div[@class='panel panel-default']//div[@class='panel-body']")
    WebElement minusFollowButton;
    @FindBy(xpath = "//*[@class=\"navbar-header\"]/a")
    WebElement homeTitle;
    @FindBy(xpath = "//div[@class='panel-heading']/div[@class='profile_selector_name ng-binding']")
    WebElement publicProfileName;
    @FindBy(xpath = "//div[@class='profilePic']")
    WebElement profilePicture;

    public PublicProfilePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
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
        waitUntilProfilePageIsLoaded();
        return exists(profilePicture);
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
    public PublicProfilePage removeFollow(){
        clickElement(minusFollowButton);
        return this;
    }
    public boolean plusFollowPanel() {
        return exists(plusFollowButton);
    }
}
