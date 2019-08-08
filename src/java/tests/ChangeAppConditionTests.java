package tests;

import lib.CoreTestCase;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;


public class ChangeAppConditionTests extends CoreTestCase
{
    protected void setUp() throws Exception
    {
        super.setUp();
    }


    @Test
    public void testRotateAfterSearchAndCheckTitle()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.fillSearchField("Java");
        SearchPageObject.waitForSearchResultByTitle("Java (programming language)");
        SearchPageObject.waitForSearchResultAndClick("Java (programming language)");
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        String titleBeforeRotation = ArticlePageObject.getArticleTitle();
        this.rotateToLandscape();
        String titleAfterRotation = ArticlePageObject.getArticleTitle();
        assertEquals(
                "Article title is different after rotation",
                titleBeforeRotation,
                titleAfterRotation
        );
        this.rotateToPortrait();
        String titleAfterSecondRotation = ArticlePageObject.getArticleTitle();
        assertEquals(
                "Article title is different after rotation",
                titleBeforeRotation,
                titleAfterSecondRotation
        );
    }


    @Test
    public void testTurnToBackgroundAfterSearchAndCheckResult()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.fillSearchField("Java");
        SearchPageObject.waitForSearchResultByTitle("Java (programming language)");
        this.backgroundApp(2);
        SearchPageObject.waitForSearchResultByTitle("Java (programming language)");
    }
}