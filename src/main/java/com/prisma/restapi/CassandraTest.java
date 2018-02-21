package com.prisma.restapi;

import java.util.ArrayList;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.google.gson.Gson;
import com.prisma.api.GetApi;
import com.prisma.pojo.DoctorRegistration;
import com.prisma.pojo.Mortality;
import com.prisma.pojo.OutComeICUPojo;
import com.prisma.pojo.PatientDetailsRaw;
import com.prisma.pojo.RiskAssessment;

public class CassandraTest {

	public static void getScores() {
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

	public static void getICUOutCome() {
		// TODO Auto-generated method stub
		String feeds = null;
		try {
			ArrayList<OutComeICUPojo> feedData = null;
			PrismaManager projectManager = new PrismaManager();
			feedData = projectManager.GetOutcome_ICU();
			System.out.println("feedData" + feedData);
			Gson gson = new Gson();
			System.out.println(gson.toJson(feedData));

		} catch (Exception e) {
			System.out.println("error");
		}
	}

	public static void updateICUOutCome() {
		// TODO Auto-generated method stub
		String feeds = null;
		try {
			ArrayList<OutComeICUPojo> feedData = null;
			PrismaManager projectManager = new PrismaManager();
			feedData = projectManager.UpdateOutcome_ICU("BB3XEDJ201", "25");
			System.out.println("feedData" + feedData);
			// Gson gson = new Gson();
			// System.out.println(gson.toJson(feedData));

		} catch (Exception e) {
			System.out.println("error");
		}
	}

	public static void testCql() {
		Dao dao = null;
		try {
			dao = new Dao();
			Session session = dao.getSession();

			String userId = "raj1";
			String password = "raj1";

			ResultSet results = session.execute("select count(*) as cnt from prisma1.userinfo where id='" + userId
					+ "' and password='" + password + "' allow filtering");

			// System.out.println("results=" + results.all());

			for (Row row : results) {
				System.out.println("row=" + row.getClass());
				System.out.println("cnt=" + row.getLong("cnt"));
				// noRow = Integer.parseInt(row.getString("cnt"));
				// System.out.println("noRow="+noRow);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (dao != null)
				dao.close();
		}
	}

	public static void getRiskAssessment() {
		ArrayList<RiskAssessment> riskAssessments = null;
		String feeds = null;
		try {
			PrismaManager prismaManager = new PrismaManager();

			riskAssessments = prismaManager.getRiskAssessment("raj", "ABC329",1);
			Gson gson = new Gson();
			System.out.println(gson.toJson(riskAssessments));

		} catch (Exception e) {
			System.out.println("error");
		}
	}
	
	public static void getMortality() {
		Mortality mortality = null;
		String feeds = null;
		try {
			PrismaManager prismaManager = new PrismaManager();

			mortality = prismaManager.mortality("A1F42085");
			Gson gson = new Gson();
			System.out.println(gson.toJson(mortality));

		} catch (Exception e) {
			System.out.println("error");
		}
	}
	
	public static void getPatientDetailRaw(String patientId) {
		ArrayList<PatientDetailsRaw> patientDetailsRaw = null;
		Gson gson = new Gson();
		try {
			//logger.debug("<<<<<<<<<<<<<<<userId="+userId+" password="+password);
			PrismaManager prismaManager = new PrismaManager();			
			
			patientDetailsRaw 			= prismaManager.OnePatientDetailsRaw(patientId);
			
		} catch (Exception e) {
			e.getStackTrace();
		}
		
		System.out.println(gson.toJson(patientDetailsRaw));
	}
	
	public static void insertPatient(DoctorRegistration doctorRegistration) {
		Gson gson = new Gson();
		boolean isReturn = false;
		try {
			//logger.debug("<<<<<<<<<<<<<<<userId="+userId+" password="+password);
			PrismaManager prismaManager = new PrismaManager();			
			
			isReturn 			= prismaManager.insertDoctorInfo(doctorRegistration);
			
		} catch (Exception e) {
			e.getStackTrace();
		}
		
		System.out.println(gson.toJson(isReturn));
	}

	public static void main(String[] args) throws Exception {
		// getScores();
		// getICUOutCome();
		// updateICUOutCome();
		//testCql();
		//getRiskAssessment();
		//getMortality();
		//getPatientDetailRaw("7160");
		DoctorRegistration doctorRegistration = new DoctorRegistration();
		doctorRegistration.setAge("20");
		doctorRegistration.setRegisterDate("12/12/2019");
		doctorRegistration.setUsername("abc");
		insertPatient(doctorRegistration);
	}
}








