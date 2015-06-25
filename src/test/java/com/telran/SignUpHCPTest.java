package com.telran;

import com.telran.pages.HomePage;
import com.telran.pages.MainPage;
import com.telran.pages.ProfileDoctorPage;
import com.telran.pages.SignUpHCPPage;
import com.telran.util.TestUtils;
import com.telran.util.WEB_DRIVER;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
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
    public SignUpHCPPage signUpHCPPage;
    public ProfileDoctorPage profileDoctorPage;
    private boolean acceptNextAlert = true;
    //private String email = "one" + randomAlphabetic(5) + "@usgenefo.com";
    private static String email1 = "one" + randomAlphabetic(5) + "usgenefo.com";
    private static String email2 = "one" + randomAlphabetic(5) + "@us.genefo.com";
    private static String email3 = "o_ne" + randomAlphabetic(5) + "@usgenefo.com";
    private static String email4 = "one" + randomAlphabetic(5) + "@us-genefo.com";

    @BeforeClass
    public void setup() {
        this.driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 5);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        signUpHCPPage = PageFactory.initElements(driver, SignUpHCPPage.class);
        profileDoctorPage = PageFactory.initElements(driver, ProfileDoctorPage.class);
       }

    @BeforeMethod
    public void beforemethodsetup() {
        signUpHCPPage.openHCPRegPage();
    }

    @Test(groups = {"smoke", "positive"})
    public void RegTestSuccess() {
        String email = "one" + randomAlphabetic(5) + "@usgenefo.com";
        try {
            signUpHCPPage
                    .fillEmailField(email)
                    .fillFirstNameField("gggg")
                    .fillLastNameField("gggg")
                    .fillPasswordField("111111")
                    .clickOnCheckBox18()
                    .clickOnCheckBoxAgree()
                    .clickOnSignUp();
            profileDoctorPage.waitUntilProfileDoctorPageIsLoaded();
            assertTrue("Profile HCP Page doesn't open", profileDoctorPage.isOnProfileDoctorPage());
            profileDoctorPage.logOut();
            sleep();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"smoke", "negative"})
    public void RegTestWithoutLastName() {
        String email = "one" + randomAlphabetic(5) + "@usgenefo.com";
        try {
            signUpHCPPage
                    .openHCPRegPage()
                    .fillEmailField(email)
                    .fillFirstNameField("Piter")
                    .fillLastNameField("")
                    .fillPasswordField("111111")
                    .clickOnCheckBox18()
                    .clickOnCheckBoxAgree()
                    .clickOnSignUp();
            assertTrue("The Last Name is valid",signUpHCPPage.alertMessageNotValidLastName());
            assertTrue("The current page is changed",signUpHCPPage.isOnSignUpHCPPage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"smoke", "negative"})
    public void RegTestWithoutFirstName() {
        String email = "one" + randomAlphabetic(5) + "@usgenefo.com";
        try {
            signUpHCPPage
                    .openHCPRegPage()
                    .fillEmailField(email)
                    .fillFirstNameField("")
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .clickOnCheckBox18()
                    .clickOnCheckBoxAgree()
                    .clickOnSignUp();
            sleep();
            assertTrue("The First Name is valid",signUpHCPPage.alertMessageNotValidFirstName());
            assertTrue("The current page is changed",signUpHCPPage.isOnSignUpHCPPage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"smoke", "negative"})
    public void RegTestWithoutPassword() {
        String email = "one" + randomAlphabetic(5) + "@usgenefo.com";
        try {
            signUpHCPPage
                    .openHCPRegPage()
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("")
                    .fillEmailField(email)
                    .clickOnCheckBox18()
                    .clickOnCheckBoxAgree()
                    .clickOnSignUp();
            assertTrue("The Password is valid",signUpHCPPage.alertMessageNotValidPassword());
            assertTrue("The current page is changed",signUpHCPPage.isOnSignUpHCPPage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"smoke", "negative"})
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
            assertTrue("The Email is valid",signUpHCPPage.alertMessageNotValidEmail());
            assertTrue("The current page is changed",signUpHCPPage.isOnSignUpHCPPage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"smoke", "negative"})
    public void RegTestWithoutCheckBox18() {
        String email = "one" + randomAlphabetic(5) + "@usgenefo.com";
        try {
            signUpHCPPage
                    .openHCPRegPage()
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .fillEmailField(email)
                    .clickOnCheckBoxAgree()
                    .clickOnSignUp();
            assertTrue("The age verification has passed",signUpHCPPage.alertMessageNonChecked18());
            assertTrue("The current page is changed",signUpHCPPage.isOnSignUpHCPPage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"smoke", "negative"})
    public void RegTestWithoutCheckBoxTerms() {
        String email = "one" + randomAlphabetic(5) + "@usgenefo.com";
        try {
             signUpHCPPage
                    .openHCPRegPage()
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .fillEmailField(email)
                    .clickOnCheckBox18()
                    .clickOnSignUp();
            assertTrue("The terms verification has passed",signUpHCPPage.alertMessageNonCheckedTerms());
            assertTrue("The current page is changed",signUpHCPPage.isOnSignUpHCPPage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //EmailField
    //1
    @Test(groups = {"smoke", "negative"})
    public void RegTestWithoutAtInEmailField() {
        try {
            signUpHCPPage
                    .openHCPRegPage()
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .fillEmailField(email1)
                    .clickOnCheckBox18()
                    .clickOnCheckBoxAgree()
                    .clickOnSignUp();
            assertTrue("The Email is valid",signUpHCPPage.alertMessageNotValidEmail());
            assertTrue("The current page is changed",signUpHCPPage.isOnSignUpHCPPage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //2
    @Test(groups = {"negative"})
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
            assertTrue("The Email is valid",signUpHCPPage.alertMessageNotValidEmail());
            assertTrue("The current page is changed",signUpHCPPage.isOnSignUpHCPPage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //3
    @Test(groups = {"negative"})
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
            assertTrue("The Email is valid",signUpHCPPage.alertMessageNotValidEmail());
            assertTrue("The current page is changed",signUpHCPPage.isOnSignUpHCPPage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //4
    @Test(groups = {"negative"})
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
            assertTrue("The Email is valid",signUpHCPPage.alertMessageNotValidEmail());
            assertTrue("The current page is changed",signUpHCPPage.isOnSignUpHCPPage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //5

    @Test(groups = {"negative"})
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
            assertTrue("The Email is valid",signUpHCPPage.alertMessageNotValidEmail());
            assertTrue("The current page is changed",signUpHCPPage.isOnSignUpHCPPage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //6
    @Test(groups = {"negative"})
    public void RegTestWithDotInTheBeginingLocalPartEmailField() {
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
            assertTrue("The Email is valid",signUpHCPPage.alertMessageNotValidEmail());
            assertTrue("The current page is changed",signUpHCPPage.isOnSignUpHCPPage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //7
    @Test(groups = {"negative"})
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
            assertTrue("The Email is valid",signUpHCPPage.alertMessageNotValidEmail());
            assertTrue("The current page is changed",signUpHCPPage.isOnSignUpHCPPage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //8
    @Test(groups = {"negative"})
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
                            "kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk@usgenefo.com")
                    .clickOnCheckBox18()
                    .clickOnCheckBoxAgree()
                    .clickOnSignUp();
            assertTrue("The Email is valid",signUpHCPPage.alertMessageNotValidEmail());
            assertTrue("The current page is changed",signUpHCPPage.isOnSignUpHCPPage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //PasswordField
    //1
    @Test(groups = {"negative"})
    public void RegTestWithPasswordContains5Symbols() {
        String email = "one" + randomAlphabetic(5) + "@usgenefo.com";
        try {
            signUpHCPPage
                    .openHCPRegPage()
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("11111")
                    .fillEmailField(email)
                    .clickOnCheckBox18()
                    .clickOnCheckBoxAgree()
                    .clickOnSignUp();
            assertTrue("The Password is valid",signUpHCPPage.alertMessageNotValidPassword());
            assertTrue("The current page is changed",signUpHCPPage.isOnSignUpHCPPage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //2
    @Test(groups = {"negative"})
    public void RegTestWithPasswordContains13Symbols() {
        String email = "one" + randomAlphabetic(5) + "@usgenefo.com";
        try {
            signUpHCPPage
                    .openHCPRegPage()
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("1111111111111")
                    .fillEmailField(email)
                    .clickOnCheckBox18()
                    .clickOnCheckBoxAgree()
                    .clickOnSignUp();
            assertTrue("The Password is valid",signUpHCPPage.alertMessageNotValidPassword());
            assertTrue("The current page is changed",signUpHCPPage.isOnSignUpHCPPage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //FirstName
    //1
    @Test(groups = {"negative"})
    public void RegTestWithFirstNameContainsSpecialCharacters() {
        String email = "one" + randomAlphabetic(5) + "@usgenefo.com";
        try {
            signUpHCPPage
                    .openHCPRegPage()
                    .fillFirstNameField("@#$%^&*(")
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .fillEmailField(email)
                    .clickOnCheckBox18()
                    .clickOnCheckBoxAgree()
                    .clickOnSignUp();
            assertTrue("The First Name is valid",signUpHCPPage.alertMessageNotValidFirstName());
            assertTrue("The current page is changed",signUpHCPPage.isOnSignUpHCPPage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //2
    @Test(groups = {"negative"})
    public void RegTestWithFirstNameContainsDigits() {
        String email = "one" + randomAlphabetic(5) + "@usgenefo.com";
        try {
            signUpHCPPage
                    .openHCPRegPage()
                    .fillFirstNameField("55Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .fillEmailField(email)
                    .clickOnCheckBox18()
                    .clickOnCheckBoxAgree()
                    .clickOnSignUp();
            assertTrue("The First Name is valid",signUpHCPPage.alertMessageNotValidFirstName());
            assertTrue("The current page is changed",signUpHCPPage.isOnSignUpHCPPage());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //3
    @Test(groups = {"negative"})
    public void RegTestWithFirstNameContainsUnderscore() {
        String email = "one" + randomAlphabetic(5) + "@usgenefo.com";
        try {
            signUpHCPPage
                    .openHCPRegPage()
                    .fillFirstNameField("Piter_Pit")
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .fillEmailField(email)
                    .clickOnCheckBox18()
                    .clickOnCheckBoxAgree()
                    .clickOnSignUp();
            assertTrue("The First Name is valid",signUpHCPPage.alertMessageNotValidFirstName());
            assertTrue("The current page is changed",signUpHCPPage.isOnSignUpHCPPage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //4
    @Test(groups = {"negative"})
    public void RegTestWithFirstNameContains26Symbols() {
        String email = "one" + randomAlphabetic(5) + "@usgenefo.com";
        try {
            signUpHCPPage
                    .openHCPRegPage()
                    .fillFirstNameField("PiterPiterPiterPiterPiterr")
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .fillEmailField(email)
                    .clickOnCheckBox18()
                    .clickOnCheckBoxAgree()
                    .clickOnSignUp();
            assertTrue("The First Name is valid",signUpHCPPage.alertMessageNotValidFirstName());
            assertTrue("The current page is changed",signUpHCPPage.isOnSignUpHCPPage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //LastName
    //1
    @Test(groups = {"negative"})
    public void RegTestWithLastNameContainsSpecialCharacters() {
        String email = "one" + randomAlphabetic(5) + "@usgenefo.com";
        try {
            signUpHCPPage
                    .openHCPRegPage()
                    .fillFirstNameField("Piter")
                    .fillLastNameField("@#$%^&*(")
                    .fillPasswordField("111111")
                    .fillEmailField(email)
                    .clickOnCheckBox18()
                    .clickOnCheckBoxAgree()
                    .clickOnSignUp();
            assertTrue("The Last Name is valid",signUpHCPPage.alertMessageNotValidLastName());
            assertTrue("The current page is changed",signUpHCPPage.isOnSignUpHCPPage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //2
    @Test(groups = {"negative"})
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
            assertTrue("The Last Name is valid",signUpHCPPage.alertMessageNotValidLastName());
            assertTrue("The current page is changed",signUpHCPPage.isOnSignUpHCPPage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //3
    @Test(groups = {"negative"})
    public void RegTestWithLastNameContainsUnderscore() {
        String email = "one" + randomAlphabetic(5) + "@usgenefo.com";
        try {
            signUpHCPPage
                    .openHCPRegPage()
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen_Pen")
                    .fillPasswordField("111111")
                    .fillEmailField(email)
                    .clickOnCheckBox18()
                    .clickOnCheckBoxAgree()
                    .clickOnSignUp();
            assertTrue("The Last Name is valid",signUpHCPPage.alertMessageNotValidLastName());
            assertTrue("The current page is changed",signUpHCPPage.isOnSignUpHCPPage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //4
    @Test(groups = {"negative"})
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
            assertTrue("The Last Name is valid",signUpHCPPage.alertMessageNotValidLastName());
            assertTrue("The current page is changed",signUpHCPPage.isOnSignUpHCPPage());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //Positive Tests emails variations

    //1
    @Test(groups = {"positive"})
    public void RegTestEmailLocalPartBeginsNumber() {
        String email = "one" + randomAlphabetic(5) + "@usgenefo.com";
        try {
             signUpHCPPage
                    .openHCPRegPage()
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .fillEmailField(email)
                    .clickOnCheckBox18()
                    .clickOnCheckBoxAgree()
                    .clickOnSignUp();
            profileDoctorPage.waitUntilProfileDoctorPageIsLoaded();
            assertTrue("Profile HCP Page doesn't open", profileDoctorPage.isOnProfileDoctorPage());
            profileDoctorPage.logOut();
            sleep();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //2
    @Test(groups = {"positive"})
    public void RegTestEmailDomainNameBeginsNumber() {
        String email = "one" + randomAlphabetic(5) + "@usgenefo.com";
        try {
             signUpHCPPage
                    .openHCPRegPage()
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .fillEmailField(email)
                    .clickOnCheckBox18()
                    .clickOnCheckBoxAgree()
                    .clickOnSignUp();
            profileDoctorPage.waitUntilProfileDoctorPageIsLoaded();
            assertTrue("Profile HCP Page doesn't open", profileDoctorPage.isOnProfileDoctorPage());
            profileDoctorPage.logOut();
            sleep();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //3

    @Test(groups = {"positive"})
    public void RegTestEmailWithDotsLocalAndDomain() {
        try {
            signUpHCPPage
                    .openHCPRegPage()
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .fillEmailField(email2)
                    .clickOnCheckBox18()
                    .clickOnCheckBoxAgree()
                    .clickOnSignUp();
            profileDoctorPage.waitUntilProfileDoctorPageIsLoaded();
            assertTrue("Profile HCP Page doesn't open", profileDoctorPage.isOnProfileDoctorPage());
            profileDoctorPage.logOut();
            sleep();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //4

    @Test(groups = {"positive"})
    public void RegTestEmailWithHypenInLocalPart() {
        String email = "one" + randomAlphabetic(5) + "@usgenefo.com";
        try {
            signUpHCPPage
                    .openHCPRegPage()
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .fillEmailField(email)
                    .clickOnCheckBox18()
                    .clickOnCheckBoxAgree()
                    .clickOnSignUp();
            profileDoctorPage.waitUntilProfileDoctorPageIsLoaded();
            assertTrue("Profile HCP Page doesn't open", profileDoctorPage.isOnProfileDoctorPage());
            profileDoctorPage.logOut();
            sleep();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //5
    @Test(groups = {"positive"})
    public void RegTestEmailWithHypenInDomainPart() {
        try {
            signUpHCPPage
                    .openHCPRegPage()
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .fillEmailField(email4)
                    .clickOnCheckBox18()
                    .clickOnCheckBoxAgree()
                    .clickOnSignUp();
            profileDoctorPage.waitUntilProfileDoctorPageIsLoaded();
            assertTrue("Profile HCP Page doesn't open", profileDoctorPage.isOnProfileDoctorPage());
            profileDoctorPage.logOut();
            sleep();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //6

    @Test(groups = {"positive"})
    public void RegTestEmailWithUnderscoreInLocalPart() {
        try {
            signUpHCPPage
                    .openHCPRegPage()
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .fillEmailField(email3)
                    .clickOnCheckBox18()
                    .clickOnCheckBoxAgree()
                    .clickOnSignUp();
            profileDoctorPage.waitUntilProfileDoctorPageIsLoaded();
            assertTrue("Profile HCP Page doesn't open", profileDoctorPage.isOnProfileDoctorPage());
            profileDoctorPage.logOut();
            sleep();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Positive test for password

    //1
    @Test(groups = {"positive"})
    public void RegTestPassword6Symbols() {
        String email = "one" + randomAlphabetic(5) + "@usgenefo.com";
        try {
            signUpHCPPage
                    .openHCPRegPage()
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("abs123")
                    .fillEmailField(email)
                    .clickOnCheckBox18()
                    .clickOnCheckBoxAgree()
                    .clickOnSignUp();
            profileDoctorPage.waitUntilProfileDoctorPageIsLoaded();
            assertTrue("Profile HCP Page doesn't open", profileDoctorPage.isOnProfileDoctorPage());
            profileDoctorPage.logOut();
            sleep();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //2
    @Test(groups = {"positive"})
    public void RegTestPassword8Symbols() {
        String email = "one" + randomAlphabetic(5) + "@usgenefo.com";
        try {
            signUpHCPPage
                    .openHCPRegPage()
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("absd1234")
                    .fillEmailField(email)
                    .clickOnCheckBox18()
                    .clickOnCheckBoxAgree()
                    .clickOnSignUp();
            profileDoctorPage.waitUntilProfileDoctorPageIsLoaded();
            assertTrue("Profile HCP Page doesn't open", profileDoctorPage.isOnProfileDoctorPage());
            profileDoctorPage.logOut();
            sleep();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //3
    @Test(groups = {"positive"})
    public void RegTestPassword12Symbols() {
        String email = "one" + randomAlphabetic(5) + "@usgenefo.com";
        try {
            signUpHCPPage
                    .openHCPRegPage()
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("absdef123456")
                    .fillEmailField(email)
                    .clickOnCheckBox18()
                    .clickOnCheckBoxAgree()
                    .clickOnSignUp();
            profileDoctorPage.waitUntilProfileDoctorPageIsLoaded();
            assertTrue("Profile HCP Page doesn't open", profileDoctorPage.isOnProfileDoctorPage());
            profileDoctorPage.logOut();
            sleep();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Positive Tests for FirstNameField

    //1
    @Test(groups = {"positive"})
    public void RegTestFirstName25Symbols() {
        String email = "one" + randomAlphabetic(5) + "@usgenefo.com";
        try {
            signUpHCPPage
                    .openHCPRegPage()
                    .fillFirstNameField("PiterPiterPiter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .fillEmailField(email)
                    .clickOnCheckBox18()
                    .clickOnCheckBoxAgree()
                    .clickOnSignUp();
            profileDoctorPage.waitUntilProfileDoctorPageIsLoaded();
            assertTrue("Profile HCP Page doesn't open", profileDoctorPage.isOnProfileDoctorPage());
            profileDoctorPage.logOut();
            sleep();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test(groups = {"positive"})
    public void RegTestFirstName1Symbol() {
        String email = "one" + randomAlphabetic(5) + "@usgenefo.com";
        try {
            signUpHCPPage
                    .openHCPRegPage()
                    .fillFirstNameField("P")
                    .fillLastNameField("Pen")
                    .fillPasswordField("absdef123456")
                    .fillEmailField(email)
                    .clickOnCheckBox18()
                    .clickOnCheckBoxAgree()
                    .clickOnSignUp();
            profileDoctorPage.waitUntilProfileDoctorPageIsLoaded();
            assertTrue("Profile HCP Page doesn't open", profileDoctorPage.isOnProfileDoctorPage());
            profileDoctorPage.logOut();
            sleep();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //Positive Tests for LastNameField

    //1
    @Test(groups = {"positive"})
    public void RegTestLastName25Symbols() {
        String email = "one" + randomAlphabetic(5) + "@usgenefo.com";
        try {
             signUpHCPPage
                    .openHCPRegPage()
                    .fillFirstNameField("Piter")
                    .fillLastNameField("PiterPiterPiterPiterPiter")
                    .fillPasswordField("111111")
                    .fillEmailField(email)
                    .clickOnCheckBox18()
                    .clickOnCheckBoxAgree()
                    .clickOnSignUp();
            profileDoctorPage.waitUntilProfileDoctorPageIsLoaded();
            assertTrue("Profile HCP Page doesn't open", profileDoctorPage.isOnProfileDoctorPage());
            profileDoctorPage.logOut();
            sleep();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //2
    @Test(groups = {"positive"})
    public void RegTestLastName1Symbol() {
        String email = "one" + randomAlphabetic(5) + "@usgenefo.com";
        try {
            signUpHCPPage
                    .openHCPRegPage()
                    .fillFirstNameField("Pitel")
                    .fillLastNameField("P")
                    .fillPasswordField("absdef123456")
                    .fillEmailField(email)
                    .clickOnCheckBox18()
                    .clickOnCheckBoxAgree()
                    .clickOnSignUp();
            profileDoctorPage.waitUntilProfileDoctorPageIsLoaded();
            assertTrue("Profile HCP Page doesn't open", profileDoctorPage.isOnProfileDoctorPage());
            profileDoctorPage.logOut();
            sleep();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"positive"})
    public void TermOfServiceClick() {
        try {
            signUpHCPPage
                    .openHCPRegPage()
                    .clickOnTermOfService()
                    .openTOSPage();
            assertTrue("Term Of Service Page doesn't open",signUpHCPPage.isOnTOSage());
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

    private void sleep (){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
