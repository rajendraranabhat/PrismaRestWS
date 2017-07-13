package com.prisma.client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class JerseyClientPost {

	  public static void main(String[] args) {

		try {

			Client client = Client.create();

			WebResource webResource = client
			   .resource("http://localhost:8081/PrismaRestWS/rest/WebService/insertDoctorTestResults");

			String input = "{\"username\":\"raj\",\"questions\":[\"4\",\"3\",\"4\",\"1 in 10\",\"1%\",\"4\",\"4\",\"3\",\"4\",\"5\",\"5\",\"6\",\"6\",\"5\"],\"fullName\":\"eee\",\"password\":\"raj\",\"gender\":\"Male\",\"age\":\"30 years or less\",\"currentRoles\":\"Attending Doctor\",\"speciality\":\"ER\",\"experience\":\"7\"}";

			System.out.println(input);
			ClientResponse response = webResource.type("application/json").post(ClientResponse.class, input);

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
				     + response.getStatus());
			}

			System.out.println("Output from Server .... \n");
			String output = response.getEntity(String.class);
			System.out.println(output);

		  } catch (Exception e) {

			e.printStackTrace();

		  }

		}
	}