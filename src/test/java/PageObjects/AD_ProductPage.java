package PageObjects;

import java.util.LinkedHashMap;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import Utils.DBManager;
import Utils.ElementActions;

public class AD_ProductPage {
	WebDriver driver;
	Map<String, String> map = new LinkedHashMap<>();
	String search = "//*[@id='menuSearch']";
	String searchInp = "//*[@id='autoComplete']";
	String item = "(//*[text()='dynText'])[last()]";
	String addToCart = "//button[@name='save_to_cart']";
	String checkout = "//button[@id='checkOutPopUp']";
	String payment = "//button[@id='next_btn'][@data-ng-click='shippingDetails_next()']";
	String payUser = "//input[@name='safepay_username']";
	String payPass = "//input[@name='safepay_password']";
	String payNext = "pay_now_btn_SAFEPAY";
	String successPage = "//*[@translate='Thank_you_for_buying_with_Advantage']";
	String track = "//label[@id='trackingNumberLabel']";
	String orderId = "//label[@id='orderNumberLabel']";

	public AD_ProductPage(WebDriver driver) {
		this.driver = driver;
	}

	public void searchProduct() throws InterruptedException {

		ElementActions.dynamicClick(driver, By.xpath(search));
		ElementActions.sendData(driver, By.xpath(searchInp), DBManager.testData.get("SearchItem").trim());
		ElementActions.pause(1000);

		ElementActions.performVisibleWait(driver,
				By.xpath("(//*[text()='" + DBManager.testData.get("SearchItem") + "'])[last()]"));
		item = item.replace("dynText", DBManager.testData.get("SearchItem"));
		ElementActions.dynamicClick(driver, By.xpath(item));

	}

	public void addToCart() {
		ElementActions.dynamicClick(driver, By.xpath(addToCart));
		ElementActions.dynamicClick(driver, By.xpath(checkout));
	}

	public void completePayment() {
		ElementActions.dynamicClick(driver, By.xpath(payment));
		driver.findElement(By.xpath(payUser)).clear();
		ElementActions.sendData(driver, By.xpath(payUser), "TestUser");
		driver.findElement(By.xpath(payPass)).clear();
		ElementActions.sendData(driver, By.xpath(payPass), "Test1");
		ElementActions.dynamicClick(driver, By.id(payNext));

	}

	public Map placeOrder() throws InterruptedException {
		ElementActions.pause(3000);
		map.put("message", driver.findElement(By.xpath(successPage)).getText());
		map.put("trackId", driver.findElement(By.xpath(track)).getText());
		map.put("orderId", driver.findElement(By.xpath(orderId)).getText());
		return map;

	}
}
