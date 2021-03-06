package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {
  @Insert(
      "INSERT INTO NOTES (noteid, notetitle, notedescription, userid) VALUES(#{noteId}, #{noteTitle}, #{noteDescription}, #{userId})")
  @Options(useGeneratedKeys = true, keyProperty = "noteId")
  void create(Note note);

  @Select("SELECT * FROM NOTES WHERE userid=#{userId}")
  List<Note> read(Integer userId);

  @Update(
      "UPDATE NOTES SET notetitle = #{noteTitle}, notedescription = #{noteDescription} WHERE noteid = #{noteId} and userid = #{userId}")
  void update(Note note);

  @Delete("DELETE FROM NOTES WHERE userid=#{userId} AND noteid=#{noteId}")
  int delete(Integer noteId, Integer userId);
}
