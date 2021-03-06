import org.openqa.selenium.chrome.ChromeDriver

driver = {
		System.setProperty("webdriver.chrome.driver",'src/test/resources/chromedriver/chromedriver.exe')
		new ChromeDriver()
}

waiting {
	timeout = 120
	retryInterval = 0.5
}

environments {

	if(System.properties.get("geb.build.reportsDir") != null)
		reportsDir  =  new File((String)System.properties.get("geb.build.reportsDir"))
	else
		reportsDir  = new File ("target/test-reports/geb")

	'local'
	 {

	 }


}
