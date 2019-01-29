package YoutubeWebCrawl;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class youtubeCrawl {
	private static WebDriver driver = null;
	
	public static void main(String[] args) {
		
		
			driver = new ChromeDriver();   
		    driver.get("https://www.youtube.com/watch?v=QSR6hilYP4Y&t=17s");
		    
		    waitForLoad(driver);
		    JavascriptExecutor js = (JavascriptExecutor)driver;
		    String theTextIWant=null;
		    try {
		    	for(int i=0;i<=1;i++) {
		    		Thread.sleep(250);
		    		theTextIWant = js.executeScript("return arguments[0].innerHTML;",driver.findElement(By.xpath("//*[@id=\"count\"]/yt-view-count-renderer/span[1]"))).toString();}
		    		Thread.sleep(250);
		    		js.executeScript("arguments[0].click();",driver.findElement(By.xpath("//*[@id=\"buttons\"]/ytd-button-renderer/a")));
		    }catch(Exception ex) {}
		    
		    System.out.println(theTextIWant);
		   
		    
		    
		    
		   driver.quit();
		
		
	}
	
	

	public static void waitForLoad(WebDriver driver) {
	        ExpectedCondition<Boolean> pageLoadCondition = new
	                ExpectedCondition<Boolean>() {
	                    public Boolean apply(WebDriver driver) {
	                        return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
	                    }
	                };
	        WebDriverWait wait = new WebDriverWait(driver, 50);
	        wait.until(pageLoadCondition);
	        
	 }
	
	
}	

