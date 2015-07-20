package com.telran.pages;

import com.telran.LogLog4j;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Anton, Yuri on 30-May-15.
 */
public class LikesPage extends Page{
    private static Logger Log = Logger.getLogger(LogLog4j.class.getName());
    //'like' elements for checking their status
    @FindBy(xpath = "//*[@class='panel story-panel ng-scope panel-default']/../div[5]//*[@class='fa fa-heart-o']")
    WebElement likeUnchecked;
    @FindBy(xpath = "//*[@class='panel story-panel ng-scope panel-default']/../div[5]//*[@ng-if='story.didLike']")
    WebElement likeChecked;
    @FindBy(xpath = "//*[@class='post-top-right']//*[@class='post-like']")
    WebElement likeBlock;
    //universal like - for clicking to it, for the first or not the first time
    @FindBy(xpath = "//*[@class='panel story-panel ng-scope panel-default']/../div[5]//*[contains (@class,'fa fa-heart')]")
    WebElement likeForClick;

    public LikesPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.PAGE_URL = "http://52.10.6.51:8080/home";
    }


    public boolean likeUnchecked() {
        return exists(likeUnchecked);
    }

    public boolean likeChecked() {
        return exists(likeChecked);
    }

    public int getLikesNumber() {
        Log.info("Getting likes number");
        String text = likeBlock.getText();
        return Integer.parseInt(text.replaceAll("[\\D]", ""));

    }

    public LikesPage reloadPage() {
        Log.info("Reloading Page");
        driver.navigate().refresh();
        return this;
    }

    public LikesPage clickToLike() {
        Log.info("Clicking to like");
        clickElement(likeForClick);
        return this;
    }

}
