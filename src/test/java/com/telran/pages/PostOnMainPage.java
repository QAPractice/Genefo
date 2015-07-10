package com.telran.pages;

import com.telran.LogLog4j;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

/**
 * Created by alex on 01/06/2015.
 */
public class PostOnMainPage extends Page{

    // Serves as indication that we are on 'Post' Panel. We need double quota inside. Do not remove it, please
    @FindBy(xpath = "//a[@class='active'][@ng-click='setType(\"general\")']")
    WebElement postButtonHighLighted;

    // text field for posting
    @FindBy(xpath = "//textarea[@name = 'bio']")
    WebElement postField;

    @FindBy(xpath = "//button[@id='submit'][contains(text(),'Post')]")
    WebElement submitButton;

    private static Logger Log = Logger.getLogger(LogLog4j.class.getName());

    public PostOnMainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        //this.PAGE_URL = "http://52.10.6.51:8080/home";
    }

    // Waits until title of our 'What works' Panel appears on the screen
    public void waitUntilPostPanelIsLoaded() {
        try {
            waitUntilElementIsLoaded(postButtonHighLighted);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Checks that title of our 'Post' Panel have appeared on the screen so we can work with it.
    public boolean isOnPostPanel() {
        waitUntilPostPanelIsLoaded();
        return exists(postButtonHighLighted);
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

