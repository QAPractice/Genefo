package com.telran.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

/**
 * Created by Iakov Volf 27.05.15.
 */
public class WhatsWorksOnMainPage extends Page {
    //Category Symptom buttons
    @FindBy(xpath = "//div[@class='btn-group']/button[contains(text(),'Therapy')]")
    WebElement therapyButton;

    @FindBy(xpath = "//div[@class='btn-group']/button[contains(text(),'Equipment')]")
    WebElement equipmentButton;

    @FindBy(xpath = "//div[@class='btn-group']/button[contains(text(),'Nutrition')]")
    WebElement nutritionButton;

    @FindBy(xpath = "//div[@class='btn-group']/button[contains(text(),'Exercises')]")
    WebElement exercisesButton;

    @FindBy(xpath = "//div[@class='btn-group']/button[contains(text(),'Alternative')]")
    WebElement alternativeButton;

    @FindBy(xpath = "//div[@class='btn-group']/button[contains(text(),'Other')]")
    WebElement otherButton;

    //Dropdown list
    @FindBy(xpath = "//*[contains(text(),'Please select a specific item')]")
    WebElement selectItemList;
    @FindBy(xpath = "//*[contains(text(),'Please select a specific item')]/../div/b")
    WebElement selectItemListButton;


    //elements of dropdown list
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='1']")
    WebElement itemPhysicalTherapy;
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='7']")
    WebElement itemSpeechTherapy;
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='4']")
    WebElement itemPsychotherapy;

    // Rating stars( marked ones. Have asterisk sign in definition)
    @FindBy(name = "//*[@class=\"ng-isolate-scope ng-valid ng-dirty\"]/*[3]/*[contains(text(),'*')]")
    WebElement markedStarsButton;

    // Rating stars( non-marked ones. Do not have asterisk sign in definition)
    @FindBy(name = "//*[@class=\"ng-isolate-scope ng-valid ng-dirty\"]/*[3]/*[not(contains(text(),'*'))]")
    WebElement NonMarkedStarsButton;

    // Serves as indication that we are on 'WhatWorks' Panel.
    @FindBy(name = "//label[@for='what_works_category_1']/../label[@for='symptoms_select'] ")
    WebElement CategorySymptomTitle;



    public WhatsWorksOnMainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.PAGE_URL = "http://genefo-env.elasticbeanstalk.com/home";
    }

    // Waits until title of our 'What works' Panel appears on the screen
    public void waitUntilWhatWorksPanelIsLoaded() {
        try {
            waitUntilElementIsLoaded(CategorySymptomTitle);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    // Checks that title of our 'What works' Panel have appeared on the screen so we can work with it.
    public boolean isOnWhatWorksPanel() {
        waitUntilWhatWorksPanelIsLoaded();
        return exists(CategorySymptomTitle);
    }


    public void clickOnTherapyOption() {
        clickElement(therapyButton);
    }

    public void clickOnEquipmentOption() {
        clickElement(equipmentButton);
    }

    public void clickOnNutritionOption() {
        clickElement(nutritionButton);
    }

    public void clickOnExercisesOption() {
        clickElement(exercisesButton);
    }

    public void clickOnAlternativeOption() {
        clickElement(alternativeButton);
    }

    public void clickOnOtherOption() {
        clickElement(otherButton);
    }

    public void clickOnItemList() {
        clickElement(selectItemList);
    }

    public void chooseFirstItemFromItemList() {
        clickElement(itemPhysicalTherapy);
    }

}











