package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import lib.Platform;

abstract public class ArticlePageObject extends MainPageObject
{
    protected static String
    TITLE,
    FOOTER,
    OPTIONS_BUTTON,
    ADD_TO_LIST_BUTTON,
    EXISTED_LIST_TPL,
    OK_ONBOARDING_BUTTON,
    MY_LIST_NAME_FIELD,
    CREATE_MY_LIST_BUTTON,
    CLOSE_ARTICLE_BUTTON;


    /* TEMPLATE METHODS */
    private static String getListXpathInCreatingList(String listName)
    {
        return EXISTED_LIST_TPL.replace("{LIST_NAME}", listName);
    }


    public ArticlePageObject(AppiumDriver driver)
    {
        super(driver);
    }


    public WebElement waitForTitleElement()
    {
        return this.waitForElementPresent(TITLE,
                "Cannot find the article title",
                15);
    }

    public String getArticleTitle()
    {
        if (Platform.getInstance().isAndroid()) {
            return waitForTitleElement().getAttribute("text");
        }
        else
        {
            return waitForTitleElement().getAttribute("name");
        }
    }


    public void swipeToFooter()
    {
        if (Platform.getInstance().isAndroid())
        {
            this.swipeUpToFindElement(FOOTER, "Cannot swipe to the footer using 50 swipes", 50);
        }
        else
        {
            this.swipeUpTillElementAppears(FOOTER, "Cannot swipe to the footer using 50 swipes", 50);
        }
    }


    public void createListAndAddArticle(String folderName) throws InterruptedException
    {

        this.checkElementIsMoving(OPTIONS_BUTTON);

        this.waitForElementAndClick(
                OPTIONS_BUTTON,
                "Cannot find the 'Option' button"
        );

        this.checkElementIsMoving(ADD_TO_LIST_BUTTON);

        this.waitForElementAndClick(
                ADD_TO_LIST_BUTTON,
                "Cannot find the 'Add to list' button"
        );


        this.waitForElementAndClick(
                OK_ONBOARDING_BUTTON,
                "Cannot find the 'Ok onboarding' button"
        );


        this.waitForElementAndClear(
                MY_LIST_NAME_FIELD,
                "Cannot find the 'Name for list' field"
        );

        this.waitForElementAndSendKeys(
                MY_LIST_NAME_FIELD,
                folderName,
                "Cannot find the 'Name for list' field"
        );

        this.waitForElementAndClick(
                CREATE_MY_LIST_BUTTON,
                "Cannot find the 'Create list' button"
        );
    }

    public void addArticleToExistedList(String listName) throws InterruptedException
    {
        this.checkElementIsMoving(OPTIONS_BUTTON);

        this.waitForElementAndClick(
                OPTIONS_BUTTON,
                "Cannot find the 'Option' button"
        );

        this.checkElementIsMoving(ADD_TO_LIST_BUTTON);

        this.waitForElementAndClick(
                ADD_TO_LIST_BUTTON,
                "Cannot find the 'Add to list' button"
        );
        String existedListXpath = getListXpathInCreatingList(listName);
        this.waitForElementAndClick(
                existedListXpath,
                "Cannot find created folder " + listName
        );
    }


    public void closeArticle ()
    {
        this.waitForElementAndClick(
                CLOSE_ARTICLE_BUTTON,
                "Cannot find the 'Close' button"
        );
    }


    public void checkTitleWithoutWait()
    {
        this.assertElementPresent(
                TITLE,
                "Cannot find the title"
        );
    }


    public void addFirstArticleToMyList() throws InterruptedException  // первый раз появляется подсказка, которую нужно закрыть
    {
        this.checkElementIsMoving(ADD_TO_LIST_BUTTON);
        this.waitForElementAndClick(ADD_TO_LIST_BUTTON, "Cannot find 'Add to list' button");
        this.waitForElementAndClick("id:places auth close", "Cannot close window");
    }


    public void addArticleToMyList() throws InterruptedException  // второй раз подсказки уже нет
    {
        this.checkElementIsMoving(ADD_TO_LIST_BUTTON);
        this.waitForElementAndClick(ADD_TO_LIST_BUTTON, "Cannot find 'Add to list' button");
    }


}
