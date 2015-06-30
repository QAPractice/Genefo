package com.telran.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

/**
 * Created by Yuri on 6/8/2015.
 */
public class GrafsPage extends Page {


    //elements of dropdown list from medical facilities
    @FindBy(xpath = "//*[contains(text(),'Rating')]/..//*[contains(text(),'Facilities')]")
    WebElement Facilities;

    @FindBy(xpath = "//*[contains(text(),'Facilities')]/..//*[contains(text(),'Procedures')]")
    WebElement proceduresLink;

    @FindBy(xpath = "//*[contains(text(),'Facilities')]/..//*[contains(text(),'Professionals')]")
    WebElement ProfessionalsLink;

    //elements of dropdown list from Miscellaneous
    @FindBy(xpath = "//*[contains(text(),'Miscellaneous')]/..//*[contains(text(),'Location')]")
    WebElement LocationLink;

    @FindBy(xpath = "//*[contains(text(),'Miscellaneous')]/..//*[contains(text(),'Gender')]")
    WebElement GenderLink;

    @FindBy(xpath = "//*[contains(text(),'Miscellaneous')]/..//*[contains(text(),'Age')]")
    WebElement AgeLink;

    @FindBy(xpath = "//*[contains(text(),'Miscellaneous')]/..//*[contains(text(),'Race')]")
    WebElement RaceLink;

    @FindBy(xpath = "//*[contains(text(),'Miscellaneous')]/..//*[contains(text(),'Genes')]")
    WebElement GenesLink;

    //elements of dropdown list from What Works For Me
    @FindBy(xpath = "//*[contains(text(),'What')]/..//*[contains(text(),'What Works Best')]")
    WebElement WhatWorksBestLink;

    @FindBy(xpath = "//*[contains(text(),'What')]/..//*[contains(text(),'Therapy')]")
    WebElement TherapyLink;

    @FindBy(xpath = "//*[contains(text(),'What')]/..//*[contains(text(),'Equipment')]")
    WebElement EquipmentLink;

    @FindBy(xpath = "//*[contains(text(),'What')]/..//*[contains(text(),'Nutrition')]")
    WebElement NutritionLink;

    @FindBy(xpath = "//*[contains(text(),'What')]/..//*[contains(text(),'Exercises')]")
    WebElement ExercisesLink;

    @FindBy(xpath = "//*[contains(text(),'What')]/..//*[contains(text(),'Alternative Medicine')]")
    WebElement AlternativeMedicineLink;

    //elements of dropdown list from Milestones
    @FindBy(xpath = "//*[contains(text(),'Milestones')]/..//*[contains(text(),'Language Milestones')]")
    WebElement LanguageMilestonesLink;

    @FindBy(xpath = "//*[contains(text(),'Milestones')]/..//*[contains(text(),'Movement Milestones')]")
    WebElement MovementMilestonesLink;

    @FindBy(xpath = "//*[contains(text(),'Milestones')]/..//*[contains(text(),'Toileting Milestones')]")
    WebElement ToiletingMilestonesLink;

    //elements of dropdown list from Medicines

    @FindBy(xpath = "//*[contains(text(),'Medicines')]/..//*[text()='Medicine']")
    WebElement MedicineLink;

    @FindBy(xpath = "//*[contains(text(),'Medicines')]/..//*[text()='Medicine Reasons']")
    WebElement MedicineReasonsLink;

    @FindBy(xpath = "//*[contains(text(),'Medicines')]/..//*[text()='Medicine Effectiveness']")
    WebElement MedicineEffectivenessLink;

    @FindBy(xpath = "//*[contains(text(),'Medicines')]/..//*[text()='Across Conditions']")
    WebElement AcrossConditionsLink;


