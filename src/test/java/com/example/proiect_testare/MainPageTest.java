package com.example.proiect_testare;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;

public class MainPageTest {
    MainPage mainPage = new MainPage();

    @BeforeAll
    public static void setUpAll() {
        Configuration.browserSize = "1280x800";
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    public void setUp() {
        open("https://medicalcentertestare.azurewebsites.net/");
    }

    @Test
    public void checkRegisterLinkFunctionality() {
        mainPage.registerLink.click();
        boolean  isDisplayed = $x("/html/body/div/main/div/div/form/h4").isDisplayed();
        Assertions.assertTrue(isDisplayed);
    }

    @Test
    public void registerAccountUsingOnlyEmail() {
        mainPage.registerLink.click();
        $x("//*[@id=\"Input_Email\"]").sendKeys("test@gmail.com");
        mainPage.registerButton.click();
        boolean isMessageFound = $x("/html/body/div/main/div/div/form/div[1]/ul/li").getText().contains("A valid password is required");
        Assertions.assertTrue(isMessageFound);
    }

    @Test
    public void registerAccountUsingOnlyPassword() {
        mainPage.registerLink.click();

        $x("//*[@id=\"Input_Password\"]").sendKeys("test1234");
        $x("//*[@id=\"Input_ConfirmPassword\"]").sendKeys("test1234");
        mainPage.registerButton.click();
        boolean isMessageFound = $x("/html/body/div/main/div/div/form/div[1]/ul/li").getText().contains("A valid email is required");
        Assertions.assertTrue(isMessageFound);

    }

    @Test
    public void registerAccountUsingEmailAndPass() {
        mainPage.registerLink.click();

        $x("//*[@id=\"Input_Email\"]").sendKeys("test1shhs211121221s3@gmail.com");
        $x("//*[@id=\"Input_Password\"]").sendKeys("Test1234!");
        $x("//*[@id=\"Input_ConfirmPassword\"]").sendKeys("Test1234!");

        mainPage.registerButton.click();
        boolean emailExists = $("div.validation-summary-errors").getText().contains("Username 'test1shhs211121221s3@gmail.com' is already taken.");
        Assertions.assertTrue(emailExists);
    }


    @Test

    public void checkLoginFunctinality() {
        mainPage.loginLink.click();
        boolean isDisplayed = $x("//*[@id=\"account\"]/div[2]").isDisplayed();
        Assertions.assertTrue(isDisplayed);
    }

    @Test
    public void loginInUsingEmailAndPass() {
        mainPage.loginLink.click();
        $x("//*[@id=\"Input_Email\"]").sendKeys("test@gmail.com");
        $x("//*[@id=\"Input_Password\"]").sendKeys("Test1234!");
        mainPage.loginButton.click();
        boolean isTextFound = $x("//*[@id=\"account\"]/div[1]/ul/li").getText().contains("Invalid login attempt.");
        Assertions.assertTrue(isTextFound);
    }

    @Test
    public void selectGermanLanguage() {
        $x("//*[@id=\"culture-options\"]").click();
        $x("//*[@id=\"culture-options\"]").selectOptionByValue("de");
        boolean isTextFound = $x("/html/body/header/nav/div/a").getText().contains("Ärztezentrum");
        Assertions.assertTrue(isTextFound);

    }

    @Test
    public void selectJapaneseLanguage() {
        $x("//*[@id=\"culture-options\"]").click();
        $x("//*[@id=\"culture-options\"]").selectOptionByValue("ja");
        boolean isTextFound = $x("/html/body/header/nav/div/a").getText().contains("医療センター");
        Assertions.assertTrue(isTextFound);

    }

    @Test
    public void selectEmptyLanguage() {
        $x("//*[@id=\"culture-options\"]").selectOption(0);

        List<WebElement> headerElements = webdriver().object().findElements(By.className("nav-item"));
        Assertions.assertTrue(headerElements.size() > 0);

    }
}


