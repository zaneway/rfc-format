package com.github.zaneway.format.rfc;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Spring Boot 上下文加载冒烟测试。
 *
 * 目的:验证所有 Bean 配置 (包括自动装配、AI 客户端、MyBatis 等)
 * 在测试环境下能够正确装配并启动,失败时立即暴露配置问题。
 */
@SpringBootTest
class RfcFormatApplicationTests {

	/**
	 * 仅依赖上下文加载成功作为断言;如果 Bean 装配出现冲突或缺失,
	 * Spring 会在测试启动阶段直接抛错。
	 */
	@Test
	void contextLoads() {
	}

}
