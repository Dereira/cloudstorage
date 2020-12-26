package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {
  @Insert(
      "INSERT INTO NOTES (noteid, notetitle, notedescription, userid) VALUES(#{noteid}, #{notetitle}, #{notedescription}, #{userid})")
  @Options(useGeneratedKeys = true, keyProperty = "noteid")
  int insertNote(Note note);

  @Select("SELECT * FROM NOTES WHERE userid=#{userid}")
  List<Note> getNotes(Integer userid);

  @Select("SELECT * FROM NOTES WHERE userid=#{userid}")
  Note getNote(Integer userid);

  @Update(
      "UPDATE NOTES SET notetitle=#{notetitle}, notedescription=#{notedescription} WHERE noteid=#{noteid}")
  void updateNote(Note note);

  @Delete("DELETE FROM NOTES WHERE noteid=#{noteid}")
  void deleteNote(Integer noteid);
}
