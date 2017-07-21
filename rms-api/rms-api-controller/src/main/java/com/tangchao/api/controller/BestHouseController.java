package com.tangchao.api.controller;

import com.tangchao.service.BestHouseService;
import com.tangchao.model.vo.BestHouseVO;
import com.xqsight.common.model.BaseResult;
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

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public Object queryBestHouse(){
        List<BestHouseVO> bestHouses = bestHouseService.queryBestHouse();
        return new BaseResult(bestHouses);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Object queryBestHouse(@PathVariable String id){
        BestHouseVO bestHouses = bestHouseService.queryBestHouseById(id);
        return new BaseResult(bestHouses);
    }
}
