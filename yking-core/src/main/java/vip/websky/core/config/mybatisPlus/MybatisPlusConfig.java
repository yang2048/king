package vip.websky.core.config.mybatisPlus;

import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Copyright yang2048@qq.com @沐之永
 * @Company
 * @Author: YangYong
 * @Date: 2019/8/5 12:11
 * @Description:
 */
@Configuration
public class MybatisPlusConfig {

    /**
     * mybatisPlus-plus分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    /**
     * 这里可以扩展，比如使用配置文件来配置扫描Mapper的路径
     */
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer scannerConfigurer = new MapperScannerConfigurer();
        scannerConfigurer.setBasePackage("vip.websky.**.dao");
        return scannerConfigurer;
    }

    /**
     * 自定义填充公共
     * @return
     */
    @Bean
    public MyMetaObjectHandler metaObjectHandler() {
        return new MyMetaObjectHandler();
    }

    /**
     * 乐观锁开启
     * @return
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }
}
