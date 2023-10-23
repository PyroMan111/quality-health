package com.woniuxy.cxy.config;

import static org.junit.jupiter.api.Assertions.*;

import com.woniuxy.cxy.entity.Commodity;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
class InitTop5CommodityRunnerTest {
    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Test
    public void searchTest() {
        // 创建条件构造器对象
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        builder.withQuery(QueryBuilders.multiMatchQuery("功能", "name", "title", "subhead", "detail"));
        // 查询
        SearchHits<Commodity> hits = elasticsearchRestTemplate.search(builder.build(), Commodity.class);
        List<Commodity> bookList = hits.stream().map(SearchHit::getContent).collect(Collectors.toList());
        bookList.forEach(System.out::println);
    }
}

