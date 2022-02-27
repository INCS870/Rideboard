package com.rideboard.data;

import java.util.Set;
import java.util.Map.Entry;

import com.rideboard.data.model.UserModel;

public class UserDao {
	public UserModel findUserById(int key) {
		return Database.findUserById(key);
	}

	public UserModel findUserByUserName(String userName) {
		return Database.findUserByUserName(userName);
	}
	
	public void updateUser(UserModel user) {
		Database.updateUserById(user.getUserid(), user);
	}
	
	public String getAllUsers() {
		String retVal = "";
		 Set<Entry<Integer, UserModel>> list = Database.listUsers();
		 for(Entry<Integer, UserModel> entry:list) {
			 UserModel model = entry.getValue();
			retVal += " | [" + entry.getKey() + "] " + model.getUser_name() + ", " + model.getStatus() + ", " + model.getAttempt_count();  
		 }
		 return retVal;
	}
}
