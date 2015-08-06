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
    @FindBy(xpath = "//*[@class='panel story-panel ng-scope panel-default']/../div[5]//*[contains (@class,'fa-heart')]")
    WebElement likeForClick;

    public LikesPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
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
        int likesNumber = Integer.parseInt(text.replaceAll("[\\D+]", ""));
        Log.info("Number of likes is " + likesNumber);
        return likesNumber;

    }

    public LikesPage pressBackBrowserButton() {
        Log.info("Going back using browser button");
        goBackBrowserButton();
        return this;
    }

    public LikesPage pressForwardBrowserButton() {
        Log.info("Going back using browser button");
        goForwardBrowserButton();
        return this;
    }

    public LikesPage refreshPage() {
        Log.info("Reloading Page");
        reloadPage();
        return this;
    }

    public LikesPage clickToLike() {
        Log.info("Clicking to like");
        clickElement(likeForClick);
        waitUntilIsLoaded(likeChecked);
        return this;
    }

}
