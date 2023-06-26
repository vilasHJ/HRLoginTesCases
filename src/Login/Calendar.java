package Login;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class Calendar extends Login {
	public void datePicker(String a) throws InterruptedException {
		 Actions actions2 = new Actions(driver);
		 Actions actions1 = new Actions(driver);
 		actions1.moveToElement( driver.findElement(By.xpath("//div[@class='rmdp-header-values']/span[2]"))).perform();
 		Thread.sleep(1000);	
 		String ad= a;
 		String[] ab= ad.split("/");
 		String de = ab[0];
 		String nj = ab[1];
 		String lm = ab[2];
 	String monthname=DateConverter(nj);
 		int t=Integer.parseInt(lm);
 	String cd=	driver.findElement(By.xpath("//div[@class='rmdp-header-values']/span[2]")).getText();
 	int m=Integer.parseInt(cd);
 	if(t>m)
 	{
 		while(!driver.findElement(By.xpath("//div[@class='rmdp-header-values']/span[2]")).getText().contains(lm))
 			{
           driver.findElement(By.xpath("//span[@class='rmdp-arrow-container rmdp-right ']")).click();
 			}
 	}
 	else
 	{
 		while(!driver.findElement(By.xpath("//div[@class='rmdp-header-values']/span[2]")).getText().contains(lm))
			{
       driver.findElement(By.xpath("//span[@class='rmdp-arrow-container rmdp-left ']")).click();
			}
 	}
	driver.findElement(By.xpath("//div[@class='rmdp-header-values']/span[1]")).click();
	List<WebElement> Month = driver.findElements(By.xpath("//div[@class='rmdp-ym']/div/span"));
for(int mo=0;mo<Month.size();mo++)
{	 
	   String mn= Month.get(mo).getText();
if(mn.equals(monthname))
	  {
		  Month.get(mo).click();
	  }
}
List<WebElement> Date= driver.findElements(By.xpath("//div[@class='rmdp-day']/span"));
for(int da=0;da<Date.size();da++)
{
	 String dy=  Date.get(da).getText();
	 if(dy.equals(de))
	 {
		 Date.get(da).click();
		break;
		 }
}	
driver.findElement(By.xpath("//button[@class='rmdp-button rmdp-action-button'][1]")).click();
	}

	public String DateConverter(String abc)
	{
		String[] ab = abc.split("/");
	String ce= ab[0];
	String ef = ab[1];
	String gh = ab[2];
	String month= null;
	if(ef.equals("01"))
	{
		month= "January";
	}
	if(ef.equals("02"))
	{
		month= "February";
	}
	if(ef.equals("03"))
	{
		month= "March";
	}
	if(ef.equals("04"))
	{
		month= "April";
	}
	if(ef.equals("05"))
	{
		month= "May";
	}
	if(ef.equals("06"))
	{
		month= "June";
	}
	if(ef.equals("07"))
	{
		month= "July";
	}
	if(ef.equals("08"))
	{
		month= "August";
	}
	if(ef.equals("09"))
	{
		month= "Seprember";
	}
	if(ef.equals("10"))
	{
		month= "October";
	}
	if(ef.equals("11"))
	{
		month= "November";
	}
	
	if(ef.equals("12"))
	{
		month= "December";
	}
		return month;	
	}

}
