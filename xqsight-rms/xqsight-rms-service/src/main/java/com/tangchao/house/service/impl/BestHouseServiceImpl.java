package com.tangchao.house.service.impl;

import com.tangchao.constans.RentTypeEnum;
import com.tangchao.house.mapper.BestHouseMapper;
import com.tangchao.house.model.vo.BestHouseVO;
import com.tangchao.house.service.BestHouseService;
import com.xqsight.common.core.orm.Criterion;
import com.xqsight.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wangganggang
 * @date 2017年07月21日 15:29
 */
@Service
public class BestHouseServiceImpl implements BestHouseService {

    @Autowired
    private BestHouseMapper bestHouseMapper;

    @Override
    public List<BestHouseVO> queryBestHouse() {
        Criterion criterion = new Criterion();
        return bestHouseMapper.queryBestHouse(criterion);
    }

    @Override
    public BestHouseVO queryBestHouseById(String id, int rentType) {
        if (rentType == RentTypeEnum.RENT_ROOM.value()) {
            return bestHouseMapper.queryBestRoomById(id);
        } else if (rentType == RentTypeEnum.RENT_HOUSE.value()) {
            return bestHouseMapper.queryBestHouseById(id);
        }
        return null;
    }
}
