package com.prisma.client;

import java.sql.Timestamp;
import java.util.Date;

import com.prisma.pojo.Login;
import com.prisma.pojo.Mortality;
import com.prisma.pojo.PatientRecord;
import com.prisma.restapi.PrismaManager;

public class CassandraDBTest {

	public static void getPatientDetails() throws Exception{
		PrismaManager manager = new PrismaManager();
		manager.patientDetail("10893");
	}
	
	public static void getPatientDetailAdmin() throws Exception{
		PrismaManager manager = new PrismaManager();
		manager.patientDetailAdmin("10893");
	}
	
	public static void onePatient() throws Exception{
		PrismaManager manager = new PrismaManager();
		manager.onePatient("raj", "11181");
	}
	
	public static void patientPrediction() throws Exception{
		PrismaManager manager = new PrismaManager();
		manager.patientPrediction("raj", "11181");
	}
	
	public static void noAttempt() throws Exception{
		PrismaManager manager = new PrismaManager();
		manager.noOfAttempt("raj");
	}
	
	public static void getPatienRecords() throws Exception{
		PrismaManager manager = new PrismaManager();
		manager.getPatienRecords("11181");
	}
	
	public static void getMortality() throws Exception{
		PrismaManager manager = new PrismaManager();
		Mortality mort = manager.mortality("3496");
		System.out.println(mort.getMort_status_30d());
		
	}
	
	public static void getPatientRaw() throws Exception{
		PrismaManager manager = new PrismaManager();
		manager.OnePatientDetailsRaw("5059");
	}
	
	public static Login getLogin() throws Exception{
		PrismaManager manager = new PrismaManager();
		Login user = manager.login("10893", "10893");
		return user;
	}
	
	public static void getPage() throws Exception{
		PrismaManager manager = new PrismaManager();
		String page = manager.getPageComplete("10893", "1232");
		System.out.println("page="+page);
	}
	
	public static void insertPageComplete() throws Exception{
		PrismaManager manager = new PrismaManager();
		manager.insertPageComplete("10893", "123","2");
	}
	
	public static void getUserRole() throws Exception{
		PrismaManager manager = new PrismaManager();
		manager.getUserRole("10893");
	}

	
	public static void main(String[] args) throws Exception {
		//System.out.println(getLogin());
		//getPatientDetailAdmin();
		//getPatientDetails();
		//onePatient();
		//patientPrediction();
		//getPatienRecords();
		//getMortality();
		//Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		//System.out.println(timestamp.toString());
		//System.out.println(new Timestamp(new Date().getTime()));
		//getPatientRaw();
		//insertPageComplete();
		//getPage();
		getUserRole();
	}
}
