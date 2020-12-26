package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialMapper {
  @Insert(
      "INSERT INTO CREDENTIALS (credentialid, url, username, key, password, userid) VALUES(#{credentialid}, #{url}, #{username}, #{key}, #{password}, #{userid})")
  @Options(useGeneratedKeys = true, keyProperty = "credentialid")
  int insertCredential(Credential credential);

  @Select("SELECT * FROM CREDENTIALS WHERE userid=#{userid}")
  List<Credential> getCredentials(Integer userid);

  @Select("SELECT * FROM CREDENTIALS WHERE credentialid=#{credentialid}")
  Credential getCredential(Integer credentialid);

  @Update(
      "UPDATE CREDENTIALS SET url=#{url}, username=#{username}, key=#{key}, password=#{password} WHERE credentialid=#{credentialid}")
  void updateCredential(Credential credential);

  @Delete("DELETE FROM CREDENTIALS WHERE credentialid=#{credentialid}")
  void deleteCredential(Integer credentialid);
}
