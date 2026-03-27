package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.WebElement;
import java.time.Duration;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

//mvn compile ;  mvn exec:java -Dexec.mainClass="com.example.App"
public class App {
    static String fileName = "test.json";
    static WebDriver driver;

    public static void main(String[] args) {
        driver = new FirefoxDriver();
        driver.get("https://fuhsd.schoology.com");

        login();

        driver.get(getBankURL(1));

        int numQuestions = 5;
        for (int i = 0; i < numQuestions; i++){
            populateQuestion(i);
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

    }

    public static void login() {
        //enter email
        WebElement emailInput = waitFor(By.id("identifierId"));
        emailInput.sendKeys("czhang299@student.fuhsd.org");
        //click next
        driver.findElement(By.xpath("//span[text()='Next']/..")).click();

        //enter password, click next
        WebElement passInput =  waitFor(By.xpath("//input[@type='password']"));
        passInput.sendKeys("enter password here");

        driver.findElement(By.xpath("//span[text()='Next']/..")).click();

    }

    public static WebElement waitFor(By thing) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(thing));
    }

    public static String getBankURL(int unit){
        List<String> urls = Arrays.asList(
            "https://fuhsd.schoology.com/common-assessment-question-banks/219881976?f=378005801",
            "https://fuhsd.schoology.com/common-assessment-question-banks/220480315?f=378005802",
            "https://fuhsd.schoology.com/common-assessment-question-banks/220480355?f=378005803"
        );
        return urls.get(unit+1);
    }

    public static void populateQuestion(int questionNum) {

    }
}
