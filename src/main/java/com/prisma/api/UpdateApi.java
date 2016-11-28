package com.prisma.api;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.prisma.pojo.OutComeICUPojo;

public class UpdateApi {
	
	final static Logger logger = Logger.getLogger(UpdateApi.class);

	public ArrayList<OutComeICUPojo> UpdateICU_OutCome(Session session,String id, String outcome)//(Connection connection)
			throws Exception {
		ArrayList<OutComeICUPojo> OutcomeICUList = new ArrayList<OutComeICUPojo>();
		try {
			// String uname = request.getParameter("uname");
			ResultSet results = session.execute("update prisma1.outcome_icu set outcome = '"+outcome+"' where id = '"+id+"'");
			//ResultSet results = session.execute("select id, outcome from prisma1.outcome_icu");
			System.out.println("results="+results);
			
			/*for (Row row : results) {
				System.out.format("%s %s\n", row.getString("id"), row.getString("outcome"));
				OutComeICUPojo outcomeICU = new OutComeICUPojo();
				outcomeICU.setId(row.getString("id"));
				outcomeICU.setOutcome(row.getString("outcome"));
				OutcomeICUList.add(outcomeICU);
			}*/
			
			return OutcomeICUList;
		} catch (Exception e) {
			throw e;
		}
	}
}

