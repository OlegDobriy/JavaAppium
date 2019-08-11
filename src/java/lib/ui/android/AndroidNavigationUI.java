package lib.ui.android;

import lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidNavigationUI extends NavigationUI
{
    static {
        MY_LIST_BUTTON = "xpath://android.widget.FrameLayout[@content-desc='My lists']";
    }


    public AndroidNavigationUI(RemoteWebDriver driver)
    {
        super(driver);
    }
}
