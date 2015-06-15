package com.telran.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
/**
 * Created by Oleg on 02.06.2015.
 */
public class DocProfInfPage extends Page{

    //Titles
    @FindBy(xpath = "//*[contains(text(),'Healthcare Professional Information')]")
    WebElement profInfTitle;

    //buttons
    @FindBy(xpath ="//*[@ng-click='addHCPspecialty()' and not(@disabled='disabled')]" )
    WebElement addSpecButton;
    @FindBy(xpath ="//*[@ng-click='addHCPspecialty()' and @disabled='disabled']" )
    WebElement addSpecDisButton;
    @FindBy(xpath ="//*[@ng-click='checkDelete(specialty)']")
    WebElement delSpecButton;
    @FindBy(xpath ="//*[@ng-click='cancelDelete(specialty)']")
    WebElement cancelSpecButton;
    @FindBy(xpath ="//*[@ng-click='deleteHCPspecialty($index)']")
    WebElement confirmSpecButton;
    @FindBy(xpath ="//*[@ng-click='addHCPsubspecialty()'and not(@disabled='disabled')]" )
    WebElement addSubButton;
    @FindBy(xpath ="//*[@ng-click='addHCPsubspecialty()'and @disabled='disabled']" )
    WebElement addSubDisButton;
    @FindBy(xpath ="//*[@ng-click='checkDelete(subspecialty)']" )
    WebElement delSubButton;
    @FindBy(xpath ="//*[@ng-click='cancelDelete(subspecialty)']" )
    WebElement cancelSubButton;
    @FindBy(xpath ="//*[@ng-click='deleteHCPsubspecialty($index)']" )
    WebElement confirmSubButton;
    @FindBy(xpath ="//*[@ng-click='addHCPtitle()'and not(@disabled='disabled')]" )
    WebElement addTitleButton;
    @FindBy(xpath ="//*[@ng-click='addHCPtitle()'and @disabled='disabled']" )
    WebElement addTitleDisButton;
    @FindBy(xpath ="//*[@ng-click='checkDelete(hcptitle)']" )
    WebElement delTitleButton;
    @FindBy(xpath ="//*[@ng-click='cancelDelete(hcptitle)']" )
    WebElement cancelTitleButton;
    @FindBy(xpath ="//*[@ng-click='deleteHCPtitle($index)']" )
    WebElement confirmTitleButton;
    @FindBy(xpath ="//*[@ng-click='addHCPareaofinterest()'and not(@disabled='disabled')]" )
    WebElement addAreasButton;
    @FindBy(xpath ="//*[@ng-click='addHCPareaofinterest()'and @disabled='disabled']" )
    WebElement addAreasDisButton;
    @FindBy(xpath ="//*[@ng-click='cancelDelete(areaofinterest)']" )
    WebElement cancelAreasButton;
    @FindBy(xpath ="//*[@ng-click='checkDelete(areaofinterest)']" )
    WebElement delAreasButton;
    @FindBy(xpath ="//*[@ng-click='deleteHCPareaofinterest($index)']" )
    WebElement confirmAreasButton;
    @FindBy(xpath ="//*[@ng-click='addHCPworkplace()'and not(@disabled='disabled')]" )
    WebElement addWorkPlacesButton;
    @FindBy(xpath ="//*[@ng-click='addHCPworkplace()'and @disabled='disabled']" )
    WebElement addWorkPlacesDisButton;
    @FindBy(xpath ="//*[@ng-click='cancelDelete(workplace)']" )
    WebElement cancelWorkPlacesButton;
    @FindBy(xpath ="//*[@ng-click='checkDelete(workplace)']" )
    WebElement delWorkPlacesButton;
    @FindBy(xpath ="//*[@ng-click='deleteHCPworkplace(workplace, $index)']" )
    WebElement confirmWorkPlacesButton;
    @FindBy(xpath ="//*[@class='btn btn-success' and @ng-click='cancel_save()']" )
    WebElement doneButton;

    //fields
    @FindBy(xpath ="//*[contains( @typeahead,'HCPspecialties')]" )
    WebElement specField;
    @FindBy(xpath ="//*[contains( @typeahead,'subspecialty')]" )
    WebElement subspecField;
    @FindBy(id="titles" )
    WebElement titleField;
    @FindBy(xpath ="//*[contains( @typeahead,'areaofinterest')]" )
    WebElement areasField;
    @FindBy(id="workplacename")
    WebElement workNameField;
    @FindBy(id="workplacelocation" )
    WebElement workLocatField;

    //toltips
    @FindBy(xpath = "//*[contains(@id,'option-0')]/a")
    WebElement areasToltip;
    @FindBy(xpath = "//*[contains(@id,'option-0')]/a")
    WebElement worklocatToltip;


    public DocProfInfPage(WebDriver driver) {
        super(driver);
        this.PAGE_URL = "http://52.10.6.51:8080/account_hcp/hcp";
        PageFactory.initElements(driver, this);
    }

    public DocProfInfPage waitUntilDocProfInfPageIsLoaded() {
        try {
            waitUntilElementIsLoaded(profInfTitle);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }

    public boolean isOnDocProfInfPage() {
        waitUntilDocProfInfPageIsLoaded();
        return exists(profInfTitle);
    }

    public boolean isAddWorkPlacesDisButtonExists() {
        return exists(addWorkPlacesDisButton);
    }

    public DocProfInfPage fillSpecialtiesField(String specialties) {
        setElementText(specField, specialties);
        return this;
    }

    public DocProfInfPage clickOnAddSpecialtiesButton() {
        clickElement(addSpecButton);
        return this;
    }

    public DocProfInfPage clickOnAddSpecialtiesDisButton() {
        clickElement(addSpecDisButton);
        return this;
    }

    public DocProfInfPage fillSubspecialtiesField(String subspecialties) {
        setElementText(subspecField, subspecialties);
        return this;
    }

    public DocProfInfPage clickOnAddSubspecialtiesButton() {
        clickElement(addSubButton);
        return this;
    }

    public DocProfInfPage fillTitlesField(String titles) {
        setElementText(titleField, titles);
        return this;
    }

    public DocProfInfPage clickOnAddTitlesButton() {
        clickElement(addTitleButton);
        return this;
    }

    public DocProfInfPage fillAreasField(String aries) {
        setElementText(areasField, aries);
        return this;
    }

    public DocProfInfPage clickOnAddAreasButton() {
        clickElement(addAreasButton);
        return this;
    }

    public DocProfInfPage fillWorkPlacesNameField(String workName) {
        setElementText(workNameField, workName);
        return this;
    }

    public DocProfInfPage fillWorkPlacesLocationField(String worklocation) {
        setElementText(workLocatField, worklocation);
        return this;
    }

    public DocProfInfPage clickOnAddWorkPlacesButton() {
        clickElement(addWorkPlacesButton);
        return this;
    }

    public DocProfInfPage clickOnDoneButton() {
        clickElement(doneButton);
        return this;
    }

}
