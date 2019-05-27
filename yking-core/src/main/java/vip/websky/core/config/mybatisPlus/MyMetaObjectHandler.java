package vip.websky.core.config.mybatisPlus;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 自定义填充公共字段
 * @author Yong
 */
public class MyMetaObjectHandler implements MetaObjectHandler {

    /**
     * 字段为空自动填充,如果要使填充生效,一定在在实体类对应的字段上设置@TableField(fill = FieldFill.INSERT)字段
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        Object createTime = getFieldValByName("createdTime", metaObject);
        Object createdBy =getFieldValByName("createdBy", metaObject);
        Object deleted =getFieldValByName("deleted", metaObject);
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        System.out.println("==> 请求者的IP："+request.getRemoteAddr());
        if (createdBy == null) {
            setFieldValByName("createdBy", "admin", metaObject);
        }
        if (createTime == null) {
            setFieldValByName("createdTime", LocalDateTime.now(), metaObject);
        }
        if (deleted == null) {
            //0:正常，1:删除
            setFieldValByName("deleted", "0", metaObject);
        }

    }

    /**
     *  自动填充     @TableField(fill = FieldFill.INSERT_UPDATE)
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        setFieldValByName("updatedBy", "admin", metaObject);
        setFieldValByName("updatedTime", LocalDateTime.now(), metaObject);
    }

}