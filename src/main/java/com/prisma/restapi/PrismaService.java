package com.prisma.restapi;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

//import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.datastax.driver.core.Session;
import com.google.gson.Gson;
import com.prisma.pojo.DoctorInterventions;
import com.prisma.pojo.DoctorMitigation;
import com.prisma.pojo.DoctorRegistration;
import com.prisma.pojo.IndexPatient;
import com.prisma.pojo.Mortality;
import com.prisma.pojo.OutComeICUPojo;
import com.prisma.pojo.OutcomeRank1;
import com.prisma.pojo.OutcomeStats;
import com.prisma.pojo.PatientDetails;
import com.prisma.pojo.PatientDetailsRaw;
import com.prisma.pojo.PatientRecord;
import com.prisma.pojo.Reco;
import com.prisma.pojo.RecoCase;
import com.prisma.pojo.RecoTaken;
import com.prisma.pojo.ReturnVal;
import com.prisma.pojo.ReviewResult;
import com.prisma.pojo.RiskAssessment;
import com.prisma.pojo.OutComeResult;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

@Path("/WebService")
public class PrismaService {
	
	private ReturnVal retVal = null;
	private String apiValues = null;
	final static Logger logger = Logger.getLogger(PrismaService.class);
	private Gson gson = new Gson();

	@GET
	@Path("/GetScores")
	@Produces("application/json")
	public ArrayList feed() {
		String feeds = null;
		ArrayList<ScorePojo> scoreLists = null;
		try {			
			logger.debug("GetScores");
			PrismaManager prismaManager = new PrismaManager();
			scoreLists = prismaManager.GetScoreObj();
			
			logger.debug("<<<<<<<<<<<<<<<<"+scoreLists);
			//Gson gson = new Gson();
			//logger.debug(gson.toJson(scoreLists));
			//feeds = gson.toJson(scoreLists);
			logger.debug(gson.toJson(scoreLists));

		} catch (Exception e) {
			logger.debug("error:"+e.getMessage());
		}
		//return feeds;
		return scoreLists;
	}

	@GET
	@Path("/GetICUOutCome")
	@Produces("application/json")
	public ArrayList icu_outcome() {
		ArrayList<OutComeICUPojo> icuOutcomeLists = null;
		try {			
			logger.debug("GetICUOutCome ");
			PrismaManager prismaManager = new PrismaManager();
			icuOutcomeLists = prismaManager.GetOutcome_ICU();
			//Gson gson = new Gson();
			//logger.debug(gson.toJson(icuOutcomeLists));
			//apiValues = gson.toJson(icuOutcomeLists);

		} catch (Exception e) {
			logger.debug("error:"+e.getMessage());
			e.getStackTrace();
		}
		logger.debug(gson.toJson(icuOutcomeLists));
		return icuOutcomeLists;
	}
	
	@GET
	@Path("/noOfAttempt")
	@Produces(MediaType.APPLICATION_JSON)
	public int noOfAttempt(@QueryParam("user") String username) {
		
		int attempt = 0;
		try {			
	
			logger.debug("noOfAttempt username="+username);
			PrismaManager prismaManager = new PrismaManager();			
			
			attempt 			= prismaManager.noOfAttempt(username);
			
		} catch (Exception e) {
			logger.debug("error:"+e.getMessage());
			e.getStackTrace();
		}
		logger.debug(gson.toJson(attempt));
		return attempt;
	}
	
	@GET
	@Path("/reviewResults")
	@Produces(MediaType.APPLICATION_JSON)
	public ReviewResult reviewResults(@QueryParam("outcomeID") int outcomeID) {
		
		ReviewResult review = null;
		int noUsers = 0;
		try {			
			/*if(logger.isDebugEnabled()){
				logger.debug("This is debug : " + parameter);
			}*/
			logger.debug("reviewResults outcomeID="+outcomeID);
			PrismaManager prismaManager = new PrismaManager();			
			
			review 			= prismaManager.reviewResults(outcomeID);
			
		} catch (Exception e) {
			logger.debug("error:"+e.getMessage());
			e.getStackTrace();
		}
		logger.debug(gson.toJson(review));
		return review;
	}
	
