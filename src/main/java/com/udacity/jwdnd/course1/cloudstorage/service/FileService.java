package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class FileService {
  private final FileMapper fileMapper;
  private final UserService userService;

  public FileService(FileMapper fileMapper, UserService userService) {
    this.fileMapper = fileMapper;
    this.userService = userService;
  }

  public File read(Authentication auth, Integer fileId) {
    User user = userService.read(auth.getName());
    return fileMapper.read(fileId, user.getUserid());
  }

  public List<File> readAll(Authentication auth) {
    User user = userService.read(auth.getName());
    return fileMapper.readAll(user.getUserid());
  }

  public Boolean create(Authentication auth, MultipartFile file) throws IOException {
    User user = userService.read(auth.getName());
    File fileExists = fileMapper.find(file.getOriginalFilename(), user.getUserid());

    if (fileExists != null) {
      return false;
    }

    fileMapper.create(
        new File(
            null,
            file.getOriginalFilename(),
            file.getContentType(),
            user.getUserid(),
            Long.toString(file.getSize()),
            file.getBytes()));

    return true;
  }

  public int delete(Authentication auth, Integer fileId) {
    User user = userService.read(auth.getName());
    return fileMapper.delete(fileId, user.getUserid());
  }
}
