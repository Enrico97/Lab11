package it.polito.tdp.rivers.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class River {
	private int id;
	private String name;
	private double flowAvg;
	private List<Flow> flows;
	private LocalDate min;
	private LocalDate max;
	private int tot;
	
	public River(int id) {
		this.id = id;
	}

	public River(int id, String name) {
		this.id = id;
		this.name = name;
	}

	/**
	 * @return the tot
	 */
	public int getTot() {
		return tot;
	}

	/**
	 * @param tot the tot to set
	 */
	public void setTot(int tot) {
		this.tot = tot;
	}

	/**
	 * @param flowAvg
	 * @param min
	 * @param max
	 * @param tot
	 */
	public River(double flowAvg, LocalDate min, LocalDate max, int tot) {
		super();
		this.flowAvg = flowAvg;
		this.min = min;
		this.max = max;
		this.tot = tot;
	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public double getFlowAvg() {
		return flowAvg;
	}

	public void setFlowAvg(double flowAvg) {
		this.flowAvg = flowAvg;
	}

	public void setFlows(List<Flow> flows) {
		this.flows = flows;
	}

	public List<Flow> getFlows() {
		if (flows == null)
			flows = new ArrayList<Flow>();
		return flows;
	}
	
	

	/**
	 * @return the min
	 */
	public LocalDate getMin() {
		return min;
	}

	/**
	 * @param min the min to set
	 */
	public void setMin(LocalDate min) {
		this.min = min;
	}

	/**
	 * @return the max
	 */
	public LocalDate getMax() {
		return max;
	}

	/**
	 * @param max the max to set
	 */
	public void setMax(LocalDate max) {
		this.max = max;
	}

	@Override
	public String toString() {
		return name+" "+min+" "+max+" "+flowAvg;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		River other = (River) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
