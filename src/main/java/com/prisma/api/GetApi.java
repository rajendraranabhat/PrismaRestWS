package com.prisma.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.prisma.pojo.Mortality;
import com.prisma.pojo.OutComeICUPojo;
import com.prisma.pojo.OutcomeRank;
import com.prisma.pojo.PatientDetails;
import com.prisma.pojo.PatientRecord;
import com.prisma.pojo.OutcomeRankFeature;
import com.prisma.pojo.ReviewResult;
import com.prisma.pojo.RiskAssessment;
import com.prisma.restapi.PrismaManager;

public class GetApi {
	
	final static Logger logger = Logger.getLogger(GetApi.class);
	
	public String executeQuery(Session session, String query, String colName){
		String returnVal = "";
		//System.out.println("query="+query+" colName="+colName);
		//System.out.println("query="+query);
		ResultSet results = session.execute(query.toLowerCase());
		for(Row row: results){
			returnVal = row.getString(colName);
		}
		//System.out.println("<<<<<<returnVal="+returnVal);

		return returnVal;
	}
	
	public ArrayList executeQuery1(Session session, String query, String colName){
		ArrayList<String> returnVal = new ArrayList<String>();
		String retVal = "";
		//System.out.println("query="+query+" colName="+colName);
		ResultSet results = session.execute(query.toLowerCase());
		for(Row row: results){
			retVal=row.getString(colName);
			returnVal.add(retVal);
		}
		//System.out.println("<<<<<<returnVal="+returnVal);
		return returnVal;
	}
	
	public int userCheck(Session session, String userId) {

		int noRow = 0;
		
		System.out.println("select count(*) as cnt from prisma1.userinfo where id='"+userId+"' allow filtering");
		
		ResultSet results = session
				.execute("select count(*) as cnt from prisma1.userinfo where id='"+userId+"' allow filtering");
			
		System.out.println("results=" + results);
		
		for (Row row : results){
			System.out.println("cnt="+row.getLong("cnt"));
			noRow = (int)row.getLong("cnt");
			System.out.println("noRow="+noRow);
		}

		System.out.println("results=" + results+" noRow="+noRow);
		return noRow;		
	}
	
	public int noOfAttempt(Session session, String userName){
		
		int noAttempt = 0;
		
		String attemptQuery = "select count(attempt) as attempt from prisma1.indexPatient where user='"+userName+"' allow filtering";
		
		ResultSet results = session.execute(attemptQuery);
		for (Row row : results){
			//System.out.println("cnt="+row.getLong("attempt"));
			noAttempt = (int)row.getLong("attempt");
			//System.out.println("noRow="+noAttempt);
		}		
		
		return noAttempt;
	}
	
	/*public List patientPrediction(Session session, String doctorId, String patientId){
		int id;
		List outcomeList = new ArrayList();
		List ids = new ArrayList();
		String outcomeTableQuery = "select id from prisma1.outcomes";
		ResultSet results = session.execute(outcomeTableQuery);
		for(Row row: results){
			id = row.getInt("id");
			ids.add(id);
			//outcomeList.add(patientPrediction(session, doctorId, patientId, id));
		}
		Collections.sort(ids);
		
		for(int i=0;i<ids.size();i++){
			System.out.println("id="+ (Integer)ids.get(i) );
			outcomeList.add(patientPrediction(session, doctorId, patientId, (Integer)ids.get(i)) );
		}
		
		return outcomeList;
	}*/
	
