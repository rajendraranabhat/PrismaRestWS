package com.prisma.restapi;

import java.util.ArrayList;


import java.util.List;


//import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.prisma.pojo.DoctorRegistration;
import com.prisma.pojo.OutComeICUPojo;
import com.prisma.pojo.PatientDetails;
import com.prisma.pojo.ReturnVal;

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
	@Path("/patientPrediction")
	@Produces(MediaType.APPLICATION_JSON)
	public List OnePatientPrediction(@QueryParam("doctorId") String doctorId,
			@QueryParam("patientId") String patientId, @QueryParam("outcomeId") int outcomeId) {
		List outcomeRankList =null;
		try {
			//System.out.println("<<<<<<<<<<<<<<<userId="+userId+" password="+password);
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
			logger.debug("<<<<<<<<<<<<<<<userId="+userId);
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
			logger.debug("<<<<<<<<<<<<<<<userId="+userId+" password="+password);
			PrismaManager prismaManager = new PrismaManager();			
			
			noUsers 			= prismaManager.login(userId, password);
			
		} catch (Exception e) {
			System.out.println("error");
			e.getStackTrace();
		}
		return noUsers;
	}

	@POST
	@Path("/insertDoctorTestResults")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public DoctorRegistration insertDoctorTestResults(DoctorRegistration doctorReg) {
		retVal = new ReturnVal();
		boolean isSuccess, isSuccessDoctorReg;
		try {
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
