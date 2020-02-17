package com.dfsoft.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dfsoft.mapper.TransformerMapper;

@Service
public class TransformerServiceImpl implements TransformerService{

	@Autowired
	private TransformerMapper transformerMapper;
	
	@Override
	public List<Map<String, Object>> findTransformer() {
		// TODO Auto-generated method stub
		return transformerMapper.findTransformer();
	}

	@Override
	public Map<String, Object> findTransformerById(String id) {
		// TODO Auto-generated method stub
		return transformerMapper.findTransformerById(id);
	}

	@Override
	public boolean addTransformer(Map<String, Object> entity) {
		// TODO Auto-generated method stub
		return transformerMapper.addTransformer(entity);
	}

	@Override
	public boolean updateTransformer(Map<String, Object> entity) {
		// TODO Auto-generated method stub
		return transformerMapper.updateTransformer(entity);
	}

	@Override
	public boolean delTransformer(String id) {
		// TODO Auto-generated method stub
		return transformerMapper.delTransformer(id);
	}

}
