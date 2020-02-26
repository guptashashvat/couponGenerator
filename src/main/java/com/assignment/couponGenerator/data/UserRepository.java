package com.assignment.couponGenerator.data;

import com.assignment.couponGenerator.beans.UserData;

public interface UserRepository {
Iterable<UserData> findAll();
UserData findOne(String userId);
UserData save(UserData data);
}
