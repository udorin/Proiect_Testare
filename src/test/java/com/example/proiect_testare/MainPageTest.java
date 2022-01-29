package com.example.proiect_testare;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
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

        $x("/html/body/div/main/div/div/form/h4").isDisplayed();
    }

    @Test
    public void registerAccountUsingOnlyEmail(){
        mainPage.registerLink.click();
        $x("//*[@id=\"Input_Email\"]").sendKeys("test@gmail.com");
        mainPage.registerButton.click();
        $x("/html/body/div/main/div/div/form/div[1]/ul/li").getText().contains("A valid password is required");




    }

    @Test
    public void registerAccountUsingOnlyPassword(){
        mainPage.registerLink.click();

        $x("//*[@id=\"Input_Password\"]").sendKeys("test1234");
        $x("//*[@id=\"Input_ConfirmPassword\"]").sendKeys("test1234");
        mainPage.registerButton.click();
        $x("/html/body/div/main/div/div/form/div[1]/ul/li").getText().contains("A valid email is required");

    }

    @Test
    public void registerAccountUsingEmailAndPass()  {
        mainPage.registerLink.click();

        $x("//*[@id=\"Input_Email\"]").sendKeys("test1shhs211121221s3@gmail.com");
        $x("//*[@id=\"Input_Password\"]").sendKeys("Test1234!");
        $x("//*[@id=\"Input_ConfirmPassword\"]").sendKeys("Test1234!");

        mainPage.registerButton.click();
       String value = String.valueOf($x("/html/body/div/main/p").getText().contains("This app does not currently have a real email sender registered, see these docs for how to configure a real email sender. Normally this would be emailed: Click here to confirm your account"));


    }


    @Test

    public void checkLoginFunctinality(){
    mainPage.loginLink.click();
    $x("//*[@id=\"account\"]/div[2]").isDisplayed();
    }

    @Test
    public void loginInUsingEmailAndPass(){
        mainPage.loginLink.click();
        $x("//*[@id=\"Input_Email\"]").sendKeys("test@gmail.com");
        $x("//*[@id=\"Input_Password\"]").sendKeys("Test1234!");
        mainPage.loginButton.click();
        $x("//*[@id=\"account\"]/div[1]/ul/li").getText().contains("Invalid login attempt.");
        System.out.println($x("//*[@id=\"account\"]/div[1]/ul/li").getText());

    }

    @Test
    public void selectGermanLanguage(){
        $x("//*[@id=\"culture-options\"]").click();
        $x("//*[@id=\"culture-options\"]").selectOptionByValue("de");
        $x("/html/body/header/nav/div/a").getText().contains("Ärztezentrum");

    }

    @Test
    public void selectJapaneseLanguage(){
        $x("//*[@id=\"culture-options\"]").click();
        $x("//*[@id=\"culture-options\"]").selectOptionByValue("ja");
        $x("/html/body/header/nav/div/a").getText().contains("医療センター");

    }
}


