package com.telran.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

/**
 * Created by alex on 01/06/2015.
 */
public class PostOnMainPage extends Page{

    // Serves as indication that we are on 'Post' Panel.
    //@FindBy(xpath = "//a[@class='active'][@ng-click='setType('general')']")
    WebElement postButtonChecked;

    // text field for posting
    @FindBy(xpath = "//textarea[@name = 'bio']")
    WebElement postField;

    @FindBy(xpath = "//button[@id='submit'][contains(text(),'Post')]")
    WebElement submitButton;


    public PostOnMainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        //this.PAGE_URL = "http://genefo-env.elasticbeanstalk.com/home";
    }

    // Waits until title of our 'What works' Panel appears on the screen
    public void waitUntilPostPanelIsLoaded() {
        try {
            waitUntilElementIsLoaded(postButtonChecked);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Checks that title of our 'Post' Panel have appeared on the screen so we can work with it.
    public boolean isOnPostPanel() {
        waitUntilPostPanelIsLoaded();
        return exists(postButtonChecked);
    }

    public PostOnMainPage fillTextField(String post) {
        setElementText(postField, post);
        return this;
    }

    public PostOnMainPage sendPost() {
        clickElement(submitButton);
        return this;
    }


}

