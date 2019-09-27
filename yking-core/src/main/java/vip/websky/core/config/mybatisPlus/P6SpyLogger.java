package vip.websky.core.config.mybatisPlus;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.p6spy.engine.spy.appender.MessageFormattingStrategy;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author yang2048@qq.com @Y.Yang
 * @copyright 赞赞星球（上海）电子商务有限公司
 * @since 2019/9/27 15:41
 */
public class P6SpyLogger implements MessageFormattingStrategy {
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");

    public String formatMessage(int connectionId, String now,
                                long elapsed, String category, String prepared, String sql) {
        return StringUtils.isNotEmpty(sql) ? "==> Consume Time：" + elapsed + " ms  | " + now + " | "+ category + " | connection " + connectionId
                + "\n " + sql.replaceAll("[\\s]+", " ") : null;
    }
}
