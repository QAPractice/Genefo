package com.telran.pages;

import org.apache.xpath.operations.Bool;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class AddProfilePage extends Page {




    public AddProfilePage(WebDriver driver) {
        super(driver);
        fillMap();
    }
    private void fillMap(){
// if you want to find by id: "id=element_id";
// if you want to find by name: "name=element_name";

        allElementsMap.put("Create new Profile","//div[@class='panel-heading']//*[contains(text(),'Create New Profile')]");

        allElementsMap.put("How do you know the patient?", "//div[@ng-controller='ProfileNewController']//select[@name='relationID']");
        allElementsMap.put("First name","//div[@ng-controller='ProfileNewController'] //*[@name='firstName']");
        allElementsMap.put("Last name", "//div[@ng-controller='ProfileNewController'] //*[@name='lastName']");
        allElementsMap.put("Condition","//div[@ng-controller='ProfileNewController'] //li[@id='typeahead-08I-3754-option-0']");
        allElementsMap.put("Condition choice 1", "//li[@id='typeahead-08I-3754-option-0']");

        allElementsMap.put("Patient's Diagnosis Date Month", "//div[@ng-controller='ProfileNewController']//select[@name='diagnosisMonth'] ");
        allElementsMap.put("Patient's Diagnosis Date Year", "//div[@ng-controller='ProfileNewController']//select[@name='diagnosisYear'] ");
        allElementsMap.put("Patient's Diagnosis Date Gender", "//div[@ng-controller='ProfileNewController']//select[@name='genderID']");
        //        allElementsMap.put("Patient's Race", "//ul[@class='chosen-results']/li[@data-option-array-index='1']"); //1..6
        fillById("Patient's Race", "//ul[@class='chosen-results']/li[@data-option-array-index='", "']", 1, 6);
        allElementsMap.put("Patient's Birthday Month", "//div[@ng-controller='ProfileNewController']//select[@name='birthmonth']");
        allElementsMap.put("Patient's Birthday Day", "//div[@ng-controller='ProfileNewController']//select[@name='birthday']");
        allElementsMap.put("Patient's Birthday Year", "//div[@ng-controller='ProfileNewController']//select[@name='birthyear']");
        allElementsMap.put("Patient's Location", "//div[@ng-controller='ProfileNewController'] //textarea[@name='bio']");

//        allElementsMap.put("Patient's Location", "//ul[@class='dropdown-menu ng-isolate-scope']/li[1]");
        fillById("Patient's Location", "//ul[@class='dropdown-menu ng-isolate-scope']/li[", "]", 1, 10);
        allElementsMap.put("Save", "id=submit");
        allElementsMap.put("Cancel","//*[@class='ng-click']/*[contains(text(),'button')]");

        allElementsMap.put("Add a Profile Picture", "//div[@ng-controller='ProfileNewController']//div[@class='profilePicUploadDragBox ng-isolate-scope ng-valid ng-pristine']");
//        driver.findElement(By.id("inputFile")).sendKeys("C:/path/to/file.jpg");

        allElementsMap.put("", "");
    }

    private void fillById(String name,String begin,String end,int... index){
        for (int i = index[0]; i <= index[1]; i++) {
            allElementsMap.put(name+" "+i,begin+i+end);
        }

    }

    private enum FindByWhat{

        BY_ID,BY_NAME,BY_XPATH;
    }
    public WebElement getWebElement(String name){

        FindByWhat value;

        if(name.contains("name=")) {
            name = name.substring(name.indexOf("="), name.length());
            value = FindByWhat.BY_NAME;
        }
        else if(name.contains("id=")) {
            name = name.substring(name.indexOf("="), name.length());
            value = FindByWhat.BY_ID;
        }
        else
            value=FindByWhat.BY_XPATH;

        switch(value){
            case BY_NAME:
                return driver.findElement(By.name(name));
            case BY_ID:
                return driver.findElement(By.id(name));
            case BY_XPATH:
                return driver.findElement(By.xpath(name));
            default:
                try {
                    throw new Exception();
                } catch (Exception e) {
                    System.out.println("Element not FOUND in Map");
                    e.printStackTrace();
                }
                return null;
        }


    }

}
