package com.lxc.recruitment.entity;


import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(tableName ="delivery")
public class DeliveryEntity {
	
	@ColumnInfo(name = "d_id")
	public Long id;
	@ColumnInfo(name = "r_id")
	public Long resumeId;
	@ColumnInfo(name = "j_id")
	public Long jobId;

	public Long sender;

	public String senderName;
}
