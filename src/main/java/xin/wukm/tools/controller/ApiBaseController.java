/**
 * Create with IntelliJ IDEA
 * Project name : common
 * Package name : xin.wukm.common.controller
 * Author : Wukunmeng
 * User : wukm
 * Date : 17-7-6
 * Time : 下午8:44
 * ---------------------------------
 */
package xin.wukm.tools.controller;

import com.google.common.collect.Maps;
import com.jfinal.core.Controller;
import com.jfinal.kit.HttpKit;
import org.apache.log4j.Logger;

import java.util.Map;

/**
 * Create with IntelliJ IDEA
 * Project name : common
 * Package name : xin.wukm.common.controller
 * Author : Wukunmeng
 * User : wukm
 * Date : 17-7-6
 * Time : 下午8:44
 * ---------------------------------
 * To change this template use File | Settings | File and Code Templates.
 */
public abstract class ApiBaseController extends Controller {

    protected Logger logger = Logger.getLogger(this.getClass());

    public void index(){
        renderJson(ok());
    }

    public Map<String,Object> ok(Object data){
        Map<String,Object> map = Maps.newHashMap();
        map.put("success", true);
        map.put("code", "success");
        map.put("message", "成功");
        if(data != null){
            map.put("data", data);
        }
        return map;
    }

    public Map<String,Object> ok(){
        return ok(null);
    }

    public Map<String,Object> fail(String code, String message, Object data){
        Map<String,Object> map = Maps.newHashMap();
        map.put("success", false);
        map.put("code", code);
        map.put("message", message);
        if(data != null){
            map.put("data",data);
        }
        return map;
    }

    public Map<String,Object> fail(String code, String message){
        return fail(code,message,null);
    }

    protected String body(){
        String body = HttpKit.readData(getRequest());
        logger.info("request-body:" + body);
        return body;
    }
}
