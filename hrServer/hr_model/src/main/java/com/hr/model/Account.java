package com.hr.model;

/**
 * ClassName: Account
 * Description:
 * date: 2023/1/7 21:56
 *
 * @author: Jenf
 * @version:
 * @since JDK 17
 */
public class Account {

    private String accountId;

    private String password;

    public Account(String accountId, String password) {
        this.accountId = accountId;
        this.password = password;
    }

    public Account() {
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId='" + accountId + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
