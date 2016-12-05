package com.prisma.api;

import org.apache.log4j.Logger;

import com.datastax.driver.core.Session;

public class DeletedataApi {
	final static Logger logger = Logger.getLogger(DeletedataApi.class);

	public boolean deleteidList(Session session, String patientId) {

		boolean isSuccess = false;

		try {
			String deleteidListQuery = "Delete from prisma1.outcomeResult where id='"+patientId+"'";
			System.out.println(deleteidListQuery);
			session.execute(deleteidListQuery);

			isSuccess = true;
			return isSuccess;

		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			e.getStackTrace();
		}
		return isSuccess;
	}
}
