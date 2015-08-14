package com.telran.pages;

import com.telran.LogLog4j;
import org.apache.log4j.Logger;
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
    private static Logger Log = Logger.getLogger(LogLog4j.class.getName());

    //elements of dropdown list from medical facilities
    @FindBy(xpath = "//*[contains(text(),'Rating')]/..//*[contains(text(),'Facilities')]")
    WebElement facilitiesLink;

    @FindBy(xpath = "//*[contains(text(),'Rating')]/..//*[contains(text(),'Procedures')]")
    WebElement proceduresLink;

    @FindBy(xpath = "//*[contains(text(),'Rating')]/..//*[contains(text(),'Professionals')]")
    WebElement professionalsLink;

    //elements of dropdown list from Miscellaneous
    @FindBy(xpath = "//*[contains(text(),'Miscellaneous')]/..//*[contains(text(),'Location')]")
    WebElement locationLink;

    @FindBy(xpath = "//*[contains(text(),'Miscellaneous')]/..//*[contains(text(),'Gender')]")
    WebElement genderLink;

    @FindBy(xpath = "//*[contains(text(),'Miscellaneous')]/..//*[contains(text(),'Age')]")
    WebElement ageLink;

    @FindBy(xpath = "//*[contains(text(),'Miscellaneous')]/..//*[contains(text(),'Race')]")
    WebElement raceLink;

    @FindBy(xpath = "//*[contains(text(),'Miscellaneous')]/..//*[contains(text(),'Genes')]")
    WebElement genesLink;

    //elements of dropdown list from What Works For Me
    @FindBy(xpath = "//*[contains(text(),'What Works For Me')]/..//*[contains(text(),'What Works Best')]")
    WebElement whatWorksBestLink;

    @FindBy(xpath = "//*[contains(text(),'What Works For Me')]/..//*[contains(text(),'Therapy')]")
    WebElement therapyLink;

    @FindBy(xpath = "//*[contains(text(),'What Works For Me')]/..//*[contains(text(),'Equipment')]")
    WebElement equipmentLink;

    @FindBy(xpath = "//*[contains(text(),'What Works For Me')]/..//*[contains(text(),'Nutrition')]")
    WebElement nutritionlink;

    @FindBy(xpath = "//*[contains(text(),'What Works For Me')]/..//*[contains(text(),'Exercises')]")
    WebElement exercisesLink;

    @FindBy(xpath = "//*[contains(text(),'What Works For Me')]/..//*[contains(text(),'Alternative Medicine')]")
    WebElement alternativeMedicineLink;

    //elements of dropdown list from Progress
    @FindBy(xpath = "//*[contains(text(),'Progress')]/..//*[contains(text(),'Language')]")
    WebElement languageProgressLink;

    @FindBy(xpath = "//*[contains(text(),'Progress')]/..//*[contains(text(),'Movement')]")
    WebElement movementProgressLink;

    @FindBy(xpath = "//*[contains(text(),'Progress')]/..//*[contains(text(),'Daily living')]")
    WebElement DailylivingProgressLink;

    @FindBy(xpath = "//*[contains(text(),'Progress')]/..//*[contains(text(),'Procedure')]")
    WebElement ProcedureProgressLink;

    @FindBy(xpath = "//*[contains(text(),'Progress')]/..//*[contains(text(),'Complications')]")
    WebElement ComplicationsProgressLink;

    //elements of dropdown list from Medicines

    @FindBy(xpath = "//*[contains(text(),'Medicines')]/..//*[text()='Medicine']")
    WebElement medicineLink;

    @FindBy(xpath = "//*[contains(text(),'Medicines')]/..//*[text()='Medicine Reasons']")
    WebElement medicineReasonsLink;

    @FindBy(xpath = "//*[contains(text(),'Medicines')]/..//*[text()='Medicine Effectiveness']")
    WebElement medicineEffectivenessLink;

    @FindBy(xpath = "//*[contains(text(),'Medicines')]/..//*[text()='Across Conditions']")
    WebElement acrossConditionsLink;


    public GrafsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.PAGE_URL = baseUrl + "/home";
    }


    // Waits until 'Medicine' Panel appears on the screen pp

    public GrafsPage waitUntilGrafsPageIsLoaded() {
        try {
            waitUntilElementIsLoaded(facilitiesLink);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }


    // Checks that title of our 'Medicine' Panel have appeared on the screen so we can work with it.
    public boolean isGrafsPage() {
        waitUntilGrafsPageIsLoaded();
        return exists(facilitiesLink);
    }


    // Click on the third star
    public GrafsPage clikOnFacilities() {
        clickElement(facilitiesLink);
        return this;
    }


    public GrafsPage clikOnProceduresLink() {
        clickElement(proceduresLink);
        return this;
    }

    public GrafsPage clikOnProfessionalsLink() {
        clickElement(professionalsLink);
        return this;
    }

    public GrafsPage clikOnLocationLink() {
        clickElement(locationLink);
        return this;
    }

    public GrafsPage clikOnGenderLink() {
        clickElement(genderLink);
        return this;
    }

    public GrafsPage clikOnAgeLink() {
        clickElement(ageLink);
        return this;
    }

    public GrafsPage clikOnRaceLink() {
        clickElement(raceLink);
        return this;
    }

    public GrafsPage clikOnGenesLink() {
        clickElement(genesLink);
        return this;
    }

    public GrafsPage clikOnWhatWorksBestLink() {
        clickElement(whatWorksBestLink);
        return this;
    }

    public GrafsPage clikOnTherapyLink() {
        clickElement(therapyLink);
        return this;
    }

    public GrafsPage clikOnEquipmentLink() {
        clickElement(equipmentLink);
        return this;
    }

    public GrafsPage clikOnNutritionLink() {
        clickElement(nutritionlink);
        return this;
    }

    public GrafsPage clikOnExercisesLink() {
        clickElement(exercisesLink);
        return this;
    }

    public GrafsPage clikOnAlternativeMedicineLink() {
        clickElement(alternativeMedicineLink);
        return this;
    }

    public GrafsPage clikOnLanguageProgressLink() {
        clickElement(languageProgressLink);
        return this;
    }

    public GrafsPage clikOnMovementProgressLink() {
        clickElement(movementProgressLink);
        return this;
    }

    public GrafsPage clikOnDailylivingProgressLink() {
        clickElement(DailylivingProgressLink);
        return this;
    }

    public GrafsPage clikOnProcedureProgressLink() {
        clickElement(ProcedureProgressLink);
        return this;
    }

    public GrafsPage clikOnComplicationsProgressLink() {
        clickElement(ComplicationsProgressLink);
        return this;
    }

    public GrafsPage clikOnMedicineLink() {
        clickElement(medicineLink);
        return this;
    }

    public GrafsPage clikOnMedicineReasonsLink() {
        clickElement(medicineReasonsLink);
        return this;
    }

    public GrafsPage clikOnMedicineEffectivenessLink() {
        clickElement(medicineEffectivenessLink);
        return this;
    }


    public boolean isGraphLoaded(String element) {
        java.lang.String xpath = "xxx";


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
            case "Language Progress":
                xpath = "Progress - Language";
                break;
            case "Movement Progress":
                xpath = "Developmental Progress - Movement";
                break;
            case "Daily living Progress":
                xpath = "Developmental Progress - Daily living";
                break;
            case "Procedure Progress":
                xpath = "Developmental Progress - Procedure";
                break;
            case "Complications Progress":
                xpath = "Developmental Progress - Complications";
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
            case "Medical Facilities":
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

            case "Medicine":
                xpath = "Medicine used";
                break;
        }

        waitUntilIsLoaded(driver.findElement(By.xpath("//*[contains(text(), '" + xpath + "')]")));
        return exists(driver.findElement(By.xpath("//*[contains(text(), '" + xpath + "')]")));
    }

    public void loadGraphs(String element) {

        switch (element) {

            case "Medicine Reasons":
                clikOnMedicineReasonsLink();
                Log.info("Clicking " + element + " link");
                break;
            case "Medicine Effectiveness":
                clikOnMedicineEffectivenessLink();
                Log.info("Clicking " + element + " link");
                break;
            case "Across Conditions":
                clikOnAcrossConditionsLink();
                Log.info("Clicking " + element + " link");
                break;
            case "Language Progress":
                clikOnLanguageProgressLink();
                Log.info("Clicking " + element + " link");
                break;
            case "Movement Progress":
                clikOnMovementProgressLink();
                Log.info("Clicking " + element + " link");
                break;
            case "Daily living Progress":
                clikOnDailylivingProgressLink();
                Log.info("Clicking " + element + " link");
                break;
            case "Procedure Progress":
                clikOnProcedureProgressLink();
                Log.info("Clicking " + element + " link");
                break;
            case "Complications Progress":
                clikOnComplicationsProgressLink();
                Log.info("Clicking " + element + " link");
                break;
            case "What Works Best":
                clikOnWhatWorksBestLink();
                Log.info("Clicking " + element + " link");
                break;
            case "Therapy":
                clikOnTherapyLink();
                Log.info("Clicking " + element + " link");
                break;
            case "Equipment":
                clikOnEquipmentLink();
                Log.info("Clicking " + element + " link");
                break;
            case "Nutrition":
                clikOnNutritionLink();
                Log.info("Clicking " + element + " link");
                break;
            case "Exercises":
                clikOnExercisesLink();
                Log.info("Clicking " + element + " link");
                break;
            case "Alternative Medicine":
                clikOnAlternativeMedicineLink();
                Log.info("Clicking " + element + " link");
                break;
            case "Medical Facilities":
                clikOnFacilities();
                Log.info("Clicking " + element + " link");
                break;
            case "Procedures":
                clikOnProceduresLink();
                Log.info("Clicking " + element + " link");
                break;
            case "Healthcare Professionals":
                clikOnProfessionalsLink();
                Log.info("Clicking " + element + " link");
                break;
            case "Location":
                clikOnLocationLink();
                Log.info("Clicking " + element + " link");
                break;
            case "Gender":
                clikOnGenderLink();
                Log.info("Clicking " + element + " link");
                break;
            case "Age":
                clikOnAgeLink();
                Log.info("Clicking " + element + " link");
                break;
            case "Race":
                clikOnRaceLink();
                Log.info("Clicking " + element + " link");
                break;
            case "Genes":
                clikOnGenesLink();
                Log.info("Clicking " + element + " link");
                break;
            case "Medicine":
                clikOnMedicineLink();
                Log.info("Clicking " + element + " link");
                break;
        }


    }


    public GrafsPage clikOnAcrossConditionsLink() {
        clickElement(acrossConditionsLink);
        return this;
    }
}
