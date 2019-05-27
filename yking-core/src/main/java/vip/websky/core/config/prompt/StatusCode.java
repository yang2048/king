package vip.websky.core.config.prompt;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.setting.dialect.Props;

/**
 * 系统固定消息
 */
public class StatusCode {

    /**
     * 成功
     */
    public static String RTN_CODE_SUCCESS = "000000";

    public final static String UNEXPECTED_ERROR = "000001";

    public final static String TOKEN_VERIFY_ERROR = "000002";

    public final static String OBJECT_CONVERT_ERROR = "000003";

    /**
     * 未知异常代码
     */
    public static String RTN_CODE_UNKNOW_ERROR = "999999";

    /**
     * 表单验证不通过
     */
    public static String RTN_CODE_VALIDATE = "RTN_CODE_VALIDATE";

    /**
     * 参数不能空
     */
    public static String RTN_NOT_NULL = "RTN_NOT_NULL";



    /**
     * @param key
     * @return
     * @description 获取key对应的属性值
     */
    public static String getKeyValue(String key) {
        Props props = new Props(profilepath);
        //中文转码后输出
        return Convert.convertCharset(props.getStr(key), CharsetUtil.UTF_8, "UTF-8");
    }
    /**
     * 属性文件的路径
     */
    private static String profilepath = "i18n/message.properties";
}
