package com.ibs.tests.parametrizedrgstest;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

@RunWith(Parameterized.class)
public class ParametrizedRgsTest {
    private WebDriver driver;

    @Parameterized.Parameters
    public static Collection bio() {
        return Arrays.asList(new Object[][]{
                {"Иванов Иван Иванович"},
                {"Петров Петр Петрович"},
                {"Григорьев Григорий Григорьевич"}
        });
    }

    @Parameterized.Parameter()
    public String Bio;

    @Before
    public void before() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
        driver = new ChromeDriver();
        driver.get("https://www.rgs.ru/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(3, TimeUnit.SECONDS);
    }

    @Test
    public void test() {

        //Переход по вкладке Компаниям
        checkFrame();
        WebElement companiam = driver.findElement(By.xpath("//a[@href='/for-companies']"));
        checkFrame();
        companiam.click();
        checkFrame();
        Assert.assertTrue("Переход по вкладке Компаниям не произошел",
                driver.findElement(By.xpath("//span[@class='padding' and text()='Строительство']")).isDisplayed());
        checkFrame();

        //Переход по вкладке Здоровье
        WebElement zdorovie = driver.findElement(By.xpath("//span[text()='Здоровье' and @class='padding']"));
        checkFrame();
        zdorovie.click();
        checkFrame();
        WebElement dms = driver.findElement(By.xpath("//a[contains(@href, 'dobrovolnoe')]"));
        new WebDriverWait(driver, 3, 300)
                .until(ExpectedConditions.visibilityOf(dms));
        checkFrame();

        //Переход по вкладке ДМС
        dms.click();
        checkFrame();
        WebElement dmsSign = driver.findElement(By.xpath("//h1[contains(@class, 'word-breaking')]"));
        Assert.assertTrue("Не отображается хед текст", dmsSign.isDisplayed());
        Assert.assertTrue("Текст хедтекста не соответсвует",
                dmsSign.getText().contains("Добровольное медицинское страхование"));

        //Ввод данных
        WebElement nameInput = driver.findElement(By.xpath("//input[@name='userName']"));
        nameInput.sendKeys(Bio);
        Assert.assertEquals("Фио не соответствует требованиям",
                Bio, nameInput.getAttribute("value"));

        WebElement phoneInput = driver.findElement(By.xpath("//input[@name='userTel']"));
        phoneInput.sendKeys("+79999999999");
        Assert.assertEquals("Номер не соответствует требованиям",
                "+7 (799) 999-9999", phoneInput.getAttribute("value"));

        WebElement emailInput = driver.findElement(By.xpath("//input[@name='userEmail']"));
        emailInput.sendKeys("qwerty qwerty");
        Assert.assertEquals("Почта не соответствует требованиям",
                "qwerty qwerty", emailInput.getAttribute("value"));

        WebElement addressInput = driver.findElement(By.xpath("//input[contains(@class, 'dadata')]"));
        addressInput.sendKeys("Москва, Тверская ул., дом 5/6");
        Assert.assertEquals("Адрес не соответсвует требованиям",
                "Москва, Тверская ул., дом 5/6",
                addressInput.getAttribute("value"));
        addressInput.sendKeys(Keys.TAB);

        //Галочка политики безопасности и сабмит
        WebElement policiesKey = driver.findElement(By.xpath("//input[@type='checkbox']"));

        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click()", policiesKey);

        new WebDriverWait(driver, 3, 300)
                .until(ExpectedConditions.attributeToBe(policiesKey, "value", "true"));
        WebElement submitButton = driver.findElement(By.xpath("//button[@type='submit']"));
        executor.executeScript("arguments[0].click()", submitButton);

        //Проверка ошибки на некорректный ввод email
        Assert.assertTrue(driver.findElement(By.xpath("//span[contains(@class, 'input__error')]")).isDisplayed());
        Assert.assertEquals("Ожидаемый ввод не соответствует действительному", "Введите корректный адрес электронной почты",
                driver.findElement(By.xpath("//span[contains(@class, 'input__error')]")).getText());
    }

    //Проверка наличия фрейма и его закрытие
    public void checkFrame() {
        try {
            WebElement frame = driver.findElement(By.xpath("//iframe[@title='Flocktory widget']"));
            new WebDriverWait(driver, 3, 500)
                    .until(ExpectedConditions.visibilityOf(frame));
            driver.switchTo().frame(frame);
            new WebDriverWait(driver, 3)
                    .until(ExpectedConditions
                            .visibilityOf(driver.findElement(By.xpath("//div[@class='widget__close js-collapse-login']")))).click();
        } catch (Exception ignore) {
        } finally {
        driver.switchTo().defaultContent();
        }
    }

    @After
    public void after() {
        driver.quit();
    }

}
