package com.lxc.recruitment.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;


/**
 * 管理员
 */
@Entity(tableName = "administrator_info")
public class AdministratorEntity {
	
	@ColumnInfo(name = "a_id")
	public Long id;
	@ColumnInfo(name = "u_id")
	public Long userId;
	@ColumnInfo(name = "a_name")
	public String name;
	@ColumnInfo(name = "a_sex")
	public String sex;
	@ColumnInfo(name = "a_phone")
	public String phone;
	@ColumnInfo(name = "a_number")
	public String number;
}
