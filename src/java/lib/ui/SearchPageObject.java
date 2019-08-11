package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;

import static org.junit.Assert.assertEquals;

abstract public class SearchPageObject extends MainPageObject
{
    protected static String
    SEARCH_FIELD_ON_MAIN_SCREEN,
    SEARCH_CANCEL_BUTTON,
    SEARCH_FIELD_ON_SEARCH_SCREEN,
    SEARCH_LINE_WITHOUT_TEXT,
    SEARCH_RESULT_BY_TITLE_TPL,
    SEARCH_RESULT_ELEMENT,
    SEARCH_RESULT_TITLE_ELEMENT,
    SEARCH_EMPTY_RESULT_ELEMENT,
    SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TPL;


    /* TEMPLATE METHODS */
    private static String getSearchResultByTitle(String substring)
    {
        return SEARCH_RESULT_BY_TITLE_TPL.replace("{TITLE}", substring);
    }

    private static String getSearchResultByTitleAndSubtitle(String title, String description)
    {
        return SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TPL
                .replace("{TITLE}", title)
                .replace("{DESCRIPTION}", description);
    }
    /* TEMPLATE METHODS */


    public SearchPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }


    public void initSearchInput()
    {
        this.waitForElementAndClick(SEARCH_FIELD_ON_MAIN_SCREEN,
                "Cannot find the search field on main screen");
        this.waitForElementPresent(SEARCH_FIELD_ON_SEARCH_SCREEN,
                "Cannot find the search field on search screen");
    }


    public void fillSearchField(String searchRequest)
    {
        this.waitForElementAndSendKeys(SEARCH_FIELD_ON_SEARCH_SCREEN, searchRequest, "Cannot fill the search field on search screen");
    }

    public void waitForSearchCancelButtonToAppear()
    {
        waitForElementPresent(SEARCH_CANCEL_BUTTON, "Cannot find the 'Search Cancel' button on search screen");
    }


    public void waitForSearchCancelButtonToDisappear()
    {
        waitForElementNotPresent(SEARCH_CANCEL_BUTTON, "'Search Cancel' button is still on search screen");
    }


    public void clickSearchCancelButton()
    {
        waitForElementAndClick(SEARCH_CANCEL_BUTTON, "Cannot click the 'Search Cancel' button on search screen");
    }


    public void waitForSearchResultByTitle(String title)
    {
        String searchResultAfterReplace = getSearchResultByTitle(title);
        this.waitForElementPresent(searchResultAfterReplace, "Cannot find the search result: " + title);
    }


    public void waitForSearchResultAndClick(String searchResult)
    {
        String searchResultAfterReplace = getSearchResultByTitle(searchResult);
        this.waitForElementAndClick(searchResultAfterReplace, "Cannot click to the search result: " + searchResult);
    }


    public int getAmountOfFoundResults()
    {
        this.waitForElementPresent(
                SEARCH_RESULT_ELEMENT,
                "Cannot find the search results");
        return this.getAmountOfElements(
                SEARCH_RESULT_ELEMENT);
    }

    public void waitForEmptyResultLabel()
    {
        this.waitForElementPresent(
                SEARCH_EMPTY_RESULT_ELEMENT,
                "'No results' label was't found"
        );
    }


    public void checkTextInSearchField()
    {
        WebElement element = this.waitForElementPresent(
                SEARCH_LINE_WITHOUT_TEXT,
                "Cannot find the search field on search screen"
        );

        String textInTheSearchField = element.getAttribute("text");

        assertEquals(
                "There is no 'Search…' word in the search field",
                "Search…",
                textInTheSearchField
        );
    }


    public void assertThereIsNoResultAfterSearch() throws InterruptedException
    {
        this.waitForElementNotPresent(
                SEARCH_RESULT_ELEMENT,
                "There is still at least one search result for the search"
        );
    }


    public List<WebElement> getListOfSearchResults()
    {
        return this.waitForElementsAndGetList(
                SEARCH_RESULT_TITLE_ELEMENT,
                "Cannot find at least one search result",
                15
        );
    }


    public void checkAllResultsContainSearchRequest(String searchRequest)
    {
        List<WebElement> list = getListOfSearchResults();
        int wrongResultCount = 0;
        List<String> listOfResults = this.getTextFromElementsInList(list);

        for (String listItem : listOfResults) {
            if (listItem.contains(searchRequest)) {
                assert true;
            }
            else {
                System.out.println("The title '" + listItem + "' does not contain the word '" + searchRequest + "'");
                ++wrongResultCount;
            }
        }
        if (wrongResultCount > 0) {
            throw new java.lang.Error("Not all results contain the word '" + searchRequest + "'. Quantity of wrong topics is " + wrongResultCount);
        }
    }


    public void waitForSearchResultByTitleAndDescription(String title, String description)
    {
        String searchResultXpath = getSearchResultByTitleAndSubtitle(title, description);
        this.waitForElementPresent(
                searchResultXpath,
                "Cannot find the search result title: " + title + ". Search result description: " + description,
        15
        );
    }
}
