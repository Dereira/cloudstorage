package com.udacity.jwdnd.course1.cloudstorage.model;

public class File {

  private Integer fileId;
  private String filename;
  private String contenttype;
  private Integer userid;
  private String filesize;
  private byte[] filedata;

  public File(
      Integer fileId,
      String filename,
      String contenttype,
      Integer userid,
      String filesize,
      byte[] filedata) {
    this.fileId = fileId;
    this.filename = filename;
    this.contenttype = contenttype;
    this.userid = userid;
    this.filesize = filesize;
    this.filedata = filedata;
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

  public String getContenttype() {
    return contenttype;
  }

  public void setContenttype(String contenttype) {
    this.contenttype = contenttype;
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

  public byte[] getFiledata() {
    return filedata;
  }

  public void setFiledata(byte[] filedata) {
    this.filedata = filedata;
  }
}
