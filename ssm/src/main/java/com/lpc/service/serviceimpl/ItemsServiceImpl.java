package com.lpc.service.serviceimpl;


import com.lpc.mapper.ItemsMapper;
import com.lpc.mapper.ItemsMapperCustom;
import com.lpc.po.Items;
import com.lpc.po.ItemsCustom;
import com.lpc.po.ItemsQueryVo;
import com.lpc.service.ItemsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ItemsServiceImpl implements ItemsService {

    @Autowired
    private ItemsMapperCustom itemsMapperCustom;
    @Autowired
    ItemsMapper itemsMapper;

    @Override
    public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception {
        return itemsMapperCustom.findItemsList(itemsQueryVo);
    }

    @Override
    public ItemsCustom findItemsById(int id) throws Exception {
        Items items = itemsMapper.selectByPrimaryKey(id);
        ItemsCustom itemsCustom = new ItemsCustom();
        BeanUtils.copyProperties(items,itemsCustom);
        return itemsCustom;
    }

    @Override
    public void updateItems(Integer id, ItemsCustom itemsCustom) throws Exception {

        /*
        对于关键业务的处理 以及对ID为空的校验
        空指针异常一定要杜绝！
         */
        itemsMapper.updateByPrimaryKeyWithBLOBs(itemsCustom);
    }
}
