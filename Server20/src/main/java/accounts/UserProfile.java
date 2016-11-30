package accounts;

/**
 * Created by turge on 028 28.11.16.
 */
public class UserProfile {
    private final String login;
    private final String pass;

    public UserProfile(String login, String pass) {
        this.login = login;
        this.pass = pass;
    }

    public String getLogin() {
        return login;
    }

    public String getPass() {
        return pass;
    }
}
