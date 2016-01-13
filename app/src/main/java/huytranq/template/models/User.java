package huytranq.template.models;

/**
 * Created by huytr on 13-01-2016.
 */
public class User {

    public static final String TABLE = "User";
    public static final String USERNAME = "Username";
    public static final String ID = "id";
    public static final String BIRTHDAY = "DateOfBirth";
    public static final String PASSWORD = "Password";

    private String username;
    private int id;
    private String birthday;
    private byte[] password;

    public User(int id, String username, byte[] password, String birthday) {
        this.username = username;
        this.id = id;
        this.birthday = birthday;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public byte[] getPassword() {
        return password;
    }

    public void setPassword(byte[] password) {
        this.password = password;
    }
}
