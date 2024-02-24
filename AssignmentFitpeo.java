package basicseleniumprog;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AssignmentFitpeo {
public static void main(String[] args) throws InterruptedException {
	
  WebDriverManager.chromedriver().setup();
  ChromeOptions co=new ChromeOptions();
  co.addArguments("--remote-allow-origins=*");

  WebDriver driver= new ChromeDriver(co);
  driver.manage().window().maximize();
  driver.get("https://www.flipkart.com/");
  
  //iPad Search
  WebElement searchBox = driver.findElement(By.name("q"));
  searchBox.sendKeys("ipad");
  
  Actions act=new Actions(driver);
  Thread.sleep(2000);
  act.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).click().build().perform();
  searchBox.submit();

  //First Filter
  WebElement displaySize = driver.findElement(By.xpath("//*[text()='Display Size']"));
  Thread.sleep(2000);
  displaySize.click();
  Thread.sleep(2000);
  WebElement checkBox = driver.findElement(By.className("_24_Dny"));
  checkBox.click();
  Thread.sleep(2000);
  
  //Second Filter using Scroll down feature
  JavascriptExecutor js = (JavascriptExecutor) driver;
  js.executeScript("window.scrollBy(0,500)");
  Thread.sleep(4000);
  WebElement ram = driver.findElement(By.xpath("//*[text()='RAM']"));
  ram.click();
  Thread.sleep(2000);
  WebElement ramSize = driver.findElement(By.xpath("//*[text()='8 GB and Above']"));
  Thread.sleep(2000);
  ramSize.click();
  Thread.sleep(2000);
  
  //Third Filter using Scroll down feature
  JavascriptExecutor js1 = (JavascriptExecutor) driver;
  js1.executeScript("window.scrollBy(0,500)");
  Thread.sleep(2000);
  WebElement customerRating = driver.findElement(By.xpath("//*[text()='4★ & above']"));
  customerRating.click();
  Thread.sleep(2000);
 
  String firstWindowId=driver.getWindowHandle();
  System.out.println("Main Window="+firstWindowId);
 
  //Scroll to product using Action class
  Actions actions = new Actions(driver);
  actions.sendKeys(Keys.PAGE_DOWN).build().perform();
  WebElement iPad = driver.findElement(By.xpath("//*[text()='Apple iPad Pro 2021 (5th Generation) 16 GB RAM 2 TB ROM 12.9 inches with Wi-Fi Only (Space Grey)']"));
  Thread.sleep(2000);
  iPad.click();
	 
  //To get new Tab using windowHandle
  Set<String> allWindowId = driver.getWindowHandles();
    for(String productPage:allWindowId)
    {
    	if(!productPage.equals(firstWindowId))
    	{
    		driver.switchTo().window(productPage);
    		break;
    	}
    }
    
 //Buy button 
  WebElement buyButton = driver.findElement(By.xpath("//*[text()='Buy Now']"));
  buyButton.click();
  Thread.sleep(3000);
  
  //Email address and Mobile Number
  WebElement enterEmail = driver.findElement(By.xpath("//*[@type='text']"));
  enterEmail.sendKeys("abc@gmail.com");
  Thread.sleep(2000);
  WebElement continue1 = driver.findElement(By.xpath("//*[@type='submit']"));
  continue1.click();
  Thread.sleep(2000);

  WebElement enterPhoneNumber = driver.findElement(By.xpath("(//*[@type='text'])[2]"));
  enterPhoneNumber.sendKeys("9876543213");
  Thread.sleep(3000);
  
  String title2=driver.getTitle();
  System.out.println(title2);
  
  //Assertion
  Assert.assertTrue(title2.contains("Flipkart.com"), "Expected substring is not found within the actual text");
  
  driver.quit();

  
}
}

