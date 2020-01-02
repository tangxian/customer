/**
 * Copyright 2018-2020 stylefeng & fengshuonan (https://gitee.com/stylefeng)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.boot.modular.system.controller;

import com.boot.core.common.annotion.BussinessLog;
import com.boot.core.common.annotion.Permission;
import com.boot.core.common.constant.Const;
import com.boot.core.common.constant.dictmap.DictMap;
import com.boot.core.common.constant.factory.ConstantFactory;
import com.boot.core.common.exception.BizExceptionEnum;
import com.boot.core.kernel_model.exception.ServiceException;
import com.boot.core.log.LogObjectHolder;
import com.boot.modular.system.model.Dict;
import com.boot.modular.system.service.IDeptService;
import com.boot.modular.system.service.IDictService;
import com.boot.modular.system.warpper.DictWarpper;
import com.boot.core.kernel_core.base.controller.BaseController;
import com.boot.core.kernel_core.util.ToolUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 字典控制器
 *
 * @author fengshuonan
 * @Date 2017年4月26日 12:55:31
 */
@Controller
@RequestMapping("/dict")
public class DictController extends BaseController {

    private String PREFIX = "/system/dict/";

    @Autowired
    private IDictService dictService;
    @Autowired
    private IDeptService deptService;

    /**
     * 跳转到字典管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "dict.html";
    }

    /**
     * 跳转到添加字典
     */
    @RequestMapping("/dict_add")
    public String deptAdd() {
        return PREFIX + "dict_add.html";
    }

    /**
     * 跳转到修改字典
     */
    @Permission({Const.ADMIN_NAME,Const.GENERALADMIN_NAME})
    @RequestMapping("/dict_edit/{dictId}")
    public String deptUpdate(@PathVariable Integer dictId, Model model) {
        Dict dict = dictService.selectById(dictId);
        model.addAttribute("dict", dict);
        List<Dict> subDicts = dictService.selectList(new EntityWrapper<Dict>().eq("pid", dictId));
        model.addAttribute("subDicts", subDicts);
        LogObjectHolder.me().set(dict);
        return PREFIX + "dict_edit.html";
    }

    /**
     * 新增字典
     *
     * @param dictValues 格式例如   "1:启用;2:禁用;3:冻结"
     */
    @BussinessLog(value = "添加字典记录", key = "dictName,dictValues", dict = DictMap.class)
    @RequestMapping(value = "/add")
    @Permission({Const.ADMIN_NAME,Const.GENERALADMIN_NAME})
    @ResponseBody
    public Object add(String dictCode, String dictTips, String dictName, String dictValues) {
        if (ToolUtil.isOneEmpty(dictCode, dictName, dictValues)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        this.dictService.addDict(dictCode, dictName, dictTips, dictValues);
        return SUCCESS_TIP;
    }

    /**
     * 获取所有字典列表
     */
    @RequestMapping(value = "/list")
    @Permission({Const.ADMIN_NAME,Const.GENERALADMIN_NAME})
    @ResponseBody
    public Object list(String condition) {
        List<Map<String, Object>> list = this.dictService.list(condition);
        return super.warpObject(new DictWarpper(list));
    }
    
    /**
     * 根据父类编码获取词典列表
     */
    @RequestMapping(value = "/selectbyparentcodelist")
    @ResponseBody
    public Object selectbyparentcodelist(String code) {
        List<Dict> list =  (List<Dict>)ConstantFactory.me().getDictsByParentCode(code);
        return  JSON.toJSON(list);
    }

    /**
     * 根据父类编码获取词典列表通过缓存
     */
    @RequestMapping(value = "/selectbyparentcodelistByCache")
    @ResponseBody
    public Object selectbyparentcodelistByCache(String code) {
        List<Dict> list =  (List<Dict>)ConstantFactory.me().getDictsByParentCode(code);
        return  JSON.toJSON(list);
    }

    /**
     * 字典详情
     */
    @RequestMapping(value = "/detail/{dictId}")
    @Permission({Const.ADMIN_NAME,Const.GENERALADMIN_NAME})
    @ResponseBody
    public Object detail(@PathVariable("dictId") Integer dictId) {
        return dictService.selectById(dictId);
    }

    /**
     * 修改字典
     */
    @BussinessLog(value = "修改字典", key = "dictName,dictValues", dict = DictMap.class)
    @RequestMapping(value = "/update")
    @Permission({Const.ADMIN_NAME,Const.GENERALADMIN_NAME})
    @ResponseBody
    public Object update(Integer dictId, String dictCode, String dictName, String dictTips, String dictValues) {
        if (ToolUtil.isOneEmpty(dictId, dictCode, dictName, dictValues)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        dictService.editDict(dictId, dictCode, dictName, dictTips, dictValues);
        return SUCCESS_TIP;
    }

    /**
     * 删除字典记录
     */
    @BussinessLog(value = "删除字典记录", key = "dictId", dict = DictMap.class)
    @RequestMapping(value = "/delete")
    @Permission({Const.ADMIN_NAME,Const.GENERALADMIN_NAME})
    @ResponseBody
    public Object delete(@RequestParam Integer dictId) {

        //缓存被删除的名称
        LogObjectHolder.me().set(ConstantFactory.me().getDictName(dictId));

        this.dictService.delteDict(dictId);
        return SUCCESS_TIP;
    }

    /*
    * 测试方法 查询词典并存入到缓存
    * */
    @RequestMapping("/set/{name}/{val}")
    @ResponseBody
    public String set(@PathVariable("name") String name,@PathVariable("val") Integer val){
        return ConstantFactory.me().getDictsByName(name,val);
    }
    /*
     * 测试方法 根据父类编码获取词典列表存入到缓存
     * */
    @RequestMapping("/setByCode/{code}")
    @ResponseBody
    public Object setByCode(@PathVariable("code") String code){
        return ConstantFactory.me().getDictsByParentCode(code);
    }

    /**
     * 获取迪瑞云默认的分成比例
     */
    @RequestMapping("/getdrcloudDivided")
    @ResponseBody
    public Object getdrcloudDivided(String code,String name){
        return dictService.getDrcCloudDivided(code,name);
    }
}
