package com.project.ayqcby.tools;

import java.util.UUID;

public class StringUtils {
    public static String getUUid(){
        return UUID.randomUUID().toString().replaceAll("-","").toLowerCase();
    }
}