	public List patientPrediction(Session session, String doctorId, String patientId){
		
		String outcomeTable="",label="",outcome="";
		//List outcomeRankRifle7List = new ArrayList();
		ArrayList<OutcomeRankFeature> outcomeRankFeatureList = new ArrayList<OutcomeRankFeature>();//id,var,rank,weight
		ArrayList<OutcomeRankFeature> outcomeRankFeatures = new ArrayList<OutcomeRankFeature>();//id,var,rank,weight
		
		OutcomeRankFeature outcomeRankFeature = null;
				
		/*String outcomeTableQuery = "select outcome,description from prisma1.outcomes where id="+outcomeId+" allow filtering";
		ResultSet results = session.execute(outcomeTableQuery);
		for(Row row: results){
			outcomeTable = row.getString("outcome");
			label 		 = row.getString("description");			
		}
		System.out.println("outcomeTable="+outcomeTable+" label="+label);*/
		
		String cvcomppred="", icucomppred="", mvcomppred="", neurodelpred="", proc_wound_pred="", sepsispred="", vtepred="";
		String riskScores = "select * from prisma1.allriskscores where patient_deiden_id='"+patientId+"' allow filtering";
		ResultSet results = session.execute(riskScores);
		for (Row row : results){
			cvcomppred = row.getString("cvcomppred");
			icucomppred = row.getString("icucomppred");
			mvcomppred = row.getString("mvcomppred");
			neurodelpred = row.getString("neurodelpred");
			proc_wound_pred = row.getString("proc_wound_pred");
			sepsispred = row.getString("sepsispred");
			vtepred = row.getString("vtepred");
		}
		
		String impFeatureQuery = "select * from prisma1.impfeatures where patient_deiden_id='"+patientId+"' allow filtering";
		results = session.execute(impFeatureQuery);

		for (Row row : results){
			outcomeRankFeature = new OutcomeRankFeature();
			outcomeRankFeature.setEncounter_deiden_id(row.getString("encounter_deiden_id"));
			outcomeRankFeature.setPatient_deiden_id(row.getString("patient_deiden_id"));
			String complicationName = row.getString("complicationname");
			//outcomeRankFeature.setComplicationname(complicationName);
			
			if(complicationName.equalsIgnoreCase("ICU_duration")){
				outcomeRankFeature.setRiskScore(icucomppred);
				outcomeRankFeature.setComplicationname("ICU Admission > 48 hours");
			}
			else if(complicationName.equalsIgnoreCase("cardiovasc_comp_comb")){
				outcomeRankFeature.setRiskScore(cvcomppred);
				outcomeRankFeature.setComplicationname("Cardiovascular complications");
			}
			else if(complicationName.equalsIgnoreCase("neuro_comp_comb")){
				outcomeRankFeature.setRiskScore(neurodelpred);
				outcomeRankFeature.setComplicationname("Neurologic complications");
			}
			else if(complicationName.equalsIgnoreCase("post_op_mech_proc_comb")){
				outcomeRankFeature.setRiskScore(mvcomppred);
				outcomeRankFeature.setComplicationname("Wound complications");
			}
			else if(complicationName.equalsIgnoreCase("sepsis_comb")){
				outcomeRankFeature.setRiskScore(sepsispred);
				outcomeRankFeature.setComplicationname("Severe sepsis");
			}
			else if(complicationName.equalsIgnoreCase("venos_throm_comb")){
				outcomeRankFeature.setRiskScore(proc_wound_pred);
				outcomeRankFeature.setComplicationname("Venous thromboembolism");
			}
			else if(complicationName.equalsIgnoreCase("vent_duration")){
				outcomeRankFeature.setRiskScore(vtepred);
				outcomeRankFeature.setComplicationname("Mechanical Ventilation > 48 hours");
			}
			
			
			outcomeRankFeature.setTopfeature1(row.getString("topfeature1"));
			outcomeRankFeature.setTopfeature2(row.getString("topfeature2"));
			outcomeRankFeature.setTopfeature3(row.getString("topfeature3"));
			
			outcomeRankFeature.setBtmfeature1(row.getString("btmfeature1"));
			outcomeRankFeature.setBtmfeature2(row.getString("btmfeature2"));
			outcomeRankFeature.setBtmfeature3(row.getString("btmfeature3"));
			
			outcomeRankFeatureList.add(outcomeRankFeature);
		}
		
		//System.out.println(outcomeRankFeatureList);
		return outcomeRankFeatureList;
	}
	
	
	public PatientDetails onePatient(Session session, String doctorId, String patientId) {
		
		PatientDetails patient = new PatientDetails();
		
		String docName= "";
		int noOfAttempt=0;
		String name, age, race, gender, cci, service, admission_type = "";
		String attend_doc, pr1_temp, pr1="", mdcVal, mdc="", pr1_day, admit_day, admission_source = "";
		String combordities, HGB, HCT, PROTUR, GLUURN, HGBUR, noBLOOD, noURINE, cr_base, eGFR_epi,MDRD_Cr ="";
		String Medications, county, area, med_inc, total, Prop_pov, prop_black, prop_hisp, pay_grp, net_decisions="";
		
		String docNameQuery = "select name from prisma1.userinfo where id='"+doctorId+"'";
		docName = executeQuery(session, docNameQuery,"name");
		
		String noOfAttemptQuery = "select count(attempt) as attempt from prisma1.indexPatient where user='"+doctorId+"' allow filtering";//
		ResultSet results = session.execute(noOfAttemptQuery);
		for(Row row: results){
			noOfAttempt= (int)row.getLong("attempt");
		}
		//System.out.println("noOfAttempt="+noOfAttempt);	
		String ageQuery = "select value from prisma1.patientdetailstemp where patient_id='"+patientId+"' and feature='age' allow filtering";//
		age = executeQuery(session, ageQuery,"value");
		//System.out.println("age="+age);	
		
		String raceQuery1 = "SELECT VALUE FROM prisma1.patientdetailstemp WHERE patient_id='"+patientId+"' AND feature='race' allow filtering";//
		String map_tmp = executeQuery(session, raceQuery1,"value");
		String raceQuery = "SELECT VALUE FROM prisma1.varMap WHERE id= 'race' AND map='"+map_tmp+"'";
		race = executeQuery(session, raceQuery,"value");
		//System.out.println("race="+race);
		
		String genderQuery = "select value from prisma1.patientdetailstemp where patient_id='"+patientId+"' and feature='sex' allow filtering";//
		gender = executeQuery(session, genderQuery,"value");
		//System.out.println("gender:"+gender);
		
		String cciQuery = "select value from prisma1.patientdetailstemp where patient_id='"+patientId+"' and feature='cci' allow filtering";//
		cci = executeQuery(session, cciQuery,"value");
		//System.out.println("cci:"+cci);
		
		if(cci!=null && Integer.parseInt(cci)==0){
			combordities = "with <b>no known combordities</b>";
		}
		else{		
			combordities = "has <b>"+cci+" combordities";
		}
		//System.out.println("combordities:"+combordities);
		
		String serviceQuery = "SELECT value FROM prisma1.patientdetailstemp WHERE patient_id='"+patientId+"' AND feature='service' allow filtering";
		service = executeQuery(session, serviceQuery,"value");
		serviceQuery = "SELECT value FROM prisma1.varMap WHERE map='"+service+"' allow filtering";
		service = executeQuery(session, serviceQuery,"value");	
		//System.out.println("service:"+service);
		
		String admissionTypeQuery = "select value from prisma1.patientdetailstemp where patient_id='"+patientId+"' and feature='admitting_type' allow filtering";//
		admission_type = executeQuery(session, admissionTypeQuery,"value");
		//System.out.println("admissionType:"+admission_type);
		
		String attend_docQuery = "select value from prisma1.patientdetailstemp where patient_id='"+patientId+"' and feature='attend_doc' allow filtering";//
		attend_doc = executeQuery(session, attend_docQuery,"value");
		//System.out.println("attend_doc:"+attend_doc);
		
		String pr1_tempQuery = "select value from prisma1.patientdetailstemp where patient_id='"+patientId+"' and feature='pr1' allow filtering";
		pr1_temp = executeQuery(session, pr1_tempQuery,"value");		
		
		if(!pr1_temp.isEmpty()){
			String pr1Query = "select description from prisma1.pr1Map where id='"+pr1_temp+"'";
			pr1 = executeQuery(session, pr1Query,"description");
			//System.out.println("pr1:"+pr1);
		}
		
		
		String mdcValQuery = "select value from prisma1.patientdetailstemp where patient_id='"+patientId+"' and feature='mdc' allow filtering";
		mdcVal = executeQuery(session, mdcValQuery,"value");
		System.out.println("mdcVal:"+mdcVal);
		if(mdcVal!=null && !mdcVal.isEmpty()){
			mdcVal = Integer.parseInt(mdcVal)+"";
			//System.out.println("mdcVal:"+mdcVal);
			
			String MDCQuery = "select description from prisma1.mdcMap where id='"+mdcVal+"'";
			mdc = executeQuery(session, MDCQuery,"description");
			//System.out.println("mdc:"+mdc);
		}
		
		
		String pr1_dayQuery = "select value from prisma1.patientdetailstemp where patient_id='"+patientId+"' and feature='pr1_day' allow filtering";
		pr1_day = executeQuery(session, pr1_dayQuery,"value");			
		if(pr1_day!=null && !pr1_day.isEmpty() && Integer.parseInt(pr1_day)==0)pr1_day="Today";
		else pr1_day=pr1_day+" day(s) ago";
		//System.out.println("pr1_day:"+pr1_day);
		
		String admit_dayQuery = "select value from prisma1.patientdetailstemp where patient_id='"+patientId+"' and feature='admit_day' allow filtering";//
		admit_day = executeQuery(session, admit_dayQuery,"value");
		//System.out.println("admit_day:"+admit_day);
		
		String admission_sourceQuery = "select value from prisma1.patientdetailstemp where patient_id='"+patientId+"' and feature='admission_source' allow filtering";//
		admission_source = executeQuery(session, admission_sourceQuery,"value");
		//System.out.println("admission_source:"+admission_source);
		
		
		String HGBQueryQuery = "select value from prisma1.patientdetailstemp where patient_id='"+patientId+"' and feature='hgb' allow filtering";
		HGB = executeQuery(session, HGBQueryQuery,"value");
		if(HGB.isEmpty())
			HGB="Not Measured";
		else HGB = HGB + " g/dl";		
		//System.out.println("HGB:"+HGB);
		
		String HCTQuery = "select value from prisma1.patientdetailstemp where patient_id='"+patientId+"' and feature='hct' allow filtering";
		HCT = executeQuery(session, HCTQuery,"value");
		if(HCT.isEmpty())
			HCT="Not Measured";
		else HCT = HCT + " %";
		//System.out.println("HCT:"+HCT);
		
		String PROTURTmpQuery = "SELECT value FROM  prisma1.patientdetailstemp WHERE patient_id='"+patientId+"' AND feature='protur' allow filtering";
		String PROTUR_tmp = executeQuery(session, PROTURTmpQuery,"value");
		String PROTURQuery = "SELECT value FROM prisma1.varMap WHERE map='"+PROTUR_tmp+"' AND id='protur'";
		PROTUR = executeQuery(session, PROTURQuery,"value");
		if(PROTUR.isEmpty())
			PROTUR="Not Measured";
		//System.out.println("PROTUR:"+PROTUR);
		
		String GLUURNTmpQuery = "SELECT VALUE FROM  prisma1.patientdetailstemp WHERE patient_id='"+patientId+"' AND feature='gluurn' allow filtering";
		String GLUURNT_tmp = executeQuery(session, GLUURNTmpQuery,"value");
		String GLUURNQuery = "SELECT VALUE FROM prisma1.varMap WHERE map='"+GLUURNT_tmp+"' AND id='gluurn'";
		GLUURN = executeQuery(session, GLUURNQuery,"value");
		if(GLUURN.isEmpty())
			GLUURN="Not Measured";
		//System.out.println("GLUURN:"+GLUURN);
		
		String HGBURTmpQuery = "SELECT VALUE FROM  prisma1.patientdetailstemp WHERE patient_id='"+patientId+"' AND feature='hgbur' allow filtering";
		String HGBUR_tmp = executeQuery(session, HGBURTmpQuery,"value");
		String HGBURQuery = "SELECT VALUE FROM prisma1.varMap WHERE map='"+HGBUR_tmp+"' AND id='hgbur'";
		HGBUR = executeQuery(session, HGBURQuery,"value");
		if(HGBUR.isEmpty())
			HGBUR="Not Measured";
		//System.out.println("HGBUR:"+HGBUR);
		
		String noBloodQuery = "select value from prisma1.patientdetailstemp where patient_id='"+patientId+"' and feature='noblood' allow filtering";
		noBLOOD = executeQuery(session, noBloodQuery,"value");		
		if(noBLOOD.isEmpty() || Integer.parseInt(noBLOOD)==0)noBLOOD="no CBC analysis";
		else noBLOOD = noBLOOD+" blood tests";
		//System.out.println("noBLOOD:"+noBLOOD);
		
		String noUrineQuery = "select value from prisma1.patientdetailstemp where patient_id='"+patientId+"' and feature='nourine' allow filtering";
		noURINE = executeQuery(session, noUrineQuery,"value");
		if(noURINE.isEmpty() || Integer.parseInt(noURINE)==0)noURINE="no CBC analysis";
		else noURINE = noURINE+" urine tests";
		//System.out.println("noURINE:"+noURINE);
		
		String cr_baseQuery = "select value from prisma1.patientdetailstemp where patient_id='"+patientId+"' and feature='cr_base' allow filtering";
		cr_base = executeQuery(session, cr_baseQuery,"value");
		//System.out.println("cr_base:"+cr_base);
		
		String eGFR_epiQuery = "select value from prisma1.patientdetailstemp where patient_id='"+patientId+"' and feature='egfr_epi' allow filtering";
		eGFR_epi = executeQuery(session, eGFR_epiQuery,"value");
		//System.out.println("eGFR_epi:"+eGFR_epi);
		
		String MDRD_CrQuery = "select value from prisma1.patientdetailstemp where patient_id='"+patientId+"' and feature='mdrd_cr' allow filtering";
		MDRD_Cr = executeQuery(session, MDRD_CrQuery,"value");
		//System.out.println("MDRD_Cr:"+MDRD_Cr);
		
		String MedicationsQuery = "SELECT feature FROM  prisma1.patientdetailstemp WHERE patient_id='"+patientId+"' AND VALUE='1' allow filtering";
		//System.out.println(MedicationsQuery);
		results = session.execute(MedicationsQuery); 
		ArrayList<String> medicationTmp = new ArrayList<String>();
		ArrayList<String> medication = new ArrayList<String>();
		for(Row row: results){
			medicationTmp.add(row.getString("feature"));
		}
		//System.out.println(medicationTmp.toString());
		
		for(String medi:medicationTmp){
			String medicaQuery = "SELECT description FROM prisma1.medicine WHERE id='"+medi.toLowerCase()+"'";
			String val = executeQuery(session, medicaQuery,"description");
			
			if(!val.isEmpty())medication.add(val);
		}		
		
		if(medication.isEmpty())Medications="No known medicines";
		else {
			//Medications = medication.toArray(new String[0])+"";
			StringBuilder sb = new StringBuilder();
			for (String s : medication)
			{
			    sb.append(s);
			    sb.append(" ");
			}
			Medications = sb.toString();
		}
			
		
		//System.out.println("Medications:"+Medications);
		
		String countyQuery = "select value from prisma1.patientdetailstemp where patient_id='"+patientId+"' and feature='county' allow filtering";
		county = executeQuery(session, countyQuery,"value");
		//System.out.println("county:"+county);
		
		String areaQuery = "select value from prisma1.patientdetailstemp where patient_id='"+patientId+"' and feature='area' allow filtering";
		area = executeQuery(session, areaQuery,"value");
		//System.out.println("area:"+area);
		
		String med_incQuery = "select value from prisma1.patientdetailstemp where patient_id='"+patientId+"' and feature='med_inc' allow filtering";
		med_inc = executeQuery(session, med_incQuery,"value");
		//System.out.println("med_inc:"+med_inc);
		
		String totalQuery = "select value from prisma1.patientdetailstemp where patient_id='"+patientId+"' and feature='total' allow filtering";
		total = executeQuery(session, totalQuery,"value");
		//System.out.println("total:"+total);
		
		String Prop_povQuery = "select value from prisma1.patientdetailstemp where patient_id='"+patientId+"' and feature='prop_pov' allow filtering";
		Prop_pov = executeQuery(session, Prop_povQuery,"value");
		if(!Prop_pov.isEmpty())
			Prop_pov = Float.parseFloat(Prop_pov)*100+"";
		//System.out.println("Prop_pov:"+Prop_pov);
		
		String prop_blackQuery = "select value from prisma1.patientdetailstemp where patient_id='"+patientId+"' and feature='prop_black' allow filtering";
		prop_black = executeQuery(session, prop_blackQuery,"value");
		if(!prop_black.isEmpty())
			prop_black = Float.parseFloat(prop_black)*100+"";
		//System.out.println("prop_black:"+prop_black);
		
		String prop_hispQuery = "select value from prisma1.patientdetailstemp where patient_id='"+patientId+"' and feature='prop_hisp' allow filtering";
		prop_hisp = executeQuery(session, prop_hispQuery,"value");
		if(!prop_hisp.isEmpty())
			prop_hisp= Float.parseFloat(prop_hisp)*100+"";
		//System.out.println("prop_hisp:"+prop_hisp);
		
		String pay_grpTmpQuery = "SELECT value FROM prisma1.patientdetailstemp WHERE patient_id='"+patientId+"' AND feature='pay_grp' allow filtering";
		String pay_grp_tmp = executeQuery(session, pay_grpTmpQuery,"value");
		String pay_grpQuery = "SELECT value FROM prisma1.varMap WHERE id='pay_grp' AND map='"+pay_grp_tmp+"'";
		pay_grp = executeQuery(session, pay_grpQuery,"value");
		//System.out.println("pay_grp:"+pay_grp);
		
		String net_decisionsQuery = "select id,description from prisma1.outcomes";
		results = session.execute(net_decisionsQuery);
		HashMap<Integer, String> net_decisionsMap = new HashMap<Integer, String>();		
		for(Row row: results){
			int idx = row.getInt("id");
			String desc = row.getString("description");
			net_decisionsMap.put(idx-1, desc);
			//System.out.println("idx="+idx+" desc="+desc);
		}
		
		String story1 = "Your patient is <b>"+age+" year old "+race+" "+gender+"</b>  "+combordities+". The patient was admitted to hospital <b>"+pr1_day+"</b> on <b>"+admit_day+"</b> from <b>"+admission_source+"</b> setting with primary diagnosis related "+
        		"to <b>"+mdc+"</b> and is scheduled to have a <b>"+admission_type+" "+service+" "+pr1+"</b> by <b>Dr."+docName+"</b>. On admission there were <b>"+noBLOOD+"</b> and <b>"+noURINE+"</b> done, and "+ 
        		"the laboratories values are Hgb <b>"+HGB+"</b>, Hct <b>"+HCT+"</b>, urine protein <b>"+PROTUR+"</b>, urine glucose <b>"+GLUURN+"</b>, urine blood <b>"+HGBUR+"</b>. The patient's serum creatinine on admission was <b>"+cr_base+" mg/dl</b> with calculated estimated GFR of <b>"+
     			eGFR_epi+" ml/min/1.72m2</b> while estimated baseline creatinine using age, gender, and race with MDRD equation is <b>"+MDRD_Cr+" </b>. Admission medications are: <b>"+Medications+"</b>.";
		
		String story2 = "Patient has <b>"+pay_grp+"</b> insurance and resides in <b>"+county+"</b> county in  <b>"+area+"</b>  area with total population of <b>"+total+"</b>. The percentage of African-Americans and Hispanics in his neighborhood is "+
     			"<b>"+prop_black+"% and "+prop_hisp+"%</b> respectively. The overall median income for his neighborhood is $<b>"+med_inc+"</b> and <b>"+Prop_pov+"%</b> of population lives below poverty level.";
		
		patient.setDoctorId(doctorId);
		patient.setDoctorName(docName);
		patient.setPatientId(patientId);
		patient.setStory1(story1);
		patient.setStory2(story2);
		patient.setLabels(net_decisionsMap);
		
		//System.out.println(story1);		
		//System.out.println(story2);
		
		
		return patient;
	}

