package com.telran.pages;

import com.telran.LogLog4j;
import com.telran.util.PropertyLoader;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.IOException;
import java.util.HashMap;
import java.util.NoSuchElementException;

/**
 * Abstract class representation of a Page in the UI. Page object pattern
 */
public abstract class Page {
  private static Logger Log = Logger.getLogger(LogLog4j.class.getName());
public String baseUrl;
  public String PAGE_URL;
  public String PAGE_TITLE;
protected WebDriver driver;
  protected StringBuffer verificationErrors = new StringBuffer();
  HashMap<String, String> allElementsMap;
  /*
   * Constructor injecting the WebDriver interface
   * 
   * @param webDriver
   */

  public Page(WebDriver driver) {
    this.driver=driver;
    this.allElementsMap = new HashMap<String, String>();
    try {
      baseUrl = PropertyLoader.loadProperty("site.url");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private WebElement getWebElement(String name) {
    return driver.findElement(By.xpath(allElementsMap.get(name)));
  }

  public Page clickWebElement(String name) {
    getWebElement(name).click();
    return this;
  }

  public Page setElementText(String name, String text) {
    WebElement element = getWebElement(name);
    element.clear();
    element.sendKeys(text);
  //  Assert.assertEquals(element.getAttribute("value"), text);
    return this;
  }

  public void goBackBrowserButton() {
    driver.navigate().back();
  }

  public void goForwardBrowserButton() {
    driver.navigate().forward();
  }

  public void reloadPage() {
    driver.navigate().refresh();
  }

  public void waitUntilIsLoaded(String name) throws IOException, InterruptedException {
    WebElement element = getWebElement(name);

    new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOf(element));
  }

  public boolean verifyTextBoolean(String name, String text) {
    WebElement element = getWebElement(name);
    return text.equals(element.getText());
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
//  Assert.assertEquals(getTitle(), getPageTitle());
  }

  public void setElementText(WebElement element, String text) {
    element.click();
    element.clear();
    Log.info("entering text '" + text + "' into element " + element);
    element.sendKeys(text);
  // Assert.assertEquals(element.getAttribute("value"), text);
  }


  public void clickElement(WebElement element) {
    Log.info("clicking on element " + element + "");
    element.click();
  }

  public void waitUntilIsLoadedCustomTime(WebElement element, int time) {
    try {
      new WebDriverWait(driver, time).until(ExpectedConditions.visibilityOf(element));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void waitUntilIsLoaded(WebElement element) {
    try {
      new WebDriverWait(driver, 7).until(ExpectedConditions.visibilityOf(element));
    } catch (Exception e) {
      Log.info("---------------------------------");
      Log.info("element " + element + " can not be found by ExpectedConditions.visibilityOf(element)");
      Log.info("---------------------------------");
      e.printStackTrace();
    }
  }

  // public void selectValueInDropdown(WebElement dropdown, String value) {
  //   Select select = new Select(dropdown);
  //  select.selectByValue(value);
  // }

  // Returns label that we chose
  public String selectValueInDropdown(WebElement dropdown, String value) {
    Select select = new Select(dropdown);
    select.selectByValue(value);
    WebElement option = select.getFirstSelectedOption(); // Chooses label that fits the value
    return option.getText();
  }

  public boolean verifyElementIsPresent(WebElement element) {
    try {
      element.getTagName();
      return true;
    } catch (NoSuchElementException e) {
      Log.info("---------------------------------");
      Log.info("element " + element + " can not be found by  element.getTagName()");
      Log.info("---------------------------------");
      return false;
    }
  }

  public void verifyText(WebElement element, String text) {
    try {
      Assert.assertEquals(text, element.getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
  }

  public boolean verifyTextBoolean(WebElement element, String text) {
    Log.info("verifying that text from element " + element + " - ('" + element.getText() + "') - is equal to text '" + text + "'");
    return text.equals(element.getText());
  }

  // Verifies that we chose the label that we wanted.
  public boolean verifyTextBooleanInDropDown(String label, String chosenOption) {
    return chosenOption.equals(label);
  }

  public boolean exists(WebElement element) {
    try {
      return element.isDisplayed();
    } catch (org.openqa.selenium.NoSuchElementException ignored) {
      return false;
    }
  }

  public void waitUntilElementIsLoaded(WebElement element) throws IOException, InterruptedException {
    try {
      new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOf(element));

    }catch (TimeoutException e){

    }
  }


  public void waitForElement(WebDriverWait wait, String element) {
    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(element)));
  }

  protected boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (org.openqa.selenium.NoSuchElementException e) {
      Log.info("----------ALERT-----------------");
      Log.info("element " + by + " can not be found by ExpectedConditions.visibilityOf(element)");
      Log.info("---------ALERT------------------");
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

  public void verifyClass(WebElement element, String text) {
    try {
      Assert.assertEquals(text, element.getAttribute("class"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
  }
}



