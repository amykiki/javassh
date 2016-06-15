package com.github.amysue.shiro.chapter5;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.CodecSupport;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.*;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;
import org.junit.Assert;
import org.junit.Test;

import javax.xml.stream.events.EndDocument;
import java.security.Key;

/**
 * Created by Amysue on 2016/6/14.
 */
public class CodecAndCryptoTest {

    @Test
    public void testBase64() {
        String str = "hello";
        String base64Encoded = Base64.encodeToString(str.getBytes());
        String decode = Base64.decodeToString(base64Encoded);
        System.out.println("str = " + str);
        System.out.println("base64Encoded = " + base64Encoded);
        System.out.println("decode = " + decode);
    }

    @Test
    public void testHex() {
        String str = "hello";
        String hexEncode = Hex.encodeToString(str.getBytes());
        String decode = new String(Hex.decode(hexEncode));
        System.out.println("str = " + str);
        System.out.println("base64Encoded = " + hexEncode);
        System.out.println("decode = " + decode);
        Assert.assertEquals(str, decode);
    }

    @Test
    public void testCodecSupport() {
        String str = "hello";
        byte[] strBytes = str.getBytes();
        byte[] bytes = CodecSupport.toBytes(str, "utf-8");
        String decode = CodecSupport.toString(bytes, "utf-8");
        Assert.assertEquals(str, decode);
    }

    @Test
    public void testMD5() {
        String str = "hello";
        String salt = "123";
        String withoutSalt = new Md5Hash(str).toString();
        String withSalt = new Md5Hash(str, salt).toString();
        String withSalt2 = new Md5Hash(str, salt, 2).toString();
        System.out.println("withoutSalt = " + withoutSalt);
        System.out.println("withSalt = " + withSalt);
        System.out.println("withSalt2 = " + withSalt2);
    }

    @Test
    public void testSHA256() {
        String str = "hello";
        String salt = "123";
        String withoutSalt = new Sha256Hash(str).toString();
        String withSalt = new Sha256Hash(str, salt).toString();
        String withSalt2 = new Sha256Hash(str, salt, 2).toString();
        System.out.println("withoutSalt = " + withoutSalt);
        System.out.println("withSalt = " + withSalt);
        System.out.println("withSalt2 = " + withSalt2);
    }
    @Test
    public void testSimpleHashSHA512() {
        String str = "hello";
        String salt = "123";
        String withoutSalt = new SimpleHash("SHA-512", str).toString();
        String withSalt = new SimpleHash("SHA-512", str, salt).toString();
        String withSalt2 = new SimpleHash("SHA-512", str, salt, 2).toString();
        System.out.println("withoutSalt = " + withoutSalt);
        System.out.println("withSalt = " + withSalt);
        System.out.println("withSalt2 = " + withSalt2);
        String withoutSalt1 = new Sha512Hash(str).toString();
        String withSalt1 = new Sha512Hash(str, salt).toString();
        String withSalt12 = new Sha512Hash(str, salt, 2).toString();
        System.out.println("withoutSalt1 = " + withoutSalt1);
        System.out.println("withSalt1 = " + withSalt1);
        System.out.println("withSalt12 = " + withSalt12);
        Assert.assertEquals(withoutSalt, withoutSalt1);
        Assert.assertEquals(withSalt, withSalt1);
        Assert.assertEquals(withSalt2, withSalt12);
    }

    @Test
    public void testRandom() {
        SecureRandomNumberGenerator generator = new SecureRandomNumberGenerator();
        generator.setSeed("hello".getBytes());
        System.out.println(generator.nextBytes().toString());
        System.out.println(generator.nextBytes().toHex());
        System.out.println(generator.nextBytes().toBase64());
    }

    @Test
    public void testHashService() {
        DefaultHashService hashService = new DefaultHashService();
        hashService.setHashAlgorithmName("SHA-256");
        hashService.setPrivateSalt(new SimpleByteSource("456"));
        hashService.setGeneratePublicSalt(true);
        hashService.setRandomNumberGenerator(new SecureRandomNumberGenerator());
        hashService.setHashIterations(1);

        HashRequest request = new HashRequest.Builder().setAlgorithmName("MD5")
                .setSource(ByteSource.Util.bytes("hello"))
                .setSalt(ByteSource.Util.bytes("123"))
                .setIterations(2).build();
        String hex = hashService.computeHash(request).toHex();
        System.out.println("hex = " + hex);
    }

    @Test
    public void testAesCipherService() {
        AesCipherService service = new AesCipherService();
        service.setKeySize(128);

        String src = "hello";
        Key key = service.generateNewKey();

        String encode = service.encrypt(src.getBytes(), key.getEncoded()).toHex();
        System.out.println("encode = " + encode);
        String decode = new String(service.decrypt(Hex.decode(encode), key.getEncoded()).getBytes());
        System.out.println("decode = " + decode);
        Assert.assertEquals(src, decode);
    }
}
