package lib.ui;

import io.appium.java_client.AppiumDriver;

public class WelcomePageObject extends MainPageObject
{
    private static final String
            STEP_LEARN_MORE_ABOUT_WIKIPEDIA_LINK = "id:Learn more about Wikipedia",
            STEP_NEW_WAYS_TO_EXPLORE_TITLE = "id:New ways to explore",
            STEP_ADD_OR_EDIT_PREFERRED_LANGUAGES_LINK = "id:Add or edit preferred languages",
            STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK = "id:Learn more about data collected",
            NEXT_LINK = "id:Next",
            GET_STARTED_BUTTON = "id:Get started",
            SKIP_BUTTON = "id:Skip";


    public WelcomePageObject(AppiumDriver driver)
    {
        super(driver);
    }


    public void waitForLearMoreLink()
    {
        this.waitForElementPresent(
                STEP_LEARN_MORE_ABOUT_WIKIPEDIA_LINK,
                "Cannot find 'Lear more about Wikipedia' link",
                10);
    }


    public void waitForNewWaysToExplore()
    {
        this.waitForElementPresent(
                STEP_NEW_WAYS_TO_EXPLORE_TITLE,
                "Cannot find 'New ways to explore' title",
                10);
    }


    public void waitForAddOrEditPreferredLanguages()
    {
        this.waitForElementPresent(
                STEP_ADD_OR_EDIT_PREFERRED_LANGUAGES_LINK,
                "Cannot find 'Add or edit preferred languages' link",
                10);
    }


    public void waitForLearnMoreAboutDataCollected()
    {
        this.waitForElementPresent(
                STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK,
                "Cannot find 'Learn more about data collected' link",
                10);
    }


    public void clickNext()
    {
        this.waitForElementAndClick(
                NEXT_LINK,
                "Cannot find and click 'Next' button",
                10);
    }

    public void clickGetStartedButton()
    {
        this.waitForElementAndClick(
                GET_STARTED_BUTTON,
                "Cannot find and click 'Get started' button",
                10);
    }


    public void clickSkip()
    {
        this.waitForElementAndClick(
                SKIP_BUTTON,
                "Cannot find and click 'Skip' button",
                10);
    }
}
