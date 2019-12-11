package IMS;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest {
	
	WebDriver bo;
	String bpath= "http://192.168.0.119:8000/IMS1/";
	
	
	@BeforeMethod
	public void b() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\QA6\\Downloads\\chromedriver1.exe");
		bo= new ChromeDriver();
		bo.get(bpath);
	}
	@AfterMethod
	public void bclose() {
		bo.quit();
	}
	
	//Valid Userid, Valid Password
	@Test(enabled=true, priority=1,groups="main")
  public void TC1_1() throws Exception  {
	bo.findElement(By.xpath("//input[@id='userid']")).sendKeys("1");	
	bo.findElement(By.xpath("//input[@id='password']")).sendKeys("admin");
	bo.findElement(By.xpath("//select[@id='type']")).click();
	bo.findElement(By.xpath("//input[@name='Submit']")).click();
	Thread.sleep(4000);
	Assert.assertEquals(bo.getTitle(),"Untitled Document");
	bo.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
  }
	
	//Valid Userid, Invalid Password
	@Test(enabled=true, priority=2,groups="main")
	  public void TC1_2() throws Exception{
		bo.findElement(By.xpath("//input[@id='userid']")).sendKeys("1");	
		bo.findElement(By.xpath("//input[@id='password']")).sendKeys("xxxxx");	
		bo.findElement(By.xpath("//input[@name='Submit']")).click();
		Thread.sleep(4000);
		Assert.assertEquals(bo.findElement(By.xpath("//em[contains(text(),'INVALID UserID/Password')]")).getText(),"INVALID UserID/Password");
		bo.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		
	}
	//InValid Userid, valid Password
	@Test(enabled=true, priority=3,groups="main")
	  public void TC1_3() throws Exception{
		bo.findElement(By.xpath("//input[@id='userid']")).sendKeys("123");	
		bo.findElement(By.xpath("//input[@id='password']")).sendKeys("admin");	
		bo.findElement(By.xpath("//input[@name='Submit']")).click();
		Thread.sleep(4000);
		Assert.assertEquals(bo.findElement(By.xpath("//em[contains(text(),'INVALID UserID/Password')]")).getText(),"INVALID UserID/Password");
		bo.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
	
	}
	
	//InValid Userid, Invalid Password
	@Test(enabled=true, priority=4,groups="main")
	  public void TC1_4() throws Exception{
		bo.findElement(By.xpath("//input[@id='userid']")).sendKeys("123");	
		bo.findElement(By.xpath("//input[@id='password']")).sendKeys("xxxxx");	
		bo.findElement(By.xpath("//input[@name='Submit']")).click();
		Thread.sleep(4000);
		Assert.assertEquals(bo.findElement(By.xpath("//em[contains(text(),'INVALID UserID/Password')]")).getText(),"INVALID UserID/Password");
		bo.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
	}
	
	
	//Reset Button
	@Test(enabled=true, priority=5,groups="main")
	  public void TC1_5() throws Exception{
		bo.findElement(By.xpath("//input[@id='userid']")).sendKeys("1");	
		bo.findElement(By.xpath("//input[@id='password']")).sendKeys("admin");	
		bo.findElement(By.xpath("//input[@name='Submit2']")).click();
		Thread.sleep(4000);
	Assert.assertEquals(bo.findElement(By.xpath("//input[@id='userid']")).getText(),"");
		bo.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
	}
}
