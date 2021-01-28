package com.monportailrh.utility;

import com.monportailrh.core.BaseTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Utility extends BaseTest {

    public String listAllElements(List<String> listArray) {
        return Arrays.toString(listArray.toArray());
    }


}
