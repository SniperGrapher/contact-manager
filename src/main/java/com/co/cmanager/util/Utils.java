package com.co.cmanager.util;

import org.springframework.data.domain.Sort;

/**
 * @author by Cyprian Omenuko on 6/9/2016.
 */
public class Utils {


    public static Sort sortBy(String sort) {
        return new Sort(Sort.Direction.DESC, sort);
    }
}
