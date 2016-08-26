/**
 * Company:新启信息技术有限责任公司
 * Copyright: Copyright (c) 2011 
 */
package com.xqsight.wechat.bxs.service;

import java.util.List;

import com.xqsight.wechat.bxs.model.WxPromise;

/**
 * @Description: this is use for  预约
 * @author xqsight-jerry
 * @date 2016年3月25日 下午8:23:43
 */
public interface PromiseService {
	
	/**
	 * 
	  * save(这里用一句话描述这个方法的作用)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * TODO(这里描述这个方法的执行流程 – 可选)
	  * TODO(这里描述这个方法的使用方法 – 可选)
	  * TODO(这里描述这个方法的注意事项 – 可选)
	  *
	  * @Title: save
	  * @Description: TODO
	  * @param @param wxPromise    设定文件
	  * @return void    返回类型
	  * @throws
	 */
	void save(WxPromise wxPromise);
	
	/**
	 * 
	  * update(这里用一句话描述这个方法的作用)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * TODO(这里描述这个方法的执行流程 – 可选)
	  * TODO(这里描述这个方法的使用方法 – 可选)
	  * TODO(这里描述这个方法的注意事项 – 可选)
	  *
	  * @Title: update
	  * @Description: TODO
	  * @param @param wxPromise    设定文件
	  * @return void    返回类型
	  * @throws
	 */
	void update(WxPromise wxPromise);
	
	/**
	 * 
	  * updateStatus(这里用一句话描述这个方法的作用)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * TODO(这里描述这个方法的执行流程 – 可选)
	  * TODO(这里描述这个方法的使用方法 – 可选)
	  * TODO(这里描述这个方法的注意事项 – 可选)
	  *
	  * @Title: updateStatus
	  * @Description: TODO
	  * @param @param wxPromise    设定文件
	  * @return void    返回类型
	  * @throws
	 */
	void updateStatus(WxPromise wxPromise);
	
	/**
	 * 
	  * delete(这里用一句话描述这个方法的作用)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * TODO(这里描述这个方法的执行流程 – 可选)
	  * TODO(这里描述这个方法的使用方法 – 可选)
	  * TODO(这里描述这个方法的注意事项 – 可选)
	  *
	  * @Title: delete
	  * @Description: TODO
	  * @param @param promiseId    设定文件
	  * @return void    返回类型
	  * @throws
	 */
	void delete(long promiseId);
	
	/**
	 * 
	  * query(这里用一句话描述这个方法的作用)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * TODO(这里描述这个方法的执行流程 – 可选)
	  * TODO(这里描述这个方法的使用方法 – 可选)
	  * TODO(这里描述这个方法的注意事项 – 可选)
	  *
	  * @Title: query
	  * @Description: TODO
	  * @param @return    设定文件
	  * @return List<WxPromise>    返回类型
	  * @throws
	 */
	List<WxPromise> query();
	
	/**
	 * 
	  * querybyId(这里用一句话描述这个方法的作用)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * TODO(这里描述这个方法的执行流程 – 可选)
	  * TODO(这里描述这个方法的使用方法 – 可选)
	  * TODO(这里描述这个方法的注意事项 – 可选)
	  *
	  * @Title: querybyId
	  * @Description: TODO
	  * @param @param promiseId
	  * @param @return    设定文件
	  * @return WxPromise    返回类型
	  * @throws
	 */
	WxPromise querybyId(long promiseId);
	
	/**
	 *  根据微信号查询
	  * queryByUserId(这里用一句话描述这个方法的作用)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * TODO(这里描述这个方法的执行流程 – 可选)
	  * TODO(这里描述这个方法的使用方法 – 可选)
	  * TODO(这里描述这个方法的注意事项 – 可选)
	  *
	  * @Title: queryByUserId
	  * @Description: TODO
	  * @param @param wxUserCode 
	  * @param @return    设定文件
	  * @return List<WxPromise>    返回类型
	  * @throws
	 */
	List<WxPromise> queryByUserId(long wxUserId);
}
