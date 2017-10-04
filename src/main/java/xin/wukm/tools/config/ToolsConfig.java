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
import com.jfinal.config.Constants;
import com.jfinal.core.ActionReporter;
import com.jfinal.ext.handler.FakeStaticHandler;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.render.VelocityRender;
import com.jfinal.render.ViewType;
import com.jfinal.template.Engine;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.velocity.app.VelocityEngine;
import xin.wukm.tools.routes.AdminRoute;
import xin.wukm.tools.tables.Tables;
import xin.wukm.tools.util.*;
import xin.wukm.tools.writer.LoggerWriter;

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

    private Logger logger = Logger.getLogger(this.getClass());

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
        ActionReporter.setWriter(new LoggerWriter());
        constants.setViewType(ViewType.VELOCITY);
        String uploadPath = Commons.getBaseUploadPath();
        if(StringUtils.isBlank(uploadPath)) {
            constants.setBaseUploadPath("/tmp/".concat(xin.wukm.tools.util.Constants.UPLOAD_PATH));
        } else {
            constants.setBaseUploadPath(uploadPath);
        }

        constants.setMaxPostSize(1024 * 1024 * 1024);
        Properties properties = System.getProperties();
        JSONObject property = new JSONObject();
        Enumeration names = properties.propertyNames();
        while (names.hasMoreElements()){
            Object key = names.nextElement();
            property.put(key.toString(),properties.getProperty(key.toString()));
        }
        logger.info("system.property : " + Commons.formatJson(properties));

        Map map = System.getenv();

        JSONObject env = new JSONObject();
        env.putAll(map);
        logger.info("system.env : " + Commons.formatJson(env));
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
        initDatabase(me);
    }

    @Override
    public void configInterceptor(Interceptors me) {

    }

    @Override
    public void configHandler(Handlers handlers) {
        handlers.add(new FakeStaticHandler(".action"));
    }

    private void initDatabase(Plugins me){
        String host = "72.30.239.148";
        String port = "3306";
        String database = "tools";
        String userName = "tools";
        String password = "tools";
        StringBuilder url = new StringBuilder("jdbc:mysql://");
        url.append(host).append(":").append(port).append("/").append(database).append("?useUnicode=true&characterEncoding=UTF-8");
        logger.info("init database:" + url.toString());
        DruidPlugin druidPlugin = new DruidPlugin(url.toString(),userName,password);
        me.add(druidPlugin);

        ActiveRecordPlugin activeRecordPlugin = new ActiveRecordPlugin(druidPlugin);
        new Tables().register(activeRecordPlugin);
        me.add(activeRecordPlugin);
    }
}
