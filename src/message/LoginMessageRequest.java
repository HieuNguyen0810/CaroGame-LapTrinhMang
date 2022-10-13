package message;

import java.io.Serializable;

public class LoginMessageRequest implements Serializable {
    private static final long serialVersionUID = 1234567L;
    private String username;
    private String password;

    public LoginMessageRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString(){
        return username + String.valueOf(this.password);
    }
}
