package com.telran.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.io.IOException;

/**
 * Created by Yuri on 6/8/2015.
 */
public class GrafsPage extends Page {



    //elements of dropdown list
    @FindBy(xpath = "//*[contains(text(),'Facilities')]/..//*[contains(text(),'Rating')]")
    WebElement mdRatingLink;


    public GrafsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);

    }


    // Waits until 'Medicine' Panel appears on the screen

    public GrafsPage waitUntilGrafsPageIsLoaded() {
        try {
            waitUntilElementIsLoaded(mdRatingLink);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }


    // Checks that title of our 'Medicine' Panel have appeared on the screen so we can work with it.
    public boolean isGrafsPage() {
        waitUntilGrafsPageIsLoaded();
        return exists(mdRatingLink);
    }





    // Click on the third star
    public GrafsPage clickOnMDRating() {
        clickElement(mdRatingLink);
        return this;
    }

}