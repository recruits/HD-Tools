package com.chilicool.hdtools.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VersionInfoExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table version_info
     *
     * @mbggenerated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table version_info
     *
     * @mbggenerated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table version_info
     *
     * @mbggenerated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table version_info
     *
     * @mbggenerated
     */
    public VersionInfoExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table version_info
     *
     * @mbggenerated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table version_info
     *
     * @mbggenerated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table version_info
     *
     * @mbggenerated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table version_info
     *
     * @mbggenerated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table version_info
     *
     * @mbggenerated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table version_info
     *
     * @mbggenerated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table version_info
     *
     * @mbggenerated
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table version_info
     *
     * @mbggenerated
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table version_info
     *
     * @mbggenerated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table version_info
     *
     * @mbggenerated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table version_info
     *
     * @mbggenerated
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andVerNameIsNull() {
            addCriterion("VER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andVerNameIsNotNull() {
            addCriterion("VER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andVerNameEqualTo(String value) {
            addCriterion("VER_NAME =", value, "verName");
            return (Criteria) this;
        }

        public Criteria andVerNameNotEqualTo(String value) {
            addCriterion("VER_NAME <>", value, "verName");
            return (Criteria) this;
        }

        public Criteria andVerNameGreaterThan(String value) {
            addCriterion("VER_NAME >", value, "verName");
            return (Criteria) this;
        }

        public Criteria andVerNameGreaterThanOrEqualTo(String value) {
            addCriterion("VER_NAME >=", value, "verName");
            return (Criteria) this;
        }

        public Criteria andVerNameLessThan(String value) {
            addCriterion("VER_NAME <", value, "verName");
            return (Criteria) this;
        }

        public Criteria andVerNameLessThanOrEqualTo(String value) {
            addCriterion("VER_NAME <=", value, "verName");
            return (Criteria) this;
        }

        public Criteria andVerNameLike(String value) {
            addCriterion("VER_NAME like", value, "verName");
            return (Criteria) this;
        }

        public Criteria andVerNameNotLike(String value) {
            addCriterion("VER_NAME not like", value, "verName");
            return (Criteria) this;
        }

        public Criteria andVerNameIn(List<String> values) {
            addCriterion("VER_NAME in", values, "verName");
            return (Criteria) this;
        }

        public Criteria andVerNameNotIn(List<String> values) {
            addCriterion("VER_NAME not in", values, "verName");
            return (Criteria) this;
        }

        public Criteria andVerNameBetween(String value1, String value2) {
            addCriterion("VER_NAME between", value1, value2, "verName");
            return (Criteria) this;
        }

        public Criteria andVerNameNotBetween(String value1, String value2) {
            addCriterion("VER_NAME not between", value1, value2, "verName");
            return (Criteria) this;
        }

        public Criteria andVerCodeIsNull() {
            addCriterion("VER_CODE is null");
            return (Criteria) this;
        }

        public Criteria andVerCodeIsNotNull() {
            addCriterion("VER_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andVerCodeEqualTo(String value) {
            addCriterion("VER_CODE =", value, "verCode");
            return (Criteria) this;
        }

        public Criteria andVerCodeNotEqualTo(String value) {
            addCriterion("VER_CODE <>", value, "verCode");
            return (Criteria) this;
        }

        public Criteria andVerCodeGreaterThan(String value) {
            addCriterion("VER_CODE >", value, "verCode");
            return (Criteria) this;
        }

        public Criteria andVerCodeGreaterThanOrEqualTo(String value) {
            addCriterion("VER_CODE >=", value, "verCode");
            return (Criteria) this;
        }

        public Criteria andVerCodeLessThan(String value) {
            addCriterion("VER_CODE <", value, "verCode");
            return (Criteria) this;
        }

        public Criteria andVerCodeLessThanOrEqualTo(String value) {
            addCriterion("VER_CODE <=", value, "verCode");
            return (Criteria) this;
        }

        public Criteria andVerCodeLike(String value) {
            addCriterion("VER_CODE like", value, "verCode");
            return (Criteria) this;
        }

        public Criteria andVerCodeNotLike(String value) {
            addCriterion("VER_CODE not like", value, "verCode");
            return (Criteria) this;
        }

        public Criteria andVerCodeIn(List<String> values) {
            addCriterion("VER_CODE in", values, "verCode");
            return (Criteria) this;
        }

        public Criteria andVerCodeNotIn(List<String> values) {
            addCriterion("VER_CODE not in", values, "verCode");
            return (Criteria) this;
        }

        public Criteria andVerCodeBetween(String value1, String value2) {
            addCriterion("VER_CODE between", value1, value2, "verCode");
            return (Criteria) this;
        }

        public Criteria andVerCodeNotBetween(String value1, String value2) {
            addCriterion("VER_CODE not between", value1, value2, "verCode");
            return (Criteria) this;
        }

        public Criteria andMajorIsNull() {
            addCriterion("MAJOR is null");
            return (Criteria) this;
        }

        public Criteria andMajorIsNotNull() {
            addCriterion("MAJOR is not null");
            return (Criteria) this;
        }

        public Criteria andMajorEqualTo(String value) {
            addCriterion("MAJOR =", value, "major");
            return (Criteria) this;
        }

        public Criteria andMajorNotEqualTo(String value) {
            addCriterion("MAJOR <>", value, "major");
            return (Criteria) this;
        }

        public Criteria andMajorGreaterThan(String value) {
            addCriterion("MAJOR >", value, "major");
            return (Criteria) this;
        }

        public Criteria andMajorGreaterThanOrEqualTo(String value) {
            addCriterion("MAJOR >=", value, "major");
            return (Criteria) this;
        }

        public Criteria andMajorLessThan(String value) {
            addCriterion("MAJOR <", value, "major");
            return (Criteria) this;
        }

        public Criteria andMajorLessThanOrEqualTo(String value) {
            addCriterion("MAJOR <=", value, "major");
            return (Criteria) this;
        }

        public Criteria andMajorLike(String value) {
            addCriterion("MAJOR like", value, "major");
            return (Criteria) this;
        }

        public Criteria andMajorNotLike(String value) {
            addCriterion("MAJOR not like", value, "major");
            return (Criteria) this;
        }

        public Criteria andMajorIn(List<String> values) {
            addCriterion("MAJOR in", values, "major");
            return (Criteria) this;
        }

        public Criteria andMajorNotIn(List<String> values) {
            addCriterion("MAJOR not in", values, "major");
            return (Criteria) this;
        }

        public Criteria andMajorBetween(String value1, String value2) {
            addCriterion("MAJOR between", value1, value2, "major");
            return (Criteria) this;
        }

        public Criteria andMajorNotBetween(String value1, String value2) {
            addCriterion("MAJOR not between", value1, value2, "major");
            return (Criteria) this;
        }

        public Criteria andSlaveIsNull() {
            addCriterion("SLAVE is null");
            return (Criteria) this;
        }

        public Criteria andSlaveIsNotNull() {
            addCriterion("SLAVE is not null");
            return (Criteria) this;
        }

        public Criteria andSlaveEqualTo(String value) {
            addCriterion("SLAVE =", value, "slave");
            return (Criteria) this;
        }

        public Criteria andSlaveNotEqualTo(String value) {
            addCriterion("SLAVE <>", value, "slave");
            return (Criteria) this;
        }

        public Criteria andSlaveGreaterThan(String value) {
            addCriterion("SLAVE >", value, "slave");
            return (Criteria) this;
        }

        public Criteria andSlaveGreaterThanOrEqualTo(String value) {
            addCriterion("SLAVE >=", value, "slave");
            return (Criteria) this;
        }

        public Criteria andSlaveLessThan(String value) {
            addCriterion("SLAVE <", value, "slave");
            return (Criteria) this;
        }

        public Criteria andSlaveLessThanOrEqualTo(String value) {
            addCriterion("SLAVE <=", value, "slave");
            return (Criteria) this;
        }

        public Criteria andSlaveLike(String value) {
            addCriterion("SLAVE like", value, "slave");
            return (Criteria) this;
        }

        public Criteria andSlaveNotLike(String value) {
            addCriterion("SLAVE not like", value, "slave");
            return (Criteria) this;
        }

        public Criteria andSlaveIn(List<String> values) {
            addCriterion("SLAVE in", values, "slave");
            return (Criteria) this;
        }

        public Criteria andSlaveNotIn(List<String> values) {
            addCriterion("SLAVE not in", values, "slave");
            return (Criteria) this;
        }

        public Criteria andSlaveBetween(String value1, String value2) {
            addCriterion("SLAVE between", value1, value2, "slave");
            return (Criteria) this;
        }

        public Criteria andSlaveNotBetween(String value1, String value2) {
            addCriterion("SLAVE not between", value1, value2, "slave");
            return (Criteria) this;
        }

        public Criteria andExtIsNull() {
            addCriterion("EXT is null");
            return (Criteria) this;
        }

        public Criteria andExtIsNotNull() {
            addCriterion("EXT is not null");
            return (Criteria) this;
        }

        public Criteria andExtEqualTo(String value) {
            addCriterion("EXT =", value, "ext");
            return (Criteria) this;
        }

        public Criteria andExtNotEqualTo(String value) {
            addCriterion("EXT <>", value, "ext");
            return (Criteria) this;
        }

        public Criteria andExtGreaterThan(String value) {
            addCriterion("EXT >", value, "ext");
            return (Criteria) this;
        }

        public Criteria andExtGreaterThanOrEqualTo(String value) {
            addCriterion("EXT >=", value, "ext");
            return (Criteria) this;
        }

        public Criteria andExtLessThan(String value) {
            addCriterion("EXT <", value, "ext");
            return (Criteria) this;
        }

        public Criteria andExtLessThanOrEqualTo(String value) {
            addCriterion("EXT <=", value, "ext");
            return (Criteria) this;
        }

        public Criteria andExtLike(String value) {
            addCriterion("EXT like", value, "ext");
            return (Criteria) this;
        }

        public Criteria andExtNotLike(String value) {
            addCriterion("EXT not like", value, "ext");
            return (Criteria) this;
        }

        public Criteria andExtIn(List<String> values) {
            addCriterion("EXT in", values, "ext");
            return (Criteria) this;
        }

        public Criteria andExtNotIn(List<String> values) {
            addCriterion("EXT not in", values, "ext");
            return (Criteria) this;
        }

        public Criteria andExtBetween(String value1, String value2) {
            addCriterion("EXT between", value1, value2, "ext");
            return (Criteria) this;
        }

        public Criteria andExtNotBetween(String value1, String value2) {
            addCriterion("EXT not between", value1, value2, "ext");
            return (Criteria) this;
        }

        public Criteria andVerInfoIsNull() {
            addCriterion("VER_INFO is null");
            return (Criteria) this;
        }

        public Criteria andVerInfoIsNotNull() {
            addCriterion("VER_INFO is not null");
            return (Criteria) this;
        }

        public Criteria andVerInfoEqualTo(String value) {
            addCriterion("VER_INFO =", value, "verInfo");
            return (Criteria) this;
        }

        public Criteria andVerInfoNotEqualTo(String value) {
            addCriterion("VER_INFO <>", value, "verInfo");
            return (Criteria) this;
        }

        public Criteria andVerInfoGreaterThan(String value) {
            addCriterion("VER_INFO >", value, "verInfo");
            return (Criteria) this;
        }

        public Criteria andVerInfoGreaterThanOrEqualTo(String value) {
            addCriterion("VER_INFO >=", value, "verInfo");
            return (Criteria) this;
        }

        public Criteria andVerInfoLessThan(String value) {
            addCriterion("VER_INFO <", value, "verInfo");
            return (Criteria) this;
        }

        public Criteria andVerInfoLessThanOrEqualTo(String value) {
            addCriterion("VER_INFO <=", value, "verInfo");
            return (Criteria) this;
        }

        public Criteria andVerInfoLike(String value) {
            addCriterion("VER_INFO like", value, "verInfo");
            return (Criteria) this;
        }

        public Criteria andVerInfoNotLike(String value) {
            addCriterion("VER_INFO not like", value, "verInfo");
            return (Criteria) this;
        }

        public Criteria andVerInfoIn(List<String> values) {
            addCriterion("VER_INFO in", values, "verInfo");
            return (Criteria) this;
        }

        public Criteria andVerInfoNotIn(List<String> values) {
            addCriterion("VER_INFO not in", values, "verInfo");
            return (Criteria) this;
        }

        public Criteria andVerInfoBetween(String value1, String value2) {
            addCriterion("VER_INFO between", value1, value2, "verInfo");
            return (Criteria) this;
        }

        public Criteria andVerInfoNotBetween(String value1, String value2) {
            addCriterion("VER_INFO not between", value1, value2, "verInfo");
            return (Criteria) this;
        }

        public Criteria andVerStatusIsNull() {
            addCriterion("VER_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andVerStatusIsNotNull() {
            addCriterion("VER_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andVerStatusEqualTo(String value) {
            addCriterion("VER_STATUS =", value, "verStatus");
            return (Criteria) this;
        }

        public Criteria andVerStatusNotEqualTo(String value) {
            addCriterion("VER_STATUS <>", value, "verStatus");
            return (Criteria) this;
        }

        public Criteria andVerStatusGreaterThan(String value) {
            addCriterion("VER_STATUS >", value, "verStatus");
            return (Criteria) this;
        }

        public Criteria andVerStatusGreaterThanOrEqualTo(String value) {
            addCriterion("VER_STATUS >=", value, "verStatus");
            return (Criteria) this;
        }

        public Criteria andVerStatusLessThan(String value) {
            addCriterion("VER_STATUS <", value, "verStatus");
            return (Criteria) this;
        }

        public Criteria andVerStatusLessThanOrEqualTo(String value) {
            addCriterion("VER_STATUS <=", value, "verStatus");
            return (Criteria) this;
        }

        public Criteria andVerStatusLike(String value) {
            addCriterion("VER_STATUS like", value, "verStatus");
            return (Criteria) this;
        }

        public Criteria andVerStatusNotLike(String value) {
            addCriterion("VER_STATUS not like", value, "verStatus");
            return (Criteria) this;
        }

        public Criteria andVerStatusIn(List<String> values) {
            addCriterion("VER_STATUS in", values, "verStatus");
            return (Criteria) this;
        }

        public Criteria andVerStatusNotIn(List<String> values) {
            addCriterion("VER_STATUS not in", values, "verStatus");
            return (Criteria) this;
        }

        public Criteria andVerStatusBetween(String value1, String value2) {
            addCriterion("VER_STATUS between", value1, value2, "verStatus");
            return (Criteria) this;
        }

        public Criteria andVerStatusNotBetween(String value1, String value2) {
            addCriterion("VER_STATUS not between", value1, value2, "verStatus");
            return (Criteria) this;
        }

        public Criteria andVerTypeIsNull() {
            addCriterion("VER_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andVerTypeIsNotNull() {
            addCriterion("VER_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andVerTypeEqualTo(String value) {
            addCriterion("VER_TYPE =", value, "verType");
            return (Criteria) this;
        }

        public Criteria andVerTypeNotEqualTo(String value) {
            addCriterion("VER_TYPE <>", value, "verType");
            return (Criteria) this;
        }

        public Criteria andVerTypeGreaterThan(String value) {
            addCriterion("VER_TYPE >", value, "verType");
            return (Criteria) this;
        }

        public Criteria andVerTypeGreaterThanOrEqualTo(String value) {
            addCriterion("VER_TYPE >=", value, "verType");
            return (Criteria) this;
        }

        public Criteria andVerTypeLessThan(String value) {
            addCriterion("VER_TYPE <", value, "verType");
            return (Criteria) this;
        }

        public Criteria andVerTypeLessThanOrEqualTo(String value) {
            addCriterion("VER_TYPE <=", value, "verType");
            return (Criteria) this;
        }

        public Criteria andVerTypeLike(String value) {
            addCriterion("VER_TYPE like", value, "verType");
            return (Criteria) this;
        }

        public Criteria andVerTypeNotLike(String value) {
            addCriterion("VER_TYPE not like", value, "verType");
            return (Criteria) this;
        }

        public Criteria andVerTypeIn(List<String> values) {
            addCriterion("VER_TYPE in", values, "verType");
            return (Criteria) this;
        }

        public Criteria andVerTypeNotIn(List<String> values) {
            addCriterion("VER_TYPE not in", values, "verType");
            return (Criteria) this;
        }

        public Criteria andVerTypeBetween(String value1, String value2) {
            addCriterion("VER_TYPE between", value1, value2, "verType");
            return (Criteria) this;
        }

        public Criteria andVerTypeNotBetween(String value1, String value2) {
            addCriterion("VER_TYPE not between", value1, value2, "verType");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("CREATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("CREATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("CREATE_TIME =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("CREATE_TIME <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("CREATE_TIME >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("CREATE_TIME >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("CREATE_TIME <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("CREATE_TIME <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("CREATE_TIME in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("CREATE_TIME not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("CREATE_TIME between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("CREATE_TIME not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andOperCodeIsNull() {
            addCriterion("OPER_CODE is null");
            return (Criteria) this;
        }

        public Criteria andOperCodeIsNotNull() {
            addCriterion("OPER_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andOperCodeEqualTo(String value) {
            addCriterion("OPER_CODE =", value, "operCode");
            return (Criteria) this;
        }

        public Criteria andOperCodeNotEqualTo(String value) {
            addCriterion("OPER_CODE <>", value, "operCode");
            return (Criteria) this;
        }

        public Criteria andOperCodeGreaterThan(String value) {
            addCriterion("OPER_CODE >", value, "operCode");
            return (Criteria) this;
        }

        public Criteria andOperCodeGreaterThanOrEqualTo(String value) {
            addCriterion("OPER_CODE >=", value, "operCode");
            return (Criteria) this;
        }

        public Criteria andOperCodeLessThan(String value) {
            addCriterion("OPER_CODE <", value, "operCode");
            return (Criteria) this;
        }

        public Criteria andOperCodeLessThanOrEqualTo(String value) {
            addCriterion("OPER_CODE <=", value, "operCode");
            return (Criteria) this;
        }

        public Criteria andOperCodeLike(String value) {
            addCriterion("OPER_CODE like", value, "operCode");
            return (Criteria) this;
        }

        public Criteria andOperCodeNotLike(String value) {
            addCriterion("OPER_CODE not like", value, "operCode");
            return (Criteria) this;
        }

        public Criteria andOperCodeIn(List<String> values) {
            addCriterion("OPER_CODE in", values, "operCode");
            return (Criteria) this;
        }

        public Criteria andOperCodeNotIn(List<String> values) {
            addCriterion("OPER_CODE not in", values, "operCode");
            return (Criteria) this;
        }

        public Criteria andOperCodeBetween(String value1, String value2) {
            addCriterion("OPER_CODE between", value1, value2, "operCode");
            return (Criteria) this;
        }

        public Criteria andOperCodeNotBetween(String value1, String value2) {
            addCriterion("OPER_CODE not between", value1, value2, "operCode");
            return (Criteria) this;
        }

        public Criteria andNoeIsNull() {
            addCriterion("NOE is null");
            return (Criteria) this;
        }

        public Criteria andNoeIsNotNull() {
            addCriterion("NOE is not null");
            return (Criteria) this;
        }

        public Criteria andNoeEqualTo(String value) {
            addCriterion("NOE =", value, "noe");
            return (Criteria) this;
        }

        public Criteria andNoeNotEqualTo(String value) {
            addCriterion("NOE <>", value, "noe");
            return (Criteria) this;
        }

        public Criteria andNoeGreaterThan(String value) {
            addCriterion("NOE >", value, "noe");
            return (Criteria) this;
        }

        public Criteria andNoeGreaterThanOrEqualTo(String value) {
            addCriterion("NOE >=", value, "noe");
            return (Criteria) this;
        }

        public Criteria andNoeLessThan(String value) {
            addCriterion("NOE <", value, "noe");
            return (Criteria) this;
        }

        public Criteria andNoeLessThanOrEqualTo(String value) {
            addCriterion("NOE <=", value, "noe");
            return (Criteria) this;
        }

        public Criteria andNoeLike(String value) {
            addCriterion("NOE like", value, "noe");
            return (Criteria) this;
        }

        public Criteria andNoeNotLike(String value) {
            addCriterion("NOE not like", value, "noe");
            return (Criteria) this;
        }

        public Criteria andNoeIn(List<String> values) {
            addCriterion("NOE in", values, "noe");
            return (Criteria) this;
        }

        public Criteria andNoeNotIn(List<String> values) {
            addCriterion("NOE not in", values, "noe");
            return (Criteria) this;
        }

        public Criteria andNoeBetween(String value1, String value2) {
            addCriterion("NOE between", value1, value2, "noe");
            return (Criteria) this;
        }

        public Criteria andNoeNotBetween(String value1, String value2) {
            addCriterion("NOE not between", value1, value2, "noe");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table version_info
     *
     * @mbggenerated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table version_info
     *
     * @mbggenerated
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}