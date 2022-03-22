package com.rideboard.data;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.rideboard.data.model.EventModel;
import com.rideboard.data.model.RaceModel;
import com.rideboard.data.model.RacerModel;
import com.rideboard.data.model.UserModel;

public class DummyData {
	private static Map<Integer, UserModel> user_tbl = null;
	private static Map<Integer, EventModel> event_tbl = null;
//	private static Map<Integer, SponsorModel> sponsor_tbl = null;
//	private static Map<Integer, TeamModel> team_tbl = null;
	private static Map<Integer, RaceModel> race_tbl = null;
	private static Map<Integer, RacerModel> racer_tbl = null;
	private static boolean inited = false;
	
	// initial testing data
//	static {
//		if(!inited) {
//			initUserData();
//			initRacerData();
//			initTeamData();
//			initSponsorData();
//			initRaceData();
//			initEventData();
//			inited = true;
//		}
//	}
//
//	private static void initUserData() {
//		user_tbl = user_tbl == null ? new HashMap<Integer, UserModel>(5) : user_tbl;
//		final String pwd = com.rideboard.common.Utils.hash("password");
//		final Date todate = new Date();
//		user_tbl.put(1, new UserModel() {
//			{
//				setUserId(1);
//				setRole(com.rideboard.common.Constants.TYPE_RACE);
//				setUserName("racer1");
//				setPassword(pwd);
//				setStatus("A");
//				setAttempt_count(0);
//				setCre_dt(todate);
//				setCre_user("SYSTEM");
//				setLast_upd_dt(todate);
//				setLast_upd_user("SYSTEM");
//			}
//		});
//		user_tbl.put(2, new UserModel() {
//			{
//				setUserId(2);
//				setRole(com.rideboard.common.Constants.TYPE_TEAM);
//				setUserName("team1");
//				setPassword(pwd);
//				setStatus("A");
//				setAttempt_count(0);
//				setCre_dt(todate);
//				setCre_user("SYSTEM");
//				setLast_upd_dt(todate);
//				setLast_upd_user("SYSTEM");
//			}
//		});
//		user_tbl.put(3, new UserModel() {
//			{
//				setUserId(3);
//				setRole(com.rideboard.common.Constants.TYPE_SPONSOR);
//				setUserName("company1");
//				setPassword(pwd);
//				setStatus("A");
//				setAttempt_count(0);
//				setCre_dt(todate);
//				setCre_user("SYSTEM");
//				setLast_upd_dt(todate);
//				setLast_upd_user("SYSTEM");
//			}
//		});
//	}
//	private static void initRaceData() {
//		race_tbl = race_tbl == null ? new HashMap<Integer, RaceModel>(10) : race_tbl;
//		final Date todate = new Date();
//		race_tbl.put(1, new RaceModel() {
//			{
//				setRaceId(1);
//				setTitle("GP F1 2022");
//				setDesc("");
//				setLocation("Yokohama, Japan");
//				setDateStr("2022-03-15");
//				setType("F1");
//				setStage(1);
//				setCre_dt(todate);
//				setCre_user("SYSTEM");
//				setLast_upd_dt(todate);
//				setLast_upd_user("SYSTEM");
//			}
//		});
//		race_tbl.put(2, new RaceModel() {
//			{
//				setRaceId(2);
//				setTitle("GP F1 2022");
//				setDesc("");
//				setLocation("Hokaido, Japan");
//				setDateStr("2022-03-19");
//				setType("F1");
//				setStage(2);
//				setCre_dt(todate);
//				setCre_user("SYSTEM");
//				setLast_upd_dt(todate);
//				setLast_upd_user("SYSTEM");
//			}
//		});
//		race_tbl.put(3, new RaceModel() {
//			{
//				setRaceId(3);
//				setTitle("GP F3 2022");
//				setDesc("");
//				setLocation("Mexico City, Mexico");
//				setDateStr("2022-03-25");
//				setType("F3");
//				setStage(1);
//				setCre_dt(todate);
//				setCre_user("SYSTEM");
//				setLast_upd_dt(todate);
//				setLast_upd_user("SYSTEM");
//			}
//		});
//		race_tbl.put(4, new RaceModel() {
//			{
//				setRaceId(4);
//				setTitle("GP F1 2022");
//				setDesc("");
//				setLocation("Seoul, South Korea");
//				setDateStr("2022-03-30");
//				setType("F1");
//				setStage(3);
//				setCre_dt(todate);
//				setCre_user("SYSTEM");
//				setLast_upd_dt(todate);
//				setLast_upd_user("SYSTEM");
//			}
//		});
//	}
//	private static void initEventData() {
//		event_tbl = event_tbl == null ? new HashMap<Integer, EventModel>(10) : event_tbl;
//		final Date todate = new Date();
//		event_tbl.put(1, new EventModel() {
//			{
//				setEventId(1);
//				setUserId(1);
//				setRaceId(1);
//				setType(Constants.TYPE_RACE);
//				setCre_dt(todate);
//				setCre_user("SYSTEM");
//				setLast_upd_dt(todate);
//				setLast_upd_user("SYSTEM");
//			}
//		});
//		event_tbl.put(2, new EventModel() {
//			{
//				setEventId(2);
//				setUserId(1);
//				setRaceId(2);
//				setType(Constants.TYPE_RACE);
//				setCre_dt(todate);
//				setCre_user("SYSTEM");
//				setLast_upd_dt(todate);
//				setLast_upd_user("SYSTEM");
//			}
//		});
//		event_tbl.put(3, new EventModel() {
//			{
//				setEventId(3);
//				setUserId(1);
//				setSponsorId(1);
//				setType(Constants.TYPE_SPONSOR);
//				setCre_dt(todate);
//				setCre_user("SYSTEM");
//				setLast_upd_dt(todate);
//				setLast_upd_user("SYSTEM");
//			}
//		});
//		event_tbl.put(4, new EventModel() {
//			{
//				setEventId(4);
//				setUserId(1);
//				setRaceId(3);
//				setType(Constants.TYPE_RACE);
//				setCre_dt(todate);
//				setCre_user("SYSTEM");
//				setLast_upd_dt(todate);
//				setLast_upd_user("SYSTEM");
//			}
//		});
//		event_tbl.put(5, new EventModel() {
//			{
//				setEventId(5);
//				setUserId(1);
//				setSponsorId(2);
//				setType(Constants.TYPE_SPONSOR);
//				setCre_dt(todate);
//				setCre_user("SYSTEM");
//				setLast_upd_dt(todate);
//				setLast_upd_user("SYSTEM");
//			}
//		});
//		event_tbl.put(6, new EventModel() {
//			{
//				setEventId(6);
//				setUserId(1);
//				setRaceId(4);
//				setType(Constants.TYPE_RACE);
//				setCre_dt(todate);
//				setCre_user("SYSTEM");
//				setLast_upd_dt(todate);
//				setLast_upd_user("SYSTEM");
//			}
//		});
//		event_tbl.put(7, new EventModel() {
//			{
//				setEventId(7);
//				setUserId(2);
//				setSponsorId(3);
//				setType(Constants.TYPE_SPONSOR);
//				setCre_dt(todate);
//				setCre_user("SYSTEM");
//				setLast_upd_dt(todate);
//				setLast_upd_user("SYSTEM");
//			}
//		});
//	}
//	private static void initTeamData() {
//		team_tbl = team_tbl == null ? new HashMap<Integer, TeamModel>(10) : team_tbl;
//		final Date todate = new Date();
//		team_tbl.put(1, new TeamModel() {
//			{
//				setTeamId(1);
//				setUserId(2);
//				setTeamTitle("Wacky Racers");
//				setRanking(11);
//				setBudget(40000);
//				setCre_dt(todate);
//				setCre_user("SYSTEM");
//				setLast_upd_dt(todate);
//				setLast_upd_user("SYSTEM");
//			}
//		});
//		team_tbl.put(2, new TeamModel() {
//			{
//				setTeamId(2);
//				setTeamTitle("The Cannonball Joggers");
//				setRanking(6);
//				setBudget(75000);
//				setCre_dt(todate);
//				setCre_user("SYSTEM");
//				setLast_upd_dt(todate);
//				setLast_upd_user("SYSTEM");
//			}
//		});
//		team_tbl.put(3, new TeamModel() {
//			{
//				setTeamId(3);
//				setTeamTitle("Grand Pricks");
//				setRanking(3);
//				setBudget(68000);
//				setCre_dt(todate);
//				setCre_user("SYSTEM");
//				setLast_upd_dt(todate);
//				setLast_upd_user("SYSTEM");
//			}
//		});
//		team_tbl.put(4, new TeamModel() {
//			{
//				setTeamId(4);
//				setTeamTitle("Grandstanders");
//				setRanking(9);
//				setBudget(56000);
//				setCre_dt(todate);
//				setCre_user("SYSTEM");
//				setLast_upd_dt(todate);
//				setLast_upd_user("SYSTEM");
//			}
//		});
//		
//	}
//	private static void initSponsorData() {
//		sponsor_tbl = sponsor_tbl == null ? new HashMap<Integer, SponsorModel>(5) : sponsor_tbl;
//		final Date todate = new Date();
//		sponsor_tbl.put(1, new SponsorModel() {
//			{
//				setSponsorId(1);
//				setUserId(3);
//				setCompanyName("Samsung Electronics Ltd.");
//				setJobTitle("Samsung Galaxy 22 Series");
//				setJobDesc("");
//				setRequestDateStr("2022-02-26");
//				setCre_dt(todate);
//				setCre_user("SYSTEM");
//				setLast_upd_dt(todate);
//				setLast_upd_user("SYSTEM");
//			}
//		});
//		sponsor_tbl.put(2, new SponsorModel() {
//			{
//				setSponsorId(2);
//				setCompanyName("Toyota Corp.");
//				setJobTitle("Toyota RAV4 Prime 2022");
//				setJobDesc("");
//				setRequestDateStr("2022-02-28");
//				setCre_dt(todate);
//				setCre_user("SYSTEM");
//				setLast_upd_dt(todate);
//				setLast_upd_user("SYSTEM");
//			}
//		});
//	}
//	private static void initRacerData() {
//		racer_tbl = racer_tbl == null ? new HashMap<Integer, RacerModel>(5) : racer_tbl;
//		final Date todate = new Date();
//		racer_tbl.put(1, new RacerModel() {
//			{
//				setRacerId(1);
//				setUserId(1);
//				setFirstName("Edwin");
//				setLastName("Stak");
//				setMidName("J.");
//				setSin("211-445-8888");
//				setRanking(5);
//				setCre_dt(todate);
//				setCre_user("SYSTEM");
//				setLast_upd_dt(todate);
//				setLast_upd_user("SYSTEM");
//			}
//		});
//		racer_tbl.put(2, new RacerModel() {
//			{
//				setRacerId(2);
//				setFirstName("Peter");
//				setLastName("McLen");
//				setSin("520-612-3322");
//				setRanking(9);
//				setCre_dt(todate);
//				setCre_user("SYSTEM");
//				setLast_upd_dt(todate);
//				setLast_upd_user("SYSTEM");
//			}
//		});
//	}
	
