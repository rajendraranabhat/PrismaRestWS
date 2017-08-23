package com.prisma.client;

import java.sql.Timestamp;
import java.util.Date;

import com.prisma.pojo.PatientRecord;
import com.prisma.restapi.PrismaManager;

public class CassandraDBTest {

	public static void getPatientDetails() throws Exception{
		PrismaManager manager = new PrismaManager();
		manager.patientDetail("raj");
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
		manager.mortality("3496");
	}
	
	public static void main(String[] args) throws Exception {		
		//onePatient();
		//patientPrediction();
		//getPatienRecords();
		getMortality();
		//Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		//System.out.println(timestamp.toString());
		//System.out.println(new Timestamp(new Date().getTime()));
	}

}
