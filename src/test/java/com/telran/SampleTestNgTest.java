package com.telran;

import com.telran.pages.HomePage;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class SampleTestNgTest extends TestNgTestBase {

  private HomePage homepage;

  // @BeforeMethod
  public void initPageObjects() {
    homepage = PageFactory.initElements(driver, HomePage.class);
  }

  //@Test
  public void testHomePageHasAHeader() {
    driver.get(baseUrl);
    Assert.assertFalse("".equals(homepage.header.getText()));
  }
}
