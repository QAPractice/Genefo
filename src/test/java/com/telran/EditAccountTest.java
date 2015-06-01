package com.telran;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;

import java.io.File;


public class EditAccountTest {

    public WebDriver driver;
    public WebDriverWait wait;

    @BeforeClass
    public void setup(){

        File file = new File("C://Users//E.Frumker//AppData//Local//Mozilla Firefox//firefox.exe");
        FirefoxBinary binary = new FirefoxBinary();
        FirefoxProfile profile = new FirefoxProfile();
        this.driver = new FirefoxDriver(binary,profile);
        wait = new WebDriverWait(driver,5);
    }

}
