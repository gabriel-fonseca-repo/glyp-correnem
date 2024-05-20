package br.com.glyp.msorm.crypto;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.KeySpec;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

public class Crypto {

  private Cipher embaralhar;

  private Cipher desembaralhar;

  public Crypto() throws GeneralSecurityException {
    this("9XD8L3aL4J");
  }

  public Crypto(String passPhrase) throws GeneralSecurityException {
    byte salt[] = { -87, -101, -57, 51, 85, 54, -28, 2 };
    int iterationCount = 27;

    KeySpec keySpec = new PBEKeySpec(
      passPhrase.toCharArray(),
      salt,
      iterationCount
    );

    SecretKey key = SecretKeyFactory
      .getInstance("PBEWithMD5AndDES")
      .generateSecret(keySpec);

    embaralhar = Cipher.getInstance(key.getAlgorithm());
    desembaralhar = Cipher.getInstance(key.getAlgorithm());

    AlgorithmParameterSpec paramSpec = new PBEParameterSpec(
      salt,
      iterationCount
    );

    embaralhar.init(1, key, paramSpec);
    desembaralhar.init(2, key, paramSpec);
  }

  public String encrypt(String str)
    throws GeneralSecurityException, UnsupportedEncodingException {
    byte utf8[] = str.getBytes("UTF8");
    byte enc[] = embaralhar.doFinal(utf8);
    return new String(Base64.getEncoder().encode(enc));
  }

  public String decrypt(String str)
    throws GeneralSecurityException, UnsupportedEncodingException {
    byte dec[] = (Base64.getDecoder().decode(str));
    byte utf8[] = desembaralhar.doFinal(dec);
    return new String(utf8, "UTF8");
  }

  public static void main(String args[]) {
    try {
      Crypto encrypter = new Crypto();

      String encrypted = encrypter.encrypt("hminovacao");
      String decrypted = encrypter.decrypt(encrypted);

      System.out.println(
        String.format(
          """
            Senha criptografada: %s
            Senha descriptografada: %s
          """,
          encrypted,
          decrypted
        )
      );
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
