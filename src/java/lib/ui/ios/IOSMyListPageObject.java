package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListPageObject;

public class IOSMyListPageObject extends MyListPageObject
{
    static {
        ARTICLE_IN_MY_LISTS_BY_NAME_TPL = "xpath://XCUIElementTypeLink[contains(@name, '{ARTICLE_TITLE}')]";
        ARTICLE_TITLE_ON_ARTICLE_SCREEN_BY_NAME_TPL = "";
    }


    public IOSMyListPageObject(AppiumDriver driver)
    {
        super(driver);
    }
}
