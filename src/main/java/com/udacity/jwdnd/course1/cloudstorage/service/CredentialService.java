package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Service
public class CredentialService {
  private final CredentialMapper credentialMapper;
  private final UserService userService;
  private final EncryptionService encryptionService;

  public CredentialService(
      CredentialMapper credentialMapper,
      UserService userService,
      EncryptionService encryptionService) {
    this.credentialMapper = credentialMapper;
    this.userService = userService;
    this.encryptionService = encryptionService;
  }

  public void create(Authentication auth, Credential credential) {
    User user = userService.read(auth.getName());

    SecureRandom random = new SecureRandom();
    byte[] key = new byte[16];
    random.nextBytes(key);
    String encodedKey = Base64.getEncoder().encodeToString(key);

    credentialMapper.create(
        new Credential(
            null,
            credential.getUrl(),
            credential.getUsername(),
            encodedKey,
            encryptionService.encryptValue(credential.getPassword(), encodedKey),
            user.getUserid()));
  }

  public List<Credential> read(Authentication auth) {
    User user = userService.read(auth.getName());
    List<Credential> credentials = credentialMapper.readAll(user.getUserid());

    credentials.forEach(
        credential ->
            credential.setPassword(
                encryptionService.decryptValue(credential.getPassword(), credential.getKey())));

    return credentials;
  }

  public void update(Authentication auth, Credential credential) {
    User user = userService.read(auth.getName());
    String key = credentialMapper.read(user.getUserid(), credential.getCredentialId()).getKey();

    credentialMapper.update(
        new Credential(
            credential.getCredentialId(),
            credential.getUrl(),
            credential.getUsername(),
            key,
            encryptionService.encryptValue(credential.getPassword(), key),
            user.getUserid()));
  }

  public int delete(Authentication auth, int credentialId) {
    User user = userService.read(auth.getName());
    return credentialMapper.delete(user.getUserid(), credentialId);
  }
}
