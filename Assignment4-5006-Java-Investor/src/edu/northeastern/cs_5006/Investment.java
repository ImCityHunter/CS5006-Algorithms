package edu.northeastern.cs_5006;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Investment {
	
	/**
	 * Bond, an inner class of Investment
	 */
	public static class Bond{
		String name; //name of the bond
		public float shares; //number of shares
		public float cost; //cost per share
		public float yield; //yield per share 
		Bond(String name, float shares, float cost, float yield){
			this.name = name;
			this.shares = shares;
			this.cost = cost;
			this.yield= yield;
		}
		/**
		 * return the total cost of current bond
		 * @return total cost
		 */
		public float totalCost() {
			return this.shares*this.cost;
		}
		
		/**
		 * return the total profit of current bond
		 * @return profit
		 */
		public float totalProfit() {
			return this.shares*this.cost*this.yield;
		}
	}
	
	public PriorityQueue<Bond> sortedOpportunities = null;
	
	/**
	 * default constructor of Investment
	 * 
	 * not used**
	 */
	public Investment() {
		sortedOpportunities = new PriorityQueue<Bond>(new Investment.BondComparator());
	}
	/**
	 * Given a collection of bonds and budget, and create a portfolio
	 * @param total
	 * @param opportunities / collection of bonds
	 * @return portfolio of bonds with best profit
	 */
	public Collection <Bond> invest(float total, Collection<Bond> opportunities) {
		if(opportunities == null || opportunities.size()==0) {
			System.out.println("input error");
			return null;
		}
		
		//add all the bonds to PriorityQueue
		this.sortedOpportunities.addAll(opportunities);
		
		
		float cost = 0;
		Collection <Bond> portfolio = new ArrayList<Bond>();
		
		while(cost<=total && !sortedOpportunities.isEmpty()) {
			Bond toBeAdded = sortedOpportunities.poll();
			if(toBeAdded.totalCost()+cost <= total) {
				portfolio.add(toBeAdded);
				cost += toBeAdded.totalCost();
			}
			else if(total-cost > 0) {
				float allowedShares = (float) ((total-cost)/toBeAdded.cost);
				Bond fraction = new Bond(toBeAdded.name,allowedShares,toBeAdded.cost,toBeAdded.yield);
				portfolio.add(fraction);
				toBeAdded.shares -= allowedShares;
				sortedOpportunities.add(toBeAdded);
				cost += fraction.totalCost();
			}
		}
		return portfolio;
	}
	
	/**
	 * 
	 * @author hky
	 * override comparator 
	 */
	public class BondComparator implements Comparator <Bond>{
		@Override
		public int compare(Bond o1, Bond o2) {
			if(o1.yield < o2.yield) return 1;
			//if yield are the same, compare total profit, otherwise -1
			else if(o1.yield == o2.yield) {
				if(o1.totalProfit()<o2.totalProfit()) return 1;
				else if(o1.totalProfit() == o2.totalProfit()) return 0;
				else return -1;
			}
			else return -1;
		}
			
		}
	

}
