package com.lpc.mapper;

import com.lpc.po.ItemsCustom;
import com.lpc.po.ItemsQueryVo;


import java.util.List;


/**
 * 商品自定义mapper
 */
public interface ItemsMapperCustom {
	// 商品查询列表
	List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo)
			throws Exception;

}
