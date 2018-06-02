package com.darkmatter.test;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.darkmatter.base.BaseTest;
import com.darkmatter.base.Wrapper;
import com.darkmatter.module.Todos;
import com.relevantcodes.extentreports.LogStatus;

public class TodosTestCase extends BaseTest {

	Todos TD = Todos.getInstance();
	Wrapper WA = Wrapper.getInstance();
	HashMap<String, String> todoListItemMap = new HashMap<String, String>();

	@Test(description = "Create to do list for daily task")
	public void testCreateTodoList() throws InterruptedException {
		test = extent.startTest("Verify the todo list ").assignCategory(
				"CreateTodoList");
		todoListItemMap = WA.readXML("testdata", "ToDoList");
		System.out.println(todoListItemMap.size());
		test.log(LogStatus.INFO,
				"To do list store successfully in map and number of item in Map -->"
						+ todoListItemMap.size());
		for (String key : todoListItemMap.keySet()) {

			String itemValue = todoListItemMap.get(key);
			TD.createToDoList(itemValue);
			test.log(LogStatus.INFO, "To do list created for item " + itemValue);
		}

		int listSize = TD.getToDoListSize();
		test.log(LogStatus.INFO, "To do list created and size of todos is  "
				+ listSize);
		Assert.assertEquals(listSize, todoListItemMap.size(),
				"List created verifed ");

	}

	@Test(description = "Create to do list for daily task and mark first item as completed ")
	public void testCompleteTodoItem() throws InterruptedException {
		test = extent.startTest("Verify the todo Complete Item list ")
				.assignCategory("CreateTodoList");
		todoListItemMap = WA.readXML("testdata", "ToDoList");
		System.out.println(todoListItemMap.size());
		test.log(LogStatus.INFO,
				"To do list store successfully in map and number of item in Map -->"
						+ todoListItemMap.size());
		for (String key : todoListItemMap.keySet()) {

			String itemValue = todoListItemMap.get(key);
			TD.createToDoList(itemValue);
			test.log(LogStatus.INFO, "To do list created for item " + itemValue);
		}

		TD.setItemCompleted(1);
		TD.getompletedItemList();
		int listSize = TD.getCompletedToDoListSize();
		Assert.assertEquals(1, listSize);

	}

	@Test(description = "Create to do list and mark on todo item is as completed and then clear the completed list from do list")
	public void testClearedCompletedItem() throws InterruptedException {
		test = extent.startTest("Verify the Ceared Completed Item ")
				.assignCategory("CreateTodoList");
		todoListItemMap = WA.readXML("testdata", "ToDoList");
		System.out.println(todoListItemMap.size());
		test.log(LogStatus.INFO,
				"To do list store successfully in map and number of item in Map -->"
						+ todoListItemMap.size());
		for (String key : todoListItemMap.keySet()) {

			String itemValue = todoListItemMap.get(key);
			TD.createToDoList(itemValue);
			test.log(LogStatus.INFO, "To do list created for item " + itemValue);
		}
		int listSize = TD.getToDoListSize();
		TD.setItemCompleted(1);
		TD.clearompletedTodoList();
		Assert.assertEquals(TD.getToDoListSize(), listSize - 1);

	}

	@Test(description = "Create to do list and mark on todo item is as completed and then validated how many active to do items left")
	public void testActiveToDoItem() throws InterruptedException {
		test = extent.startTest("Verify the Active todos  Item ")
				.assignCategory("CreateTodoList");
		todoListItemMap = WA.readXML("testdata", "ToDoList");
		System.out.println(todoListItemMap.size());
		test.log(LogStatus.INFO,
				"To do list store successfully in map and number of item in Map -->"
						+ todoListItemMap.size());
		for (String key : todoListItemMap.keySet()) {

			String itemValue = todoListItemMap.get(key);
			TD.createToDoList(itemValue);
			test.log(LogStatus.INFO, "To do list created for item " + itemValue);
		}
		int listSize = TD.getToDoListSize();
		TD.setItemCompleted(1);
		Assert.assertEquals(TD.getAllActiveToDoListSize(), listSize - 1);

	}
}
