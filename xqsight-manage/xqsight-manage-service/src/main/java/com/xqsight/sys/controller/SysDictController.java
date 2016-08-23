/**
 * 上海汽车集团财务有限责任公司
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.sys.controller;

import com.xqsight.common.support.MessageSupport;
import com.xqsight.sso.utils.SSOUtils;
import com.xqsight.sys.model.SysDict;
import com.xqsight.sys.model.SysDictDetail;
import com.xqsight.sys.service.SysDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author wangganggang
 * @Description: TODO
 * @date 2015年12月31日 下午3:24:54
 */
@RestController
@RequestMapping("/sys/dict/")
public class SysDictController {

    @Autowired
    private SysDictService sysDictService;

    @RequestMapping("save")
    public Object saveDict(SysDict sysDict) {
        sysDict.setCreateOprId(SSOUtils.getCurrentUserId().toString());
        List<SysDict> sysDicts = sysDictService.querySysDictByDictCode(sysDict.getDictCode());
        if (sysDicts != null && sysDicts.size() > 0) {
            return MessageSupport.failureMsg("字典编号[" + sysDict.getDictCode() + "]已经存在，请修改后保存");
        } else {
            sysDictService.saveSysDict(sysDict);
            return MessageSupport.successMsg("保存成功");
        }
    }

    @RequestMapping("savedetail")
    public Object saveDictDetail(SysDictDetail sysDictDetail) {
        sysDictDetail.setCreateOprId(SSOUtils.getCurrentUserId().toString());
        sysDictService.saveSysDictDetail(sysDictDetail);
        return MessageSupport.successMsg("保存成功");
    }

    @RequestMapping("update")
    public Object updateDict(SysDict sysDict) {
        sysDict.setUpdOprId(SSOUtils.getCurrentUserId().toString());
        List<SysDictDetail> sysDictDetails = sysDictService.querySysDictDetailByDictId(sysDict.getDictId());
        boolean booUpd = true;
        if (sysDictDetails != null && sysDictDetails.size() > 0) {
            for (SysDictDetail sysDictDetail : sysDictDetails) {
                if (sysDictDetail.getEditable() == -1) {
                    booUpd = false;
                    break;
                }
            }
        }
        if (booUpd) {
            sysDictService.updateSysDict(sysDict);
            return MessageSupport.successMsg("修改成功");
        } else {
            return MessageSupport.failureMsg("该条字典是系统内置数据不可修改的，请修改其他记录");
        }
    }

    @RequestMapping("updatedetail")
    public Object updateDictDetail(SysDictDetail sysDictDetail) {
        sysDictDetail.setUpdOprId(SSOUtils.getCurrentUserId().toString());
        SysDictDetail sysDictDetails = sysDictService.querySysDictDetailByDictDetailId(sysDictDetail.getDictDetailId());
        if (sysDictDetails.getEditable() == -1) {
            return MessageSupport.failureMsg("该条明细是系统内置数据不可修改的，请修改其他记录");
        } else {
            sysDictService.updateSysDictDetail(sysDictDetail);
            return MessageSupport.successMsg("修改成功");
        }
    }

    @RequestMapping("delete")
    public Object deleteDict(int dictId) {
        List<SysDictDetail> sysDictDetails = sysDictService.querySysDictDetailByDictId(dictId);
        boolean booDel = true;
        if (sysDictDetails != null && sysDictDetails.size() > 0) {
            for (SysDictDetail sysDictDetail : sysDictDetails) {
                if (sysDictDetail.getEditable() == -1) {
                    booDel = false;
                    break;
                }
            }
        }
        if (booDel) {
            sysDictService.deleteSysDict(dictId);
            return MessageSupport.successMsg("删除成功");
        } else {
            return MessageSupport.failureMsg("该条字典是系统内置数据不可删除的，请查看其他记录");
        }
    }

    @RequestMapping("deletedetail")
    public Object deleteDictDetail(int dictDetailId) {
        SysDictDetail sysDictDetail = sysDictService.querySysDictDetailByDictDetailId(dictDetailId);
        if (sysDictDetail.getEditable() == -1) {
            return MessageSupport.failureMsg("该条字典是系统内置数据不可删除的，请查看其他记录");
        } else {
            sysDictService.deleteSysDictDetail(dictDetailId);
            return MessageSupport.successMsg("删除成功");
        }
    }

    @RequestMapping("query")
    public Object queryDictByName(SysDict sysDict) {
        List<SysDict> sysDicts = sysDictService.querySysDictByDictName(sysDict.getDictName());
        return MessageSupport.successDataMsg(sysDicts, "查询成功");
    }

    @RequestMapping("querydetail")
    public Object queryDictDetailByDictId(int dictId) {
        List<SysDictDetail> sysDictDetails = sysDictService.querySysDictDetailByDictId(dictId);
        return MessageSupport.successDataMsg(sysDictDetails, "查询成功");
    }

    @RequestMapping("querybyid")
    public Object queryDictById(int dictId) {
        SysDict sysDicts = sysDictService.querySysDictByDictId(dictId);
        return MessageSupport.successDataMsg(sysDicts, "查询成功");
    }

    @RequestMapping("querydetailbyid")
    public Object queryDictDetailById(int dictDetailId) {
        SysDictDetail sysDictDetails = sysDictService.querySysDictDetailByDictDetailId(dictDetailId);
        return MessageSupport.successDataMsg(sysDictDetails, "查询成功");
    }

}
