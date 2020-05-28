package it.polito.tdp.rivers.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.PriorityQueue;

import it.polito.tdp.rivers.model.Event.EventType;

public class Simulator {
	
	// PARAMETRI DI SIMULAZIONE
	private double Q;
	private LocalDate min;
	private LocalDate max;
	private long giorni;
	private River river;
	double x = 0;
	
	// OUTPUT DA CALCOLARE
	private int no;
	
	// STATO DEL SISTEMA
	private double C;
	
	// CODA DEGLI EVENTI
		private PriorityQueue<Event> queue;
		
		// INIZIALIZZAZIONE
public void init(double k, River river) {
			this.queue = new PriorityQueue<>();
			this.river=river;
			Q = k*river.getFlowAvg()*3600*24*30;
			C = Q/2;
			no=0;
			min = river.getMin();
			max = river.getMax();
			giorni = min.until(max, ChronoUnit.DAYS)+1;
			LocalDate partenza = min;
			while (partenza.isBefore(max) || partenza.isEqual(max)) {
				Event e = new Event (partenza, river.getFlows().get(partenza.getDayOfMonth()-1).getFlow()*3600*24);
				queue.add(e);
				partenza = partenza.plusDays(1);
			}
		}
public void run() {
	while (!this.queue.isEmpty()) {
		Event e = this.queue.poll();
		processEvent(e);
	}
}

private void processEvent(Event e) {
	
		C+=e.getFlow();
		double f_out=0;
		if (Math.random()>=0.95) {
			f_out = 10*0.8*river.getFlowAvg()*3600*24;
			if(C<=Q+f_out && C>=f_out) {
				C=C-f_out;
			}
			if(C<=Q+f_out && C<f_out) {
				C=0;
				no++;
			}
			if(C>Q+f_out) {
				C=Q;
			}
		}
		else {
			f_out = 0.8*river.getFlowAvg()*3600*24;
			if(C<=Q+f_out && C>=f_out) {
				C=C-f_out;
			}
			if(C<=Q+f_out && C<f_out) {
				C=0;
				no++;
			}
			if(C>Q+f_out) {
				C=Q;
			}
			}	
		x+=C;
}

public double getMedia() {
	return x/giorni;
}

public int getNo() {
	return no;
}

}
