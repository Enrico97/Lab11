package it.polito.tdp.rivers.model;

import java.util.List;

import it.polito.tdp.rivers.db.RiversDAO;

public class Model {
	
	RiversDAO dao = new RiversDAO();
	Simulator s = new Simulator();
	
	public List <River> getAllRivers() {
		return dao.getAllRivers();
	}
	
	public River getCompletamento(River river) {
		return dao.getCompletamento(river);
	}
	
	public void simula(int k, River river) {
		dao.getFlow(river);
		s.init(k, river);
		s.run();
	}
	
	public double getMedia() {
		return s.getMedia();
	}
	
	public int getNo() {
		return s.getNo();
	}
}
