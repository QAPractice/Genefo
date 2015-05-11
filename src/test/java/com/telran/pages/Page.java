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
 */
public abstract class Page {

  public String PAGE_URL;
  public String PAGE_TITLE;
  protected WebDriver driver;
  protected StringBuffer verificationErrors = new StringBuffer();
  /*
   * Constructor injecting the WebDriver interface
   * 
   * @param webDriver
   */
  public Page(WebDriver driver) {

      this.driver = driver;
  }

  public String getTitle() {
    return driver.getTitle();
  }

  public String getPageUrl() {
    return PAGE_URL;
  }

  public String getPageTitle() {
    return PAGE_TITLE;
  }

  public void loadPage() {
    driver.get(getPageUrl());
    //assertEquals(getTitle(), getPageTitle());
  }

  public void setElementText(WebElement element, String text) {
    element.clear();
    element.sendKeys(text);
    Assert.assertEquals(element.getAttribute("value"), text);
  }


  public void clickElement(WebElement element) {
    element.click();
  }

  public void waitUntilIsLoaded(WebElement element) throws IOException, InterruptedException {
    new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOf(element));
  }

  public void selectValueInDropdown(WebElement dropdown, String value) {
    Select select = new Select(dropdown);
    select.selectByValue(value);
  }

  public boolean verifyElementIsPresent(WebElement element) {
    try {
      element.getTagName();
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }


  public void verifyText(String text, WebElement element) {
    try {
      Assert.assertEquals(text, element.getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
  }

    public boolean verifyTextBoolean(String text, WebElement element) {
       return text.equals(element.getText());
    }


  public boolean exists(WebElement element) {
    try {
      element.isDisplayed();
      return true;
    } catch (org.openqa.selenium.NoSuchElementException ignored) {
      return false;
    }
  }

  public void waitUntilElementIsLoaded(WebElement element) throws IOException, InterruptedException {
    new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOf(element));
  }

  public void waitForElement(WebDriverWait wait, String element) {
    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(element)));
  }

  protected boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (org.openqa.selenium.NoSuchElementException e) {
      return false;
    }
  }

  // Pay attention: Works Only for first cell
  public boolean IsCellGreenAfterClick(WebElement locator) {
    clickElement(locator);
    // Is it Green?
    return "#008000".equals(Color.fromString(locator.getCssValue("background-color")).asHex());
  }

  public boolean IsCellColorChangedAfterClick(WebElement cell) {
    String cellColorBeforeClick = Color.fromString(cell.getCssValue("background-color")).asHex();
    clickElement(cell);
    String cellColorAfterClick = Color.fromString(cell.getCssValue("background-color")).asHex();
    return !cellColorBeforeClick.equals(cellColorAfterClick);
  }


}
