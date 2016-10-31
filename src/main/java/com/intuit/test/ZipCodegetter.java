package com.intuit.test;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.AddressComponent;
import com.google.maps.model.AddressComponentType;
import com.google.maps.model.GeocodingResult;
import com.intuit.test.ProfileProtos.StreetAddressPayload;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class ZipCodegetter {

    static GeoApiContext context;
    final static Logger logger = Logger.getLogger(ZipCodegetter.class.getName());

    static {
        context = new GeoApiContext().setApiKey("AIzaSyCNzWTTHgm6BfOnoz8iPiKclssSHFhSMT0");
    }

    public static List<StreetAddressPayload> padWithZipCodes(final List<StreetAddressPayload> payloadList) {

        return payloadList.stream().map(streetAddressPayload -> {
            try {
                GeocodingResult[] results = GeocodingApi.geocode(context,
                                                                 streetAddressPayload.getAddress())
                    .await();
                for (AddressComponent addressComponent : results[0].addressComponents) {
                    for (AddressComponentType addressComponentType : addressComponent.types) {
                        if (addressComponentType.toString().equalsIgnoreCase("postal_code")) {
                            return streetAddressPayload.toBuilder()
                                .setZipcode(Integer.parseInt(addressComponent.longName)).build();
                        }
                    }
                }
            } catch (Exception e) {
                logger.log(Level.SEVERE, String.format("Could not figure zipCode for streetAddress=%s error=%s",
                                                       streetAddressPayload, e.getMessage()));
            }
            return streetAddressPayload.toBuilder().setZipcode(0000).build();
        }).collect(Collectors.toList());
    }
}
