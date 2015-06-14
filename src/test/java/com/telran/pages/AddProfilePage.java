package com.telran.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;


public class AddProfilePage extends Page {



    @FindBy(xpath = "//div[@ng-controller='ProfileNewController'] //input[@name='condition']" )
    WebElement Condition;


    @FindBy(xpath = "//div[@ng-controller='ProfileNewController']//input[@value='Select Some Options']" )
    WebElement Patient_Race;

    @FindBy(xpath = "//div[@ng-controller='ProfileNewController'] //*[@name='lastName']")
    WebElement Last_name;

    @FindBy(xpath = "//div[@ng-controller='ProfileNewController'] //*[@name='firstName']")
    WebElement First_name;

    @FindBy(xpath = "//div[@class='panel-heading']//*[contains(text(),'Create New Profile')]")
    WebElement Create_New_Profile;

     @FindBy(xpath = "//div[@ng-controller='ProfileNewController']//input[@type='file']")
    WebElement Add_Picture;

    @FindBy(xpath = "//h2[contains(text(),'My Profiles')]")
    WebElement My_Profiles;

    @FindBy(xpath = "//a[@href='/profile/new']")
    WebElement ADD_ANOTHER_PROFILE;

    @FindBy(xpath = "//div[@ng-controller='ProfileNewController']//select[@name='relationID']")
    WebElement How_do_you_know;

    @FindBy(xpath = "//div[@ng-controller='ProfileNewController']//select[@name='diagnosisMonth'] ")
    WebElement Patient_Diagnosis_Month;

    @FindBy(xpath ="//div[@ng-controller='ProfileNewController']//select[@name='diagnosisYear'] " )
    WebElement Patient_Diagnosis_Year;

    @FindBy(xpath = "//div[@ng-controller='ProfileNewController']//select[@name='genderID']")
    WebElement Patient_Diagnosis_Gender;

    @FindBy(xpath = "//div[@ng-controller='ProfileNewController']//select[@name='birthmonth']")
    WebElement Patient_Birthday_Month;

    @FindBy(xpath = "//div[@ng-controller='ProfileNewController']//select[@name='birthday']")
    WebElement Patient_Birthday_Day;

    @FindBy(xpath = "//div[@ng-controller='ProfileNewController']//select[@name='birthyear']")
    WebElement Patient_Birthday_Year;

    @FindBy(xpath = "//ul[@class='dropdown-menu ng-isolate-scope']/li[1]")
    WebElement Patient_Location_1;

    @FindBy(xpath = "//div[@ng-controller='ProfileNewController']//input[@name='location']")
    WebElement Patient_Location;

    @FindBy(xpath = "//div[@ng-controller='ProfileNewController']//button[@id='submit']")
    WebElement ButtonSave;

    @FindBy(xpath = "//button[contains(text(),'Cancel')]")
    WebElement ButtonCancel;

    @FindBy(xpath = "//textarea")
    WebElement Comment;


    public AddProfilePage(WebDriver driver) {
        super(driver);
        this.PAGE_URL = "http://52.10.6.51:8080/profiles";

    }

//    public void selectById(String name, int id){
//        Select select = new Select(getWebElement(name));
//        select.selectByIndex(id);
//    }
//    public void selectByName(String name, String itemToClick){
//        Select select = new Select(getWebElement(name));
//        select.selectByVisibleText(itemToClick);
//    }

    public AddProfilePage select_How_do_you_know(String itemNumber){
        Select select = new Select(How_do_you_know);
        select.selectByValue(itemNumber);
        return this;
    }


    public AddProfilePage select_Patient_Diagnosis_Month(String itemNumber){
        Select select = new Select(Patient_Diagnosis_Month);
        select.selectByValue(itemNumber);
        return this;
    }
    public AddProfilePage select_Patient_Diagnosis_Year(String itemNumber){
        Select select = new Select(Patient_Diagnosis_Year);
        select.selectByValue(itemNumber);
        return this;
    }
    public AddProfilePage select_Patient_Diagnosis_Gender(String itemNumber){
        Select select = new Select(Patient_Diagnosis_Gender);
        select.selectByValue(itemNumber);
        return this;
    }
    public AddProfilePage select_Patient_Race(int itemNumber){
        Patient_Race.click();
        WebElement  element= driver.findElement(By.xpath("//ul[@class='chosen-results']/li["+itemNumber+"]"));

        element.click();
        return this;
    }
    public AddProfilePage select_Patient_Birthday_Day(String itemNumber){
        Select select = new Select(Patient_Birthday_Day);
        select.selectByValue(itemNumber);
        return this;
    }
    public AddProfilePage select_Patient_Birthday_Month(String itemNumber){
        Select select = new Select(Patient_Birthday_Month);
        select.selectByValue(itemNumber);
        return this;
    }
    public AddProfilePage select_Patient_Birthday_Year(String itemNumber){
        Select select = new Select(Patient_Birthday_Year);
        select.selectByValue(itemNumber);
        return this;
    }

    public AddProfilePage input_Patient_Location(String location){
        setElementText(Patient_Location, location);
//        BUG nothing to choose
//        String xpath = "//*[@class='dropdown-menu ng-isolate-scope']//*[contains(text(),'"+location+"')]";
//        WebElement  element= driver.findElement(By.xpath(xpath));
//        element.click();
        return this;
    }

    public AddProfilePage input_Comment(String text){
        setElementText(Comment, text);
        return this;
    }

    public AddProfilePage input_First_Name(String input){

        setElementText(First_name, input);
        return this;
    }

    public AddProfilePage input_Condition(String input){

        setElementText(Condition, input);
        sleep(1);
        String xpath = "//*[@class='dropdown-menu ng-isolate-scope']//*[contains(text(),'"+input+"')]";
        WebElement  element= driver.findElement(By.xpath(xpath));
        element.click();
        return this;
    }

    public AddProfilePage input_Last_Name(String input){

        setElementText(Last_name, input);
        return this;
    }
    public WebElement get_My_Profiles(){
        return My_Profiles;
    }

    public AddProfilePage ADD_ANOTHER_PROFILE_click(){
        ADD_ANOTHER_PROFILE.click();
        return this;
    }
    public boolean ADD_ANOTHER_PROFILE_isDisplayed(){
        return ADD_ANOTHER_PROFILE.isDisplayed();
    }


    public WebElement get_Create_New_Profile(){
        return My_Profiles;
    }


    public AddProfilePage sleep(int n){
        try {
            TimeUnit.SECONDS.sleep(n);
        } catch (InterruptedException e) {
            System.out.println("Function on Page.class 'sleep'");
            e.printStackTrace();
        }
        return this;
    }

}
