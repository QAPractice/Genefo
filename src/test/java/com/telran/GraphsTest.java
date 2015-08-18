

package com.telran;

import com.telran.pages.DataProviders;
import com.telran.pages.GrafsPage;
import com.telran.pages.LoginPage;
import com.telran.pages.MainPage;
import junit.framework.Assert;
import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/*
*
 * Created by Yura on 19.06.2015.


*/


public class GraphsTest extends TestNgTestBase {
    private static Logger Log = Logger.getLogger(LogLog4j.class.getName());

    public WebDriverWait wait;
    public LoginPage loginPage;
    public MainPage mainPage;
    public GrafsPage grafsPage;
    private boolean acceptNextAlert = true;

    public GraphsTest() {
        super();
    }

    @BeforeClass(alwaysRun = true)
    public void setup() {


        loginPage = PageFactory.initElements(driver, LoginPage.class);
        mainPage = PageFactory.initElements(driver, MainPage.class);
        grafsPage = PageFactory.initElements(driver, GrafsPage.class);


        try {
            loginPage.openLoginPage()
                    .waitUntilLoginPageIsLoaded()
                    .login("jakoff+444@gmail.com", "111111");
            mainPage.waitUntilMainPageIsLoaded()
                    .clikToSeeMoreGraphsButton();
            grafsPage.waitUntilGrafsPageIsLoaded();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"positive"}, dataProviderClass = DataProviders.class, dataProvider = "loadGrafFromFile")
    public void TestGraphsLink(String graph) {
        grafsPage.loadGraphs(graph);
        Log.info("Checking " + graph + " link");
        Assert.assertTrue("Graph element isn't found", grafsPage.isGraphLoaded(graph));
        Log.info("Hurra!!! Graph " + graph + " is presented!");
        Reporter.log("Graph " + graph + " is presented");

    }


    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}
