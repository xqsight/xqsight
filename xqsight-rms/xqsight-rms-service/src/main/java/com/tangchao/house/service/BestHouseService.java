package com.tangchao.house.service;

import com.tangchao.house.model.vo.BestHouseVO;

import java.util.List;

/**
 * Created by wangganggang on 2017/7/21.
 * <p>
 * 精品房源
 */
public interface BestHouseService {

    List<BestHouseVO> queryBestHouse();

    BestHouseVO queryBestHouseById(String id,int rentType);
}