	@GET
	@Path("/patientPrediction")
	@Produces(MediaType.APPLICATION_JSON)
	public List OnePatientPrediction(@QueryParam("doctorId") String doctorId,
			@QueryParam("patientId") String patientId) throws Exception {
		List outcomeRankList =null;
		
		try {
			//logger.debug("<<<<<<<<<<<<<<<userId="+userId+" password="+password);
			logger.debug("OnePatientPrediction doctorId="+doctorId+" patientId="+patientId);
			PrismaManager prismaManager = new PrismaManager();			
			
			outcomeRankList 			= prismaManager.patientPrediction(doctorId,patientId);
			Gson gson = new Gson();
			logger.debug(gson.toJson(outcomeRankList));
			
		} catch (Exception e) {
			logger.debug("error:"+e.getMessage());
			e.getStackTrace();
		}
		logger.debug(gson.toJson(outcomeRankList));
		return outcomeRankList;
	}
	
	/*@GET
	@Path("/patientPrediction")
	@Produces(MediaType.APPLICATION_JSON)
	public List OnePatientPrediction(@QueryParam("doctorId") String doctorId,
			@QueryParam("patientId") String patientId, @QueryParam("outcomeId") int outcomeId) {
		List outcomeRankList =null;
		try {
			//logger.debug("<<<<<<<<<<<<<<<userId="+userId+" password="+password);
			logger.debug("OnePatientPrediction doctorId="+doctorId+" patientId="+patientId);
			PrismaManager prismaManager = new PrismaManager();			
			
			outcomeRankList 			= prismaManager.patientPrediction(doctorId,patientId,outcomeId);
			
		} catch (Exception e) {
			logger.debug("error");
			e.getStackTrace();
		}
		return outcomeRankList;
	}*/
	
	@GET
	@Path("/patientDetails")
	@Produces(MediaType.APPLICATION_JSON)
	public PatientDetails OnePatientDetail(@QueryParam("doctorId") String doctorId,
			@QueryParam("patientId") String patientId) {
		PatientDetails patientDetails = null;
		try {
			//logger.debug("<<<<<<<<<<<<<<<userId="+userId+" password="+password);
			logger.debug("OnePatientDetail doctorId="+doctorId+" patientId="+patientId);
			PrismaManager prismaManager = new PrismaManager();			
			
			patientDetails 			= prismaManager.onePatient(doctorId,patientId);
			
		} catch (Exception e) {
			logger.debug("error:"+e.getMessage());
			e.getStackTrace();
		}
		
		logger.debug(gson.toJson(patientDetails));
		return patientDetails;
	}
	
	@GET
	@Path("/patients")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<PatientDetails> patientDetail(@QueryParam("doctorId") String doctorId) {
		ArrayList<PatientDetails> patientDetails = null;
		try {
			//logger.debug("<<<<<<<<<<<<<<<userId="+userId+" password="+password);
			logger.debug("patientDetail doctorId="+doctorId);
			PrismaManager prismaManager = new PrismaManager();			
			
			patientDetails 			= prismaManager.patientDetail(doctorId);
			
		} catch (Exception e) {
			logger.debug("error:"+e.getMessage());
			e.getStackTrace();
		}
		logger.debug(gson.toJson(patientDetails));
		return patientDetails;
	}
	
	@GET
	@Path("/patientDetailsRaw")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<PatientDetailsRaw> OnePatientDetailsRaw(@QueryParam("patientId") String patientId) {
		ArrayList<PatientDetailsRaw> patientDetailsRaw = null;
		try {
			//logger.debug("<<<<<<<<<<<<<<<userId="+userId+" password="+password);
			logger.debug("OnePatientDetailsRaw patientId="+patientId);
			PrismaManager prismaManager = new PrismaManager();			
			
			patientDetailsRaw 			= prismaManager.OnePatientDetailsRaw(patientId);
			
		} catch (Exception e) {
			logger.debug("error:"+e.getMessage());
			e.getStackTrace();
		}
		
		logger.debug(gson.toJson(patientDetailsRaw));
		return patientDetailsRaw;
	}

