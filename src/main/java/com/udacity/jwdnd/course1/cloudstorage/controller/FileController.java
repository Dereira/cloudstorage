package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.service.FileService;
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

  public FileController(FileService fileService) {
    this.fileService = fileService;
  }

  @GetMapping("/files/{fileId}")
  public String getFile(@PathVariable Integer fileId, Model model, Authentication auth) {
    try {
      File file = fileService.getFile(auth, fileId);

      ResponseEntity.ok()
          .header(
              HttpHeaders.CONTENT_DISPOSITION,
              "attachment; filename=\"" + file.getFilename() + "\"")
          .body(new ByteArrayResource(file.getFiledata()));
      return "home";
    } catch (Exception e) {
      e.printStackTrace();
      model.addAttribute("fileError", "There was an error downloading the file, please try again.");
      return "home";
    }
  }

  @PostMapping("/files")
  public String insertFile(
      @RequestAttribute MultipartFile fileUpload, Model model, Authentication auth)
      throws IOException {
    Boolean fileStatus;
    String fileError = null;

    if (fileUpload.getSize() == 0) {
      fileError = "The file is not valid, please try again.";
    }

    if (fileUpload.getSize() > 10485760) {
      fileError = "The file is too big, please try again.";
    }

    fileStatus = fileService.insertFile(auth, fileUpload);

    if (!fileStatus) {
      fileError = "The file name already exists, please try with a different one.";
    }

    model.addAttribute("fileError", fileError);
    model.addAttribute("fileStatus", fileStatus);

    return "result";
  }

  @GetMapping("/files/delete/{fileId}")
  public String deleteFile(@PathVariable Integer fileId, Model model, Authentication auth) {
    int fileRows = fileService.deleteFile(auth, fileId);

    if (fileRows == 0) {
      model.addAttribute("fileError", "There was an error deleting the file, please try again.");
      return "result";
    }

    model.addAttribute("fileStatus", true);
    return "result";
  }
}
