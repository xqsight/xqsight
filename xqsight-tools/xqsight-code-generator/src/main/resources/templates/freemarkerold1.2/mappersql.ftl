<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<#assign updNotNeed = ["createUserId", "createTime","createOprId"]>
<mapper namespace="${basePackage}.${moduleName}.mapper.${table.className}Mapper">

    <resultMap id="BaseResultMap"  type="${basePackage}.${moduleName}.model.${table.className}">
	<#list table.columns as col>
        <result column="${col.columnName}" property="${col.javaProperty}" jdbcType="${col.mybatisJdbcType}"/>
	</#list>
    </resultMap>

    <sql id="Base_Column_List">
	<#list table.primaryKeys as column> ${column.columnName},</#list><#list table.baseColumns as column>${column.columnName}<#if column_has_next>,</#if></#list>
    </sql>

    <insert id="insert" parameterType="${basePackage}.${moduleName}.model.${table.className}" useGeneratedKeys="true" keyProperty="<#list table.primaryKeys as column>${column.javaProperty}</#list>">
        insert into ${table.tableName} (
	<#list table.baseColumns as column>${column.columnName}<#if column_has_next>,</#if></#list>
        ) values (
	<#list table.baseColumns as column>
	${r'#{'}${column.javaProperty},jdbcType=${column.mybatisJdbcType}}<#if column_has_next>,</#if>
	</#list>
        )
    </insert>

    <insert id="insertSelective" parameterType="${basePackage}.${moduleName}.model.${table.className}" useGeneratedKeys="true" keyProperty="<#list table.primaryKeys as column>${column.javaProperty}</#list>">
        insert into ${table.tableName}
        <trim prefix="(" suffix=")" suffixOverrides="," >
		<#list table.baseColumns as column>
            <if test="${column.javaProperty}!=null">
			${column.columnName},
            </if>
		</#list>
        </trim>
        <trim prefix="values (" suffix=")" suffixOverrides="," >
		<#list table.baseColumns as column>
            <if test="${column.javaProperty}!=null">
			${r'#{'}${column.javaProperty},jdbcType=${column.mybatisJdbcType}},
            </if>
		</#list>
        </trim>
    </insert>

    <update id="updateByPrimaryKey" parameterType="${basePackage}.${moduleName}.model.${table.className}">
        update ${table.tableName} set
	<#list table.baseColumns as column>
		<#if updNotNeed?seq_contains(column.javaProperty)><#else>
		${column.columnName} = ${r'#{'}${column.javaProperty},jdbcType=${column.mybatisJdbcType}}<#if column_has_next>,</#if>
		</#if>
	</#list>
        where <#list table.primaryKeys as column> ${column.columnName} = ${r'#{'}${column.javaProperty},jdbcType=${column.mybatisJdbcType}} <#if column_has_next>and</#if> </#list>
    </update>

    <update id="updateByPrimaryKeySelective" parameterType="${basePackage}.${moduleName}.model.${table.className}">
        update ${table.tableName}
        <set>
		<#list table.baseColumns as column>
			<#if updNotNeed?seq_contains(column.javaProperty)><#else>
            <if test="${column.javaProperty}!=null">
            ${column.columnName} = ${r'#{'}${column.javaProperty},jdbcType=${column.mybatisJdbcType}},
            </if>
			</#if>
		</#list>
        </set>
        where <#list table.primaryKeys as column> ${column.columnName} = ${r'#{'}${column.javaProperty},jdbcType=${column.mybatisJdbcType}} <#if column_has_next>and</#if> </#list>
    </update>

    <delete id="deleteByPrimaryKey" parameterType="<#list table.primaryKeys as key>${key.fullJavaType}</#list>">
        delete from ${table.tableName}
        where <#list table.primaryKeys as column> ${column.columnName} = ${r'#{'}${column.javaProperty},jdbcType=${column.mybatisJdbcType}} <#if column_has_next>and</#if> </#list>
    </delete>

    <select id="selectByPrimaryKey" resultMap="BaseResultMap" parameterType="<#list table.primaryKeys as key>${key.fullJavaType}</#list>">
        select <include refid="Base_Column_List"/> from ${table.tableName}
        where <#list table.primaryKeys as column> ${column.columnName} = ${r'#{'}${column.javaProperty},jdbcType=${column.mybatisJdbcType}} <#if column_has_next>and</#if> </#list>
    </select>

    <select id="search" resultMap="BaseResultMap" parameterType="com.xqsight.common.core.orm.Criterion">
        select <include refid="Base_Column_List"/> from ${table.tableName}
        WHERE 1=1
	    ${r'${whereSqlString}'}

        <trim prefix=" and " suffix="" suffixOverrides="" >
        <if test="customSql != null">
        ${r'${customSqlString}'}
        </if>
        </trim>

        <if test="orderBy != null">
		${r'${orderBySqlString}'}
        </if>
    </select>
</mapper>
