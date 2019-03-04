package com.zhijin.elasticsearch;

import com.zhijin.entity.Teacher;
import io.searchbox.client.JestClient;
import io.searchbox.core.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ElasticsearchApplicationTests {

	@Autowired
	private JestClient jestClient;

	@Test
	public void contextLoads() {
		Teacher teacher = new Teacher();
		teacher.setId(3);
		teacher.setAge(18);
		teacher.setName("cyf");
		teacher.setCompany("知金教育3更新");
		teacher.setRemark("神魔恋3");
		Index build = new Index.Builder(teacher).index("test01").type("teacher").id("3").build();
		try {
			DocumentResult execute = jestClient.execute(build);
			System.out.println(execute);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test01(){
		String json = "{\n" +
				" \"query\" : {\n" +
				"  \"match\" : {\n" +
				"   \"company\" : \"知金教育技术\"\n" +
				"  }\n" +
				" }\n" +
				"}";
		Search search = new Search.Builder(json).addIndex("test01").addType("teacher").build();
		try {
			SearchResult execute = jestClient.execute(search);
			List<SearchResult.Hit<Teacher, Void>> hits = execute.getHits(Teacher.class);
			for (SearchResult.Hit<Teacher, Void> hit : hits) {
				Teacher teacher = hit.source;
				System.out.println(hit.source);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testDel(){
		Delete delete = new Delete.Builder("").index("test01").type("teacher").id("3").build();
		try {
			DocumentResult execute = jestClient.execute(delete);
			System.out.println(execute.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}

