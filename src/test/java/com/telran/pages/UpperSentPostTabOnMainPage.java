package com.telran.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by alex on 5/31/2015.
 */




public class UpperSentPostTabOnMainPage extends Page{


    // Upper Tab of sent posts
    @FindBy(xpath = "//*[@class='panel story-panel ng-scope panel-default']/../div[5]")
    WebElement  UpperSentPostTab;

    @FindBy(xpath = "//*[@class='panel story-panel ng-scope panel-default']/../div[5]//div[@class='post-note ng-binding']")
    WebElement  SentPostText;

    @FindBy(xpath = "//*[@class='panel story-panel ng-scope panel-default']/../div[5]//*[contains(text(),'Category')]/../*[contains(text(),'Therapy')]")
    WebElement  SentPostCategoryTherapy;

    @FindBy(xpath = "//*[@class='panel story-panel ng-scope panel-default']/../div[5]//*[@class='ng-isolate-scope ng-pristine ng-valid']/*[3]//*[contains(text(),'*')]")
    WebElement  filledThirdStarInSentPost;// for now does not work

    @FindBy(xpath = "//*[@class='panel story-panel ng-scope panel-default']/../div[5]//*[@class='ng-isolate-scope ng-pristine ng-valid']/*[4]//*[not(contains(text(),'*'))]")
    WebElement nonFilledFourthStarInSentPost;// for now does not work

    public UpperSentPostTabOnMainPage(WebDriver driver) {
        super(driver);
        this.PAGE_URL = "http://genefo-env.elasticbeanstalk.com/main";
        PageFactory.initElements(driver, this);
    }

    public Boolean verifyTextFromSentPost(String text)  {
              return verifyTextBoolean(SentPostText, text);
    }

    public Boolean verifyCategoryTherapyExists()  {
        return exists(SentPostCategoryTherapy);
    }


}
