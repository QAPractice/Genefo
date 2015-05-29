package com.telran.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

/**
 * Created by oshil on 29.05.2015.
 */
public class ProfileDoctorPage extends Page{

    //lables
    @FindBy(xpath = "//*[@class=\"ng-binding\" and contains(.,'HCP Account')]")
    WebElement HCP_AccountLable;

    //buttons
    @FindBy(xpath = "//*[@class=\"btn btn-success\" and @ng-click=\"setProfileView('account_hcp_account')]")
    WebElement EditAccountInformationButton;

    @FindBy(xpath = "//*[@class=\"btn btn-success\" and @ng-click=\"setProfileView('account_hcp_basic')\"]")
    WebElement EditBasicInformationButton;

    @FindBy(xpath = "//*[@class=\"btn btn-success ng-binding\"]")
    WebElement EditHealthcareProfessionalInformationButton;

    @FindBy(xpath = "//*[@class=\"btn btn-success btn-discover-homepage\"]")
    WebElement DiscoverYourHomePageButton;




    public ProfileDoctorPage (WebDriver driver) {
        super(driver);
        this.PAGE_URL = "http://genefo-env.elasticbeanstalk.com/account_hcp";
        PageFactory.initElements(driver, this);
    }

    public ProfileDoctorPage waitUntilProfileDoctorPageIsLoaded() {
        try {
            waitUntilElementIsLoaded(HCP_AccountLable);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }




}
