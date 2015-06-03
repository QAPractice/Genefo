package com.telran.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.IOException;


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

    @FindBy(name="oldpassword")
    WebElement oldpasswordElement;

    //    buttons
    @FindBy(xpath = "//*[@name='info_form']/div[3]/div//*[@id='submit']")
    WebElement submitButton1;

    @FindBy(xpath = "//*[@name='basic_form']/div[3]/div//*[@id='submit']")
    WebElement submitButton2;

    @FindBy(xpath="//div[@class='modal-footer']/a[contains(text(),'Save')]")
    WebElement submitButtonOldPassword;

// alerts

    @FindBy(xpath = "//*[@class='alert alert-success alert-dismissible']/div")
    WebElement changesSuccessAlert;

    public EditAccountPage(WebDriver driver) {
        super(driver);
        this.PAGE_URL="http://genefo-env.elasticbeanstalk.com/account";
    }

    public EditAccountPage openEditAccountPage(){
        loadPage();
        return this;
    }

    public WebElement getNewPasswordElement() {
        return newPasswordElement;
    }

    public WebElement getFirstNameElement() {
        return firstNameElement;
    }

    public WebElement getLastNameElement() {
        return lastNameElement;
    }

    public WebElement getEmailElement(){
        return emailElement;
    }



    public EditAccountPage fillEmailField(String e_mail){
        //Added because function "element.clean()" not worked for me!
        cleanElement(emailElement);
        setElementText(emailElement, e_mail);
        return this;
    }
    public EditAccountPage fillPasswordField(String pass){
        //Added because function "element.clean()" not worked for me!
        cleanElement(newPasswordElement);
        setElementText(newPasswordElement, pass);
        return this;
    }

    public EditAccountPage fillField(WebElement element, String str){
        cleanElement(element);
        setElementText(element, str);
        return this;
    }


    private void cleanElement(WebElement element){

        element.sendKeys(Keys.CONTROL + "a");
        element.sendKeys(Keys.DELETE);
    }
    public  EditAccountPage waitUntilEditElementIsLoaded(){
        try {
            waitUntilElementIsLoaded(emailElement);
            waitUntilElementIsLoaded(newPasswordElement);
            waitUntilElementIsLoaded(firstNameElement);
            waitUntilElementIsLoaded(lastNameElement);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }
    public EditAccountPage fillOldPasswordField(String pass){

        cleanElement(oldpasswordElement);
        setElementText(oldpasswordElement, pass);
        return this;
    }

    public EditAccountPage clickOnSubmitButton1(){
        clickElement(submitButton1);
        return this;
    }
    public EditAccountPage clickOnSubmitButton2(){
        clickElement(submitButton2);
        return this;
    }

    public EditAccountPage clickOnSubmitButtonOldPassword(){
        clickElement(submitButtonOldPassword);
        return this;
    }

    public boolean isSuccessAlert() {

        try {
            waitUntilElementIsLoaded(changesSuccessAlert);
        } catch (IOException e) {
            return false;
        } catch (InterruptedException e) {
            return false;
        }


    return exists(changesSuccessAlert);
    }

}
