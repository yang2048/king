package vip.websky.core.config.mybatisPlus;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @Copyright yang2048@qq.com @沐之永
 * @Company
 * @Author: YangYong
 * @Date: 2018/8/5 12:11
 * @Description:
 */
@Configuration
public class MybatisPlusConfig {

    /**
     * mybatisPlus-plus SQL执行效率插件【生产环境可以关闭】
     */
    @Bean
    @Profile({"dev","test"})// 设置 dev test 环境开启
    public PerformanceInterceptor performanceInterceptor() {
        PerformanceInterceptor performance = new PerformanceInterceptor();
        //performance.setFormat(true);
        performance.setMaxTime(1000);
        //performance.setWriteInLog(true);
        return performance;
    }

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

}
