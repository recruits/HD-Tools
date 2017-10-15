package com.chilicool.hdtools.dao;

import com.chilicool.hdtools.domain.DataModuleEnumParam;
import com.chilicool.hdtools.domain.DataModuleEnumParamExample;
import java.util.List;
import java.util.Map;

import com.chilicool.hdtools.model.EnumParamSimp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface DataModuleEnumParamMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table data_module_enum_param
     *
     * @mbggenerated
     */
    int countByExample(DataModuleEnumParamExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table data_module_enum_param
     *
     * @mbggenerated
     */
    int deleteByExample(DataModuleEnumParamExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table data_module_enum_param
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table data_module_enum_param
     *
     * @mbggenerated
     */
    int insert(DataModuleEnumParam record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table data_module_enum_param
     *
     * @mbggenerated
     */
    int insertSelective(DataModuleEnumParam record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table data_module_enum_param
     *
     * @mbggenerated
     */
    List<DataModuleEnumParam> selectByExample(DataModuleEnumParamExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table data_module_enum_param
     *
     * @mbggenerated
     */
    DataModuleEnumParam selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table data_module_enum_param
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") DataModuleEnumParam record, @Param("example") DataModuleEnumParamExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table data_module_enum_param
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") DataModuleEnumParam record, @Param("example") DataModuleEnumParamExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table data_module_enum_param
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(DataModuleEnumParam record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table data_module_enum_param
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(DataModuleEnumParam record);

    List<EnumParamSimp> getEnumParamWithModuleAndEnumId(Map<String, Object> inParam);
}