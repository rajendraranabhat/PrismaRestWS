package com.prisma.client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class JerseyClientGet {

	  public static void main(String[] args) {
		try {

			Client client = Client.create();
			
			//String url = "http://localhost:8081/PrismaRestWS/rest/WebService/login?user=raj1&password=raj1";
			//String url = "http://localhost:8081/PrismaRestWS/rest/WebService/userCheck?user=raj1";
			//String url = "http://localhost:8081/PrismaRestWS/rest/WebService/patients?doctorId=raj";
			//String url = "http://localhost:8081/PrismaRestWS/rest/WebService/patientDetails?doctorId=raj&patientId=BB3XEDJ201";
			//String url = "http://localhost:8081/PrismaRestWS/rest/WebService/patientPrediction?doctorId=raj&patientId=BB3XEDJ201&outcomeId=1";
			//String url = "http://localhost:8081/PrismaRestWS/rest/WebService/patientPrediction?doctorId=raj&patientId=BB3XEDJ201";
			//String url = "http://localhost:8081/PrismaRestWS/rest/WebService/reviewResults?outcomeID=1";
			//String url = "http://localhost:8081/PrismaRestWS/rest/WebService/noOfAttempt?user=raj";
			//String url = "http://localhost:8081/PrismaRestWS/rest/WebService/getRiskAssessment?doctorId=raj&patientId=A1F40528";
			String url="http://localhost:8081/PrismaRestWS/rest/WebService/mortality?patientId=A1F42085";
			
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

