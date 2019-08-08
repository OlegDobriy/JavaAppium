package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;


public class MyListTests extends CoreTestCase
{
    protected void setUp() throws Exception
    {
        super.setUp();
    }


    @Test
    public void testAddFirstArticleToListAndDelete() throws InterruptedException
    {
        String
                searchRequest = "Java",
                folderName = "test: add to my list and delete";
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.fillSearchField(searchRequest);
        SearchPageObject.waitForSearchResultByTitle(searchRequest);
        SearchPageObject.waitForSearchResultAndClick(searchRequest);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        if (Platform.getInstance().isAndroid())
        {
            ArticlePageObject.createListAndAddArticle(folderName);
        }
        else
        {
            ArticlePageObject.addFirstArticleToMyList();
        }
        ArticlePageObject.closeArticle();
        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.openMyList();
        Thread.sleep(1000);  // ВТОРОЙ слип, без этого иногда тапает не по папке, а по кнопке My list
        MyListPageObject MyListPageObject = MyListPageObjectFactory.get(driver);
        if (Platform.getInstance().isAndroid())
        {
            MyListPageObject.openMyFolderByName(folderName);
        }
        MyListPageObject.swipeArticleToDelete(searchRequest);
    }


    @Test
    public void testAddTwoArticlesToListAndDeleteOne() throws InterruptedException  // ex8, ex11
    {
        String
                SearchRequest = "Java",
                firstResult = "Java (programming language)",
                secondResult = "JavaScript",
                folderName = "testAddTwoArticlesToListAndDeleteOne";
        // first article
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.fillSearchField(SearchRequest);
        SearchPageObject.waitForSearchResultByTitle(firstResult);
        SearchPageObject.waitForSearchResultAndClick(firstResult);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        if (Platform.getInstance().isAndroid())
        {
            ArticlePageObject.createListAndAddArticle(folderName);
        }
        else
        {
            ArticlePageObject.addFirstArticleToMyList();
        }
        ArticlePageObject.closeArticle();
        // second article
        SearchPageObject.initSearchInput();
        if (Platform.getInstance().isAndroid())
        {
            SearchPageObject.fillSearchField(SearchRequest);
        }
        SearchPageObject.waitForSearchResultByTitle(secondResult);
        SearchPageObject.waitForSearchResultAndClick(secondResult);
        if (Platform.getInstance().isAndroid())
        {
            ArticlePageObject.addArticleToExistedList(folderName);
        }
        else
        {
            ArticlePageObject.addArticleToMyList();
        }
        ArticlePageObject.closeArticle();
        // my list
        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.openMyList();
        MyListPageObject MyListPageObject = MyListPageObjectFactory.get(driver);
        if (Platform.getInstance().isAndroid())
        {
            MyListPageObject.openMyFolderByName(folderName);
        }
        MyListPageObject.swipeArticleToDelete(firstResult);
        MyListPageObject.checkArticleInMyList(secondResult);
    }
}