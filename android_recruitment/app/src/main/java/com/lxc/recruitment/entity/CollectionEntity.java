package com.lxc.recruitment.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

/**
 * 收藏
 */
@Entity(tableName = "collection")
public class CollectionEntity {
	
	@ColumnInfo(name = "col_id")
	public Long id;
	@ColumnInfo(name = "a_id")
	public Long userId;
	@ColumnInfo(name = "j_id")
	public Long jobId;

	public String jobName;
}
