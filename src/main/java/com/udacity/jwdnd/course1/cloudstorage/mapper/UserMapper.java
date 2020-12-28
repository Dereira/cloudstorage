package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
  @Insert(
      "INSERT INTO USERS (userid, username, salt, password, firstname, lastname) VALUES(#{userid}, #{username}, #{salt}, #{password}, #{firstName}, #{lastName})")
  @Options(useGeneratedKeys = true, keyProperty = "userid")
  int insertUser(User user);

  @Select("SELECT * FROM USERS WHERE username = #{username}")
  User getUser(String username);
}
