/*
Navicat MySQL Data Transfer

Source Server         : HD-Tools
Source Server Version : 50719
Source Host           : localhost:3306
Source Database       : hdtool

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

hdtool/Hd!ToO123
Date: 2017-11-07 18:00:52
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for area_info
-- ----------------------------
DROP TABLE IF EXISTS `area_info`;
CREATE TABLE `area_info` (
  `ID` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '序列编号',
  `PROJ_ID` bigint(12) DEFAULT NULL COMMENT '项目编号',
  `DEPT_TYPE_ID` bigint(12) DEFAULT NULL COMMENT '部门分类编号',
  `DEPT_ID` bigint(12) DEFAULT NULL COMMENT '部门编号',
  `OFFICE_NAME` varchar(255) DEFAULT NULL COMMENT '区域名称',
  `OFFICE_CODE` varchar(16) DEFAULT NULL COMMENT '区域编码',
  `NAME_SPELLING` varchar(255) DEFAULT NULL COMMENT '名称拼音',
  `NAME_CODE` varchar(255) DEFAULT NULL COMMENT '名称简拼',
  `ORDER_IDX` smallint(6) DEFAULT NULL COMMENT '顺序编号',
  `ORDER_NAME` varchar(32) DEFAULT NULL COMMENT '完整编号',
  `PLAN_CNT` int(11) DEFAULT NULL COMMENT '规划数量',
  `PLAN_AREA_TOTAL` double(8,2) DEFAULT NULL COMMENT '规划面积小计',
  `PLAN_AREA_SUMMARY` double(8,2) DEFAULT NULL COMMENT '规划面积合计',
  `DESIGN_CNT` int(11) DEFAULT NULL COMMENT '设计数量',
  `DESIGN_AREA_TOTAL` double(8,2) DEFAULT NULL COMMENT '设计面积小计',
  `DESIGN_AREA_SUMMARY` double(8,2) DEFAULT NULL COMMENT '设计面积合计',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  `NOTE` varchar(255) DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of area_info
-- ----------------------------
INSERT INTO `area_info` VALUES ('2', '329', '2465', '108', 'BB', 'BB', null, null, null, null, null, null, null, null, '225.00', null, '2017-10-26 20:43:07', null, 'BB');
INSERT INTO `area_info` VALUES ('4', '331', '2481', '116', 'TEST', 'P-01-01', null, null, '1', null, null, null, null, null, '264.00', null, '2017-10-29 20:30:34', null, 'TEST');
INSERT INTO `area_info` VALUES ('5', '331', '2481', '120', '测试区域', 'P.04.01', null, null, '1', null, null, null, null, null, '252.00', null, '2017-11-04 19:09:44', null, '测试备注');
INSERT INTO `area_info` VALUES ('6', '331', '2481', '120', 'aaa', 'P.04.04', null, null, '4', null, null, null, null, null, null, null, '2017-11-04 19:10:17', null, 'aa');
INSERT INTO `area_info` VALUES ('7', '331', '2481', '118', '11', 'P-03.01', null, null, '1', null, null, null, null, null, '121.00', null, '2017-11-04 19:38:54', null, '11');

-- ----------------------------
-- Table structure for area_summary
-- ----------------------------
DROP TABLE IF EXISTS `area_summary`;
CREATE TABLE `area_summary` (
  `ID` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '序列编号',
  `PROJ_ID` bigint(12) NOT NULL COMMENT '项目编号',
  `DEPT_TYPE_ID` bigint(12) NOT NULL COMMENT '部门分类编码',
  `DEPT_ID` bigint(12) NOT NULL COMMENT '部门编码',
  `PLAN_AREA_TOTAL` double(8,2) DEFAULT NULL COMMENT '规划面积总计',
  `DESIGN_AREA_TOTAL` double(8,2) DEFAULT NULL COMMENT '设计面积总计',
  `PLAN_AREA_PERSENT` double(8,2) DEFAULT NULL COMMENT '规划面积百分比',
  `DESIGN_AREA_PERSENT` double(8,2) DEFAULT NULL COMMENT '设计合计百分比',
  `PLAN_AREA_RATIO` double(8,2) DEFAULT NULL COMMENT '规划面积系数',
  `DESIGN_AREA_RATIO` double(8,2) DEFAULT NULL COMMENT '设计面积系统',
  `PLAN_AREA_SUMMARY` double(8,2) DEFAULT NULL COMMENT '规划面积小计',
  `DESIGN_AREA_SUMMARY` double(8,2) DEFAULT NULL COMMENT '设计面积小计',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  `NOTE` varchar(255) DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of area_summary
-- ----------------------------
INSERT INTO `area_summary` VALUES ('1', '329', '2465', '108', '300.00', '292.50', null, null, '1.50', '1.30', '200.00', '225.00', '2017-10-22 17:04:31', '2017-10-22 17:04:31', '区域信息测试');
INSERT INTO `area_summary` VALUES ('2', '329', '2466', '110', '121.00', '121.00', null, null, '1.20', '1.00', '100.83', '121.00', '2017-10-22 18:28:40', '2017-10-22 18:28:40', '123');
INSERT INTO `area_summary` VALUES ('3', '331', '2481', '111', '12.00', '144.00', null, null, '1.00', '1.00', '12.00', '144.00', '2017-10-28 15:29:56', '2017-10-28 15:29:56', '12');
INSERT INTO `area_summary` VALUES ('4', '331', '2481', '116', '120.00', '290.40', null, null, '1.10', '1.10', '109.09', '264.00', '2017-10-29 20:30:35', '2017-10-29 20:30:35', 'TEST');
INSERT INTO `area_summary` VALUES ('5', '331', '2481', '120', '100.00', '252.00', null, null, '1.10', '1.00', '90.91', '252.00', '2017-11-04 19:09:44', '2017-11-04 19:09:44', '测试备注');
INSERT INTO `area_summary` VALUES ('6', '331', '2481', '118', '100.00', '121.00', null, null, '1.00', '1.00', '100.00', '121.00', '2017-11-04 19:38:54', '2017-11-04 19:38:54', '11');

-- ----------------------------
-- Table structure for data_module_enum
-- ----------------------------
DROP TABLE IF EXISTS `data_module_enum`;
CREATE TABLE `data_module_enum` (
  `ID` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '序列编号',
  `MODULE_ID` bigint(12) NOT NULL COMMENT '模块编号',
  `ENUM_NAME` varchar(255) DEFAULT NULL COMMENT '枚举名称',
  `ENUM_CODE` varchar(255) DEFAULT NULL COMMENT '枚举简码',
  `ORDER_IDX` smallint(6) DEFAULT NULL COMMENT '顺序编号',
  `NAME_SPELLING` varchar(255) DEFAULT NULL COMMENT '名称拼音',
  `NAME_CODE` varchar(255) DEFAULT NULL COMMENT '名称简拼',
  `SELECT_TYPE` varchar(8) DEFAULT NULL COMMENT '参数方式',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `NOTE` varchar(255) DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1027 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of data_module_enum
-- ----------------------------
INSERT INTO `data_module_enum` VALUES ('1001', '1001', '高度', 'GD', '1', null, null, 'radio', null, null);
INSERT INTO `data_module_enum` VALUES ('1002', '1001', '吊顶材料', 'DDCL', '2', null, null, 'checkbox', null, null);
INSERT INTO `data_module_enum` VALUES ('1003', '1001', '辅助', 'FZ', '3', null, null, 'checkbox', null, null);
INSERT INTO `data_module_enum` VALUES ('1004', '1002', '地面材料', 'DMCL', '1', null, null, 'checkbox', null, null);
INSERT INTO `data_module_enum` VALUES ('1005', '1002', '防潮药水', 'FCYS', '2', null, null, 'checkbox', null, null);
INSERT INTO `data_module_enum` VALUES ('1006', '1003', '墙面材料', 'QMCL', '1', null, null, 'checkbox', null, null);
INSERT INTO `data_module_enum` VALUES ('1007', '1003', '踢脚材料', 'TJCL', '2', null, null, 'checkbox', null, null);
INSERT INTO `data_module_enum` VALUES ('1008', '1003', '踢脚高度', 'TJGD', '3', null, null, 'checkbox', null, null);
INSERT INTO `data_module_enum` VALUES ('1009', '1004', '门', 'M', '1', null, null, 'checkbox', null, null);
INSERT INTO `data_module_enum` VALUES ('1010', '1004', '门开关', 'MKG', '2', null, null, 'checkbox', null, null);
INSERT INTO `data_module_enum` VALUES ('1011', '1004', '窗', 'C', '3', null, null, 'checkbox', null, null);
INSERT INTO `data_module_enum` VALUES ('1012', '1006', '辐射当量', 'FSDL', '1', null, null, 'checkbox', null, null);
INSERT INTO `data_module_enum` VALUES ('1013', '1006', '防辐射材料', 'FFSCL', '2', null, null, 'checkbox', null, null);
INSERT INTO `data_module_enum` VALUES ('1014', '1007', '给水', 'GS', '1', null, null, 'checkbox', null, null);
INSERT INTO `data_module_enum` VALUES ('1015', '1007', '给水开关', 'GSKG', '2', null, null, 'checkbox', null, null);
INSERT INTO `data_module_enum` VALUES ('1016', '1008', '排水', 'PS', '1', null, null, 'checkbox', null, null);
INSERT INTO `data_module_enum` VALUES ('1017', '1009', '气体种类', 'QTZL', '1', null, null, 'checkbox', null, null);
INSERT INTO `data_module_enum` VALUES ('1018', '1009', '气体传输系统', 'QTCSXT', '2', null, null, 'checkbox', null, null);
INSERT INTO `data_module_enum` VALUES ('1019', '1010', '插座', 'CZ', '1', null, null, 'checkbox', null, null);
INSERT INTO `data_module_enum` VALUES ('1020', '1010', '灯光', 'DG', '2', null, null, 'checkbox', null, null);
INSERT INTO `data_module_enum` VALUES ('1021', '1011', '弱电信息', 'RDXX', '1', null, null, 'checkbox', null, null);
INSERT INTO `data_module_enum` VALUES ('1022', '1011', '物流', 'WL', '2', null, null, 'checkbox', null, null);
INSERT INTO `data_module_enum` VALUES ('1023', '1012', '通排风', 'TPF', '1', null, null, 'checkbox', null, null);
INSERT INTO `data_module_enum` VALUES ('1024', '1012', '洁净等级', 'JJDJ', '2', null, null, 'checkbox', null, null);
INSERT INTO `data_module_enum` VALUES ('1025', '1012', '空调', 'KT', '3', null, null, 'checkbox', null, null);
INSERT INTO `data_module_enum` VALUES ('1026', '1013', '医疗设备', 'YLSB', '1', null, null, 'checkbox', null, null);

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `ID` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '序列编号',
  `PROJ_ID` bigint(12) DEFAULT NULL COMMENT '项目编号',
  `DEPT_TYPE_ID` bigint(12) DEFAULT NULL COMMENT '部门分类编号',
  `DEPT_NAME` varchar(255) DEFAULT NULL COMMENT '部门名称',
  `DEPT_CODE` varchar(16) DEFAULT NULL COMMENT '部门编码',
  `NAME_SPELLING` varchar(255) DEFAULT NULL COMMENT '名称拼音',
  `NAME_CODE` varchar(255) DEFAULT NULL COMMENT '名称简拼',
  `ORDER_IDX` smallint(6) DEFAULT NULL COMMENT '顺序编号',
  `ORDER_NAME` varchar(32) DEFAULT NULL COMMENT '完整编号',
  `PLAN_AREA` double(8,2) DEFAULT NULL COMMENT '规划面积',
  `DESIGN_AREA` double(8,2) DEFAULT NULL COMMENT '设计面积',
  `PLAN_AREA_PERSENT` double(4,2) DEFAULT NULL COMMENT '规划面积百分比',
  `DESIGN_AREA_PERSENT` double(4,2) DEFAULT NULL COMMENT '设计合计百分比',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  `NOTE` varchar(255) DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=121 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES ('108', '329', '2465', '测试部门', 'P01', null, null, null, null, '300.00', '292.50', null, null, '2017-10-22 15:36:26', null, null);
INSERT INTO `department` VALUES ('110', '329', '2466', 'as', 'as', null, null, null, null, '121.00', '121.00', null, null, '2017-10-22 18:25:31', null, null);
INSERT INTO `department` VALUES ('116', '331', '2481', '测试部门', 'P-01', null, null, '1', null, '120.00', '290.40', null, null, '2017-10-29 18:03:26', null, null);
INSERT INTO `department` VALUES ('117', '331', '2481', '测试部门02', 'P-02', null, null, '2', null, '200.00', '0.00', null, null, '2017-10-29 18:03:51', null, null);
INSERT INTO `department` VALUES ('118', '331', '2481', '又一个新部门', 'P-03', null, null, '3', null, '100.00', '121.00', null, null, '2017-10-29 19:20:12', null, null);
INSERT INTO `department` VALUES ('119', '331', '2486', '测试', 'AD-01', null, null, '1', null, '20.00', '0.00', null, null, '2017-10-29 19:21:37', null, null);
INSERT INTO `department` VALUES ('120', '331', '2481', '测试部门', 'P.04', null, null, '4', null, '100.00', '252.00', null, null, '2017-11-04 19:09:13', null, null);

-- ----------------------------
-- Table structure for dept_summary
-- ----------------------------
DROP TABLE IF EXISTS `dept_summary`;
CREATE TABLE `dept_summary` (
  `ID` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '序列编号',
  `PROJ_ID` bigint(12) NOT NULL COMMENT '项目编号',
  `PLAN_AREA_TOTAL` double(8,2) DEFAULT NULL COMMENT '规划面积总计',
  `DESIGN_AREA_TOTAL` double(8,2) DEFAULT NULL COMMENT '设计面积总计',
  `PLAN_AREA_PERSENT` double(8,2) DEFAULT NULL COMMENT '规划面积百分比',
  `DESIGN_AREA_PERSENT` double(8,2) DEFAULT NULL COMMENT '设计合计百分比',
  `PLAN_AREA_RATIO` double(8,2) DEFAULT NULL COMMENT '规划面积系数',
  `DESIGN_AREA_RATIO` double(8,2) DEFAULT NULL COMMENT '设计面积系数',
  `PLAN_AREA_SUMMARY` double(8,2) DEFAULT NULL COMMENT '规划面积小计',
  `DESIGN_AREA_SUMMARY` double(8,2) DEFAULT NULL COMMENT '设计面积小计',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  `NOTE` varchar(255) DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=226 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dept_summary
-- ----------------------------
INSERT INTO `dept_summary` VALUES ('221', '329', '840.00', '620.25', '100.00', '100.00', '1.20', '1.50', '700.00', '413.50', null, null, null);
INSERT INTO `dept_summary` VALUES ('222', '330', '0.00', '0.00', '100.00', '100.00', '1.00', '1.00', '0.00', '0.00', null, null, null);
INSERT INTO `dept_summary` VALUES ('223', '331', '648.00', '729.74', '100.00', '100.00', '1.20', '1.10', '540.00', '663.40', null, null, null);
INSERT INTO `dept_summary` VALUES ('224', '332', '0.00', '0.00', '100.00', '100.00', '1.00', '1.00', '0.00', '0.00', null, null, null);
INSERT INTO `dept_summary` VALUES ('225', '333', '0.00', '0.00', '100.00', '100.00', '1.10', '1.00', '0.00', '0.00', null, null, null);

-- ----------------------------
-- Table structure for dept_type
-- ----------------------------
DROP TABLE IF EXISTS `dept_type`;
CREATE TABLE `dept_type` (
  `ID` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '序列编号',
  `PROJ_ID` bigint(12) DEFAULT NULL COMMENT '项目编号',
  `DEPT_NAME` varchar(255) DEFAULT NULL COMMENT '部门分类名称',
  `DEPT_CODE` varchar(16) DEFAULT NULL COMMENT '部门分类编码',
  `NAME_SPELLING` varchar(255) DEFAULT NULL COMMENT '名称拼音',
  `NAME_CODE` varchar(255) DEFAULT NULL COMMENT '名称简拼',
  `ORDER_IDX` smallint(6) DEFAULT NULL COMMENT '顺序编号',
  `ORDER_NAME` varchar(32) DEFAULT NULL COMMENT '完整编号',
  `PLAN_AREA` double(8,2) DEFAULT NULL COMMENT '规划面积',
  `DESIGN_AREA` double(8,2) DEFAULT NULL COMMENT '设计面积',
  `PLAN_AREA_PERSENT` double(8,2) DEFAULT NULL COMMENT '规划面积百分比',
  `DESIGN_AREA_PERSENT` double(8,2) DEFAULT NULL COMMENT '设计合计百分比',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  `NOTE` varchar(255) DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2505 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dept_type
-- ----------------------------
INSERT INTO `dept_type` VALUES ('2465', '329', '公共空间', 'P', null, null, '1', null, '300.00', '292.50', '42.86', '70.74', '2017-10-22 14:55:29', null, null);
INSERT INTO `dept_type` VALUES ('2466', '329', '急诊部', 'A', null, null, '2', null, '200.00', '121.00', '28.57', '29.26', '2017-10-22 14:55:29', null, null);
INSERT INTO `dept_type` VALUES ('2467', '329', '门诊部', 'C', null, null, '3', null, '200.00', '0.00', '28.57', '0.00', '2017-10-22 14:55:29', null, null);
INSERT INTO `dept_type` VALUES ('2468', '329', '住院部', 'I', null, null, '4', null, '0.00', '0.00', '0.00', '0.00', '2017-10-22 14:55:29', null, null);
INSERT INTO `dept_type` VALUES ('2469', '329', '医技部', 'D', null, null, '5', null, '0.00', '0.00', '0.00', '0.00', '2017-10-22 14:55:29', null, null);
INSERT INTO `dept_type` VALUES ('2470', '329', '行政部', 'AD', null, null, '6', null, '0.00', '0.00', '0.00', '0.00', '2017-10-22 14:55:29', null, null);
INSERT INTO `dept_type` VALUES ('2471', '329', '后勤支持', 'S', null, null, '7', null, '0.00', '0.00', '0.00', '0.00', '2017-10-22 14:55:29', null, null);
INSERT INTO `dept_type` VALUES ('2472', '329', '机房', 'M', null, null, '8', null, '0.00', '0.00', '0.00', '0.00', '2017-10-22 14:55:29', null, null);
INSERT INTO `dept_type` VALUES ('2473', '330', '公共空间', 'P', null, null, '1', null, '0.00', '0.00', '12.50', '12.50', '2017-10-28 15:29:19', null, null);
INSERT INTO `dept_type` VALUES ('2474', '330', '急诊部', 'A', null, null, '2', null, '0.00', '0.00', '12.50', '12.50', '2017-10-28 15:29:19', null, null);
INSERT INTO `dept_type` VALUES ('2475', '330', '门诊部', 'C', null, null, '3', null, '0.00', '0.00', '12.50', '12.50', '2017-10-28 15:29:19', null, null);
INSERT INTO `dept_type` VALUES ('2476', '330', '住院部', 'I', null, null, '4', null, '0.00', '0.00', '12.50', '12.50', '2017-10-28 15:29:19', null, null);
INSERT INTO `dept_type` VALUES ('2477', '330', '医技部', 'D', null, null, '5', null, '0.00', '0.00', '12.50', '12.50', '2017-10-28 15:29:19', null, null);
INSERT INTO `dept_type` VALUES ('2478', '330', '行政部', 'AD', null, null, '6', null, '0.00', '0.00', '12.50', '12.50', '2017-10-28 15:29:19', null, null);
INSERT INTO `dept_type` VALUES ('2479', '330', '后勤支持', 'S', null, null, '7', null, '0.00', '0.00', '12.50', '12.50', '2017-10-28 15:29:19', null, null);
INSERT INTO `dept_type` VALUES ('2480', '330', '机房', 'M', null, null, '8', null, '0.00', '0.00', '12.50', '12.50', '2017-10-28 15:29:19', null, null);
INSERT INTO `dept_type` VALUES ('2481', '331', '公共空间', 'P', null, null, '1', null, '520.00', '663.40', '96.30', '100.00', '2017-10-28 15:29:31', null, null);
INSERT INTO `dept_type` VALUES ('2482', '331', '急诊部', 'A', null, null, '2', null, '0.00', '0.00', '0.00', '0.00', '2017-10-28 15:29:31', null, null);
INSERT INTO `dept_type` VALUES ('2483', '331', '门诊部', 'C', null, null, '3', null, '0.00', '0.00', '0.00', '0.00', '2017-10-28 15:29:31', null, null);
INSERT INTO `dept_type` VALUES ('2484', '331', '住院部', 'I', null, null, '4', null, '0.00', '0.00', '0.00', '0.00', '2017-10-28 15:29:31', null, null);
INSERT INTO `dept_type` VALUES ('2485', '331', '医技部', 'D', null, null, '5', null, '0.00', '0.00', '0.00', '0.00', '2017-10-28 15:29:31', null, null);
INSERT INTO `dept_type` VALUES ('2486', '331', '行政部', 'AD', null, null, '6', null, '20.00', '0.00', '3.70', '0.00', '2017-10-28 15:29:31', null, null);
INSERT INTO `dept_type` VALUES ('2487', '331', '后勤支持', 'S', null, null, '7', null, '0.00', '0.00', '0.00', '0.00', '2017-10-28 15:29:31', null, null);
INSERT INTO `dept_type` VALUES ('2488', '331', '机房', 'M', null, null, '8', null, '0.00', '0.00', '0.00', '0.00', '2017-10-28 15:29:31', null, null);
INSERT INTO `dept_type` VALUES ('2489', '332', '公共空间', 'P', null, null, '1', null, '0.00', '0.00', '12.50', '12.50', '2017-10-28 15:54:30', null, null);
INSERT INTO `dept_type` VALUES ('2490', '332', '急诊部', 'A', null, null, '2', null, '0.00', '0.00', '12.50', '12.50', '2017-10-28 15:54:30', null, null);
INSERT INTO `dept_type` VALUES ('2491', '332', '门诊部', 'C', null, null, '3', null, '0.00', '0.00', '12.50', '12.50', '2017-10-28 15:54:30', null, null);
INSERT INTO `dept_type` VALUES ('2492', '332', '住院部', 'I', null, null, '4', null, '0.00', '0.00', '12.50', '12.50', '2017-10-28 15:54:30', null, null);
INSERT INTO `dept_type` VALUES ('2493', '332', '医技部', 'D', null, null, '5', null, '0.00', '0.00', '12.50', '12.50', '2017-10-28 15:54:30', null, null);
INSERT INTO `dept_type` VALUES ('2494', '332', '行政部', 'AD', null, null, '6', null, '0.00', '0.00', '12.50', '12.50', '2017-10-28 15:54:30', null, null);
INSERT INTO `dept_type` VALUES ('2495', '332', '后勤支持', 'S', null, null, '7', null, '0.00', '0.00', '12.50', '12.50', '2017-10-28 15:54:30', null, null);
INSERT INTO `dept_type` VALUES ('2496', '332', '机房', 'M', null, null, '8', null, '0.00', '0.00', '12.50', '12.50', '2017-10-28 15:54:30', null, null);
INSERT INTO `dept_type` VALUES ('2497', '333', '公共空间', 'P', null, null, '1', null, '0.00', '0.00', '12.50', '12.50', '2017-11-04 19:51:03', null, null);
INSERT INTO `dept_type` VALUES ('2498', '333', '急诊部', 'A', null, null, '2', null, '0.00', '0.00', '12.50', '12.50', '2017-11-04 19:51:03', null, null);
INSERT INTO `dept_type` VALUES ('2499', '333', '门诊部', 'C', null, null, '3', null, '0.00', '0.00', '12.50', '12.50', '2017-11-04 19:51:03', null, null);
INSERT INTO `dept_type` VALUES ('2500', '333', '住院部', 'I', null, null, '4', null, '0.00', '0.00', '12.50', '12.50', '2017-11-04 19:51:03', null, null);
INSERT INTO `dept_type` VALUES ('2501', '333', '医技部', 'D', null, null, '5', null, '0.00', '0.00', '12.50', '12.50', '2017-11-04 19:51:03', null, null);
INSERT INTO `dept_type` VALUES ('2502', '333', '行政部', 'AD', null, null, '6', null, '0.00', '0.00', '12.50', '12.50', '2017-11-04 19:51:03', null, null);
INSERT INTO `dept_type` VALUES ('2503', '333', '后勤支持', 'S', null, null, '7', null, '0.00', '0.00', '12.50', '12.50', '2017-11-04 19:51:03', null, null);
INSERT INTO `dept_type` VALUES ('2504', '333', '机房', 'M', null, null, '8', null, '0.00', '0.00', '12.50', '12.50', '2017-11-04 19:51:03', null, null);

-- ----------------------------
-- Table structure for module_enum_param
-- ----------------------------
DROP TABLE IF EXISTS `module_enum_param`;
CREATE TABLE `module_enum_param` (
  `ID` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '序列编号',
  `MODULE_ID` bigint(12) NOT NULL COMMENT '模块编号',
  `ENUM_ID` bigint(12) NOT NULL COMMENT '枚举编号',
  `ENUM_PARAM_NAME` varchar(255) DEFAULT NULL COMMENT '枚举参数名称',
  `ENUM_PARAM_CODE` varchar(255) DEFAULT NULL COMMENT '枚举参数简码',
  `ORDER_IDX` smallint(6) DEFAULT NULL COMMENT '顺序编号',
  `NAME_SPELLING` varchar(255) DEFAULT NULL COMMENT '名称拼音',
  `NAME_CODE` varchar(255) DEFAULT NULL COMMENT '名称简拼',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `NOTE` varchar(255) DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1133 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of module_enum_param
-- ----------------------------
INSERT INTO `module_enum_param` VALUES ('1001', '1001', '1001', '3.0米', '', '1', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1002', '1001', '1001', '2.8米', '', '2', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1003', '1001', '1001', '2.6米', '', '3', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1004', '1001', '1001', '2.4米', '', '4', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1005', '1001', '1001', '其它', '', '5', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1006', '1001', '1002', '输液帘', 'SYL', '1', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1007', '1001', '1002', '输液轨', 'SYG', '2', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1008', '1001', '1003', '吸音矿棉板', '', '1', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1009', '1001', '1003', '石膏板', '', '2', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1010', '1001', '1003', '金属板', '', '3', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1011', '1001', '1003', '格栅', '', '4', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1012', '1001', '1003', '不吊顶', '', '5', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1013', '1002', '1004', 'PVC块材', '', '1', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1014', '1002', '1004', 'PVC卷材', '', '2', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1015', '1002', '1004', '天然橡胶', '', '3', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1016', '1002', '1004', '瓷砖', '', '4', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1017', '1002', '1004', '地毯', '', '5', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1018', '1002', '1004', '自留坪地面', '', '6', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1019', '1002', '1004', '大理石', '', '7', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1021', '1002', '1005', '防水', '', '1', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1022', '1003', '1006', 'PVC块材', '', '1', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1023', '1003', '1006', 'PVC卷材', '', '2', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1024', '1003', '1006', '天然橡胶', '', '3', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1025', '1003', '1006', '瓷砖', '', '4', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1026', '1003', '1006', '地毯', '', '5', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1027', '1003', '1006', '自留坪地面', '', '6', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1028', '1003', '1006', '大理石', '', '7', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1029', '1003', '1007', 'PVC块材', '', '1', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1030', '1003', '1007', 'PVC卷材', '', '2', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1031', '1003', '1007', '天然橡胶', '', '3', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1032', '1003', '1007', '瓷砖', '', '4', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1033', '1003', '1007', '大理石', '', '5', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1034', '1003', '1007', '金属', '', '6', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1035', '1003', '1008', '100', '', '1', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1036', '1003', '1008', '150', '', '2', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1037', '1003', '1008', '其它', '', '3', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1038', '1004', '1009', '平开门', '', '1', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1039', '1004', '1009', '推拉门', '', '2', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1040', '1004', '1009', '旋转门', '', '3', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1041', '1004', '1009', '带观察窗', '', '4', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1042', '1004', '1010', '普通门把手', '', '1', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1043', '1004', '1010', '感应门', '', '2', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1044', '1004', '1010', '电动门', '', '3', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1045', '1004', '1010', '门禁系统', '', '4', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1046', '1006', '1012', '厚铅板', '', '1', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1047', '1006', '1012', '铅板', '', '1', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1048', '1006', '1012', '重晶石涂料', '', '2', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1049', '1006', '1012', '铜板', '', '3', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1050', '1006', '1012', '其它', '', '4', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1051', '1007', '1014', '独立水盆', '', '1', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1052', '1007', '1014', '双水盆', '', '2', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1053', '1007', '1014', '刷手槽', '', '3', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1054', '1007', '1014', '不锈钢水槽', '', '4', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1055', '1007', '1014', '蒸汽枪', '', '5', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1056', '1007', '1014', '洗眼装置', '', '6', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1057', '1007', '1014', '去离子水', '', '7', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1058', '1007', '1014', '蒸馏纯水', '', '8', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1059', '1007', '1014', '冲便器', '', '9', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1060', '1007', '1014', '座便器', '', '10', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1061', '1007', '1014', '小便斗', '', '11', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1062', '1007', '1014', '蹲便器', '', '12', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1063', '1007', '1015', '红外线感应开关', '', '1', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1064', '1007', '1015', '肘控开关', '', '2', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1065', '1007', '1015', '膝控开关', '', '3', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1066', '1007', '1015', '长把手开关', '', '4', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1067', '1007', '1015', '普通开关', '', '5', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1068', '1008', '1016', '地漏', '', '1', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1069', '1008', '1016', '银回收', '', '2', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1070', '1008', '1016', '酸水处理', '', '3', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1071', '1008', '1016', '血透排口', '', '4', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1072', '1008', '1016', '蒸汽枪', '', '5', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1073', '1009', '1017', '氧气', '', '1', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1074', '1009', '1017', '负压', '', '2', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1075', '1009', '1017', '压缩空气', '', '3', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1076', '1009', '1017', '笑气', '', '4', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1077', '1009', '1017', '氮气', '', '5', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1078', '1009', '1017', '液氮', '', '6', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1079', '1009', '1018', '墙插', '', '1', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1080', '1009', '1018', '软管', '', '2', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1081', '1009', '1018', '模块化医疗气体带', '', '3', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1082', '1009', '1018', '吊塔', '', '4', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1083', '1009', '1018', '通高柱桩', '', '5', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1084', '1009', '1018', '悬挂柱桩', '', '6', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1085', '1010', '1019', '双孔插座', '', '1', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1086', '1010', '1019', '三相插座', '', '2', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1087', '1010', '1019', '380伏三相插座', '', '3', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1088', '1010', '1019', '应急电源插座', '', '4', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1089', '1010', '1019', '插线板', '', '5', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1090', '1010', '1019', 'X光接口', '', '6', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1091', '1010', '1020', '荧光灯', '', '1', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1092', '1010', '1020', 'LED', '', '2', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1093', '1010', '1020', '白炽灯', '', '3', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1094', '1010', '1020', '反光灯', '', '4', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1095', '1010', '1020', '检查灯', '', '5', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1096', '1010', '1020', '可调光灯', '', '6', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1097', '1010', '1020', '手术灯', '', '7', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1098', '1010', '1020', '手术辅灯', '', '8', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1099', '1011', '1021', '护士呼叫系统', '', '1', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1100', '1011', '1021', '对讲系统', '', '2', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1101', '1011', '1021', '紧急呼救系统', '', '3', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1102', '1011', '1021', 'inter网络系统', '', '4', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1103', '1011', '1021', 'WiFi系统', '', '5', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1104', '1011', '1021', '有线电话系统', '', '6', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1105', '1011', '1021', '有线电视系统', '', '7', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1106', '1011', '1021', '时钟系统', '', '8', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1107', '1011', '1021', '安保监控', '', '9', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1108', '1011', '1022', '气动传输系统', '', '1', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1109', '1011', '1022', '轨道小车', '', '2', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1110', '1011', '1022', '机器人小车', '', '3', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1111', '1012', '1023', '排气扇', '', '1', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1112', '1012', '1023', '排风柜', '', '2', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1113', '1012', '1023', '负压-5Pa', '', '3', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1114', '1012', '1023', '负压-10Pa', '', '4', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1115', '1012', '1023', '负压-15Pa', '', '5', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1116', '1012', '1024', '十万级', '', '1', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1117', '1012', '1024', '万级', '', '2', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1118', '1012', '1024', '千级', '', '3', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1119', '1012', '1024', '百级', '', '4', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1120', '1012', '1025', '冬天温度__度', '', '1', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1121', '1012', '1025', '夏天温度__度', '', '2', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1122', '1012', '1025', '湿度', '', '3', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1123', '1012', '1025', '温度控制', '', '4', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1124', '1012', '1025', '湿度控制', '', '5', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1125', '1013', '1026', '急救推车', '', '1', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1126', '1013', '1026', '生理监护仪', '', '2', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1127', '1013', '1026', '遥感监测', '', '3', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1128', '1013', '1026', '视频监护', '', '4', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1129', '1013', '1026', 'X光观片灯', '', '5', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1130', '1013', '1026', '灰阶屏', '', '6', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1131', '1013', '1026', '垃圾处理机', '', '7', null, null, null, null);
INSERT INTO `module_enum_param` VALUES ('1132', '1013', '1026', '利器盒', '', '8', null, null, null, null);

-- ----------------------------
-- Table structure for proj_base_info
-- ----------------------------
DROP TABLE IF EXISTS `proj_base_info`;
CREATE TABLE `proj_base_info` (
  `ID` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '序列编号',
  `GROUP_ID` bigint(12) DEFAULT NULL COMMENT '项目识别号',
  `PROJ_NAME` varchar(255) DEFAULT NULL COMMENT '项目名称',
  `PROJ_SUBTITLE` varchar(255) DEFAULT NULL COMMENT '项目副标题',
  `NAME_SPELLING` varchar(255) DEFAULT NULL COMMENT '名称拼音',
  `NAME_CODE` varchar(255) DEFAULT NULL COMMENT '名称简拼',
  `REGION_CODE` varchar(16) DEFAULT NULL COMMENT '区域编码',
  `REGION_NAME` varchar(32) DEFAULT NULL COMMENT '区域名称',
  `PROJ_DESC` varchar(2048) DEFAULT NULL COMMENT '项目描述',
  `PROJ_TYPE` varchar(4) DEFAULT NULL COMMENT '项目类型',
  `PROJ_PHASE` varchar(4) DEFAULT NULL COMMENT '项目阶段:1.概念阶段；2.方案阶段；3.设计阶段；4.施工阶段',
  `FLOORAGE` double(8,2) DEFAULT NULL COMMENT '建筑面积',
  `AREA_RATIO` double(4,2) DEFAULT NULL COMMENT '面积系数',
  `VER_ID` bigint(12) DEFAULT NULL COMMENT '版本编号',
  `VER_INFO` varchar(64) DEFAULT NULL COMMENT '版本信息',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  `NOTE` varchar(255) DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=334 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of proj_base_info
-- ----------------------------
INSERT INTO `proj_base_info` VALUES ('329', '377', '测试项目', '测试项目描述信息', null, null, '110000', null, null, '1', '1', null, null, '415', 'V01', '2017-10-22 14:55:05', null, null);
INSERT INTO `proj_base_info` VALUES ('330', '378', null, null, null, null, null, null, null, null, null, null, null, '416', 'V01', null, null, null);
INSERT INTO `proj_base_info` VALUES ('331', '379', '重庆项目', '', null, null, '110000', null, null, '1', '1', null, null, '417', 'V01', '2017-10-28 15:29:18', null, null);
INSERT INTO `proj_base_info` VALUES ('332', '380', null, null, null, null, null, null, null, null, null, null, null, '418', 'V01', null, null, null);
INSERT INTO `proj_base_info` VALUES ('333', '381', '创建新的项目', '项目描述信息AAAAAA', null, null, '110000', null, null, '1', '4', null, null, '419', 'V01', '2017-11-04 19:50:25', null, null);

-- ----------------------------
-- Table structure for proj_group
-- ----------------------------
DROP TABLE IF EXISTS `proj_group`;
CREATE TABLE `proj_group` (
  `ID` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '识别号',
  `PROJ_NAME` varchar(255) DEFAULT NULL COMMENT '项目名称',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=382 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of proj_group
-- ----------------------------
INSERT INTO `proj_group` VALUES ('377', null);
INSERT INTO `proj_group` VALUES ('378', null);
INSERT INTO `proj_group` VALUES ('379', null);
INSERT INTO `proj_group` VALUES ('380', null);
INSERT INTO `proj_group` VALUES ('381', null);

-- ----------------------------
-- Table structure for proj_version
-- ----------------------------
DROP TABLE IF EXISTS `proj_version`;
CREATE TABLE `proj_version` (
  `PROJ_ID` bigint(12) NOT NULL COMMENT '项目编号',
  `GROUP_ID` bigint(12) NOT NULL COMMENT '项目识别号',
  `VER_ID` bigint(12) NOT NULL COMMENT '版本编号',
  `VER_STATUS` varchar(4) DEFAULT NULL COMMENT '版本状态',
  `VER_INFO` varchar(64) DEFAULT NULL COMMENT '版本信息，包含简称和两级编号'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of proj_version
-- ----------------------------

-- ----------------------------
-- Table structure for room_data_detail
-- ----------------------------
DROP TABLE IF EXISTS `room_data_detail`;
CREATE TABLE `room_data_detail` (
  `ID` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '序列编号',
  `ROOM_ID` bigint(12) NOT NULL COMMENT '房间编号',
  `MODULE_ID` bigint(12) NOT NULL COMMENT '模块编号',
  `ENUM_ID` bigint(12) NOT NULL COMMENT '枚举编号',
  `PARAM_ID` bigint(12) NOT NULL COMMENT '参数编号',
  `MODULE_ENUM` varchar(255) DEFAULT NULL COMMENT '名称拼音',
  `MODULE_PARAM` varchar(255) DEFAULT NULL COMMENT '名称简拼',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=210 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of room_data_detail
-- ----------------------------
INSERT INTO `room_data_detail` VALUES ('2', '8', '1001', '1001', '1005', '1001_1001', '1001_1001_1005', '2017-10-28 15:26:43');
INSERT INTO `room_data_detail` VALUES ('4', '8', '1001', '1002', '1006', '1001_1002', '1001_1002_1006', '2017-10-28 15:26:45');
INSERT INTO `room_data_detail` VALUES ('106', '12', '1013', '1026', '1126', '1013_1026', '1013_1026_1126', '2017-11-04 18:55:04');
INSERT INTO `room_data_detail` VALUES ('107', '12', '1013', '1026', '1129', '1013_1026', '1013_1026_1129', '2017-11-04 18:55:05');
INSERT INTO `room_data_detail` VALUES ('108', '12', '1013', '1026', '1132', '1013_1026', '1013_1026_1132', '2017-11-04 18:55:05');
INSERT INTO `room_data_detail` VALUES ('109', '12', '1002', '1004', '1013', '1002_1004', '1002_1004_1013', '2017-11-04 18:55:05');
INSERT INTO `room_data_detail` VALUES ('110', '12', '1003', '1006', '1022', '1003_1006', '1003_1006_1022', '2017-11-04 18:55:05');
INSERT INTO `room_data_detail` VALUES ('111', '12', '1003', '1007', '1029', '1003_1007', '1003_1007_1029', '2017-11-04 18:55:06');
INSERT INTO `room_data_detail` VALUES ('112', '12', '1002', '1005', '1021', '1002_1005', '1002_1005_1021', '2017-11-04 18:55:06');
INSERT INTO `room_data_detail` VALUES ('113', '12', '1001', '1002', '1006', '1001_1002', '1001_1002_1006', '2017-11-04 18:55:06');
INSERT INTO `room_data_detail` VALUES ('114', '12', '1004', '1009', '1038', '1004_1009', '1004_1009_1038', '2017-11-04 18:55:06');
INSERT INTO `room_data_detail` VALUES ('115', '12', '1006', '1012', '1046', '1006_1012', '1006_1012_1046', '2017-11-04 18:55:06');
INSERT INTO `room_data_detail` VALUES ('116', '12', '1009', '1017', '1073', '1009_1017', '1009_1017_1073', '2017-11-04 18:55:06');
INSERT INTO `room_data_detail` VALUES ('117', '12', '1008', '1016', '1068', '1008_1016', '1008_1016_1068', '2017-11-04 18:55:06');
INSERT INTO `room_data_detail` VALUES ('118', '12', '1007', '1014', '1051', '1007_1014', '1007_1014_1051', '2017-11-04 18:55:06');
INSERT INTO `room_data_detail` VALUES ('119', '12', '1007', '1015', '1063', '1007_1015', '1007_1015_1063', '2017-11-04 18:55:06');
INSERT INTO `room_data_detail` VALUES ('120', '12', '1009', '1018', '1079', '1009_1018', '1009_1018_1079', '2017-11-04 18:55:06');
INSERT INTO `room_data_detail` VALUES ('121', '12', '1011', '1021', '1099', '1011_1021', '1011_1021_1099', '2017-11-04 18:55:06');
INSERT INTO `room_data_detail` VALUES ('122', '12', '1011', '1021', '1102', '1011_1021', '1011_1021_1102', '2017-11-04 18:55:06');
INSERT INTO `room_data_detail` VALUES ('123', '12', '1011', '1021', '1105', '1011_1021', '1011_1021_1105', '2017-11-04 18:55:06');
INSERT INTO `room_data_detail` VALUES ('125', '12', '1001', '1001', '1001', '1001_1001', '1001_1001_1001', '2017-11-04 18:55:07');
INSERT INTO `room_data_detail` VALUES ('153', '10', '1004', '1009', '1039', '1004_1009', '1004_1009_1039', '2017-11-04 19:07:42');
INSERT INTO `room_data_detail` VALUES ('154', '10', '1007', '1015', '1064', '1007_1015', '1007_1015_1064', '2017-11-04 19:07:42');
INSERT INTO `room_data_detail` VALUES ('156', '10', '1001', '1001', '1005', '1001_1001', '1001_1001_1005', '2017-11-04 19:07:42');
INSERT INTO `room_data_detail` VALUES ('161', '13', '1013', '1026', '1126', '1013_1026', '1013_1026_1126', '2017-11-04 19:14:14');
INSERT INTO `room_data_detail` VALUES ('162', '13', '1013', '1026', '1129', '1013_1026', '1013_1026_1129', '2017-11-04 19:14:14');
INSERT INTO `room_data_detail` VALUES ('163', '13', '1013', '1026', '1132', '1013_1026', '1013_1026_1132', '2017-11-04 19:14:14');
INSERT INTO `room_data_detail` VALUES ('164', '13', '1002', '1004', '1013', '1002_1004', '1002_1004_1013', '2017-11-04 19:14:14');
INSERT INTO `room_data_detail` VALUES ('165', '13', '1003', '1006', '1022', '1003_1006', '1003_1006_1022', '2017-11-04 19:14:14');
INSERT INTO `room_data_detail` VALUES ('166', '13', '1003', '1007', '1029', '1003_1007', '1003_1007_1029', '2017-11-04 19:14:14');
INSERT INTO `room_data_detail` VALUES ('167', '13', '1002', '1005', '1021', '1002_1005', '1002_1005_1021', '2017-11-04 19:14:14');
INSERT INTO `room_data_detail` VALUES ('168', '13', '1001', '1002', '1006', '1001_1002', '1001_1002_1006', '2017-11-04 19:14:15');
INSERT INTO `room_data_detail` VALUES ('169', '13', '1004', '1009', '1038', '1004_1009', '1004_1009_1038', '2017-11-04 19:14:15');
INSERT INTO `room_data_detail` VALUES ('170', '13', '1006', '1012', '1046', '1006_1012', '1006_1012_1046', '2017-11-04 19:14:15');
INSERT INTO `room_data_detail` VALUES ('171', '13', '1009', '1017', '1073', '1009_1017', '1009_1017_1073', '2017-11-04 19:14:15');
INSERT INTO `room_data_detail` VALUES ('172', '13', '1008', '1016', '1068', '1008_1016', '1008_1016_1068', '2017-11-04 19:14:15');
INSERT INTO `room_data_detail` VALUES ('173', '13', '1007', '1014', '1051', '1007_1014', '1007_1014_1051', '2017-11-04 19:14:15');
INSERT INTO `room_data_detail` VALUES ('174', '13', '1007', '1015', '1063', '1007_1015', '1007_1015_1063', '2017-11-04 19:14:15');
INSERT INTO `room_data_detail` VALUES ('175', '13', '1009', '1018', '1079', '1009_1018', '1009_1018_1079', '2017-11-04 19:14:15');
INSERT INTO `room_data_detail` VALUES ('176', '13', '1011', '1021', '1099', '1011_1021', '1011_1021_1099', '2017-11-04 19:14:15');
INSERT INTO `room_data_detail` VALUES ('177', '13', '1011', '1021', '1102', '1011_1021', '1011_1021_1102', '2017-11-04 19:14:15');
INSERT INTO `room_data_detail` VALUES ('178', '13', '1011', '1021', '1105', '1011_1021', '1011_1021_1105', '2017-11-04 19:14:15');
INSERT INTO `room_data_detail` VALUES ('186', '13', '1001', '1001', '1001', '1001_1001', '1001_1001_1001', '2017-11-04 19:23:25');
INSERT INTO `room_data_detail` VALUES ('187', '14', '1013', '1026', '1126', '1013_1026', '1013_1026_1126', '2017-11-04 19:39:47');
INSERT INTO `room_data_detail` VALUES ('188', '14', '1013', '1026', '1129', '1013_1026', '1013_1026_1129', '2017-11-04 19:39:47');
INSERT INTO `room_data_detail` VALUES ('189', '14', '1013', '1026', '1132', '1013_1026', '1013_1026_1132', '2017-11-04 19:39:47');
INSERT INTO `room_data_detail` VALUES ('190', '14', '1002', '1004', '1013', '1002_1004', '1002_1004_1013', '2017-11-04 19:39:48');
INSERT INTO `room_data_detail` VALUES ('191', '14', '1003', '1006', '1022', '1003_1006', '1003_1006_1022', '2017-11-04 19:39:48');
INSERT INTO `room_data_detail` VALUES ('192', '14', '1003', '1007', '1029', '1003_1007', '1003_1007_1029', '2017-11-04 19:39:48');
INSERT INTO `room_data_detail` VALUES ('193', '14', '1002', '1005', '1021', '1002_1005', '1002_1005_1021', '2017-11-04 19:39:48');
INSERT INTO `room_data_detail` VALUES ('194', '14', '1001', '1002', '1006', '1001_1002', '1001_1002_1006', '2017-11-04 19:39:48');
INSERT INTO `room_data_detail` VALUES ('195', '14', '1004', '1009', '1038', '1004_1009', '1004_1009_1038', '2017-11-04 19:39:48');
INSERT INTO `room_data_detail` VALUES ('196', '14', '1006', '1012', '1046', '1006_1012', '1006_1012_1046', '2017-11-04 19:39:48');
INSERT INTO `room_data_detail` VALUES ('197', '14', '1009', '1017', '1073', '1009_1017', '1009_1017_1073', '2017-11-04 19:39:48');
INSERT INTO `room_data_detail` VALUES ('198', '14', '1008', '1016', '1068', '1008_1016', '1008_1016_1068', '2017-11-04 19:39:48');
INSERT INTO `room_data_detail` VALUES ('199', '14', '1007', '1014', '1051', '1007_1014', '1007_1014_1051', '2017-11-04 19:39:48');
INSERT INTO `room_data_detail` VALUES ('200', '14', '1007', '1015', '1063', '1007_1015', '1007_1015_1063', '2017-11-04 19:39:48');
INSERT INTO `room_data_detail` VALUES ('201', '14', '1009', '1018', '1079', '1009_1018', '1009_1018_1079', '2017-11-04 19:39:48');
INSERT INTO `room_data_detail` VALUES ('202', '14', '1011', '1021', '1099', '1011_1021', '1011_1021_1099', '2017-11-04 19:39:48');
INSERT INTO `room_data_detail` VALUES ('203', '14', '1011', '1021', '1102', '1011_1021', '1011_1021_1102', '2017-11-04 19:39:48');
INSERT INTO `room_data_detail` VALUES ('204', '14', '1011', '1021', '1105', '1011_1021', '1011_1021_1105', '2017-11-04 19:39:48');
INSERT INTO `room_data_detail` VALUES ('209', '14', '1001', '1001', '1001', '1001_1001', '1001_1001_1001', '2017-11-04 20:14:24');

-- ----------------------------
-- Table structure for room_data_module
-- ----------------------------
DROP TABLE IF EXISTS `room_data_module`;
CREATE TABLE `room_data_module` (
  `ID` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '序列编号',
  `MODULE_NAME` varchar(255) DEFAULT NULL COMMENT '模块名称',
  `MODULE_TYPE` varchar(8) DEFAULT NULL COMMENT '模块分类：1.装饰装修；2.设备；3.耗材；9.其它',
  `MODULE_CODE` varchar(255) DEFAULT NULL COMMENT '模块简码',
  `NAME_SPELLING` varchar(255) DEFAULT NULL COMMENT '名称拼音',
  `NAME_CODE` varchar(255) DEFAULT NULL COMMENT '名称简拼',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `NOTE` varchar(255) DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1017 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of room_data_module
-- ----------------------------
INSERT INTO `room_data_module` VALUES ('1001', '天花', '1', 'TH', null, null, null, null);
INSERT INTO `room_data_module` VALUES ('1002', '地面', '1', 'DM', null, null, null, null);
INSERT INTO `room_data_module` VALUES ('1003', '墙面及踢脚', '1', 'QM', null, null, null, null);
INSERT INTO `room_data_module` VALUES ('1004', '门窗', '1', 'MC', null, null, null, null);
INSERT INTO `room_data_module` VALUES ('1005', '五金', '1', 'WJ', null, null, null, null);
INSERT INTO `room_data_module` VALUES ('1006', '屏蔽', '1', 'PB', null, null, null, null);
INSERT INTO `room_data_module` VALUES ('1007', '给水', '1', 'GS', null, null, null, null);
INSERT INTO `room_data_module` VALUES ('1008', '排水', '1', 'PS', null, null, null, null);
INSERT INTO `room_data_module` VALUES ('1009', '医疗气体', '1', 'YLQT', null, null, null, null);
INSERT INTO `room_data_module` VALUES ('1010', '电气及照明', '1', 'DQ', null, null, null, null);
INSERT INTO `room_data_module` VALUES ('1011', '弱电信息及物流系统', '1', 'RD', null, null, null, null);
INSERT INTO `room_data_module` VALUES ('1012', '通风空调系统', '1', 'TF', '', null, null, null);
INSERT INTO `room_data_module` VALUES ('1013', '医疗设备及显示屏', '1', 'YLSB', null, null, null, null);
INSERT INTO `room_data_module` VALUES ('1014', '非医疗设备', '1', 'FYLSB', null, null, null, null);
INSERT INTO `room_data_module` VALUES ('1015', '固定家具', '1', 'GDJJ', null, null, null, null);
INSERT INTO `room_data_module` VALUES ('1016', '活动家具', '1', 'HDJJ', null, null, null, null);

-- ----------------------------
-- Table structure for room_enum_param
-- ----------------------------
DROP TABLE IF EXISTS `room_enum_param`;
CREATE TABLE `room_enum_param` (
  `ROOM_ID` bigint(12) NOT NULL COMMENT '房间编号',
  `MODULE_ID` bigint(12) NOT NULL COMMENT '模块编号',
  `ENUM_ID` bigint(12) NOT NULL COMMENT '模块参数',
  `ENUM_PARAM_ID` bigint(12) NOT NULL COMMENT '参数规格',
  `ENUM_PARAM_STATUS` varchar(4) DEFAULT NULL COMMENT '参数状态:1.选中；2.未选中；',
  `ENUM_PARAM_NAME` varchar(255) DEFAULT NULL COMMENT '枚举参数名称',
  `ENUM_PARAM_CODE` varchar(255) DEFAULT NULL COMMENT '枚举参数简码',
  `ORDER_IDX` smallint(6) DEFAULT NULL COMMENT '顺序编号',
  `NAME_SPELLING` varchar(255) DEFAULT NULL COMMENT '名称拼音',
  `NAME_CODE` varchar(255) DEFAULT NULL COMMENT '名称简拼',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of room_enum_param
-- ----------------------------

-- ----------------------------
-- Table structure for room_info
-- ----------------------------
DROP TABLE IF EXISTS `room_info`;
CREATE TABLE `room_info` (
  `ID` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '序列编号',
  `PROJ_ID` bigint(12) DEFAULT NULL COMMENT '项目编号',
  `DEPT_TYPE_ID` bigint(12) DEFAULT NULL COMMENT '部门分类编号',
  `DEPT_ID` bigint(12) DEFAULT NULL COMMENT '部门编号',
  `AREA_ID` bigint(12) DEFAULT NULL COMMENT '区域编号',
  `ROOM_NAME` varchar(255) DEFAULT NULL COMMENT '房间名称',
  `ROOM_CODE` varchar(16) DEFAULT NULL COMMENT '房间编码',
  `NAME_SPELLING` varchar(255) DEFAULT NULL COMMENT '名称拼音',
  `NAME_CODE` varchar(255) DEFAULT NULL COMMENT '名称简拼',
  `ORDER_IDX` smallint(6) DEFAULT NULL COMMENT '顺序编号',
  `ORDER_NAME` varchar(32) DEFAULT NULL COMMENT '完整编号',
  `PLAN_CNT` int(11) DEFAULT NULL COMMENT '规划数量',
  `PLAN_AREA_TOTAL` double(8,2) DEFAULT NULL COMMENT '规划面积小计',
  `PLAN_AREA_SUMMARY` double(8,2) DEFAULT NULL COMMENT '规划面积合计',
  `DESIGN_CNT` int(11) DEFAULT NULL COMMENT '设计数量',
  `DESIGN_AREA_TOTAL` double(8,2) DEFAULT NULL COMMENT '设计面积小计',
  `DESIGN_AREA_SUMMARY` double(8,2) DEFAULT NULL COMMENT '设计面积合计',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  `NOTE` varchar(255) DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of room_info
-- ----------------------------
INSERT INTO `room_info` VALUES ('5', '329', '2465', '108', '0', '12', '12', null, null, null, null, null, null, null, '12', '120.00', '10.00', '2017-10-26 20:25:38', null, null);
INSERT INTO `room_info` VALUES ('6', '329', '2465', '108', '0', 'as', 'ad', null, null, null, null, null, null, null, '11', '132.00', '12.00', '2017-10-26 20:38:25', null, null);
INSERT INTO `room_info` VALUES ('8', '329', '2465', '108', '2', 'aa', 'aa', null, null, null, null, null, null, null, '15', '225.00', '15.00', '2017-10-26 20:48:20', null, null);
INSERT INTO `room_info` VALUES ('10', '331', '2481', '116', '4', '测试房间', 'P-01-01-01', null, null, '1', null, null, null, null, '12', '12.00', '1.00', '2017-10-29 21:20:04', null, null);
INSERT INTO `room_info` VALUES ('11', '331', '2481', '116', '4', 'HELLO', 'P-01-01-02', null, null, '2', null, null, null, null, '2', '246.00', '123.00', '2017-11-04 18:54:00', null, null);
INSERT INTO `room_info` VALUES ('12', '331', '2481', '116', '4', '新的房间', 'P-01-01-03', null, null, '3', null, null, null, null, '2', '6.00', '3.00', '2017-11-04 18:54:30', null, null);
INSERT INTO `room_info` VALUES ('13', '331', '2481', '120', '5', 'aaa', 'P.04.01.01', null, null, '1', null, null, null, null, '12', '252.00', '21.00', '2017-11-04 19:13:51', null, null);
INSERT INTO `room_info` VALUES ('14', '331', '2481', '118', '7', '123', 'P-03.01.01', null, null, '1', null, null, null, null, '11', '121.00', '11.00', '2017-11-04 19:39:04', null, null);

-- ----------------------------
-- Table structure for room_module
-- ----------------------------
DROP TABLE IF EXISTS `room_module`;
CREATE TABLE `room_module` (
  `ROOM_ID` bigint(12) NOT NULL COMMENT '房间编号',
  `MODULE_ID` bigint(12) NOT NULL COMMENT '模块编号',
  `MODULE_NAME` varchar(255) DEFAULT NULL COMMENT '模块名称',
  `MODULE_TYPE` varchar(8) DEFAULT NULL COMMENT '模块分类：1.装饰装修；2.设备；3.耗材；9.其它',
  `MODULE_CODE` varchar(255) DEFAULT NULL COMMENT '模块简码',
  `NAME_SPELLING` varchar(255) DEFAULT NULL COMMENT '名称拼音',
  `NAME_CODE` varchar(255) DEFAULT NULL COMMENT '名称简拼',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of room_module
-- ----------------------------

-- ----------------------------
-- Table structure for room_module_enum
-- ----------------------------
DROP TABLE IF EXISTS `room_module_enum`;
CREATE TABLE `room_module_enum` (
  `ROOM_ID` bigint(12) NOT NULL COMMENT '房间编号',
  `MODULE_ID` bigint(12) NOT NULL COMMENT '模块编号',
  `ENUM_ID` bigint(12) NOT NULL COMMENT '模块枚举编号',
  `ENUM_NAME` varchar(255) DEFAULT NULL COMMENT '枚举名称',
  `ENUM_CODE` varchar(255) DEFAULT NULL COMMENT '枚举简码',
  `ORDER_IDX` smallint(6) DEFAULT NULL COMMENT '顺序编号',
  `NAME_SPELLING` varchar(255) DEFAULT NULL COMMENT '名称拼音',
  `NAME_CODE` varchar(255) DEFAULT NULL COMMENT '名称简拼',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of room_module_enum
-- ----------------------------

-- ----------------------------
-- Table structure for sm_param_detail
-- ----------------------------
DROP TABLE IF EXISTS `sm_param_detail`;
CREATE TABLE `sm_param_detail` (
  `TYPE_CODE` varchar(255) NOT NULL COMMENT '参数名称（编码）',
  `PARAM_CODE` varchar(255) NOT NULL COMMENT '参数值',
  `PARAM_DESC` varchar(255) DEFAULT NULL COMMENT '参数值说明',
  `PARENT_TYPE_CODE` varchar(255) DEFAULT NULL COMMENT '上级参数编码',
  `PARENT_PARAM_CODE` varchar(255) DEFAULT NULL COMMENT '上级参数值',
  `STATUS` varchar(4) DEFAULT NULL COMMENT 'Y:有效 | N:无效',
  `SYSTEM_ID` varchar(8) DEFAULT NULL,
  `PARAM_ORDER` smallint(6) DEFAULT NULL,
  `LANGUAGE_TYPE` varchar(8) NOT NULL,
  `OPER_ID` varchar(8) DEFAULT NULL,
  `MODIFY_TIME` datetime DEFAULT NULL,
  PRIMARY KEY (`TYPE_CODE`,`PARAM_CODE`,`LANGUAGE_TYPE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='参数明细配置';

-- ----------------------------
-- Records of sm_param_detail
-- ----------------------------
INSERT INTO `sm_param_detail` VALUES ('AREA_CODE', '110000', '北京市', '', null, 'Y', '', '1', 'zh_cn', '', null);
INSERT INTO `sm_param_detail` VALUES ('AREA_CODE', '120000', '天津市', '', null, 'Y', '', '2', 'zh_cn', '', null);
INSERT INTO `sm_param_detail` VALUES ('AREA_CODE', '130000', '河北省', '', null, 'Y', '', '3', 'zh_cn', '', null);
INSERT INTO `sm_param_detail` VALUES ('AREA_CODE', '140000', '山西省', '', null, 'Y', '', '4', 'zh_cn', '', null);
INSERT INTO `sm_param_detail` VALUES ('AREA_CODE', '150000', '内蒙古', '', null, 'Y', '', '5', 'zh_cn', '', null);
INSERT INTO `sm_param_detail` VALUES ('AREA_CODE', '210000', '辽宁省', '', null, 'Y', '', '6', 'zh_cn', '', null);
INSERT INTO `sm_param_detail` VALUES ('AREA_CODE', '220000', '吉林省', '', null, 'Y', '', '7', 'zh_cn', '', null);
INSERT INTO `sm_param_detail` VALUES ('AREA_CODE', '230000', '黑龙江', '', null, 'Y', '', '8', 'zh_cn', '', null);
INSERT INTO `sm_param_detail` VALUES ('AREA_CODE', '310000', '上海市', '', null, 'Y', '', '9', 'zh_cn', '', null);
INSERT INTO `sm_param_detail` VALUES ('AREA_CODE', '320000', '江苏省', '', null, 'Y', '', '10', 'zh_cn', '', null);
INSERT INTO `sm_param_detail` VALUES ('AREA_CODE', '330000', '浙江省', '', null, 'Y', '', '11', 'zh_cn', '', null);
INSERT INTO `sm_param_detail` VALUES ('AREA_CODE', '340000', '安徽省', '', null, 'Y', '', '12', 'zh_cn', '', null);
INSERT INTO `sm_param_detail` VALUES ('AREA_CODE', '350000', '福建省', '', null, 'Y', '', '13', 'zh_cn', '', null);
INSERT INTO `sm_param_detail` VALUES ('AREA_CODE', '360000', '江西省', '', null, 'Y', '', '14', 'zh_cn', '', null);
INSERT INTO `sm_param_detail` VALUES ('AREA_CODE', '370000', '山东省', '', null, 'Y', '', '15', 'zh_cn', '', null);
INSERT INTO `sm_param_detail` VALUES ('AREA_CODE', '410000', '河南省', '', null, 'Y', '', '16', 'zh_cn', '', null);
INSERT INTO `sm_param_detail` VALUES ('AREA_CODE', '420000', '湖北省', '', null, 'Y', '', '17', 'zh_cn', '', null);
INSERT INTO `sm_param_detail` VALUES ('AREA_CODE', '430000', '湖南省', '', null, 'Y', '', '18', 'zh_cn', '', null);
INSERT INTO `sm_param_detail` VALUES ('AREA_CODE', '440000', '广东省', '', null, 'Y', '', '19', 'zh_cn', '', null);
INSERT INTO `sm_param_detail` VALUES ('AREA_CODE', '450000', '广西', '', null, 'Y', '', '20', 'zh_cn', '', null);
INSERT INTO `sm_param_detail` VALUES ('AREA_CODE', '460000', '海南省', '', null, 'Y', '', '21', 'zh_cn', '', null);
INSERT INTO `sm_param_detail` VALUES ('AREA_CODE', '500000', '重庆市', '', null, 'Y', '', '22', 'zh_cn', '', null);
INSERT INTO `sm_param_detail` VALUES ('AREA_CODE', '510000', '四川省', '', null, 'Y', '', '23', 'zh_cn', '', null);
INSERT INTO `sm_param_detail` VALUES ('AREA_CODE', '520000', '贵州省', '', null, 'Y', '', '24', 'zh_cn', '', null);
INSERT INTO `sm_param_detail` VALUES ('AREA_CODE', '530000', '云南省', '', null, 'Y', '', '25', 'zh_cn', '', null);
INSERT INTO `sm_param_detail` VALUES ('AREA_CODE', '540000', '西藏', '', null, 'Y', '', '26', 'zh_cn', '', null);
INSERT INTO `sm_param_detail` VALUES ('AREA_CODE', '610000', '陕西省', '', null, 'Y', '', '27', 'zh_cn', '', null);
INSERT INTO `sm_param_detail` VALUES ('AREA_CODE', '620000', '甘肃省', '', null, 'Y', '', '28', 'zh_cn', '', null);
INSERT INTO `sm_param_detail` VALUES ('AREA_CODE', '630000', '青海省', '', null, 'Y', '', '29', 'zh_cn', '', null);
INSERT INTO `sm_param_detail` VALUES ('AREA_CODE', '640000', '宁夏', '', null, 'Y', '', '30', 'zh_cn', '', null);
INSERT INTO `sm_param_detail` VALUES ('AREA_CODE', '650000', '新疆', '', null, 'Y', '', '31', 'zh_cn', '', null);
INSERT INTO `sm_param_detail` VALUES ('AREA_CODE', '660000', '新疆兵团', '', null, 'Y', '', '32', 'zh_cn', '', null);
INSERT INTO `sm_param_detail` VALUES ('DEPT_TYPE', 'A', '急诊部', null, null, 'Y', null, '2', 'zh_cn', null, null);
INSERT INTO `sm_param_detail` VALUES ('DEPT_TYPE', 'AD', '行政部', null, null, 'Y', null, '6', 'zh_cn', null, null);
INSERT INTO `sm_param_detail` VALUES ('DEPT_TYPE', 'C', '门诊部', null, null, 'Y', null, '3', 'zh_cn', null, null);
INSERT INTO `sm_param_detail` VALUES ('DEPT_TYPE', 'D', '医技部', null, null, 'Y', null, '5', 'zh_cn', null, null);
INSERT INTO `sm_param_detail` VALUES ('DEPT_TYPE', 'I', '住院部', null, null, 'Y', null, '4', 'zh_cn', null, null);
INSERT INTO `sm_param_detail` VALUES ('DEPT_TYPE', 'M', '机房', null, null, 'Y', null, '8', 'zh_cn', null, null);
INSERT INTO `sm_param_detail` VALUES ('DEPT_TYPE', 'P', '公共部门', null, null, 'Y', null, '1', 'zh_cn', null, null);
INSERT INTO `sm_param_detail` VALUES ('DEPT_TYPE', 'S', '后勤支持', null, null, 'Y', null, '7', 'zh_cn', null, null);
INSERT INTO `sm_param_detail` VALUES ('PROJ_PHASE', '1', '概念阶段', null, null, 'Y', null, '1', 'zh_cn', null, null);
INSERT INTO `sm_param_detail` VALUES ('PROJ_PHASE', '2', '方案阶段', null, null, 'Y', null, '2', 'zh_cn', null, null);
INSERT INTO `sm_param_detail` VALUES ('PROJ_PHASE', '3', '设计阶段', null, null, 'Y', null, '3', 'zh_cn', null, null);
INSERT INTO `sm_param_detail` VALUES ('PROJ_PHASE', '4', '施工阶段', null, null, 'Y', null, '4', 'zh_cn', null, null);

-- ----------------------------
-- Table structure for spec_data_detail
-- ----------------------------
DROP TABLE IF EXISTS `spec_data_detail`;
CREATE TABLE `spec_data_detail` (
  `ID` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '序列编号',
  `SPEC_ROOM_ID` bigint(12) DEFAULT NULL COMMENT '样板房间编号',
  `MODULE_ID` bigint(12) NOT NULL COMMENT '模块编号',
  `ENUM_ID` bigint(12) NOT NULL COMMENT '枚举编号',
  `PARAM_ID` bigint(12) NOT NULL COMMENT '参数编号',
  `MODULE_ENUM` varchar(255) DEFAULT NULL COMMENT '名称拼音',
  `MODULE_PARAM` varchar(255) DEFAULT NULL COMMENT '名称简拼',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=93 DEFAULT CHARSET=utf8 COMMENT='样板房间的参数配置表，用来创建样板房间，用来提前创建适用于不同类型的样板房间数据，以便于快速初始化房间数据';

-- ----------------------------
-- Records of spec_data_detail
-- ----------------------------
INSERT INTO `spec_data_detail` VALUES ('59', '6', '1013', '1026', '1126', '1013_1026', '1013_1026_1126');
INSERT INTO `spec_data_detail` VALUES ('60', '6', '1013', '1026', '1129', '1013_1026', '1013_1026_1129');
INSERT INTO `spec_data_detail` VALUES ('61', '6', '1013', '1026', '1132', '1013_1026', '1013_1026_1132');
INSERT INTO `spec_data_detail` VALUES ('68', '4', '1004', '1009', '1039', '1004_1009', '1004_1009_1039');
INSERT INTO `spec_data_detail` VALUES ('69', '4', '1007', '1015', '1064', '1007_1015', '1007_1015_1064');
INSERT INTO `spec_data_detail` VALUES ('72', '6', '1002', '1004', '1013', '1002_1004', '1002_1004_1013');
INSERT INTO `spec_data_detail` VALUES ('73', '6', '1003', '1006', '1022', '1003_1006', '1003_1006_1022');
INSERT INTO `spec_data_detail` VALUES ('74', '6', '1003', '1007', '1029', '1003_1007', '1003_1007_1029');
INSERT INTO `spec_data_detail` VALUES ('75', '6', '1002', '1005', '1021', '1002_1005', '1002_1005_1021');
INSERT INTO `spec_data_detail` VALUES ('76', '6', '1001', '1002', '1006', '1001_1002', '1001_1002_1006');
INSERT INTO `spec_data_detail` VALUES ('77', '6', '1004', '1009', '1038', '1004_1009', '1004_1009_1038');
INSERT INTO `spec_data_detail` VALUES ('78', '6', '1006', '1012', '1046', '1006_1012', '1006_1012_1046');
INSERT INTO `spec_data_detail` VALUES ('79', '6', '1009', '1017', '1073', '1009_1017', '1009_1017_1073');
INSERT INTO `spec_data_detail` VALUES ('80', '6', '1008', '1016', '1068', '1008_1016', '1008_1016_1068');
INSERT INTO `spec_data_detail` VALUES ('81', '6', '1007', '1014', '1051', '1007_1014', '1007_1014_1051');
INSERT INTO `spec_data_detail` VALUES ('82', '6', '1007', '1015', '1063', '1007_1015', '1007_1015_1063');
INSERT INTO `spec_data_detail` VALUES ('83', '6', '1009', '1018', '1079', '1009_1018', '1009_1018_1079');
INSERT INTO `spec_data_detail` VALUES ('84', '6', '1011', '1021', '1099', '1011_1021', '1011_1021_1099');
INSERT INTO `spec_data_detail` VALUES ('85', '6', '1011', '1021', '1102', '1011_1021', '1011_1021_1102');
INSERT INTO `spec_data_detail` VALUES ('86', '6', '1011', '1021', '1105', '1011_1021', '1011_1021_1105');
INSERT INTO `spec_data_detail` VALUES ('91', '4', '1001', '1001', '1005', '1001_1001', '1001_1001_1005');
INSERT INTO `spec_data_detail` VALUES ('92', '6', '1001', '1001', '1001', '1001_1001', '1001_1001_1001');

-- ----------------------------
-- Table structure for spec_room_data
-- ----------------------------
DROP TABLE IF EXISTS `spec_room_data`;
CREATE TABLE `spec_room_data` (
  `ID` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '序列编号',
  `SPEC_ROOM_NAME` varchar(255) DEFAULT NULL COMMENT '样板房间名称',
  `DEPT_TYPE_CODE` varchar(255) NOT NULL COMMENT '适用的部门类别',
  `DEPT_TYPE_NAME` varchar(255) DEFAULT NULL COMMENT '适用的部门类型名称',
  `DEPT_ID` bigint(12) DEFAULT NULL COMMENT '适用的部门编号',
  `DEPT_NAME` varchar(255) DEFAULT NULL COMMENT '适用的部门名称',
  `NAME_SPELLING` varchar(255) DEFAULT NULL COMMENT '名称拼音',
  `NAME_CODE` varchar(255) DEFAULT NULL COMMENT '名称简拼',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '修改时间',
  `NOTE` varchar(255) DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='样板房间的参数配置表，用来创建样板房间，用来提前创建适用于不同类型的样板房间数据，以便于快速初始化房间数据';

-- ----------------------------
-- Records of spec_room_data
-- ----------------------------
INSERT INTO `spec_room_data` VALUES ('4', '修改掉AA这个值', 'A', '急诊部', null, null, null, null, '2017-11-03 21:24:43', '2017-11-04 19:48:03', 'AAa');
INSERT INTO `spec_room_data` VALUES ('6', '测试样板房间', 'A', '急诊部', null, null, null, null, '2017-11-04 12:07:17', '2017-11-04 19:49:23', '111');
INSERT INTO `spec_room_data` VALUES ('9', '新的样板房间数据', 'C', '门诊部', null, null, null, null, '2017-11-04 15:57:17', '2017-11-04 19:49:30', '测试ABC21');

-- ----------------------------
-- Table structure for version_info
-- ----------------------------
DROP TABLE IF EXISTS `version_info`;
CREATE TABLE `version_info` (
  `ID` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '序列编号',
  `VER_NAME` varchar(255) DEFAULT NULL COMMENT '版本名称',
  `VER_CODE` varchar(8) DEFAULT NULL COMMENT '版本简称',
  `MAJOR` varchar(8) DEFAULT NULL COMMENT '主版本',
  `SLAVE` varchar(8) DEFAULT NULL COMMENT '次版本',
  `EXT` varchar(8) DEFAULT NULL COMMENT '扩展',
  `VER_INFO` varchar(64) DEFAULT NULL COMMENT '版本信息，包含简称和两级编号',
  `VER_STATUS` varchar(4) DEFAULT NULL COMMENT '版本状态：1.使用中；2.已删除',
  `VER_TYPE` varchar(8) DEFAULT NULL COMMENT '版本属性：1.普通版本；2.里程碑',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `OPER_CODE` varchar(32) DEFAULT NULL COMMENT '操作员',
  `NOE` varchar(255) DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=420 DEFAULT CHARSET=utf8 COMMENT='版本信息';

-- ----------------------------
-- Records of version_info
-- ----------------------------
INSERT INTO `version_info` VALUES ('415', '项目版本', 'V', '01', null, null, 'V01', '1', '1', '2017-10-22 14:55:29', null, null);
INSERT INTO `version_info` VALUES ('416', '项目版本', 'V', '01', null, null, 'V01', '1', '1', '2017-10-28 15:29:19', null, null);
INSERT INTO `version_info` VALUES ('417', '项目版本', 'V', '01', null, null, 'V01', '1', '1', '2017-10-28 15:29:30', null, null);
INSERT INTO `version_info` VALUES ('418', '项目版本', 'V', '01', null, null, 'V01', '1', '1', '2017-10-28 15:54:30', null, null);
INSERT INTO `version_info` VALUES ('419', '项目版本', 'V', '01', null, null, 'V01', '1', '1', '2017-11-04 19:51:03', null, null);

-- ----------------------------
-- View structure for area_view
-- ----------------------------
DROP VIEW IF EXISTS `area_view`;
CREATE ALGORITHM=UNDEFINED DEFINER=`hdtool`@`%` SQL SECURITY DEFINER VIEW `area_view` AS select `a`.`ID` AS `ID`,`a`.`PID` AS `PID`,`a`.`LEVEL` AS `LEVEL`,`a`.`AREA_NAME` AS `AREA_NAME`,`a`.`AREA_CODE` AS `AREA_CODE`,`a`.`PLAN_CNT` AS `PLAN_CNT`,`a`.`PLAN_AREA_TOTAL` AS `PLAN_AREA_TOTAL`,`a`.`PLAN_AREA_SUMMARY` AS `PLAN_AREA_SUMMARY`,`a`.`DESIGN_CNT` AS `DESIGN_CNT`,`a`.`DESIGN_AREA_TOTAL` AS `DESIGN_AREA_TOTAL`,`a`.`DESIGN_AREA_SUMMARY` AS `DESIGN_AREA_SUMMARY`,`a`.`NOTE` AS `NOTE`,`a`.`PROJ_ID` AS `PROJ_ID`,`a`.`DEPT_TYPE_ID` AS `DEPT_TYPE_ID`,`a`.`DEPT_ID` AS `DEPT_ID` from (select `hdtool`.`area_info`.`ID` AS `ID`,0 AS `PID`,1 AS `LEVEL`,`hdtool`.`area_info`.`OFFICE_NAME` AS `AREA_NAME`,`hdtool`.`area_info`.`OFFICE_CODE` AS `AREA_CODE`,`hdtool`.`area_info`.`PLAN_CNT` AS `PLAN_CNT`,`hdtool`.`area_info`.`PLAN_AREA_TOTAL` AS `PLAN_AREA_TOTAL`,`hdtool`.`area_info`.`PLAN_AREA_SUMMARY` AS `PLAN_AREA_SUMMARY`,`hdtool`.`area_info`.`DESIGN_CNT` AS `DESIGN_CNT`,`hdtool`.`area_info`.`DESIGN_AREA_TOTAL` AS `DESIGN_AREA_TOTAL`,`hdtool`.`area_info`.`DESIGN_AREA_SUMMARY` AS `DESIGN_AREA_SUMMARY`,`hdtool`.`area_info`.`NOTE` AS `NOTE`,`hdtool`.`area_info`.`PROJ_ID` AS `PROJ_ID`,`hdtool`.`area_info`.`DEPT_TYPE_ID` AS `DEPT_TYPE_ID`,`hdtool`.`area_info`.`DEPT_ID` AS `DEPT_ID` from `hdtool`.`area_info` union all select `hdtool`.`room_info`.`ID` AS `ID`,`hdtool`.`room_info`.`AREA_ID` AS `PID`,2 AS `LEVEL`,`hdtool`.`room_info`.`ROOM_NAME` AS `AREA_NAME`,`hdtool`.`room_info`.`ROOM_CODE` AS `AREA_CODE`,`hdtool`.`room_info`.`PLAN_CNT` AS `PLAN_CNT`,`hdtool`.`room_info`.`PLAN_AREA_TOTAL` AS `PLAN_AREA_TOTAL`,`hdtool`.`room_info`.`PLAN_AREA_SUMMARY` AS `PLAN_AREA_SUMMARY`,`hdtool`.`room_info`.`DESIGN_CNT` AS `DESIGN_CNT`,`hdtool`.`room_info`.`DESIGN_AREA_TOTAL` AS `DESIGN_AREA_TOTAL`,`hdtool`.`room_info`.`DESIGN_AREA_SUMMARY` AS `DESIGN_AREA_SUMMARY`,`hdtool`.`room_info`.`NOTE` AS `NOTE`,`hdtool`.`room_info`.`PROJ_ID` AS `PROJ_ID`,`hdtool`.`room_info`.`DEPT_TYPE_ID` AS `DEPT_TYPE_ID`,`hdtool`.`room_info`.`DEPT_ID` AS `DEPT_ID` from `hdtool`.`room_info`) `a` order by `a`.`LEVEL`,`a`.`ID` ;

-- ----------------------------
-- View structure for dept_view
-- ----------------------------
DROP VIEW IF EXISTS `dept_view`;
CREATE ALGORITHM=UNDEFINED DEFINER=`hdtool`@`%` SQL SECURITY DEFINER VIEW `dept_view` AS select `a`.`ID` AS `ID`,`a`.`PID` AS `PID`,`a`.`LEVEL` AS `LEVEL`,`a`.`DEPT_NAME` AS `DEPT_NAME`,`a`.`DEPT_CODE` AS `DEPT_CODE`,`a`.`PLAN_AREA` AS `PLAN_AREA`,`a`.`DESIGN_AREA` AS `DESIGN_AREA`,`a`.`PLAN_AREA_PERSENT` AS `PLAN_AREA_PERSENT`,`a`.`DESIGN_AREA_PERSENT` AS `DESIGN_AREA_PERSENT`,`a`.`NOTE` AS `NOTE`,`a`.`order_idx` AS `order_idx`,`a`.`proj_id` AS `proj_id` from (select `hdtool`.`dept_type`.`ID` AS `ID`,'0' AS `PID`,'1' AS `LEVEL`,`hdtool`.`dept_type`.`DEPT_NAME` AS `DEPT_NAME`,`hdtool`.`dept_type`.`DEPT_CODE` AS `DEPT_CODE`,`hdtool`.`dept_type`.`PLAN_AREA` AS `PLAN_AREA`,`hdtool`.`dept_type`.`DESIGN_AREA` AS `DESIGN_AREA`,`hdtool`.`dept_type`.`PLAN_AREA_PERSENT` AS `PLAN_AREA_PERSENT`,`hdtool`.`dept_type`.`DESIGN_AREA_PERSENT` AS `DESIGN_AREA_PERSENT`,`hdtool`.`dept_type`.`NOTE` AS `NOTE`,`hdtool`.`dept_type`.`ORDER_IDX` AS `order_idx`,`hdtool`.`dept_type`.`PROJ_ID` AS `proj_id` from `hdtool`.`dept_type` union all select `hdtool`.`department`.`ID` AS `ID`,`hdtool`.`department`.`DEPT_TYPE_ID` AS `PID`,'2' AS `LEVEL`,`hdtool`.`department`.`DEPT_NAME` AS `DEPT_NAME`,`hdtool`.`department`.`DEPT_CODE` AS `DEPT_CODE`,`hdtool`.`department`.`PLAN_AREA` AS `PLAN_AREA`,`hdtool`.`department`.`DESIGN_AREA` AS `DESIGN_AREA`,`hdtool`.`department`.`PLAN_AREA_PERSENT` AS `PLAN_AREA_PERSENT`,`hdtool`.`department`.`DESIGN_AREA_PERSENT` AS `DESIGN_AREA_PERSENT`,`hdtool`.`department`.`NOTE` AS `NOTE`,`hdtool`.`department`.`ORDER_IDX` AS `order_idx`,`hdtool`.`department`.`PROJ_ID` AS `proj_id` from `hdtool`.`department`) `a` order by `a`.`PID`,`a`.`ID` ;

-- ----------------------------
-- View structure for sm_sys_dict
-- ----------------------------
DROP VIEW IF EXISTS `sm_sys_dict`;
CREATE ALGORITHM=UNDEFINED DEFINER=`hdtool`@`%` SQL SECURITY DEFINER VIEW `sm_sys_dict` AS select `a`.`TYPE_CODE` AS `TYPE_CODE`,`a`.`PARAM_CODE` AS `PARAM_CODE`,`a`.`PARAM_DESC` AS `PARAM_DESC`,`a`.`PARENT_TYPE_CODE` AS `PARENT_TYPE_CODE`,`a`.`PARENT_PARAM_CODE` AS `PARENT_PARAM_CODE`,`a`.`LANGUAGE_TYPE` AS `LANGUAGE_TYPE` from (select `hdtool`.`sm_param_detail`.`TYPE_CODE` AS `TYPE_CODE`,`hdtool`.`sm_param_detail`.`PARAM_CODE` AS `PARAM_CODE`,`hdtool`.`sm_param_detail`.`PARAM_DESC` AS `PARAM_DESC`,`hdtool`.`sm_param_detail`.`PARENT_TYPE_CODE` AS `PARENT_TYPE_CODE`,`hdtool`.`sm_param_detail`.`PARENT_PARAM_CODE` AS `PARENT_PARAM_CODE`,`hdtool`.`sm_param_detail`.`LANGUAGE_TYPE` AS `LANGUAGE_TYPE` from `hdtool`.`sm_param_detail` where (`hdtool`.`sm_param_detail`.`STATUS` = 'Y')) `a` order by `a`.`TYPE_CODE`,`a`.`PARAM_CODE` ;

-- ----------------------------
-- Function structure for getChildLst
-- ----------------------------
DROP FUNCTION IF EXISTS `getChildLst`;
DELIMITER ;;
CREATE DEFINER=`hdtool`@`%` FUNCTION `getChildLst`(rootId varchar(255), projId varchar(255)) RETURNS varchar(10000) CHARSET utf8
BEGIN  
  DECLARE sTemp VARCHAR(1000);  
  DECLARE sTempChd VARCHAR(1000);  
 
  SET sTemp = '$';  
  SET sTempChd =rootId;  
 
  WHILE sTempChd is not null DO  
    SET sTemp = concat(sTemp,',',sTempChd);  
    SELECT group_concat(id) INTO sTempChd FROM dept_view where FIND_IN_SET(pid,sTempChd)>0;  
  END WHILE;  
  RETURN sTemp;  
END
;;
DELIMITER ;
