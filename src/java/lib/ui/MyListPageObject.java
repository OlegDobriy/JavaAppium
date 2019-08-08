package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;


abstract public class MyListPageObject extends MainPageObject
{

    protected static String
            FOLDER_BY_NAME_TPL,
            ARTICLE_IN_MY_LISTS_BY_NAME_TPL,
            ARTICLE_TITLE_ON_ARTICLE_SCREEN_BY_NAME_TPL,
            SEARCH_BUTTON;


    /* TEMPLATE METHODS */
    private static String getFolderXpathInMyLists(String folderName)
    {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", folderName);
    }


    private static String getArticleXpathInMyLists(String articleTitle)
    {
        return ARTICLE_IN_MY_LISTS_BY_NAME_TPL.replace("{ARTICLE_TITLE}", articleTitle);
    }

    private static String getArticleXpathOnArticleScreen(String articleTitle)
    {
        return ARTICLE_TITLE_ON_ARTICLE_SCREEN_BY_NAME_TPL.replace("{ARTICLE_TITLE}", articleTitle);
    }

    /* TEMPLATE METHODS */


    public MyListPageObject(AppiumDriver driver)
    {
        super(driver);
    }

    public void openMyFolderByName (String folderName) throws InterruptedException
    {
        this.checkElementIsMoving(SEARCH_BUTTON);

        String folderXpath = getFolderXpathInMyLists(folderName);
        this.waitForElementAndClick(
                folderXpath,
                "Cannot find the created folder"
        );
    }

    public void swipeArticleToDelete(String articleTitle)  // в папке выводится title фактического результата поиска, а не запрос
    {
        this.waitForArticleToAppearInMyLists(articleTitle);
        String articleXpath = getArticleXpathInMyLists(articleTitle);
        this.swipeElementToLeft(
                articleXpath,
                "Cannot find the saved article"
        );
        if (Platform.getInstance().isIOS())
        {
            this.clickElementToTheRightUpperCorner(articleXpath, "Cannot find saved article: " + articleTitle);
        }
        this.waitForArticleToDisappearInMyLists(articleTitle);
    }


    public void checkArticleInMyList(String articleTitle)
    {
        this.waitForArticleToAppearInMyLists(articleTitle);
    }


    public void waitForArticleToAppearInMyLists(String articleTitle)  // в папке выводится title фактического результата поиска, а не запрос
    {
        String articleXpath = getArticleXpathInMyLists(articleTitle);
        this.waitForElementPresent(
                articleXpath,
                "Article isn't presented. Article name is " + articleTitle);
    }

    public void waitForArticleToDisappearInMyLists(String articleName)
    {
        String articleXpath = getArticleXpathInMyLists(articleName);
        this.waitForElementNotPresent(
                articleXpath,
                "Article is still present. Article name is " + articleName);
    }

    public void checkArticleTitleByName(String articleTitle)
    {
        String articleXpath = getArticleXpathInMyLists(articleTitle);
        this.waitForElementAndClick(
                articleXpath,
                "Article doesn't present in the folder. Expected article: " + articleTitle);
        String articleTitleXpath = getArticleXpathOnArticleScreen(articleTitle);
        this.waitForElementPresent(
                articleTitleXpath,
                "Cannot find the article title. Expected article: " + articleTitle,
                15
        );
    }
}
