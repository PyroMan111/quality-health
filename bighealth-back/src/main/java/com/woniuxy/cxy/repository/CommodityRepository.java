package com.woniuxy.cxy.repository;
import com.woniuxy.cxy.entity.Commodity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface CommodityRepository extends ElasticsearchRepository<Commodity,Long>{
}
