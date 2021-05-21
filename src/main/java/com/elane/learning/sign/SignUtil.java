package com.elane.learning.sign;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.HmacAlgorithms;
import org.apache.commons.codec.digest.HmacUtils;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import static java.time.temporal.ChronoUnit.SECONDS;

public class SignUtil {

  //接口签名逻辑，使用同一密钥对请求参数签名，签名结果与请求携带签名一致，代表签名通过
  //防止接口非法调用，参数被篡改，接口重放，接口过期参数请求

  public static void main(String[] args) {
    System.out.println("********************************* 签名 *********************************");
//        long timestamp = LocalDateTime.now().plusMinutes(-10).toInstant(ZoneOffset.ofHours(8)).toEpochMilli();
    long timestamp = System.currentTimeMillis();
    String appKey = "testtest";
    String appSecret = "123456";

    Map<String, String> map = new HashMap<>();
    map.put("appKey", appKey);
    map.put("k1", "k1");
    map.put("k2", "k2");
    map.put("timestamp", String.valueOf(timestamp));
    String outSignData = getSignData(map);
    byte[] hmac = new HmacUtils(HmacAlgorithms.HMAC_SHA_1, appSecret).hmac(outSignData);
    String sign = new String(Base64.encodeBase64(hmac));
    map.put("sign", sign);
    System.out.println("outSign: " + sign);
    System.out.println("outSignData: " + outSignData);
    String outParams = new Gson().toJson(map);
    System.out.println("outParams: " + outParams);

    System.out.println("\n\n********************************* 验签 *********************************");
    Map<String, String> inMap = new Gson().fromJson(outParams, new TypeToken<Map<String, String>>() {
    }.getType());
    // 校验请求是否过期
    String inTimeStamp = inMap.getOrDefault("timestamp", "0");
    LocalDateTime inTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(Long.parseLong(inTimeStamp)), ZoneOffset.ofHours(8));
    Duration duration = Duration.between(inTime, LocalDateTime.now());
    long seconds = duration.get(SECONDS);
    System.out.println("seconds: " + seconds);
    if (seconds > 10 * 60) {
      System.out.println("请求超时");
      return;
    }
    String inSignData = getSignData(inMap);
    System.out.println("inSignData: " + inSignData);
    byte[] inHmac = new HmacUtils(HmacAlgorithms.HMAC_SHA_1, appSecret).hmac(inSignData);
    String sign2 = new String(Base64.encodeBase64(inHmac));
    System.out.println("sign2: " + sign2);
    System.out.println("验签结果: " + sign.equals(sign2));
  }

  public static String getSignData(Map<String, String> params) {
    StringBuilder content = new StringBuilder();
    // key 自然排序
    List<String> keys = new ArrayList<>(params.keySet());
    Collections.sort(keys);

    for (int i = 0; i < keys.size(); i++) {
      String key = keys.get(i);
      if ("sign".equals(key)) {
        continue;
      }
      String value = params.get(key);
      if (value != null) {
        content.append(i == 0 ? "" : "&").append(key).append("=").append(value);
      } else {
        content.append(i == 0 ? "" : "&").append(key).append("=");
      }
    }

    return content.toString();
  }
}
