package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class SearchTests extends CoreTestCase
{
    protected void setUp() throws Exception
    {
        super.setUp();
    }


    @Test
    public void testSearchAndVerifyResult()
    {
        String
                searchRequest = "Java",
                title = "Java (programming language)";
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.fillSearchField(searchRequest);
        SearchPageObject.waitForSearchResultByTitle(title);
    }


    @Test
    public void testCloseSearch()
    {
        String searchRequest = "Java";
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.fillSearchField(searchRequest);
        SearchPageObject.waitForSearchCancelButtonToAppear();
        SearchPageObject.clickSearchCancelButton();  // первый раз очищает введенный в поиск текст
        SearchPageObject.clickSearchCancelButton();  // второй раз выходит из экрана поиска
        SearchPageObject.waitForSearchCancelButtonToDisappear();
    }


    @Test
    public void testSearchAndClose() throws InterruptedException  // ex8
    {
        String searchRequest = "Python";
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.fillSearchField(searchRequest);
        int quantityOfSearchResults = SearchPageObject.getAmountOfFoundResults();
        assertTrue(
                "There is less than 2 search results",
                quantityOfSearchResults > 2
        );
        SearchPageObject.clickSearchCancelButton();
        SearchPageObject.assertThereIsNoResultAfterSearch();
    }


    @Test
    public void testSearchAndValidateResults()
    {
        String searchRequest = "Python";
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.fillSearchField(searchRequest);
        SearchPageObject.checkAllResultsContainSearchRequest(searchRequest);
    }


    @Test
    public void testCheckTextBeforeSearch()
    {
        if (Platform.getInstance().isIOS())
        {
            return;
        }
        String searchRequest = "Python";
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.checkTextInSearchField();
        SearchPageObject.fillSearchField(searchRequest);
    }


    @Test
    public void testAmountOfEmptySearch() throws InterruptedException
    {
        String searchRequest = "sgsgsgsgsgsg";
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.fillSearchField(searchRequest);
        SearchPageObject.waitForEmptyResultLabel();
        SearchPageObject.assertThereIsNoResultAfterSearch();
    }


    @Test
    public void testAmountOfNonEmptySearch()
    {
        String searchRequest = "Linkin Park discography";
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.fillSearchField(searchRequest);
        SearchPageObject.waitForSearchResultByTitle(searchRequest);
        int amountOfSearchResults = SearchPageObject.getAmountOfFoundResults();
        assertTrue(
                "There are too few search results",
                amountOfSearchResults > 0
        );
    }


    @Test
    public void testSearchAndCheckResultByTitleAndDescription()  // ex9*
    {
        String
                title = "Java",
                description = "Island of Indonesia";
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.fillSearchField(title);
        SearchPageObject.waitForSearchResultByTitleAndDescription(title, description);
    }
}