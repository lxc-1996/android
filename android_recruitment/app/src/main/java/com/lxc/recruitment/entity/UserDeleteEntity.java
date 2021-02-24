package com.lxc.recruitment.entity;


import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(tableName ="user_delete")
public class UserDeleteEntity {
	
	@ColumnInfo(name = "ud_id")
	public Long id;
	@ColumnInfo(name = "a_id")
	public Long adminId;
	@ColumnInfo(name = "ud_deleted_id")
	public Long deletedId;
	@ColumnInfo(name = "ud_reason")
	public String reason;
}
