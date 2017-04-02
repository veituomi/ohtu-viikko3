package ohtu.domain;

public class User {

    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    public User(String usernameTabPassword) {
        String[] parts = usernameTabPassword.split("\t");
        this.username = parts[0];
        this.password = parts[1];
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
}
