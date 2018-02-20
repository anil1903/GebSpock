package spec.salesforce

import pages.GoogleHomePage
import pages.GoogleSearchResultPage
import spec.specUtil.FunctionalSpec
import spock.lang.Stepwise
import spock.lang.Unroll

@Stepwise
class SearchSpec extends FunctionalSpec {

    def setupSpec() {
        // Fetch data to read from json file(SearchSpec.json)
        readSetupJSONDataFile(SearchSpec.class.getName())
    }

    @Unroll
    def "Search a String in Google and Validate the result via #scenario"(){
        given:"We are on google home page"
        to GoogleHomePage

        when: "We perform a search"
        searchString(strSearchString)

        then: "Google Search Result page should be displayed"
        waitFor {at GoogleSearchResultPage}

        and:  "Google should return the search result"
        assert (validateSearchResult(strSearchResult) == true)

        where:
        scenario           | strSearchString                  | strSearchResult
        "via Where Clause" | "The Book Of Geb"                | "The Book Of Geb"
        "via JSON File"    | GlobalParam.get("SEARCH_STRING") | GlobalParam.get("SEARCH_RESULT")
    }

    def cleanupSpec() {
        // cleanSpec(cleanupIDs)
    }
}
