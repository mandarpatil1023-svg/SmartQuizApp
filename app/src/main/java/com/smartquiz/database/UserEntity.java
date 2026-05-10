package com.smartquiz.database;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "users")
public class UserEntity {
    @PrimaryKey
    @NonNull
    private String uid;
    private String name;
    private String email;
    private String phone;
    private String location;
    private String targetExam;
    private String profileImageBase64;

    public UserEntity(@NonNull String uid, String name, String email, String phone,
                      String location, String targetExam, String profileImageBase64) {
        this.uid = uid;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.location = location;
        this.targetExam = targetExam;
        this.profileImageBase64 = profileImageBase64;
    }

    @NonNull
    public String getUid() {
        return uid;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getLocation() {
        return location;
    }

    public String getTargetExam() {
        return targetExam;
    }

    public String getProfileImageBase64() {
        return profileImageBase64;
    }
}
