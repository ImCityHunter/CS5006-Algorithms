package edu.northeastern.cs_5006;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.PriorityQueue;
import org.junit.jupiter.api.Test;

import edu.northeastern.cs_5006.Investment.Bond;

public class InvestmentTest {

	/*
	 * Intialize all the bonds that will be used with global variables
	 */
	Bond Amazon = new Bond("Amazon",(float) 50,(float) 4, (float) 0.04);
	Bond Dell = new Bond("Dell",(float) 40,(float) 4,(float) 0.04);
	Bond Google = new Bond("Google",(float) 60,(float) 6,(float) 0.06);
	Bond Yahoo = new Bond("Yahoo",(float) 50,(float) 5, (float) 0.05);

	/**
	 * 
	 * Initialize all the markets with all the bonds
	 */
	Collection<Bond> initializeMarket() {
		ArrayList<Bond> collection = new ArrayList<Bond>();
		collection.add(Amazon);
		collection.add(Dell);
		collection.add(Google);
		collection.add(Yahoo);
		return collection;
	}
	
	
	/**
	 * Test Market Initiation
	 */
	@Test
	public void test_sortedQueue() {
		ArrayList<Bond> collection = (ArrayList<Bond>) initializeMarket();
		
		Investment investment = new Investment();
		
		PriorityQueue<Bond> queue = investment.sortedOpportunities;
		queue.addAll(collection);
		
		
		assertEquals(queue.poll(),Google); //highest yield
		assertEquals(queue.poll(),Yahoo);  //2nd highest yield
		assertEquals(queue.poll(),Amazon); //same yield but has higher total profit
		assertEquals(queue.poll(),Dell);   //same yield but has lower total profit

		
	}
	/**
	 * Test funds that can buy all the bonds
	 */
	@Test
	public void test_fundsLeftOver() {
		
		ArrayList <Bond> collection = (ArrayList<Bond>) initializeMarket();
		Investment investment = new Investment();
		
		
		float fund = 1000000;
		ArrayList <Bond> portfolio = (ArrayList<Bond>) investment.invest(fund, collection);
		
		assertEquals(portfolio.size(),collection.size()); //portfolio should be 3
		
		assertEquals(portfolio.remove(0),Google); //highest yield
		assertEquals(portfolio.remove(0),Yahoo);  //2nd highest yield
		assertEquals(portfolio.remove(0),Amazon); //0.04 yield but has higher total profit
		assertEquals(portfolio.remove(0),Dell);   //0.04 yield but has less total profit
		assertEquals(portfolio.size(),0); //assure nothing more is inside the portfolio
		
		
	}
	
	/**
	 * Test exact fund that can buy all funds
	 */
	@Test
	public void test_wholePosition() {
		ArrayList<Bond> collection = (ArrayList<Bond>) initializeMarket();
		Investment investment = new Investment();
		float fund = 610;
		ArrayList<Bond> portfolio = (ArrayList<Bond>) investment.invest(fund, collection);
		assertEquals(2,portfolio.size());
		
	}
	
	/**
	 * Test fund buying fraction of funds (not whole bond)
	 */
	@Test
	public void test_fractionPosition() {
		
		ArrayList<Bond> collection = (ArrayList<Bond>) initializeMarket();
		Investment investment = new Investment();
		float fund = 507;
		ArrayList<Bond> portfolio = (ArrayList<Bond>) investment.invest(fund, collection);
		assertEquals(portfolio.size(), 2);
		Bond fraction = portfolio.get(1);
		
		//There are 50 shares available, but can only buy 29.4 (mathematically);
		assertEquals(fraction.shares,29.4,0.1);
		
		
	}

}
