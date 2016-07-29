import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



public class WatchGumtreeDog {
	
	public WebDriver driver;
	WebElement newestResult ;
	WebElement location; 
	WebElement price;
	WebElement dateTime;
	
	public static void main(String[] args) throws InterruptedException {
			
		
		WatchGumtreeDog watchGumTree = new WatchGumtreeDog();
		
		watchGumTree.driver = new ChromeDriver(); 
		watchGumTree.driver.get("http://gumtree.co.uk");
	
		
		WebElement search = watchGumTree.driver.findElement(ByXPath.xpath("//*[@id=\"header-search-q\"]"));
		

		search.sendKeys(args[0]);
		search.submit();
		
		watchGumTree.newestResult  = watchGumTree.driver.findElement(ByXPath.xpath("//*[@id=\"srp-results\"]/div/div[2]/ul/li[1]/article/a/div[2]/h2"));
		watchGumTree.location = watchGumTree.driver.findElement(ByXPath.xpath("//*[@id=\"srp-results\"]/div/div[2]/ul/li[1]/article/a/div[2]/div/span"));
		watchGumTree.price = watchGumTree.driver.findElement(ByXPath.xpath("//*[@id=\"srp-results\"]/div/div[2]/ul/li[1]/article/a/div[2]/strong[1]"));
		watchGumTree.dateTime = watchGumTree.driver.findElement(ByXPath.xpath("//*[@id=\"srp-results\"]/div/div[2]/ul/li[1]/article/a/div[2]/strong[2]"));
		
		watchGumTree.waitForElementClickable(watchGumTree.location, "Location");
		
		System.out.println("------------VOLCA---------------");
		System.out.println("Name: "+watchGumTree.newestResult.getText());
		System.out.println("Location: " + watchGumTree.location.getText());
		System.out.println("Price: " + watchGumTree.price.getText());
		System.out.println("Time: " + watchGumTree.dateTime.getText());
		//watchGumTree.sendEmail();
		System.out.println("-------THATS ALL!!!!!!!!--------");
		//watchGumTree.driver.quit();
		
		
	}
	
	
	public void waitForElementClickable(WebElement element, String elementName) {
		 
		 try {
		 WebDriverWait wait = new WebDriverWait(driver,1);
		 element = wait.until(ExpectedConditions.elementToBeClickable(element));
		 }
		 catch (TimeoutException e){
			 
			System.out.println("Element "+elementName+" is not clickable for more than 5 seconds"); 
			 
		 }
		 
	}
	
	
	public void sendEmail(){
		
		final String username = "jakberan@gmail.com";
		final String password = "t1ltng0*";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		
		Session session = Session.getInstance(props,
				  new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				  });
		
		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("from-email@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse("jakberan@gmail.com"));
			message.setSubject("Testing Subject");
			message.setText("Dear Mail Crawler,"
				+ "\n\n No spam to my email, please!");

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	} 
		
		
		
		
		
	}
	


