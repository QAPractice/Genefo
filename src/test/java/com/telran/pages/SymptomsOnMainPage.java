package com.telran.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;


/**
 * Created by tanyagaus on 5/30/15.
 */
public class SymptomsOnMainPage  extends Page {

    //Fields
    @FindBy(id = "general_area")
    WebElement generalAreaField;

    @FindBy(id = "specific_area")
    WebElement specificAreaField;

    @FindBy(id = "symptoms_name")
    WebElement symptomField;

    @FindBy(name = "bio")
    WebElement tellUsMoreAboutThisSymptom;


    //elements of dropdown list
    @FindBy
    WebElement tooltipGeneralArea;

    @FindBy
    WebElement tooltipSpecificArea;

    @FindBy
    WebElement tooltipSymptom;


    //Buttons
    @FindBy(id = "submit")
    WebElement postButton;

    // Serves as indication that we are on 'Symptoms' Panel
    @FindBy(xpath = "")
    WebElement nameOfSymptomsTitle;


    public SymptomsOnMainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.PAGE_URL = "http://genefo-env.elasticbeanstalk.com/home";
    }


    public SymptomsOnMainPage waitUntilSymptomsPanelIsLoaded(){
        try {
            waitUntilElementIsLoaded(nameOfSymptomsTitle);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;

    }

    // Checks that title of our 'Symptoms' Panel have appeared on the screen so we can work with it.
    public boolean isOnSymptomsPanel() {
        waitUntilSymptomsPanelIsLoaded();
        return exists(nameOfSymptomsTitle);
    }

    public SymptomsOnMainPage selectGeneralArea(String value) {
        selectValueInDropdown(tooltipGeneralArea, value);
        return this;
    }

    public boolean isGeneralAreaSelected(String value) {

        return verifyTextBoolean(tooltipGeneralArea, value);
    }

    public SymptomsOnMainPage selectSymptoms(String value) {
        selectValueInDropdown(tooltipSymptom, value);
        return this;
    }

    public boolean isSymptomSelected(String value) {
        return verifyTextBoolean(tooltipSymptom, value);
    }


}
