package com.tangchao.api.controller;

import com.tangchao.house.model.vo.BestHouseVO;
import com.tangchao.house.service.BestHouseService;
import com.xqsight.common.model.BaseResult;
import com.xqsight.security.annontation.AuthIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author wangganggang
 * @date 2017年07月21日 15:34
 */
@RestController
@RequestMapping("/api/house")
public class BestHouseController {

    @Autowired
    private BestHouseService bestHouseService;

    @AuthIgnore
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public Object queryBestHouse(){
        List<BestHouseVO> bestHouses = bestHouseService.queryBestHouse();
        return new BaseResult(bestHouses);
    }

    @AuthIgnore
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Object queryBestHouse(@PathVariable String id,Integer rentType){
        BestHouseVO bestHouses = bestHouseService.queryBestHouseById(id,rentType);
        return new BaseResult(bestHouses);
    }
}
