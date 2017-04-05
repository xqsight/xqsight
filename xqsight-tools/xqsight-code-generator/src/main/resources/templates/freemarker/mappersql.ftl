<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<#assign updNotNeed = ["createUserId", "createTime","createOprId"]>
<mapper namespace="${basePackage}.${moduleName}.mapper.${table.className}Mapper">

    <resultMap id="BaseResultMap"  type="${basePackage}.${moduleName}.model.${table.className}">
    <#list table.columns as col>
        <result column="${col.columnName}" property="${col.javaProperty}" jdbcType="${col.mybatisJdbcType}"/>
    </#list>
    </resultMap>

    <sql id="Base_Column_List_Without_Id">
        <#list table.baseColumns as column>${column.columnName}<#if column_has_next>,</#if></#list>
    </sql>
    <sql id="Base_Column_List">
        <#list table.primaryKeys as column> ${column.columnName},</#list>
        <include refid="Base_Column_List_Without_Id"/>
    </sql>

    <sql id="Insert_Columns">
    <#list table.baseColumns as column>
        <if test="${column.javaProperty}!=null"> ${column.columnName},</if>
    </#list>
    </sql>
    <sql id="Insert_Values">
    <#list table.baseColumns as column>
        <if test="${column.javaProperty}!=null">${r'#{'}${column.javaProperty},jdbcType=${column.mybatisJdbcType}},</if>
    </#list>
    </sql>
    <sql id="Batch_Insert_Values">
    <#list table.baseColumns as column>
        ${r'#{'}${column.javaProperty},jdbcType=${column.mybatisJdbcType}},
    </#list>
    </sql>

    <sql id="Update_Set_From_Bean">
    <#list table.baseColumns as column>
        <if test="${column.javaProperty}!=null"> ${column.columnName}=${r'#{'}${column.javaProperty},jdbcType=${column.mybatisJdbcType}},</if>
    </#list>
    </sql>
    <sql id="BatchUpdate_Set_From_Bean">
    <#list table.baseColumns as column>
        ${column.columnName}=${r'#{'}${column.javaProperty},jdbcType=${column.mybatisJdbcType}},
    </#list>
    </sql>

    <!--insert-->
    <insert id="insert" parameterType="${basePackage}.${moduleName}.model.${table.className}" useGeneratedKeys="true" keyProperty="<#list table.primaryKeys as column>${column.javaProperty}</#list>">
        insert into ${table.tableName}
        <trim prefix="(" suffix=")" suffixOverrides=",">
            <include refid="Insert_Columns"/>
        </trim>
        <trim prefix="values (" suffix=")" suffixOverrides=",">
            <include refid="Insert_Values"/>
        </trim>
    </insert>
    <insert id="batchInsert" parameterType="java.util.List">
        insert into ${table.tableName}
        <trim prefix="(" suffix=")" suffixOverrides=",">
            <include refid="Base_Column_List_Without_Id"/>
        </trim>
        values
        <foreach collection="list" item="item" index="index" separator=",">
            <trim prefix="(" suffix=")" suffixOverrides=",">
                <include refid="Batch_Insert_Values"/>
            </trim>
        </foreach>
    </insert>
    <!-- end insert -->

    <!-- delete -->
    <delete id="deleteById" parameterType="<#list table.primaryKeys as key>${key.fullJavaType}</#list>">
        delete from ${table.tableName}
        where <#list table.primaryKeys as column> ${column.columnName} = ${r'#{'}${column.javaProperty},jdbcType=${column.mybatisJdbcType}} <#if column_has_next>and</#if> </#list>
    </delete>
    <delete id="deleteByCriterion" parameterType="com.xqsight.common.core.orm.Criterion">
        delete from ${table.tableName}
        where 1=1
        ${r'${whereSqlString}'}
    </delete>
    <!-- end delete -->

    <!-- update -->
    <update id="updateById" parameterType="${basePackage}.${moduleName}.model.${table.className}">
        update ${table.tableName}
        <set>
            <include refid="Update_Set_From_Bean"/>
        </set>
        where <#list table.primaryKeys as column> ${column.columnName} = ${r'#{'}${column.javaProperty},jdbcType=${column.mybatisJdbcType}} <#if column_has_next>and</#if> </#list>
    </update>
    <update id="batchUpdate" parameterType="java.util.List">
        <foreach collection="list" item="item" index="index" open="" close="" separator=";">
        update ${table.tableName}
        <include refid="BatchUpdate_Set_From_Bean"/>
        where
            <#list table.primaryKeys as column> ${column.columnName} = ${r'#{'}${column.javaProperty},jdbcType=${column.mybatisJdbcType}} <#if column_has_next>and</#if> </#list>
        </foreach>
    </update>
    <!-- end update -->

    <!-- select -->
    <select id="selectById" resultMap="BaseResultMap" parameterType="<#list table.primaryKeys as key>${key.fullJavaType}</#list>">
        select <include refid="Base_Column_List"/> from ${table.tableName}
        where
            <#list table.primaryKeys as column> ${column.columnName} = ${r'#{'}${column.javaProperty},jdbcType=${column.mybatisJdbcType}} <#if column_has_next>and</#if> </#list>
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
    <!-- end select -->
</mapper>
