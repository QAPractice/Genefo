package com.telran.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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

    // Rating stars( marked ones. have asterisk sign in definition)
    @FindBy(name = "//*[@class=\"ng-isolate-scope ng-valid ng-dirty\"]/*[3]/*[contains(text(),'*')]")
    WebElement markedStarsButton;

    // Rating stars( non-marked ones. do not have asterisk sign in definition)
    @FindBy(name = "//*[@class=\"ng-isolate-scope ng-valid ng-dirty\"]/*[3]/*[not(contains(text(),'*'))]")
    WebElement NonMarkedStarsButton;


    public WhatsWorksOnMainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.PAGE_URL = "http://genefo-env.elasticbeanstalk.com/home";
    }



}











