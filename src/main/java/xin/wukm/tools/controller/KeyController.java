/**
 * Create with IntelliJ IDEA
 * Project name : key
 * Package name : xin.wukm.common.controller
 * Author : Wukunmeng
 * User : wukm
 * Date : 17-8-10
 * Time : 上午10:21
 * ---------------------------------
 */
package xin.wukm.tools.controller;

import com.jfinal.aop.Before;
import com.jfinal.ext.interceptor.Restful;
import com.jfinal.plugin.activerecord.Page;
import xin.wukm.tools.model.License;

/**
 * Create with IntelliJ IDEA
 * Project name : key
 * Package name : xin.wukm.common.controller
 * Author : Wukunmeng
 * User : wukm
 * Date : 17-8-10
 * Time : 上午10:21
 * ---------------------------------
 * To change this template use File | Settings | File and Code Templates.
 */
@Before(Restful.class)
public class KeyController extends ApiBaseController {

    public void index(){
        int p = getParaToInt("page",1);
        Page<License> license = License.LICENSE.paginate(p, 10, "select *","from idea order by id desc");
        renderJson(ok(license));
    }

}
