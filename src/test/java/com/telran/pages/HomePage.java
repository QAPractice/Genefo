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

    //title
  @FindBy(xpath = "//*[@class='col-md-6']//a[contains(text(),'Sign Up as a Regular User')]")
  WebElement regularUserButton;

    //private String label;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(driver, this);
    }

    public void waitUntilHomePageIsLoaded() {
        try {
            waitUntilElementIsLoaded(regularUserButton);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public boolean isOnHomePage() {
        waitUntilHomePageIsLoaded();
        return exists(regularUserButton);
    }


}
