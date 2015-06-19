package com.telran.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.LinkedList;
/**
 * Created by Л on 5/20/2015.
 */
public class SummaryPage extends Page {
    @FindBy(xpath = "//div[@class='progress' and contains(.,'75% Complete')]")
    WebElement progressBar;
    @FindBy(xpath = "//div[@class='profile-summary-section ng-scope']//a [@class='btn btn-success btn-discover-homepage']")
    WebElement discoverHomePage;
    @FindBy(xpath = "//div[@class='panel panel-default']//div[@class='col-xs-7 text-left text-capitalize ng-binding'][1]")
    WebElement relationshipField;
    @FindBy(xpath = "//div[@class='panel panel-default']//div[@class='col-xs-7 text-left text-capitalize ng-binding'][2]")
    WebElement nameField;
    @FindBy(xpath = "//div[@class='panel panel-default']//div[@class='col-xs-7 text-left text-capitalize ng-binding'][3]")
    WebElement conditionField;
    @FindBy(xpath = "//div[@class='panel panel-default']//div[@class='col-xs-7 text-left text-capitalize ng-binding'][4]")
    WebElement patientDiagnosisDateField;
    @FindBy(xpath = "//div[@class='panel panel-default']//div[@class='col-xs-7 text-left text-capitalize ng-binding'][5]")
    WebElement genderField;
    @FindBy(xpath = "//div[@class='panel panel-default']//div[@class='col-xs-7 text-left text-capitalize ng-binding'][6]")
    WebElement birthdayField;
    @FindBy(xpath = "//ul[@class='profile_list people_list_sidebar']/li[1]//div[@class='profileName ng-binding']")
    WebElement firstProfileButton;

    public SummaryPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void waitUntilProfilePageIsLoaded() {
        try {
            waitUntilElementIsLoaded(progressBar);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public boolean isOnSummaryPage() {
        waitUntilProfilePageIsLoaded();
        return exists(progressBar);
    }

    public void clickOnDiscoverHome() {
        clickElement(discoverHomePage);
    }
    public boolean isProfileNamePresents(String name) {
        try {
            driver.findElement(By.xpath("//*[contains(text(),'" + name + "')]"));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void clickOnFirstProfile() {
        clickElement(firstProfileButton);
    }

    public boolean areProfileFieldsCorrect(String relation, String name, String lName, String condition, String gender, String month, String day, String year, String diagnoseYear) {
        LinkedList<String> allReqField = new LinkedList<String>();
        String xpath = null;
        allReqField.add(relation);
        allReqField.add(name);
        allReqField.add(lName);
        allReqField.add(condition);
        allReqField.add(gender);
        allReqField.add(month + " " + day + ", " + year);
        allReqField.add(diagnoseYear);
        try {
            for (String s : allReqField) {
                xpath = "//div [@class='col-lg-9']//*[contains(text(), '" + s + "')][@class='col-xs-7 text-left text-capitalize ng-binding']";
                driver.findElement(By.xpath(xpath));
            }
        } catch (NoSuchElementException e) {
            System.out.println("--------------------------------------------");
            System.out.println("Error Required element was not found!");
            System.out.println("xpath of the element:" + xpath);
            System.out.println("--------------------------------------------");
            return false;
        }
        return true;
    }
}