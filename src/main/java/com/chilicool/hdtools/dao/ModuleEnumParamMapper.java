package com.chilicool.hdtools.dao;

import com.chilicool.hdtools.domain.ModuleEnumParam;
import com.chilicool.hdtools.domain.ModuleEnumParamExample;
import java.util.List;
import java.util.Map;

import com.chilicool.hdtools.model.RoomParamJson;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface ModuleEnumParamMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table module_enum_param
     *
     * @mbggenerated
     */
    int countByExample(ModuleEnumParamExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table module_enum_param
     *
     * @mbggenerated
     */
    int deleteByExample(ModuleEnumParamExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table module_enum_param
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table module_enum_param
     *
     * @mbggenerated
     */
    int insert(ModuleEnumParam record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table module_enum_param
     *
     * @mbggenerated
     */
    int insertSelective(ModuleEnumParam record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table module_enum_param
     *
     * @mbggenerated
     */
    List<ModuleEnumParam> selectByExample(ModuleEnumParamExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table module_enum_param
     *
     * @mbggenerated
     */
    ModuleEnumParam selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table module_enum_param
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") ModuleEnumParam record, @Param("example") ModuleEnumParamExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table module_enum_param
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") ModuleEnumParam record, @Param("example") ModuleEnumParamExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table module_enum_param
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(ModuleEnumParam record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table module_enum_param
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(ModuleEnumParam record);

    /**
     * 获取所有参数信息
     *
     * @return
     */
    List<RoomParamJson> getAllEnumParamInfo(Map<String, Long> inParams);
}