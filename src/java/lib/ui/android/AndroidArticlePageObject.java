package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class AndroidArticlePageObject extends ArticlePageObject
{
    static {
        TITLE = "id:org.wikipedia:id/view_page_title_text";
        FOOTER = "id:org.wikipedia:id/page_external_link";
        OPTIONS_BUTTON = "xpath://android.widget.ImageView[@content-desc='More options']";
        ADD_TO_LIST_BUTTON = "xpath://android.widget.TextView[@text='Add to reading list']";
        EXISTED_LIST_TPL = "xpath://android.widget.TextView[@text='{LIST_NAME}']";
        OK_ONBOARDING_BUTTON = "id:org.wikipedia:id/onboarding_button";
        MY_LIST_NAME_FIELD = "id:org.wikipedia:id/text_input";
        CREATE_MY_LIST_BUTTON = "xpath://android.widget.Button[@text='OK']";
        CLOSE_ARTICLE_BUTTON = "xpath://android.widget.ImageButton[@content-desc='Navigate up']";
    }


    public AndroidArticlePageObject(AppiumDriver driver)
    {
        super(driver);
    }
}
