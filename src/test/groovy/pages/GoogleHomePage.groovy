package pages

import geb.Page
import org.slf4j.Logger
import org.slf4j.LoggerFactory


public class GoogleHomePage extends Page {

    private static final Logger logger = LoggerFactory.getLogger(GoogleHomePage.class);
    static url = "https://www.google.com.au/"

    static at = {title.equalsIgnoreCase("Google") }

    static content={
        edtSearch { $('input',name:"q")}
        btnSearch(to: GoogleSearchResultPage) { $('input',"value": "Google Search")}

    }

    def searchString(String strSearchString)
    {
        //Enter value in the Search box
        waitFor(10) {edtSearch.isDisplayed()}
        edtSearch.value(strSearchString)
        logger.info("Value " + strSearchString + " entered in the Edit Box")

        //Click on the Search button
        waitFor(10) {btnSearch.isDisplayed()}
        btnSearch.click()
        logger.info("Search button is clicked successfully")

    }
}
