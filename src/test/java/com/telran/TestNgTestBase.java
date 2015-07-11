package com.telran;

import com.telran.util.PropertyLoader;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import ru.stqa.selenium.factory.WebDriverFactory;
import ru.stqa.selenium.factory.WebDriverFactoryMode;

import java.io.IOException;

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

  @BeforeClass
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
