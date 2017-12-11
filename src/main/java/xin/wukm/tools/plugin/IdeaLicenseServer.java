/**
 * Create with IntelliJ IDEA
 * Project name : tools
 * Package name : xin.wukm.tools.plugin
 * Author : Wukunmeng
 * User : wukm
 * Date : 17-10-18
 * Time : 上午10:51
 * ---------------------------------
 */
package xin.wukm.tools.plugin;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.kit.PathKit;
import com.jfinal.plugin.IPlugin;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.io.*;

/**
 * Create with IntelliJ IDEA
 * Project name : tools
 * Package name : xin.wukm.tools.plugin
 * Author : Wukunmeng
 * User : wukm
 * Date : 17-10-18
 * Time : 上午10:51
 * ---------------------------------
 * To change this template use File | Settings | File and Code Templates.
 */
public class IdeaLicenseServer implements IPlugin {

    private Logger logger = Logger.getLogger(this.getClass());

    @Override
    public boolean start() {
        logger.info("start license server");
        String classPath = PathKit.getRootClassPath();
        logger.info("class path:" + classPath);
        String server = commandServer(classPath);
        logger.info("change server:" + server);
        if(StringUtils.isBlank(server)){
            logger.warn("can not execute change server:" + server);
        } else {
            command(server);
        }
        String script = commandScript(classPath);
        logger.info("change script:" + script);
        if(StringUtils.isBlank(script)){
            logger.warn("can not execute change script:" + script);
        } else {
            command(script);
        }
        String executeScript = executeScript(classPath);
        logger.info("execute script:" + executeScript);
        if(StringUtils.isBlank(executeScript)){
            logger.warn("can not execute change script:" + script);
        } else {
            command(executeScript);
        }
        return true;
    }

    private String commandServer(String path){
        File f = new File(new File(path),"IntelliJIDEALicenseServer_linux_amd64");
        if(f.exists() && f.isFile()){
           return "chmod +x ".concat(f.getAbsolutePath());
        }
        return null;
    }

    private String commandScript(String path){
        File f = new File(new File(path),"start.sh");
        if(f.exists() && f.isFile()){
            return "chmod +x ".concat(f.getAbsolutePath());
        }
        return null;
    }

    private String executeScript(String path){
        File f = new File(new File(path),"start.sh");
        if(f.exists() && f.isFile()){
            return f.getAbsolutePath();
        }
        return null;
    }


    private void close(Process process, InputStream is,
                         InputStreamReader isr, BufferedReader brStat){
        try {
            brStat.close();
            isr.close();
            is.close();
            process.destroy();
        } catch (IOException e){
            logger.error("IOException : ", e);
        }
    }

    private String command(String command){
        Process process = null;
        InputStream is = null;
        InputStreamReader isr = null;
        BufferedReader brStat = null;
        try {
            String[] cmd = {"/bin/sh", "-c", command};
            logger.info("cmd:" + JSONObject.toJSONString(cmd));
            process = Runtime.getRuntime().exec(cmd);
            process.waitFor();
            is = process.getInputStream();
            isr = new InputStreamReader(is);
            brStat = new BufferedReader(isr);
            String line = brStat.readLine();
            StringBuilder processResult = new StringBuilder();
            while (line != null){
                processResult.append(line);
                line = brStat.readLine();
            }
            logger.info("execute : " + command);
            logger.info("result : " + processResult.toString());
            logger.info("exit : " + process.exitValue());
            if(!StringUtils.isBlank(line)){
                return line.trim();
            } else {
                logger.info("line:" + line);
            }
        } catch (InterruptedException e) {
            logger.error("InterruptedException : ", e);
        } catch (IOException e) {
            logger.error("IOException : ", e);
        } finally {
            logger.info("close resources ...");
            close(process,is,isr,brStat);
        }
        return "";
    }

    @Override
    public boolean stop() {
        return true;
    }
}
