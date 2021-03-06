package com.chilicool.hdtools.dao;

import com.chilicool.hdtools.domain.SmSysDict;
import com.chilicool.hdtools.domain.SmSysDictExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface SmSysDictMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sm_sys_dict
     *
     * @mbggenerated
     */
    int countByExample(SmSysDictExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sm_sys_dict
     *
     * @mbggenerated
     */
    int deleteByExample(SmSysDictExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sm_sys_dict
     *
     * @mbggenerated
     */
    int insert(SmSysDict record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sm_sys_dict
     *
     * @mbggenerated
     */
    int insertSelective(SmSysDict record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sm_sys_dict
     *
     * @mbggenerated
     */
    List<SmSysDict> selectByExample(SmSysDictExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sm_sys_dict
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") SmSysDict record, @Param("example") SmSysDictExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sm_sys_dict
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") SmSysDict record, @Param("example") SmSysDictExample example);

    List<SmSysDict> selectByRootTypeCode(Map<String,String> param);
}