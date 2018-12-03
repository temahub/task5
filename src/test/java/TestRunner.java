
import org.json.JSONException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageobjects.MainPage;

import java.io.IOException;

import static pageobjects.MainPage.REGEXINTEGER;
import static webdriver.WebBrowserDriverInitialize.*;

public class TestRunner {

    private static final String MAIN_PAGE_URL = "https://store.steampowered.com/";
    private static final String SALE = "//div[@class='discount_pct']";

    private MainPage mainPage;
    private WebDriver driver;

    @BeforeClass
    public void setUp() throws IOException, JSONException {
        driver = initialize();
        mainPage = new MainPage(driver);
        mainPage.mainPage();
    }

    @AfterClass
    public void tearDown(){
        driver.close();
    }

    @Test
    public void steamTest() throws InterruptedException{
        Assert.assertEquals(MAIN_PAGE_URL, driver.getCurrentUrl());

        mainPage.selectMenuAdventures();
        mainPage.selectTopSellerGame();
        int sale = Integer.parseInt(driver.findElement(By.xpath(SALE)).getText().replaceAll(REGEXINTEGER, ""));
        Assert.assertEquals(mainPage.maxSale, sale);

        mainPage.downloadSteam();
        Assert.assertTrue(mainPage.file.exists());
    }
}
