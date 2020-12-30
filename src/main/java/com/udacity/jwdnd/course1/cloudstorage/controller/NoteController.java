package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.service.FileService;
import com.udacity.jwdnd.course1.cloudstorage.service.NoteService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class NoteController {
  private final NoteService noteService;
  private final FileService fileService;

  public NoteController(NoteService noteService, FileService fileService) {
    this.noteService = noteService;
    this.fileService = fileService;
  }

  @PostMapping("/notes")
  public String addNote(@ModelAttribute Note note, Model model, Authentication auth) {
    Optional<Integer> noteId = Optional.ofNullable(note.getNoteid());
    String actionMessage;

    if (noteId.isPresent()) {
      noteService.updateNote(auth, note);
      actionMessage = "Your note was successfully updated.";
    } else {
      noteService.insertNote(auth, note);
      actionMessage = "Your note was successfully saved.";
    }

    addAttributes(model, actionMessage, auth);
    return "home";
  }

  @GetMapping("/notes/delete/{noteId}")
  public String deleteFile(@PathVariable Integer noteId, Model model, Authentication auth) {
    String actionMessage = "Your note was successfully deleted.";

    int noteRows = noteService.deleteNote(auth, noteId);

    if (noteRows == 0) {
      actionMessage = "There was an error deleting the note, please try again.";
    }

    addAttributes(model, actionMessage, auth);
    return "home";
  }

  private void addAttributes(Model model, String actionMessage, Authentication auth) {
    model.addAttribute("actionMessage", actionMessage);
    model.addAttribute("isNotesActive", true);
    model.addAttribute("files", fileService.getFiles(auth));
    model.addAttribute("notes", noteService.getNotes(auth));
  }
}
