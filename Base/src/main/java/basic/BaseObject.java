package basic;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 基类，提供一个标准gson工具和logger工具
 *
 * @author zhangqian
 */
public abstract class BaseObject {

    /**
     * 日志
     */
    protected static Logger logger = LoggerFactory.getLogger(new Object() {
        Class getClazz() {
            return this.getClass();
        }
    }.getClazz());


    /**
     * gson
     */
    protected static Gson gson = new GsonBuilder().disableHtmlEscaping().serializeNulls().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

}
