//package com.baec.antiviral.lib.server.entity;

import lombok.Data;
import javax.persistence.*;
import java.util.Date;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
* 血氧小时统计表
*/
@Entity
@Data
@Table(name = "mvs_spo2_hour")
public class MvsSpo2Hour {

        /**
        * 
        */
        @Id
        @Column(name = "id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        /**
        * 用户id
        */
        @Column(name = "user_id")
        private Long userId;
        /**
        * 整点小时格式: yyyy-MM-dd HH:00:00
        */
        @Column(name = "timepoint")
        private Date timepoint;
        /**
        * 多体征设备编码
        */
        @Column(name = "device_code")
        private String deviceCode;
        /**
        * 设备类型（0:贴片, 1：博智多体征，2：微芯多体征）
        */
        @Column(name = "device_type")
        private Integer deviceType;
        /**
        * 最大值
        */
        @Column(name = "min_value")
        private Long minValue;
        /**
        * 最小值
        */
        @Column(name = "max_value")
        private Long maxValue;
        /**
        * 血氧总值
        */
        @Column(name = "total_value")
        private Long totalValue;
        /**
        * 血氧数据总数量
        */
        @Column(name = "total_count")
        private Long totalCount;
        /**
        * 呼吸率均值
        */
        @Column(name = "avg_value")
        private  avgValue;
        /**
        * 状态（0：正常，1：已删除）
        */
        @Column(name = "archived")
        private Integer archived;
        /**
        * 版本号
        */
        @Column(name = "version")
        private Integer version;
        /**
        * 
        */
        @Column(name = "create_user")
        private Long createUser;
        /**
        * 
        */
        @Column(name = "update_user")
        private Long updateUser;
        /**
        * 
        */
        @Column(name = "create_time")
        private Date createTime;
        /**
        * 
        */
        @Column(name = "update_time")
        private Date updateTime;
}
