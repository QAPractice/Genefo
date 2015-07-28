package com.telran.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;
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
    @FindBy(xpath = "//*[@id='typeahead-02P-91']")
    WebElement nameOfMedicineOptions;
    @FindBy(xpath = "//*[@id='typeahead-02Q-2475']")
    WebElement reasonForMedicineOptions;

    //elements of dropdown list
    @FindBy(xpath = "//*[contains(@id,'typeahead')][@ng-show='isOpen()']/li[1]")
    WebElement tooltipNameOfMedicine;
    @FindBy(xpath = "//*[contains(@id,'typeahead')][@ng-show='isOpen()']/li[1]")
    WebElement tooltipReasonForMedicine;

    //Buttons
    @FindBy(id = "submit")
    WebElement postButton;


    //Rate Stars Sent Post
    @FindBy(xpath = "//*[@class='ng-isolate-scope ng-valid ng-dirty'][@ng-model='medicine_effect']")
    WebElement allStarsTogether;

    // Rating star( marked one. Have asterisk sign in definition)
    @FindBy(xpath = "//*[@class='ng-isolate-scope ng-valid ng-dirty']/*[3]/*[contains(text(),'*')]")
    WebElement thirdMarkedRatingStar;

    // Rating star( non-marked one. Do not have asterisk sign in definition)
    @FindBy(xpath = "//*[@class='ng-isolate-scope ng-valid ng-dirty']/*[3]/*[not(contains(text(),'*'))]")
    WebElement thirdNonMarkedRatingStar;

    // Rating marked First Star
    @FindBy(xpath = "//*[@class='panel story-panel ng-scope panel-default']/../div[5]//*[@ng-model=\"medicine_effect\"]/i[1]/span[contains(text(),'*')]")
    WebElement markedFirstStarInSentPost;

    // Rating unmarked second Star
    @FindBy(xpath = "//*[@class='panel story-panel ng-scope panel-default']/../div[5]//*[@ng-model=\"medicine_effect\"]/i[2]/span[not(contains(text(),'*'))]")
    WebElement unmarkedSecondStarInSentPost;

    @FindBy(xpath = "//*[@class='panel story-panel ng-scope panel-default']/../div[5]//*[@ng-model=\"medicine_effect\"]/i[3]/span[contains(text(),'*')]")
    WebElement markedThirdStarInSentPost;

    //Rating unmarked fourth star in the post
    @FindBy(xpath = "//*[@class='panel story-panel ng-scope panel-default']/../div[5]//*[@ng-model=\"medicine_effect\"]/i[4]/span[not(contains(text(),'*'))]")
    WebElement unmarkedFourthStarInSentPost;

    @FindBy(xpath = "//*[@class='panel story-panel ng-scope panel-default']/../div[5]//*[@ng-model=\"medicine_effect\"]/i[5]/span[contains(text(),'*')]")
    WebElement markedFifthStarInSentPost;


    // Rating star - third Rating Star Medicine
    @FindBy(xpath = "//*[@class='ng-isolate-scope ng-valid ng-dirty']/*[3]")
    WebElement thirdRatingStar;


    // Rating star - first Rating Star Medicine
    @FindBy(xpath = "//*[@class='ng-isolate-scope ng-valid ng-dirty']/*[1]")
    WebElement firstRatingStar;

    // Rating star - fifth Rating Star Medicine
    @FindBy(xpath = "//*[@class='ng-isolate-scope ng-valid ng-dirty']/*[5]")
    WebElement fifthRatingStar;

    // fields for sent post
    @FindBy(xpath = "//*[@class='panel story-panel ng-scope panel-default']/../div[5]//*[@class='table post-table']//tr[2]/td[2]")
    WebElement medicineName;

    @FindBy(xpath = "//*[@class='panel story-panel ng-scope panel-default']/../div[5]//*[@class='table post-table']//tr[3]/td[2]")
    WebElement reasonName;

    //alerts
    @FindBy(xpath = "//*[text()='REQUIRED FIELDS']")
    WebElement errorRequiredFields;


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
        this.PAGE_URL = baseUrl + "/home";
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

    /**
     *
  //   * @param nameMedicineShort
  //   * @param nameMedicineFull
     * @return
     * @throws IOException
     * @throws InterruptedException
     */



    public void createMedicinePost(){
        setNameOfMedicine("a");
        WebElement nameOfMedicine;
        List<WebElement> nameOfMedicineList = nameOfMedicineOptions.findElements(By.tagName("li"));
        int nameOfMedicineCounter = 8;
        while (nameOfMedicineCounter < nameOfMedicineList.size()){
            nameOfMedicineList = nameOfMedicineOptions.findElements(By.tagName("li"));
            nameOfMedicine = nameOfMedicineList.get(nameOfMedicineCounter);
            String name = nameOfMedicine.getText();
            try{
                waitUntilElementIsLoaded(nameOfMedicine);
            }catch (IOException e){
                e.printStackTrace();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            clickElement(nameOfMedicine);
            // Log.info("Selecting name of Medicine: " + name + " ");

            setReasonForMedicine("a");
            WebElement reasonForMedicine;
            List<WebElement> reasonForMedicineList = reasonForMedicineOptions.findElements(By.tagName("li"));
            int nameOfReasonCounter = 0;
            while (nameOfReasonCounter < 8){
                reasonForMedicineList = reasonForMedicineOptions.findElements(By.tagName("li"));
                reasonForMedicine = reasonForMedicineList.get(nameOfReasonCounter);
                String reason = reasonForMedicine.getText();
                try{
                    waitUntilElementIsLoaded(reasonForMedicine);
                }catch (IOException e){
                    e.printStackTrace();
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
                clickElement(reasonForMedicine);
                // Log.info("Selecting reason for Medicine: " + reason + " ");
            }
        }

    }





    public MedicineOnMainPage setNameOfMedicine(String nameMedicine) {
        setElementText(nameOfMedicinefield, nameMedicine);
        return this;
    }

    public MedicineOnMainPage setReasonForMedicine(String nameOfReason){
        setElementText(reasonForMedicineField,nameOfReason);
        return this;
    }

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

    // Click on the first star
    public MedicineOnMainPage rateOneStar() {
        clickElement(firstRatingStar);
        return this;
    }

    // Click on the fifth star
    public MedicineOnMainPage rateFifeStars() {
        clickElement(fifthRatingStar);
        return this;
    }

    public void clickOnPostButton() {
        clickElement(postButton);
    }


    // verify sent Post
    public boolean verifyNewNameFromSentPost(String newName) {
        return verifyTextBoolean(medicineName, newName);
    }

    public boolean verifyNewReasonFromSentPost(String newReason) {
        return verifyTextBoolean(reasonName, newReason);
    }

    //verify error message

    public boolean alertErrorMessageRequiredFields() {
        return exists(errorRequiredFields);
    }

    public boolean verifyFirstStarCheckedInSentPost() {
        return exists(markedFirstStarInSentPost);
    }

    public boolean verifySecondStarNonCheckedInSentPost() {
        return exists(unmarkedSecondStarInSentPost);
    }

    public boolean verifyThirdStarCheckedInSentPost() {
        return exists(markedThirdStarInSentPost);

    }

    public boolean verifyFourthStarNonCheckedInSentPost() {
        return exists(unmarkedFourthStarInSentPost);
    }

    public boolean verifyFifthStarCheckedInSentPost() {
        return exists(markedFifthStarInSentPost);
    }


}