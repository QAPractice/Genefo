package com.telran.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

/**
 * aka Landing page - first page of the website -  in our case http://52.10.6.51:8080
 * Oleg
 */
public class HomePage extends Page {

  @FindBy(how = How.TAG_NAME, using = "h1")
  @CacheLookup
  public WebElement header;

    //buttons
  @FindBy(xpath = "//*[@class='col-md-6']//a[contains(text(),'Sign Up as a Regular User')]")
  WebElement regularUserButton;

  @FindBy(xpath = "//*[@class='col-md-6']//a[contains(text(),'Sign Up as a Healthcare Professional')]")
  WebElement doctorButton;

    //private String label;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
        this.PAGE_URL = "http://52.10.6.51:8080";
        PageFactory.initElements(driver, this);
    }

    public HomePage openHomePage() {
        driver.get(PAGE_URL);
        return this;
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

    public HomePage clickOnSignUpDoctorButton() {
        clickElement(doctorButton);
        return this;
    }


}