	public ArrayList<PatientDetails> getPatientDetail(Session session, String doctorId) {
		
		ResultSet results = null;
		ArrayList<String> encounterIds = new ArrayList<String>();
		ArrayList<String> patientIDs = new ArrayList<String>();
		ArrayList<String> patientSummary = new ArrayList<String>();
		ArrayList<PatientDetails> patientDetailsList = new ArrayList<PatientDetails>();
		PatientDetails patientDetails = null;
				
		//BEGIN BATCH  select name from prisma1.userinfo where id='raj';select id from patientDetails where feature = 'attend_doc' and value ='raj' APPLY BATCH;
		//APPLY BATCH;
		
		//doctorId = "raj1";
		String docNameQuery = "select name from prisma1.userinfo where id='"+doctorId+"'";
		String docName  = executeQuery(session, docNameQuery,"name");			
		//System.out.println("docName="+docName);
		
		//doctorId = "raj";		select * from patientdetailstemp where feature='attend_doc' allow filtering;
		String patentIdQuery = "select encounter_id, patient_id from prisma1.patientdetailstemp where feature='attend_doc' and value='"+doctorId+"' allow filtering";//
		//System.out.println("query2="+patentIdQuery);
		encounterIds = executeQuery1(session, patentIdQuery, "encounter_id");		
		System.out.println("encounterIds=" + encounterIds);
		
		patientIDs = executeQuery1(session, patentIdQuery, "patient_id");		
		System.out.println("patientIDs=" + patientIDs);
		
		//System.out.println("patientID=" + patientID.get(0));
		
		//List<String>   ptId = Arrays.asList(patientID.get(0)); 
		
		String name, age, race, gender, cci, service, admission_type = "";
		String attend_doc, pr1_temp, pr1, mdcVal, mdc, pr1_day, admit_day, admission_source = "";
		String combordities="";
		int patientIdx=0;
		
		for(String encounterId:encounterIds){
			
			//String nameQuery = "select value from prisma1.patientdetails where id='"+encounterId+"' and feature = 'name'";
			//name = executeQuery(session, nameQuery,"value");
					
			String ageQuery = "select value from prisma1.patientdetailstemp where encounter_id='"+encounterId+"' and feature='age'";//
			age = executeQuery(session, ageQuery,"value");
			//System.out.println("age="+age);	
			
			String raceQuery1 = "SELECT VALUE FROM prisma1.patientdetailstemp WHERE encounter_id='"+encounterId+"' AND feature='race'";//
			String map_tmp = executeQuery(session, raceQuery1,"value");
			
			String raceQuery = "SELECT VALUE FROM prisma1.varMap WHERE id= 'race' AND map='"+map_tmp+"'";
			race = executeQuery(session, raceQuery,"value");
						
			String genderQuery = "select value from prisma1.patientdetailstemp where encounter_id='"+encounterId+"' and feature='sex'";//
			gender = executeQuery(session, genderQuery,"value");			
			
			String cciQuery = "select value from prisma1.patientdetailstemp where encounter_id='"+encounterId+"' and feature='cci'";//
			cci = executeQuery(session, cciQuery,"value");
			//System.out.println("cci:"+cci);
			if(cci!=null && Integer.parseInt(cci)==0){
				combordities = "with <b>no known combordities</b>";
			}
			else{		
				combordities = "has <b>"+cci+" combordities";
			}	
			
			/*
			String serviceQuery = "SELECT value FROM prisma1.patientdetailstemp WHERE encounter_id='"+encounterId+"' AND feature='service'";
			service = executeQuery(session, serviceQuery,"value");
			serviceQuery = "SELECT value FROM prisma1.varMap WHERE map='"+service+"' allow filtering";
			service = executeQuery(session, serviceQuery,"value");	*/		
			
			String admissionTypeQuery = "select value from prisma1.patientdetailstemp where encounter_id='"+encounterId+"' and feature='admitting_type'";//
			admission_type = executeQuery(session, admissionTypeQuery,"value");
			//System.out.println("admissionType:"+admission_type);
			
			String attend_docQuery = "select value from prisma1.patientdetailstemp where encounter_id='"+encounterId+"' and feature='attend_doc'";//
			attend_doc = executeQuery(session, attend_docQuery,"value");
			//System.out.println("attend_doc:"+attend_doc);
			
			/*
			String pr1_tempQuery = "select value from prisma1.patientdetailstemp where encounter_id='"+encounterId+"' and feature='pr1'";
			pr1_temp = executeQuery(session, pr1_tempQuery,"value");
			//System.out.println("pr1_temp:"+pr1_temp);
			
			String pr1Query = "select description from prisma1.pr1Map where id='"+pr1_temp+"'";
			pr1 = executeQuery(session, pr1Query,"description");
			//System.out.println("pr1:"+pr1);
			
			String mdcValQuery = "select value from prisma1.patientdetailstemp where encounter_id='"+encounterId+"' and feature='mdc'";
			mdcVal = executeQuery(session, mdcValQuery,"value");
			mdcVal = Integer.parseInt(mdcVal)+"";
			
			String MDCQuery = "select description from prisma1.mdcMap where id='"+mdcVal+"'";
			mdc = executeQuery(session, MDCQuery,"description");
			//System.out.println("mdc:"+mdc);
			
			String pr1_dayQuery = "select value from prisma1.patientdetailstemp where encounter_id='"+encounterId+"' and feature='pr1_day'";
			pr1_day = executeQuery(session, pr1_dayQuery,"value");			
			if(Integer.parseInt(pr1_day)==0)pr1_day="Today";
			else pr1_day=pr1_day+" day(s) ago";
			//System.out.println("pr1_day:"+pr1_day);*/
			
			String admit_dayQuery = "select value from prisma1.patientdetailstemp where encounter_id='"+encounterId+"' and feature='admit_day'";//
			admit_day = executeQuery(session, admit_dayQuery,"value");
			//System.out.println("admit_day:"+admit_day);
			
			String admission_sourceQuery = "select value from prisma1.patientdetailstemp where encounter_id='"+encounterId+"' and feature='admission_source'";//
			admission_source = executeQuery(session, admission_sourceQuery,"value");
			//System.out.println("admission_source:"+admission_source);
			
			/*
			String story = "Your patient is <b>"+name+"</b>, and is <b>"+ age+" year old "+race+" "+gender+" </b> "+ combordities+
					" . The patient was admitted to hospital <b>"+pr1_day+" </b> on <b>"+admit_day+" </b> from <b>"+admission_source+
					" </b> setting with primary diagnosis related to <b>"+mdc+"</b> and is scheduled to have a <b>"+
					admission_type+" "+ service+" "+ pr1+"</b> by <b>Dr."+docName+"</b>.";*/
			
			String story = "Your patient is <b>"+ age+" year old "+race+" "+gender+" </b> "+ combordities+
					" . The patient was admitted to hospital on <b>"+admit_day+" </b> from <b>"+admission_source+
					" </b> setting with primary diagnosis by <b>Dr."+docName+"</b>.";
			
			patientSummary.add(story);
			patientDetails = new PatientDetails();
			
			patientDetails.setDoctorId(doctorId);
			patientDetails.setDoctorName(docName);
			patientDetails.setEncounterId(encounterId);
			patientDetails.setPatientId(patientIDs.get(patientIdx)); 
			patientIdx++;
			patientDetails.setStory1(story);
			
			patientDetailsList.add(patientDetails);
			//System.out.println("story="+story);			
		}		
		
		//System.out.println("patientSummary="+patientSummary.toString());

		return patientDetailsList;		
	}
	
	
	public ReviewResult reviewResults(Session session, int outcomeId) {
		
		ReviewResult review = null;
		String outcome="", description="";
				
		String reviewQuery = "select outcome,description from prisma1.outcomes where id="+outcomeId+" allow filtering";
		logger.debug("reviewQuery="+reviewQuery);
		
		ResultSet results = session.execute(reviewQuery);
		for (Row row : results){
			review = new ReviewResult();
			outcome     = row.getString("outcome");
			description = row.getString("description");
			review.setOutcome(outcome);
			review.setDescription(description);
		}
		
		logger.debug("outcome="+outcome+" description="+description);
		System.out.println("outcome="+outcome+" description="+description);
		
		return review;
	}
	
