package com.telran.pages;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Zizi78 on 5/28/2015.
 */
public class MilestoneOnMainPage {

    //Category Developmental Milestone buttons

    @FindBy(xpath = "//div [@class='btn-group']/button[contains(text(),'Language')]")
    WebElement languageButton;
    @FindBy(xpath = "//div [@class='btn-group']/button[contains(text(),'Movement')]")
    WebElement movementButton;
    @FindBy(xpath = " //div [@class='btn-group']/button[contains(text(),'Eating')]")
    WebElement eatingtButton;
    @FindBy(xpath = "//div [@class='btn-group']/button[contains(text(),'Treatment')]")
    WebElement treatmentButton;
    @FindBy(xpath = "//div [@class='btn-group']/button[contains(text(),'Other')]")
    WebElement othertButton;
    @FindBy(xpath = "//div [@class='btn-group']/button[contains(text(),'Toileting')]")
    WebElement toiletingtButton;


    //Dropdown list
    @FindBy(xpath = "//*[contains(text(),'Select an Option')]")
    WebElement selectItemList;
    @FindBy(xpath = "//*[contains(text(),'Select an Option')]/../div")
    WebElement selectItemListButton;


    //elements of dropdown list
    //for button Language
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='1']")
    WebElement itemSmiles;
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='2']")
    WebElement itemBabbles;
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='3']")
    WebElement itemFirstword;
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='4']")
    WebElement itemwords;

    //for button Movement

    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='1']")
    WebElement itemHoldshead;
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='2']")
    WebElement itemReachesforobjects;
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='5']")
    WebElement itemCrawls;
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='8']")
    WebElement itemRuns;

    //for  button Eating

    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='1']")
    WebElement itemHoldsbottles;
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='2']")
    WebElement itemEatswithspoon;


    //for button Toleiting
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='1']")
    WebElement itemToilettrained;
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='2']")
    WebElement itemDressesalone;

    //button of Treatment
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='1']")
    WebElement itemSurgery;
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='2']")
    WebElement itemMedicaltrial;
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='3']")
    WebElement itemRemission;


    //button of other


}
