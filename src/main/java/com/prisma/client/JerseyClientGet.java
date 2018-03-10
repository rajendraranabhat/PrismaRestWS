package com.prisma.client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class JerseyClientGet {

	  public static void main(String[] args) {
		try {

			Client client = Client.create();
			
			//String url = "http://localhost:8081/PrismaRestWS/rest/WebService/login?user=10893&password=10893";
			//String url = "http://localhost:8081/PrismaRestWS/rest/WebService/userCheck?user=raj1";
			//String url = "http://localhost:8081/PrismaRestWS/rest/WebService/patients?doctorId=10893";
			String url = "http://localhost:8081/PrismaRestWS/rest/WebService/patientsAdmin?doctorId=10893";
			//String url = "http://localhost:8081/PrismaRestWS/rest/WebService/patientDetails?doctorId=raj&patientId=BB3XEDJ201";
			//String url = "http://localhost:8081/PrismaRestWS/rest/WebService/patientPrediction?doctorId=raj&patientId=BB3XEDJ201&outcomeId=1";
			//String url = "http://localhost:8081/PrismaRestWS/rest/WebService/patientPrediction?doctorId=10294&patientId=14675";
			//String url = "http://localhost:8081/PrismaRestWS/rest/WebService/reviewResults?outcomeID=1";
			//String url = "http://localhost:8081/PrismaRestWS/rest/WebService/noOfAttempt?user=raj";
			//String url = "http://localhost:8081/PrismaRestWS/rest/WebService/getRiskAssessment?doctorId=raj&patientId=A1F40528";
			//String url="http://localhost:8081/PrismaRestWS/rest/WebService/mortality?patientId=826";
			//String url="http://localhost:8081/PrismaRestWS/rest/WebService/patienRecords?patientId=3496";
			//String url="http://localhost:8081/PrismaRestWS/rest/WebService/getPageComplete?doctorId=10893&patientId=123";
			//String url="http://localhost:8081/PrismaRestWS/rest/WebService/getUserRole?doctorId=108931";
			
			WebResource webResource = client.resource(url);

			ClientResponse response = webResource.accept("application/json")
	                   .get(ClientResponse.class);

			if (response.getStatus() != 200) {
			   throw new RuntimeException("Failed : HTTP error code : "
				+ response.getStatus());
			}

			String output = response.getEntity(String.class);

			System.out.println("Output from Server .... \n");
			System.out.println(output);

		  } catch (Exception e) {

			e.printStackTrace();

		  }

		}
	}

