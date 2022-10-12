package ru.ibs.framework.managers;

import org.apache.commons.exec.OS;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static ru.ibs.framework.utils.PropConstants.*;

public class DriverManager {

    private WebDriver driver;

    private static DriverManager INSTANCE = null;

    private final TestPropManager props = TestPropManager.getTestPropManager();

    private DriverManager() {
    }

    public static DriverManager getDriverManager() {
        if (INSTANCE == null) {
            INSTANCE = new DriverManager();
        }
        return INSTANCE;
    }

    public WebDriver getDriver() {
        if (driver == null) {
            initDriver();
        }
        return driver;
    }

    public void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    private void initDriver() {
        if (OS.isFamilyWindows()) {
            initDriverWindowsOsFamily();
        } else if (OS.isFamilyMac()) {
            initDriverMacOsFamily();
        }
    }

    private void initDriverWindowsOsFamily() {
        initDriverAnyOsFamily(PATH_CHROME_DRIVER_WINDOWS);
    }

    private void initDriverMacOsFamily() {
        initDriverAnyOsFamily(PATH_CHROME_DRIVER_MAC);
    }

    private void initDriverAnyOsFamily(String chrome) {
        if ("chrome".equals(props.getProperty(BROWSER_TYPE))) {
            System.setProperty("webdriver.chrome.driver", props.getProperty(chrome));
            driver = new ChromeDriver();
        } else {
            Assert.fail("Типа браузера '" + props.getProperty(BROWSER_TYPE) + "' не существует во фреймворке");
        }
    }
}
