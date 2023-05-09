package itu.project.reeexample;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;

@RestController
public class EncryptionController {

  @GetMapping("/key")
  public String getKey(){
    byte[] bytes = new byte[0];
    try {
      bytes = EncryptionController.class.getResourceAsStream("/key.txt").readAllBytes();
      return new String(bytes);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
  @GetMapping("/encryption")
  public String encryption(@RequestParam String plain) throws NoSuchPaddingException, NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException, ShortBufferException, IllegalBlockSizeException, BadPaddingException {
    Security.addProvider(new BouncyCastleProvider());
    byte[] keyBytes = getKey().getBytes();
    byte[] input=plain.getBytes();
    SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");
    Cipher cipher = Cipher.getInstance("AES/ECB/PKCS7Padding", "BC");
    cipher.init(Cipher.ENCRYPT_MODE, key);
    byte[] cipherText = new byte[cipher.getOutputSize(input.length)];
    int ctLength = cipher.update(input, 0, input.length, cipherText, 0);
    ctLength += cipher.doFinal(cipherText, ctLength);
    return Hex.toHexString(cipherText).toUpperCase();
  }
}
