package com.p6e.bounce.service;

import com.p6e.bounce.model.dto.P6eListResultDto;
import com.p6e.bounce.model.dto.P6eRoomParamDto;
import com.p6e.bounce.model.dto.P6eRoomResultDto;

/**
 * 该类为 room service 的服务接口
 * @author LiDaShuang
 * @version 1.0
 */
public interface P6eRoomService {

    /**
     * 获取已经创建的房间列表
     * @return 房间列表数据
     */
    public P6eListResultDto<P6eRoomResultDto> list();

    /**
     * 创建一个房间
     * @param param 创建的房间参数
     * @return 创建的房间数据
     */
    public P6eRoomResultDto create(P6eRoomParamDto param);

    /**
     * 删除一个房间
     * @param param 删除的房间参数
     * @return 删除的房间数据
     */
    public P6eRoomResultDto remove(P6eRoomParamDto param);

}
