/**
 * Company:新启信息技术有限责任公司
 * Copyright: Copyright (c) 2011 
 */
package com.xqsight.wechat.bxs.service;

import java.util.List;

import com.xqsight.wechat.bxs.model.WxUserInfo;


/**
  * @Description: this is use for 
  * @author xqsight-jerry
  * @date 2016年3月17日 下午8:03:34
  */

public interface WxUserInfoService {
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
	  * @param @param wxUserInfo    设定文件
	  * @return void    返回类型
	  * @throws
	 */
	void save(WxUserInfo wxUserInfo);
	
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
	  * @param @param wxUserInfo    设定文件
	  * @return void    返回类型
	  * @throws
	 */
	void update(WxUserInfo wxUserInfo);
	
	/**
	 * 
	  * updateBase(这里用一句话描述这个方法的作用)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * TODO(这里描述这个方法的执行流程 – 可选)
	  * TODO(这里描述这个方法的使用方法 – 可选)
	  * TODO(这里描述这个方法的注意事项 – 可选)
	  *
	  * @Title: updateBase
	  * @Description: TODO
	  * @param @param wxUserInfo    设定文件
	  * @return void    返回类型
	  * @throws
	 */
	void updateBase(WxUserInfo wxUserInfo);
	
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
	  * @param @param wxUserInfo    设定文件
	  * @return void    返回类型
	  * @throws
	 */
	void delete(WxUserInfo wxUserInfo);
	
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
	  * @return List<WxUserInfo>    返回类型
	  * @throws
	 */
	List<WxUserInfo> query();
	
	/**
	 * 
	  * queryByWxUserCode(这里用一句话描述这个方法的作用)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * TODO(这里描述这个方法的执行流程 – 可选)
	  * TODO(这里描述这个方法的使用方法 – 可选)
	  * TODO(这里描述这个方法的注意事项 – 可选)
	  *
	  * @Title: queryByWxUserCode
	  * @Description: TODO
	  * @param @param wxUserCode
	  * @param @return    设定文件
	  * @return List<WxUserInfo>    返回类型
	  * @throws
	 */
	WxUserInfo queryByWxUserCode(String wxUserCode);
	
	/**
	 * 
	  * queryByWxId(这里用一句话描述这个方法的作用)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * TODO(这里描述这个方法的执行流程 – 可选)
	  * TODO(这里描述这个方法的使用方法 – 可选)
	  * TODO(这里描述这个方法的注意事项 – 可选)
	  *
	  * @Title: queryByWxId
	  * @Description: TODO
	  * @param @param wxId
	  * @param @return    设定文件
	  * @return List<WxUserInfo>    返回类型
	  * @throws
	 */
	List<WxUserInfo> queryByWxId(long wxId);
}
