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

  public void create(Authentication auth, Note note) {
    User user = userService.read(auth.getName());

    noteMapper.create(
        new Note(null, note.getNoteTitle(), note.getNoteDescription(), user.getUserid()));
  }

  public List<Note> read(Authentication auth) {
    User user = userService.read(auth.getName());
    return noteMapper.read(user.getUserid());
  }

  public void update(Authentication auth, Note note) {
    User user = userService.read(auth.getName());
    noteMapper.update(
        new Note(
            note.getNoteId(), note.getNoteTitle(), note.getNoteDescription(), user.getUserid()));
  }

  public int delete(Authentication auth, Integer noteId) {
    User user = userService.read(auth.getName());
    return noteMapper.delete(noteId, user.getUserid());
  }
}
