package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class IOSSearchPageObject extends SearchPageObject
{
    static
    {
    SEARCH_FIELD_ON_MAIN_SCREEN = "xpath://XCUIElementTypeSearchField[@name='Search Wikipedia']";
    SEARCH_CANCEL_BUTTON = "id:Close";
    SEARCH_FIELD_ON_SEARCH_SCREEN = "xpath://XCUIElementTypeSearchField[@value='Search Wikipedia']";
    SEARCH_RESULT_BY_TITLE_TPL = "xpath://XCUIElementTypeLink[contains(@name, '{TITLE}')]";
    SEARCH_RESULT_ELEMENT = "xpath://XCUIElementTypeLink";
    SEARCH_RESULT_TITLE_ELEMENT = "xpath://XCUIElementTypeLink";
    SEARCH_EMPTY_RESULT_ELEMENT = "xpath://XCUIElementTypeStaticText[@name='No results found']";
    SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TPL = "xpath://*[*[@text='{TITLE}'] and *[@text='{DESCRIPTION}']]";
    }


    public IOSSearchPageObject(AppiumDriver driver)
    {
        super(driver);
    }
}
