package pages

import geb.Page
import org.slf4j.Logger
import org.slf4j.LoggerFactory


public class GoogleSearchResultPage extends Page {

    static at = {title.contains("Google Search") }

    private static final Logger logger = LoggerFactory.getLogger(GoogleSearchResultPage.class);

    static content={
        lnkResult { strResult -> $('a',text: strResult)}
    }

    def validateSearchResult(String strResult){
        boolean bFlag = false
       //return if the Search result is displayed
        if (lnkResult(strResult).isDisplayed()){
            logger.info("Search Result is displayed")
            bFlag = true
        }else{
            logger.error("Search Result is not displayed")
        }
       return bFlag

    }
}
