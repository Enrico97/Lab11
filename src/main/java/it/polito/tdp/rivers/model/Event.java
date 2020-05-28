package it.polito.tdp.rivers.model;

import java.time.LocalDate;

public class Event implements Comparable <Event>{
	
	public enum EventType {
		
		F_IN,
	}
	
	private LocalDate date;
	private double flow;
	/**
	 * @param date
	 * @param tipo
	 * @param flow
	 */
	public Event(LocalDate date, double flow) {
		super();
		this.date = date;
		this.flow = flow;
	}
	/**
	 * @return the date
	 */
	public LocalDate getDate() {
		return date;
	}
	/**
	 * @return the flow
	 */
	public double getFlow() {
		return flow;
	}
	@Override
	public int compareTo(Event o) {
		return this.getDate().compareTo(o.getDate());
	}
	
	

}
