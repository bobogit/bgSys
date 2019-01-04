package com.imooc.product.util;

import com.imooc.product.VO.ResultVO;

public class ResultVOUtil {

    /**
     * 返回成功对象
     * @param data
     * @return
     */
    public static ResultVO success(Object data){
        ResultVO vo = new ResultVO();
        vo.setCode(0);
        vo.setMsg("SUCCESS");
        vo.setData(data);
        return vo;
    }
}
