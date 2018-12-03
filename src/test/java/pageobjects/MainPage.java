package pageobjects;

import menu.GameMenu;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import util.ReaderSettings;

import java.io.File;
import java.util.*;
public class MainPage {

    private static final String TOPSELLING = "//*[@id=\"tab_select_TopSellers\"]/div";
    private static final String MAXSALE =
            "//a/div[@class='discount_block tab_item_discount' and 2]/div[@class='discount_pct' and 1]";
    private static final String AGEYEAR = "//select[@id='ageYear']";
    private static final String VIEWPAGE = "//*[@id=\"app_agegate\"]/div[1]/div[4]/a[1]/span";
    private static final String INSTALLSTEAM = "https://store.steampowered.com/about/";
    private static final String DOWNLOADSTEAM =
            "//div[2]/a[@id='about_install_steam_link' and @class='btn_medium btn_green_white_innerfade' and 1]/span[1]";
    private static final String AGE = "1980";
    public static final String REGEXINTEGER = "[^0-9]";

    private WebDriver driver;
    private GameMenu gameMenu;
    public int maxSale = 0;
    public File file = new File(System.getProperty("user.home")+ "/SteamSetup.exe");

    public MainPage(WebDriver driver){
        this.driver = driver;
        gameMenu = new GameMenu(driver);
    }

    public void mainPage(){
        driver.get(ReaderSettings.urlName);
    }

    public void selectMenuAdventures(){
        gameMenu.selectItem(GameMenu.SubGameMenu.ACTION);
    }

    public void selectTopSellerGame(){
        driver.findElement(By.xpath(TOPSELLING)).click();

        List<WebElement> myList =
                driver.findElements(By.xpath(MAXSALE));

        WebElement maxW = myList.get(0);
        for (WebElement e : myList
             ) {
            String price = e.getText().replaceAll(REGEXINTEGER, "");
            if (price.length() > 0){
                int saleInt = Integer.parseInt(price);
                if (saleInt > maxSale){
                    maxSale = saleInt;
                    maxW = e;
                }
            }
        }
        maxW.click();

        if (!(driver.findElements(By.xpath(VIEWPAGE)).isEmpty())){
            passAge();
        }
    }

    private void passAge(){
        if (driver.findElement(By.xpath(AGEYEAR)).isDisplayed()){
            Select dropDown = new Select(driver.findElement(By.xpath(AGEYEAR)));
            dropDown.selectByValue(AGE);
        }
        driver.findElement(By.xpath(VIEWPAGE)).click();
    }

    public void downloadSteam() throws InterruptedException{
        driver.get(INSTALLSTEAM);
        driver.findElement(By.xpath(DOWNLOADSTEAM)).click();
        while (!file.exists()){
            Thread.sleep(1000);
        }
    }
}
