package com.telran.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Anton on 30-May-15.
 */
public class LikesPage extends Page{
    //'like' elements for checking their status
    @FindBy(xpath = "//*[@class='panel story-panel ng-scope panel-default']/../div[5]//*[@class='fa fa-heart-o']")
    WebElement likeUnchecked;
    @FindBy(xpath = "//*[@class='panel story-panel ng-scope panel-default']/../div[5]//*[@class='fa fa-heart ng-scope']")
    WebElement likeChecked;
    @FindBy(xpath = "//*[@class='panel story-panel ng-scope panel-default']/../div[5]//*[@class='post-top-right ng-binding']")
    WebElement likeBlock;
    //universal like - for clicking to it, for the first or not the first time
    @FindBy(xpath = "//*[@class='panel story-panel ng-scope panel-default']/../div[5]//*[contains (@class,'fa fa-heart')]")
    WebElement likeForClick;

    public LikesPage(WebDriver driver) {
        super(driver);
    }

    public void clickElement(WebElement Like) {
        super.clickElement(Like);
    }

    public String getLikesNumber() {

        return likeBlock.getText();
    }

    public LikesPage clickToLike() {
        clickElement(likeForClick);

        return this;
    }
}
