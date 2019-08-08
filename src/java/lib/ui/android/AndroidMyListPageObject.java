package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListPageObject;

public class AndroidMyListPageObject extends MyListPageObject
{
    static {
        FOLDER_BY_NAME_TPL = "xpath://android.widget.TextView[@text='{FOLDER_NAME}']";
        ARTICLE_IN_MY_LISTS_BY_NAME_TPL = "xpath://android.widget.TextView[@text='{ARTICLE_TITLE}']";
        ARTICLE_TITLE_ON_ARTICLE_SCREEN_BY_NAME_TPL = "xpath://*[@resource-id='org.wikipedia:id/view_page_title_text'][@text='{ARTICLE_TITLE}']";
        SEARCH_BUTTON = "id:org.wikipedia:id/menu_search_lists";
    }


    public AndroidMyListPageObject(AppiumDriver driver)
    {
        super(driver);
    }
}
