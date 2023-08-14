package io.renren.common.utils;

import io.renren.modules.course.service.StudentService;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class RecordUtils {
    // 类变量（用static修饰）是属于类的
    // 在spring容器中都是实例化对象。当我们使用静态变量或静态方法时
    // 不需要new出来一个类的实例化对象
    // 所以使用@Autowired修饰一个静态变量时
    // 该静态变量并没有真正实例化成一个对象，因此该静态变量为null
    // 不能使用 @Autowired来注入静态变量。
    // 先注入再分配给静态对象
    @Autowired
    private StudentService stuService;

    private static StudentService studentService;
    //该注解被用来修饰一个非静态的void（）方法。
    // 被@PostConstruct修饰的方法会在服务器加载Servlet的时候运行，并且只会被服务器执行一次。
    @PostConstruct
    public void init(){
        //对studentService初始化
       studentService = stuService;
    }

    private static Map<Integer,String> num_map = new HashMap<Integer,String>(){{
        put(0,"listen_num");
        put(1,"interaction_num");
        put(2,"flip_num");
        put(3,"after_class_num");
        put(4,"teamwork_num");
        put(5,"political_num");
        put(6,"other_num");
        }
    };

    public static Map<Integer,String> map = new HashMap<Integer,String>(){{
        put(0,"listen");
        put(1,"interaction");
        put(2,"flip");
        put(3,"after_class");
        put(4,"teamwork");
        put(5,"political");
        put(6,"other");
    }
    };

    public static void changeRecordNum(Integer stuId, int type, boolean isAdd){

        String t = num_map.get(type);
        int add= 1, dec = -1;
        int change = isAdd? add :dec;
        System.out.println(change);
        System.out.println("xuehao "+stuId);
        System.out.println("t"+t);
        studentService.updateRecord(stuId,t,change);
    }

}
