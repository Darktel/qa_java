package praktikum.courier;

public class CredentialsLoginPassword {
    private String login;
    private String password;

    public CredentialsLoginPassword(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public static CredentialsLoginPassword from(Courier courier) {
        return new CredentialsLoginPassword(courier.getLogin(), courier.getPassword());
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
