package com.telran.pages;

import com.telran.LogLog4j;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.io.IOException;

/**
 * Created by Oleg on 29.05.2015.
 */
public class ProfileDoctorPage extends Page{
    private static Logger Log = Logger.getLogger(LogLog4j.class.getName());

    //lables
    @FindBy(xpath = "//*[@class='ng-binding' and contains(.,'HCP Account')]")
    @CacheLookup
    WebElement HCPAccountLable;

    @FindBy(xpath = "//*[@class='ng-binding ng-scope'][@ng-repeat='specialty in profile.hcpSpecialties']")
    @CacheLookup
    WebElement specialties;

    //buttons
    @FindBy(xpath = "//*[contains(@ng-click,'account_hcp_account')]")
    @CacheLookup
    WebElement EditAccountInformationButton;

    @FindBy(xpath = "//*[contains(@ng-click,'account_hcp_basic')]")
    @CacheLookup
    WebElement EditBasicInformationButton;

    @FindBy(xpath = "//*[@class='btn btn-success ng-binding']")
    @CacheLookup
    WebElement AddHealthcareProfessionalInformationButton;

    @FindBy(xpath = "//div[@class='profile-summary-section ng-scope']/*[5]")
    @CacheLookup
    WebElement DiscoverYourHomePageButton;

    @FindBy(xpath = "//div[@class='container']//i[@class='fa fa-cog fa-2x']")
    @CacheLookup
    WebElement cogwheelButton;

    @FindBy(xpath="//li[@class='ng-scope']/*[contains(text(),'Logout')]")
    @CacheLookup
    WebElement logOutButton;


    public ProfileDoctorPage (WebDriver driver) {
        super(driver);
        this.PAGE_URL = baseUrl + "/account_hcp";
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
        PropertyConfigurator.configure("log4j.properties");
    }

    public ProfileDoctorPage openProfileDoctorPage() {
        driver.get(PAGE_URL);
        return this;
    }

    public ProfileDoctorPage waitUntilProfileDoctorPageIsLoaded() {
        Log.info("Wait for load Profile Doctor page");
        try {
            waitUntilElementIsLoaded(HCPAccountLable);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }

    public ProfileDoctorPage clickOnEditAccInf() {
        clickElement(EditAccountInformationButton);
        return this;
    }

    public ProfileDoctorPage clickOnEditBasInf() {
        clickElement(EditBasicInformationButton);
        return this;
    }

    public ProfileDoctorPage clickOnHealInf() {
        clickElement(AddHealthcareProfessionalInformationButton);
        return this;
    }

    public ProfileDoctorPage clickOnDisYourHP() {
        Log.info("Main page is open");
        clickElement(DiscoverYourHomePageButton);
        return this;
    }

    public ProfileDoctorPage logOut() {
        clickElement(cogwheelButton);
        clickElement(logOutButton);
        return this;
    }

    public boolean isOnProfileDoctorPage() {
        waitUntilProfileDoctorPageIsLoaded();
        return exists(HCPAccountLable);
    }

    public boolean isSpecialtiesExist() {
        return exists(specialties);
    }

}
