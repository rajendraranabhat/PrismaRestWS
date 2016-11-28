package com.prisma.restapi;

import java.util.ArrayList;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.google.gson.Gson;
import com.prisma.api.GetApi;
import com.prisma.pojo.OutComeICUPojo;

public class CassandraTest {
	
	public static void getScores(){
		// TODO Auto-generated method stub
				String feeds = null;
				try {
					ArrayList<ScorePojo> feedData = null;
					PrismaManager projectManager = new PrismaManager();
					feedData = projectManager.GetScoreObj();
					Gson gson = new Gson();
					System.out.println(gson.toJson(feedData));
					
				} catch (Exception e) {
					System.out.println("error");
				}
	}
	
	public static void getICUOutCome(){
		// TODO Auto-generated method stub
		String feeds = null;
		try {
			ArrayList<OutComeICUPojo> feedData = null;
			PrismaManager projectManager = new PrismaManager();
			feedData = projectManager.GetOutcome_ICU();
			System.out.println("feedData"+feedData);
			Gson gson = new Gson();
			System.out.println(gson.toJson(feedData));
			
		} catch (Exception e) {
			System.out.println("error");
		}
	}
	
	public static void updateICUOutCome(){
		// TODO Auto-generated method stub
		String feeds = null;
		try {
			ArrayList<OutComeICUPojo> feedData = null;
			PrismaManager projectManager = new PrismaManager();
			feedData = projectManager.UpdateOutcome_ICU("BB3XEDJ201","25");
			System.out.println("feedData"+feedData);
			//Gson gson = new Gson();
			//System.out.println(gson.toJson(feedData));

		} catch (Exception e) {
			System.out.println("error");
		}
	}
	
	public static void testCql(){
		Dao dao = null;
		try {
			dao = new Dao();
			Session session = dao.getSession();
			
			String userId   = "raj1";
			String password = "raj1";
			
			ResultSet results = session
					.execute("select count(*) as cnt from prisma1.userinfo where id='"+userId+"' and password='"+password+"' allow filtering");
				
			//System.out.println("results=" + results.all());
			
			for (Row row : results){
				System.out.println("row="+row.getClass());
				System.out.println("cnt="+row.getLong("cnt"));
				//noRow = Integer.parseInt(row.getString("cnt"));
				//System.out.println("noRow="+noRow);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (dao != null)
				dao.close();
		}
	}


	public static void main(String[] args) throws Exception {
		//getScores();
		//getICUOutCome();
		//updateICUOutCome();
		testCql();
	}
}



