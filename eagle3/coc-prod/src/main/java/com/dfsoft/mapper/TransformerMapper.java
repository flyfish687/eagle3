package com.dfsoft.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface TransformerMapper {
	/**
	 * 查询所有变压器
	 * @return
	 */
	List<Map<String,Object>> findTransformer();
	/**
	 * 查询所有变压器
	 * @param id
	 * @return
	 */
	Map<String,Object> findTransformerById(String id);
	/**
	 * 新增变压器
	 * @param entity
	 * @return
	 */
	boolean addTransformer(Map<String,Object> entity);
	/**
	 * 修改变压器
	 * @param entity
	 * @return
	 */
	boolean updateTransformer(Map<String,Object> entity);
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	boolean delTransformer(String id);
}
