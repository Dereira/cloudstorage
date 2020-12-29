package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.service.FileService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {
  private final FileService fileService;

  public HomeController(FileService fileService) {
    this.fileService = fileService;
  }

  @GetMapping
  public String homeView(Model model, Authentication auth) {
    model.addAttribute("files", fileService.getFiles(auth));

    return "home";
  }
}
