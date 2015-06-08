package com.telran.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * Abstract class representation of a Page in the UI. Page object pattern
 *
 */
public abstract class Page {

  public String PAGE_URL;
  public String PAGE_TITLE;
  protected WebDriver driver;
  protected StringBuffer verificationErrors = new StringBuffer();

  /**
   * Constructor injecting the WebDriver interface
   *
   * @param driver
   */
  public Page(WebDriver driver) {

      this.driver = driver;
  }

  /**
   *
   * @return
   */
  public String getTitle() {
    return driver.getTitle();
  }

  /**
   *
   * @return
   */
  public String getPageUrl() {
    return PAGE_URL;
  }

  /**
   *
   * @return
   */
  public String getPageTitle() {
    return PAGE_TITLE;
  }

  /**
   *
   */
  public void loadPage() {
    driver.get(getPageUrl());
    //assertEquals(getTitle(), getPageTitle());
  }

  /**
   *
   * @param element
   * @param text
   */
  public void setElementText(WebElement element, String text) {
    element.clear();
    element.sendKeys(text);
    Assert.assertEquals(element.getAttribute("value"), text);
  }

  /**
   *
   * @param element
   */
  public void clickElement(WebElement element) {
    element.click();
  }

  /**
   *
   * @param element
   * @throws IOException
   * @throws InterruptedException
   */
  public void waitUntilIsLoaded(WebElement element) throws IOException, InterruptedException {
    new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOf(element));
  }

 // public void selectValueInDropdown(WebElement dropdown, String value) {
 //   Select select = new Select(dropdown);
  //  select.selectByValue(value);
 // }

  /**
   * Returns label that we chose
   *
   * @param dropdown
   * @param value
   * @return
   */
   public String selectValueInDropdown(WebElement dropdown, String value) {
        Select select = new Select(dropdown);
        select.selectByValue(value);
        WebElement option = select.getFirstSelectedOption(); // Chooses label that fits the value
        return option.getText();
    }

  /**
   *
   * @param element
   * @return
   */
  public boolean verifyElementIsPresent(WebElement element) {
    try {
      element.getTagName();
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  /**
   *
   * @param element
   * @param text
   */
  public void verifyText(WebElement element, String text) {
    try {
      Assert.assertEquals(text, element.getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
  }

  /**
   *
   * @param element
   * @param text
   * @return
   */
  public boolean verifyTextBoolean(WebElement element, String text) {
       return text.equals(element.getText());
    }

  /**
   * Verifies that we chose the label that we wanted
   *
   * @param label
   * @param chosenOption
   * @return
   */
   public boolean verifyTextBooleanInDropDown(String label, String chosenOption) {
       return chosenOption.equals(label);
   }

  /**
   *
   * @param element
   * @return
   */
  public boolean exists(WebElement element) {
    try {
      element.isDisplayed();
      return true;
    } catch (org.openqa.selenium.NoSuchElementException ignored) {
      return false;
    }
  }

  /**
   *
   * @param element
   * @throws IOException
   * @throws InterruptedException
   */
  public void waitUntilElementIsLoaded(WebElement element) throws IOException, InterruptedException {
    new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOf(element));
  }

  /**
   *
   * @param wait
   * @param element
   */
  public void waitForElement(WebDriverWait wait, String element) {
    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(element)));
  }

  /**
   *
   * @param by
   * @return
   */
  protected boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (org.openqa.selenium.NoSuchElementException e) {
      return false;
    }
  }

  /**
   * Pay attention: Works Only for first cell
   *
   * @param locator
   * @return
   */
  public boolean IsCellGreenAfterClick(WebElement locator) {
    clickElement(locator);
    // Is it Green?
    return "#008000".equals(Color.fromString(locator.getCssValue("background-color")).asHex());
  }

  /**
   *
   * @param cell
   * @return
   */
  public boolean IsCellColorChangedAfterClick(WebElement cell) {
    String cellColorBeforeClick = Color.fromString(cell.getCssValue("background-color")).asHex();
    clickElement(cell);
    String cellColorAfterClick = Color.fromString(cell.getCssValue("background-color")).asHex();
    return !cellColorBeforeClick.equals(cellColorAfterClick);
  }

  /**
   *
   * @param element
   * @param text
   */
    public void verifyClass (WebElement element, String text) {
        try {
            Assert.assertEquals(text, element.getAttribute("class"));
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
    }


}
