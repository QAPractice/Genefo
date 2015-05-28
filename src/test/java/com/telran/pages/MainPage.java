package com.telran.pages;

/**
 * Created by alex on 28/05/2015.
 */

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class MainPage extends Page {

    // Upper Menu buttons
    @FindBy(xpath = "//ul[@class='post_option']//span[contains(text(),'Post')]")
    WebElement postButton;

    @FindBy(xpath = "//ul[@class='post_option']//span[contains(text(),'Medicine')]")
    WebElement medicineButton;

    @FindBy(xpath = "//ul[@class='post_option']//span[contains(text(),'Milestone')]")
    WebElement milestoneButton;

    @FindBy(xpath = "//ul[@class='post_option']//span[contains(text(),'Symptoms')]")
    WebElement symptomsButton;

    @FindBy(xpath = "//ul[@class='post_option']//span[contains(text(),'What Works')]")
    WebElement whatWorksButton;

    @FindBy(xpath = "//ul[@class='post_option']//span[contains(text(),'MD Rating')]")
    WebElement mdRatingButton;

    @FindBy(xpath = "//ul[@class='post_option']//span[contains(text(),'Question')]")
    WebElement questionButton;



    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.PAGE_URL = "http://genefo-env.elasticbeanstalk.com/home";
    }

    public MainPage openMainPage() {
        driver.get(PAGE_URL);
         return this;
    }

    public MainPage openPostPanel() {
        clickElement(postButton);
        return this;
    }

    public MainPage openMedicinePanel() {
        clickElement(medicineButton);
        return this;
    }

    public MainPage openMilestonePanel() {
        clickElement(milestoneButton);
        return this;
    }

    public MainPage openSymptomsPanel() {
        clickElement(symptomsButton);
        return this;
    }

    public MainPage openWhatWorksButtonPanel() {
        clickElement(whatWorksButton);
        return this;
    }

    public MainPage openMDRatingButtonPanel() {
        clickElement(mdRatingButton);
        return this;
    }

    public MainPage openQuestionButtonPanel() {
        clickElement(questionButton);
        return this;
    }

}