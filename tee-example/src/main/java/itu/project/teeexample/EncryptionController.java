package itu.project.teeexample;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.util.Base64;

@RestController
public class EncryptionController {

  public String getKey() throws IOException, InterruptedException {
    String auth = Base64.getEncoder().encodeToString("user:9164bdec-7e62-4d83-845f-cd9756b5c2af".getBytes());
    HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://localhost:8083/key")).GET()
        .setHeader(HttpHeaders.AUTHORIZATION, "Basic " + auth).build();
    HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
    return response.body();
  }
  @GetMapping("/encryption")
  public String encryption(@RequestParam String plain) throws NoSuchPaddingException, NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException, ShortBufferException, IllegalBlockSizeException, BadPaddingException, IOException, InterruptedException {
    Security.addProvider(new BouncyCastleProvider());
    byte[] keyBytes = Hex.decode(getKey());
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
