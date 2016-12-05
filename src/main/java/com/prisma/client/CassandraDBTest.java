package com.prisma.client;

import com.prisma.restapi.PrismaManager;

public class CassandraDBTest {

	public static void getPatientDetails() throws Exception{
		PrismaManager manager = new PrismaManager();
		manager.patientDetail("raj");
	}
	
	public static void onePatient() throws Exception{
		PrismaManager manager = new PrismaManager();
		manager.onePatient("raj", "BB3XEDJ201");
	}
	
	public static void patientPrediction() throws Exception{
		PrismaManager manager = new PrismaManager();
		manager.patientPrediction("raj", "BB3XEDJ201",1);
	}
	
	public static void noAttempt() throws Exception{
		PrismaManager manager = new PrismaManager();
		manager.noOfAttempt("raj");
	}
	
	public static void main(String[] args) throws Exception {		
		noAttempt();
	}

}
