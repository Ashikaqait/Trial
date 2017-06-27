package com.qainfotech.tap.training.resourceio;

import com.qainfotech.tap.training.resourceio.exceptions.ObjectNotFoundException;
import com.qainfotech.tap.training.resourceio.model.Individual;
import com.qainfotech.tap.training.resourceio.model.Team;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Ramandeep RamandeepSingh AT QAInfoTech.com
 */
public class TeamsJsonReader{
	List<Individual> list1;
	List<Team> list2;
	

    /**
     * get a list of individual objects from db json file
     * 
     * @return 
     */
	public TeamsJsonReader() {
		try {
			FileReader fr = new FileReader(
					new File("C:\\Users\\ashikasrivastava\\Downloads\\assignment-resource-io-master\\assignment-resource-io-master\\src\\test\\resources\\db.json"));
			JSONParser parser = new JSONParser();
			Object ob = parser.parse(fr);
			JSONObject jo = (JSONObject) ob;
			list1 = new ArrayList<Individual>();
			JSONArray ja = (JSONArray) jo.get("individuals");
			JSONObject job[] = new JSONObject[ja.size()];
			for (int i = 0; i < ja.size(); i++) {
				job[i] = (JSONObject) ja.get(i);
				Integer id = ((Long) job[i].get("id")).intValue();
				String name = job[i].get("name").toString();
				Boolean active = (Boolean) job[i].get("active");
				Map<String, Object> map = new HashMap();
				map.put("id", id);
				map.put("name", name);
				map.put("active", active);
				Individual ind = new Individual(map);
				list1.add(ind);

			}
			list2 = new ArrayList<Team>();
			List<Individual> list1;

			JSONArray jaa = (JSONArray) jo.get("teams");
			JSONObject joa[] = new JSONObject[jaa.size()];
			for (int i = 0; i < jaa.size(); i++) {
				list1 = new ArrayList<Individual>();
				joa[i] = (JSONObject) jaa.get(i);
				Integer id = ((Long) joa[i].get("id")).intValue();
				String name = joa[i].get("name").toString();

				JSONArray jas = (JSONArray) joa[i].get("members");
				for (int j = 0; j < jas.size(); j++) {
					Integer idd = ((Long) jas.get(j)).intValue();
					Individual ind = getIndividualById(idd);
					list1.add(ind);

				}
				System.out.println();
				Map<String, Object> map = new HashMap();

				map.put("id", id);
				map.put("name", name);
				map.put("members", list1);

				Team tem = new Team(map);
				list2.add(tem);

			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

    public List<Individual> getListOfIndividuals(){
    	  //throw new UnsupportedOperationException("Not implemented.");
    	
			FileReader fr = null;
			try {
				fr = new FileReader(
						new File("C:\\Users\\ashikasrivastava\\Downloads\\assignment-resource-io-master\\assignment-resource-io-master\\src\\test\\resources\\db.json"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			JSONParser parser = new JSONParser();
			Object ob = null;
			try {
				ob = parser.parse(fr);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			JSONObject jo = (JSONObject) ob;
			list1 = new ArrayList<Individual>();
			JSONArray ja = (JSONArray) jo.get("individuals");
			JSONObject job[] = new JSONObject[ja.size()];
			for (int i = 0; i < ja.size(); i++) {
				job[i] = (JSONObject) ja.get(i);
				Integer id = ((Long) job[i].get("id")).intValue();
				String name = job[i].get("name").toString();
				Boolean active = (Boolean) job[i].get("active");
				Map<String, Object> map = new HashMap();
				map.put("id", id);
				map.put("name", name);
				map.put("active", active);
				Individual ind = new Individual(map);
				list1.add(ind);

			}
			return list1;

    	}
   
    
    /**
     * get individual object by id
     * 
     * @param id individual id
     * @return 
     * @throws com.qainfotech.tap.training.resourceio.exceptions.ObjectNotFoundException 
     */
    public Individual getIndividualById(Integer id) throws ObjectNotFoundException{
		
       // throw new UnsupportedOperationException("Not implemented.");
    	
    	Individual in = null;
		int flag = 0;
		Iterator<Individual> itr = list1.iterator();
		while (itr.hasNext()) {
			in = itr.next();
			int a = id;
			int b = in.getId();
			if (a == b) {
				flag = 1;
				break;
			}

		}
		if (flag == 1)
			return in;
		else
			throw new ObjectNotFoundException("Individual", "id", id.toString());

	}
    	
    
    
    
    /**
     * get individual object by name
     * 
     * @param name
     * @return 
     * @throws com.qainfotech.tap.training.resourceio.exceptions.ObjectNotFoundException 
     */
    public Individual getIndividualByName(String name) throws ObjectNotFoundException{
        //throw new UnsupportedOperationException("Not implemented.");
    	Individual in = null;
		Iterator<Individual> itr = list1.iterator();
		int flag = 0;

		while (itr.hasNext()) {
			in = itr.next();
			String a = name;
			String b = in.getName();

			if (a.equalsIgnoreCase(b)) {
				flag = 1;
				break;
			}

		}
		if (flag == 0)
			throw new ObjectNotFoundException("Individual", "name", name);
		else
			return in;

	}
    
    
    
    /**
     * get a list of individual objects who are not active
     * 
     * @return List of inactive individuals object
     */
    public List<Individual> getListOfInactiveIndividuals(){
       // throw new UnsupportedOperationException("Not implemented.");

    	List<Individual> list1 = new ArrayList<Individual>();

		Iterator<Individual> itr = list1.iterator();
		while (itr.hasNext()) {
			Individual ind = itr.next();
			Boolean a = false;
			Boolean b = ind.isActive();
			if (a == b) {
				list1.add(ind);
			}

		}
		return list1;

	}
    
    
    /**
     * get a list of individual objects who are active
     * 
     * @return List of active individuals object
     */
    public List<Individual> getListOfActiveIndividuals(){
       // throw new UnsupportedOperationException("Not implemented.");
    	List<Individual> list1 = new ArrayList<Individual>();

		Iterator<Individual> itr = list1.iterator();
		while (itr.hasNext()) {
			Individual ind = itr.next();
			Boolean a = true;
			Boolean b = ind.isActive();
			if (a == b) {
				list1.add(ind);
			}

		}
		return list1;



	}
    
    
    /**
     * get a list of team objects from db json
     * 
     * @return 
     */
    public List<Team> getListOfTeams(){
       // throw new UnsupportedOperationException("Not implemented.");
    
    	try {
			FileReader fr = new FileReader(
					new File("C:\\Users\\ashikasrivastava\\Downloads\\assignment-resource-io-master\\assignment-resource-io-master\\src\\test\\resources\\db.json"));
			JSONParser parser = new JSONParser();
			Object ob = parser.parse(fr);
			JSONObject jo = (JSONObject) ob;

			list2 = new ArrayList<Team>();
			List<Individual> list1;

			JSONArray jaa = (JSONArray) jo.get("teams");
			JSONObject joa[] = new JSONObject[jaa.size()];
			for (int i = 0; i < jaa.size(); i++) {
				list1 = new ArrayList<Individual>();
				joa[i] = (JSONObject) jaa.get(i);
				Integer id = ((Long) joa[i].get("id")).intValue();
				String name = joa[i].get("name").toString();

				JSONArray jas = (JSONArray) joa[i].get("members");
				for (int j = 0; j < jas.size(); j++) {
					Integer id1 = ((Long) jas.get(j)).intValue();
					Individual in = getIndividualById(id1);
					list1.add(in);

				}
				System.out.println();
				Map<String, Object> map = new HashMap<String, Object>();

				map.put("id", id);
				map.put("name", name);
				map.put("members", list1);

				Team tm = new Team(map);
				list2.add(tm);

			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return list2;


    	}
}
