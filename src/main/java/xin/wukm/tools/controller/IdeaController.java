/**
 * Create with IntelliJ IDEA
 * Project name : tools
 * Package name : xin.wukm.tools.controller
 * Author : Wukunmeng
 * User : wukm
 * Date : 17-9-30
 * Time : 下午4:38
 * ---------------------------------
 */
package xin.wukm.tools.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.jfinal.aop.Clear;
import org.apache.commons.lang.StringUtils;
import xin.wukm.tools.model.License;
import xin.wukm.tools.util.HttpUtil;

import java.util.Enumeration;
import java.util.Map;

/**
 * Create with IntelliJ IDEA
 * Project name : tools
 * Package name : xin.wukm.tools.controller
 * Author : Wukunmeng
 * User : wukm
 * Date : 17-9-30
 * Time : 下午4:38
 * ---------------------------------
 * To change this template use File | Settings | File and Code Templates.
 */
public class IdeaController extends ViewController {

    public void obtainTicket(){
        process("obtainTicket.action");
    }

    private void process(String action){
        Map<String,String[]> map = getRequest().getParameterMap();

        Enumeration<String> keys = getRequest().getHeaderNames();
        JSONObject header = new JSONObject();
        while (keys.hasMoreElements()){
            String key= keys.nextElement();
            header.put(key,getRequest().getHeader(key));
        }

        StringBuilder request = new StringBuilder("http://119.80.184.210/rpc/");
        request.append(action);
        request.append("?");
        Enumeration<String> names = getParaNames();
        JSONObject req = new JSONObject();
        while (names.hasMoreElements()){
            String name = names.nextElement();
            request.append(name.concat("=").concat(getPara(name)).concat("&"));
            req.put(name,getPara(name));
        }
        License license = new License();
        license.set("action",action);
        license.set("user_agent",getUserAgent());
        license.set("user_address",getAddress());
        license.set("user_name",req.getString("userName"));
        license.set("content",req.toJSONString());
        request.deleteCharAt(request.length() - 1);
        String body = HttpUtil.get(request.toString(),"user-center.demo.vmovier.cc");
        if(StringUtils.isBlank(body)) {
            Map<String, Object> m = Maps.newHashMap();
            m.put("success", false);
            license.set("license",JSONObject.toJSONString(m));
            renderJson(m);
        } else {
            license.set("license",body);
            renderText(body);
        }
        license.save();
    }

    public void releaseTicket(){
        process("releaseTicket.action");
    }

    public void prolongTicket(){
        process("prolongTicket.action");
    }
}
