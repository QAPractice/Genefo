package com.telran.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

/**
 * Created by Marina and Olga on 5/28/2015.
 */
public class MedicineOnMainPage extends Page {


    //Fields
    @FindBy(id = "medicine_name")
    WebElement nameOfMedicine;
    @FindBy(id = "medicine_reason")
    WebElement reasonForMedicine;
    @FindBy(name = "bio")
    WebElement tellUsMoreAboutThisMedicine;
    //elements of dropdown list
    @FindBy(id = "typeahead-005-725-option-0")
    WebElement itemNameOfMedicine;
    @FindBy(id = "typeahead-006-9768-option-0")
    WebElement itemReasonForMedicine;

    //Buttons
    @FindBy(id = "submit")
    WebElement postButton;


    //Rate Stars
    @FindBy(xpath = "//div[3]/div[1]/div/form/div[2]/span[2]/span/i[3]")
    WebElement RateBy3Stars;
    @FindBy(xpath = "//div[3]/div[1]/div/form/div[2]/span[2]/span/i[5]")
    WebElement RateBy5Stars;
    @FindBy(xpath = "//div[3]/div[1]/div/form/div[2]/span[2]/span/i[1]")
    WebElement RateBy1Star;


    // Serves as indication that we are on 'Medicine' Panel
    @FindBy(xpath = "//div[@class='form-group']/label[@for='medicine_name']")
    WebElement nameOfMedicineTitle;


    public MedicineOnMainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.PAGE_URL = "http://genefo-env.elasticbeanstalk.com/home";
    }


    // Waits until 'Medicine' Panel appears on the screen

    public void waitUntilMedicinePanelIsLoaded() {
        try {
            waitUntilElementIsLoaded(nameOfMedicineTitle);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    // Checks that title of our 'Medicine' Panel have appeared on the screen so we can work with it.
    public boolean isOnMedicinePanel() {
        waitUntilMedicinePanelIsLoaded();
        return exists(nameOfMedicineTitle);
    }


    //Methods

    public MedicineOnMainPage typeNameOfMedicine(String fillNameMedicine) {
        setElementText(nameOfMedicine, fillNameMedicine);
        return this;
    }

    public MedicineOnMainPage typeReasonForMedicine(String fillReason) {
        setElementText(reasonForMedicine, fillReason);
        return this;
    }

    public MedicineOnMainPage typeTellUsMore(String fillTellUs) {
        setElementText(tellUsMoreAboutThisMedicine, fillTellUs);
        return this;
    }


    public void clickOnOneStar() {
        clickElement(RateBy1Star);
    }

    public void clickOnFiveStar() {
        clickElement(RateBy5Stars);
    }

    public void clickOnThreeStar() {
        clickElement(RateBy3Stars);
    }

    public void clickOnPostButton() {
        clickElement(postButton);
    }


}