	public static Collection<UserModel> listUsers(){
		return user_tbl.values();
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
			if (retVal != null && userName.equals(retVal.getUserName()))
				return retVal;
		}
		return null;
	}

	public static Collection<RaceModel> listRaces() {
		return race_tbl.values();
	}
	public static RaceModel findRaceById(int key) {
		return race_tbl.containsKey(key)?race_tbl.get(key):null;
	}

	public static Collection<EventModel> listEvents() {
		return event_tbl.values();
	}
	public static EventModel findEventById(int key) {
		return event_tbl.containsKey(key)?event_tbl.get(key):null;
	}
	public static List<EventModel> findEventByUserId(int key) {
		List<EventModel> retList = null;
		if(event_tbl != null && event_tbl.size() > 0) {
			retList = new java.util.ArrayList<EventModel>(event_tbl.size());
			for(EventModel event:event_tbl.values()) {
				if(event != null && event.getUserId() == key) {
					retList.add(event);
				}
			}
		}
		return retList;
	}
	
//	public static Collection<TeamModel> listTeams() {
//		return team_tbl.values();
//	}
//	public static TeamModel findTeamById(int key) {
//		return team_tbl.containsKey(key)?team_tbl.get(key):null;
//	}
//	public static TeamModel findTeamByUserId(int key) {
//		TeamModel retVal = null;
//		for(TeamModel model:listTeams()) {
//			if(model != null && model.getUserId() == key) {
//				retVal = model;
//				break;
//			}
//		}
//		return retVal;
//	}
//	
	public static Collection<RacerModel> listRacers() {
		return racer_tbl.values();
	}
	public static RacerModel findRacerById(int key) {
		return racer_tbl.containsKey(key)?racer_tbl.get(key):null;
	}
	public static RacerModel findRacerByUserId(int key) {
		RacerModel retVal = null;
		for(RacerModel model:listRacers()) {
			if(model != null && model.getUserId() == key) {
				retVal = model;
				break;
			}
		}
		return retVal;
	}
	
//	public static Collection<SponsorModel> listSponsors() {
//		return sponsor_tbl.values();
//	}
//	public static SponsorModel findSponsorById(int key) {
//		return sponsor_tbl.containsKey(key)?sponsor_tbl.get(key):null;
//	}
//	public static SponsorModel findSponsorByUserId(int key) {
//		SponsorModel retVal = null;
//		for(SponsorModel model:listSponsors()) {
//			if(model != null && model.getUserId() == key) {
//				retVal = model;
//				break;
//			}
//		}
//		return retVal;
//	}
//	
	public static void updateUserById(int key, UserModel user) {
		if(user_tbl != null) {
			user_tbl.put(key, user);
		}
	}
}
