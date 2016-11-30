package accounts;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by turge on 028 28.11.16.
 * loginToProfile - зарегистрированные пользователи
 * sessionIdToProfile - залогиненные пользователи
 */
public class AccountService {
    private final Map<String, UserProfile> loginToProfile;
    private final Map<String, UserProfile> sessionIdToProfile;

    public AccountService() {
        this.loginToProfile = new HashMap<>();
        this.sessionIdToProfile = new HashMap<>();
    }

    public void addNewUser(UserProfile userProfile) {
        loginToProfile.put(userProfile.getLogin(), userProfile);
    }

    public void addNewSession(String sessionId, UserProfile userProfile) {
        sessionIdToProfile.put(sessionId, userProfile);
    }

    public UserProfile getUserByLogin(String login) {
        return loginToProfile.get(login);
    }
}
