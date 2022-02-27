package com.rideboard.data;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.rideboard.data.model.EventModel;
import com.rideboard.data.model.RaceModel;
import com.rideboard.data.model.SponsorModel;
import com.rideboard.data.model.TeamModel;
import com.rideboard.data.model.UserModel;

public class Database {
	private static Map<Integer, UserModel> user_tbl = null;
	private static Map<Integer, EventModel> event_tbl = null;
	private static Map<Integer, SponsorModel> sponsor_tbl = null;
	private static Map<Integer, TeamModel> team_tbl = null;
	private static Map<Integer, RaceModel> race_tbl = null;
	private static boolean inited = false;
	// initial testing data
	static {
		if(!inited) {
			initUserData();
			inited = true;
		}
	}

	private static void initUserData() {
		user_tbl = user_tbl == null ? new HashMap<Integer, UserModel>(10) : user_tbl;
		final String pwd = com.rideboard.common.Utils.hash("password");
		final Date todate = new Date();
		user_tbl.put(1, new UserModel() {
			{
				setUserid(1);
				setRole("racer");
				setUser_name("racer1");
				setFirst_name("Tony");
				setMid_name("J.");
				setLast_name("Norton");
				setPassword(pwd);
				setStatus("A");
				setAttempt_count(0);
				setCre_dt(todate);
				setCre_user("SYSTEM");
				setLast_upd_dt(todate);
				setLast_upd_user("SYSTEM");
			}
		});
		user_tbl.put(2, new UserModel() {
			{
				setUserid(1);
				setRole("team");
				setUser_name("team1");
				setTeam_name("McLaren");
				setPassword(pwd);
				setStatus("A");
				setAttempt_count(0);
				setCre_dt(todate);
				setCre_user("SYSTEM");
				setLast_upd_dt(todate);
				setLast_upd_user("SYSTEM");
			}
		});
		user_tbl.put(3, new UserModel() {
			{
				setUserid(1);
				setRole("sponsor");
				setUser_name("company1");
				setCompany_name("Toyota");
				setPassword(pwd);
				setStatus("A");
				setAttempt_count(0);
				setCre_dt(todate);
				setCre_user("SYSTEM");
				setLast_upd_dt(todate);
				setLast_upd_user("SYSTEM");
			}
		});
	}
	private static void initRaceData() {
		race_tbl = race_tbl == null ? new HashMap<Integer, RaceModel>(10) : race_tbl;
		final Date todate = new Date();
		race_tbl.put(1, new RaceModel() {
			{
				setRaceId(1);
				setTitle("GP F1 2022");
				setDesc("");
				setLocation("Yokohama, Japan");
				setDateStr("2022-03-15");
				setType("F1");
				setStage(1);
			}
		});
		race_tbl.put(2, new RaceModel() {
			{
				setRaceId(2);
				setTitle("GP F1 2022");
				setDesc("");
				setLocation("Hokaido, Japan");
				setDateStr("2022-03-19");
				setType("F1");
				setStage(2);
			}
		});
		race_tbl.put(3, new RaceModel() {
			{
				setRaceId(3);
				setTitle("GP F3 2022");
				setDesc("");
				setLocation("Mexico City, Mexico");
				setDateStr("2022-03-25");
				setType("F3");
				setStage(1);
			}
		});
		race_tbl.put(4, new RaceModel() {
			{
				setRaceId(4);
				setTitle("GP F1 2022");
				setDesc("");
				setLocation("Seoul, South Korea");
				setDateStr("2022-03-30");
				setType("F1");
				setStage(3);
			}
		});
	}
	private static void initEventData() {
		event_tbl = event_tbl == null ? new HashMap<Integer, EventModel>(10) : event_tbl;
	}
	private static void initTeamData() {
		
	}
	private static void initSponsorData() {
		
	}
	
	public static Set<Entry<Integer, UserModel>> listUsers(){
		return user_tbl.entrySet();
	}
	
	public static UserModel findUserById(int key) {
		return user_tbl.containsKey(key) ? user_tbl.get(key) : null;
	}

	public static UserModel findUserByUserName(String userName) {
		UserModel retVal = null;
		if (userName == null)
			return null;
		for (Integer key : user_tbl.keySet()) {
			retVal = user_tbl.get(key);
			if (retVal != null && userName.equals(retVal.getUser_name()))
				return retVal;
		}
		return null;
	}

	public static void updateUserById(int key, UserModel user) {
		if(user_tbl != null) {
			user_tbl.put(key, user);
		}
	}
}
