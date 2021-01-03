package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.service.FileService;
import com.udacity.jwdnd.course1.cloudstorage.service.NoteService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class FileController {
  private final FileService fileService;
  private final NoteService noteService;

  public FileController(FileService fileService, NoteService noteService) {
    this.fileService = fileService;
    this.noteService = noteService;
  }

  @GetMapping("/file/{fileId}")
  public ResponseEntity read(@PathVariable Integer fileId, Model model, Authentication auth) {
    try {
      File file = fileService.read(auth, fileId);

      return ResponseEntity.ok()
          .header(
              HttpHeaders.CONTENT_DISPOSITION,
              "attachment; filename=\"" + file.getFilename() + "\"")
          .body(new ByteArrayResource(file.getFileData()));
    } catch (Exception e) {
      e.printStackTrace();

      String actionMessage = "There was an error downloading the file, please try again.";
      addAttributes(model, actionMessage, auth);
      return null;
    }
  }

  @PostMapping("/file")
  public String create(@RequestAttribute MultipartFile fileUpload, Model model, Authentication auth)
      throws IOException {
    String actionMessage = null;

    if (fileUpload.getSize() == 0) {
      actionMessage = "The file is not valid, please try again.";
    } else if (fileUpload.getSize() > 10485760) {
      actionMessage = "The file is too big, please try again.";
    }

    if (actionMessage == null) {
      Boolean isOk = fileService.create(auth, fileUpload);
      if (isOk) {
        actionMessage = "The file was successfully saved.";
      } else {
        actionMessage = "The file name already exists, please try with a different one.";
      }
    }

    addAttributes(model, actionMessage, auth);
    return "home";
  }

  @GetMapping("/file/delete/{fileId}")
  public String delete(@PathVariable Integer fileId, Model model, Authentication auth) {
    String actionMessage = "The file was successfully deleted";
    int fileRows = fileService.delete(auth, fileId);

    if (fileRows == 0) {
      actionMessage = "There was an error deleting the file, please try again.";
    }

    addAttributes(model, actionMessage, auth);
    return "home";
  }

  private void addAttributes(Model model, String actionMessage, Authentication auth) {
    model.addAttribute("actionMessage", actionMessage);
    model.addAttribute("isFileActive", true);
    model.addAttribute("files", fileService.readAll(auth));
    model.addAttribute("notes", noteService.read(auth));
  }
}
