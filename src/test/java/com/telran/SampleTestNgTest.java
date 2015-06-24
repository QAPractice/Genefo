package com.telran;

import com.telran.pages.HomePage;
import org.openqa.selenium.support.PageFactory;

public class SampleTestNgTest extends TestNgTestBase {

  private HomePage homepage;

  // @BeforeMethod
  public void initPageObjects() {
    homepage = PageFactory.initElements(driver, HomePage.class);
  }


}
