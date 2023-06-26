package Login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
public class Login {
	public static WebDriver driver;
	@BeforeMethod
	public void LoginSetup()
	{
		System.setProperty("webdriver.chrome.driver", "C:\\Webdriver\\chromedriver.exe");
		 driver = new ChromeDriver();	
		 driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			driver.get("http://addithr.azurewebsites.net/");
	}
	@Test(dataProvider="getData1")
	public void LoginInvalidData(String a, String b) throws InterruptedException
	{
		LoginAreas( a,b );
		 Thread.sleep(10000);
		String ErrorMessage1=driver.findElement(By.xpath("/html/body/div/div/div[1]/div[1]/div/div/div/div/div/div/div/div/div[2]")).getText();
	    String ErrorExpected1= "Login Failed";
	    Assert.assertEquals(ErrorMessage1, ErrorExpected1);
	    
	}
	@Test(dataProvider="getData")
	public void LoginCorrectData(String a, String b) throws InterruptedException
	{
		LoginAreas( a,b );
		String excpectedURL= "http://addithr.azurewebsites.net/dashboard";
		Thread.sleep(20000);
		String DashboarURL=driver.getCurrentUrl();
		Assert.assertEquals(DashboarURL, excpectedURL);
	}
	@Test(dataProvider="getData2")
	public void LoginBlanktData(String a, String b) throws InterruptedException
	{
		LoginAreas( a,b );
		Thread.sleep(15000);
		String ErrorMessage2=driver.findElement(By.xpath("//small[contains(text(),'This field is required')]")).getText();
	    String ErrorExpected2= "This field is required";
	  //  System.out.println(ErrorMessage2);
	    Assert.assertEquals(ErrorMessage2, ErrorExpected2);
	}
	@Test(dataProvider="getData3")
	public void LoginPasswordWithLessCharacter(String a, String b) throws InterruptedException
	{
		LoginAreas( a,b );
		Thread.sleep(15000);
		String ErrorMessage3=driver.findElement(By.xpath("//small[contains(text(),'Password must be at least 8 characters')]")).getText();
	    String ErrorExpected3= "Password must be at least 8 characters";
	  //  System.out.println(ErrorMessage3);
	    Assert.assertEquals(ErrorMessage3, ErrorExpected3);
	}
	@AfterMethod
	public void TearDown() {
		driver.quit();
	}
	public void LoginAreas(String ab, String ba)
	{
		driver.findElement(By.xpath("//input[@id='emailId']")).sendKeys(ab);
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys(ba);
		driver.findElement(By.xpath("//button[@id='loginButton']")).click();
	}
	@DataProvider
	public Object[][] getData()
	{
		// 3 combination with 2 values each
		Object[][] data= new Object[1][2];
		data[0][0]= "vilasjain147@gmail.com";
		data[0][1]= "Vilas147@";
		return data;
	}
	@DataProvider
	public Object[][] getData1()
	{
		// 3 combination with 2 values each
		Object[][] data= new Object[3][2];
		data[0][0]= "vilasjain147@gmai.com";
		data[0][1]= "Vilas147@";
		data[1][0]= "vilasjain9611@gmail.com";
		data[1][1]= "Vilas147";
		data[2][0]= "abd@gmai.com";
		data[2][1]= "Abd@12445";
		return data;
	}
	@DataProvider
	public Object[][] getData2()
	{
		// 3 combination with 2 values each
		Object[][] data= new Object[3][2];
		data[0][0]= " ";
		data[0][1]= "Vilas147@";
		data[1][0]= "vilasjain9611@gmail.com";
		data[1][1]= " ";
		data[2][0]= " ";
		data[2][1]= " ";
		return data;
	}
	@DataProvider
	public Object[][] getData3()
	{
		// 3 combination with 2 values each
		Object[][] data= new Object[2][2];
		data[0][0]= " ";
		data[0][1]= "Vilas1";
		data[1][0]= "vilasjain9611@gmail.com";
		data[1][1]= "1234";
		return data;
	}
	

}
