/**
 * Create with IntelliJ IDEA
 * Project name : tools
 * Package name : xin.wukm.tools.controller
 * Author : Wukunmeng
 * User : wukm
 * Date : 17-9-30
 * Time : 下午1:29
 * ---------------------------------
 */
package xin.wukm.tools.controller;

import com.google.common.collect.Maps;
import com.jfinal.core.Controller;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.util.Map;

/**
 * Create with IntelliJ IDEA
 * Project name : tools
 * Package name : xin.wukm.tools.controller
 * Author : Wukunmeng
 * User : wukm
 * Date : 17-9-30
 * Time : 下午1:29
 * ---------------------------------
 * To change this template use File | Settings | File and Code Templates.
 */
public abstract class ViewController extends Controller {

    protected Logger logger = Logger.getLogger(this.getClass());

    public void index(){
        Map<String,Object> ok = Maps.newHashMap();
        ok.put("success",true);
        ok.put("time",System.currentTimeMillis());
        renderJson(ok);
    }

    protected String getAddress(){
        String address = getHeader("x-forwarded-for");
        if(StringUtils.isBlank(address)){
            address = getRequest().getRemoteAddr();
        }
        return address;
    }

    protected String getUserAgent(){
        String address = getHeader("user-agent");
        return address;
    }
}
