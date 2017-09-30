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

    public void index(){
        Map<String,Object> ok = Maps.newHashMap();
        ok.put("success",true);
        ok.put("time",System.currentTimeMillis());
        renderJson(ok);
    }
}
