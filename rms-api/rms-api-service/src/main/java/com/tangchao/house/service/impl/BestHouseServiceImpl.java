package com.tangchao.house.service.impl;

import com.tangchao.api.mapper.BestHouseMapper;
import com.tangchao.house.model.vo.BestHouseVO;
import com.tangchao.house.service.BestHouseService;
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
        return bestHouseMapper.queryBestHouse();
    }

    @Override
    public BestHouseVO queryBestHouseById(String id) {
        return null;
    }
}
