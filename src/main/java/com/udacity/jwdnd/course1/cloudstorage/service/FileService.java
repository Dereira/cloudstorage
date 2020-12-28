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

  public File getFile(Integer fileId) {
    return fileMapper.getFile(fileId);
  }

  public List<File> getFiles(Authentication auth) {
    User user = userService.getUser(auth.getName());
    return fileMapper.getFiles(user.getUserid());
  }

  public Integer insertFile(Authentication auth, MultipartFile file) throws IOException {
    User user = userService.getUser(auth.getName());
    return fileMapper.insertFile(
        new File(
            null,
            file.getOriginalFilename(),
            file.getContentType(),
            user.getUserid(),
            Long.toString(file.getSize()),
            file.getBytes()));
  }

  public void deleteFile(Integer fileId) {
    fileMapper.deleteFile(fileId);
  }
}
