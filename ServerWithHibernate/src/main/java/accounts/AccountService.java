package accounts;

import dbService.DBService;
import dbService.datasets.UserDataSet;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by turge on 028 28.11.16.
 * sessionIdToProfile - залогиненные пользователи
 */
public class AccountService {
    private final Map<String, UserProfile> sessionIdToProfile;
    private final DBService db;

    public AccountService() {
        this.sessionIdToProfile = new HashMap<>();
        db = new DBService();
    }

    public void addNewUser(UserProfile userProfile) {
        db.addUser(userProfile.getLogin(), userProfile.getPass());
    }

    public void addNewSession(String sessionId, UserProfile userProfile) {
        sessionIdToProfile.put(sessionId, userProfile);
    }

    public UserProfile getUserByLogin(String login) {
        UserDataSet userDataSet = db.getUser(login);
        return new UserProfile(userDataSet.getLogin(), userDataSet.getPassword());
    }
}
