package com.ibs.tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class RgsTest {
    WebDriver driver;

    @Before
    public void before() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
        driver = new ChromeDriver();
        driver.get("https://www.rgs.ru/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
    }

    @Test
    public void test() {

        //Переход по вкладке Компаниям
        checkFrame();
        WebElement companiam = driver.findElement(By.xpath("//a[@href='/for-companies']"));
        companiam.click();
        checkFrame();
        Assert.assertTrue(driver.findElement(By.xpath("//span[@class='padding' and text()='Строительство']")).isDisplayed());

        //Переход по вкладке Здоровье
        WebElement zdorovie = driver.findElement(By.xpath("//span[text()='Здоровье' and @class='padding']"));
        checkFrame();
        zdorovie.click();
        checkFrame();
        WebElement dms = driver.findElement(By.xpath("//a[@href='/for-companies/zdorove/dobrovolnoe-meditsinskoe-strakhovanie']"));
        new WebDriverWait(driver, 3, 100)
                .until(ExpectedConditions.visibilityOf(dms));

        //Переход по вкладке ДМС
        dms.click();
        checkFrame();
        WebElement dmsSign = driver.findElement(By.xpath("//h1[@class='title word-breaking title--h2']"));
        Assert.assertTrue(dmsSign.isDisplayed() && dmsSign.getText().contains("Добровольное медицинское страхование"));

        //Ввод данных
        WebElement nameInput = driver.findElement(By.xpath("//input[@name='userName']"));
        nameInput.sendKeys("Иванов Иван Иванович");
        Assert.assertEquals("Иванов Иван Иванович", nameInput.getAttribute("value"));

        WebElement phoneInput = driver.findElement(By.xpath("//input[@name='userTel']"));
        phoneInput.sendKeys("+79999999999");
        Assert.assertEquals("+7 (799) 999-9999", phoneInput.getAttribute("value"));

        WebElement emailInput = driver.findElement(By.xpath("//input[@name='userEmail']"));
        emailInput.sendKeys("qwerty qwerty");
        Assert.assertEquals("qwerty qwerty", emailInput.getAttribute("value"));

        WebElement addressInput = driver.findElement(By.xpath("//input[@class='vue-dadata__input']"));
        addressInput.sendKeys("Москва, Тверская ул., дом 5/6");
        Assert.assertEquals("Москва, Тверская ул., дом 5/6", addressInput.getAttribute("value"));
        addressInput.sendKeys(Keys.TAB);

        //Галочка политики безопасности и сабмит
        WebElement policiesKey = driver.findElement(By.xpath("//input[@type='checkbox']"));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click()", policiesKey);
        new WebDriverWait(driver, 5, 200)
                .until(ExpectedConditions.attributeToBe(policiesKey, "value", "true"));
        WebElement submitButton = driver.findElement(By.xpath("//button[@type='submit']"));
        executor.executeScript("arguments[0].click()", submitButton);

        //Проверка ошибки на некорректный ввод email

        Assert.assertTrue(driver.findElement(By.xpath("//span[@class=\"input__error text--small\" ]")).isDisplayed());
        Assert.assertEquals("Введите корректный адрес электронной почты", driver.findElement(By.xpath("//span[@class=\"input__error text--small\" ]")).getText());
    }

    //Проверка наличия фрейма и его закрытие
    public void checkFrame() {
        try  {
        WebElement frame = driver.findElement(By.xpath("//iframe[@title='Flocktory widget']"));
        new WebDriverWait(driver, 5, 100)
                .until(ExpectedConditions.visibilityOf(frame));
        driver.switchTo().frame(frame);
            new WebDriverWait(driver, 3)
               .until(ExpectedConditions
                       .visibilityOf(driver.findElement(By.xpath("//div[@class='widget__close js-collapse-login']")))).click();
        }
        catch (Exception e) {
        }
        finally {
            driver.switchTo().defaultContent();
        }
    }

    @After
    public void after() {
        driver.quit();
    }


}
