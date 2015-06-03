package com.telran;

import com.telran.pages.HomePage;
import com.telran.pages.ProfileDoctorPage;
import com.telran.pages.SignUpHCPPage;
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
 * Created by Oleg on 31.05.2015.
 */
public class SignUpHCPTest {

    public WebDriver driver;
    public WebDriverWait wait;
    public String EmailNickname; // Keeps the part of email before sign @
    HomePage homePage;
    SignUpHCPPage signUpHCPPage;
    ProfileDoctorPage profileDoctorPage;
    private boolean acceptNextAlert = true;

    @BeforeClass
    public void setup() {
        this.driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 5);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        signUpHCPPage = PageFactory.initElements(driver, SignUpHCPPage.class);
        profileDoctorPage = PageFactory.initElements(driver, ProfileDoctorPage.class);

        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void RegTestSuccess() {

        try {

            EmailNickname = randomAlphabetic(5);
            signUpHCPPage
                    .openHCPRegPage()
                    .fillFirstNameField("gggg")
                    .fillLastNameField("gggg")
                    .fillPasswordField("111111")
                    .fillEmailField("one" + EmailNickname + "@usgenefo.com")
                    .clickOnCheckBox18()
                    .clickOnCheckBoxAgree()
                    .clickOnSignUp();
            profileDoctorPage.waitUntilProfileDoctorPageIsLoaded();
            assertTrue(profileDoctorPage.isOnProfileDoctorPage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void RegTestWithoutLastName() {

        try {
            signUpHCPPage
                    .openHCPRegPage()
                    .fillFirstNameField("Piter")
                    .fillLastNameField("222")
                    .fillPasswordField("111111")
                    .fillEmailField("us000998@genefo.com")
                    .clickOnCheckBox18()
                    .clickOnCheckBoxAgree()
                    .clickOnSignUp();
            assertTrue(signUpHCPPage.alertMessageNotValidLastName());
            assertTrue(signUpHCPPage.isOnSignUpHCPPage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   @Test
    public void RegTestWithoutFirstName() {

        try {
            signUpHCPPage
                    .openHCPRegPage()
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .fillEmailField("us000998@genefo.com")
                    .clickOnCheckBox18()
                    .clickOnCheckBoxAgree()
                    .clickOnSignUp();
            assertTrue(signUpHCPPage.alertMessageNotValidFirstName());
            assertTrue(signUpHCPPage.isOnSignUpHCPPage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void RegTestWithoutPassword() {

        try {
            signUpHCPPage
                    .openHCPRegPage()
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("")
                    .fillEmailField("us000998@genefo.com")
                    .clickOnCheckBox18()
                    .clickOnCheckBoxAgree()
                    .clickOnSignUp();
            assertTrue(signUpHCPPage.alertMessageNotValidPassword());
            assertTrue(signUpHCPPage.isOnSignUpHCPPage());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void RegTestWithoutEmail() {

        try {
            signUpHCPPage
                    .openHCPRegPage()
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .fillEmailField("")
                    .clickOnCheckBox18()
                    .clickOnCheckBoxAgree()
                    .clickOnSignUp();
            assertTrue(signUpHCPPage.alertMessageNotValidEmail());
            assertTrue(signUpHCPPage.isOnSignUpHCPPage());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void RegTestWithoutCheckBox18() {

        try {
            signUpHCPPage
                    .openHCPRegPage()
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .fillEmailField("us000998@genefo.com")
                    .clickOnCheckBoxAgree()
                    .clickOnSignUp();
            assertTrue(signUpHCPPage.alertMessageNonChecked18());
            assertTrue(signUpHCPPage.isOnSignUpHCPPage());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void RegTestWithoutCheckBoxTerms() {

        try {
            signUpHCPPage
                    .openHCPRegPage()
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .fillEmailField("us000998@genefo.com")
                    .clickOnCheckBox18()
                    .clickOnSignUp();
            assertTrue(signUpHCPPage.alertMessageNonCheckedTerms());
            assertTrue(signUpHCPPage.isOnSignUpHCPPage());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //EmailField
    //1
    @Test
    public void RegTestWithoutAtInEmailField() {

        try {
            signUpHCPPage
                    .openHCPRegPage()
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .fillEmailField("us000998genefo.com")
                    .clickOnCheckBox18()
                    .clickOnCheckBoxAgree()
                    .clickOnSignUp();
            assertTrue(signUpHCPPage.alertMessageNotValidEmail());
            assertTrue(signUpHCPPage.isOnSignUpHCPPage());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //2
    @Test
    public void RegTestWithSpecialCharactersInEmailField() {

        try {
            signUpHCPPage
                    .openHCPRegPage()
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .fillEmailField("!)*@#$%^&*.com")
                    .clickOnCheckBox18()
                    .clickOnCheckBoxAgree()
                    .clickOnSignUp();
            assertTrue(signUpHCPPage.alertMessageNotValidEmail());
            assertTrue(signUpHCPPage.isOnSignUpHCPPage());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //3
    @Test
    public void RegTestWithoutLocalPartInEmailField() {

        try {
            signUpHCPPage
                    .openHCPRegPage()
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .fillEmailField("@genefo.com")
                    .clickOnCheckBox18()
                    .clickOnCheckBoxAgree()
                    .clickOnSignUp();
            assertTrue(signUpHCPPage.alertMessageNotValidEmail());
            assertTrue(signUpHCPPage.isOnSignUpHCPPage());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //4
    @Test
    public void RegTestWithoutDomainPartInEmailField() {

        try {
            signUpHCPPage
                    .openHCPRegPage()
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .fillEmailField("us000998@")
                    .clickOnCheckBox18()
                    .clickOnCheckBoxAgree()
                    .clickOnSignUp();
            assertTrue(signUpHCPPage.alertMessageNotValidEmail());
            assertTrue(signUpHCPPage.isOnSignUpHCPPage());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //5

    @Test
    public void RegTestWithConsecutiveDotsInEmailField() {

        try {
            signUpHCPPage
                    .openHCPRegPage()
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .fillEmailField("us000998@genefo..com")
                    .clickOnCheckBox18()
                    .clickOnCheckBoxAgree()
                    .clickOnSignUp();
            assertTrue(signUpHCPPage.alertMessageNotValidEmail());
            assertTrue(signUpHCPPage.isOnSignUpHCPPage());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //6
    @Test
    public void RegTestWithDotInTheBeginningLocalPartEmailField() {

        try {
            signUpHCPPage
                    .openHCPRegPage()
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .fillEmailField(".us000998@genefo.com")
                    .clickOnCheckBox18()
                    .clickOnCheckBoxAgree()
                    .clickOnSignUp();
            assertTrue(signUpHCPPage.alertMessageNotValidEmail());
            assertTrue(signUpHCPPage.isOnSignUpHCPPage());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //7
    @Test
    public void RegTestWithDotInTheBeginningDomainPartEmailField() {

        try {
            signUpHCPPage
                    .openHCPRegPage()
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .fillEmailField("us000998@.genefo.com")
                    .clickOnCheckBox18()
                    .clickOnCheckBoxAgree()
                    .clickOnSignUp();
            assertTrue(signUpHCPPage.alertMessageNotValidEmail());
            assertTrue(signUpHCPPage.isOnSignUpHCPPage());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //8
    @Test
    public void RegTestWithEmailContains256Symbols() {

        try {
            signUpHCPPage
                    .openHCPRegPage()
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .fillEmailField("kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk" +
                            "kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk" +
                            "kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk" +
                            "kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk" +
                            "kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk@.genefo.com")
                    .clickOnCheckBox18()
                    .clickOnCheckBoxAgree()
                    .clickOnSignUp();
            assertTrue(signUpHCPPage.alertMessageNotValidEmail());
            assertTrue(signUpHCPPage.isOnSignUpHCPPage());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //PasswordField
    //1
    @Test
    public void RegTestWithPasswordContains5Symbols() {

        try {
            signUpHCPPage
                    .openHCPRegPage()
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("11111")
                    .fillEmailField("us000998@genefo.com")
                    .clickOnCheckBox18()
                    .clickOnCheckBoxAgree()
                    .clickOnSignUp();
            assertTrue(signUpHCPPage.alertMessageNotValidPassword());
            assertTrue(signUpHCPPage.isOnSignUpHCPPage());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //2
    @Test
    public void RegTestWithPasswordContains13Symbols() {

        try {
            signUpHCPPage
                    .openHCPRegPage()
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("1111111111111")
                    .fillEmailField("us000998@genefo.com")
                    .clickOnCheckBox18()
                    .clickOnCheckBoxAgree()
                    .clickOnSignUp();
            assertTrue(signUpHCPPage.alertMessageNotValidPassword());
            assertTrue(signUpHCPPage.isOnSignUpHCPPage());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //FirstName
    //1
    @Test
    public void RegTestWithFirstNameContainsSpecialCharacters() {

        try {
            signUpHCPPage
                    .openHCPRegPage()
                    .fillFirstNameField("@#$%^&*(")
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .fillEmailField("us000998@genefo.com")
                    .clickOnCheckBox18()
                    .clickOnCheckBoxAgree()
                    .clickOnSignUp();
            assertTrue(signUpHCPPage.alertMessageNotValidFirstName());
            assertTrue(signUpHCPPage.isOnSignUpHCPPage());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //2
    @Test
    public void RegTestWithFirstNameContainsDigits() {

        try {
            signUpHCPPage
                    .openHCPRegPage()
                    .fillFirstNameField("55Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .fillEmailField("us000998@genefo.com")
                    .clickOnCheckBox18()
                    .clickOnCheckBoxAgree()
                    .clickOnSignUp();
            assertTrue(signUpHCPPage.alertMessageNotValidFirstName());
            assertTrue(signUpHCPPage.isOnSignUpHCPPage());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //3
    @Test
    public void RegTestWithFirstNameContainsUnderscore() {

        try {
            signUpHCPPage
                    .openHCPRegPage()
                    .fillFirstNameField("Piter_Pit")
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .fillEmailField("us000998@genefo.com")
                    .clickOnCheckBox18()
                    .clickOnCheckBoxAgree()
                    .clickOnSignUp();
            assertTrue(signUpHCPPage.alertMessageNotValidFirstName());
            assertTrue(signUpHCPPage.isOnSignUpHCPPage());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //4
    @Test
    public void RegTestWithFirstNameContains26Symbols() {

        try {
            signUpHCPPage
                    .openHCPRegPage()
                    .fillFirstNameField("PiterPiterPiterPiterPiterr")
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .fillEmailField("us000998@genefo.com")
                    .clickOnCheckBox18()
                    .clickOnCheckBoxAgree()
                    .clickOnSignUp();
            assertTrue(signUpHCPPage.alertMessageNotValidFirstName());
            assertTrue(signUpHCPPage.isOnSignUpHCPPage());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //LastName
    //1
    @Test
    public void RegTestWithLastNameContainsSpecialCharacters() {

        try {
            signUpHCPPage
                    .openHCPRegPage()
                    .fillFirstNameField("Piter")
                    .fillLastNameField("@#$%^&*(")
                    .fillPasswordField("111111")
                    .fillEmailField("us000998@genefo.com")
                    .clickOnCheckBox18()
                    .clickOnCheckBoxAgree()
                    .clickOnSignUp();
            assertTrue(signUpHCPPage.alertMessageNotValidLastName());
            assertTrue(signUpHCPPage.isOnSignUpHCPPage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //2
    @Test
    public void RegTestWithLastNameContainsDigits() {

        try {
            signUpHCPPage
                    .openHCPRegPage()
                    .fillFirstNameField("Piter")
                    .fillLastNameField("55Pen")
                    .fillPasswordField("111111")
                    .fillEmailField("us000998@genefo.com")
                    .clickOnCheckBox18()
                    .clickOnCheckBoxAgree()
                    .clickOnSignUp();
            assertTrue(signUpHCPPage.alertMessageNotValidLastName());
            assertTrue(signUpHCPPage.isOnSignUpHCPPage());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //3
    @Test
    public void RegTestWithLastNameContainsUnderscore() {

        try {
            signUpHCPPage
                    .openHCPRegPage()
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen_Pen")
                    .fillPasswordField("111111")
                    .fillEmailField("us000998@genefo.com")
                    .clickOnCheckBox18()
                    .clickOnCheckBoxAgree()
                    .clickOnSignUp();
            assertTrue(signUpHCPPage.alertMessageNotValidLastName());
            assertTrue(signUpHCPPage.isOnSignUpHCPPage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //4
    @Test
    public void RegTestWithLastNameContains26Symbols() {

        try {
            signUpHCPPage
                    .openHCPRegPage()
                    .fillFirstNameField("Piter")
                    .fillLastNameField("PenPenPenPenPenPenPenPennn")
                    .fillPasswordField("111111")
                    .fillEmailField("us000998@genefo.com")
                    .clickOnCheckBox18()
                    .clickOnCheckBoxAgree()
                    .clickOnSignUp();
            assertTrue(signUpHCPPage.alertMessageNotValidLastName());
            assertTrue(signUpHCPPage.isOnSignUpHCPPage());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //Positive Tests emails variations

    //1
    @Test
    public void RegTestEmailLocalPartBeginsNumber() {

        try {
            signUpHCPPage
                    .openHCPRegPage()
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .fillEmailField("99piter@genefo.com")
                    .clickOnCheckBox18()
                    .clickOnCheckBoxAgree()
                    .clickOnSignUp();
            profileDoctorPage.waitUntilProfileDoctorPageIsLoaded();
            assertTrue(profileDoctorPage.isOnProfileDoctorPage());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //2
    @Test
    public void RegTestEmailDomainNameBeginsNumber() {

        try {
            signUpHCPPage
                    .openHCPRegPage()
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .fillEmailField("piter@77genefo.com")
                    .clickOnCheckBox18()
                    .clickOnCheckBoxAgree()
                    .clickOnSignUp();
            profileDoctorPage.waitUntilProfileDoctorPageIsLoaded();
            assertTrue(profileDoctorPage.isOnProfileDoctorPage());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //3

    @Test
    public void RegTestEmailWithDotsLocalAndDomain() {

        try {
            signUpHCPPage
                    .openHCPRegPage()
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .fillEmailField("piter.pen.21@77.genefo.com")
                    .clickOnCheckBox18()
                    .clickOnCheckBoxAgree()
                    .clickOnSignUp();
            profileDoctorPage.waitUntilProfileDoctorPageIsLoaded();
            assertTrue(profileDoctorPage.isOnProfileDoctorPage());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //4

    @Test
    public void RegTestEmailWithHypenInLocalPart() {

        try {
            signUpHCPPage
                    .openHCPRegPage()
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .fillEmailField("piter-pen@genefo.com")
                    .clickOnCheckBox18()
                    .clickOnCheckBoxAgree()
                    .clickOnSignUp();
            profileDoctorPage.waitUntilProfileDoctorPageIsLoaded();
            assertTrue(profileDoctorPage.isOnProfileDoctorPage());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //5
    @Test
    public void RegTestEmailWithHypenInDomainPart() {

        try {
            signUpHCPPage
                    .openHCPRegPage()
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .fillEmailField("piter@ru-genefo.com")
                    .clickOnCheckBox18()
                    .clickOnCheckBoxAgree()
                    .clickOnSignUp();
            profileDoctorPage.waitUntilProfileDoctorPageIsLoaded();
            assertTrue(profileDoctorPage.isOnProfileDoctorPage());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //6

    @Test
    public void RegTestEmailWithUnderscoreInLocalPart() {

        try {
            signUpHCPPage
                    .openHCPRegPage()
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .fillEmailField("piter_pen@genefo.com")
                    .clickOnCheckBox18()
                    .clickOnCheckBoxAgree()
                    .clickOnSignUp();
            profileDoctorPage.waitUntilProfileDoctorPageIsLoaded();
            assertTrue(profileDoctorPage.isOnProfileDoctorPage());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Positive test for password

    //1
    @Test
    public void RegTestPassword6Symbols() {

        try {
            signUpHCPPage
                    .openHCPRegPage()
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("abs123")
                    .fillEmailField("us000998@genefo.com")
                    .clickOnCheckBox18()
                    .clickOnCheckBoxAgree()
                    .clickOnSignUp();
            profileDoctorPage.waitUntilProfileDoctorPageIsLoaded();
            assertTrue(profileDoctorPage.isOnProfileDoctorPage());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //2
    @Test
    public void RegTestPassword8Symbols() {

        try {
            signUpHCPPage
                    .openHCPRegPage()
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("absd1234")
                    .fillEmailField("us000998@genefo.com")
                    .clickOnCheckBox18()
                    .clickOnCheckBoxAgree()
                    .clickOnSignUp();
            profileDoctorPage.waitUntilProfileDoctorPageIsLoaded();
            assertTrue(profileDoctorPage.isOnProfileDoctorPage());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //3
    @Test
    public void RegTestPassword12Symbols() {

        try {
            signUpHCPPage
                    .openHCPRegPage()
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("absdef123456")
                    .fillEmailField("us000998@genefo.com")
                    .clickOnCheckBox18()
                    .clickOnCheckBoxAgree()
                    .clickOnSignUp();
            profileDoctorPage.waitUntilProfileDoctorPageIsLoaded();
            assertTrue(profileDoctorPage.isOnProfileDoctorPage());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Positive Tests for FirstNameField

    //1
    @Test
    public void RegTestFirstName25Symbols() {

        try {
            signUpHCPPage
                    .openHCPRegPage()
                    .fillFirstNameField("PiterPiterPiter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .fillEmailField("us000998@genefo.com")
                    .clickOnCheckBox18()
                    .clickOnCheckBoxAgree()
                    .clickOnSignUp();
            profileDoctorPage.waitUntilProfileDoctorPageIsLoaded();
            assertTrue(profileDoctorPage.isOnProfileDoctorPage());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void RegTestFirstName1Symbol() {

        try {
            signUpHCPPage
                    .openHCPRegPage()
                    .fillFirstNameField("P")
                    .fillLastNameField("Pen")
                    .fillPasswordField("absdef123456")
                    .fillEmailField("us000998@genefo.com")
                    .clickOnCheckBox18()
                    .clickOnCheckBoxAgree()
                    .clickOnSignUp();
            profileDoctorPage.waitUntilProfileDoctorPageIsLoaded();
            assertTrue(profileDoctorPage.isOnProfileDoctorPage());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //Positive Tests for LastNameField

    //1
    @Test
    public void RegTestLastName25Symbols() {

        try {
            signUpHCPPage
                    .openHCPRegPage()
                    .fillFirstNameField("Piter")
                    .fillLastNameField("PiterPiterPiterPiterPiter")
                    .fillPasswordField("111111")
                    .fillEmailField("us000998@genefo.com")
                    .clickOnCheckBox18()
                    .clickOnCheckBoxAgree()
                    .clickOnSignUp();
            profileDoctorPage.waitUntilProfileDoctorPageIsLoaded();
            assertTrue(profileDoctorPage.isOnProfileDoctorPage());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //2
    @Test
    public void RegTestLastName1Symbol() {

        try {
            signUpHCPPage
                    .openHCPRegPage()
                    .fillFirstNameField("Pitel")
                    .fillLastNameField("P")
                    .fillPasswordField("absdef123456")
                    .fillEmailField("us000998@genefo.com")
                    .clickOnCheckBox18()
                    .clickOnCheckBoxAgree()
                    .clickOnSignUp();
            profileDoctorPage.waitUntilProfileDoctorPageIsLoaded();
            assertTrue(profileDoctorPage.isOnProfileDoctorPage());

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