	public int getLogin(Session session, String userId, String password) {

		int noRow = 0;
		
		System.out.println("select count(*) as cnt from prisma1.userinfo where id='"+userId+"' and password='"+password+"' allow filtering");
		
		logger.debug("select count(*) as cnt from prisma1.userinfo where id='"+userId+"' and password='"+password+"' allow filtering");
				
		ResultSet results = session
				.execute("select count(*) as cnt from prisma1.userinfo where id='"+userId+"' and password='"+password+"' allow filtering");
			
		System.out.println("results=" + results);
		
		for (Row row : results){
			logger.debug("cnt="+row.getLong("cnt"));
			noRow = (int)row.getLong("cnt");
			System.out.println("noRow="+noRow);
		}

		//System.out.println("results=" + results+" noRow="+noRow);
		return noRow;		
	}

	public ArrayList<OutComeICUPojo> GetICU_OutCome(Session session)// (Connection
																	// connection)
			throws Exception {
		ArrayList<OutComeICUPojo> OutcomeICUList = new ArrayList<OutComeICUPojo>();
		try {
			// String uname = request.getParameter("uname");
			ResultSet results = session
					.execute("select id, outcome from prisma1.outcome_icu");
			System.out.println("results=" + results);

			for (Row row : results) {
				System.out.format("%s %s\n", row.getString("id"),
						row.getString("outcome"));
				OutComeICUPojo outcomeICU = new OutComeICUPojo("", "");
				outcomeICU.setId(row.getString("id"));
				outcomeICU.setOutcome(row.getString("outcome"));
				OutcomeICUList.add(outcomeICU);
			}

			return OutcomeICUList;
		} catch (Exception e) {
			throw e;
		}
	}

