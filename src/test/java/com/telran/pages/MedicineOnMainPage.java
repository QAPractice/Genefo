package com.telran.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Marina on 5/28/2015.
 */
public class MedicineOnMainPage {

    //Dropdown list
    @FindBy(id = "medicine_name")
    WebElement nameOfMedicine;

    @FindBy(id = "medicine_reason")
    WebElement reasonForMedicine;

    //elements of dropdown list
    @FindBy(id = "typeahead-005-725-option-0")
    WebElement itemNameOfMedicine;

    @FindBy(id = "typeahead-006-9768-option-0")
    WebElement itemReasonForMedicine;

    //field
    @FindBy(name = "bio")
    WebElement tellUsMoreAboutThisMedicine;

    //Rate Stars
    @FindBy(xpath = "//div[3]/div[1]/div/form/div[2]/span[2]/span/i[3]")
    WebElement RateBy3Stars;

    @FindBy(xpath = "//div[3]/div[1]/div/form/div[2]/span[2]/span/i[5]")
    WebElement RateBy5Stars;

    @FindBy(xpath = "//div[3]/div[1]/div/form/div[2]/span[2]/span/i[1]")
    WebElement RateBy1Star;

    // Serves as indication that we are on 'WhatWorks' Panel.
    //???????

}
