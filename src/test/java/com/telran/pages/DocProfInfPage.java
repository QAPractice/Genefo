package com.telran.pages;

        import org.openqa.selenium.WebDriver;
        import org.openqa.selenium.WebElement;
        import org.openqa.selenium.support.FindBy;
        import org.openqa.selenium.support.PageFactory;

        import java.io.IOException;
/**
 * Created by Oleg on 02.06.2015.
 */
public class DocProfInfPage extends Page{

    //Titles
    @FindBy(xpath = "//*[contains(text(),'Healthcare Professional Information')]")
    WebElement profInfTitle;

    //buttons
    @FindBy(xpath ="//*[@ng-click='addHCPspecialty()']" )
    WebElement addSpecButton;
    @FindBy(xpath ="//*[@ng-click='checkDelete(specialty)']")
    WebElement delSpecButton;
    @FindBy(xpath ="//*[@ng-click='cancelDelete(specialty)']")
    WebElement cancelSpecButton;
    @FindBy(xpath ="//*[@ng-click='deleteHCPspecialty($index)']")
    WebElement confirmSpecButton;
    @FindBy(xpath ="//*[@ng-click='addHCPsubspecialty()']" )
    WebElement addSubButton;
    @FindBy(xpath ="//*[@ng-click='checkDelete(subspecialty)']" )
    WebElement delSubButton;
    @FindBy(xpath ="//*[@ng-click='cancelDelete(subspecialty)']" )
    WebElement cancelSubButton;
    @FindBy(xpath ="//*[@ng-click='deleteHCPsubspecialty($index)']" )
    WebElement confirmSubButton;
    @FindBy(xpath ="//*[@ng-click='addHCPtitle()']" )
    WebElement addTitleButton;
    @FindBy(xpath ="//*[@ng-click='checkDelete(hcptitle)']" )
    WebElement delTitleButton;
    @FindBy(xpath ="//*[@ng-click='cancelDelete(hcptitle)']" )
    WebElement cancelTitleButton;
    @FindBy(xpath ="//*[@ng-click='deleteHCPtitle($index)']" )
    WebElement confirmTitleButton;
    @FindBy(xpath ="//*[@ng-click='addHCPareaofinterest()']" )
    WebElement addAreasButton;
    @FindBy(xpath ="//*[@ng-click='cancelDelete(areaofinterest)']" )
    WebElement cancelAreasButton;
    @FindBy(xpath ="//*[@ng-click='checkDelete(areaofinterest)']" )
    WebElement delAreasButton;
    @FindBy(xpath ="//*[@ng-click='deleteHCPareaofinterest($index)']" )
    WebElement confirmAreasButton;
    @FindBy(xpath ="//*[@ng-click='addHCPworkplace()']" )
    WebElement addWorkPlacesButton;
    @FindBy(xpath ="//*[@ng-click='cancelDelete(workplace)']" )
    WebElement cancelWorkPlacesButton;
    @FindBy(xpath ="//*[@ng-click='checkDelete(workplace)']" )
    WebElement delWorkPlacesButton;
    @FindBy(xpath ="//*[@ng-click='deleteHCPworkplace(workplace, $index)']" )
    WebElement confirmWorkPlacesButton;
    @FindBy(xpath ="//*[@class='btn btn-success' and @ng-click='cancel_save()']" )
    WebElement doneButton;

    //fields
    @FindBy(xpath ="//*[@aria-owns='typeahead-1FT-2349']" )
    WebElement specField;
    @FindBy(xpath ="//*[@aria-owns='typeahead-1FU-1901']" )
    WebElement subspecField;
    @FindBy(id="titles" )
    WebElement titleField;
    @FindBy(xpath ="//*[@aria-owns='typeahead-1FV-5967']" )
    WebElement areasField;
    @FindBy(id="workplacename")
    WebElement workNameField;
    @FindBy(id="workplacelocation" )
    WebElement workLocatField;

    //toltips
    @FindBy(xpath = "//*[contains(@id,'option-0')]/a")
    WebElement areasToltip;
    @FindBy(xpath = "//*[contains(@id,'option-0')]/a")
    WebElement worklocatToltip;


    public DocProfInfPage(WebDriver driver) {
        super(driver);
        this.PAGE_URL = "http://genefo-env.elasticbeanstalk.com/account_hcp/hcp";
        PageFactory.initElements(driver, this);
    }

    public DocProfInfPage waitUntilDocProfInfPageIsLoaded() {
        try {
            waitUntilElementIsLoaded(profInfTitle);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }

    public boolean isOnDocProfInfPage() {
        waitUntilDocProfInfPageIsLoaded();
        return exists(profInfTitle);
    }


}
