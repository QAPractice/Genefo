package com.telran.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.io.IOException;

/**
 * Created by Marina and Olga on 5/28/2015.
 */
public class MedicineOnMainPage extends Page {


    //Fields
    @FindBy(id = "medicine_name")
    WebElement nameOfMedicinefield;
    @FindBy(id = "medicine_reason")
    WebElement reasonForMedicineField;
    @FindBy(name = "bio")
    WebElement tellUsMoreAboutThisMedicineField;

    //elements of dropdown list
    @FindBy(xpath = "//*[contains(@id,'typeahead')][@ng-show='isOpen()']/li[1]")
    WebElement tooltipNameOfMedicine;
    @FindBy(xpath = "//*[contains(@id,'typeahead')][@ng-show='isOpen()']/li[1]")
    WebElement tooltipReasonForMedicine;

    //Buttons
    @FindBy(id = "submit")
    WebElement postButton;


    //Rate Stars
    @FindBy(xpath = "//*[@ng-model=\"medicine_effect\"]//*[@class=\"sr-only ng-binding\"]")
    WebElement allStarsTogether;

    // Rating star( marked one. Have asterisk sign in definition)
    @FindBy(xpath = "//*[@class='ng-isolate-scope ng-valid ng-dirty']/*[3]/*[contains(text(),'*')]")
    WebElement thirdMarkedRatingStar;

    // Rating star( non-marked one. Do not have asterisk sign in definition)
    @FindBy(xpath = "//*[@class='ng-isolate-scope ng-valid ng-dirty']/*[3]/*[not(contains(text(),'*'))]")
    WebElement thirdNonMarkedRatingStar;

    // Rating star - marked and non-marked together
    @FindBy(xpath = "//*[@class='ng-isolate-scope ng-valid ng-dirty']/*[3]")
    WebElement thirdRatingStar;


    // @FindBy(xpath = "//div[3]/div[1]/div/form/div[2]/span[2]/span/i[3]")
    // WebElement RateBy3Stars;
    //  @FindBy(xpath = "//div[3]/div[1]/div/form/div[2]/span[2]/span/i[5]")
    //  WebElement RateBy5Stars;
    //   @FindBy(xpath = "//div[3]/div[1]/div/form/div[2]/span[2]/span/i[1]")
    //   WebElement RateBy1Star;


    // Serves as indication that we are on 'Medicine' Panel
    @FindBy(xpath = "//div[@class='form-group']/label[@for='medicine_name']")
    WebElement nameOfMedicineTitle;


    public MedicineOnMainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.PAGE_URL = "http://genefo-env.elasticbeanstalk.com/home";
    }


    // Waits until 'Medicine' Panel appears on the screen

    public MedicineOnMainPage waitUntilMedicinePanelIsLoaded() {
        try {
            waitUntilElementIsLoaded(nameOfMedicineTitle);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }


    // Checks that title of our 'Medicine' Panel have appeared on the screen so we can work with it.
    public boolean isOnMedicinePanel() {
        waitUntilMedicinePanelIsLoaded();
        return exists(nameOfMedicineTitle);
    }


    //Methods

    public MedicineOnMainPage fillExistingNameOfMedicine(String nameMedicineShort, String nameMedicineFull) throws IOException, InterruptedException {
        setElementText(nameOfMedicinefield, nameMedicineShort);
        waitUntilIsLoaded(tooltipNameOfMedicine);
        clickElement(tooltipNameOfMedicine);
        Assert.assertEquals(nameOfMedicinefield.getAttribute("value"), nameMedicineFull);
        return this;
    }

    public MedicineOnMainPage fillNewNameOfMedicine(String nameMedicine) throws IOException, InterruptedException {
        setElementText(nameOfMedicinefield, nameMedicine);
        return this;
    }

    public MedicineOnMainPage fillExistingReasonForMedicine(String nameReasonShort, String nameReasonFull) throws IOException, InterruptedException {
        setElementText(reasonForMedicineField, nameReasonShort);
        waitUntilIsLoaded(tooltipReasonForMedicine);
        clickElement(tooltipReasonForMedicine);
        Assert.assertEquals(reasonForMedicineField.getAttribute("value"), nameReasonFull);
        return this;
    }


    public MedicineOnMainPage fillNewReasonForMedicine(String nameReason) throws IOException, InterruptedException {
        setElementText(reasonForMedicineField, nameReason);
        return this;
    }


    public MedicineOnMainPage typeTellUsMore(String fillTellUs) {
        setElementText(tellUsMoreAboutThisMedicineField, fillTellUs);
        return this;
    }


    // We need to click on all stars together to set free each one of them
    public MedicineOnMainPage clickOnAllStarsTogether() {
        clickElement(allStarsTogether);
        return this;
    }

    // Click on the third star
    public MedicineOnMainPage rateThreeStars() {
        clickElement(thirdRatingStar);
        return this;
    }

    public void clickOnPostButton() {
        clickElement(postButton);
    }


}
