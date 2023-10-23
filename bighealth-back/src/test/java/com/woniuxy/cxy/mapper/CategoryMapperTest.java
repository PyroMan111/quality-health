package com.woniuxy.cxy.mapper;

import com.woniuxy.cxy.entity.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CategoryMapperTest {
    @Autowired
    CategoryMapper categoryMapper;

    @Test
    void queryCategoryByCondition() {

    }
    @Test
    public void testQueryCategoryByCondition() {
        // Given
        HashMap<String, Object> condition = new HashMap<>();
        condition.put("categoryName", "");
        condition.put("begin", "2015-01-01");
        condition.put("end", null);
        List<Category> expectedResult = new ArrayList<>();

        expectedResult.forEach(System.out::println);
//        expectedResult.add(new Category()); // TODO:填充实际的数据
//        // When
//        List<Category> result = categoryMapper.QueryCategoryByCondition(condition);
//        // Then
//        assertEquals(expectedResult.size(), result.size());
    }

    @Test
    void findAllCategoryName() {
    }
}