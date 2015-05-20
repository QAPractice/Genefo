package com.telran.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

/**
 * Created by Л on 5/20/2015.
 */
public class SummaryPage extends Page{
    public SummaryPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public SummaryPage openSummaryPage() {
        driver.get(PAGE_URL);
        return this;
    }

    @FindBy(xpath = "//*[@class = \"progress\"]")
    WebElement progressBar;


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
}
