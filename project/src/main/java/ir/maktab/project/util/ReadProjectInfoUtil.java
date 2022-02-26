package ir.maktab.project.util;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Map;

public class ReadProjectInfoUtil {

    public static Map<String,String> readProjectInformation() throws IOException {
       RandomAccessFile file=new RandomAccessFile("info.txt","rw");


        Map<String,String> info=new HashMap<>();
        String record="";
        while ( (record=file.readLine())!=null){

            String key= record.substring(0, record.indexOf("="));
            String value=record.substring(record.indexOf("=")+1);
            info.put(key,value);
            record=  record.replace("\\w+","");
        }
        return info;
    }
}
