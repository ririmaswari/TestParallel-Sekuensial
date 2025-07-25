package com.juaracoding.ecom;

import com.juaracoding.ecom.pages.LoginPage;
import com.juaracoding.ecom.providers.DataTestProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Ignore;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.time.Duration;

@Listeners(ListernerTest.class)
public class AuthenticationTest {

    private WebDriver driver;
    private LoginPage loginPage;
    private WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        loginPage = new LoginPage(driver);
        loginPage.openLoginPage();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }


    @Test(dataProvider = "loginDataProvider", dataProviderClass = DataTestProvider.class)
    public void loginTest(String username, String password) {
        loginPage.login(username, password);

        String expectedUrl = "https://www.saucedemo.com/v1/inventory.html";
        String actualUrl = loginPage.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl, "Login gagal: URL tidak sesuai");
    }

    @Test()
    @Ignore
    public void loginWithInvalidUsername() {
        loginPage.login("standard_userss", "secret_sauce");

        String expected = "Epic sadface: Username and password do not match any user in this service";
        Assert.assertEquals(loginPage.getErrorMessage(), expected,
                "Error message tidak sesuai untuk username invalid");
    }

    @Test
    public void loginWithInvalidPassword() {
        loginPage.login("standard_user", "secret_sauce123");

        String expected = "Epic sadface: Username and password do not match any user in this service";
        Assert.assertEquals(loginPage.getErrorMessage(), expected,
                "Error message tidak sesuai untuk password invalid");
    }

    @Test
    public void loginWithoutPassword() {
        loginPage.login("standard_user", "");

        String expected = "Epic sadface: Password is required";
        Assert.assertEquals(loginPage.getErrorMessageByXpath(), expected,
                "Error message tidak sesuai untuk password kosong");
    }

    @Test
    public void loginWithoutUsername() {
        loginPage.login("", "secret_sauce");

        String expected = "Epic sadface: Username is required";
        Assert.assertEquals(loginPage.getErrorMessageByXpath(), expected,
                "Error message tidak sesuai untuk username kosong");
    }

    @Test
    public void loginWithEmptyCredentials() {
        loginPage.login("", "");

        String expected = "Epic sadface: Username is required";
        Assert.assertEquals(loginPage.getErrorMessageByXpath(), expected,
                "Error message tidak sesuai untuk kredensial kosong");
    }
}
