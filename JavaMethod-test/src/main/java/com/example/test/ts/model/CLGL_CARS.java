package com.example.test.ts.model;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * CLGL_CARS实体类（根据.NET原文件转换）
 */
@Data
@TableName("CLGL_CARS")
public class CLGL_CARS {
    /**
     * 主键ID
     */
    private Integer ID;

    /**
     * 车牌号
     */
    private String CARNO;

    /**
     * 车辆类型
     */
    private String CARTYPE;

    /**
     * 载重量，可为空
     */
    private Integer CARRYING;

    /**
     * 司机ID，可为空
     */
    private Integer CARS_SJID;

    /**
     * 检查日期，可为空
     */
    private String  CHECKDATE;

    /**
     * 备注
     */
    private String REMARK;

    /**
     * 颜色
     */
    private String CS;

    /**
     * 能源类型
     */
    private String NYLX;

    /**
     * 车身价格
     */
    private String CSJG;

    /**
     * 图片
     */
    private String PICTURES;

    /**
     * 车编号
     */
    private String CARBH;

    /**
     * 油号，可为空
     */
    private Integer OILNO;

    /**
     * 车型限制，可为空
     */
    private Integer CQXZ;

    /**
     * 油箱容量，可为空
     */
    private Integer YXRL;

    /**
     * 车架号
     */
    private String CJH;

    /**
     * 发动机号
     */
    private String FDJH;

    /**
     * 排放标准
     */
    private String PFBZ;

    /**
     * 购车发票金额，可为空
     */
    private Integer GCFPJE;

    /**
     * 购车发票号
     */
    private String GCFPH;

    /**
     * 注册时间，可为空
     */
    private String  ZCSJ;

    /**
     * 工作业绩
     */
    private String GZYJ;

    /**
     * 工作编号
     */
    private String GZBH;

    /**
     * 资金来源
     */
    private String ZJLY;

    /**
     * 车辆来源
     */
    private String CLLY;

    /**
     * 产权单位
     */
    private String CQDW;

    /**
     * ETC ID，可为空
     */
    private Integer ETCID;

    /**
     * 是否有效，可为空
     */
    private Integer ISVALUE;

    /**
     * 提交人
     */
    private String TJR;

    /**
     * 提交时间，可为空
     */
    private String TJSJ;

    /**
     * 项目ID，可为空
     */
    private Integer PRJID;

    /**
     * 公司ID，可为空
     */
    private Integer GSID;

    /**
     * 车辆品牌
     */
    private String CLPP;

    /**
     * 加油卡ID，可为空
     */
    private Integer JYKID;

    /**
     * 保险公司
     */
    private String BXGS;

    /**
     * 使用状态
     */
    private String SYZT;

    /**
     * 下次检查日期，可为空
     */
    private String  NEXTCHECKDATE;

    /**
     * 排量
     */
    private String PL;

    /**
     * 车辆识别号，可为空
     */
    private Double CSLCBS;

    /**
     * 位置
     */
    private Location LOCATION;

    /**
     * 单位类型，可为空
     */
    private Integer DWTYPE;

    /**
     * 单位ID，可为空
     */
    private Integer DWID;

}
