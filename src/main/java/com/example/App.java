package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.WebElement;
import java.time.Duration;
import java.util.List;
import java.util.Arrays;
import io.github.cdimascio.dotenv.Dotenv;

//mvn compile ;  mvn exec:java -Dexec.mainClass="com.example.App"
public class App {
    static String fileName = "test.json";
    static WebDriver driver;

    public static void main(String[] args) {
        driver = new FirefoxDriver();
        driver.get(getBankURL(1));

        login();

        int numQuestions = 5;
        for (int i = 0; i < numQuestions; i++){
            populateQuestion(i);
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

    }

    public static WebElement waitFor(By thing) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(thing));
    }

    public static void sleep(int seconds){
        try{
            Thread.sleep(seconds * 1000);
        }catch(InterruptedException error){

        }
    }

    public static void login() {
        Dotenv dotenv = Dotenv.load();
        String email = dotenv.get("EMAIL");
        String pass = dotenv.get("PASS");

        //enter email
        WebElement emailInput = waitFor(By.id("identifierId"));
        emailInput.sendKeys(email);

        //click next
        sleep(1);
        driver.findElement(By.xpath("//span[text()='Next']/..")).click();

        //enter password, click next
        WebElement passInput =  waitFor(By.xpath("//input[@type='password']"));

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        passInput.sendKeys(pass);


        //Must click next manually??
        // sleep(5);
        // driver.findElement(By.xpath("//span[text()='Next']/..")).click();
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
