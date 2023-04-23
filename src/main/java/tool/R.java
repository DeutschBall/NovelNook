package tool;

import class4.spm.novelnook.pojo.Staff;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class R<T> {
    private Integer code;//1:success 0:fault

    private String msg;//错误信息

    private T data;//数据


    public static <T> R<T> success(){
        R<T> r = new R<T>();
        r.data = null;
        r.code = 1;
        return r;
    }

    public static <T> R<T> success(T object){
        R<T> r = new R<T>();
        r.data = object;
        r.code = 1;
        return r;
    }

    public static <T> R<T> error(String msg){
        R r = new R();
        r.msg = msg;
        r.code = 0;
        return r;
    }

}
