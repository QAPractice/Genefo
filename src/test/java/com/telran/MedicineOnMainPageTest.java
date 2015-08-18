package com.telran;

import com.telran.pages.DataProviders;
import com.telran.pages.LoginPage;
import com.telran.pages.MainPage;
import com.telran.pages.MedicineOnMainPage;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static java.lang.Thread.sleep;
import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by Marina on 6/1/2015.
 */
public class MedicineOnMainPageTest extends TestNgTestBase {
    public WebDriverWait wait;
    public LoginPage loginPage;                         // Pages that we use in our tests
    public MainPage mainPage;
    public MedicineOnMainPage medicineOnMainPage;
    private boolean acceptNextAlert = true;

    @BeforeClass(alwaysRun = true)
    public void setup() {
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        mainPage = PageFactory.initElements(driver, MainPage.class);
        medicineOnMainPage = PageFactory.initElements(driver, MedicineOnMainPage.class);

        try {
            loginPage.openLoginPage(driver, baseUrl);
            loginPage.login("jakoff+Rere@gmail.com", "111111");
            mainPage.waitUntilMainPageIsLoaded()
                    .openMedicinePanel();
            medicineOnMainPage.isOnMedicinePanel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethodSetUp() {
        mainPage
                .openMedicinePanel();
        medicineOnMainPage.waitUntilMedicinePanelIsLoaded();
    }

    //Positive tests
    @Test(groups = {"smoke", "positive"}, enabled = true, description = "Post medicine Randomly", dataProviderClass = DataProviders.class, dataProvider = "loadMedicineTypesFromFile")
    public void postSomeMedicineRandom(String name, String effect, String reason) throws InterruptedException {
        medicineOnMainPage.createMedicinePostRandom(name, effect, reason);
    }
    //Negative tests

    @Test(groups = {"negative"})
    public void sendMedicineWithBlankFieldTest() {


        try {
            medicineOnMainPage
                    .fillNewNameOfMedicine(" ")
                    .fillNewSideEffect(" ")
                    .fillNewReasonForMedicine(" ")
                    .typeTellUsMore(" ")
                    .clickOnPostButton();
            sleep(3000);

            assertTrue(medicineOnMainPage.alertErrorMessageRequiredFields());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Rating with blank mandatory field
    @Test(groups = {"smoke", "negative"})
    public void sendMedicineRatingWithBlankMandatoryFieldTest() {


        try {
            medicineOnMainPage
                    .fillNewNameOfMedicine(" ")
                    .fillNewSideEffect(" ")
                    .fillNewReasonForMedicine(" ")
                    .clickOnAnyStar(1)
                    .typeTellUsMore(" ")
                    .clickOnPostButton();
            sleep(3000);


            assertTrue(medicineOnMainPage.alertErrorMessageRequiredFields());


        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}

