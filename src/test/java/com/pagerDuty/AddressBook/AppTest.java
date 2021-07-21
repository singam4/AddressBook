package com.pagerDuty.AddressBook;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

import org.json.JSONException;
import org.junit.Assert;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
	@Test
	public void testListAll() throws IOException, JSONException, InterruptedException {
		AddressBook addressBook = new AddressBook();
		List<String> output = addressBook.fetch("https://api.pagerduty.com/users?offset=");

		Assert.assertEquals(output.size(), 47);
	}
	
	@Test
	public void testListOne() throws IOException, JSONException, InterruptedException {
		AddressBook addressBook = new AddressBook();
		List<String> output = addressBook.fetch("https://api.pagerduty.com/users?query=Alan&offset=");

		Assert.assertEquals(output.size(), 1);
		Assert.assertEquals(output.toString(), "[Alan B. Shepard, Jr.	alan.shepard@nasa.example]");
	}
}
