package pageobjects;

import menu.GameMenu;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.ReaderSettings;

import java.util.*;
public class MainPage {

    private static final String TOPSELLING = "//*[@id=\"tab_select_TopSellers\"]/div";
    private static final String MAXSALE = "//a[@class='tab_item   app_impression_tracked']/div[@class='discount_block tab_item_discount' and 2]/div[@class='discount_pct' and not(@class <= preceding-sibling::div/@class) and not(@class <=following-sibling::div/@class)]";
    private static final String AGEYEAR = "//select[@id='ageYear']";
    private static final String VIEWPAGE = "//*[@id=\"app_agegate\"]/div[1]/div[4]/a[1]/span";
    private static final String INSTALLSTEAM = "//a[@class='header_installsteam_btn_content']";
    private static final String DOWNLOADSTEAM = "//div[2]/a[@id='about_install_steam_link' and @class='btn_medium btn_green_white_innerfade' and 1]/span[1]";

    private WebDriver driver;
    private GameMenu gameMenu;
    //private WebDriverWait wait;
    //private FluentWait<WebDriver> fluentWait;

    public MainPage(WebDriver driver){
        this.driver = driver;
        gameMenu = new GameMenu(driver);
        //wait = new WebDriverWait(driver, 40);
        //fluentWait = new FluentWait<>(driver)
                //.ignoring(NoSuchElementException.class);
    }

    public void mainPage(){
        driver.get(ReaderSettings.urlName);
    }

    public void selectMenuAdventures(){
        //mainPage();
        gameMenu.selectItem(GameMenu.SubGameMenu.ACTION);
    }

    public void selectTopSellerGame(){
        driver.findElement(By.xpath(TOPSELLING)).click();
        //WebElement maxSale = driver.findElement(By.xpath(MAXSALE));
        //System.out.println(maxSale.getText());
        //driver.findElement(By.xpath(MAXSALE)).click();
        driver.get("https://store.steampowered.com/agecheck/app/360430/");

        if (!(driver.findElements(By.xpath(AGEYEAR)).isEmpty())){
            passAge();
        }
    }

    private void passAge(){
        Select dropDown = new Select(driver.findElement(By.xpath(AGEYEAR)));
        dropDown.selectByValue("1980");
        driver.findElement(By.xpath(VIEWPAGE)).click();
    }

    public void downloadSteam(){
        driver.findElement(By.xpath(INSTALLSTEAM)).click();
        driver.findElement(By.xpath(DOWNLOADSTEAM)).click();
    }


}