	public ArrayList<RiskAssessment>  getRiskAssessment(Session session, String doctorId, String patientId, int riskAssessmentType) throws Exception {
		
		ArrayList<RiskAssessment> riskAssessmentList = new ArrayList<RiskAssessment>();
		RiskAssessment riskAssessment = null;
		try {
			
			String riskQuery = "select * from prisma1.riskassessment where docid='"+doctorId+"' and patientid='"+patientId+"' and risktype="+riskAssessmentType;
			
			ResultSet results = session.execute(riskQuery);
			System.out.println(riskQuery);
			//System.out.println("results=" + results);
			
			
			for (Row row : results) {
				riskAssessment = new RiskAssessment();
				
				riskAssessment.setCardiovascular(row.getFloat("cardiovascular"));
				riskAssessment.setIcu(row.getFloat("icu"));
				riskAssessment.setNeurological(row.getFloat("neurological"));
				riskAssessment.setRifle7(row.getFloat("rifle7"));
				riskAssessment.setSepsis(row.getFloat("sepsis"));
				riskAssessment.setVenous(row.getFloat("venous"));
				riskAssessment.setVentilator(row.getFloat("ventilator"));
				riskAssessment.setWound(row.getFloat("wound"));
				riskAssessment.setRisktype(row.getInt("risktype"));
				riskAssessment.setNumAttempts(row.getInt("numattempts"));
				
				riskAssessmentList.add(riskAssessment);
			}

			return riskAssessmentList;
		} catch (Exception e) {
			e.printStackTrace(System.out);
			throw e;
		}
	}

