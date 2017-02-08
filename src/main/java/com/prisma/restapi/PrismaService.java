package com.prisma.restapi;

import java.util.ArrayList;


import java.util.List;











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
import com.prisma.pojo.DoctorRegistration;
import com.prisma.pojo.IndexPatient;
import com.prisma.pojo.OutComeICUPojo;
import com.prisma.pojo.OutcomeRank1;
import com.prisma.pojo.OutcomeStats;
import com.prisma.pojo.PatientDetails;
import com.prisma.pojo.Reco;
import com.prisma.pojo.RecoCase;
import com.prisma.pojo.RecoTaken;
import com.prisma.pojo.ReturnVal;
import com.prisma.pojo.ReviewResult;
import com.prisma.pojo.OutComeResult;

@Path("/WebService")
public class PrismaService {
	
	private ReturnVal retVal = null;
	private String apiValues = null;
	final static Logger logger = Logger.getLogger(PrismaService.class);

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
			
			System.out.println("<<<<<<<<<<<<<<<<"+scoreLists);
			//Gson gson = new Gson();
			//System.out.println(gson.toJson(scoreLists));
			//feeds = gson.toJson(scoreLists);

		} catch (Exception e) {
			System.out.println("error");
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
			//System.out.println(gson.toJson(icuOutcomeLists));
			//apiValues = gson.toJson(icuOutcomeLists);

		} catch (Exception e) {
			System.out.println("error");
			e.getStackTrace();
		}
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
			System.out.println("error");
			e.getStackTrace();
		}
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
			System.out.println("error");
			e.getStackTrace();
		}
		return review;
	}
	
	@GET
	@Path("/patientPrediction")
	@Produces(MediaType.APPLICATION_JSON)
	public List OnePatientPrediction(@QueryParam("doctorId") String doctorId,
			@QueryParam("patientId") String patientId, @QueryParam("outcomeId") int outcomeId) {
		List outcomeRankList =null;
		try {
			//System.out.println("<<<<<<<<<<<<<<<userId="+userId+" password="+password);
			logger.debug("OnePatientPrediction doctorId="+doctorId+" patientId="+patientId);
			PrismaManager prismaManager = new PrismaManager();			
			
			outcomeRankList 			= prismaManager.patientPrediction(doctorId,patientId,outcomeId);
			
		} catch (Exception e) {
			System.out.println("error");
			e.getStackTrace();
		}
		return outcomeRankList;
	}
	
	@GET
	@Path("/patientDetails")
	@Produces(MediaType.APPLICATION_JSON)
	public PatientDetails OnePatientDetail(@QueryParam("doctorId") String doctorId,
			@QueryParam("patientId") String patientId) {
		PatientDetails patientDetails = null;
		try {
			//System.out.println("<<<<<<<<<<<<<<<userId="+userId+" password="+password);
			logger.debug("OnePatientDetail doctorId="+doctorId+" patientId="+patientId);
			PrismaManager prismaManager = new PrismaManager();			
			
			patientDetails 			= prismaManager.onePatient(doctorId,patientId);
			
		} catch (Exception e) {
			System.out.println("error");
			e.getStackTrace();
		}
		return patientDetails;
	}
	
	@GET
	@Path("/patients")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<PatientDetails> patientDetail(@QueryParam("doctorId") String doctorId) {
		ArrayList<PatientDetails> patientDetails = null;
		try {
			//System.out.println("<<<<<<<<<<<<<<<userId="+userId+" password="+password);
			logger.debug("patientDetail doctorId="+doctorId);
			PrismaManager prismaManager = new PrismaManager();			
			
			patientDetails 			= prismaManager.patientDetail(doctorId);
			
		} catch (Exception e) {
			System.out.println("error");
			e.getStackTrace();
		}
		return patientDetails;
	}

	@GET
	@Path("/userCheck")
	@Produces(MediaType.APPLICATION_JSON)
	public int userCheck(@QueryParam("user") String userId) {
		int noUsers = 0;
		try {
			System.out.println("<<<<<<<<<<<<<<<userId="+userId);
			logger.debug("userCheck userId="+userId);
			PrismaManager prismaManager = new PrismaManager();			
			
			noUsers 			= prismaManager.userCheck(userId);
			
		} catch (Exception e) {
			System.out.println("error");
			e.getStackTrace();
		}
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
			System.out.println("error");
			e.getStackTrace();
		}
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
<<<<<<< HEAD
			logger.debug("insertOutcomeRank ");
