package de.biware.mailchecker.api;

public class MailAccount {

    private final String name;
    private final String user;
    private final String password;
    private final String host;
    private final int port;

    public MailAccount(String name, String user, String password, String host, int port) {
        this.name = name;
        this.user = user;
        this.password = password;
        this.host = host;
        this.port = port;
    }

    public String getName() {
        return this.name;
    }

    public String getUser() {
        return this.user;
    }

    public String getPassword() {
        return this.password;
    }

    public String getHost() {
        return this.host;
    }

    public int getPort() {
        return this.port;
    }

    @Override
    public String toString() {
        return "MailAccount{name=" + this.name + ", user=" + this.user + ", password=" + this.password + ", host=" + this.host + ", port=" + this.port + '}';
    }
}
