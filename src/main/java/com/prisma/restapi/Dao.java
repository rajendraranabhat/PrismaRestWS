package com.prisma.restapi;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Host;
import com.datastax.driver.core.Metadata;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.policies.DefaultRetryPolicy;
import com.prisma.api.GetApi;

public class Dao {
	
	private Cluster cluster;
	private Session session = null;
	private OutputStream output = null;
	private Properties prop = new Properties();
	private InputStream input = null;
	final static Logger logger = Logger.getLogger(Dao.class);
	
	public void connect(String node, int port) {
		cluster = Cluster.builder().addContactPoint(node)
								   .withRetryPolicy(DefaultRetryPolicy.INSTANCE)
								   //.withLoadBalancingPolicy(new TokenAwarePolicy(new DCAwareRoundRobinPolicy()))
                         			.build();
		                 			//.addContactPoint(node).withPort(port).build();
		Metadata metadata = cluster.getMetadata();
		System.out.printf("Cluster: %s\n", metadata.getClusterName());
		for ( Host host : metadata.getAllHosts() ) {
			System.out.printf("Host: %s \n",host.getAddress());
		}
		this.session = cluster.connect();
	}
	
	public void close(){
		this.session.close();
		this.cluster.close();
	}
	
	public Session getSession() throws Exception {
		try{
			//logger.debug(System.getProperty("user.dir")); //Get the root directory.
			//input = new FileInputStream("src/main/resources/config.properties");
			//input = new FileInputStream("config.properties");
			//System.out.println("input"+input);
			
			//InputStream input = Dao.class.getResourceAsStream("config.properties");
			//load a properties file
			//prop.load(input);
			//String db_url = prop.getProperty("dburl");
			// get the property value and print it out
			//logger.debug("db_url:"+db_url);
			//System.out.println("db_url:"+db_url);
			
			if(this.session==null){
				//connect(db_url,9042);
				//connect("172.17.0.5",9042);
				//connect("deepc04.acis.ufl.edu",9042); //"deepc04.acis.ufl.edu"
				connect("localhost",9042);
			}				
		}
		catch(Exception e){
			throw e;
		}
		
		return this.session;
	}
}
