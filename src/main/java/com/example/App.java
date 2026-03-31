package com.example;

import io.github.cdimascio.dotenv.Dotenv;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.lang.Character;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.WheelInput;
import org.openqa.selenium.support.ui.*;

//mvn compile ;  mvn exec:java -Dexec.mainClass="com.example.App"
public class App {

    static String fileName = "test.json";
    static WebDriver driver;
    static Dotenv dotenv;

    public static void main(String[] args) {
        dotenv = Dotenv.load();

        FirefoxOptions options = new FirefoxOptions();

        String profilePath = dotenv.get("FIREFOX_PROFILE_PATH");
        options.addArguments("-profile", profilePath);

        driver = new FirefoxDriver(options);
        driver.get(getBankURL(1));

        By accountButtonBy = By.xpath(
            "//div[@data-button-type='multipleChoiceIdentifier']"
        );
        WebElement accountButton = waitForElement(accountButtonBy);
        accountButton.click();

        populateQuestion(1);
        // int numQuestions = 5;
        // for (int i = 0; i < numQuestions; i++) {
        //     populateQuestion(i);
        // }
    }

    public static void populateQuestion(int questionNum) {
        WebElement createMCQButton = waitForElement(
            By.xpath("//a[@aria-label='Add Multiple Choice Item']")
        );
        createMCQButton.click();

        WebElement questionBox = waitForElement(
            By.xpath("//div[@sgy-translation-attr='Write your question']")
        );
        sleep(0.2);

        questionBox.click();
        sleep(0.2);
        String question = "What is the pKa of magic acid?";
        sendKeysSlower(question);

        //add the 5th option if exists
        WebElement addOptionButton = waitForElement(
            By.xpath("//button[@aria-label='Option']")
        );
        int numOptions = 5;
        if (numOptions == 5) addOptionButton.click();
        sleep(0.5);

        List<WebElement> optionBoxes = waitForElements(
            By.xpath(
                "//div[contains(@aria-label, 'Label') and @role='textbox']"
            )
        );

        for (WebElement option : optionBoxes) {
            option.click();
            sleep(0.1);
            String answer = "i don't know?????";
            sendKeysSlower(answer);
            sleep(0.1);
        }

        sleep(0.2);

        //get scrollable container
        WebElement scrollableDiv = waitForElement(
            By.className("ltq-modal-question-editor__scrollable-content")
        );
        //https://www.selenium.dev/documentation/webdriver/actions_api/wheel/
        WheelInput.ScrollOrigin scrollOrigin =
            WheelInput.ScrollOrigin.fromElement(scrollableDiv);
        new Actions(driver).scrollFromOrigin(scrollOrigin, 0, 400).perform();

        sleep(3);

        int answer = 5;
        List<WebElement> answerChoiceRadios = waitForElements(
            By.xpath("//*[@class='lrn-mcq-option']//input[@type='radio']")
        );
        answerChoiceRadios.get(answer - 1).click();

        WebElement shuffleOptionButton = waitForElement(
            By.xpath(
                "//span[@class='lrn-qe-switch-trigger' and @yes-text='Yes']"
            )
        );
        shuffleOptionButton.click();

        sleep(0.5);

        WebElement saveButton = waitForElement(
            By.cssSelector(".save.btn.btn-primary")
        );
        saveButton.click();
    }

    public static WebElement waitForElement(By thing) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(thing));
    }

    public static List<WebElement> waitForElements(By thing) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(
            ExpectedConditions.presenceOfAllElementsLocatedBy(thing)
        );
    }

    public static void sleep(double seconds) {
        try {
            Thread.sleep((int) (seconds * 1000));
        } catch (InterruptedException error) {}
    }

    public static String getBankURL(int unit) {
        List<String> urls = Arrays.asList(
            "https://fuhsd.schoology.com/common-assessment-question-banks/219881976?f=378005801",
            "https://fuhsd.schoology.com/common-assessment-question-banks/220480315?f=378005802",
            "https://fuhsd.schoology.com/common-assessment-question-banks/220480355?f=378005803",
            "https://fuhsd.schoology.com/common-assessment-question-banks/220480373?f=378005804",
            "https://fuhsd.schoology.com/common-assessment-question-banks/220381074?f=378005807",
            "https://fuhsd.schoology.com/common-assessment-question-banks/220480392?f=378005809",
            "https://fuhsd.schoology.com/common-assessment-question-banks/220480403?f=378005814",
            "https://fuhsd.schoology.com/common-assessment-question-banks/220480413?f=378005823",
            "https://fuhsd.schoology.com/common-assessment-question-banks/220480432?f=378005834"
        );
        return urls.get(unit + 1);
    }

    static void sendKeysSlower(String text) {
        for (char c : text.toCharArray()) {
            new Actions(driver).sendKeys(Character.toString(c)).perform();
            sleep(0.02);
        }
    }
}
