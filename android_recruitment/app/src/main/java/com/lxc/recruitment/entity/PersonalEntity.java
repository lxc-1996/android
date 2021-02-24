package com.lxc.recruitment.entity;


import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(tableName ="personal_info")
public class PersonalEntity {
	
	@ColumnInfo(name = "p_id")
	public Long id;
	@ColumnInfo(name = "u_id")
	public Long userId;
	@ColumnInfo(name = "p_name")
	public String name;
	@ColumnInfo(name = "p_sex")
	public String sex;
	@ColumnInfo(name = "p_docType")
	public String docType;
	@ColumnInfo(name = "p_idcard")
	public String idcard;
	@ColumnInfo(name = "p_nationality")
	public String nationality;
	@ColumnInfo(name = "p_age")
	public String age;
	@ColumnInfo(name = "p_national")
	public String national;
	@ColumnInfo(name = "p_native")
	public String nativeAddress;
	@ColumnInfo(name = "p_address")
	public String address;
	@ColumnInfo(name = "p_political")
	public String political;
	@ColumnInfo(name = "p_degree")
	public String degree;
	@ColumnInfo(name = "p_status")
	public String status;
	@ColumnInfo(name = "p_phone")
	public String phone;
	@ColumnInfo(name = "p_mail")
	public String mail;
}
