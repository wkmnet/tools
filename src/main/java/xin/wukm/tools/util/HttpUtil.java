/**
 * Create with IntelliJ IDEA
 * Project name : tools
 * Package name : xin.wukm.tools.util
 * Author : Wukunmeng
 * User : wukm
 * Date : 17-9-30
 * Time : 下午4:44
 * ---------------------------------
 */
package xin.wukm.tools.util;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.log4j.Logger;

import java.io.IOException;

/**
 * Create with IntelliJ IDEA
 * Project name : tools
 * Package name : xin.wukm.tools.util
 * Author : Wukunmeng
 * User : wukm
 * Date : 17-9-30
 * Time : 下午4:44
 * ---------------------------------
 * To change this template use File | Settings | File and Code Templates.
 */
public class HttpUtil {

    private static Logger logger = Logger.getLogger(HttpUtil.class);

    private HttpUtil(){}


    public static String get(String url, String host){
        logger.info("host:" + host);
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url).addHeader("Host",host)
                .build();
        Response response = null;
        try {
            logger.info("request:" + url);
            response = client.newCall(request).execute();
            if (!response.isSuccessful()) {
            }
            String body = response.body().string();
            logger.info("response:" + body);
            return body;
        } catch (IOException e) {
            System.out.println("IOException:" + e.getMessage());
        } finally {
            if(response != null){
                response.close();
            }
        }
        return null;
    }
}
