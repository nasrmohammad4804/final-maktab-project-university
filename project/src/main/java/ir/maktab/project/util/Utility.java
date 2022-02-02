package ir.maktab.project.util;

import javax.servlet.http.HttpServletRequest;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.IntStream;

public class Utility {
    public static String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }
    public static String generateVerification(){
         Random random=new Random();
        AtomicReference<String> result= new AtomicReference<>("");
        IntStream.range(1,6).forEach(x -> result.updateAndGet(v -> v + random.nextInt(10)));
        return result.get();
    }
    public static String getReverseSortDir(String sortDir){
       return sortDir.equals("asc") ? "desc" : "asc";
    }
}
