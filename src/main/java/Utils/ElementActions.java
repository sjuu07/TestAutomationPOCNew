package Utils;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementActions {

	public static void performClickableWait(WebDriver driver,By path) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(30));
		wait.until(ExpectedConditions.elementToBeClickable(path));
	}
	
	public static void performVisibleWait(WebDriver driver,By path) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(path));
	}
	
	public static void pause(long milliseconds) throws InterruptedException {
		Thread.sleep(milliseconds);
	}
	
	
	public static void dynamicClick(WebDriver driver,By path) {
		performClickableWait(driver, path);
		boolean isClicked=false;
		
		
		for(int i=0;i<3;i++) {
		try {
			
				WebElement ele=driver.findElement(path);
				ele.click();
				isClicked=true;
				break;
			}
		catch(Exception e){
			System.out.println("Counter :"+i);
		}
		}
		if(isClicked==false) {
			WebElement ele=driver.findElement(path);
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", ele);
		}
		
	}
	
	public static void sendData(WebDriver driver,By path,String data) {
		driver.findElement(path).sendKeys(data);
	}
}