    public GrafsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);

    }


    // Waits until 'Medicine' Panel appears on the screen pp

    public GrafsPage waitUntilGrafsPageIsLoaded() {
        try {
            waitUntilElementIsLoaded(Facilities);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }



    public boolean isGrafsPage() {
        waitUntilGrafsPageIsLoaded();
        return exists(Facilities);
    }


    public GrafsPage clickOnFacilities() {
        clickElement(Facilities);
        return this;
    }


    public GrafsPage clikOnProceduresLink() {
        clickElement(proceduresLink);
        return this;
    }

    public GrafsPage clikOnProfessionalsLink() {
        clickElement(ProfessionalsLink);
        return this;
    }

    public GrafsPage clikOnMiscellaneousLink() {
        clickElement(LocationLink);
        return this;
    }

    public GrafsPage clikOnGenderLink() {
        clickElement(GenderLink);
        return this;
    }

    public GrafsPage clikOnAgeLink() {
        clickElement(AgeLink);
        return this;
    }

    public GrafsPage clikOnRaceLink() {
        clickElement(RaceLink);
        return this;
    }

    public GrafsPage clikOnGenesLink() {
        clickElement(GenesLink);
        return this;
    }

    public GrafsPage clikOnWhatWorksBestLink() {
        clickElement(WhatWorksBestLink);
        return this;
    }

    public GrafsPage clikOnTherapyLink() {
        clickElement(TherapyLink);
        return this;
    }

    public GrafsPage clikOnEquipmentLink() {
        clickElement(EquipmentLink);
        return this;
    }

    public GrafsPage clikOnNutritionLink() {
        clickElement(NutritionLink);
        return this;
    }

    public GrafsPage clikOnExercisesLink() {
        clickElement(ExercisesLink);
        return this;
    }

    public GrafsPage clikOnAlternativeMedicineLink() {
        clickElement(AlternativeMedicineLink);
        return this;
    }

    public GrafsPage clikOnLanguageMilestonesLink() {
        clickElement(LanguageMilestonesLink);
        return this;
    }

    public GrafsPage clikOnMovementMilestonesLink() {
        clickElement(MovementMilestonesLink);
        return this;
    }

    public GrafsPage clikOnToiletingMilestonesLink() {
        clickElement(ToiletingMilestonesLink);
        return this;
    }

    public GrafsPage clikOnMedicineLink() {
        clickElement(MedicineLink);
        return this;
    }

    public GrafsPage clikOnMedicineReasonsLink() {
        clickElement(MedicineReasonsLink);
        return this;
    }

    public GrafsPage clikOnMedicineEffectivenessLink() {
        clickElement(MedicineEffectivenessLink);
        return this;
    }

    public boolean isGraphLoaded(java.lang.String element) {
        java.lang.String xpath = "xxx";
        element = "xxx";

        switch (element) {

            case "Medicine Reasons":
                xpath = "Reason medicine";
                break;
            case "Medicine Effectiveness":
                xpath = "Medicine reviews across";
                break;
            case "Across Conditions":
                xpath = "Across Conditions";
                break;
            case "Language Milestones":
                xpath = "Milestones - Language";
                break;
            case "Movement Milestones":
                xpath = "Developmental Milestones - Movement";
                break;
            case "Eating Milestones":
                xpath = "Developmental Milestones - Eating";
                break;
            case "Toileting Milestones":
                xpath = "Developmental Milestones - Toileting";
                break;
            case "What Works Best":
                xpath = "What works best";
                break;
            case "Therapy":
                xpath = "Psychotherapy";
                break;
            case "Equipment":
                xpath = "Splints";
                break;
            case "Nutrition":
                xpath = "Vitamin E";
                break;
            case "Exercises":
                xpath = "'Yoga";
                break;
            case "Alternative Medicine":
                xpath = "Pets";
                break;
            case "Facilities":
                xpath = "Medical Facility";
                break;
            case "Procedures":
                xpath = "Medical Procedures";
                break;
            case "Healthcare Professionals":
                xpath = "Medical Healthcare";
                break;
            case "Location":
                xpath = "Location share in Aldosteronism, glucocorticoid-remediable";
                break;
            case "Gender":
                xpath = "Gender share in Aldosteronism, glucocorticoid-remediable";
                break;
            case "Age":
                xpath = "Birthday";
                break;
            case "Race":
                xpath = "Race share in Aldosteronism, glucocorticoid-remediable";
                break;
            case "Genes":
                xpath = "Genes in Aldosteronism, glucocorticoid-remediable";
                break;


            case "":

            default:
            case "Medicine":
                xpath = "Medicine used";
                break;
        }


        return exists(driver.findElement(By.xpath("//*[contains(text(), '" + xpath + "')]")));
    }


    public GrafsPage clikOnAcrossConditionsLink() {
        clickElement(AcrossConditionsLink);
        return this;
    }
}
