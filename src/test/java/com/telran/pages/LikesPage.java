package com.telran.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Anton on 30-May-15.
 */
public class LikesPage extends Page{
    @FindBy(xpath = " ")
    WebElement Like;


    public void clickElement(WebElement Like) {
        super.clickElement(Like);
    }

    public LikesPage(WebDriver driver) {
        super(driver);
    }
}
