package com.monportailrh.utility.model;

import java.util.Arrays;
import java.util.List;

public class Utility {

    public String listAllElements(List<String> listArray) {
        return Arrays.toString(listArray.toArray());
    }
}
