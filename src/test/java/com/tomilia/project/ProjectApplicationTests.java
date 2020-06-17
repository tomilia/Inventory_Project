package com.tomilia.project;

import com.tomilia.project.api.MainFrameCSVUploadController;
import com.tomilia.project.api.ProductController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ProjectApplicationTests {

	@Autowired
	private MainFrameCSVUploadController main_controller;
	@Autowired
	private ProductController inventory_controller;

	@Test
	public void contexLoads() throws Exception {
		assertThat(main_controller).isNotNull();
		assertThat(inventory_controller).isNotNull();
	}

}
