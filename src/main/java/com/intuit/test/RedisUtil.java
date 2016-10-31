package com.intuit.test;

import com.intuit.test.ProfileProtos.StreetAddressPayload;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import redis.clients.jedis.Jedis;

public class RedisUtil {

    static Jedis jedis = new Jedis("127.0.0.1", 7777);
    final static Logger logger = Logger.getLogger(RedisUtil.class.getName());

    public static void writeToMemoryByZip(final StreetAddressPayload streetAddressPayload) {
        jedis.rpush(String.valueOf(streetAddressPayload.getZipcode()),
                    Base64.getEncoder().encodeToString(streetAddressPayload.toByteArray()));

    }

    public static List<StreetAddressPayload> readFromMemoryByZip(final String zip) {
        List<String> values = jedis.lrange(zip, 0, -1);
        List<StreetAddressPayload> results = new ArrayList<>();
        values.stream().forEach(value -> {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(Base64.getDecoder().decode(value));
            StreetAddressPayload streetAddressPayload1 = null;
            try {
                streetAddressPayload1 = StreetAddressPayload.PARSER.parseFrom(byteArrayInputStream);
                results.add(streetAddressPayload1);
            } catch (IOException e) {

                logger.log(Level.SEVERE, String.format("Could not parse object", e.getMessage()));
            }
        });
        return results;
    }

}
