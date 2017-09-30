/**
 * Create with IntelliJ IDEA
 * Project name : tools
 * Package name : xin.wukm.tools.config
 * Author : Wukunmeng
 * User : wukm
 * Date : 17-9-30
 * Time : 下午1:19
 * ---------------------------------
 */
package xin.wukm.tools.config;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.config.*;
import com.jfinal.render.VelocityRender;
import com.jfinal.render.ViewType;
import com.jfinal.template.Engine;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.app.VelocityEngine;
import xin.wukm.tools.routes.AdminRoute;

import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;

/**
 * Create with IntelliJ IDEA
 * Project name : tools
 * Package name : xin.wukm.tools.config
 * Author : Wukunmeng
 * User : wukm
 * Date : 17-9-30
 * Time : 下午1:19
 * ---------------------------------
 * To change this template use File | Settings | File and Code Templates.
 */
public class ToolsConfig extends JFinalConfig {

    @Override
    public void configConstant(Constants constants) {
        constants.setDevMode(true);
        Properties vp = new Properties();
        //关闭velocity runtime log
        vp.put(VelocityEngine.RUNTIME_LOG_LOGSYSTEM_CLASS, "org.apache.velocity.runtime.log.NullLogChute");
        //vp.put(VelocityEngine.RUNTIME_LOG_LOGSYSTEM_CLASS, "org.apache.velocity.runtime.log.Log4JLogChute");
        VelocityRender.setProperties(vp);
        //自定义renderFactory
        //RenderManager.me().setRenderFactory(new MyRenderFactory());
        //ActionReporter.setWriter(new LoggerWriter());
        constants.setViewType(ViewType.VELOCITY);
        if(StringUtils.isBlank(System.getenv("OPENSHIFT_DATA_DIR"))) {
            constants.setBaseUploadPath("/tmp/upload");
        } else {
            constants.setBaseUploadPath(System.getenv("OPENSHIFT_DATA_DIR").concat("/upload"));
        }

        constants.setMaxPostSize(1024 * 1024 * 1024);
        Properties properties = System.getProperties();
        JSONObject property = new JSONObject();
        Enumeration names = properties.propertyNames();
        while (names.hasMoreElements()){
            Object key = names.nextElement();
            property.put(key.toString(),properties.getProperty(key.toString()));
        }
        //logger.info("system.property : " + property.toJSONString());

        Map map = System.getenv();

        JSONObject env = new JSONObject();
        env.putAll(map);
        if(env.get("OPENSHIFT_DATA_DIR") != null){
            constants.setBaseUploadPath(env.get("OPENSHIFT_DATA_DIR").toString().concat("upload"));
        }
        //logger.info("system.env : " + env.toJSONString());
    }

    @Override
    public void configRoute(Routes me) {
        me.add(new AdminRoute());
    }

    @Override
    public void configEngine(Engine me) {

    }

    @Override
    public void configPlugin(Plugins me) {

    }

    @Override
    public void configInterceptor(Interceptors me) {

    }

    @Override
    public void configHandler(Handlers me) {

    }
}