	@GET
	@Path("/userCheck")
	@Produces(MediaType.APPLICATION_JSON)
	public int userCheck(@QueryParam("user") String userId) {
		int noUsers = 0;
		try {
			logger.debug("<<<<<<<<<<<<<<<userId="+userId);
			logger.debug("userCheck userId="+userId);
			PrismaManager prismaManager = new PrismaManager();			
			
			noUsers 			= prismaManager.userCheck(userId);
			
		} catch (Exception e) {
			logger.debug("error:"+e.getMessage());
			e.getStackTrace();
		}
		logger.debug(gson.toJson(noUsers));
		return noUsers;
	}	
	
	
	@GET
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	public int loginDoctor(@QueryParam("user") String userId , @QueryParam("password") String password) {
		int noUsers = 0;
		try {			
			/*if(logger.isDebugEnabled()){
				logger.debug("This is debug : " + parameter);
			}*/
			logger.debug("loginDoctor userId="+userId);
			PrismaManager prismaManager = new PrismaManager();			
			
			noUsers 			= prismaManager.login(userId, password);
			
		} catch (Exception e) {
			logger.debug("error:"+e.getMessage());
			e.getStackTrace();
		}
		logger.debug(gson.toJson(noUsers));
		return noUsers;
	}
	
	@POST
	@Path("/insertOutcomeRank")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ReturnVal insertOutcomeRank(OutcomeRank1[] outcomeRank) {
		retVal = new ReturnVal();
		boolean isSuccess=false;
		try {
			logger.debug("insertOutcomeRank ");

			logger.debug("<<<<<<<<<<<<<<<"+outcomeRank.toString()+" length:"+outcomeRank.length);
			
			PrismaManager prismaManager = new PrismaManager();

			isSuccess = prismaManager.insertOutcomeRank(outcomeRank);

			retVal.setSuccess(isSuccess);
		} catch (Exception e) {
			logger.debug("error:"+e.getMessage());
			e.getStackTrace();
		}
		logger.debug(gson.toJson(retVal));
		return retVal;
	}
			
	@POST
	@Path("/insertRecoTable")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ReturnVal insertRecoTable(Reco[] reco) {
		retVal = new ReturnVal();
		boolean isSuccess;
		try {
			logger.debug("insertRecoTable ");
			//logger.debug("<<<<<<<<<<<<<<<"+doctorReg);
			PrismaManager prismaManager = new PrismaManager();			
			
			isSuccess 			= prismaManager.insertRecoTable(reco);
			
			retVal.setSuccess(isSuccess);
		} catch (Exception e) {
			logger.debug("error:"+e.getMessage());
			e.getStackTrace();
		}
		logger.debug(gson.toJson(retVal));
		return retVal;
	}
	
	@POST
	@Path("/insertRecoCaseTable")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ReturnVal insertRecoCaseTable(RecoCase[] recoCase) {
		retVal = new ReturnVal();
		boolean isSuccess;
		try {
			logger.debug("insertRecoCaseTable");
			//logger.debug("<<<<<<<<<<<<<<<"+doctorReg);
			PrismaManager prismaManager = new PrismaManager();			
			
			isSuccess 			= prismaManager.insertRecoCaseTable(recoCase);
			
			retVal.setSuccess(isSuccess);
		} catch (Exception e) {
			logger.debug("error:"+e.getMessage());
			e.getStackTrace();
		}
		logger.debug(gson.toJson(retVal));
		return retVal;
	}
	
	@POST
	@Path("/insertOutcomeStats")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ReturnVal insertOutcomeStats(OutcomeStats outComeStats) {
		retVal = new ReturnVal();
		boolean isSuccess;
		try {
			logger.debug("insertOutcomeStats");
			//logger.debug("<<<<<<<<<<<<<<<"+doctorReg);
			PrismaManager prismaManager = new PrismaManager();			
			
			isSuccess 			= prismaManager.insertOutcomeStats(outComeStats);
			
			retVal.setSuccess(isSuccess);
		} catch (Exception e) {
			logger.debug("error:"+e.getMessage());
			e.getStackTrace();
		}
		logger.debug(gson.toJson(retVal));
		return retVal;
	}
	
	
	@POST
	@Path("/insertRecoTakenTable")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ReturnVal insertRecoTakenTable(RecoTaken recoTaken) {
		retVal = new ReturnVal();
		boolean isSuccess;
		try {
			logger.debug("insertRecoTakenTable");
			//logger.debug("<<<<<<<<<<<<<<<"+doctorReg);
			PrismaManager prismaManager = new PrismaManager();			
			
			isSuccess 			= prismaManager.insertRecoTakenTable(recoTaken);
			
			retVal.setSuccess(isSuccess);
		} catch (Exception e) {
			logger.debug("error:"+e.getMessage());
			e.getStackTrace();
		}
		logger.debug(gson.toJson(retVal));
		return retVal;
	}
	
