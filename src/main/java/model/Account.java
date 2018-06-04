package model;

import java.math.BigInteger;

/**
 * @author guyue
 * @date 2018/6/4
 */
public class Account {

    private String id;

    private String password;

    private BigInteger authority;

    public Account() {}

    public Account(String id, String password, BigInteger authority) {
        this.id = id;
        this.password = password;
        this.authority = authority;
    }

    public Account(String id, String password, int authority) {
        this.id = id;
        this.password = password;
        this.authority = BigInteger.valueOf(authority);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public BigInteger getAuthority() {
        return authority;
    }

    public void setAuthority(BigInteger authority) {
        this.authority = authority;
    }
}
