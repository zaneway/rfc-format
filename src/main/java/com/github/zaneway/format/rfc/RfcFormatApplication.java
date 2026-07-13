package com.github.zaneway.format.rfc;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;

/**
 * RFC 格式化与解析服务的主启动类。
 *
 * 职责:
 * - 作为 Spring Boot 应用的入口,负责引导 ApplicationContext 初始化
 * - 通过 {@code @SpringBootApplication} 启用自动装配与组件扫描
 *   (覆盖 com.github.zaneway.format.rfc 包及其子包)
 */
@SpringBootApplication
public class RfcFormatApplication {



	/**
	 * JVM 入口方法:委托给 Spring Boot 启动嵌入式 Servlet 容器并加载所有 Bean。
	 *
	 * @param args 命令行参数,可用于覆盖 application.yaml 中的配置
	 */
	public static void main(String[] args) {
		SpringApplication.run(RfcFormatApplication.class, args);

	}

}
