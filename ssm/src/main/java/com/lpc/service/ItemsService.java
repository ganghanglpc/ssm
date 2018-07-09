package com.lpc.service;

import com.lpc.po.ItemsCustom;
import com.lpc.po.ItemsQueryVo;

import java.util.List;

public interface ItemsService {
   List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception;
   ItemsCustom findItemsById(int id) throws Exception;
   void updateItems(Integer id,ItemsCustom itemsCustom) throws Exception;
}