	public Mortality mortality(Session session, String patientId) throws Exception {
		
		try {
		Mortality patientMortality =new Mortality();
		String outcomeTableQuery = "select * from prisma1.mortalityscores where patient_deiden_id='"+patientId+"' allow filtering";
		System.out.println(outcomeTableQuery);
		ResultSet results = session.execute(outcomeTableQuery);
		
		patientMortality.setPatientId(patientId);
		
		for(Row row: results){
			patientMortality.setMort_status_30d(row.getString("mort_status_30d"));
			patientMortality.setMort_status_90d(row.getString("mort_status_90d"));
			patientMortality.setMort_status_6m(row.getString("mort_status_6m"));
			patientMortality.setMort_status_1y(row.getString("mort_status_1y"));
			patientMortality.setMort_status_2y(row.getString("mort_status_2y"));
		}
		//System.out.println(patientMortality);
		return patientMortality;
		} catch (Exception e) {
			e.printStackTrace(System.out);
			throw e;
		}
	}

	public PatientRecord getPatienRecords(Session session, String patientId) throws Exception {
		try {
			PatientRecord patientRecords =new PatientRecord();
			patientRecords.setPatientid(patientId);
			
			String medDataQuery = "select value from prisma1.patientdetailstemp where patient_id='"+patientId+"' and feature='meddata' allow filtering";
			System.out.println(medDataQuery);
			ResultSet results = session.execute(medDataQuery);
						
			for(Row row: results){
				patientRecords.setMeddata(row.getString("value"));
			}
			
			String labDataQuery = "select value from prisma1.patientdetailstemp where patient_id='"+patientId+"' and feature='labdata' allow filtering";
			System.out.println(labDataQuery);
			results = session.execute(labDataQuery);
						
			for(Row row: results){
				patientRecords.setLabdata(row.getString("value"));
			}
			
			String dgnDataQuery = "select value from prisma1.patientdetailstemp where patient_id='"+patientId+"' and feature='dgnData' allow filtering";
			System.out.println(dgnDataQuery);
			results = session.execute(dgnDataQuery);
						
			for(Row row: results){
				patientRecords.setDgndata(row.getString("value"));
			}
			
			System.out.println(patientRecords);
			return patientRecords;
			} catch (Exception e) {
				e.printStackTrace(System.out);
				throw e;
			}
	}
}
