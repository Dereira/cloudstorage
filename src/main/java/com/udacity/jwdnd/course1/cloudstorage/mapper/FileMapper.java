package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper {
  @Insert(
      "INSERT INTO FILES (fileId, filename, contenttype, userid, filesize, filedata) VALUES(#{fileId}, #{filename}, #{contenttype}, #{userid}, #{filesize}, #{filedata})")
  @Options(useGeneratedKeys = true, keyProperty = "fileId")
  int insertFile(File file);

  @Select("SELECT * FROM FILES WHERE userid=#{userid}")
  List<File> getFiles(Integer userid);

  @Select("SELECT * FROM FILES WHERE fileId=#{fileId} AND userid=#{userid}")
  File getFile(Integer fileId, Integer userid);

  @Select("SELECT * FROM FILES WHERE filename=#{fileName} AND userid=#{userid}")
  File findFile(String fileName, Integer userid);

  @Delete("DELETE FROM FILES WHERE fileId=#{fileId} AND userid=#{userid}")
  int deleteFile(Integer fileId, Integer userid);
}
