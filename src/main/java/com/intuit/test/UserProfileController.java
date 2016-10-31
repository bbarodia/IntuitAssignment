package com.intuit.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hubspot.jackson.datatype.protobuf.ProtobufModule;
import com.intuit.test.ProfileProtos.StreetAddressPayload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController

public class UserProfileController {

    @Autowired
    ProfileService service;

    @Bean
    ProtobufHttpMessageConverter protobufHttpMessageConverter() {
        return new ProtobufHttpMessageConverter();
    }

    @Bean
    MappingJackson2HttpMessageConverter jackson2HttpMessageConverter() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new ProtobufModule());
        return new MappingJackson2HttpMessageConverter(objectMapper);
    }

    @RequestMapping(value = "/user/profiles", method = RequestMethod.POST)
    public List<StreetAddressPayload> addUSerProfile(@RequestBody List<StreetAddressPayload>
                                                             streetAddressPayloads) {
        return service.addToMemory(streetAddressPayloads);
    }

    @RequestMapping(value = "/{zipCode:.+}/user/profiles", method = RequestMethod.GET)
    public Collection<StreetAddressPayload> query(@PathVariable final String zipCode) {
        return service.getAllUsersByZip(zipCode);
    }

}
