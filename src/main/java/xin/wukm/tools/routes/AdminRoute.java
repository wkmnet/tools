/**
 * Create with IntelliJ IDEA
 * Project name : tools
 * Package name : xin.wukm.tools.routes
 * Author : Wukunmeng
 * User : wukm
 * Date : 17-9-30
 * Time : 下午1:24
 * ---------------------------------
 */
package xin.wukm.tools.routes;

import com.jfinal.config.Routes;
import xin.wukm.tools.controller.HomeController;
import xin.wukm.tools.controller.IdeaController;

/**
 * Create with IntelliJ IDEA
 * Project name : tools
 * Package name : xin.wukm.tools.routes
 * Author : Wukunmeng
 * User : wukm
 * Date : 17-9-30
 * Time : 下午1:24
 * ---------------------------------
 * To change this template use File | Settings | File and Code Templates.
 */
public class AdminRoute extends Routes {
    @Override
    public void config() {
        add("/", HomeController.class);
        add("/rpc",IdeaController.class);
    }
}
