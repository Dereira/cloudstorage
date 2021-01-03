package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper {
  @Insert(
      "INSERT INTO FILES (fileId, filename, contenttype, userid, filesize, filedata) VALUES(#{fileId}, #{filename}, #{contentType}, #{userid}, #{filesize}, #{fileData})")
  @Options(useGeneratedKeys = true, keyProperty = "fileId")
  void create(File file);

  @Select("SELECT * FROM FILES WHERE userid=#{userid}")
  List<File> readAll(Integer userid);

  @Select("SELECT * FROM FILES WHERE fileId=#{fileId} AND userid=#{userid}")
  File read(Integer fileId, Integer userid);

  @Select("SELECT * FROM FILES WHERE filename=#{fileName} AND userid=#{userid}")
  File find(String fileName, Integer userid);

  @Delete("DELETE FROM FILES WHERE fileId=#{fileId} AND userid=#{userid}")
  int delete(Integer fileId, Integer userid);
}
