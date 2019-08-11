package lib.ui.mobile_web;

import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;


public class MWSearchPageObject extends SearchPageObject
{
    static
    {
        SEARCH_FIELD_ON_MAIN_SCREEN = "css:button#searchIcon";
        SEARCH_CANCEL_BUTTON = "css:div>button.cancel";
        SEARCH_FIELD_ON_SEARCH_SCREEN = "css:form>input[type='search']";
        SEARCH_RESULT_BY_TITLE_TPL = "xpath://a[contains(@data-title, '{TITLE}')]";
        SEARCH_RESULT_ELEMENT = "css:a.title";
        SEARCH_RESULT_TITLE_ELEMENT = "xpath://li[@title]";
        SEARCH_EMPTY_RESULT_ELEMENT = "xpath://p[@class='with-results'][@style='display: none;']";
        SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TPL = "id:not_defined";
    }


    public MWSearchPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
}

