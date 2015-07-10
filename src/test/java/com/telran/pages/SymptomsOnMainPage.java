package com.telran.pages;

import com.telran.LogLog4j;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 *
 *  Created by tanyagaus, Lev on 5/30/15.
 */
public class SymptomsOnMainPage  extends Page {
    private static Logger Log = Logger.getLogger(LogLog4j.class.getName());
    /**
     * Labels of categories
     */
    @FindBy(xpath = "//div[@class='col-sm-12']/label[contains(text(),'General Area')]")
    WebElement generalArea;
    @FindBy(xpath = "//button[@id='submit']")
    WebElement submitButton;

    @FindBy(xpath="//input[@type='file']")
    WebElement fileUploadMenu;

    @FindBy(xpath = "//div[@class='col-sm-12']/label[contains(text(),'Specific Area')]")
    WebElement specificArea;

    @FindBy(xpath = "//div[@class='col-sm-12']/label[contains(text(),'Symptom')]")
    WebElement symptom;

    @FindBy(xpath="//select[@data-placeholder='Select a General Area']")
    WebElement selectGeneralArea;

    @FindBy(xpath = "//textarea[@name='bio']")
    WebElement postElement;


    /**
     * Feilds  of Symptoms area
     */
    @FindBy(xpath = "//*[contains(text(),'Select a General Area')]")
    WebElement tooltipGeneralArea;

    @FindBy(xpath = "//*[@class=search-choice-close]")
    WebElement ERROR;

    @FindBy(xpath = "//*[contains(text(),'Select a Specific Area')]")
    WebElement tooltipSpecificArea;

    @FindBy(xpath = "//*[contains(text(),'Select a Symptom')]")
    WebElement tooltipSymptom;

    /**
     * Field for input PostText
     */
    @FindBy(xpath = "//*[@class='form-group']/textarea")
    WebElement tellUsMoreAboutThisSymptomField;


    /**
     * Element of tooltip General area
     */
    @FindBy(xpath= "//ul[@class='chosen-results']/li[@data-option-array-index='1']")
    WebElement itemGrowth;

    @FindBy(xpath= "//ul[@class='chosen-results']/li[@data-option-array-index='2']")
    WebElement itemHeadAndNeck;

    @FindBy(xpath= "//ul[@class='chosen-results']/li[@data-option-array-index='3']")
    WebElement itemHeartAndBloodVessels;

    @FindBy(xpath= "//ul[@class='chosen-results']/li[@data-option-array-index='4']")
    WebElement itemChestAndLungs;

    @FindBy(xpath= "//ul[@class='chosen-results']/li[@data-option-array-index='5']")
    WebElement itemAbdomen;

    @FindBy(xpath= "//ul[@class='chosen-results']/li[@data-option-array-index='6']")
    WebElement itemGrGenitaliaAndUrinaryTract;

    @FindBy(xpath= "//ul[@class='chosen-results']/li[@data-option-array-index='7']")
    WebElement itemSkeletal;

    @FindBy(xpath= "//ul[@class='chosen-results']/li[@data-option-array-index='8']")
    WebElement itemSkinNailHair;

    @FindBy(xpath= "//ul[@class='chosen-results']/li[@data-option-array-index='9']")
    WebElement itemNeurologic;

    @FindBy(xpath= "//ul[@class='chosen-results']/li[@data-option-array-index='10']")
    WebElement itemComplicationsDuringPregnancy;

    @FindBy(xpath= "//ul[@class='chosen-results']/li[@data-option-array-index='11']")
    WebElement itemTumors;

    /**
     * Element of tooltip Specific area
     */
    @FindBy(xpath= "//ul[@class='chosen-results']/li[@data-option-array-index='1']")
    WebElement itemGrowthSpesific;

    @FindBy(xpath= "//ul[@class='chosen-results']/li[@data-option-array-index='1']")
    WebElement itemHeadSpesific;

    @FindBy(xpath= "//ul[@class='chosen-results']/li[@data-option-array-index='2']")
    WebElement itemEarSpesific;

