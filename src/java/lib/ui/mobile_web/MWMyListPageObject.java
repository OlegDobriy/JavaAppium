package lib.ui.mobile_web;

import lib.ui.MyListPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWMyListPageObject extends MyListPageObject
{
    static
    {
    ARTICLE_IN_MY_LISTS_BY_NAME_TPL = "xpath://ul//h3[text()='{ARTICLE_TITLE}']";
    ARTICLE_TITLE_ON_ARTICLE_SCREEN_BY_NAME_TPL = "xpath://h1[@id='section_0'][text()='{ARTICLE_TITLE}']";
    REMOVE_FROM_LIST_BUTTON_TPL = "xpath://ul//h3[text()='{ARTICLE_TITLE}']/../../div[contains(@class, 'watched')]";
    }


    public MWMyListPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
}

