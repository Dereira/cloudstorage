package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialMapper {
  @Insert(
      "INSERT INTO CREDENTIALS (credentialid, url, username, key, password, userid) VALUES(#{credentialId}, #{url}, #{username}, #{key}, #{password}, #{userid})")
  @Options(useGeneratedKeys = true, keyProperty = "credentialId")
  void create(Credential credential);

  @Select("SELECT * FROM CREDENTIALS WHERE userid=#{userid} AND credentialid=#{credentialId}")
  Credential read(int userid, int credentialId);

  @Select("SELECT * FROM CREDENTIALS WHERE userid=#{userId}")
  List<Credential> readAll(int userId);

  @Update(
      "UPDATE CREDENTIALS SET url=#{url}, username=#{username}, key=#{key}, password=#{password} WHERE credentialid=#{credentialId}")
  void update(Credential credential);

  @Delete("DELETE FROM CREDENTIALS WHERE userid=#{userId} AND credentialid=#{credentialId}")
  int delete(int userId, int credentialId);
}
