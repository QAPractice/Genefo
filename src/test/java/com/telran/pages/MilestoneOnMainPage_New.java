package com.telran.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Lev on 06/09/2015.
 *
 */
public class MilestoneOnMainPage_New extends Page {

    public MilestoneOnMainPage_New(WebDriver driver) {
        super(driver);
        fillAllXpath();
    }

//          All you need to fill this map in the following format:
//         allElementsMap.put(" Uniq name of the element(you choose)","xpath");
    private void fillAllXpath(){
        allElementsMap= new HashMap<String, String>();

        allElementsMap.put("Developmental Milestone","//div [@class='col-sm-8']/label");
        allElementsMap.put("Treatment Milestone","//div [@class='col-sm-4']/label");
        allElementsMap.put("Language","//div [@class='btn-group']/button[contains(text(),'Language')]");
        allElementsMap.put("Movement","//div [@class='btn-group']/button[contains(text(),'Movement')]");
        allElementsMap.put("Eating","//div [@class='btn-group']/button[contains(text(),'Eating')]");
        allElementsMap.put("Treatment","//div [@class='btn-group']/button[contains(text(),'Treatment')]");
        allElementsMap.put("Drop down list","//*[@class ='chosen-single']/div/b");
        allElementsMap.put("Field years",".//*[@id='milestone_years']");
        allElementsMap.put("Field months",".//*[@id='milestone_months']");
        allElementsMap.put("Field post","//*[@class='form-group']/textarea");
        allElementsMap.put("Button POST","//*[@id='submit']");
        allElementsMap.put("New post","//*[@class='panel story-panel ng-scope panel-default']/../div[5]//*[@class='table post-table']//tr[3]/td[2]");
        allElementsMap.put("New post==>milestone","//*[@class='panel story-panel ng-scope panel-default']/../div[5]//*[@class='table post-table']//tr[3]/td[2]");
        allElementsMap.put("New post==>age","//*[@class='panel story-panel ng-scope panel-default']/../div[5]//*[@class='table post-table']//tr[1]/td[2]");
        allElementsMap.put("New post==>post","//*[@class='panel story-panel ng-scope panel-default']/../div[5]//*[@class='post-note ng-binding']");
        allElementsMap.put("New post==>type","//*[@class='panel story-panel ng-scope panel-default']/../div[5]//*[@class='table post-table']//tr[2]/td[2]");
        allElementsMap.put("Smiles","//ul[@class='chosen-results']/li[@data-option-array-index='1']");

    }
//    no need to write your own methods! All you need is to use standart methods
//    from Test you need to call one of 4 action types:
//    1- click element:                         .clickWebElement(" Uniq name of the element(you choose)")
//    2- wait for element loaded:               .waitUntilIsLoaded(" Uniq name of the element(you choose)")
//    3- input what you need to the field:      .setElementText(" Uniq name of the element(you choose)", "input text here")
//    4- varify that something correct:         .verifyTextBoolean(" Uniq name of the element(you choose)", "text to verify here")

}