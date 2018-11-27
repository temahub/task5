package pageobjects;

import menu.GameMenu;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.ReaderSettings;

import java.util.*;

public class MainPage {

    private WebDriver driver;
    private GameMenu gameMenu;
    private WebDriverWait wait;
    private FluentWait<WebDriver> fluentWait;

    public MainPage(WebDriver driver){
        this.driver = driver;
        gameMenu = new GameMenu(driver);
        wait = new WebDriverWait(driver, 10);
        fluentWait = new FluentWait<>(driver)
                .ignoring(NoSuchElementException.class);
    }

    public void mainPage(){
        driver.get(ReaderSettings.urlName);
    }

    public void selectMenuAdvenures(){
        gameMenu.selectItem(GameMenu.SubGameMenu.ACTION);
    }
}
