
import org.json.JSONException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageobjects.MainPage;

import java.io.IOException;

import static webdriver.WebBrowserDriverInitialize.*;

public class TestRunner {

    private static final String MAIN_PAGE_URL = "https://store.steampowered.com/";

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
        //driver.close();
    }

    @Test
    public void steamTest(){
        Assert.assertEquals(MAIN_PAGE_URL, driver.getCurrentUrl());
        mainPage.selectMenuAdventures();
        mainPage.selectTopSellerGame();

        mainPage.downloadSteam();
    }


}
