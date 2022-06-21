package com.tsi.melvin.program;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.system.SystemProperties;

public class seleniumTest {
    public WebDriver driver;

  /*  @Test
    public void chromeDriverTest(){
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.quit();

    }


    @Test
    public void chromeSessionTest(){

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Melvin Joy\\IdeaProjects\\program\\chromdriver.exe");
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        driver.quit();

    } */
}
