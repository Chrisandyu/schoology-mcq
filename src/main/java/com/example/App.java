package com.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.time.Duration;

//mvn compile ;  mvn exec:java -Dexec.mainClass="com.example.App"
public class App {
    static String fileName = "test.json";
    static WebDriver driver;

    public static void main(String[] args) {
        driver = new FirefoxDriver();
        driver.get("https://fuhsd.schoology.com");

        login();

        navigateToUnit(1);
        //get the file
        int numQuestions = 5;
        for (int i = 0; i < numQuestions; i++){
            populateQuestion(i);
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

    }

    public static void login() {
        //click on user google account
        // enter password, click next
        // wait for success
    }

    public static void navigateToUnit(int unit){
        //go to schoology group url
        // click through the folders
    }

    public static void populateQuestion(int questionNum) {
        //parse json for question details
        // enter details
        // save
    }
}