	@DELETE
	@Path("/deleteidList")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ReturnVal deleteidList(String patientId) {
		retVal = new ReturnVal();
		boolean isSuccess;
		try {
			logger.debug("deleteidList patientId="+patientId);
			//logger.debug("<<<<<<<<<<<<<<<"+doctorReg);
			PrismaManager prismaManager = new PrismaManager();			
			
			isSuccess 			= prismaManager.deleteidList(patientId);
			
			retVal.setSuccess(isSuccess);
		} catch (Exception e) {
			logger.debug("error:"+e.getMessage());
			e.getStackTrace();
		}
		logger.debug(gson.toJson(retVal));
		return retVal;
	}
	
	@POST
	@Path("/insertOutcomeResult")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ReturnVal insertOutcomeResult(OutComeResult[] outComeResult) {
		retVal = new ReturnVal();
		boolean isSuccess;
		try {
			logger.debug("insertOutcomeResult");
			//logger.debug("<<<<<<<<<<<<<<<"+doctorReg);
			PrismaManager prismaManager = new PrismaManager();			
			
			isSuccess 			= prismaManager.insertOutcomeResult(outComeResult);
			
			retVal.setSuccess(isSuccess);
		} catch (Exception e) {
			logger.debug("error:"+e.getMessage());
			e.getStackTrace();
		}
		logger.debug(gson.toJson(retVal));
		return retVal;
	}
	
	@POST
	@Path("/insertIndexPatient")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ReturnVal insertIndexPatient(IndexPatient indexPatient) {
		retVal = new ReturnVal();
		boolean isSuccess;
		try {
			logger.debug("insertIndexPatient");
			//logger.debug("<<<<<<<<<<<<<<<"+doctorReg);
			PrismaManager prismaManager = new PrismaManager();			
			
			isSuccess 			= prismaManager.insertIndexPatient(indexPatient);
			
			retVal.setSuccess(isSuccess);
		} catch (Exception e) {
			logger.debug("error:"+e.getMessage());
			e.getStackTrace();
		}
		logger.debug(gson.toJson(retVal));
		return retVal;
	}
	

	@POST
	@Path("/insertDoctorTestResults")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public DoctorRegistration insertDoctorTestResults(DoctorRegistration doctorReg) {
		retVal = new ReturnVal();
		boolean isSuccess, isSuccessDoctorReg;
		try {
			logger.debug("insertDoctorTestResults");
			//logger.debug("<<<<<<<<<<<<<<<"+doctorReg);
			PrismaManager prismaManager = new PrismaManager();			
			
			isSuccess 			= prismaManager.insertDoctorTestResult(doctorReg);
			isSuccessDoctorReg  = prismaManager.insertDoctorInfo(doctorReg);
			
			retVal.setSuccess(isSuccess && isSuccessDoctorReg);
		} catch (Exception e) {
			logger.debug("error:"+e.getMessage());
			e.getStackTrace();
		}
		logger.debug(gson.toJson(doctorReg));
		return doctorReg;
	}
	
	@POST
	@Path("/initialRiskAssessment")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ReturnVal initialRiskAssessment(RiskAssessment riskAssessment) {
		retVal = new ReturnVal();
		boolean isSuccess;
		try {
			logger.debug("initialRiskAssessment");
			//logger.debug("<<<<<<<<<<<<<<<"+doctorReg);
			PrismaManager prismaManager = new PrismaManager();			
			
			isSuccess 			= prismaManager.insertRiskAssessment(riskAssessment, 1);
			
			retVal.setSuccess(isSuccess);
		} catch (Exception e) {
			logger.debug("error:"+e.getMessage());
			e.getStackTrace();
		}
		logger.debug(gson.toJson(retVal));
		return retVal;
	}
	
	@POST
	@Path("/finalRiskAssessment")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ReturnVal finalRiskAssessment(RiskAssessment riskAssessment) {
		retVal = new ReturnVal();
		boolean isSuccess;
		try {
			logger.debug("finalRiskAssessment");
			//logger.debug("<<<<<<<<<<<<<<<"+doctorReg);
			PrismaManager prismaManager = new PrismaManager();			
			
			isSuccess 			= prismaManager.insertRiskAssessment(riskAssessment, 2);
			
			retVal.setSuccess(isSuccess);
		} catch (Exception e) {
			logger.debug("error:"+e.getMessage());
			e.getStackTrace();
		}
		logger.debug(gson.toJson(retVal));
		return retVal;
	}
	
