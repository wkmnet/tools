/**
 * Create with IntelliJ IDEA
 * Project name : tools
 * Package name : xin.wukm.tools.boot
 * Author : Wukunmeng
 * User : wukm
 * Date : 17-9-30
 * Time : 下午1:35
 * ---------------------------------
 */
package xin.wukm.tools.boot;

import com.jfinal.kit.PathKit;
import com.jfinal.server.IServer;
import com.jfinal.server.JettyServerForIDEA;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.Properties;

/**
 * Create with IntelliJ IDEA
 * Project name : tools
 * Package name : xin.wukm.tools.boot
 * Author : Wukunmeng
 * User : wukm
 * Date : 17-9-30
 * Time : 下午1:35
 * ---------------------------------
 * To change this template use File | Settings | File and Code Templates.
 */
public class Application {
    public static final String PORT_KEY = "port";

    public static final String CONTEXT_PATH_KEY = "context.path";

    private static Logger LOG = Logger.getLogger(Application.class);

    public static void main(String[] args){
        LOG.info("start server...");
        try {
            int port = 8080;
            String contextPath = "/";
            String webApp = "/mnt/coding/idea_workspaces/openshift3/tools/src/main/webapp";
            if (StringUtils.isNotBlank(System.getProperty("web.app"))) {
                webApp = System.getProperty("web.app");
                PathKit.setWebRootPath(webApp);
            }
            if (StringUtils.isNotBlank(System.getProperty("server.dir"))) {
                File file = new File(new File(System.getProperty("server.dir")), "jetty.conf");
                if (file.exists() && file.isFile()) {
                    Properties property = new Properties();
                    property.load(FileUtils.openInputStream(file));
                    port = NumberUtils.toInt(property.getProperty(PORT_KEY),8080);
                    contextPath = property.getProperty(CONTEXT_PATH_KEY,"/");
                }
            }
            LOG.info("scan web.app : " + webApp);
            IServer server ;
            if(StringUtils.equalsIgnoreCase("true",System.getProperty("product"))){
                server = new JettyServer(webApp, port, contextPath);
            } else {
                server = new JettyServerForIDEA(webApp, port, contextPath);
            }
            LOG.info("start server listen port : " + port + ", context path : " + contextPath);
            server.start();
            LOG.info("start server successful...");
        } catch (Exception e){
            LOG.error("start server exception : ", e);
        }
        LOG.info("end server...");
    }
}
