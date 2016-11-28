package com.prisma.restapi;

//import java.sql.Connection;
import com.datastax.driver.core.Session;
import com.prisma.api.GetApi;
import com.prisma.api.InsertdateApi;
import com.prisma.api.UpdateApi;
import com.prisma.pojo.DoctorRegistration;
import com.prisma.pojo.OutComeICUPojo;
import com.prisma.pojo.PatientDetails;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class PrismaManager {
	
	final static Logger logger = Logger.getLogger(PrismaManager.class);

	public ArrayList<ScorePojo> GetScoreObj() throws Exception {
		ArrayList<ScorePojo> scoreLists = null;
		Dao dao = null;
		try {
			dao = new Dao();
			Session session = dao.getSession();
			Score project = new Score();
			scoreLists = project.GetScores(session);
		} catch (Exception e) {
			throw e;
		} finally {
			if (dao != null)
				dao.close();
		}
		
		return scoreLists;
	}

	public ArrayList<OutComeICUPojo> GetOutcome_ICU() throws Exception {
		ArrayList<OutComeICUPojo> outComeICULists = null;

		Dao dao = null;
		try {
			dao = new Dao();
			Session session = dao.getSession();
			GetApi api = new GetApi();
			outComeICULists = api.GetICU_OutCome(session);
		} catch (Exception e) {
			throw e;
		} finally {
			if (dao != null)
				dao.close();
		}

		return outComeICULists;
	}

	public ArrayList<OutComeICUPojo> UpdateOutcome_ICU(String id, String outcome)
			throws Exception {
		ArrayList<OutComeICUPojo> outComeICULists = null;

		Dao dao = null;
		try {
			dao = new Dao();
			Session session = dao.getSession();
			UpdateApi api = new UpdateApi();
			outComeICULists = api.UpdateICU_OutCome(session, id, outcome);
		} catch (Exception e) {
			throw e;
		} finally {
			if (dao != null)
				dao.close();
		}

		return outComeICULists;
	}
	
	public List patientPrediction(String doctorId, String patientId, int outcomeId) throws Exception {

		//ArrayList<PatientDetails> patientDetails = null;
		List outcomeRank = null;

		Dao dao = null;
		try {
			dao = new Dao();
			Session session = dao.getSession();
			GetApi api = new GetApi();
			outcomeRank = api.patientPrediction(session, doctorId, patientId, outcomeId);
			
		} catch (Exception e) {
			throw e;
		} finally {
			if (dao != null)
				dao.close();
		}
		return outcomeRank;
	}
	
	public PatientDetails onePatient(String doctorId, String patientId) throws Exception {

		//ArrayList<PatientDetails> patientDetails = null;
		PatientDetails retVal = null;

		Dao dao = null;
		try {
			dao = new Dao();
			Session session = dao.getSession();
			GetApi api = new GetApi();
			retVal = api.onePatient(session, doctorId, patientId);
			
		} catch (Exception e) {
			throw e;
		} finally {
			if (dao != null)
				dao.close();
		}
		return retVal;
	}
	
	public ArrayList<PatientDetails> patientDetail(String doctorId) throws Exception {

		ArrayList<PatientDetails> patientDetails = null;

		Dao dao = null;
		try {
			dao = new Dao();
			Session session = dao.getSession();
			GetApi api = new GetApi();
			patientDetails = api.getPatientDetail(session, doctorId);
			
		} catch (Exception e) {
			throw e;
		} finally {
			if (dao != null)
				dao.close();
		}
		return patientDetails;
	}
	
	public int userCheck(String userId) throws Exception {

		int noUsers = 0;

		Dao dao = null;
		try {
			dao = new Dao();
			Session session = dao.getSession();
			GetApi api = new GetApi();
			noUsers = api.userCheck(session, userId);
			
		} catch (Exception e) {
			throw e;
		} finally {
			if (dao != null)
				dao.close();
		}
		return noUsers;
	}
	
	public int login(String userId, String password) throws Exception {

		int noUsers = 0;

		Dao dao = null;
		try {
			dao = new Dao();
			Session session = dao.getSession();
			GetApi api = new GetApi();
			noUsers = api.getLogin(session, userId, password);
			
		} catch (Exception e) {
			throw e;
		} finally {
			if (dao != null)
				dao.close();
		}
		return noUsers;
	}
	
	public boolean insertDoctorInfo(DoctorRegistration doctorRegistration) throws Exception {

		boolean isSuccess = false;

		Dao dao = null;
		try {
			dao = new Dao();
			Session session = dao.getSession();
			InsertdateApi api = new InsertdateApi();
			isSuccess = api.insertDoctorInfo(session, doctorRegistration);
		} catch (Exception e) {
			throw e;
		} finally {
			if (dao != null)
				dao.close();
		}
		return isSuccess;
	}

	public boolean insertDoctorTestResult(DoctorRegistration doctorRegistration) throws Exception {

		boolean isSuccess = false;

		Dao dao = null;
		try {
			dao = new Dao();
			Session session = dao.getSession();
			InsertdateApi api = new InsertdateApi();
			isSuccess = api.insertDoctorTestResults(session, doctorRegistration);
		} catch (Exception e) {
			throw e;
		} finally {
			if (dao != null)
				dao.close();
		}
		return isSuccess;
	}
	
}
