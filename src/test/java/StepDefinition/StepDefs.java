package StepDefinition;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import CucumberExecutor.TestRunner;
import PageObjects.AD_ProductPage;
import PageObjects.LoginPage;
import Utils.DBManager;

public class StepDefs {
	public Scenario scenario;
	public static WebDriver driver ;
	
	LoginPage lp;
	AD_ProductPage pg;

	@Before
	public void initailSetUp(Scenario scenario) throws Exception {
		driver = TestRunner.driver;
		this.scenario = scenario;
		DBManager dbManager = new DBManager();
		dbManager.getExcelData("src\\test\\resources\\testData\\testdata.xlsx", "testdata", scenario.getName());
		System.out.println(DBManager.testData);

		lp = new LoginPage(driver);
		pg=new AD_ProductPage(driver);
	}
	
	@After
	public void tearDown() throws Exception {
		if(scenario.isFailed()) {
			scenario.attach(((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES), "image/png", "Error Screenshot");
			//driver.quit();
			driver=null;
		}
	}

	@Given("user opens the browser")
	public void userOpensTheBrowser() {
		driver.manage().window().maximize();
		System.out.println("Maximised");
	}

	@When("user navigates to url")
	public void userNavigatesToURL() {
		driver.get(DBManager.testData.get("url"));
		
	}

	@When("click menu to provide login credentials")
	public void click_menu_to_provide_login_credentials() throws InterruptedException {
		lp.setLogin();
	}

	@Then("verify the page title")
	public void thePageTitleShouldContain(){
		scenario.log("Page Title : "+lp.verifyTitle(DBManager.testData.get("username")));
	}
	//place order
	
	@Given("user is on application welcome page")
	public void user_is_on_application_welcome_page() {
		scenario.log("Page Title : "+lp.verifyTitle(DBManager.testData.get("username")));
	}

	@When("user search an item")
	public void user_search_an_item() throws InterruptedException {
	    pg.searchProduct();
	}

	@When("add item to cart")
	public void add_item_to_cart() {
	   pg.addToCart();
	}

	@When("complete the payment")
	public void complete_the_payment() {
	   pg.completePayment();
	}

	@Then("order is placed successfully")
	public void order_is_placed_successfully() throws InterruptedException {
		
	   Map<String, String> mp=pg.placeOrder();
	   for(Map.Entry<String,String> m:mp.entrySet()){
		   scenario.log(m.getKey()+":"+m.getValue());
		  
	   }
	}


}
