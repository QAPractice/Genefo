package com.telran;

import com.telran.pages.*;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;


/**
 * Created by Iakov Volf, Olga  on 5/4/2015.
 */
public class RegistrationTest extends TestNgTestBase {

    private static Logger Log = Logger.getLogger(LogLog4j.class.getName());

    public WebDriverWait wait;
    public LoginPage loginPage;
    public String emailNickname; // Keeps the part of email before sign @
    public String emailPositive; // Generates unique Email for positive tests @
    RegistrationPage registrationPage;
    ProfilePage profilePage;
    MainPage mainPage;
    private boolean acceptNextAlert = true;

    @BeforeClass(alwaysRun = true)
    public void setup() {
        PropertyConfigurator.configure("log4j.properties");
        //this.driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 5);
        //driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        registrationPage = PageFactory.initElements(driver, RegistrationPage.class);
        profilePage = PageFactory.initElements(driver, ProfilePage.class);
        mainPage = PageFactory.initElements(driver, MainPage.class);

    }

    @BeforeMethod(alwaysRun = true)
    public void GenerateData() {
        emailNickname = randomAlphabetic(7);
        emailPositive = "one" + emailNickname + "@yopmail.com";
    }

    // @Test(groups = {"positive", "smoke"}, dataProviderClass = DataProviders.class, dataProvider = "SignUpAddress")
    public void AsteriskTest(String address, String type) {
        Log.info("Checking that FirstNAme has asterisk");
        registrationPage
                .checkThatFirstNameFieldHasAsterisk();

    }

