package IMS;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;



public class DummyTest {
	WebDriver bo;
	String bpath= "http://192.168.0.119:8000/IMS1/";
	
	@Parameters("browser")
	@BeforeMethod
	public void browser(String br) {
	
	 if (br.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\QA3\\Desktop\\prathamesh\\chromedriver.exe");
	bo= new ChromeDriver();
	bo.get(bpath);		
		}
	 
		else if(br.equalsIgnoreCase("firefox")){
			
			System.setProperty("webdriver.gecko.driver","C:\\Users\\QA3\\Desktop\\prathamesh\\geckodriver.exe");
			bo=new FirefoxDriver();
			bo.get(bpath);
			bo.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
				}
  }
	
	
	@AfterMethod
	public void bclose() {
		bo.quit();
	}
	
	//logout
		@Test(enabled=true, priority=6,groups="Administrator")
	  public void TC1_6() throws Exception  {
		bo.findElement(By.xpath("//input[@id='userid']")).sendKeys("1");	
		bo.findElement(By.xpath("//input[@id='password']")).sendKeys("admin");
		bo.findElement(By.xpath("//select[@id='type']")).click();
		bo.findElement(By.xpath("//input[@name='Submit']")).click();
		Thread.sleep(2000);
		bo.switchTo().frame("topFrame");
		bo.findElement(By.xpath("//a[contains(text(),'Logout')]")).click();
		Assert.assertEquals(bo.findElement(By.xpath("//strong[contains(text(),'LOGIN PAGE')]")).getText(), "LOGIN PAGE");
	}
		
}
	
	
	
	


