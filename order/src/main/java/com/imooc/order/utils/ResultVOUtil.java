package com.imooc.order.utils;

import com.imooc.order.VO.ResultVO;

public class ResultVOUtil {

    public static ResultVO success(Object object){
        ResultVO resultVO = new ResultVO<>();
        resultVO.setCode(0);
        resultVO.setMsg("SUCCESS");
        resultVO.setData(object);
        return resultVO;
    }
}
