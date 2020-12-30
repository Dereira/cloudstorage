package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {
  private final NoteMapper noteMapper;
  private final UserService userService;

  public NoteService(NoteMapper noteMapper, UserService userService) {
    this.noteMapper = noteMapper;
    this.userService = userService;
  }

  public List<Note> getNotes(Authentication auth) {
    User user = userService.getUser(auth.getName());
    return noteMapper.getNotes(user.getUserid());
  }

  public int insertNote(Authentication auth, Note note) {
    User user = userService.getUser(auth.getName());

    return noteMapper.insertNote(
        new Note(null, note.getNotetitle(), note.getNotedescription(), user.getUserid()));
  }

  public int deleteNote(Authentication auth, Integer noteId) {
    User user = userService.getUser(auth.getName());
    return noteMapper.deleteNote(noteId, user.getUserid());
  }

  public int updateNote(Authentication auth, Note note) {
    User user = userService.getUser(auth.getName());
    return noteMapper.updateNote(
        new Note(
            note.getNoteid(), note.getNotetitle(), note.getNotedescription(), user.getUserid()));
  }
}
