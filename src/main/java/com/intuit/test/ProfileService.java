package com.intuit.test;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.intuit.test.ProfileProtos.StreetAddressPayload;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class ProfileService {

    final static Logger logger = Logger.getLogger(Service.class.getName());
    static ObjectMapper OBJECT_MAPPER;

    static {
        OBJECT_MAPPER = new ObjectMapper();
        OBJECT_MAPPER.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    public List<StreetAddressPayload> addToMemory(List<StreetAddressPayload> streetAddressPayloads) {
        // query and fill zipCodes
        List<StreetAddressPayload> streetAddressPayloadList = ZipCodegetter.padWithZipCodes(streetAddressPayloads);
        streetAddressPayloadList.stream()
            .forEach(streetAddressPayload -> RedisUtil.writeToMemoryByZip(streetAddressPayload));
        return streetAddressPayloadList;
    }

    public List<StreetAddressPayload> getAllUsersByZip(final String zipCode) {
        List<StreetAddressPayload> results = RedisUtil.readFromMemoryByZip(zipCode);
        return results;
    }

}
