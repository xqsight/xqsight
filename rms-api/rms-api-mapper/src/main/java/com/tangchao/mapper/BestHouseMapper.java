package com.tangchao.mapper;

import com.tangchao.model.vo.BestHouseVO;

import java.util.List;

/**
 * Created by wangganggang on 2017/7/17.
 *
 * 精品房源
 */
public interface BestHouseMapper {

    List<BestHouseVO> queryBestHouse();
}