    @FindBy(xpath= "//ul[@class='chosen-results']/li[@data-option-array-index='3']")
    WebElement itemEyeSpesific;

    @FindBy(xpath= "//ul[@class='chosen-results']/li[@data-option-array-index='4']")
    WebElement itemFaceSpesific;

    @FindBy(xpath= "//ul[@class='chosen-results']/li[@data-option-array-index='5']")
    WebElement itemMouthSpesific;

    @FindBy(xpath= "//ul[@class='chosen-results']/li[@data-option-array-index='6']")
    WebElement itemNeckSpesific;

    @FindBy(xpath= "//ul[@class='chosen-results']/li[@data-option-array-index='7']")
    WebElement itemNoseSpesific;

    @FindBy(xpath= "//ul[@class='chosen-results']/li[@data-option-array-index='1']")
    WebElement itemHeartSpesific;

    @FindBy(xpath= "//ul[@class='chosen-results']/li[@data-option-array-index='1']")
    WebElement itemBloodSpesific;

    @FindBy(xpath= "//ul[@class='chosen-results']/li[@data-option-array-index='1']")
    WebElement itemChestSpesific;

    @FindBy(xpath= "//ul[@class='chosen-results']/li[@data-option-array-index='2']")
    WebElement itemLungsSpesific;

    @FindBy(xpath= "//ul[@class='chosen-results']/li[@data-option-array-index='1']")
    WebElement itemAbdomenSpecific;

    @FindBy(xpath= "//ul[@class='chosen-results']/li[@data-option-array-index='1']")
    WebElement itemGenetaliaSpesific;

    @FindBy(xpath= "//ul[@class='chosen-results']/li[@data-option-array-index='2']")
    WebElement itemKidneySpesific;

    @FindBy(xpath= "//ul[@class='chosen-results']/li[@data-option-array-index='1']")
    WebElement itemScullSpesific;

    @FindBy(xpath= "//ul[@class='chosen-results']/li[@data-option-array-index='2']")
    WebElement itemBackSpecific;

    @FindBy(xpath= "//ul[@class='chosen-results']/li[@data-option-array-index='3']")
    WebElement itemArmsHandsSpecific;

    @FindBy(xpath= "//ul[@class='chosen-results']/li[@data-option-array-index='4']")
    WebElement itemLegsSpecific;

    @FindBy(xpath= "//ul[@class='chosen-results']/li[@data-option-array-index='1']")
    WebElement itemSkinSpecific;

    @FindBy(xpath= "//ul[@class='chosen-results']/li[@data-option-array-index='2']")
    WebElement itemNailSpecific;

    @FindBy(xpath= "//ul[@class='chosen-results']/li[@data-option-array-index='3']")
    WebElement itemHairSpecific;

    @FindBy(xpath= "//ul[@class='chosen-results']/li[@data-option-array-index='1']")
    WebElement itemTumorsSpecific;


    /**
     *  Element of tooltip Symptom area
     */
    @FindBy(xpath= "//ul[@class='chosen-results']/li[@data-option-array-index='1']")
    WebElement itemLargeBirthWeight;

    @FindBy(xpath= "//ul[@class='chosen-results']/li[@data-option-array-index='5']")
    WebElement itemTallStature;

    @FindBy(xpath= "//ul[@class='chosen-results']/li[@data-option-array-index='9']")
    WebElement itemOther;

    /**
     *  Buttons
     */
    @FindBy(xpath = "//*[@id='submit']")
    WebElement postButton;

    /**
     * Serves as indication that we are on 'Symptoms' Panel
     */
    @FindBy(xpath = "//div [@class='col-sm-12']/label[contains(text(),'Symptom')]")
    WebElement nameOfSymptomsTitle;

