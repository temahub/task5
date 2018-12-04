package util;

import org.openqa.selenium.WebElement;

public class WebElementText {
    private static final String REGEXINTEGER = "[^0-9]";

    public static String extractDigitToString(WebElement webElement){
        return webElement.getText().replaceAll(REGEXINTEGER, "");
    }
}
