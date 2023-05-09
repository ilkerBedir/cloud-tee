package itu.project.trustedapp;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.KeyGenerator;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;

@RestController
public class EncryptionController {

  @GetMapping("/key")
  public String getKey() throws NoSuchAlgorithmException, NoSuchProviderException {
    Security.addProvider(new BouncyCastleProvider());
    KeyGenerator generator = KeyGenerator.getInstance("AES", "BC");
    generator.init(256);
    Key encryptionKey = generator.generateKey();
    return Hex.toHexString(encryptionKey.getEncoded()).toUpperCase();
  }
}