=======
>>>>>>> 1339062b3490dbfb2ad33810659b3701d7de3a03
			System.out.println("<<<<<<<<<<<<<<<"+outcomeRank.toString()+" length:"+outcomeRank.length);
			
			PrismaManager prismaManager = new PrismaManager();

			isSuccess = prismaManager.insertOutcomeRank(outcomeRank);

			retVal.setSuccess(isSuccess);
		} catch (Exception e) {
			System.out.println("error");
			e.getStackTrace();
		}
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
			//System.out.println("<<<<<<<<<<<<<<<"+doctorReg);
			PrismaManager prismaManager = new PrismaManager();			
			
			isSuccess 			= prismaManager.insertRecoTable(reco);
			
			retVal.setSuccess(isSuccess);
		} catch (Exception e) {
			System.out.println("error");
			e.getStackTrace();
		}
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
			//System.out.println("<<<<<<<<<<<<<<<"+doctorReg);
			PrismaManager prismaManager = new PrismaManager();			
			
			isSuccess 			= prismaManager.insertRecoCaseTable(recoCase);
			
			retVal.setSuccess(isSuccess);
		} catch (Exception e) {
			System.out.println("error");
			e.getStackTrace();
		}
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
			//System.out.println("<<<<<<<<<<<<<<<"+doctorReg);
			PrismaManager prismaManager = new PrismaManager();			
			
			isSuccess 			= prismaManager.insertOutcomeStats(outComeStats);
			
			retVal.setSuccess(isSuccess);
		} catch (Exception e) {
			System.out.println("error");
			e.getStackTrace();
		}
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
			//System.out.println("<<<<<<<<<<<<<<<"+doctorReg);
			PrismaManager prismaManager = new PrismaManager();			
			
			isSuccess 			= prismaManager.insertRecoTakenTable(recoTaken);
			
			retVal.setSuccess(isSuccess);
		} catch (Exception e) {
			System.out.println("error");
			e.getStackTrace();
		}
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
			//System.out.println("<<<<<<<<<<<<<<<"+doctorReg);
			PrismaManager prismaManager = new PrismaManager();			
			
			isSuccess 			= prismaManager.deleteidList(patientId);
			
			retVal.setSuccess(isSuccess);
		} catch (Exception e) {
			System.out.println("error");
			e.getStackTrace();
		}
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
			//System.out.println("<<<<<<<<<<<<<<<"+doctorReg);
			PrismaManager prismaManager = new PrismaManager();			
			
			isSuccess 			= prismaManager.insertOutcomeResult(outComeResult);
			
			retVal.setSuccess(isSuccess);
		} catch (Exception e) {
			System.out.println("error");
			e.getStackTrace();
		}
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
			//System.out.println("<<<<<<<<<<<<<<<"+doctorReg);
			PrismaManager prismaManager = new PrismaManager();			
			
			isSuccess 			= prismaManager.insertIndexPatient(indexPatient);
			
			retVal.setSuccess(isSuccess);
		} catch (Exception e) {
			System.out.println("error");
			e.getStackTrace();
		}
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
			//System.out.println("<<<<<<<<<<<<<<<"+doctorReg);
			PrismaManager prismaManager = new PrismaManager();			
			
			isSuccess 			= prismaManager.insertDoctorTestResult(doctorReg);
			isSuccessDoctorReg  = prismaManager.insertDoctorInfo(doctorReg);
			
			retVal.setSuccess(isSuccess && isSuccessDoctorReg);
		} catch (Exception e) {
			System.out.println("error");
			e.getStackTrace();
		}
		return doctorReg;
	}
}
