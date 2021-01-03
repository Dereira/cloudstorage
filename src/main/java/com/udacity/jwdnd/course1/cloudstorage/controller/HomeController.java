package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.service.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.service.FileService;
import com.udacity.jwdnd.course1.cloudstorage.service.NoteService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {
  private final FileService fileService;
  private final NoteService noteService;
  private final CredentialService credentialService;

  public HomeController(
      FileService fileService, NoteService noteService, CredentialService credentialService) {
    this.fileService = fileService;
    this.noteService = noteService;
    this.credentialService = credentialService;
  }

  @GetMapping
  public String homeView(Model model, Authentication auth) {
    model.addAttribute("files", fileService.readAll(auth));
    model.addAttribute("credentials", credentialService.read(auth));
    model.addAttribute("notes", noteService.read(auth));
    model.addAttribute("isFileActive", true);

    return "home";
  }
}
