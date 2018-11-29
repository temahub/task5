package menu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class GameMenu {

    private static final String MENU = "//div[@id='genre_tab']/span[@class='pulldown' and 1]";
    private static final String MENUPATH = "//*[@id=\"genre_flyout\"]/div/a[%s]";
    private WebDriver driver;

    public enum SubGameMenu{
        ACTION("7"),
        ADVENTURE("8"),
        CASUAL("9"),
        INDIE("10"),
        Massively_Multiplayer("11"),
        RACING("12"),
        RPG("13"),
        SIMULATION("14"),
        SPORTS("15"),
        STRATEGY("16");

        private String subItem;

        SubGameMenu(String subItem){
            this.subItem = subItem;
        }

        public String getSubItem(){
            return subItem;
        }
    }

    public GameMenu(WebDriver driver){
        this.driver = driver;
    }

    private WebElement MenuItem(SubGameMenu subGameMenu){
        String fullPath = String.format(MENUPATH, subGameMenu.getSubItem());
        return driver.findElement(By.xpath(fullPath));
    }

    public void selectItem(SubGameMenu subGameMenu){
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath(MENU))).build().perform();
        MenuItem(subGameMenu).click();
    }
}
