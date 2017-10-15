package com.chilicool.hdtools.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataModuleEnumParamExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table data_module_enum_param
     *
     * @mbggenerated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table data_module_enum_param
     *
     * @mbggenerated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table data_module_enum_param
     *
     * @mbggenerated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table data_module_enum_param
     *
     * @mbggenerated
     */
    public DataModuleEnumParamExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table data_module_enum_param
     *
     * @mbggenerated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table data_module_enum_param
     *
     * @mbggenerated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table data_module_enum_param
     *
     * @mbggenerated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table data_module_enum_param
     *
     * @mbggenerated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table data_module_enum_param
     *
     * @mbggenerated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table data_module_enum_param
     *
     * @mbggenerated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table data_module_enum_param
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
     * This method corresponds to the database table data_module_enum_param
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
     * This method corresponds to the database table data_module_enum_param
     *
     * @mbggenerated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table data_module_enum_param
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
     * This class corresponds to the database table data_module_enum_param
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

        public Criteria andModuleIdIsNull() {
            addCriterion("MODULE_ID is null");
            return (Criteria) this;
        }

        public Criteria andModuleIdIsNotNull() {
            addCriterion("MODULE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andModuleIdEqualTo(Long value) {
            addCriterion("MODULE_ID =", value, "moduleId");
            return (Criteria) this;
        }

        public Criteria andModuleIdNotEqualTo(Long value) {
            addCriterion("MODULE_ID <>", value, "moduleId");
            return (Criteria) this;
        }

        public Criteria andModuleIdGreaterThan(Long value) {
            addCriterion("MODULE_ID >", value, "moduleId");
            return (Criteria) this;
        }

        public Criteria andModuleIdGreaterThanOrEqualTo(Long value) {
            addCriterion("MODULE_ID >=", value, "moduleId");
            return (Criteria) this;
        }

        public Criteria andModuleIdLessThan(Long value) {
            addCriterion("MODULE_ID <", value, "moduleId");
            return (Criteria) this;
        }

        public Criteria andModuleIdLessThanOrEqualTo(Long value) {
            addCriterion("MODULE_ID <=", value, "moduleId");
            return (Criteria) this;
        }

        public Criteria andModuleIdIn(List<Long> values) {
            addCriterion("MODULE_ID in", values, "moduleId");
            return (Criteria) this;
        }

        public Criteria andModuleIdNotIn(List<Long> values) {
            addCriterion("MODULE_ID not in", values, "moduleId");
            return (Criteria) this;
        }

        public Criteria andModuleIdBetween(Long value1, Long value2) {
            addCriterion("MODULE_ID between", value1, value2, "moduleId");
            return (Criteria) this;
        }

        public Criteria andModuleIdNotBetween(Long value1, Long value2) {
            addCriterion("MODULE_ID not between", value1, value2, "moduleId");
            return (Criteria) this;
        }

        public Criteria andEnumIdIsNull() {
            addCriterion("ENUM_ID is null");
            return (Criteria) this;
        }

        public Criteria andEnumIdIsNotNull() {
            addCriterion("ENUM_ID is not null");
            return (Criteria) this;
        }

        public Criteria andEnumIdEqualTo(Long value) {
            addCriterion("ENUM_ID =", value, "enumId");
            return (Criteria) this;
        }

        public Criteria andEnumIdNotEqualTo(Long value) {
            addCriterion("ENUM_ID <>", value, "enumId");
            return (Criteria) this;
        }

        public Criteria andEnumIdGreaterThan(Long value) {
            addCriterion("ENUM_ID >", value, "enumId");
            return (Criteria) this;
        }

        public Criteria andEnumIdGreaterThanOrEqualTo(Long value) {
            addCriterion("ENUM_ID >=", value, "enumId");
            return (Criteria) this;
        }

        public Criteria andEnumIdLessThan(Long value) {
            addCriterion("ENUM_ID <", value, "enumId");
            return (Criteria) this;
        }

        public Criteria andEnumIdLessThanOrEqualTo(Long value) {
            addCriterion("ENUM_ID <=", value, "enumId");
            return (Criteria) this;
        }

        public Criteria andEnumIdIn(List<Long> values) {
            addCriterion("ENUM_ID in", values, "enumId");
            return (Criteria) this;
        }

        public Criteria andEnumIdNotIn(List<Long> values) {
            addCriterion("ENUM_ID not in", values, "enumId");
            return (Criteria) this;
        }

        public Criteria andEnumIdBetween(Long value1, Long value2) {
            addCriterion("ENUM_ID between", value1, value2, "enumId");
            return (Criteria) this;
        }

        public Criteria andEnumIdNotBetween(Long value1, Long value2) {
            addCriterion("ENUM_ID not between", value1, value2, "enumId");
            return (Criteria) this;
        }

        public Criteria andEnumParamNameIsNull() {
            addCriterion("ENUM_PARAM_NAME is null");
            return (Criteria) this;
        }

        public Criteria andEnumParamNameIsNotNull() {
            addCriterion("ENUM_PARAM_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andEnumParamNameEqualTo(String value) {
            addCriterion("ENUM_PARAM_NAME =", value, "enumParamName");
            return (Criteria) this;
        }

        public Criteria andEnumParamNameNotEqualTo(String value) {
            addCriterion("ENUM_PARAM_NAME <>", value, "enumParamName");
            return (Criteria) this;
        }

        public Criteria andEnumParamNameGreaterThan(String value) {
            addCriterion("ENUM_PARAM_NAME >", value, "enumParamName");
            return (Criteria) this;
        }

        public Criteria andEnumParamNameGreaterThanOrEqualTo(String value) {
            addCriterion("ENUM_PARAM_NAME >=", value, "enumParamName");
            return (Criteria) this;
        }

        public Criteria andEnumParamNameLessThan(String value) {
            addCriterion("ENUM_PARAM_NAME <", value, "enumParamName");
            return (Criteria) this;
        }

        public Criteria andEnumParamNameLessThanOrEqualTo(String value) {
            addCriterion("ENUM_PARAM_NAME <=", value, "enumParamName");
            return (Criteria) this;
        }

        public Criteria andEnumParamNameLike(String value) {
            addCriterion("ENUM_PARAM_NAME like", value, "enumParamName");
            return (Criteria) this;
        }

        public Criteria andEnumParamNameNotLike(String value) {
            addCriterion("ENUM_PARAM_NAME not like", value, "enumParamName");
            return (Criteria) this;
        }

        public Criteria andEnumParamNameIn(List<String> values) {
            addCriterion("ENUM_PARAM_NAME in", values, "enumParamName");
            return (Criteria) this;
        }

        public Criteria andEnumParamNameNotIn(List<String> values) {
            addCriterion("ENUM_PARAM_NAME not in", values, "enumParamName");
            return (Criteria) this;
        }

        public Criteria andEnumParamNameBetween(String value1, String value2) {
            addCriterion("ENUM_PARAM_NAME between", value1, value2, "enumParamName");
            return (Criteria) this;
        }

        public Criteria andEnumParamNameNotBetween(String value1, String value2) {
            addCriterion("ENUM_PARAM_NAME not between", value1, value2, "enumParamName");
            return (Criteria) this;
        }

        public Criteria andEnumParamCodeIsNull() {
            addCriterion("ENUM_PARAM_CODE is null");
            return (Criteria) this;
        }

        public Criteria andEnumParamCodeIsNotNull() {
            addCriterion("ENUM_PARAM_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andEnumParamCodeEqualTo(String value) {
            addCriterion("ENUM_PARAM_CODE =", value, "enumParamCode");
            return (Criteria) this;
        }

        public Criteria andEnumParamCodeNotEqualTo(String value) {
            addCriterion("ENUM_PARAM_CODE <>", value, "enumParamCode");
            return (Criteria) this;
        }

        public Criteria andEnumParamCodeGreaterThan(String value) {
            addCriterion("ENUM_PARAM_CODE >", value, "enumParamCode");
            return (Criteria) this;
        }

        public Criteria andEnumParamCodeGreaterThanOrEqualTo(String value) {
            addCriterion("ENUM_PARAM_CODE >=", value, "enumParamCode");
            return (Criteria) this;
        }

        public Criteria andEnumParamCodeLessThan(String value) {
            addCriterion("ENUM_PARAM_CODE <", value, "enumParamCode");
            return (Criteria) this;
        }

        public Criteria andEnumParamCodeLessThanOrEqualTo(String value) {
            addCriterion("ENUM_PARAM_CODE <=", value, "enumParamCode");
            return (Criteria) this;
        }

        public Criteria andEnumParamCodeLike(String value) {
            addCriterion("ENUM_PARAM_CODE like", value, "enumParamCode");
            return (Criteria) this;
        }

        public Criteria andEnumParamCodeNotLike(String value) {
            addCriterion("ENUM_PARAM_CODE not like", value, "enumParamCode");
            return (Criteria) this;
        }

        public Criteria andEnumParamCodeIn(List<String> values) {
            addCriterion("ENUM_PARAM_CODE in", values, "enumParamCode");
            return (Criteria) this;
        }

        public Criteria andEnumParamCodeNotIn(List<String> values) {
            addCriterion("ENUM_PARAM_CODE not in", values, "enumParamCode");
            return (Criteria) this;
        }

        public Criteria andEnumParamCodeBetween(String value1, String value2) {
            addCriterion("ENUM_PARAM_CODE between", value1, value2, "enumParamCode");
            return (Criteria) this;
        }

        public Criteria andEnumParamCodeNotBetween(String value1, String value2) {
            addCriterion("ENUM_PARAM_CODE not between", value1, value2, "enumParamCode");
            return (Criteria) this;
        }

        public Criteria andOrderIdxIsNull() {
            addCriterion("ORDER_IDX is null");
            return (Criteria) this;
        }

        public Criteria andOrderIdxIsNotNull() {
            addCriterion("ORDER_IDX is not null");
            return (Criteria) this;
        }

        public Criteria andOrderIdxEqualTo(Short value) {
            addCriterion("ORDER_IDX =", value, "orderIdx");
            return (Criteria) this;
        }

        public Criteria andOrderIdxNotEqualTo(Short value) {
            addCriterion("ORDER_IDX <>", value, "orderIdx");
            return (Criteria) this;
        }

        public Criteria andOrderIdxGreaterThan(Short value) {
            addCriterion("ORDER_IDX >", value, "orderIdx");
            return (Criteria) this;
        }

        public Criteria andOrderIdxGreaterThanOrEqualTo(Short value) {
            addCriterion("ORDER_IDX >=", value, "orderIdx");
            return (Criteria) this;
        }

        public Criteria andOrderIdxLessThan(Short value) {
            addCriterion("ORDER_IDX <", value, "orderIdx");
            return (Criteria) this;
        }

        public Criteria andOrderIdxLessThanOrEqualTo(Short value) {
            addCriterion("ORDER_IDX <=", value, "orderIdx");
            return (Criteria) this;
        }

        public Criteria andOrderIdxIn(List<Short> values) {
            addCriterion("ORDER_IDX in", values, "orderIdx");
            return (Criteria) this;
        }

        public Criteria andOrderIdxNotIn(List<Short> values) {
            addCriterion("ORDER_IDX not in", values, "orderIdx");
            return (Criteria) this;
        }

        public Criteria andOrderIdxBetween(Short value1, Short value2) {
            addCriterion("ORDER_IDX between", value1, value2, "orderIdx");
            return (Criteria) this;
        }

        public Criteria andOrderIdxNotBetween(Short value1, Short value2) {
            addCriterion("ORDER_IDX not between", value1, value2, "orderIdx");
            return (Criteria) this;
        }

        public Criteria andNameSpellingIsNull() {
            addCriterion("NAME_SPELLING is null");
            return (Criteria) this;
        }

        public Criteria andNameSpellingIsNotNull() {
            addCriterion("NAME_SPELLING is not null");
            return (Criteria) this;
        }

        public Criteria andNameSpellingEqualTo(String value) {
            addCriterion("NAME_SPELLING =", value, "nameSpelling");
            return (Criteria) this;
        }

        public Criteria andNameSpellingNotEqualTo(String value) {
            addCriterion("NAME_SPELLING <>", value, "nameSpelling");
            return (Criteria) this;
        }

        public Criteria andNameSpellingGreaterThan(String value) {
            addCriterion("NAME_SPELLING >", value, "nameSpelling");
            return (Criteria) this;
        }

        public Criteria andNameSpellingGreaterThanOrEqualTo(String value) {
            addCriterion("NAME_SPELLING >=", value, "nameSpelling");
            return (Criteria) this;
        }

        public Criteria andNameSpellingLessThan(String value) {
            addCriterion("NAME_SPELLING <", value, "nameSpelling");
            return (Criteria) this;
        }

        public Criteria andNameSpellingLessThanOrEqualTo(String value) {
            addCriterion("NAME_SPELLING <=", value, "nameSpelling");
            return (Criteria) this;
        }

        public Criteria andNameSpellingLike(String value) {
            addCriterion("NAME_SPELLING like", value, "nameSpelling");
            return (Criteria) this;
        }

        public Criteria andNameSpellingNotLike(String value) {
            addCriterion("NAME_SPELLING not like", value, "nameSpelling");
            return (Criteria) this;
        }

        public Criteria andNameSpellingIn(List<String> values) {
            addCriterion("NAME_SPELLING in", values, "nameSpelling");
            return (Criteria) this;
        }

        public Criteria andNameSpellingNotIn(List<String> values) {
            addCriterion("NAME_SPELLING not in", values, "nameSpelling");
            return (Criteria) this;
        }

        public Criteria andNameSpellingBetween(String value1, String value2) {
            addCriterion("NAME_SPELLING between", value1, value2, "nameSpelling");
            return (Criteria) this;
        }

        public Criteria andNameSpellingNotBetween(String value1, String value2) {
            addCriterion("NAME_SPELLING not between", value1, value2, "nameSpelling");
            return (Criteria) this;
        }

        public Criteria andNameCodeIsNull() {
            addCriterion("NAME_CODE is null");
            return (Criteria) this;
        }

        public Criteria andNameCodeIsNotNull() {
            addCriterion("NAME_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andNameCodeEqualTo(String value) {
            addCriterion("NAME_CODE =", value, "nameCode");
            return (Criteria) this;
        }

        public Criteria andNameCodeNotEqualTo(String value) {
            addCriterion("NAME_CODE <>", value, "nameCode");
            return (Criteria) this;
        }

        public Criteria andNameCodeGreaterThan(String value) {
            addCriterion("NAME_CODE >", value, "nameCode");
            return (Criteria) this;
        }

        public Criteria andNameCodeGreaterThanOrEqualTo(String value) {
            addCriterion("NAME_CODE >=", value, "nameCode");
            return (Criteria) this;
        }

        public Criteria andNameCodeLessThan(String value) {
            addCriterion("NAME_CODE <", value, "nameCode");
            return (Criteria) this;
        }

        public Criteria andNameCodeLessThanOrEqualTo(String value) {
            addCriterion("NAME_CODE <=", value, "nameCode");
            return (Criteria) this;
        }

        public Criteria andNameCodeLike(String value) {
            addCriterion("NAME_CODE like", value, "nameCode");
            return (Criteria) this;
        }

        public Criteria andNameCodeNotLike(String value) {
            addCriterion("NAME_CODE not like", value, "nameCode");
            return (Criteria) this;
        }

        public Criteria andNameCodeIn(List<String> values) {
            addCriterion("NAME_CODE in", values, "nameCode");
            return (Criteria) this;
        }

        public Criteria andNameCodeNotIn(List<String> values) {
            addCriterion("NAME_CODE not in", values, "nameCode");
            return (Criteria) this;
        }

        public Criteria andNameCodeBetween(String value1, String value2) {
            addCriterion("NAME_CODE between", value1, value2, "nameCode");
            return (Criteria) this;
        }

        public Criteria andNameCodeNotBetween(String value1, String value2) {
            addCriterion("NAME_CODE not between", value1, value2, "nameCode");
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

        public Criteria andNoteIsNull() {
            addCriterion("NOTE is null");
            return (Criteria) this;
        }

        public Criteria andNoteIsNotNull() {
            addCriterion("NOTE is not null");
            return (Criteria) this;
        }

        public Criteria andNoteEqualTo(String value) {
            addCriterion("NOTE =", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotEqualTo(String value) {
            addCriterion("NOTE <>", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteGreaterThan(String value) {
            addCriterion("NOTE >", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteGreaterThanOrEqualTo(String value) {
            addCriterion("NOTE >=", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteLessThan(String value) {
            addCriterion("NOTE <", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteLessThanOrEqualTo(String value) {
            addCriterion("NOTE <=", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteLike(String value) {
            addCriterion("NOTE like", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotLike(String value) {
            addCriterion("NOTE not like", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteIn(List<String> values) {
            addCriterion("NOTE in", values, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotIn(List<String> values) {
            addCriterion("NOTE not in", values, "note");
            return (Criteria) this;
        }

        public Criteria andNoteBetween(String value1, String value2) {
            addCriterion("NOTE between", value1, value2, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotBetween(String value1, String value2) {
            addCriterion("NOTE not between", value1, value2, "note");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table data_module_enum_param
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
     * This class corresponds to the database table data_module_enum_param
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