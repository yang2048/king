package vip.websky.core.config.swagger;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@EnableSwaggerBootstrapUI
public class SwaggerConfiguration {

    /**
     * 订餐
     */
    @Bean
    public Docket createRicebarApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("饭吧")
                .host("http://127.0.0.1:8080/ricebar")
                //.pathMapping("V1")
                .select()
                .apis(RequestHandlerSelectors.basePackage("vip.websky.ricebar"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 后台管理
     */
    @Bean
    public Docket createAdminApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("后台管理")
                .select()
                .apis(RequestHandlerSelectors.basePackage("vip.websky.admin"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("api文档")
                .description("简单优雅的restfun风格")
                .termsOfServiceUrl("http://localhost:8080/")
                .license("暂未开源")
                .contact("yang2048@qq.com")
                .version("1.0")
                .build();
    }

}
