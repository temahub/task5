package menu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class GameMenu {

    private static final String MENU = "//*[@class='pulldown_desktop' and text()='Games']";
    private static final String MENUPATH = "//*[contains(text(),'%s')]";
    private WebDriver driver;

    public enum SubGameMenu{
        ACTION("Action"),
        ADVENTURE("Adventure"),
        CASUAL("Casual"),
        INDIE("Indie"),
        Massively_Multiplayer("Massively Multiplayer"),
        RACING("Racing"),
        RPG("RPG"),
        SIMULATION("Simulation"),
        SPORTS("Sports"),
        STRATEGY("Strategy");

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
        System.out.println(fullPath);
        return driver.findElement(By.xpath(fullPath));
    }

    public void selectItem(SubGameMenu subGameMenu){
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath(MENU))).build().perform();
        actions.moveToElement(MenuItem(subGameMenu)).click().build().perform();

        //MenuItem(subGameMenu).click();
    }
}
