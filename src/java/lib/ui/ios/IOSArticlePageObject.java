package lib.ui.ios;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IOSArticlePageObject extends ArticlePageObject
{
    static {
        TITLE = "id:Java (programming language)";
        FOOTER = "id:View article in browser";
        OPTIONS_BUTTON = ""; // isn't used
        ADD_TO_LIST_BUTTON = "id:Save for later";
        OK_ONBOARDING_BUTTON = ""; // isn't used
        MY_LIST_NAME_FIELD = ""; // isn't used
        CREATE_MY_LIST_BUTTON = ""; // isn't used
        CLOSE_ARTICLE_BUTTON = "xpath://XCUIElementTypeButton[@name='Back']";
    }


    public IOSArticlePageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
}
