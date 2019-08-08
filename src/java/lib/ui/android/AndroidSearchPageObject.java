package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class AndroidSearchPageObject extends SearchPageObject
{
    static
    {
        SEARCH_FIELD_ON_MAIN_SCREEN = "xpath://android.widget.TextView[@text='Search Wikipedia']";
        SEARCH_CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn";
        SEARCH_FIELD_ON_SEARCH_SCREEN = "xpath://*[@resource-id='org.wikipedia:id/search_src_text'][@text='Search…']";
        SEARCH_LINE_WITHOUT_TEXT = "id:org.wikipedia:id/search_src_text";
        SEARCH_RESULT_BY_TITLE_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='{TITLE}']";
        SEARCH_RESULT_ELEMENT = "xpath://android.widget.LinearLayout[@resource-id='org.wikipedia:id/page_list_item_container']";
        SEARCH_RESULT_TITLE_ELEMENT = "xpath://android.widget.TextView[@resource-id='org.wikipedia:id/page_list_item_title']";
        SEARCH_EMPTY_RESULT_ELEMENT = "xpath://*[@text='No results found']";
        SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TPL = "xpath://*[*[@text='{TITLE}'] and *[@text='{DESCRIPTION}']]";
    }

    public AndroidSearchPageObject(AppiumDriver driver)
    {
        super(driver);
    }

}
