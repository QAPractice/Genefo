package com.telran.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

/**
 * aka Landing page - first page of the website -  in our case http://genefo-env.elasticbeanstalk.com
 */
public class HomePage extends Page {

  @FindBy(how = How.TAG_NAME, using = "h1")
  @CacheLookup
    public WebElement header;
    @FindBy(xpath = "//*[contains(text(),'MY HOME')]")
    WebElement homeTitle;
    @FindBy(xpath = "//*[ @class=\"ng-scope\"]/*[contains(text(),'My Profiles')]")
    WebElement myProfilesButton;
    @FindBy(xpath = "//*[@class=\"fa fa-cog fa-2x\"]")
    WebElement cogwheelButton;
    private String label;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(driver, this);
    }

    public void waitUntilHomePageIsLoaded() {
        try {
            waitUntilElementIsLoaded(homeTitle);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public boolean isOnHomePage() {
        waitUntilHomePageIsLoaded();
        return exists(homeTitle);
    }
    public HomePage selectMyProfile () {
        clickElement(cogwheelButton);
        clickElement(myProfilesButton);
        return this;
    }


}
