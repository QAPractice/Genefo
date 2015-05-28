package com.telran.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Л on 5/28/2015.
 */
public class MDRatingOnMainPage extends Page {

    public MDRatingOnMainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.PAGE_URL = "http://genefo-env.elasticbeanstalk.com/home";
    }

    //fields
    @FindBy(id = "medical_facility")
    WebElement medicalFacilityField;

    @FindBy(id = "medical_physician_first")
    WebElement physicianField;

    //Buttons
    @FindBy(id = "submit")
    WebElement postButton;

}
