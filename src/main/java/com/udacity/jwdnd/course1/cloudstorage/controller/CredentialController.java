package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.service.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.service.FileService;
import com.udacity.jwdnd.course1.cloudstorage.service.NoteService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
public class CredentialController {
  private final CredentialService credentialService;
  private final NoteService noteService;
  private final FileService fileService;

  public CredentialController(
      CredentialService credentialService, NoteService noteService, FileService fileService) {
    this.credentialService = credentialService;
    this.noteService = noteService;
    this.fileService = fileService;
  }

  @PostMapping("/credential")
  public String createUpdate(
      @ModelAttribute Credential credential, Model model, Authentication auth) {
    Optional<Integer> credentialId = Optional.ofNullable(credential.getCredentialId());
    String actionMessage;

    if (credentialId.isPresent()) {
      credentialService.update(auth, credential);
      actionMessage = "Your credential was successfully updated.";
    } else {
      credentialService.create(auth, credential);
      actionMessage = "Your credential was successfully saved.";
    }

    addAttributes(model, actionMessage, auth);
    return "home";
  }

  @GetMapping("/credential/delete/{credentialId}")
  public String delete(@PathVariable Integer credentialId, Model model, Authentication auth) {
    String actionMessage = "Your credential was successfully deleted.";

    int credentialRows = credentialService.delete(auth, credentialId);

    if (credentialRows == 0) {
      actionMessage = "There was an error deleting the credential, please try again.";
    }

    addAttributes(model, actionMessage, auth);
    return "home";
  }

  @GetMapping("/password/{credentialId}")
  @ResponseBody
  public Map<String, String> decryptPassword(
      @PathVariable Integer credentialId, Authentication auth) {
    String password = credentialService.decryptPassword(auth, credentialId);

    Map<String, String> response = new HashMap<>();
    response.put("password", password);
    return response;
  }

  private void addAttributes(Model model, String actionMessage, Authentication auth) {
    model.addAttribute("actionMessage", actionMessage);
    model.addAttribute("isCredentialActive", true);
    model.addAttribute("credentials", credentialService.read(auth));
    model.addAttribute("files", fileService.readAll(auth));
    model.addAttribute("notes", noteService.read(auth));
  }
}
