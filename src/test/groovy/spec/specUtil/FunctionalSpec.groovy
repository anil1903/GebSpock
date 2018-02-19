package spec.specUtil

import com.saucelabs.common.SauceOnDemandSessionIdProvider
import geb.spock.GebReportingSpec
import org.json.JSONObject
import org.json.JSONTokener
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import spock.lang.Ignore

@Ignore
class FunctionalSpec extends GebReportingSpec implements SauceOnDemandSessionIdProvider {
    private static final Logger logger = LoggerFactory.getLogger(FunctionalSpec.class);

    static String sessionId;
    public static HashMap<String, String> GlobalParam = new HashMap<>()


    @Override
    public String getSessionId() {
        return sessionId;
    }

    Map<String, String> readSetupJSONDataFile(String className) throws Exception {
        logger.debug("Start reading the setup data file");

        JSONObject jsonObject
        if (className.contains("spec")) {
            String path = className.substring(className.indexOf("spec") + 5)
            logger.info("Class path: " + path)

            //String fileSep = File.separator;
            String fileSep = "/";
            String dataFilePath = fileSep + "specData" + fileSep + path.replaceAll("\\.", fileSep) + "SetupData.json"
            String strFilePath = System.getProperty("user.dir") + "/src/test/resources" + dataFilePath

            JSONTokener tokenizer = new JSONTokener(new FileReader(strFilePath))
            jsonObject = new JSONObject(tokenizer);

            if (jsonObject != null) {
                logger.debug("Fetched JSON file to JSONObject: " + JSONObject);
                ////////////////////// - to put in system properties  - ////////////////////
                Iterator<String> it = jsonObject.keys();
                while (it.hasNext()) {
                    String pairs = it.next();
                    GlobalParam.put(pairs, jsonObject.get(pairs))
                }
                ////////////////////////////////////////////////////////////////////////////
            } else {
                logger.debug("Error: resolving the JSON file path: " + filePath);
            }
        } else {
            logger.info("Error in the passed spec's class name to the JSON file")
        }
        logger.debug("End reading the JSON setup data file");
        return GlobalParam
    }



}

