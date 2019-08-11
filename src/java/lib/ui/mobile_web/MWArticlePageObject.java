package lib.ui.mobile_web;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWArticlePageObject extends ArticlePageObject
{
    static {
        TITLE = "css:#content h1";
        FOOTER = "css:.minerva-footer";
        OPTIONS_BUTTON = "";
        ADD_TO_LIST_BUTTON = "css:#page-actions a#ca-watch.mw-ui-icon-mf-watch";
        REMOVE_FROM_LIST_BUTTON = "css:#page-actions a#ca-watch.mw-ui-icon-mf-watched";
        OK_ONBOARDING_BUTTON = "";
        MY_LIST_NAME_FIELD = "";
        CREATE_MY_LIST_BUTTON = "";
        CLOSE_ARTICLE_BUTTON = "";
    }


    public MWArticlePageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
}
