package com.demo.hackernews.domain.users;

public class User {
    private String  userHandle;
    private long userAccountAge;

    public String getUserHandle() {
        return userHandle;
    }

    public void setUserHandle(String userHandle) {
        this.userHandle = userHandle;
    }

    public long getUserAccountAge() {
        return userAccountAge;
    }

    public void setUserAccountAge(long userAccountAge) {
        this.userAccountAge = userAccountAge;
    }
}
