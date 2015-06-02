package com.telran.pages;

        import org.openqa.selenium.WebDriver;
        import org.openqa.selenium.WebElement;
        import org.openqa.selenium.support.FindBy;
        import org.openqa.selenium.support.PageFactory;

        import java.io.IOException;
/**
 * Created by Oleg on 02.06.2015.
 */
public class ProfInfPage extends Page{

    //Titles
    @FindBy(xpath = "//*[contains(text(),'Healthcare Professional Information')]")
    WebElement profInfTitle;

    //buttons
    @FindBy(xpath ="//*[@ng-click='addHCPspecialty()']" )
    WebElement addSpecButton;
    @FindBy(xpath ="//*[@ng-click='checkDelete(specialty)']" )
    WebElement delSpecButton;
    @FindBy(xpath ="//*[@ng-click='addHCPsubspecialty()']" )
    WebElement addSubButton;
    @FindBy(xpath ="//*[@ng-click='checkDelete(subspecialty)']" )
    WebElement delSubButton;
    @FindBy(xpath ="//*[@ng-click='addHCPtitle()']" )
    WebElement addTitleButton;
    @FindBy(xpath ="//*[@ng-click='checkDelete(hcptitle)']" )
    WebElement delTitleButton;
    @FindBy(xpath ="//*[@ng-click='addHCPareaofinterest()']" )
    WebElement addAreasButton;
    @FindBy(xpath ="//*[@ng-click='addHCPworkplace()']" )
    WebElement addWorkPlacesButton;
    @FindBy(xpath ="//*[@class='btn btn-success' and @ng-click='cancel_save()']" )
    WebElement doneButton;

    //fields


    public ProfInfPage(WebDriver driver) {
        super(driver);
    }
}
