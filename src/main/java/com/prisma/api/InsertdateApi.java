package com.prisma.api;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.prisma.pojo.DoctorRegistration;
import com.prisma.pojo.OutComeICUPojo;

public class InsertdateApi {
	
	final static Logger logger = Logger.getLogger(InsertdateApi.class);


	/*
	 * public ArrayList<OutComeICUPojo> insertResults(Session session, String
	 * id, String outcome)//(Connection connection) throws Exception {
	 * ArrayList<OutComeICUPojo> OutcomeICUList = new
	 * ArrayList<OutComeICUPojo>(); try { // String uname =
	 * request.getParameter("uname"); ResultSet results =
	 * session.execute("update prisma1.outcome_icu set outcome = '"
	 * +outcome+"' where id = '"+id+"'"); //ResultSet results =
	 * session.execute("select id, outcome from prisma1.outcome_icu");
	 * System.out.println("results="+ results);
	 * 
	 * for (Row row : results) { System.out.format("%s %s\n",
	 * row.getString("id"), row.getString("outcome")); OutComeICUPojo outcomeICU
	 * = new OutComeICUPojo(); outcomeICU.setId(row.getString("id"));
	 * outcomeICU.setOutcome(row.getString("outcome"));
	 * OutcomeICUList.add(outcomeICU); }
	 * 
	 * return OutcomeICUList; } catch (Exception e) { throw e; } }
	 */

	public boolean insertDoctorInfo(Session session,
			DoctorRegistration doctorRegistration) throws Exception {

		boolean isSuccess = false;
		
		try {
			String userName   = doctorRegistration.getUsername();
			String password   = doctorRegistration.getPassword();
			String gender     = doctorRegistration.getGender();
			String age        = doctorRegistration.getAge();
			String role       = doctorRegistration.getCurrentRoles();
			String speciality = doctorRegistration.getSpeciality();
			String experience = doctorRegistration.getExperience();
			String name       = doctorRegistration.getFullName();
			
			System.out.println("userName:" + userName+" password:"+password+" gender:"+gender+" age: "+age+" role:"+role+" speciality:"+
							    speciality+" experience:"+experience+" name:"+name);			
			
			System.out.println("insert into prisma1.userinfo(id,age,experience,gender,name,password,role,speciality) "
					+ "values('"+userName+"','"+age+"','"+experience+"','"+gender+"','"+name+"','"+password+"','"+role+"','"+speciality+"')");
			
			session.execute("insert into prisma1.userinfo(id,age,experience,gender,name,password,role,speciality) "
					+ "values('"+userName+"','"+age+"','"+experience+"','"+gender+"','"+name+"','"+password+"','"+role+"','"+speciality+"')");
			
			isSuccess = true;
			return isSuccess;

		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			e.getStackTrace();
			throw e;
		}
	}

	public boolean insertDoctorTestResults(Session session,
			DoctorRegistration doctorRegistration) throws Exception {

		boolean isSuccess = false;
		try {
			String userName = doctorRegistration.getUsername();
			ArrayList<String> questions = doctorRegistration.getQuestions();
			int questLen = questions.size();

			System.out.println("userName: " + userName);
			System.out.println("questions: " + questions);

			for (int i = 0; i < questLen; i++) {
				// System.out.println(i+": "+questions.get(i));
				// System.out.println("insert into prisma1.doctortestresults(id,quesno,ansdoc) values('"+userName+"',"+i+",'"+questions.get(i)+"')");
				session.execute("insert into prisma1.doctortestresults(id,quesno,ansdoc) values('"
						+ userName + "'," + i + ",'" + questions.get(i) + "')");
			}

			isSuccess = true;
			return isSuccess;

		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			e.getStackTrace();
			throw e;
		}
	}
}
