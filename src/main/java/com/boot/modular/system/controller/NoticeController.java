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

import com.alibaba.fastjson.JSON;
import com.boot.core.common.annotion.BussinessLog;
import com.boot.core.common.constant.dictmap.NoticeMap;
import com.boot.core.common.constant.factory.ConstantFactory;
import com.boot.core.common.exception.BizExceptionEnum;
import com.boot.core.common.exception.FileFormatErrorException;
import com.boot.core.kernel_model.exception.ServiceException;
import com.boot.core.log.LogObjectHolder;
import com.boot.core.shiro.ShiroKit;
import com.boot.core.util.ExcelUtils;
import com.boot.modular.system.model.Notice;
import com.boot.modular.system.model.NoticeInfo;
import com.boot.modular.system.service.INoticeService;
import com.boot.modular.system.warpper.NoticeWrapper;
import com.boot.core.kernel_core.base.controller.BaseController;
import com.boot.core.kernel_core.util.ToolUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 通知控制器
 *
 * @author fengshuonan
 * @Date 2017-05-09 23:02:21
 */
@Controller
@RequestMapping("/notice")
public class NoticeController extends BaseController {

    private String PREFIX = "/system/notice/";

    @Autowired
    private INoticeService noticeService;

    /**
     * 跳转到通知列表首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "notice.html";
    }

    /**
     * 跳转到添加通知
     */
    @RequestMapping("/notice_add")
    public String noticeAdd() {
        return PREFIX + "notice_add.html";
    }

    /**
     * 跳转到通知导入
     */
    @RequestMapping("/notice_import")
    public String noticeImport() {
        return PREFIX + "notice_import.html";
    }

    @PostMapping(value = "/readExcel")
    public String readExcel(@RequestParam(value="uploadFile", required = false) MultipartFile file,Model model){
        try{
            long t1 = System.currentTimeMillis();
            List<NoticeInfo> list = ExcelUtils.readExcel("", NoticeInfo.class, file);
            long t2 = System.currentTimeMillis();
            System.out.println(String.format("read over! cost:%sms", (t2 - t1)));
            for (NoticeInfo noticeInfo : list){
                System.out.println("aaa"+JSON.toJSONString(noticeInfo));
            }
            //写入数据库

            model.addAttribute("tips", "成功导入"+list.size()+"条数据,耗时"+(t2 - t1)+"ms");
        }catch (FileFormatErrorException fileException){
            model.addAttribute("tips", "文件格式不正确");
        }
        return PREFIX + "notice_import.html";
    }

    /**
     * 跳转到修改通知
     */
    @RequestMapping("/notice_update/{noticeId}")
    public String noticeUpdate(@PathVariable Integer noticeId, Model model) {
        Notice notice = this.noticeService.selectById(noticeId);
        model.addAttribute("notice", notice);
        LogObjectHolder.me().set(notice);
        return PREFIX + "notice_edit.html";
    }

    /**
     * 跳转到首页通知
     */
    @RequestMapping("/hello")
    public String hello() {
        List<Map<String, Object>> notices = noticeService.list(null);
        super.setAttr("noticeList", notices);
        return "/blackboard.html";
    }

    /**
     * 获取通知列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        List<Map<String, Object>> list = this.noticeService.list(condition);
        return super.warpObject(new NoticeWrapper(list));
    }

    /**
     * 新增通知
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    @BussinessLog(value = "新增通知", key = "title", dict = NoticeMap.class)
    public Object add(Notice notice) {
        if (ToolUtil.isOneEmpty(notice, notice.getTitle(), notice.getContent())) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        notice.setCreater(ShiroKit.getUser().getId());
        notice.setCreatetime(new Date());
        notice.insert();
        return SUCCESS_TIP;
    }

    /**
     * 删除通知
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    @BussinessLog(value = "删除通知", key = "noticeId", dict = NoticeMap.class)
    public Object delete(@RequestParam Integer noticeId) {

        //缓存通知名称
        LogObjectHolder.me().set(ConstantFactory.me().getNoticeTitle(noticeId));

        this.noticeService.deleteById(noticeId);

        return SUCCESS_TIP;
    }

    /**
     * 修改通知
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    @BussinessLog(value = "修改通知", key = "title", dict = NoticeMap.class)
    public Object update(Notice notice) {
        if (ToolUtil.isOneEmpty(notice, notice.getId(), notice.getTitle(), notice.getContent())) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        Notice old = this.noticeService.selectById(notice.getId());
        old.setTitle(notice.getTitle());
        old.setContent(notice.getContent());
        old.updateById();
        return SUCCESS_TIP;
    }

}
