package com.lpc.controller;

import com.lpc.po.ItemsCustom;
import com.lpc.po.ItemsQueryVo;
import com.lpc.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/items") //配置根路径
//这里的查询商品列表就是/items/findAll.action
public class ItemsController {

    @Autowired
    private ItemsService itemsService;

    @RequestMapping("/findAll")
    public ModelAndView queryItems() throws Exception {
        List<ItemsCustom> itemsCustomList = itemsService.findItemsList(null);

        ModelAndView modelAndView = new ModelAndView();

        //与request.setAttribute方法功能一样
        modelAndView.addObject("itemsList", itemsCustomList);
        //以下路径如果已在视图解析器中配置JSP的前后缀，修改为items/itemsList
        //modelAndView.setViewName("/WEB-INF/jsp/items/itemsList.jsp");
        modelAndView.setViewName("itemsList");

        return modelAndView;
    }



    @RequestMapping("/editItems")
    public String editItems(Model model, Integer id) throws Exception{
        ItemsCustom itemsCustom = itemsService.findItemsById(id);
        model.addAttribute("id",id);
        model.addAttribute("itemsCustom",itemsCustom);
        return "editItem";
    }

    //商品提交页面
    //itemsQueryVo是包装类型的pojo
    @RequestMapping("/editItemSubmit")
    public String editItemSubmit(Model model,Integer id,@Validated @ModelAttribute(value="itemsCustom") ItemsCustom itemsCustom,BindingResult bindingResult) throws Exception
    {
        //获取校验错误信息
        if(bindingResult.hasErrors()){
            // 输出错误信息
            List<ObjectError> allErrors = bindingResult.getAllErrors();

            for (ObjectError objectError :allErrors){
                // 输出错误信息
                System.out.println(objectError.getDefaultMessage());
            }
            // 将错误信息传到页面
            model.addAttribute("allErrors", allErrors);

            //可以直接使用model将提交pojo回显到页面
            model.addAttribute("items", itemsCustom);

            // 出错重新到商品修改页面
            return "editItem";
        }else {
            //进行数据回显
            model.addAttribute("id", id);
//        model.addAttribute("item",itemsCustom);


            itemsService.updateItems(id, itemsCustom);
            //请求转发
//        return "forward:queryItems.action";

            return "editItem";
            //重定向
//        return "redirect:queryItems.action";
        }
    }

    // 批量删除 商品信息
    @RequestMapping("/deleteItems")
    public String deleteItems(Integer[] items_id) throws Exception{

        return "success";
    }

    @RequestMapping("/editItemsQuery")
    public ModelAndView editItemsQuery(HttpServletRequest request,ItemsQueryVo itemsQueryVo) throws Exception {
        List<ItemsCustom> itemsCustomList = itemsService.findItemsList(null);

        ModelAndView modelAndView = new ModelAndView();

        //与request.setAttribute方法功能一样
        modelAndView.addObject("itemsList", itemsCustomList);
        //以下路径如果已在视图解析器中配置JSP的前后缀，修改为items/itemsList
        //modelAndView.setViewName("/WEB-INF/jsp/items/itemsList.jsp");
        modelAndView.setViewName("editItemsQuery");

        return modelAndView;
    }

    // 批量修改商品提交
// 通过ItemsQueryVo接收批量提交的商品信息，将商品信息存储到itemsQueryVo中itemsList属性中。
    @RequestMapping("/editItemsAllSubmit")
    public String editItemsAllSubmit(ItemsQueryVo itemsQueryVo) throws Exception {

        return "success";
    }
}
