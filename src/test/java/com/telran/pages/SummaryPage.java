package com.telran.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
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

    @FindBy(xpath = "//div[@class='panel panel-default panel-profile-header']//div[@class = 'progress']")
    WebElement progressBar;
    @FindBy(xpath = "//div[@class='profile-summary-section ng-scope\"]//a [@class=\"btn btn-success btn-discover-homepage']")
    WebElement discoverHomePage;

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
    public void clickOnDiscoverHome(){
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
}
