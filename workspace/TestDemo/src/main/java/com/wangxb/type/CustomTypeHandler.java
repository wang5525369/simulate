package com.wangxb.type;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomTypeHandler extends BaseTypeHandler<Object> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Object parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i,parameter.toString());
    }

    @Override
    public Object getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String columnValue = rs.getString(columnName) + "_1";
        return columnValue;
    }

    @Override
    public Object getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String columnValue = rs.getString(columnIndex) + "_2";
        return columnValue;
    }

    @Override
    public Object getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String columnValue = cs.getString(columnIndex);
        return columnValue;
    }
}
