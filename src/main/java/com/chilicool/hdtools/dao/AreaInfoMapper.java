package com.chilicool.hdtools.dao;

import com.chilicool.hdtools.domain.AreaInfo;
import com.chilicool.hdtools.domain.AreaInfoExample;
import java.util.List;
import java.util.Map;

import com.chilicool.hdtools.model.AreaInfoModel;
import com.chilicool.hdtools.model.PlanAreaModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface AreaInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table area_info
     *
     * @mbggenerated
     */
    int countByExample(AreaInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table area_info
     *
     * @mbggenerated
     */
    int deleteByExample(AreaInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table area_info
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table area_info
     *
     * @mbggenerated
     */
    int insert(AreaInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table area_info
     *
     * @mbggenerated
     */
    int insertSelective(AreaInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table area_info
     *
     * @mbggenerated
     */
    List<AreaInfo> selectByExample(AreaInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table area_info
     *
     * @mbggenerated
     */
    AreaInfo selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table area_info
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") AreaInfo record, @Param("example") AreaInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table area_info
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") AreaInfo record, @Param("example") AreaInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table area_info
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(AreaInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table area_info
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(AreaInfo record);

    /**
     * 分级查询区域信息
     * @param inParams
     * @return
     */
    List<AreaInfoModel> loadAllAreaInfoWithLevel(Map<String, Object> inParams);

    /**
     * 查询面积汇总信息
     *
     * @param inParams
     * @return
     */
    List<PlanAreaModel> loadAllDesignAreaTotalModel(Map<String, Object> inParams);

    /**
     * 查询部门下的所有区域信息
     *
     * @param deptId
     * @return
     */
    List<Long> loadAllAreaIdByDeptId(Long deptId);

    /**
     * 获取最大的区域编号
     *
     * @param deptId
     * @return
     */
    Short getMaxAreaCodeByDeptId(Long deptId);
}