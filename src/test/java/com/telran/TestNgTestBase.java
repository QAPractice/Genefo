package com.telran;

import com.telran.util.PropertyLoader;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Base class for TestNG-based test classes
 */
public class TestNgTestBase {


  protected static Capabilities capabilities;

  public WebDriver driver;
  protected String gridHubUrl;
  protected String baseUrl;


  @BeforeClass(alwaysRun = true)
  public void init() throws IOException {
      // baseUrl = "http://52.10.6.51:8080";
      PropertyConfigurator.configure("log4j.properties");
      DesiredCapabilities dCaps = new DesiredCapabilities();
      dCaps.setJavascriptEnabled(true);
      dCaps.setCapability("takesScreenshot", true);
      // dCaps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "d:/phantomjs.exe");
      driver = new PhantomJSDriver(dCaps);

    baseUrl = PropertyLoader.loadProperty("site.url");
      gridHubUrl = PropertyLoader.loadProperty("grid2.hub");
      // driver = new FirefoxDriver();
      //Capabilities capabilities = PropertyLoader.loadCapabilities();
      //PropertyConfigurator.configure("log4j.properties");
      // driver = WebDriverFactory.getDriver(capabilities);
      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
      // driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
  }

  @AfterClass(alwaysRun = true)
  public void tearDown() {
    if (driver != null) {
      driver.quit();
    }
  }

}
