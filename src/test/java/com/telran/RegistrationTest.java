package com.telran;

import com.telran.pages.ProfilePage;
import com.telran.pages.RegistrationPage;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by Iakov Volf, on 5/4/2015.
 */
public class RegistrationTest {

    public WebDriver driver;
    public WebDriverWait wait;
    RegistrationPage registrationPage;
    ProfilePage profilePage;
    private boolean acceptNextAlert = true;


    @BeforeClass
    public void setup() {
        this.driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 5);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        registrationPage = PageFactory.initElements(driver, RegistrationPage.class);
        profilePage = PageFactory.initElements(driver, ProfilePage.class);

        try {
            registrationPage
                    .openRegistrationPage()
                    .waitUntilRegPageIsLoaded();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void RegTestSuccess() {

        try {
            String Name = randomAlphabetic(5);
            registrationPage
                    .fillFirstNameField("gggg")
                    .fillLastNameField("")
                    .fillPasswordField("111111")
                    .fillEmailField("one" + Name + "@usgenefo.com")
                    .fillConditionField("Alstrom")
                    .clickToCheckBox18()
                    .clickToCheckBoxAgree()
                    .clickToSubmit();
            assertTrue(profilePage.isOnProfilePage());
            profilePage.selectGender("2");
            assertTrue(profilePage.isGenderSelected("Other"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void RegTestWhitoutCondition() {

        try {
            registrationPage
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .fillEmailField("us000998@genefo.com")
                    .fillConditionField("NoCon")
                    .clickToCheckBox18()
                    .clickToCheckBoxAgree()
                    .clickToSubmit();
            assertTrue(registrationPage.alertMessageNotValidCondition());
            assertTrue(registrationPage.isOnRegistrationPage());
            assertTrue(registrationPage.notAvailableSignUpButton());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void RegTestWhitoutLastName() {

        try {
            registrationPage
                    .fillFirstNameField("Piter")
                    .fillLastNameField("222")
                    .fillPasswordField("111111")
                    .fillEmailField("us000998@genefo.com")
                    .fillConditionField("Alstrom")
                    .clickToCheckBox18()
                    .clickToCheckBoxAgree()
                    .clickToSubmit();
            assertTrue(registrationPage.alertMessageNotValidLastName());
            assertTrue(registrationPage.isOnRegistrationPage());
            assertTrue(registrationPage.notAvailableSignUpButton());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void RegTestWhitLastName256() {

        try {
            registrationPage
                    .fillFirstNameField("Piter")
                    .fillLastNameField("ggg")
                    .fillPasswordField("111111")
                    .fillEmailField("us000998@genefo.com")
                    .fillConditionField("Alstrom")
                    .clickToCheckBox18()
                    .clickToCheckBoxAgree()
                    .clickToSubmit();
            assertTrue(registrationPage.alertMessageNotValidLastName());
            assertTrue(registrationPage.isOnRegistrationPage());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void RegTestWhitoutFirstName() {

        try {
            registrationPage
                    .fillFirstNameField("1")
                    .fillLastNameField("Pen").fillPasswordField("111111")
                    .fillEmailField("us000998@genefo.com")
                    .fillConditionField("Alstrom")
                    .clickToCheckBox18()
                    .clickToCheckBoxAgree()
                    .clickToSubmit();
            assertTrue(registrationPage.alertMessageNotValidFirstName());
            assertTrue(registrationPage.isOnRegistrationPage());
            assertTrue(registrationPage.notAvailableSignUpButton());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void RegTestWhitoutPassword() {

        try {
            registrationPage
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("")
                    .fillEmailField("us000998@genefo.com")
                    .fillConditionField("Alstrom")
                    .clickToCheckBox18()
                    .clickToCheckBoxAgree()
                    .clickToSubmit();
            assertTrue(registrationPage.alertMessageNotValidPassword());
            assertTrue(registrationPage.isOnRegistrationPage());
            assertTrue(registrationPage.notAvailableSignUpButton());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void RegTestWhitoutEmail() {

        try {
            registrationPage
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .fillEmailField("")
                    .fillConditionField("Alstrom")
                    .clickToCheckBox18()
                    .clickToCheckBoxAgree()
                    .clickToSubmit();
            assertTrue(registrationPage.alertMessageNotValidEmail());
            assertTrue(registrationPage.isOnRegistrationPage());
            assertTrue(registrationPage.notAvailableSignUpButton());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void RegTestWhitoutCheckBox18() {

        try {
            registrationPage
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .fillEmailField("us000998@genefo.com")
                    .fillConditionField("Alstrom")
                    .clickToCheckBoxAgree()
                    .clickToSubmit();
            assertTrue(registrationPage.alertMessageNonChecked18());
            assertTrue(registrationPage.isOnRegistrationPage());
            assertTrue(registrationPage.notAvailableSignUpButton());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void RegTestWhitoutCheckBoxTerms() {

        try {
            registrationPage
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .fillEmailField("us000998@genefo.com")
                    .fillConditionField("Alstrom")
                    .clickToCheckBox18()
                    .clickToSubmit();
            assertTrue(registrationPage.alertMessageNonCheckedTerms());
            assertTrue(registrationPage.isOnRegistrationPage());
            assertTrue(registrationPage.notAvailableSignUpButton());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //EmailField
    //1
    @Test
    public void RegTestWithoutAtInEmailField() {

        try {
            registrationPage
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .fillEmailField("us000998genefo.com")
                    .fillConditionField("Alstrom")
                    .clickToCheckBox18()
                    .clickToCheckBoxAgree()
                    .clickToSubmit();
            assertTrue(registrationPage.alertMessageNotValidEmail());
            assertTrue(registrationPage.isOnRegistrationPage());
            assertTrue(registrationPage.notAvailableSignUpButton());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //2
    @Test
    public void RegTestWithSpecialCharactersInEmailField() {

        try {
            registrationPage
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .fillEmailField("!)*@#$%^&*.com")
                    .fillConditionField("Alstrom")
                    .clickToCheckBox18()
                    .clickToCheckBoxAgree()
                    .clickToSubmit();
            assertTrue(registrationPage.alertMessageNotValidEmail());
            assertTrue(registrationPage.isOnRegistrationPage());
            assertTrue(registrationPage.notAvailableSignUpButton());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //3
    @Test
    public void RegTestWithoutLocalPartInEmailField() {

        try {
            registrationPage
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .fillEmailField("@genefo.com")
                    .fillConditionField("Alstrom")
                    .clickToCheckBox18()
                    .clickToCheckBoxAgree()
                    .clickToSubmit();
            assertTrue(registrationPage.alertMessageNotValidEmail());
            assertTrue(registrationPage.isOnRegistrationPage());
            assertTrue(registrationPage.notAvailableSignUpButton());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //4
    @Test
    public void RegTestWithoutDomainPartInEmailField() {

        try {
            registrationPage
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .fillEmailField("us000998@")
                    .fillConditionField("Alstrom")
                    .clickToCheckBox18()
                    .clickToCheckBoxAgree()
                    .clickToSubmit();
            assertTrue(registrationPage.alertMessageNotValidEmail());
            assertTrue(registrationPage.isOnRegistrationPage());
            assertTrue(registrationPage.notAvailableSignUpButton());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //5

    @Test
    public void RegTestWithConsecutiveDotsInEmailField() {

        try {
            registrationPage
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .fillEmailField("us000998@genefo..com")
                    .fillConditionField("Alstrom")
                    .clickToCheckBox18()
                    .clickToCheckBoxAgree()
                    .clickToSubmit();
            assertTrue(registrationPage.alertMessageNotValidEmail());
            assertTrue(registrationPage.isOnRegistrationPage());
            assertTrue(registrationPage.notAvailableSignUpButton());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //6
    @Test
    public void RegTestWithDotInTheBeginningLocalPartEmailField() {

        try {
            registrationPage
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .fillEmailField(".us000998@genefo.com")
                    .fillConditionField("Alstrom")
                    .clickToCheckBox18()
                    .clickToCheckBoxAgree()
                    .clickToSubmit();
            assertTrue(registrationPage.alertMessageNotValidEmail());
            assertTrue(registrationPage.isOnRegistrationPage());
            assertTrue(registrationPage.notAvailableSignUpButton());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //7
    @Test
    public void RegTestWithDotInTheBeginningDomainPartEmailField() {

        try {
            registrationPage
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .fillEmailField("us000998@.genefo.com")
                    .fillConditionField("Alstrom")
                    .clickToCheckBox18()
                    .clickToCheckBoxAgree()
                    .clickToSubmit();
            assertTrue(registrationPage.alertMessageNotValidEmail());
            assertTrue(registrationPage.isOnRegistrationPage());
            assertTrue(registrationPage.notAvailableSignUpButton());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //8
    @Test
    public void RegTestWithEmailContains256Symbols() {

        try {
            registrationPage
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
                    .clickToCheckBoxAgree()
                    .clickToSubmit();
            assertTrue(registrationPage.alertMessageNotValidEmail());
            assertTrue(registrationPage.isOnRegistrationPage());
            assertTrue(registrationPage.notAvailableSignUpButton());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //PasswordField
    //1
    @Test
    public void RegTestWithPasswordContains5Symbols() {

        try {
            registrationPage
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("11111")
                    .fillEmailField("us000998@genefo.com")
                    .fillConditionField("Alstrom")
                    .clickToCheckBox18()
                    .clickToCheckBoxAgree()
                    .clickToSubmit();
            assertTrue(registrationPage.alertMessageNotValidPassword());
            assertTrue(registrationPage.isOnRegistrationPage());
            assertTrue(registrationPage.notAvailableSignUpButton());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //2
    @Test
    public void RegTestWithPasswordContains13Symbols() {

        try {
            registrationPage
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("1111111111111")
                    .fillEmailField("us000998@genefo.com")
                    .fillConditionField("Alstrom")
                    .clickToCheckBox18()
                    .clickToCheckBoxAgree()
                    .clickToSubmit();
            assertTrue(registrationPage.alertMessageNotValidPassword());
            assertTrue(registrationPage.isOnRegistrationPage());
            assertTrue(registrationPage.notAvailableSignUpButton());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //FirstName
    //1
    @Test
    public void RegTestWithFirstNameContainsSpecialCharacters() {

        try {
            registrationPage
                    .fillFirstNameField("@#$%^&*(")
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .fillEmailField("us000998@genefo.com")
                    .fillConditionField("Alstrom")
                    .clickToCheckBox18()
                    .clickToCheckBoxAgree()
                    .clickToSubmit();
            assertTrue(registrationPage.alertMessageNotValidFirstName());
            assertTrue(registrationPage.isOnRegistrationPage());
            assertTrue(registrationPage.notAvailableSignUpButton());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //2
    @Test
    public void RegTestWithFirstNameContainsDigits() {

        try {
            registrationPage
                    .fillFirstNameField("55Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .fillEmailField("us000998@genefo.com")
                    .fillConditionField("Alstrom")
                    .clickToCheckBox18()
                    .clickToCheckBoxAgree()
                    .clickToSubmit();
            assertTrue(registrationPage.alertMessageNotValidFirstName());
            assertTrue(registrationPage.isOnRegistrationPage());
            assertTrue(registrationPage.notAvailableSignUpButton());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //3
    @Test
    public void RegTestWithFirstNameContainsUnderscore() {

        try {
            registrationPage
                    .fillFirstNameField("Piter_Pit")
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .fillEmailField("us000998@genefo.com")
                    .fillConditionField("Alstrom")
                    .clickToCheckBox18()
                    .clickToCheckBoxAgree()
                    .clickToSubmit();
            assertTrue(registrationPage.alertMessageNotValidFirstName());
            assertTrue(registrationPage.isOnRegistrationPage());
            assertTrue(registrationPage.notAvailableSignUpButton());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //4
    @Test
    public void RegTestWithFirstNameContains26Symbols() {

        try {
            registrationPage
                    .fillFirstNameField("PiterPiterPiterPiterPiterr")
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .fillEmailField("us000998@genefo.com")
                    .fillConditionField("Alstrom")
                    .clickToCheckBox18()
                    .clickToCheckBoxAgree()
                    .clickToSubmit();
            assertTrue(registrationPage.alertMessageNotValidFirstName());
            assertTrue(registrationPage.isOnRegistrationPage());
            assertTrue(registrationPage.notAvailableSignUpButton());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //LastName
    //1
    @Test
    public void RegTestWithLastNameContainsSpecialCharacters() {

        try {
            registrationPage
                    .fillFirstNameField("Piter")
                    .fillLastNameField("@#$%^&*(")
                    .fillPasswordField("111111")
                    .fillEmailField("us000998@genefo.com")
                    .fillConditionField("Alstrom")
                    .clickToCheckBox18()
                    .clickToCheckBoxAgree()
                    .clickToSubmit();
            assertTrue(registrationPage.alertMessageNotValidLastName());
            assertTrue(registrationPage.isOnRegistrationPage());
            assertTrue(registrationPage.notAvailableSignUpButton());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //2
    @Test
    public void RegTestWithLastNameContainsDigits() {

        try {
            registrationPage
                    .fillFirstNameField("Piter")
                    .fillLastNameField("55Pen")
                    .fillPasswordField("111111")
                    .fillEmailField("us000998@genefo.com")
                    .fillConditionField("Alstrom")
                    .clickToCheckBox18()
                    .clickToCheckBoxAgree()
                    .clickToSubmit();
            assertTrue(registrationPage.alertMessageNotValidLastName());
            assertTrue(registrationPage.isOnRegistrationPage());
            assertTrue(registrationPage.notAvailableSignUpButton());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //3
    @Test
    public void RegTestWithLastNameContainsUnderscore() {

        try {
            registrationPage
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen_Pen")
                    .fillPasswordField("111111")
                    .fillEmailField("us000998@genefo.com")
                    .fillConditionField("Alstrom")
                    .clickToCheckBox18()
                    .clickToCheckBoxAgree()
                    .clickToSubmit();
            assertTrue(registrationPage.alertMessageNotValidLastName());
            assertTrue(registrationPage.isOnRegistrationPage());
            assertTrue(registrationPage.notAvailableSignUpButton());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //4
    @Test
    public void RegTestWithLastNameContains26Symbols() {

        try {
            registrationPage
                    .fillFirstNameField("Piter")
                    .fillLastNameField("PenPenPenPenPenPenPenPennn")
                    .fillPasswordField("111111")
                    .fillEmailField("us000998@genefo.com")
                    .fillConditionField("Alstrom")
                    .clickToCheckBox18()
                    .clickToCheckBoxAgree()
                    .clickToSubmit();
            assertTrue(registrationPage.alertMessageNotValidLastName());
            assertTrue(registrationPage.isOnRegistrationPage());
            assertTrue(registrationPage.notAvailableSignUpButton());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Condition
    @Test
    public void RegTestWithConditionNotFromList() {

        try {
            registrationPage
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .fillEmailField("us000998@genefo.com")
                    .fillConditionField("Appendicitis")
                    .clickToCheckBox18()
                    .clickToCheckBoxAgree()
                    .clickToSubmit();
            assertTrue(registrationPage.alertMessageNotValidCondition());
            assertTrue(registrationPage.isOnRegistrationPage());
            assertTrue(registrationPage.notAvailableSignUpButton());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //Positive Tests emails variations

    //1
    @Test
    public void RegTestEmailLocalPartBeginsNumber() {

        try {
            registrationPage
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .fillEmailField("99piter@genefo.com")
                    .fillConditionField("Alstrom")
                    .clickToCheckBox18()
                    .clickToCheckBoxAgree()
                    .clickToSubmit();
            assertTrue(profilePage.isOnProfilePage());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //2
    @Test
    public void RegTestEmailDomainNameBeginsNumber() {

        try {
            registrationPage
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .fillEmailField("piter@77genefo.com")
                    .fillConditionField("Alstrom")
                    .clickToCheckBox18()
                    .clickToCheckBoxAgree()
                    .clickToSubmit();
            assertTrue(profilePage.isOnProfilePage());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //3

    @Test
    public void RegTestEmailWithDotsLocalAndDomain() {

        try {
            registrationPage
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .fillEmailField("piter.pen.21@77.genefo.com")
                    .fillConditionField("Alstrom")
                    .clickToCheckBox18()
                    .clickToCheckBoxAgree()
                    .clickToSubmit();
            assertTrue(profilePage.isOnProfilePage());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //4

    @Test
    public void RegTestEmailWithHypenInLocalPart() {

        try {
            registrationPage
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .fillEmailField("piter-pen@genefo.com")
                    .fillConditionField("Alstrom")
                    .clickToCheckBox18()
                    .clickToCheckBoxAgree()
                    .clickToSubmit();
            assertTrue(profilePage.isOnProfilePage());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //5
    @Test
    public void RegTestEmailWithHypenInDomainPart() {

        try {
            registrationPage
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .fillEmailField("piter@ru-genefo.com")
                    .fillConditionField("Alstrom")
                    .clickToCheckBox18()
                    .clickToCheckBoxAgree()
                    .clickToSubmit();
            assertTrue(profilePage.isOnProfilePage());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //6

    @Test
    public void RegTestEmailWithUnderscoreInLocalPart() {

        try {
            registrationPage
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .fillEmailField("piter_pen@genefo.com")
                    .fillConditionField("Alstrom")
                    .clickToCheckBox18()
                    .clickToCheckBoxAgree()
                    .clickToSubmit();
            assertTrue(profilePage.isOnProfilePage());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Positive test for password

    //1
    @Test
    public void RegTestPassword6Symbols() {

        try {
            registrationPage
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("abs123")
                    .fillEmailField("us000998@genefo.com")
                    .fillConditionField("Alstrom")
                    .clickToCheckBox18()
                    .clickToCheckBoxAgree()
                    .clickToSubmit();
            assertTrue(profilePage.isOnProfilePage());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //2
    @Test
    public void RegTestPassword8Symbols() {

        try {
            registrationPage
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("absd1234")
                    .fillEmailField("us000998@genefo.com")
                    .fillConditionField("Alstrom")
                    .clickToCheckBox18()
                    .clickToCheckBoxAgree()
                    .clickToSubmit();
            assertTrue(profilePage.isOnProfilePage());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //3
    @Test
    public void RegTestPassword12Symbols() {

        try {
            registrationPage
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("absdef123456")
                    .fillEmailField("us000998@genefo.com")
                    .fillConditionField("Alstrom")
                    .clickToCheckBox18()
                    .clickToCheckBoxAgree()
                    .clickToSubmit();
            assertTrue(profilePage.isOnProfilePage());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Positive Tests for FirstNameField

    //1
    @Test
    public void RegTestFirstName25Symbols() {

        try {
            registrationPage
                    .fillFirstNameField("PiterPiterPiter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .fillEmailField("us000998@genefo.com")
                    .fillConditionField("Alstrom")
                    .clickToCheckBox18()
                    .clickToCheckBoxAgree()
                    .clickToSubmit();
            assertTrue(profilePage.isOnProfilePage());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void RegTestFirstName1Symbol() {

        try {
            registrationPage
                    .fillFirstNameField("P")
                    .fillLastNameField("Pen")
                    .fillPasswordField("absdef123456")
                    .fillEmailField("us000998@genefo.com")
                    .fillConditionField("Alstrom")
                    .clickToCheckBox18()
                    .clickToCheckBoxAgree()
                    .clickToSubmit();
            assertTrue(profilePage.isOnProfilePage());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //Positive Tests for LastNameField

    //1
    @Test
    public void RegTestLastName25Symbols() {

        try {
            registrationPage
                    .fillFirstNameField("Piter")
                    .fillLastNameField("PiterPiterPiterPiterPiter")
                    .fillPasswordField("111111")
                    .fillEmailField("us000998@genefo.com")
                    .fillConditionField("Alstrom")
                    .clickToCheckBox18()
                    .clickToCheckBoxAgree()
                    .clickToSubmit();
            assertTrue(profilePage.isOnProfilePage());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //2
    @Test
    public void RegTestLastName1Symbol() {

        try {
            registrationPage
                    .fillFirstNameField("Pitel")
                    .fillLastNameField("P")
                    .fillPasswordField("absdef123456")
                    .fillEmailField("us000998@genefo.com")
                    .fillConditionField("Alstrom")
                    .clickToCheckBox18()
                    .clickToCheckBoxAgree()
                    .clickToSubmit();
            assertTrue(profilePage.isOnProfilePage());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }





    @AfterClass(alwaysRun = true)
    public void teardown() {
        this.driver.quit();
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


