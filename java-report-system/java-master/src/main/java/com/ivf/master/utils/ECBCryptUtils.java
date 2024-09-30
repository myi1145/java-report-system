package com.ivf.master.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Random;

/**
 * <h1>ECB 加密和解密类</h1>
 * 本类使用 AES 进行文本加密和解密，使用了 ECB 加密模式。
 *
 * @author CAI
 * @since 2024-01-08
 */
public class ECBCryptUtils {

    /**
     * 加密模式之 ECB，算法/模式/补码方式
     */
    private static final String AES_ECB = "AES/ECB/PKCS5Padding";

    /**
     * 密钥 key
     */
    private static final String key = "ivfJavaWebBb1234";

    /**
     * AES 中的 IV 必须是 16 字节（128位）长
     */
    private static final Integer IV_LENGTH = 16;

    /***
     * <h2>空校验</h2>
     * 判断字符串是否为空或 null。
     * @param str 需要判断的值
     * @author CAI
     * @since 2024-01-08
     */
    public static boolean isEmpty(Object str) {
        return null == str || "".equals(str);
    }

    /***
     * <h2>String 转 byte</h2>
     * 将字符串转换为字节数组。
     * @param str 需要转换的字符串
     * @author CAI
     * @since 2024-01-08
     */
    public static byte[] getBytes(String str) {
        if (isEmpty(str)) {
            return null;
        }

        try {
            return str.getBytes(StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /***
     * <h2>初始化向量（IV）</h2>
     * 生成一个随机的初始化向量，用于增加加密和解密的安全性。
     * @return 随机生成的 IV
     * @author CAI
     * @since 2024-01-08
     */
    public static String getIV() {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < IV_LENGTH; i++) {
            int number = random.nextInt(str.length());
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    /***
     * <h2>获取一个 AES 密钥规范</h2>
     * 根据给定的密钥生成 AES 密钥规范。
     * @param key 密钥字符串
     * @return 密钥规范
     * @author CAI
     * @since 2024-01-08
     */
    public static SecretKeySpec getSecretKeySpec(String key) {
        SecretKeySpec secretKeySpec = new SecretKeySpec(getBytes(key), "AES");
        return secretKeySpec;
    }

    /**
     * <h2>加密 - 模式 ECB</h2>
     * 使用 ECB 模式对文本内容进行加密。
     *
     * @param text 需要加密的文本内容
     * @return 加密后的文本内容，使用 Base64 编码
     * @author CAI
     * @since 2024-01-08
     */
    public static String encrypt(String text) {
        if (isEmpty(text)) {
            return null;
        }

        try {
            // 创建AES加密器
            Cipher cipher = Cipher.getInstance(AES_ECB);

            SecretKeySpec secretKeySpec = getSecretKeySpec(key);

            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);

            // 加密字节数组
            byte[] encryptedBytes = cipher.doFinal(getBytes(text));

            // 将密文转换为 Base64 编码字符串
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <h2>解密 - 模式 ECB</h2>
     * 使用 ECB 模式对文本内容进行解密。
     *
     * @param text 需要解密的文本内容（Base64 编码）
     * @return 解密后的文本内容
     * @author CAI
     * @since 2024-01-08
     */
    public static String decrypt(String text) {
        if (isEmpty(text)) {
            return null;
        }

        // 将密文转换为16字节的字节数组
        byte[] textBytes = Base64.getDecoder().decode(text);

        try {
            // 创建AES加密器
            Cipher cipher = Cipher.getInstance(AES_ECB);

            SecretKeySpec secretKeySpec = getSecretKeySpec(key);

            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);

            // 解密字节数组
            byte[] decryptedBytes = cipher.doFinal(textBytes);

            // 将明文转换为字符串
            return new String(decryptedBytes, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
