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

    //Labels of categories
    @FindBy(xpath = "//div [@class='col-sm-12']/label[contains(text(),'General Area')]")
    WebElement generalArea;

    @FindBy(xpath = "//div [@class='col-sm-12']/label[contains(text(),'Specific Area')]")
    WebElement specificArea;

    @FindBy(xpath = "//div [@class='col-sm-12']/label[contains(text(),'Symptom')]")
    WebElement symptom;


    //feilds  of Symptoms area
    @FindBy(xpath = "//*[@class='chosen-single chosen-single-with-deselect chosen-default']/span[contains(text(),'Select a General Area')]")
    WebElement tooltipGeneralArea;

  // @FindBy(xpath="")

    @FindBy(xpath = "//*[@class='chosen-single chosen-single-with-deselect chosen-default']/span[contains(text(),'Select a Specific Area')]")
    WebElement tooltipSpecificArea;

    @FindBy(xpath = "//*[@class='chosen-single chosen-single-with-deselect chosen-default']/span[contains(text(),'Select a Symptom')]")
    WebElement tooltipSymptom;

    //Field for input PostText
    @FindBy(xpath = "//*[@class='form-group']/textarea")
    WebElement tellUsMoreAboutThisSymptomField;


    //element of tooltip General area
    @FindBy(xpath= "//ul[@class='chosen-results']/li[@data-option-array-index='1']")
    WebElement itemGrowth;

    @FindBy(xpath= "//ul[@class='chosen-results']/li[@data-option-array-index='5']")
    WebElement itemAbdomen;

    @FindBy(xpath= "//ul[@class='chosen-results']/li[@data-option-array-index='11']")
    WebElement itemTumors;

    //element of tooltip Specific area
    @FindBy(xpath= "//ul[@class='chosen-results']/li[@data-option-array-index='1']")
    WebElement itemGrowthSpesific;

    @FindBy(xpath= "//ul[@class='chosen-results']/li[@data-option-array-index='1']")
    WebElement itemAbdomenSpecific;

    @FindBy(xpath= "//ul[@class='chosen-results']/li[@data-option-array-index='1']")
    WebElement itemTumorsSpecific;


    //element of tooltip Symptom area
    @FindBy(xpath= "//ul[@class='chosen-results']/li[@data-option-array-index='1']")
    WebElement itemLargeBirthWeight;

    @FindBy(xpath= "//ul[@class='chosen-results']/li[@data-option-array-index='5']")
    WebElement itemTallStature;

    @FindBy(xpath= "//ul[@class='chosen-results']/li[@data-option-array-index='9']")
    WebElement itemOther;

    //Buttons
    @FindBy(xpath = "//*[@id='submit']")
    WebElement postButton;

    // Serves as indication that we are on 'Symptoms' Panel
    @FindBy(xpath = "//div [@class='col-sm-12']/label[contains(text(),'Symptom')]")
    WebElement nameOfSymptomsTitle;



    public SymptomsOnMainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.PAGE_URL = "http://genefo-env.elasticbeanstalk.com/home";
    }

    // Waits until title of our 'Symptoms' Panel appears on the screen
    public void waitUntilSymptomsPanelIsLoaded(){
        try {
            waitUntilElementIsLoaded(nameOfSymptomsTitle);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    // Checks that title of our 'Symptoms' Panel have appeared on the screen so we can work with it.
    public boolean isOnSymptomsPanel() {
        waitUntilSymptomsPanelIsLoaded();
        return exists(nameOfSymptomsTitle);
    }

    public SymptomsOnMainPage selectGeneralArea() {
        clickElement(tooltipGeneralArea);
        return this;
    }


    public SymptomsOnMainPage selectGrowthFromGeneralArea() {
        clickElement(itemGrowth);
        return this;
    }

    public SymptomsOnMainPage selectSpecificArea() {
        clickElement(tooltipSpecificArea);
        return this;
    }


    public SymptomsOnMainPage selectGrowthFromSpecificArea(){
        clickElement(itemGrowthSpesific);
        return this;
    }


    public SymptomsOnMainPage selectSymptom() {
        clickElement(tooltipSymptom);
        return this;
    }

    public SymptomsOnMainPage selectLargeBirthWeightFromSymptom(){
        clickElement(itemLargeBirthWeight);
        return this;
    }

    public SymptomsOnMainPage typeTellUsMore(String fillTellUs) {
        setElementText(tellUsMoreAboutThisSymptomField, fillTellUs);
        return this;
    }

    public void clickOnPostButton() {
        clickElement(postButton);
    }


}
