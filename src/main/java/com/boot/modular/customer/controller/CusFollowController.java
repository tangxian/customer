package com.boot.modular.customer.controller;

import com.boot.core.kernel_core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.boot.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.boot.modular.system.model.CusFollow;
import com.boot.modular.customer.service.ICusFollowService;

/**
 * 客户跟进控制器
 *
 * @author TANGXIAN
 * @Date 2020-01-03 21:47:36
 */
@Controller
@RequestMapping("/cusFollow")
public class CusFollowController extends BaseController {

    private String PREFIX = "/customer/cusFollow/";

    @Autowired
    private ICusFollowService cusFollowService;

    /**
     * 跳转到客户跟进首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "cusFollow.html";
    }

    /**
     * 跳转到添加客户跟进
     */
    @RequestMapping("/cusFollow_add")
    public String cusFollowAdd() {
        return PREFIX + "cusFollow_add.html";
    }

    /**
     * 跳转到修改客户跟进
     */
    @RequestMapping("/cusFollow_update/{cusFollowId}")
    public String cusFollowUpdate(@PathVariable Integer cusFollowId, Model model) {
        CusFollow cusFollow = cusFollowService.selectById(cusFollowId);
        model.addAttribute("item",cusFollow);
        LogObjectHolder.me().set(cusFollow);
        return PREFIX + "cusFollow_edit.html";
    }

    /**
     * 获取客户跟进列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return cusFollowService.selectList(null);
    }

    /**
     * 新增客户跟进
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(CusFollow cusFollow) {
        cusFollowService.insert(cusFollow);
        return SUCCESS_TIP;
    }

    /**
     * 删除客户跟进
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer cusFollowId) {
        cusFollowService.deleteById(cusFollowId);
        return SUCCESS_TIP;
    }

    /**
     * 修改客户跟进
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(CusFollow cusFollow) {
        cusFollowService.updateById(cusFollow);
        return SUCCESS_TIP;
    }

    /**
     * 客户跟进详情
     */
    @RequestMapping(value = "/detail/{cusFollowId}")
    @ResponseBody
    public Object detail(@PathVariable("cusFollowId") Integer cusFollowId) {
        return cusFollowService.selectById(cusFollowId);
    }
}
