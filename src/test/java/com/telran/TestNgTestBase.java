package com.telran;

import java.io.IOException;

import com.telran.util.TestUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Capabilities;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import ru.stqa.selenium.factory.WebDriverFactory;
import ru.stqa.selenium.factory.WebDriverFactoryMode;

import com.telran.util.PropertyLoader;

/**
 * Base class for TestNG-based test classes
 */
public class TestNgTestBase {

  //  protected static String gridHubUrl;
//  protected static String baseUrl;
  protected static Capabilities capabilities;

  protected WebDriver driver;

  public TestNgTestBase(){
    try {
    //  TestUtils.setSystemVar();
      initTestSuite();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void initTestSuite() throws IOException {
//    baseUrl = PropertyLoader.loadProperty("site.url");
//    gridHubUrl = PropertyLoader.loadProperty("grid.url");
//    if ("".equals(gridHubUrl)) {
//      gridHubUrl = null;
//    }
    capabilities = PropertyLoader.loadCapabilities();
    WebDriverFactory.setMode(WebDriverFactoryMode.THREADLOCAL_SINGLETON);
    driver = WebDriverFactory.getDriver(capabilities);
  }


  @AfterSuite(alwaysRun = true)
  public void tearDown() {
    WebDriverFactory.dismissAll();
  }
}
