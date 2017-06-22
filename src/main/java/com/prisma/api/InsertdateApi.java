package com.prisma.api;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.prisma.pojo.DoctorRegistration;
import com.prisma.pojo.IndexPatient;
import com.prisma.pojo.OutComeICUPojo;
import com.prisma.pojo.OutComeResult;
import com.prisma.pojo.OutcomeRank1;
import com.prisma.pojo.OutcomeStats;
import com.prisma.pojo.Reco;
import com.prisma.pojo.RecoCase;
import com.prisma.pojo.RecoTaken;
import com.prisma.pojo.RiskAssessment;

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
	
	public boolean insertOutcomeRank(Session session,OutcomeRank1[] outcomeRank1){
		
		boolean isSuccess = false;
		
		try {
			
			for(OutcomeRank1 outcomeRank:outcomeRank1){
				String user = outcomeRank.getUser();
				String patientId = outcomeRank.getPatientId();
				int outcomeId = outcomeRank.getOutcomeId();
				String feature = outcomeRank.getFeature();
				
				String insertOutcomeRankQuery = "insert into prisma1.outcomeRank(id,user,feature,outcomeid) "
						+ "values('"+patientId+"','"+user+"','"+feature+"',"+outcomeId+")";
				
				System.out.println(insertOutcomeRankQuery);
				session.execute(insertOutcomeRankQuery);
				
				isSuccess = true;
			}
			return isSuccess;

		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			e.getStackTrace();
		}
		return isSuccess;
	}
	
	public boolean insertRecoTable(Session session,Reco[] reco1){
		
		boolean isSuccess = false;
		
		try {
			for(Reco reco:reco1){
				String user = reco.getUser();
				String patientId = reco.getPatientId();
				String recoNo = reco.getReco();
				
			
				String insertRecoTableQuery = "insert into prisma1.recotable(id,user,reco) "
						+ "values('"+patientId+"','"+user+"','"+recoNo+"')";
				
				System.out.println(insertRecoTableQuery);
				session.execute(insertRecoTableQuery);
				
				isSuccess = true;
			}
			return isSuccess;

		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			e.getStackTrace();
		}
		return isSuccess;
	}
	
	public boolean insertRecoCaseTable(Session session,RecoCase[] recoCase1){
		
		boolean isSuccess = false;
		
		try {
			for(RecoCase recoCase:recoCase1){
				String user = recoCase.getUser();
				String patientId = recoCase.getPatientId();
				String caseNo = recoCase.getCaseno();
				
			
				String insertRecoCaseTableQuery = "insert into prisma1.recoCaseTable(id,user,caseno) "
						+ "values('"+patientId+"','"+user+"','"+caseNo+"')";
				
				System.out.println(insertRecoCaseTableQuery);
				session.execute(insertRecoCaseTableQuery);
				
				isSuccess = true;
			}
			return isSuccess;

		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			e.getStackTrace();
		}
		return isSuccess;
	}

	public boolean insertOutcomeStats(Session session,OutcomeStats outComeStats){
		
		boolean isSuccess = false;
		
		try {
			String user = outComeStats.getUsername();
			String patientId = outComeStats.getPatientId();
			int outcomeId = outComeStats.getOutcomeId();
			float timeScreen1 = outComeStats.getTimeScreen1();
			float timeScreen2 = outComeStats.getTimeScreen2();
			int click1 = outComeStats.getClick1();
			int click2 = outComeStats.getClick2();
			
		
			String insertoutcomeStatsQuery = "insert into prisma1.outcomeStats(user,id,outcomeid,timescreen1,timescreen2,click1,click2) "
					+ "values('"+user+"','"+patientId+"',"+outcomeId+","+timeScreen1+","+timeScreen2+","+click1+","+click2+")";
			System.out.println(insertoutcomeStatsQuery);
			session.execute(insertoutcomeStatsQuery);
			
			isSuccess = true;
			return isSuccess;

		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			e.getStackTrace();
		}
		return isSuccess;
	}
	
	
	public boolean insertRecoTakenTable(Session session,RecoTaken recoTaken){
		
		boolean isSuccess = false;
		
		try {
			String user = recoTaken.getUsername();
			String patientId = recoTaken.getPatientId();
			String reco = recoTaken.getReco();
			
		
			String insertRecoTakenQuery = "insert into prisma1.recoTakenTable(id,user,reco) values('"+patientId+"','"+user+"','"+reco+"')";
			System.out.println(insertRecoTakenQuery);
			session.execute(insertRecoTakenQuery);
			
			isSuccess = true;
			return isSuccess;

		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			e.getStackTrace();
		}
		return isSuccess;
	}
	
	public boolean insertOutcomeResult(Session session,OutComeResult[] outComeResult1){
		
		boolean isSuccess = false;
		
		try {
			
			for(OutComeResult outComeResult:outComeResult1){
				String user = outComeResult.getUsername();
				String patientId = outComeResult.getPatientId();
				int outcomeId = outComeResult.getOutcomeId();
				int attempt1 = outComeResult.getAttempt1();
				int attempt2 = outComeResult.getAttempt2();
		
				
				String outcomeResultQuery = "insert into prisma1.outcomeResult(user,id,outcomeID,attempt1,attempt2) values('"+user+"','"+patientId+"',"+outcomeId+","+attempt1+","+attempt2+")";
				System.out.println(outcomeResultQuery);
				session.execute(outcomeResultQuery);
				
				isSuccess = true;
			}
			return isSuccess;

		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			e.getStackTrace();
		}
		return isSuccess;
	}

	public boolean insertIndexPatient(Session session,IndexPatient indexPatient){
		
		boolean isSuccess = false;
		
		try {
			String user = indexPatient.getUser();
			String patientId = indexPatient.getPatientId();
			int noAttempt = indexPatient.getNoAttempt();
			float timeScreen = indexPatient.getTimeScreen();
			
			String indePatientQuery = "insert into prisma1.indexPatient(id,user,attempt,timescreen) values('"+patientId+"','"+user+"',"+noAttempt+","+timeScreen+")";
			System.out.println(indePatientQuery);
			session.execute(indePatientQuery);
			
			isSuccess = true;
			return isSuccess;

		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			e.getStackTrace();
		}
		return isSuccess;
	}
	
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

	public boolean insertRiskAssessment(Session session, RiskAssessment riskAssessments, int riskAssessmentType) throws Exception {
		
		boolean isSuccess = false;
		try {
			String docId = riskAssessments.getDocId();
			String patientId = riskAssessments.getPatientId();
			
			float rifle7 = riskAssessments.getRifle7();
			float icu = riskAssessments.getIcu();
			float ventilator = riskAssessments.getVentilator();
			float cardiovascular = riskAssessments.getCardiovascular();
			float sepsis = riskAssessments.getSepsis(); 
			float neurological = riskAssessments.getNeurological();
			float venous = riskAssessments.getVenous(); 
			float wound = riskAssessments.getWound(); 
			
			System.out.println("docName: " + docId);
			System.out.println("patientId: " + patientId);
			
			//System.out.println(i+": "+questions.get(i));
			//System.out.println("insert into prisma1.doctortestresults(id,quesno,ansdoc) values('"+userName+"',"+i+",'"+questions.get(i)+"')");
			
			String insertQuery = "insert into prisma1.riskAssessment(docId, patientId, riskType, rifle7, icu, ventilator, cardiovascular, sepsis,"
					+ " neurological, venous, wound) values('"
					+ docId + "','" + patientId + "'," + riskAssessmentType + ","+ rifle7 + "," + icu + "," + ventilator + "," + cardiovascular + "," + sepsis + "," +
					neurological + "," + venous + "," + wound + ")";
			
			System.out.println(insertQuery);
			
			session.execute(insertQuery);
			
			isSuccess = true;
			return isSuccess;

		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			e.getStackTrace();
			throw e;
		}
	}
}
