package vip.websky.core.aspect;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

/**
 * 操作日志切面
 */
@Slf4j
@Aspect
@Component
public class WebLogAspect {
    /**
     * 统计时间
     */
    private TimeInterval timer = DateUtil.timer();

    @Pointcut("execution(public * vip.websky..*.action..*.*(..))")
    public void webLog() {
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {
        timer.start();
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        //打印请求参数
        Map<String, String[]> paramMap = request.getParameterMap();
        if (paramMap != null && paramMap.size() > 0) {
            StringBuffer paramSbf = new StringBuffer();
            for (String mapKey : paramMap.keySet()) {
                String[] mapValue = paramMap.get(mapKey);
                //添加判断
                if (mapValue != null && mapValue.length > 0) {
                    for (String paramStr : mapValue) {
                        if (StrUtil.isNotBlank(paramStr)) {
                            paramSbf.append("" + mapKey + "=");
                            paramSbf.append(paramStr);
                            paramSbf.append(";");
                        }
                    }
                }
            }
            log.info("-->request请求参数 : \t" + paramSbf);
        }
        log.info("-->request请求URL : \t" + request.getRequestURL().toString());
        log.info("-->request请求方法 : \t" + request.getMethod());
        log.info("-->request请求IP :  \t" + getIpAddress(request));
        log.info("-->request请求地区 :  \t" + GetAddressByIp(getIpAddress(request)));
        //获取浏览器信息
        UserAgent ua = UserAgentUtil.parse(request.getHeader("User-Agent"));
        log.info("-->request操作系统 :  \t" + ua.getOs() + " 浏览器：" + ua.getBrowser());
        log.info("-->request请求类方法 : \t" + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
//        log.info( "-->request请求 : 	" + Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) {
        // 处理完请求，返回内容
        log.info("-->response请求响应结果RESULT: " + ret);
        log.info("-->response请求响应时间= 【" + timer.intervalRestart() + "】毫秒");
    }

    /**
     * 获取用户真实IP地址
     *
     * @param request
     * @return
     */
    private static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    public static String GetAddressByIp(String IP) {
        String resout = "";
        try {
            String str = getJsonContent("http://ip.taobao.com/service/getIpInfo.php?ip=" + IP);
            JSONObject obj = JSONObject.parseObject(str);
            JSONObject obj2 = (JSONObject) obj.get("data");
            int code = (Integer) obj.get("code");
            if (code == 0) {
                if (obj2.get("region").equals(obj2.get("city"))) {
                    resout = obj2.get("country") + "" + obj2.get("region") + " - " + obj2.get("isp");
                } else {
                    resout = obj2.get("country") + "" + obj2.get("region") + "" + obj2.get("city") + " - " + obj2.get("isp");
                }
            } else {
                resout = "IP地址有误";
            }
        } catch (Exception e) {

            e.printStackTrace();
            resout = "获取IP地址异常：" + e.getMessage();
        }
        return resout;

    }

    public static String getJsonContent(String urlStr) {
        try {// 获取HttpURLConnection连接对象
            URL url = new URL(urlStr);
            HttpURLConnection httpConn = (HttpURLConnection) url
                    .openConnection();
            // 设置连接属性
            httpConn.setConnectTimeout(2000);
            httpConn.setDoInput(true);
            httpConn.setRequestMethod("GET");
            // 获取相应码
            int respCode = httpConn.getResponseCode();
            if (respCode == 200) {
                return ConvertStream2Json(httpConn.getInputStream());
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }


    private static String ConvertStream2Json(InputStream inputStream) {
        String jsonStr = "";
        // ByteArrayOutputStream相当于内存输出流
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        // 将输入流转移到内存输出流中
        try {
            while ((len = inputStream.read(buffer, 0, buffer.length)) != -1) {
                out.write(buffer, 0, len);
            }
            // 将内存流转换为字符串
            jsonStr = new String(out.toByteArray());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return jsonStr;
    }
}
