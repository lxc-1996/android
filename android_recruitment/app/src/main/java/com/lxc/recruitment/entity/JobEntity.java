package com.lxc.recruitment.entity;


import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(tableName = "jobs")
public class JobEntity {
	
	@ColumnInfo(name = "j_id")
	public Long id;
	@ColumnInfo(name = "c_id")
	public Long companyId;
	@ColumnInfo(name = "j_name")
	public String name;
	@ColumnInfo(name = "j_professional")
	public String professional;
	@ColumnInfo(name = "j_degree")
	public String degree;
	@ColumnInfo(name = "j_duration")
	public String duration;
	@ColumnInfo(name = "j_site")
	public String site;
	@ColumnInfo(name = "j_treatment")
	public String treatment;
	@ColumnInfo(name = "j_creation")
	public String creation;
}
