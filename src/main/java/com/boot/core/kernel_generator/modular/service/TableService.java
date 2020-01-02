package com.boot.core.kernel_generator.modular.service;

import com.baomidou.mybatisplus.mapper.SqlRunner;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class TableService {
    @Value("${spring.datasource.db-name:guns}")
    private String dbName;

    public List<Map<String, Object>> getAllTables() {
        String sql = "select TABLE_NAME as tableName,TABLE_COMMENT as tableComment from information_schema.`TABLES` where TABLE_SCHEMA = '" + this.dbName + "'";
        return SqlRunner.db().selectList(sql, new Object[0]);
    }
}
