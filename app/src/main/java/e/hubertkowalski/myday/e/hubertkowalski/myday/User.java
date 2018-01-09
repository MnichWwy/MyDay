package e.hubertkowalski.myday;

import com.orm.SugarRecord;

public class User extends SugarRecord<User> {
    private String login;

    public void setPassword(String password) {
        this.password = password;
    }

    private String password;

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public User() {
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Override
    public String toString() {
        return this.login;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || object.getClass() != getClass()) return false;

        User user_ = (User) object;
        if (this.login.equals(user_.getLogin()) && this.password.equals(user_.getPassword())) {
            return true;
        }
        return false;
    }
}