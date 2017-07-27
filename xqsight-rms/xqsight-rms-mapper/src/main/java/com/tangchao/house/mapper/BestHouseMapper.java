package com.tangchao.house.mapper;

import com.tangchao.house.model.vo.BestHouseVO;
import com.xqsight.common.core.orm.Criterion;

import java.util.List;

/**
 * Created by wangganggang on 2017/7/17.
 *
 * 精品房源
 */
public interface BestHouseMapper {

    List<BestHouseVO> queryBestHouse(Criterion criterion);

    BestHouseVO queryBestRoomById(Long roomId);

    BestHouseVO queryBestHouseById(Long houseId);
}
