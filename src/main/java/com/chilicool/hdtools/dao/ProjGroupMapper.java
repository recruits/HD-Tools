package com.chilicool.hdtools.dao;

import com.chilicool.hdtools.domain.ProjGroup;
import com.chilicool.hdtools.domain.ProjGroupExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface ProjGroupMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table proj_group
     *
     * @mbggenerated
     */
    int countByExample(ProjGroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table proj_group
     *
     * @mbggenerated
     */
    int deleteByExample(ProjGroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table proj_group
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table proj_group
     *
     * @mbggenerated
     */
    int insert(ProjGroup record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table proj_group
     *
     * @mbggenerated
     */
    int insertSelective(ProjGroup record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table proj_group
     *
     * @mbggenerated
     */
    List<ProjGroup> selectByExample(ProjGroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table proj_group
     *
     * @mbggenerated
     */
    ProjGroup selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table proj_group
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") ProjGroup record, @Param("example") ProjGroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table proj_group
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") ProjGroup record, @Param("example") ProjGroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table proj_group
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(ProjGroup record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table proj_group
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(ProjGroup record);
}