    @Test(groups = {"positive", "smoke"}, dataProviderClass = DataProviders.class, dataProvider = "SignUpAddress")
    public void RegTestSuccess(String address, String type) {

        try {
            registrationPage
                    .openRegistrationPage(address)
                    .fillFirstNameField("gggg")
                    .checkThatFirstNameFieldHasAsterisk()
                    .fillLastNameField("")
                    .fillPasswordField("111111")
                    .fillEmailField(emailPositive)
                    .fillConditionField("Polyposis coli")
                    .clickToCheckBox18()
                    .clickToCheckBoxAgree()
                    .clickToSubmit();
            Assert.assertTrue(profilePage.isOnProfilePage());
            Reporter.log("SignUp Successful");
            profilePage.selectGender("2");
            Assert.assertTrue(profilePage.isGenderSelected("Other"), "");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"smoke", "positive"}, dataProviderClass = DataProviders.class, dataProvider = "loadConditionFromFile")
    public void RegTestSuccessWithAllConditions(String condition) {

        try {

            registrationPage
                    .openRegistrationPage();

            registrationPage
                    .fillFirstNameField("gggg")
                    .checkThatFirstNameFieldHasAsterisk()
                    .fillLastNameField("")
                    .fillPasswordField("111111")
                    .fillEmailField(emailPositive)
                    .fillConditionField(condition)
                    .clickToCheckBox18()
                    .clickToCheckBoxAgree()
                    .clickToSubmit();
            Assert.assertTrue(profilePage.isOnProfilePage());
            Reporter.log("SignUp Successful");
            profilePage.selectGender("2");
            Assert.assertTrue(profilePage.isGenderSelected("Other"), "");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"positive"}, dataProviderClass = DataProviders.class, dataProvider = "loadConditionFromFile")
    public void RegTestSuccessWithAllConditionsWebinar(String condition) {

        try {

            registrationPage
                    .openRegWebinar1Page();

            registrationPage
                    .fillFirstNameField("gggg")
                    .checkThatFirstNameFieldHasAsterisk()
                    .fillLastNameField("")
                    .fillPasswordField("111111")
                    .fillEmailField(emailPositive)
                    .fillConditionField(condition)
                    .clickToCheckBox18()
                    .clickToCheckBoxAgree()
                    .clickToSubmit();
            Assert.assertTrue(profilePage.isOnProfilePage());
            Reporter.log("SignUp Successful");
            profilePage.selectGender("2");
            Assert.assertTrue(profilePage.isGenderSelected("Other"), "");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"positive"}, dataProviderClass = DataProviders.class, dataProvider = "loadConditionFromFile")
    public void RegTestSuccessWithAllConditionsWebinar2(String condition) {

        try {

            registrationPage
                    .openRegWebinar2Page();

            registrationPage
                    .fillFirstNameField("gggg")
                    .checkThatFirstNameFieldHasAsterisk()
                    .fillLastNameField("")
                    .fillPasswordField("111111")
                    .fillEmailField(emailPositive)
                    .fillConditionField(condition)
                    .clickToCheckBox18()
                    .clickToCheckBoxAgree()
                    .clickToSubmit();
            Assert.assertTrue(profilePage.isOnProfilePage());
            Reporter.log("SignUp Successful");
            profilePage.selectGender("2");
            Assert.assertTrue(profilePage.isGenderSelected("Other"), "");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"negative"}, dataProviderClass = DataProviders.class, dataProvider = "SignUpAddress")
    public void RegTestWithoutCondition(String address, String type) {

        try {
            registrationPage
                    .openRegistrationPage(address)
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .fillEmailField(emailPositive)
                    .fillConditionFieldNeg("NoCon")
                    .clickToCheckBox18()
                    .clickToCheckBoxAgree();

            Assert.assertTrue(registrationPage.alertMessageNotValidCondition());
            Assert.assertTrue(registrationPage.isOnRegistrationPage());
            Assert.assertTrue(registrationPage.notAvailableSignUpButton());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"positive"}, dataProviderClass = DataProviders.class, dataProvider = "SignUpAddress")
    public void RegTestWithoutLastName(String address, String type) {

        try {
            registrationPage
                    .openRegistrationPage(address)
                    .fillFirstNameField("Piter")
                    .fillLastNameField("222")
                    .fillPasswordField("111111")
                    .fillEmailField(emailPositive)
                    .fillConditionField("Cystic Fibrosis")
                    .clickToCheckBox18()
                    .clickToCheckBoxAgree()
                    .clickToSubmit();
            Assert.assertTrue(registrationPage.alertMessageNotValidLastName());
            Assert.assertTrue(registrationPage.isOnRegistrationPage());
            Assert.assertTrue(registrationPage.notAvailableSignUpButton());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"negative"}, dataProviderClass = DataProviders.class, dataProvider = "SignUpAddress")
    public void RegTestWithLastName256(String address, String type) {

        try {
            registrationPage
                    .openRegistrationPage(address)
                    .fillFirstNameField("Piter")
                    .fillLastNameField("ggg")
                    .fillPasswordField("111111")
                    .fillEmailField(emailPositive)
                    .fillConditionField("Cystic Fibrosis")
                    .clickToCheckBox18()
                    .clickToCheckBoxAgree();
            Assert.assertTrue(registrationPage.alertMessageNotValidLastName());
            Assert.assertTrue(registrationPage.isOnRegistrationPage());
            Assert.assertTrue(registrationPage.notAvailableSignUpButton());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"negative"}, dataProviderClass = DataProviders.class, dataProvider = "SignUpAddress")
    public void RegTestWithoutFirstName(String address, String type) {

        try {
            registrationPage
                    .openRegistrationPage(address)
                    .fillFirstNameField("1")
                    .fillLastNameField("Pen").fillPasswordField("111111")
                    .fillEmailField(emailPositive)
                    .fillConditionField("Cystic Fibrosis")
                    .clickToCheckBox18()
                    .clickToCheckBoxAgree();

            Assert.assertTrue(registrationPage.alertMessageNotValidFirstName());
            Assert.assertTrue(registrationPage.isOnRegistrationPage());
            Assert.assertTrue(registrationPage.notAvailableSignUpButton());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"negative"}, dataProviderClass = DataProviders.class, dataProvider = "SignUpAddress")
    public void RegTestWithoutPassword(String address, String type) {

        try {
            registrationPage
                    .openRegistrationPage(address)
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("")
                    .fillEmailField(emailPositive)
                    .fillConditionField("Cystic Fibrosis")
                    .clickToCheckBox18()
                    .clickToCheckBoxAgree();
            Assert.assertTrue(registrationPage.alertMessageNotValidPassword());
            Assert.assertTrue(registrationPage.isOnRegistrationPage());
            Assert.assertTrue(registrationPage.notAvailableSignUpButton());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"negative"}, dataProviderClass = DataProviders.class, dataProvider = "SignUpAddress")
    public void RegTestWithoutEmail(String address, String type) {

        try {
            registrationPage
                    .openRegistrationPage(address)
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .fillEmailField("")
                    .fillConditionField("Cystic Fibrosis")
                    .clickToCheckBox18()
                    .clickToCheckBoxAgree();
            Assert.assertTrue(registrationPage.alertMessageNotValidEmail());
            Assert.assertTrue(registrationPage.isOnRegistrationPage());
            Assert.assertTrue(registrationPage.notAvailableSignUpButton());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"negative"}, dataProviderClass = DataProviders.class, dataProvider = "SignUpAddress")
    public void RegTestWithoutCheckBox18(String address, String type) {

        try {
            registrationPage
                    .openRegistrationPage(address)
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .fillEmailField(emailPositive)
                    .fillConditionField("Cystic Fibrosis")
                    .clickToCheckBoxAgree();
            Assert.assertTrue(registrationPage.alertMessageNonChecked18());
            Assert.assertTrue(registrationPage.isOnRegistrationPage());
            Assert.assertTrue(registrationPage.notAvailableSignUpButton());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"negative"}, dataProviderClass = DataProviders.class, dataProvider = "SignUpAddress")
    public void RegTestWithoutCheckBoxTerms(String address, String type) {

        try {
            registrationPage
                    .openRegistrationPage(address)
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .fillEmailField(emailPositive)
                    .fillConditionField("Cystic Fibrosis")
                    .clickToCheckBox18();
            Assert.assertTrue(registrationPage.alertMessageNonCheckedTerms());
            Assert.assertTrue(registrationPage.isOnRegistrationPage());
            Assert.assertTrue(registrationPage.notAvailableSignUpButton());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //EmailField
    //1
    @Test(groups = {"smoke", "negative"}, dataProviderClass = DataProviders.class, dataProvider = "SignUpAddress")
    public void RegTestWithoutAtInEmailField(String address, String type) {

        try {
            registrationPage
                    .openRegistrationPage(address)
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .fillEmailField("us000998genefo.com")
                    .fillConditionField("Cystic Fibrosis")
                    .clickToCheckBox18()
                    .clickToCheckBoxAgree();
            Assert.assertTrue(registrationPage.alertMessageNotValidEmail());
            Assert.assertTrue(registrationPage.isOnRegistrationPage());
            Assert.assertTrue(registrationPage.notAvailableSignUpButton());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //2
    @Test(groups = {"negative"}, dataProviderClass = DataProviders.class, dataProvider = "SignUpAddress")
    public void RegTestWithSpecialCharactersInEmailField(String address, String type) {

        try {
            registrationPage
                    .openRegistrationPage(address)
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .fillEmailField("!)*@#$%^&*.com")
                    .fillConditionField("Cystic Fibrosis")
                    .clickToCheckBox18()
                    .clickToCheckBoxAgree();
            Assert.assertTrue(registrationPage.alertMessageNotValidEmail());
            Assert.assertTrue(registrationPage.isOnRegistrationPage());
            Assert.assertTrue(registrationPage.notAvailableSignUpButton());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //3
    @Test(groups = {"negative"}, dataProviderClass = DataProviders.class, dataProvider = "SignUpAddress")
    public void RegTestWithoutLocalPartInEmailField(String address, String type) {

        try {
            registrationPage
                    .openRegistrationPage(address)
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .fillEmailField("@genefo.com")
                    .fillConditionField("Cystic Fibrosis")
                    .clickToCheckBox18()
                    .clickToCheckBoxAgree();
            Assert.assertTrue(registrationPage.alertMessageNotValidEmail());
            Assert.assertTrue(registrationPage.isOnRegistrationPage());
            Assert.assertTrue(registrationPage.notAvailableSignUpButton());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //4
    @Test(groups = {"negative"}, dataProviderClass = DataProviders.class, dataProvider = "SignUpAddress")
    public void RegTestWithoutDomainPartInEmailField(String address, String type) {

        try {
            registrationPage
                    .openRegistrationPage(address)
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .fillEmailField("us000998@")
                    .fillConditionField("Cystic Fibrosis")
                    .clickToCheckBox18()
                    .clickToCheckBoxAgree();
            Assert.assertTrue(registrationPage.alertMessageNotValidEmail());
            Assert.assertTrue(registrationPage.isOnRegistrationPage());
            Assert.assertTrue(registrationPage.notAvailableSignUpButton());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //5

    @Test(groups = {"negative"}, dataProviderClass = DataProviders.class, dataProvider = "SignUpAddress")
    public void RegTestWithConsecutiveDotsInEmailField(String address, String type) {

        try {
            registrationPage
                    .openRegistrationPage(address)
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .fillEmailField("us000998@genefo..com")
                    .fillConditionField("Cystic Fibrosis")
                    .clickToCheckBox18()
                    .clickToCheckBoxAgree();
            Assert.assertTrue(registrationPage.alertMessageNotValidEmail());
            Assert.assertTrue(registrationPage.isOnRegistrationPage());
            Assert.assertTrue(registrationPage.notAvailableSignUpButton());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //6
    @Test(groups = {"negative"}, dataProviderClass = DataProviders.class, dataProvider = "SignUpAddress")
    public void RegTestWithDotInTheBeginningLocalPartEmailField(String address, String type) {

        try {
            registrationPage
                    .openRegistrationPage(address)
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .fillEmailField(".us000998@genefo.com")
                    .fillConditionField("Cystic Fibrosis")
                    .clickToCheckBox18()
                    .clickToCheckBoxAgree();
            Assert.assertTrue(registrationPage.alertMessageNotValidEmail());
            Assert.assertTrue(registrationPage.isOnRegistrationPage());
            Assert.assertTrue(registrationPage.notAvailableSignUpButton());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //7
    @Test(groups = {"negative"}, dataProviderClass = DataProviders.class, dataProvider = "SignUpAddress")
    public void RegTestWithDotInTheBeginningDomainPartEmailField(String address, String type) {

        try {
            registrationPage
                    .openRegistrationPage(address)
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .fillEmailField("us000998@.genefo.com")
                    .fillConditionField("Cystic Fibrosis")
                    .clickToCheckBox18()
                    .clickToCheckBoxAgree();
            Assert.assertTrue(registrationPage.alertMessageNotValidEmail());
            Assert.assertTrue(registrationPage.isOnRegistrationPage());
            Assert.assertTrue(registrationPage.notAvailableSignUpButton());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //8
    @Test(groups = {"negative"}, dataProviderClass = DataProviders.class, dataProvider = "SignUpAddress")
    public void RegTestWithEmailContains256Symbols(String address, String type) {

        try {
            registrationPage
                    .openRegistrationPage(address)
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .fillEmailField("kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk" +
                            "kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk" +
                            "kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk" +
                            "kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk" +
                            "kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk@.genefo.com")
                    .fillConditionField("Alstrom")
                    .clickToCheckBox18()
                    .clickToCheckBoxAgree();
            Assert.assertTrue(registrationPage.alertMessageNotValidEmail());
            Assert.assertTrue(registrationPage.isOnRegistrationPage());
            Assert.assertTrue(registrationPage.notAvailableSignUpButton());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //PasswordField
    //1
    @Test(groups = {"negative"}, dataProviderClass = DataProviders.class, dataProvider = "SignUpAddress")
    public void RegTestWithPasswordContains5Symbols(String address, String type) {

        try {
            registrationPage
                    .openRegistrationPage(address)
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("11111")
                    .fillEmailField(emailPositive)
                    .fillConditionField("Cystic Fibrosis")
                    .clickToCheckBox18()
                    .clickToCheckBoxAgree();
            Assert.assertTrue(registrationPage.alertMessageNotValidPassword());
            Assert.assertTrue(registrationPage.isOnRegistrationPage());
            Assert.assertTrue(registrationPage.notAvailableSignUpButton());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //2
    @Test(groups = {"negative"}, dataProviderClass = DataProviders.class, dataProvider = "SignUpAddress")
    public void RegTestWithPasswordContains13Symbols(String address, String type) {

        try {
            registrationPage
                    .openRegistrationPage(address)
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("1111111111111")
                    .fillEmailField(emailPositive)
                    .fillConditionField("Cystic Fibrosis")
                    .clickToCheckBox18()
                    .clickToCheckBoxAgree();
            Assert.assertTrue(registrationPage.alertMessageNotValidPassword());
            Assert.assertTrue(registrationPage.isOnRegistrationPage());
            Assert.assertTrue(registrationPage.notAvailableSignUpButton());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //FirstName
    //1
    @Test(groups = {"negative"}, dataProviderClass = DataProviders.class, dataProvider = "SignUpAddress")
    public void RegTestWithFirstNameContainsSpecialCharacters(String address, String type) {

        try {
            registrationPage
                    .openRegistrationPage(address)
                    .fillFirstNameField("@#$%^&*(")
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .fillEmailField(emailPositive)
                    .fillConditionField("Cystic Fibrosis")
                    .clickToCheckBox18()
                    .clickToCheckBoxAgree();
            Assert.assertTrue(registrationPage.alertMessageNotValidFirstName());
            Assert.assertTrue(registrationPage.isOnRegistrationPage());
            Assert.assertTrue(registrationPage.notAvailableSignUpButton());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //2
    @Test(groups = {"negative"}, dataProviderClass = DataProviders.class, dataProvider = "SignUpAddress")
    public void RegTestWithFirstNameContainsDigits(String address, String type) {

        try {
            registrationPage
                    .openRegistrationPage(address)
                    .fillFirstNameField("55Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .fillEmailField(emailPositive)
                    .fillConditionField("Cystic Fibrosis")
                    .clickToCheckBox18()
                    .clickToCheckBoxAgree();
            Assert.assertTrue(registrationPage.alertMessageNotValidFirstName());
            Assert.assertTrue(registrationPage.isOnRegistrationPage());
            Assert.assertTrue(registrationPage.notAvailableSignUpButton());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //3
    @Test(groups = {"negative"}, dataProviderClass = DataProviders.class, dataProvider = "SignUpAddress")
    public void RegTestWithFirstNameContainsUnderscore(String address, String type) {

        try {
            registrationPage
                    .openRegistrationPage(address)
                    .fillFirstNameField("Piter_Pit")
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .fillEmailField(emailPositive)
                    .fillConditionField("Cystic Fibrosis")
                    .clickToCheckBox18()
                    .clickToCheckBoxAgree();
            Assert.assertTrue(registrationPage.alertMessageNotValidFirstName());
            Assert.assertTrue(registrationPage.isOnRegistrationPage());
            Assert.assertTrue(registrationPage.notAvailableSignUpButton());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //4
    @Test(groups = {"negative"}, dataProviderClass = DataProviders.class, dataProvider = "SignUpAddress")
    public void RegTestWithFirstNameContains26Symbols(String address, String type) {

        try {
            registrationPage
                    .openRegistrationPage(address)
                    .fillFirstNameField("PiterPiterPiterPiterPiterr")
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .fillEmailField(emailPositive)
                    .fillConditionField("Cystic Fibrosis")
                    .clickToCheckBox18()
                    .clickToCheckBoxAgree();
            Assert.assertTrue(registrationPage.alertMessageNotValidFirstName());
            Assert.assertTrue(registrationPage.isOnRegistrationPage());
            Assert.assertTrue(registrationPage.notAvailableSignUpButton());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //LastName
    //1
    @Test(groups = {"negative"}, dataProviderClass = DataProviders.class, dataProvider = "SignUpAddress")
    public void RegTestWithLastNameContainsSpecialCharacters(String address, String type) {

        try {
            registrationPage
                    .openRegistrationPage(address)
                    .fillFirstNameField("Piter")
                    .fillLastNameField("@#$%^&*(")
                    .fillPasswordField("111111")
                    .fillEmailField(emailPositive)
                    .fillConditionField("Cystic Fibrosis")
                    .clickToCheckBox18()
                    .clickToCheckBoxAgree();
            Assert.assertTrue(registrationPage.alertMessageNotValidLastName());
            Assert.assertTrue(registrationPage.isOnRegistrationPage());
            Assert.assertTrue(registrationPage.notAvailableSignUpButton());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //2
    @Test(groups = {"negative"}, dataProviderClass = DataProviders.class, dataProvider = "SignUpAddress")
    public void RegTestWithLastNameContainsDigits(String address, String type) {

        try {
            registrationPage
                    .openRegistrationPage(address)
                    .fillFirstNameField("Piter")
                    .fillLastNameField("55Pen")
                    .fillPasswordField("111111")
                    .fillEmailField(emailPositive)
                    .fillConditionField("Cystic Fibrosis")
                    .clickToCheckBox18()
                    .clickToCheckBoxAgree();
            Assert.assertTrue(registrationPage.alertMessageNotValidLastName());
            Assert.assertTrue(registrationPage.isOnRegistrationPage());
            Assert.assertTrue(registrationPage.notAvailableSignUpButton());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //3
    @Test(groups = {"negative"}, dataProviderClass = DataProviders.class, dataProvider = "SignUpAddress")
    public void RegTestWithLastNameContainsUnderscore(String address, String type) {

        try {
            registrationPage
                    .openRegistrationPage(address)
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen_Pen")
                    .fillPasswordField("111111")
                    .fillEmailField(emailPositive)
                    .fillConditionField("Cystic Fibrosis")
                    .clickToCheckBox18()
                    .clickToCheckBoxAgree();
            Assert.assertTrue(registrationPage.alertMessageNotValidLastName());
            Assert.assertTrue(registrationPage.isOnRegistrationPage());
            Assert.assertTrue(registrationPage.notAvailableSignUpButton());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //4
    @Test(groups = {"negative"}, dataProviderClass = DataProviders.class, dataProvider = "SignUpAddress")
    public void RegTestWithLastNameContains26Symbols(String address, String type) {

        try {
            registrationPage
                    .openRegistrationPage(address)
                    .fillFirstNameField("Piter")
                    .fillLastNameField("PenPenPenPenPenPenPenPennn")
                    .fillPasswordField("111111")
                    .fillEmailField(emailPositive)
                    .fillConditionField("Cystic Fibrosis")
                    .clickToCheckBox18()
                    .clickToCheckBoxAgree();
            Assert.assertTrue(registrationPage.alertMessageNotValidLastName());
            Assert.assertTrue(registrationPage.isOnRegistrationPage());
            Assert.assertTrue(registrationPage.notAvailableSignUpButton());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Condition
    @Test(groups = {"negative"}, dataProviderClass = DataProviders.class, dataProvider = "SignUpAddress")
    public void RegTestWithConditionNotFromList(String address, String type) {

        try {
            registrationPage
                    .openRegistrationPage(address)
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .fillEmailField(emailPositive)
                    .fillConditionFieldNeg("Appendicitis")
                    .clickToCheckBox18()
                    .clickToCheckBoxAgree();
//
            Assert.assertTrue(registrationPage.alertMessageNotValidCondition());
            Assert.assertTrue(registrationPage.isOnRegistrationPage());
            Assert.assertTrue(registrationPage.notAvailableSignUpButton());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //Positive Tests emails variations

    //1
    @Test(groups = {"positive"}, dataProviderClass = DataProviders.class, dataProvider = "SignUpAddress")
    public void RegTestEmailLocalPartBeginsNumber(String address, String type) {

        try {
            registrationPage
                    .openRegistrationPage(address)
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .fillEmailField(emailPositive)
                    .fillConditionField("Alstrom")
                    .clickToCheckBox18()
                    .clickToCheckBoxAgree()
                    .clickToSubmit();
            Assert.assertTrue(profilePage.isOnProfilePage());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //2
    @Test(groups = {"positive"}, dataProviderClass = DataProviders.class, dataProvider = "SignUpAddress")
    public void RegTestEmailDomainNameBeginsNumber(String address, String type) {

        try {
            registrationPage
                    .openRegistrationPage(address)
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .fillEmailField(emailPositive)
                    .fillConditionField("Alstrom")
                    .clickToCheckBox18()
                    .clickToCheckBoxAgree()
                    .clickToSubmit();
            Assert.assertTrue(profilePage.isOnProfilePage());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //3

    //@Test(groups = {"positive"}, dataProviderClass = DataProviders.class, dataProvider = "SignUpAddress")
    public void RegTestEmailWithDotsLocalAndDomain(String address, String type) {

        try {
            registrationPage
                    .openRegistrationPage(address)
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .fillEmailField("piter.pen.21@77.genefo.com")
                    .fillConditionField("Alstrom")
                    .clickToCheckBox18()
                    .clickToCheckBoxAgree()
                    .clickToSubmit();
            Assert.assertTrue(profilePage.isOnProfilePage());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //4

    @Test(groups = {"positive"}, dataProviderClass = DataProviders.class, dataProvider = "SignUpAddress")
    public void RegTestEmailWithHypenInLocalPart(String address, String type) {

        try {
            registrationPage
                    .openRegistrationPage(address)
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .fillEmailField(emailPositive)
                    .fillConditionField("Alstrom")
                    .clickToCheckBox18()
                    .clickToCheckBoxAgree()
                    .clickToSubmit();
            Assert.assertTrue(profilePage.isOnProfilePage());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //5
    @Test(groups = {"positive"}, dataProviderClass = DataProviders.class, dataProvider = "SignUpAddress")
    public void RegTestEmailWithHypenInDomainPart(String address, String type) {

        try {
            registrationPage
                    .openRegistrationPage(address)
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .fillEmailField(emailPositive)
                    .fillConditionField("Alstrom")
                    .clickToCheckBox18()
                    .clickToCheckBoxAgree()
                    .clickToSubmit();
            Assert.assertTrue(profilePage.isOnProfilePage());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //6

    @Test(groups = {"positive"}, dataProviderClass = DataProviders.class, dataProvider = "SignUpAddress")
    public void RegTestEmailWithUnderscoreInLocalPart(String address, String type) {

        try {
            registrationPage
                    .openRegistrationPage(address)
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .fillEmailField(emailPositive)
                    .fillConditionField("Alstrom")
                    .clickToCheckBox18()
                    .clickToCheckBoxAgree()
                    .clickToSubmit();
            Assert.assertTrue(profilePage.isOnProfilePage());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Positive test for password

    //1
    @Test(groups = {"positive"}, dataProviderClass = DataProviders.class, dataProvider = "SignUpAddress")
    public void RegTestPassword6Symbols(String address, String type) {

        try {
            registrationPage
                    .openRegistrationPage(address)
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("abs123")
                    .fillEmailField(emailPositive)
                    .fillConditionField("Alstrom")
                    .clickToCheckBox18()
                    .clickToCheckBoxAgree()
                    .clickToSubmit();
            Assert.assertTrue(profilePage.isOnProfilePage());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //2
    @Test(groups = {"positive"}, dataProviderClass = DataProviders.class, dataProvider = "SignUpAddress")
    public void RegTestPassword8Symbols(String address, String type) {

        try {
            registrationPage.openRegistrationPage(address)
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("absd1234")
                    .fillEmailField(emailPositive)
                    .fillConditionField("Alstrom")
                    .clickToCheckBox18()
                    .clickToCheckBoxAgree()
                    .clickToSubmit();
            Assert.assertTrue(profilePage.isOnProfilePage());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //3
    @Test(groups = {"positive"}, dataProviderClass = DataProviders.class, dataProvider = "SignUpAddress")
    public void RegTestPassword12Symbols(String address, String type) {

        try {
            registrationPage
                    .openRegistrationPage(address)
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("absdef123456")
                    .fillEmailField(emailPositive)
                    .fillConditionField("Alstrom")
                    .clickToCheckBox18()
                    .clickToCheckBoxAgree()
                    .clickToSubmit();
            Assert.assertTrue(profilePage.isOnProfilePage());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Positive Tests for FirstNameField

    //1
    @Test(groups = {"positive"}, dataProviderClass = DataProviders.class, dataProvider = "SignUpAddress")
    public void RegTestFirstName25Symbols(String address, String type) {

        try {
            registrationPage
                    .openRegistrationPage(address)
                    .fillFirstNameField("PiterPiterPiter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .fillEmailField(emailPositive)
                    .fillConditionField("Alstrom")
                    .clickToCheckBox18()
                    .clickToCheckBoxAgree()
                    .clickToSubmit();
            Assert.assertTrue(profilePage.isOnProfilePage());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test(groups = {"positive"}, dataProviderClass = DataProviders.class, dataProvider = "SignUpAddress")
    public void RegTestFirstName1Symbol(String address, String type) {

        try {
            registrationPage
                    .openRegistrationPage(address)
                    .fillFirstNameField("P")
                    .fillLastNameField("Pen")
                    .fillPasswordField("absdef123456")
                    .fillEmailField(emailPositive)
                    .fillConditionField("Alstrom")
                    .clickToCheckBox18()
                    .clickToCheckBoxAgree()
                    .clickToSubmit();
            Assert.assertTrue(profilePage.isOnProfilePage());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //Positive Tests for LastNameField

    //1
    @Test(groups = {"positive"}, dataProviderClass = DataProviders.class, dataProvider = "SignUpAddress")
    public void RegTestLastName25Symbols(String address, String type) {

        try {
            registrationPage
                    .openRegistrationPage(address)
                    .fillFirstNameField("Piter")
                    .fillLastNameField("PiterPiterPiterPiterPiter")
                    .fillPasswordField("111111")
                    .fillEmailField(emailPositive)
                    .fillConditionField("Alstrom")
                    .clickToCheckBox18()
                    .clickToCheckBoxAgree()
                    .clickToSubmit();
            Assert.assertTrue(profilePage.isOnProfilePage());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //2
    @Test(groups = {"positive"}, dataProviderClass = DataProviders.class, dataProvider = "SignUpAddress")
    public void RegTestLastName1Symbol(String address, String type) {

        try {
            registrationPage
                    .openRegistrationPage(address)
                    .fillFirstNameField("Pitel")
                    .fillLastNameField("P")
                    .fillPasswordField("absdef123456")
                    .fillEmailField(emailPositive)
                    .fillConditionField("Alstrom")
                    .clickToCheckBox18()
                    .clickToCheckBoxAgree()
                    .clickToSubmit();
            Assert.assertTrue(profilePage.isOnProfilePage());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // **
    // Created by Olga Berenson
    //@Test
    public void RegTestSuccessAsterisk(String address, String type) {

        try {

            emailNickname = randomAlphabetic(5);
            registrationPage
                    .openRegistrationPage(address)
                    .fillFirstNameField("gggg")
                    .checkThatFirstNameFieldHasAsterisk()
                    .checkThatConditionFieldHasAsterisk()
                    .checkThatEmailFieldHasAsterisk()
                    .checkThatPasswordFieldHasAsterisk()
                    .fillLastNameField("")
                    .fillPasswordField("111111")
                    .fillEmailField(emailPositive)
                    .fillConditionField("Alstrom")
                    .clickToCheckBox18()
                    .clickToCheckBoxAgree()
                    .clickToSubmit();
            Assert.assertTrue(profilePage.isOnProfilePage());
            profilePage.selectGender("2");
            Assert.assertTrue(profilePage.isGenderSelected("Other"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test(groups = {"positive"}, dataProviderClass = DataProviders.class, dataProvider = "SignUpAddress")
    public void RegTestCheckAgreeBox(String address, String type) {
        try {
            emailNickname = randomAlphabetic(5);
                registrationPage
                        .openRegistrationPage(address)
                        .fillFirstNameField("")
                        .fillPasswordField("111111")
                        .fillEmailField(emailPositive)
                        .fillConditionField("Alstrom")
                        .clickToCheckBox18()
                        .clickToCheckBoxAgree()
                        .CheckThatBoxAgreeAppeard()
                        .clickToSubmit();


            Assert.assertTrue(profilePage.isOnProfilePage());
            profilePage.selectGender("2");
            Assert.assertTrue(profilePage.isGenderSelected("Other"));
            } catch (Exception e) {
                e.printStackTrace();
            }
     }


    private String closeAlertAndGetItsText(String address, String type) {
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

    @AfterMethod(alwaysRun = true)
    public void postcondition() {
        if (mainPage.isLoggedIn()) {
            mainPage.logOut();
        }
    }
}



