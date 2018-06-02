package com.darkmatter.module;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.darkmatter.base.Wrapper;
import com.relevantcodes.extentreports.LogStatus;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

public class Todos {
	
	private static Todos todo;
	By todoTextBox = By.cssSelector(".new-todo");
	By todoList = By.xpath("//ul[@class='todo-list']/li");
	By Completed = By.partialLinkText("Completed");
	By completedTodoList = By.xpath("//ul[@class='todo-list filter-completed']/li");
	By clearCompletedTodoList = By.cssSelector(".clear-completed");
	By All = By.partialLinkText("All");
	By Active = By.partialLinkText("Active");
	int listSize;
	
	public static Todos getInstance() {
		if (todo == null) {
			todo = new Todos();
		}
		return todo;
	}
	Wrapper baseobj = Wrapper.getInstance();

	public void createToDoList(String item) throws InterruptedException
	{
		
		baseobj.getDriver().findElement(todoTextBox).sendKeys(item);
		Thread.sleep(5000);
		baseobj.getDriver().findElement(todoTextBox).sendKeys(Keys.ENTER);
	
			
	}
	public int getToDoListSize() {
		
		java.util.List<WebElement> element = baseobj.getDriver().findElements(todoList);
		listSize=element.size();
		return listSize;
		
	}
	
	public void setItemCompleted(int index ) throws InterruptedException
	{
		
		baseobj.getDriver().findElement(By.xpath("//ul[@class='todo-list']/li["+index+"]/div/input")).click();
					
	}
	
	public void getompletedItemList() throws InterruptedException
	{
		
		baseobj.getDriver().findElement(Completed).click();
				
	}
	
	public int getCompletedToDoListSize() {
		
		java.util.List<WebElement> element = baseobj.getDriver().findElements(By.xpath("//ul[@class='todo-list filter-completed']/li[1]"));
		System.out.println(element.size());
		listSize=element.size();
		return listSize;
		
	}
	
	
	public void clearompletedTodoList() throws InterruptedException
	{
		
		baseobj.getDriver().findElement(clearCompletedTodoList).click();
				
	}
	
	public void AllToDoList() throws InterruptedException
	{
		
		baseobj.getDriver().findElement(clearCompletedTodoList).click();
				
	}
	
	public int getAllActiveToDoListSize() throws InterruptedException {
		baseobj.getDriver().findElement(Active).click();
		Thread.sleep(5000l);
		java.util.List<WebElement> element = baseobj.getDriver().findElements(By.xpath("//li[@class='active']"));
		System.out.println(element.size());
		listSize=element.size();
		return listSize;
		
	}
	
}