    //Elements on newly created post, that we check
    @FindBy(xpath = "//*[@class='panel story-panel ng-scope panel-default']/../div[5]//*[@class='table post-table ng-scope']//tr[1]/td[2]")
    WebElement generalAreaOnPost;
    @FindBy(xpath = "//*[@class='panel story-panel ng-scope panel-default']/../div[5]//*[@class='table post-table ng-scope']//tr[2]/td[2]")
    WebElement specificAreaOnPost;
    @FindBy(xpath = "//*[@class='panel story-panel ng-scope panel-default']/../div[5]//*[@class='table post-table ng-scope']//tr[3]/td[2]")
    WebElement symptomOnPost;
    @FindBy(xpath = "//*[@class='panel story-panel ng-scope panel-default']/../div[5]//*[@class='post-note ng-binding']")
    WebElement textOnPost;

    /**
     * constructor
     *
     * @param driver
     */
    public SymptomsOnMainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.PAGE_URL = "http://www.52.10.6.51:8080";
    }

    /**
     *  Waits until title of our 'Symptoms' Panel appears on the screen
     */
    public void waitUntilSymptomsPanelIsLoaded(){
        try {
            waitUntilElementIsLoaded(nameOfSymptomsTitle);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * Checks that title of our 'Symptoms' Panel have appeared on the screen so we can work with it
     *
     * @return
     */
    public boolean isOnSymptomsPanel() {
        waitUntilSymptomsPanelIsLoaded();
        return exists(nameOfSymptomsTitle);
    }

    /**
     *
     * @return
     */
    public SymptomsOnMainPage selectGeneralArea() {
        clickElement(tooltipGeneralArea);
        return this;
    }

    /**
     *
     * @return
     */
    public SymptomsOnMainPage selectSpecificArea() {
        clickElement(tooltipSpecificArea);
        return this;
    }


    /**
     *
     * @return
     */
    public SymptomsOnMainPage selectSymptom() {
        try {
            waitUntilElementIsLoaded(tooltipSymptom);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.out.println("--------------------------------------");
            System.out.println("No element is found to select.  select symptoms()");
            System.out.println("--------------------------------------");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        clickElement(tooltipSymptom);
        return this;
    }

    public void createSymptomPost() {
        selectGeneralArea();

        List<WebElement> genAreaList = driver.findElements(By.xpath("//*[@class='chosen-results']/*[contains (@class,'active-result')]"));

        Iterator<WebElement> iter = genAreaList.iterator();

        while (iter.hasNext()) {
            WebElement genArea = iter.next();

            String general = genArea.getText();
            clickElement(genArea);
            Log.info("Selecting General area: " + general + " ");
            selectSpecificArea();
            new Actions(driver).moveToElement(tooltipSpecificArea).perform();
            List<WebElement> specificAreaList = driver.findElements(By.xpath("//*[contains (@class,'active-result')]"));


            for (WebElement specificArea : specificAreaList) {
                String specific = specificArea.getText();
                Log.info("Selecting Specific area: " + specific + " ");
                specificArea.click();

                selectSymptom();
                new Actions(driver).moveToElement(tooltipSymptom).perform();
                List<WebElement> symptomList = driver.findElements(By.xpath("//*[@class='chosen-results']/*[contains (@class,'active-result')]"));
                Iterator<WebElement> iter3 = symptomList.iterator();

                while (iter3.hasNext()) {
                    WebElement sympptom = iter3.next();
                    String symptom = sympptom.getText();
                    Log.info("Selecting Symptom: " + symptom + " ");
                    clickElement(sympptom);
                    String post = ("general Area - " + general + " Specific Area - " + specific + ", Symptom - " + symptom);
                    postText("general Area - " + general + "Specific Area - " + specific + ", Symptom - " + symptom);
                    submitPost();
                    waitForPostLoaded();
                    Assert.assertEquals(specificAreaOnPost.getText(), specific, "Specific area text is wrong");
                    Assert.assertEquals(generalAreaOnPost.getText(), general, "General area text is wrong");
                    Assert.assertEquals(symptomOnPost.getText(), symptom, "Symptom text is wrong");
                    Assert.assertEquals(textOnPost.getText(), post, "Post text is wrong");
                    Reporter.log("New post created with data: \n general Area - " + general + "\n Specific Area - " + specific + ", \n Symptom - " + symptom);

                }
                selectSpecificArea();
                new Actions(driver).moveToElement(tooltipSpecificArea).perform();

            }
            selectGeneralArea();
            new Actions(driver).moveToElement(tooltipGeneralArea).perform();
        }

    }

    /**
     *
     * @param fillTellUs
     * @return
     */
    public SymptomsOnMainPage typeTellUsMore(String fillTellUs) {
        setElementText(tellUsMoreAboutThisSymptomField, fillTellUs);
        return this;
    }

    /**
     *
     * @param i
     * @return
     */
    public WebElement giveMeItem(int i){
        WebElement element;

        String str="//ul[@class='chosen-results']/li[@data-option-array-index='"+i+"']";
        element=driver.findElement(By.xpath(str));
        return element;

    }

    /**
     *
     * @param value
     * @return
     */
    public boolean select_General_Area(String value){
        try {
            cansellInput();
            sleep(1);

            WebElement element = driver.findElement(By.xpath("//span[contains(text(),'Select a General Area')]/.."));
            element.click();
            WebElement element3 = driver.findElement(By.xpath("//select[@data-placeholder='Select a General Area']/../div/div/ul/li[contains(text(),'"+value+"')]"));
            element3.click();
            sleep(1);
        }catch (RuntimeException e){
            System.out.println("--------------------------------------");
            System.out.println("No element is found to select.  select_General_Area()");
            System.out.println("--------------------------------------");
            System.out.println(e.getMessage());

            return false;
        }
        return true;
    }

    /**
     *
     * @param value
     * @return
     */
    public boolean select_Specific_Area(int value){
        try {
            WebElement element = driver.findElement(By.xpath("//span[contains(text(),'Select a Specific Area')]/.."));
            element.click();
            WebElement element3 = driver.findElement(By.xpath("//select[@data-placeholder='Select a Specific Area']/../div/div/ul/li["+value+"]"));
            element3.click();
        }catch (RuntimeException e){
            System.out.println("--------------------------------------");
            System.out.println("No element is found to select.  select_Specific_Area()");
            System.out.println("--------------------------------------");
            System.out.println(e.getMessage());

        }
        return true;
    }

    /**
     *
     * @param value
     * @return
     */
    public boolean select_Symptom(int value){
        try {
            WebElement element = driver.findElement(By.xpath("//span[contains(text(),'Select a Symptom')]/.."));
            element.click();
            WebElement element3 = driver.findElement(By.xpath("//select[@data-placeholder='Select a Symptom']/../div/div/ul/li["+value+"]"));
            element3.click();
        }catch (RuntimeException e){
            System.out.println("--------------------------------------");
            System.out.println("No element is found to select.  select_Symptom()");
            System.out.println("--------------------------------------");
            System.out.println(e.getMessage());

        }
        return true;
    }

    /**
     *
     * @return
     */
    public SymptomsOnMainPage cansellInput(){
        try {
            WebElement element = driver.findElement(By.xpath("//abbr[@class='search-choice-close']"));
            element.click();

        }catch (RuntimeException e){
//        System.out.println("--------------------------------------");
//        System.out.println("No 'X' element is found to select.cansellInput()");
//        System.out.println("--------------------------------------");
//        System.out.println(e.getMessage());
        }

        return this;
    }

    /**
     *
     * @param text
     */
    public void postText(String text){
        setElementText(postElement, text);
    }

    /**
     *
     * @param sec
     */
    public void sleep(int sec){
        try {
            TimeUnit.SECONDS.sleep(sec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void waitForPostLoaded() {
        try {
            this.waitUntilElementIsLoaded(generalAreaOnPost);
        } catch (IOException e) {
            System.out.println("no post loaded error:" + e.getMessage());
        } catch (InterruptedException e) {
            System.out.println("no post loaded error:" + e.getMessage());
        }
    }
    /**
     *
     * @param path
     */
    public void uploadFile(String path){
    fileUploadMenu.sendKeys(path);
}

    /**
     *
     */
    public void submitPost(){
    submitButton.click();
}
}
