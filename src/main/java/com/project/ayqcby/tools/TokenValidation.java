package com.project.ayqcby.tools;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class TokenValidation {
    public static String[] validation(HttpServletRequest request){
        String token = CryptoUtil.decodeTarget(request.getHeader("Authorization"));
        try {
            String yhm = token.split("&@&")[0];
            long time = Long.valueOf(token.split("&@&")[1]);
            long sjc = new Date().getTime()/1000 - time;
            int is = 0;
            if (sjc > 7200){
                is = -1;
            }
            return (yhm+","+is+","+0).split(",");
        }catch (Exception e){
            e.printStackTrace();
            return "null,null,-1".split(",");
        }
    }
}
