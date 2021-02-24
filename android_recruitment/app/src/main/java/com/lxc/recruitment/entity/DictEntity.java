package com.lxc.recruitment.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(tableName ="dict")
public class DictEntity {

    @ColumnInfo(name = "id")
    public Long id;

    @ColumnInfo(name = "name")
    public String value;

    @ColumnInfo(name = "root")
    public Long rootId;

    public static final long YEAR = 1L;

    public static final long DEGREE = 1L;

    public static final long SALARY = 1L;
}
