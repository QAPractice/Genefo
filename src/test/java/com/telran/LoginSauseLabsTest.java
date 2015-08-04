package com.telran;

import com.saucelabs.common.SauceOnDemandAuthentication;
import com.saucelabs.common.SauceOnDemandSessionIdProvider;
import com.saucelabs.testng.SauceOnDemandAuthenticationProvider;
import com.saucelabs.testng.SauceOnDemandTestListener;
import com.telran.pages.LoginPage;
import com.telran.pages.MainPage;
import com.telran.pages.ResetYourPasswordPage;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.openqa.selenium.support.PageFactory;
import org.testng.AssertJUnit;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.net.URL;


/**
 * Simple {@link RemoteWebDriver} test that demonstrates how to run your Selenium tests with <a href="http://saucelabs.com/ondemand">Sauce OnDemand</a>.
 * <p>
 * This test also includes the <a href="https://github.com/saucelabs/sauce-java/tree/master/testng">Sauce TestNG</a> helper classes, which will use the Sauce REST API to mark the Sauce Job as passed/failed.
 * <p>
 * In order to use the {@link com.saucelabs.testng.SauceOnDemandTestListener}, the test must implement the {@link com.saucelabs.common.SauceOnDemandSessionIdProvider} and {@link com.saucelabs.testng.SauceOnDemandAuthenticationProvider} interfaces.
 *
 * @author Ross Rowe
 */
@Listeners({SauceOnDemandTestListener.class})
public class LoginSauseLabsTest implements SauceOnDemandSessionIdProvider, SauceOnDemandAuthenticationProvider {
    public LoginPage loginPage;
    public ResetYourPasswordPage resetYourPasswordPage;
    public MainPage mainPage;
    // private boolean acceptNextAlert = true;
    public SauceOnDemandAuthentication authentication;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();
    private WebDriver driver;

    @BeforeClass
    public void setup() {
        loginPage = PageFactory.initElements(driver, LoginPage.class);
    }

    /**
     * Creates a new {@link RemoteWebDriver} instance to be used to run WebDriver tests using Sauce.
     *
     * @param username       the Sauce username
     * @param key            the Sauce access key
     * @param os             the operating system to be used
     * @param browser        the name of the browser to be used
     * @param browserVersion the version of the browser to be used
     * @param method         the test method being executed
     * @throws Exception thrown if any errors occur in the creation of the WebDriver instance
     */
    @Parameters({"username", "key", "os", "browser", "browserVersion"})
    @BeforeMethod
    public void setUp(@Optional("ivolf") String username,
                      @Optional("90e3bb89-c21d-4885-85cf-f25494db06ff") String key,
                      @Optional("Windows 8.1") String os,
                      @Optional("internet explorer") String browser,
                      @Optional("11") String browserVersion,
                      Method method) throws Exception {

        if (StringUtils.isNotEmpty(username) && StringUtils.isNotEmpty(key)) {
            authentication = new SauceOnDemandAuthentication(username, key);
        } else {
            authentication = new SauceOnDemandAuthentication("ivolf", "90e3bb89-c21d-4885-85cf-f25494db06ff");
        }

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName(browser);
        capabilities.setCapability("version", browserVersion);
        capabilities.setCapability("platform", os);
        capabilities.setCapability("name", method.getName());
        this.driver = new RemoteWebDriver(
                new URL("http://" + authentication.getUsername() + ":" + authentication.getAccessKey() + "@ondemand.saucelabs.com:80/wd/hub"),
                capabilities);
    }


    /**
     * {@inheritDoc}
     *
     * @return
     */
    @Override
    public String getSessionId() {
        SessionId sessionId = ((RemoteWebDriver) driver).getSessionId();
        return (sessionId == null) ? null : sessionId.toString();
    }


    //@Test(groups = {"smoke", "positive"})
    public void LoginSuccess() {

        try {
            loginPage
                    .opennLoginPage(driver)
                    .fillEmailField("jakoff+22@gmail.com")
                    .fillPasswordField("111111")
                    .clickOnLogin();
            AssertJUnit.assertTrue(mainPage.isOnMainPage());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // @Test(groups = {"smoke", "negative"})
    public void LoginWithoutAtInEmailField() {

        try {
            loginPage
                    .opennLoginPage(driver)
                    .fillEmailField("osh_il+4yahoo.com")
                    .fillPasswordField("111111")
                    .clickOnLogin();
            AssertJUnit.assertTrue(loginPage.alertMessageInvalidEmail());
            AssertJUnit.assertTrue(loginPage.isOnLoginPage());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //  @Test(groups = {"smoke", "negative"})
    public void LoginWithPasswordContains1Symbol() {

        try {
            loginPage
                    .opennLoginPage(driver)
                    .fillEmailField("osh_il+4@yahoo.com")
                    .fillPasswordField("1")
                    .clickOnLogin();
            AssertJUnit.assertTrue(loginPage.alertMessageInvalidPassword());
            AssertJUnit.assertTrue(loginPage.isOnLoginPage());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // @Test(groups = {"smoke", "positive"})
    public void ForgotPassword() {

        try {
            loginPage
                    .opennLoginPage(driver)
                    .clickOnForgotPasswordLink();
            AssertJUnit.assertTrue(resetYourPasswordPage.isOnResetPage());
            resetYourPasswordPage.fillEmailField("osh_il+4@yahoo.com");
            resetYourPasswordPage.clickOnEmailMe();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // @Test(groups = {"smoke", "negative"})
    public void LoginWithEmptyFields() {

        try {
            loginPage
                    .opennLoginPage(driver)
                    .fillEmailField("")
                    .fillPasswordField("")
                    .clickOnLogin();
            AssertJUnit.assertTrue(loginPage.alertMessageInvalidEmail());
            AssertJUnit.assertTrue(loginPage.alertMessageInvalidPassword());
            AssertJUnit.assertTrue(loginPage.isOnLoginPage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * {@inheritDoc}
     *
     * @return
     */
    @Override
    public SauceOnDemandAuthentication getAuthentication() {
        return authentication;
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
