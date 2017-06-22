package com.prisma.restapi;

//import java.sql.Connection;
import com.datastax.driver.core.Session;
import com.prisma.api.DeletedataApi;
import com.prisma.api.GetApi;
import com.prisma.api.InsertdateApi;
import com.prisma.api.UpdateApi;
import com.prisma.pojo.DoctorRegistration;
import com.prisma.pojo.IndexPatient;
import com.prisma.pojo.OutComeICUPojo;
import com.prisma.pojo.OutComeResult;
import com.prisma.pojo.OutcomeRank1;
import com.prisma.pojo.OutcomeStats;
import com.prisma.pojo.PatientDetails;
import com.prisma.pojo.Reco;
import com.prisma.pojo.RecoCase;
import com.prisma.pojo.RecoTaken;
import com.prisma.pojo.ReviewResult;
import com.prisma.pojo.RiskAssessment;

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
	
	public int noOfAttempt(String userName) throws Exception {

		int attempt = 0;

		Dao dao = null;
		try {
			dao = new Dao();
			Session session = dao.getSession();
			GetApi api = new GetApi();
			attempt = api.noOfAttempt(session, userName);
			
		} catch (Exception e) {
			throw e;
		} finally {
			if (dao != null)
				dao.close();
		}
		return attempt;
	}
	
	public List patientPrediction(String doctorId, String patientId) throws Exception {

		//ArrayList<PatientDetails> patientDetails = null;
		List outcomeRank = null;

		Dao dao = null;
		try {
			dao = new Dao();
			Session session = dao.getSession();
			GetApi api = new GetApi();
			outcomeRank = api.patientPrediction(session, doctorId, patientId);
			
		} catch (Exception e) {
			throw e;
		} finally {
			if (dao != null)
				dao.close();
		}
		return outcomeRank;
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
	
	public ReviewResult reviewResults(int outcomeId) throws Exception {

		ReviewResult review = null;

		Dao dao = null;
		try {
			dao = new Dao();
			Session session = dao.getSession();
			GetApi api = new GetApi();
			review = api.reviewResults(session, outcomeId);
			
		} catch (Exception e) {
			throw e;
		} finally {
			if (dao != null)
				dao.close();
		}
		return review;
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
	
	public boolean insertOutcomeRank(OutcomeRank1[] outcomeRank) throws Exception {
		
		boolean isSuccess = false;

		Dao dao = null;
		try {
			dao = new Dao();
			Session session = dao.getSession();
			InsertdateApi api = new InsertdateApi();
			isSuccess = api.insertOutcomeRank(session, outcomeRank);
		} catch (Exception e) {
			throw e;
		} finally {
			if (dao != null)
				dao.close();
		}
		return isSuccess;
	}
	
	public boolean insertRecoTable(Reco[] reco) throws Exception {
		
		boolean isSuccess = false;

		Dao dao = null;
		try {
			dao = new Dao();
			Session session = dao.getSession();
			InsertdateApi api = new InsertdateApi();
			isSuccess = api.insertRecoTable(session, reco);
		} catch (Exception e) {
			throw e;
		} finally {
			if (dao != null)
				dao.close();
		}
		return isSuccess;
	}
	
	public boolean insertRecoCaseTable(RecoCase[] recoCase) throws Exception {
		
		boolean isSuccess = false;

		Dao dao = null;
		try {
			dao = new Dao();
			Session session = dao.getSession();
			InsertdateApi api = new InsertdateApi();
			isSuccess = api.insertRecoCaseTable(session, recoCase);
		} catch (Exception e) {
			throw e;
		} finally {
			if (dao != null)
				dao.close();
		}
		return isSuccess;
	}
	
	public boolean insertOutcomeStats(OutcomeStats outComeStats) throws Exception {
		
		boolean isSuccess = false;

		Dao dao = null;
		try {
			dao = new Dao();
			Session session = dao.getSession();
			InsertdateApi api = new InsertdateApi();
			isSuccess = api.insertOutcomeStats(session, outComeStats);
		} catch (Exception e) {
			throw e;
		} finally {
			if (dao != null)
				dao.close();
		}
		return isSuccess;
	}
	
	public boolean insertRecoTakenTable(RecoTaken recoTaken) throws Exception {
		
		boolean isSuccess = false;

		Dao dao = null;
		try {
			dao = new Dao();
			Session session = dao.getSession();
			InsertdateApi api = new InsertdateApi();
			isSuccess = api.insertRecoTakenTable(session, recoTaken);
		} catch (Exception e) {
			throw e;
		} finally {
			if (dao != null)
				dao.close();
		}
		return isSuccess;
	}
	
	public boolean insertOutcomeResult(OutComeResult[] outComeResult) throws Exception {
		
		boolean isSuccess = false;

		Dao dao = null;
		try {
			dao = new Dao();
			Session session = dao.getSession();
			InsertdateApi api = new InsertdateApi();
			isSuccess = api.insertOutcomeResult(session, outComeResult);
		} catch (Exception e) {
			throw e;
		} finally {
			if (dao != null)
				dao.close();
		}
		return isSuccess;
	}

	public boolean insertIndexPatient(IndexPatient indexPatient) throws Exception {
		
		boolean isSuccess = false;

		Dao dao = null;
		try {
			dao = new Dao();
			Session session = dao.getSession();
			InsertdateApi api = new InsertdateApi();
			isSuccess = api.insertIndexPatient(session, indexPatient);
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

	public boolean deleteidList(String patientId) throws Exception {
		boolean isSuccess = false;

		Dao dao = null;
		try {
			dao = new Dao();
			Session session = dao.getSession();
			DeletedataApi api = new DeletedataApi();
			isSuccess = api.deleteidList(session, patientId);
		} catch (Exception e) {
			throw e;
		} finally {
			if (dao != null)
				dao.close();
		}
		return isSuccess;
	}

	public boolean insertRiskAssessment(RiskAssessment riskAssessments, int riskAssessmentType) throws Exception {
		
		boolean isSuccess = false;

		Dao dao = null;
		try {
			dao = new Dao();
			Session session = dao.getSession();
			InsertdateApi api = new InsertdateApi();
			isSuccess = api.insertRiskAssessment(session, riskAssessments, riskAssessmentType);
		} catch (Exception e) {
			throw e;
		} finally {
			if (dao != null)
				dao.close();
		}
		
		return isSuccess;
	}

	public ArrayList<RiskAssessment> getRiskAssessment(String doctorId, String patientId) throws Exception {
		
		ArrayList<RiskAssessment> riskAssessment = null;
		Dao dao = null;
		try {
			dao = new Dao();
			Session session = dao.getSession();
			GetApi api = new GetApi();
			riskAssessment = api.getRiskAssessment(session, doctorId, patientId);
		} catch (Exception e) {
			throw e;
		} finally {
			if (dao != null)
				dao.close();
		}
		
		return riskAssessment;
	}
}
