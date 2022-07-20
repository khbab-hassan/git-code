package conn;

public class admin {

    private String id;
    private String username;
    private String password;
    private String fullname;

    public admin() {
        super();
    }

    public admin(String id, String username, String password) {
        super();
        this.id = id;
        this.password = password;
        this.username = username;
    }

    public String getId() {
        return id;

    }

    public void setId(String id) {
        this.id = id;
        
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
}
