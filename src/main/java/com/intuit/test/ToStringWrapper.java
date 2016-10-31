package com.intuit.test;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.commons.lang.StringUtils;

/**
 * Created by vdharmar on 7/23/15.
 */
public class ToStringWrapper {

    protected static ObjectMapper mapper = new ObjectMapper();

    public static String getString(Object object) {
        try {
            return wrapperHelper(object);
        } catch (Exception e) {
            return StringUtils.EMPTY;
        }

    }

    static String wrapperHelper(Object object) throws Exception {
        if (object == null) {
            throw new Exception();
        }
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
    }

}


