package it.polito.tdp.rivers.db;

import java.util.ArrayList;
import java.util.LinkedList;

import java.util.List;

import it.polito.tdp.rivers.model.Flow;
import it.polito.tdp.rivers.model.River;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RiversDAO {

	public List<River> getAllRivers() {
		
		final String sql = "SELECT id, name FROM river";

		List<River> rivers = new LinkedList<River>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				rivers.add(new River(res.getInt("id"), res.getString("name")));
			}

			conn.close();
			
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}

		return rivers;
	}
	
	public River getCompletamento(River river) {
		
		final String sql = "SELECT max(day) as max, min(DAY) as min, AVG(flow) as fmed, COUNT(*) as tot from flow WHERE river=?";
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, river.getId());
			ResultSet res = st.executeQuery();

			while (res.next()) {
				river.setFlowAvg(res.getDouble("fmed"));
				river.setMin(res.getDate("min").toLocalDate());
				river.setMax(res.getDate("max").toLocalDate());
				river.setTot(res.getInt("tot"));
			}

			conn.close();
			
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}

		return river;
	}
	
public void getFlow(River river) {
		
		final String sql = "SELECT * from flow where river=? order by day";
		List<Flow> flow = new ArrayList<>();
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, river.getId());
			ResultSet res = st.executeQuery();

			while (res.next()) {
				flow.add(new Flow(res.getDate("day").toLocalDate(), res.getDouble("flow"), river));
			}
			river.setFlows(flow);
			conn.close();
			
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}
	}
}
