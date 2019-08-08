package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.WelcomePageObject;
import org.junit.Test;

public class GetStartedTests extends CoreTestCase
{
    @Test
    public void testPassThroughWelcome()
    {
        if (Platform.getInstance().isAndroid())
        {
            return;
        }
        WelcomePageObject WelcomePage = new WelcomePageObject(driver);
        WelcomePage.waitForLearMoreLink();
        WelcomePage.clickNext();
        WelcomePage.waitForNewWaysToExplore();
        WelcomePage.clickNext();
        WelcomePage.waitForAddOrEditPreferredLanguages();
        WelcomePage.clickNext();
        WelcomePage.waitForLearnMoreAboutDataCollected();
        WelcomePage.clickGetStartedButton();
    }
}
