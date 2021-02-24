package com.lxc.recruitment.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

/**
 * 审核
 */
@Entity(tableName ="audit_info")
public class AuditEntity {
	
	@ColumnInfo(name = "ai_id")
	public Long id;
	
	@ColumnInfo(name = "a_id")
	public Long adminId;
	
	@ColumnInfo(name = "j_id")
	public Long jobId;
}
