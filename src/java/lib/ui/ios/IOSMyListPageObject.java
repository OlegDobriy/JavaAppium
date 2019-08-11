package lib.ui.ios;

import lib.ui.MyListPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IOSMyListPageObject extends MyListPageObject
{
    static
    {
        ARTICLE_IN_MY_LISTS_BY_NAME_TPL = "xpath://XCUIElementTypeLink[contains(@name, '{ARTICLE_TITLE}')]";
        ARTICLE_TITLE_ON_ARTICLE_SCREEN_BY_NAME_TPL = "";
    }


    public IOSMyListPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
}
