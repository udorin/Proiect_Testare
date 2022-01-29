package com.example.proiect_testare;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

// https://www.jetbrains.com/
public class MainPage {
    public SelenideElement registerLink = $x("/html/body/header/nav/div/div/ul[2]/li[1]/a");
    public SelenideElement registerButton = $x("/html/body/div/main/div/div/form/button");
    public SelenideElement loginLink = $x("/html/body/header/nav/div/div/ul[2]/li[2]/a");
    public SelenideElement loginButton = $x("//*[@id=\"account\"]/div[5]/button");
}
