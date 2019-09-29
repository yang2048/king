package vip.websky.core.config.mybatisPlus;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;

import java.util.List;

/**
 * @author yang2048@qq.com @Y.Yang
 * @since 2019/9/29 16:53
 */
public class MyLogicSqlInjector extends DefaultSqlInjector {

    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
        // 保留MP自带方法
        List<AbstractMethod> methodList = super.getMethodList(mapperClass);
//        methodList.add(new SelectById());
        return methodList;
    }
}
