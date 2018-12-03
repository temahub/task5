package webdriver;

import org.json.JSONException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import util.ReaderSettings;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class WebBrowserDriverInitialize {
    private static final String SETTINGS = "src/test/resources/settings.JSON";
    private static final String CHROME = "chrome";
    private static final String FIREFOX = "firefox";

    private volatile static WebDriver driver = null;

    public static WebDriver initialize() throws IOException, JSONException {
        ReaderSettings.readSettingsFromJSONFile(SETTINGS);
        if (driver == null){
            synchronized (WebDriver.class){
                if (ReaderSettings.browserName.equalsIgnoreCase(CHROME)){
                    ChromeOptions chOptions = new ChromeOptions();
                    HashMap<String, Object> chPref = new HashMap<>();
                    chPref.put("download.default_directory", System.getProperty("user.home"));
                    chPref.put("safebrowsing.enabled", "false");
                    chOptions.setExperimentalOption("prefs", chPref);

                    driver = new ChromeDriver(chOptions);

                }else if (ReaderSettings.browserName.equalsIgnoreCase(FIREFOX)){
                    FirefoxOptions fxOptions = new FirefoxOptions();
                    FirefoxProfile fxProfile = new FirefoxProfile();
                    fxProfile.setPreference("browser.download.folderList",2);
                    fxProfile.setPreference("browser.download.manager.showWhenStarting",false);
                    fxProfile.setPreference("browser.download.dir",System.getProperty("user.home"));
                    fxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk",
                            "application/vnd.microsoft.portable-executable");
                    fxOptions.setProfile(fxProfile);

                    driver = new FirefoxDriver(fxOptions);
                }
            }

            driver.manage().deleteAllCookies();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
            driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
        }
        return driver;
    }

    public void quit(){
        driver.quit();
        driver = null;
    }

    public void close(){
        driver.close();
        driver = null;
    }
}