	@GET
	@Path("/getRiskAssessment")
	@Produces(MediaType.APPLICATION_JSON)
	public List getRiskAssessment(@QueryParam("doctorId") String doctorId,
			@QueryParam("patientId") String patientId, @QueryParam("riskAssessmentType") int riskAssessmentType) {
		
		ArrayList<RiskAssessment> riskAssessments = null;
		try {
			logger.debug("getRiskAssessment");
			//logger.debug("<<<<<<<<<<<<<<<"+doctorReg);
			PrismaManager prismaManager = new PrismaManager();			
			
			riskAssessments 			= prismaManager.getRiskAssessment(doctorId, patientId, riskAssessmentType);
			
		} catch (Exception e) {
			logger.debug("error:"+e.getMessage());
			e.getStackTrace();
		}
		logger.debug(gson.toJson(riskAssessments));
		return riskAssessments;
	}
	
	@POST
	@Path("/docInterventions")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ReturnVal docInterventions(DoctorInterventions doctorInterventions) {
		retVal = new ReturnVal();
		boolean isSuccess;
		try {
			logger.debug("docInterventions");
			//logger.debug("<<<<<<<<<<<<<<<"+doctorReg);
			PrismaManager prismaManager = new PrismaManager();			
			
			isSuccess 			= prismaManager.docInterventions(doctorInterventions);
			
			retVal.setSuccess(isSuccess);
		} catch (Exception e) {
			logger.debug("error:"+e.getMessage());
			e.getStackTrace();
		}
		logger.debug(gson.toJson(retVal));
		return retVal;
	}
	
	@POST
	@Path("/docMitigations")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ReturnVal docMitigations(DoctorMitigation doctorMitigation) {
		retVal = new ReturnVal();
		boolean isSuccess;
		try {
			logger.debug("docMitigations");
			//logger.debug("<<<<<<<<<<<<<<<"+doctorReg);
			PrismaManager prismaManager = new PrismaManager();			
			
			isSuccess 			= prismaManager.docMitigations(doctorMitigation);
			
			retVal.setSuccess(isSuccess);
		} catch (Exception e) {
			logger.debug("error:"+e.getMessage());
			e.getStackTrace();
		}
		logger.debug(gson.toJson(retVal));
		return retVal;
	}
	
	@GET
	@Path("/mortality")
	@Produces(MediaType.APPLICATION_JSON)
	public Mortality mortality(@QueryParam("patientId") String patientId) {
		Mortality patientMortality =null;
		try {
			//logger.debug("<<<<<<<<<<<<<<<userId="+userId+" password="+password);
			logger.debug("mortality  patientId="+patientId);
			PrismaManager prismaManager = new PrismaManager();			
			
			patientMortality 			= prismaManager.mortality(patientId);
			
		} catch (Exception e) {
			logger.debug("error:"+e.getMessage());
			e.getStackTrace();
		}
		logger.debug(gson.toJson(patientMortality));
		return patientMortality;
	}
	
	@GET
	@Path("/patientRecords")
	@Produces(MediaType.APPLICATION_JSON)
	public PatientRecord patienRecords(@QueryParam("patientId") String patientId) {
		
		PatientRecord patientRecords = null;
		try {
			logger.debug("patientRecords");
			//logger.debug("<<<<<<<<<<<<<<<"+doctorReg);
			PrismaManager prismaManager = new PrismaManager();			
			
			patientRecords 			= prismaManager.getPatienRecords(patientId);
			
		} catch (Exception e) {
			logger.debug("error:"+e.getMessage());
			e.getStackTrace();
		}
		logger.debug(gson.toJson(patientRecords));
		return patientRecords;
	}
	
	@POST
	@Path("/insertDoctorAgreement")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public ReturnVal uploadDoctorAgreement(@FormDataParam("file") InputStream uploadedInputStream, 
			@FormDataParam("file") FormDataContentDisposition fileDetail) {
		
		retVal = new ReturnVal();
		boolean isSuccess;
		try {
			logger.debug("uploadFile");
			//logger.debug("<<<<<<<<<<<<<<<"+doctorReg);
			String fileName = fileDetail.getFileName();
			
			PrismaManager prismaManager = new PrismaManager();			
			
			isSuccess 			= prismaManager.uploadDoctorAgreement(uploadedInputStream, fileName);
			
			retVal.setSuccess(isSuccess);
		} catch (Exception e) {
			logger.debug("error:"+e.getMessage());
			e.getStackTrace();
		}
		logger.debug(gson.toJson(retVal));
		return retVal;
	}
}

