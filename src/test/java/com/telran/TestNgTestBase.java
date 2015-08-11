package com.telran;

import com.telran.util.PropertyLoader;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import ru.stqa.selenium.factory.WebDriverFactory;

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
    baseUrl = PropertyLoader.loadProperty("site.url");
    // gridHubUrl = PropertyLoader.loadProperty("grid2.hub");

    Capabilities capabilities = PropertyLoader.loadCapabilities();
    PropertyConfigurator.configure("log4j.properties");
    driver = WebDriverFactory.getDriver(capabilities);

    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @AfterClass(alwaysRun = true)
  public void tearDown() {
    if (driver != null) {
      WebDriverFactory.dismissDriver(driver);
    }
  }

}
