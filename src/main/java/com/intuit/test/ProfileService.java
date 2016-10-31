package com.intuit.test;

import com.intuit.test.ProfileProtos.StreetAddressPayload;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class ProfileService {

    final static Logger logger = Logger.getLogger(ProfileService.class.getName());

    public List<StreetAddressPayload> addToMemory(final List<StreetAddressPayload> streetAddressPayloads) {
        // query and fill zipCodes
        List<StreetAddressPayload> streetAddressPayloadList = ZipCodegetter.padWithZipCodes(streetAddressPayloads);
        streetAddressPayloadList.stream()
            .forEach(RedisUtil::writeToMemoryByZip);
        return streetAddressPayloadList;
    }

    public List<StreetAddressPayload> getAllUsersByZip(final String zipCode) {
        return RedisUtil.readFromMemoryByZip(zipCode);
    }

}
