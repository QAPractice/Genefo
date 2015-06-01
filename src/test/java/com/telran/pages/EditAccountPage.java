package com.telran.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditAccountPage extends Page{

//    fields
     @FindBy(name="email")
    WebElement emailElement;

    @FindBy(name="newpassword")
    WebElement newPasswordElement;

    @FindBy(name="firstName")
    WebElement firstNameElement;

    @FindBy(name="lastName")
    WebElement lastNameElement;

    //    buttons
    @FindBy(xpath = "//*[@name='info_form']/div[3]/div//*[@id='submit']")
    WebElement submitButton1;

    @FindBy(xpath = "//*[@name='basic_form']/div[3]/div//*[@id='submit']")
    WebElement submitButton2;

    public EditAccountPage(WebDriver driver) {
        super(driver);
        this.PAGE_URL="http://genefo-env.elasticbeanstalk.com/account";
    }
}
