package com.yundao.tenant.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;


/**
 * 丝袜哥启动入口
 *
 * @author jan
 * @create 2017-06-21 AM8:45
 **/
@Configuration
@EnableSwagger2
public class Swagger2 {

    @Bean
    public Docket createRestApi() {
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        tokenPar.name("X-Request-Certificate")
                .description("请求头参数：ticket")
                .modelRef(new ModelRef("String"))
                .defaultValue("")
                .parameterType("header")
                .required(true).build();
        pars.add(tokenPar.build());
        
        tokenPar.name("X-Request-TenantCode")
        .description("请求头参数：tenantCode")
        .modelRef(new ModelRef("String"))
        .defaultValue("zcm")
        .parameterType("header")
        .required(true).build();
        pars.add(tokenPar.build());
        
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.yundao.tenant.app.controller"))
                .paths(PathSelectors.any())
                .build().globalOperationParameters(pars);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("租户 app- 前置系统")
                .description("")
                .termsOfServiceUrl("http://www.yundao.com")
                .contact("云道")
                .version("1.0")
                .build();
    }

}
