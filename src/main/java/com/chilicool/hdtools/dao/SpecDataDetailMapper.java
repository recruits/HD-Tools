package com.chilicool.hdtools.dao;

import com.chilicool.hdtools.domain.SpecDataDetail;
import com.chilicool.hdtools.domain.SpecDataDetailExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface SpecDataDetailMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table spec_data_detail
     *
     * @mbggenerated
     */
    int countByExample(SpecDataDetailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table spec_data_detail
     *
     * @mbggenerated
     */
    int deleteByExample(SpecDataDetailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table spec_data_detail
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table spec_data_detail
     *
     * @mbggenerated
     */
    int insert(SpecDataDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table spec_data_detail
     *
     * @mbggenerated
     */
    int insertSelective(SpecDataDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table spec_data_detail
     *
     * @mbggenerated
     */
    List<SpecDataDetail> selectByExample(SpecDataDetailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table spec_data_detail
     *
     * @mbggenerated
     */
    SpecDataDetail selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table spec_data_detail
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") SpecDataDetail record, @Param("example") SpecDataDetailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table spec_data_detail
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") SpecDataDetail record, @Param("example") SpecDataDetailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table spec_data_detail
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(SpecDataDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table spec_data_detail
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(SpecDataDetail record);

    /**
     * 加载所有
     *
     * @param specRoomId
     * @return
     */
    List<String> loadCurrRoomDeail(Long specRoomId);
}