package com.tangchao.service.impl;

import com.tangchao.service.BestHouseService;
import com.tangchao.mapper.BestHouseMapper;
import com.tangchao.model.vo.BestHouseVO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author wangganggang
 * @date 2017年07月21日 15:29
 */
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
