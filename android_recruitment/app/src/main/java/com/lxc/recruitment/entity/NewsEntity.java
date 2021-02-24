package com.lxc.recruitment.entity;


import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(tableName = "news")
public class NewsEntity {
	
	@ColumnInfo(name = "n_id")
	public Long id;
	@ColumnInfo(name = "a_id")
	public Long adminId;
	@ColumnInfo(name = "n_title")
	public String title;
	@ColumnInfo(name = "n_content")
	public String content;
}
