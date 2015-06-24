/*
package com.telran.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

*/
/**
 * Created by Yuri on 6/8/2015.
 *//*

public class GrafsPage extends Page {


    //elements of dropdown list from medical facilities
    @FindBy(xpath = "/*/
/*[contains(text(),'Facilities')]/../*/
/*[contains(text(),'Rating')]")
    WebElement mdRatingLink;

    @FindBy(xpath = "/*/
/*[contains(text(),'Facilities')]/../*/
/*[contains(text(),'Procedures')]")
    WebElement ProceduresLink;

    @FindBy(xpath = "/*/
/*[contains(text(),'Facilities')]/../*/
/*[contains(text(),'Professionals')]")
    WebElement ProfessionalsLink;

    //elements of dropdown list from Miscellaneous
    @FindBy(xpath = "/*/
/*[contains(text(),'Miscellaneous')]/../*/
/*[contains(text(),'Location')]")
    WebElement MiscellaneousLink;

    @FindBy(xpath = "/*/
/*[contains(text(),'Miscellaneous')]/../*/
/*[contains(text(),'Gender')]")
    WebElement GenderLink;

    @FindBy(xpath = "/*/
/*[contains(text(),'Miscellaneous')]/../*/
/*[contains(text(),'Age')]")
    WebElement AgeLink;

    @FindBy(xpath = "/*/
/*[contains(text(),'Miscellaneous')]/../*/
/*[contains(text(),'Race')]")
    WebElement RaceLink;

    @FindBy(xpath = "/*/
/*[contains(text(),'Miscellaneous')]/../*/
/*[contains(text(),'Genes')]")
    WebElement GenesLink;

    //elements of dropdown list from What Works For Me
    @FindBy(xpath = "/*/
/*[contains(text(),'What')]/../*/
/*[contains(text(),'What Works Best')]")
    WebElement WhatWorksBestLink;

    @FindBy(xpath = "/*/
/*[contains(text(),'What')]/../*/
/*[contains(text(),'Therapy')]")
    WebElement TherapyLink;

    @FindBy(xpath = "/*/
/*[contains(text(),'What')]/../*/
/*[contains(text(),'Equipment')]")
    WebElement EquipmentLink;

    @FindBy(xpath = "/*/
/*[contains(text(),'What')]/../*/
/*[contains(text(),'Nutrition')]")
    WebElement NutritionLink;

    @FindBy(xpath = "/*/
/*[contains(text(),'What')]/../*/
/*[contains(text(),'Exercises')]")
    WebElement ExercisesLink;

    @FindBy(xpath = "/*/
/*[contains(text(),'What')]/../*/
/*[contains(text(),'Alternative Medicine')]")
    WebElement AlternativeMedicineLink;

    //elements of dropdown list from Milestones
    @FindBy(xpath = "/*/
/*[contains(text(),'Milestones')]/../*/
/*[contains(text(),'Language Milestones')]")
    WebElement LanguageMilestonesLink;

    @FindBy(xpath = "/*/
/*[contains(text(),'Milestones')]/../*/
/*[contains(text(),'Movement Milestones')]")
    WebElement MovementMilestonesLink;

    @FindBy(xpath = "/*/
/*[contains(text(),'Milestones')]/../*/
/*[contains(text(),'Toileting Milestones')]")
    WebElement ToiletingMilestonesLink;

    //elements of dropdown list from Medicines

    @FindBy(xpath = "/*/
/*[contains(text(),'Medicines')]/../*/
/*[text()='Medicine']")
    WebElement MedicineLink;

    @FindBy(xpath = "/*/
/*[contains(text(),'Medicines')]/../*/
/*[text()='Medicine Reasons']")
    WebElement MedicineReasonsLink;

    @FindBy(xpath = "/*/
/*[contains(text(),'Medicines')]/../*/
/*[text()='Medicine Effectiveness']")
    WebElement MedicineEffectivenessLink;

    @FindBy(xpath = "/*/
/*[contains(text(),'Medicines')]/../*/
/*[text()='Across Conditions']")
    WebElement AcrossConditionsLink;


    public GrafsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);

    }


    // Waits until 'Medicine' Panel appears on the screen pp

    public GrafsPage waitUntilGrafsPageIsLoaded() {
        try {
            waitUntilElementIsLoaded(mdRatingLink);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }



    public boolean isGrafsPage() {
        waitUntilGrafsPageIsLoaded();
        return exists(mdRatingLink);
    }


    public GrafsPage clickOnMDRating() {
        clickElement(mdRatingLink);
        return this;
    }


    public GrafsPage clikOnProceduresLink() {
        clickElement(ProceduresLink);
        return this;
    }

    public GrafsPage clikOnProfessionalsLink() {
        clickElement(ProfessionalsLink);
        return this;
    }

    public GrafsPage clikOnMiscellaneousLink() {
        clickElement(MiscellaneousLink);
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

        if (element.equals("Medicine Reasons")) {
            xpath = "Reason medicine";

        } else if (element.equals("Medicine Effectiveness")) {
            xpath = "Medicine reviews across";

        } else if (element.equals("Across Conditions")) {
            xpath = "Across Conditions";

        } else if (element.equals("Language Milestones")) {
            xpath = "Milestones - Language";

        } else if (element.equals("Movement Milestones")) {
            xpath = "Developmental Milestones - Movement";

        } else if (element.equals("Eating Milestones")) {
            xpath = "Developmental Milestones - Eating";

        } else if (element.equals("Toileting Milestones")) {
            xpath = "Developmental Milestones - Toileting";

        } else if (element.equals("What Works Best")) {
            xpath = "What works best";

        } else if (element.equals("Therapy")) {
            xpath = "Psychotherapy";

        } else if (element.equals("Equipment")) {
            xpath = "Splints";

        } else if (element.equals("Nutrition")) {
            xpath = "Vitamin E";

        } else if (element.equals("Exercises")) {
            xpath = "'Yoga";

        } else if (element.equals("Alternative Medicine")) {
            xpath = "Pets";

        } else if (element.equals("Medical Facilities")) {
            xpath = "Medical Facility";

        } else if (element.equals("Procedures")) {
            xpath = "Medical Procedures";

        } else if (element.equals("Healthcare Professionals")) {
            xpath = "Medical Healthcare";

        } else if (element.equals("Location")) {
            xpath = "Location share in Aldosteronism, glucocorticoid-remediable";

        } else if (element.equals("Gender")) {
            xpath = "Gender share in Aldosteronism, glucocorticoid-remediable";

        } else if (element.equals("Age")) {
            xpath = "Birthday";

        } else if (element.equals("Race")) {
            xpath = "Race share in Aldosteronism, glucocorticoid-remediable";

        } else if (element.equals("Genes")) {
            xpath = "Genes in Aldosteronism, glucocorticoid-remediable";

        } else {
            xpath = "Medicine used";

        }


        return exists(driver.findElement(By.xpath("/*/
/*[contains(text(), '" + xpath + "')]")));
    }


    public GrafsPage clikOnAcrossConditionsLink() {
        clickElement(AcrossConditionsLink);
        return this;
    }
}
*/
