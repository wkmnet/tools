/**
 * Create with IntelliJ IDEA
 * Project name : tools
 * Package name : xin.wukm.tools.controller
 * Author : Wukunmeng
 * User : wukm
 * Date : 17-9-30
 * Time : 下午1:28
 * ---------------------------------
 */
package xin.wukm.tools.controller;

/**
 * Create with IntelliJ IDEA
 * Project name : tools
 * Package name : xin.wukm.tools.controller
 * Author : Wukunmeng
 * User : wukm
 * Date : 17-9-30
 * Time : 下午1:28
 * ---------------------------------
 * To change this template use File | Settings | File and Code Templates.
 */
public class HomeController extends ViewController {

    public void index(){
        setAttr("title","首页");
        setAttr("home","Home");
        setAttr("content","content.html");
        renderVelocity("/index.html");
    }
}
