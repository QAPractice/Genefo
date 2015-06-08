package com.telran.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;


/**
 *
 *  Created by tanyagaus on 5/30/15.
 */
public class SymptomsOnMainPage  extends Page {

    /**
     * Labels of categories
     */
    @FindBy(xpath = "//div [@class='col-sm-12']/label[contains(text(),'General Area')]")
    WebElement generalArea;

    @FindBy(xpath = "//div [@class='col-sm-12']/label[contains(text(),'Specific Area')]")
    WebElement specificArea;

    @FindBy(xpath = "//div [@class='col-sm-12']/label[contains(text(),'Symptom')]")
    WebElement symptom;


    /**
     * Feilds  of Symptoms area
     */
    @FindBy(xpath = "//*[@class='chosen-single chosen-single-with-deselect chosen-default']/span[contains(text(),'Select a General Area')]")
    WebElement tooltipGeneralArea;

    @FindBy(xpath = "//*[@class='chosen-single chosen-single-with-deselect chosen-default']/span[contains(text(),'Select a Specific Area')]")
    WebElement tooltipSpecificArea;

    @FindBy(xpath = "//*[@class='chosen-single chosen-single-with-deselect chosen-default']/span[contains(text(),'Select a Symptom')]")
    WebElement tooltipSymptom;

    /**
     * Field for input PostText
     */
    @FindBy(xpath = "//*[@class='form-group']/textarea")
    WebElement tellUsMoreAboutThisSymptomField;


    /**
     * Element of tooltip General area
     */
    @FindBy(xpath= "//ul[@class='chosen-results']/li[@data-option-array-index='1']")
    WebElement itemGrowth;

    @FindBy(xpath= "//ul[@class='chosen-results']/li[@data-option-array-index='5']")
    WebElement itemAbdomen;

    @FindBy(xpath= "//ul[@class='chosen-results']/li[@data-option-array-index='11']")
    WebElement itemTumors;

    /**
     * Element of tooltip Specific area
     */
    @FindBy(xpath= "//ul[@class='chosen-results']/li[@data-option-array-index='1']")
    WebElement itemGrowthSpesific;

    @FindBy(xpath= "//ul[@class='chosen-results']/li[@data-option-array-index='1']")
    WebElement itemAbdomenSpecific;

    @FindBy(xpath= "//ul[@class='chosen-results']/li[@data-option-array-index='1']")
    WebElement itemTumorsSpecific;


    /**
     *  Element of tooltip Symptom area
     */
    @FindBy(xpath= "//ul[@class='chosen-results']/li[@data-option-array-index='1']")
    WebElement itemLargeBirthWeight;

    @FindBy(xpath= "//ul[@class='chosen-results']/li[@data-option-array-index='5']")
    WebElement itemTallStature;

    @FindBy(xpath= "//ul[@class='chosen-results']/li[@data-option-array-index='9']")
    WebElement itemOther;

    /**
     *  Buttons
     */
    @FindBy(xpath = "//*[@id='submit']")
    WebElement postButton;

    /**
     * Serves as indication that we are on 'Symptoms' Panel
     */
    @FindBy(xpath = "//div [@class='col-sm-12']/label[contains(text(),'Symptom')]")
    WebElement nameOfSymptomsTitle;


    /**
     * constructor
     *
     * @param driver
     */
    public SymptomsOnMainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.PAGE_URL = "http://www.genefo.com";
    }

    /**
     *  Waits until title of our 'Symptoms' Panel appears on the screen
     */
    public void waitUntilSymptomsPanelIsLoaded(){
        try {
            waitUntilElementIsLoaded(nameOfSymptomsTitle);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * Checks that title of our 'Symptoms' Panel have appeared on the screen so we can work with it
     *
     * @return
     */
    public boolean isOnSymptomsPanel() {
        waitUntilSymptomsPanelIsLoaded();
        return exists(nameOfSymptomsTitle);
    }

    /**
     *
     * @return
     */
    public SymptomsOnMainPage selectGeneralArea() {
        clickElement(tooltipGeneralArea);
        return this;
    }

    /**
     *
     * @return
     */
    public SymptomsOnMainPage selectGrowthFromGeneralArea() {
        clickElement(itemGrowth);
        return this;
    }

    /**
     *
     * @return
     */
    public SymptomsOnMainPage selectSpecificArea() {
        clickElement(tooltipSpecificArea);
        return this;
    }

    /**
     *
     * @return
     */
    public SymptomsOnMainPage selectGrowthFromSpecificArea(){
        clickElement(itemGrowthSpesific);
        return this;
    }

    /**
     *
     * @return
     */
    public SymptomsOnMainPage selectSymptom() {
        clickElement(tooltipSymptom);
        return this;
    }

    /**
     *
     * @return
     */
    public SymptomsOnMainPage selectLargeBirthWeightFromSymptom(){
        clickElement(itemLargeBirthWeight);
        return this;
    }

    /**
     *
     * @param fillTellUs
     * @return
     */
    public SymptomsOnMainPage typeTellUsMore(String fillTellUs) {
        setElementText(tellUsMoreAboutThisSymptomField, fillTellUs);
        return this;
    }

    /**
     *
     */
    public void clickOnPostButton() {
        clickElement(postButton);
    }


}
