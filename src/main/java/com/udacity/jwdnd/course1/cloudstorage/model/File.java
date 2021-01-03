package com.udacity.jwdnd.course1.cloudstorage.model;

public class File {

  private Integer fileId;
  private String filename;
  private String contentType;
  private Integer userid;
  private String filesize;
  private byte[] fileData;

  public File(
      Integer fileId,
      String filename,
      String contentType,
      Integer userid,
      String filesize,
      byte[] fileData) {
    this.fileId = fileId;
    this.filename = filename;
    this.contentType = contentType;
    this.userid = userid;
    this.filesize = filesize;
    this.fileData = fileData;
  }

  public Integer getFileId() {
    return fileId;
  }

  public void setFileId(Integer fileId) {
    this.fileId = fileId;
  }

  public String getFilename() {
    return filename;
  }

  public void setFilename(String filename) {
    this.filename = filename;
  }

  public String getContentType() {
    return contentType;
  }

  public void setContentType(String contentType) {
    this.contentType = contentType;
  }

  public Integer getUserid() {
    return userid;
  }

  public void setUserid(Integer userid) {
    this.userid = userid;
  }

  public String getFilesize() {
    return filesize;
  }

  public void setFilesize(String filesize) {
    this.filesize = filesize;
  }

  public byte[] getFileData() {
    return fileData;
  }

  public void setFileData(byte[] fileData) {
    this.fileData = fileData;
  }
}
