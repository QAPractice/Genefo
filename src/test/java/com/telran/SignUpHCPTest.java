package com.telran;

import com.telran.pages.HomePage;
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
    public String emailNickname;// Keeps the part of email before sign @
    public String generatedEmail;
    HomePage homePage;
    SignUpHCPPage signUpHCPPage;
    ProfileDoctorPage profileDoctorPage;
    private boolean acceptNextAlert = true;

    @BeforeClass
    public void setup() {
        //this.driver = TestUtils.chooseDriver(WEB_DRIVER.Chrome);
        //this.driver = TestUtils.chooseDriver(WEB_DRIVER.InternetExplorer);
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

    @BeforeMethod
    public void beforemethodsetup() {
        signUpHCPPage.openHCPRegPage();
//        String email = RandomRegistration.fillRandomFile();

    }

    @Test(groups = {"smoke", "positive"})
    public void RegTestSuccess() {

        try {

            emailNickname = randomAlphabetic(5);
            String email = "one" + emailNickname + "@usgenefo.com";

            signUpHCPPage
                    .fillEmailField(email)
                    .fillFirstNameField("gggg")
                    .fillLastNameField("gggg")
                    .fillPasswordField("111111")
                    .clickOnCheckBox18()
                    .clickOnCheckBoxAgree()
                    .clickOnSignUp();
            profileDoctorPage.waitUntilProfileDoctorPageIsLoaded();
            assertTrue("Profile HCP Page doesn't open",profileDoctorPage.isOnProfileDoctorPage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"smoke", "negative"})
    public void RegTestWithoutLastName() {

        try {
            emailNickname = randomAlphabetic(5);
            String email = "one" + emailNickname + "@usgenefo.com";

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

        try {
            emailNickname = randomAlphabetic(5);
            String email = "one" + emailNickname + "@usgenefo.com";
            signUpHCPPage
                    .openHCPRegPage()
                    .fillEmailField(email)
                    .fillFirstNameField("")
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .clickOnCheckBox18()
                    .clickOnCheckBoxAgree()
                    .clickOnSignUp();
            assertTrue("The First Name is valid",signUpHCPPage.alertMessageNotValidFirstName());
            assertTrue("The current page is changed",signUpHCPPage.isOnSignUpHCPPage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"smoke", "negative"})
    public void RegTestWithoutPassword() {

        try {
            emailNickname = randomAlphabetic(5);

            String email = "one" + emailNickname + "@usgenefo.com";
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

        try {
            emailNickname = randomAlphabetic(5);
            String email = "one" + emailNickname + "@usgenefo.com";
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

        try {
            emailNickname = randomAlphabetic(5);
            String email = "one" + emailNickname + "@usgenefo.com";
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
            emailNickname = randomAlphabetic(5);
            String email = "one" + emailNickname + "usgenefo.com";
            signUpHCPPage
                    .openHCPRegPage()
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .fillEmailField(email)
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

        try {
            emailNickname = randomAlphabetic(5);
            String email = "one" + emailNickname + "@usgenefo.com";
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

        try {
            emailNickname = randomAlphabetic(5);
            String email = "one" + emailNickname + "@usgenefo.com";
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

        try {
            emailNickname = randomAlphabetic(5);
            String email = "one" + emailNickname + "@usgenefo.com";
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

        try {
            emailNickname = randomAlphabetic(5);
            String email = "one" + emailNickname + "@usgenefo.com";
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

        try {
            emailNickname = randomAlphabetic(5);
            String email = "one" + emailNickname + "@usgenefo.com";
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

        try {
            emailNickname = randomAlphabetic(5);
            String email = "one" + emailNickname + "@usgenefo.com";
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

        try {
            emailNickname = randomAlphabetic(5);
            String email = "one" + emailNickname + "@usgenefo.com";
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
            emailNickname = randomAlphabetic(5);
            String email = "one" + emailNickname + "@usgenefo.com";
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

        try {
            emailNickname = randomAlphabetic(5);
            String email = "one" + emailNickname + "@usgenefo.com";
            signUpHCPPage
                    .openHCPRegPage()
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen_Pen")
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

    //4
    @Test(groups = {"negative"})
    public void RegTestWithLastNameContains26Symbols() {

        try {
            emailNickname = randomAlphabetic(5);
            String email = "one" + emailNickname + "@usgenefo.com";
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

        try {
            emailNickname = randomAlphabetic(5);
            String email = "99" + emailNickname + "@usgenefo.com";
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
            assertTrue("Profile HCP Page doesn't open",profileDoctorPage.isOnProfileDoctorPage());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //2
    @Test(groups = {"positive"})
    public void RegTestEmailDomainNameBeginsNumber() {

        try {
            emailNickname = randomAlphabetic(5);
            String email = "one" + emailNickname + "@33usgenefo.com";
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
            assertTrue("Profile HCP Page doesn't open",profileDoctorPage.isOnProfileDoctorPage());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //3

    @Test(groups = {"positive"})
    public void RegTestEmailWithDotsLocalAndDomain() {

        try {
            emailNickname = randomAlphabetic(5);
            String email = "one" + emailNickname + "@22.usgenefo.com";
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
            assertTrue("Profile HCP Page doesn't open",profileDoctorPage.isOnProfileDoctorPage());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //4

    @Test(groups = {"positive"})
    public void RegTestEmailWithHypenInLocalPart() {

        try {
            emailNickname = randomAlphabetic(5);
            String email = "one-two" + emailNickname + "@usgenefo.com";
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
            assertTrue("Profile HCP Page doesn't open",profileDoctorPage.isOnProfileDoctorPage());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //5
    @Test(groups = {"positive"})
    public void RegTestEmailWithHypenInDomainPart() {

        try {
            emailNickname = randomAlphabetic(5);
            String email = "one" + emailNickname + "@us-genefo.com";
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
            assertTrue("Profile HCP Page doesn't open",profileDoctorPage.isOnProfileDoctorPage());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //6

    @Test(groups = {"positive"})
    public void RegTestEmailWithUnderscoreInLocalPart() {

        try {
            emailNickname = randomAlphabetic(5);
            String email = "one_pem" + emailNickname + "@usgenefo.com";
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
            assertTrue("Profile HCP Page doesn't open",profileDoctorPage.isOnProfileDoctorPage());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Positive test for password

    //1
    @Test(groups = {"positive"})
    public void RegTestPassword6Symbols() {

        try {
            emailNickname = randomAlphabetic(5);
            String email = "one" + emailNickname + "@usgenefo.com";
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
            assertTrue("Profile HCP Page doesn't open",profileDoctorPage.isOnProfileDoctorPage());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //2
    @Test(groups = {"positive"})
    public void RegTestPassword8Symbols() {

        try {
            emailNickname = randomAlphabetic(5);
            String email = "one" + emailNickname + "@usgenefo.com";
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
            assertTrue("Profile HCP Page doesn't open",profileDoctorPage.isOnProfileDoctorPage());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //3
    @Test(groups = {"positive"})
    public void RegTestPassword12Symbols() {

        try {
            emailNickname = randomAlphabetic(5);
            String email = "one" + emailNickname + "@usgenefo.com";
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
            assertTrue("Profile HCP Page doesn't open",profileDoctorPage.isOnProfileDoctorPage());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Positive Tests for FirstNameField

    //1
    @Test(groups = {"positive"})
    public void RegTestFirstName25Symbols() {

        try {
            emailNickname = randomAlphabetic(5);
            String email = "one" + emailNickname + "@usgenefo.com";
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
            assertTrue("Profile HCP Page doesn't open",profileDoctorPage.isOnProfileDoctorPage());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test(groups = {"positive"})
    public void RegTestFirstName1Symbol() {

        try {
            emailNickname = randomAlphabetic(5);
            String email = "one" + emailNickname + "@usgenefo.com";
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
            assertTrue("Profile HCP Page doesn't open",profileDoctorPage.isOnProfileDoctorPage());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //Positive Tests for LastNameField

    //1
    @Test(groups = {"positive"})
    public void RegTestLastName25Symbols() {

        try {
            emailNickname = randomAlphabetic(5);
            String email = "one" + emailNickname + "@usgenefo.com";
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
            assertTrue("Profile HCP Page doesn't open",profileDoctorPage.isOnProfileDoctorPage());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //2
    @Test(groups = {"positive"})
    public void RegTestLastName1Symbol() {

        try {
            emailNickname = randomAlphabetic(5);
            String email = "one" + emailNickname + "@usgenefo.com";
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
            assertTrue("Profile HCP Page doesn't open",profileDoctorPage.isOnProfileDoctorPage());

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
}
