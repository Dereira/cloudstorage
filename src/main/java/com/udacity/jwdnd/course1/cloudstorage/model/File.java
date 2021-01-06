package com.udacity.jwdnd.course1.cloudstorage.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class File {
  private Integer fileId;
  private String filename;
  private String contentType;
  private Integer userid;
  private String filesize;
  private byte[] fileData;
}
