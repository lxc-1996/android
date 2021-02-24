package com.lxc.recruitment.entity;


import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(tableName = "company_info")
public class CompanyEntity {
	
	@ColumnInfo(name = "c_id")
	public Long id;
	@ColumnInfo(name = "u_id")
	public Long userId;
	@ColumnInfo(name = "c_name")
	public String name;
	@ColumnInfo(name = "c_sex")
	public String sex;
	@ColumnInfo(name = "c_phone")
	public String phone;
	@ColumnInfo(name = "c_mail")
	public String mail;
	@ColumnInfo(name = "c_company_name")
	public String companyName;
	@ColumnInfo(name = "c_license")
	public String license;
	@ColumnInfo(name = "c_properties")
	public String properties;
	@ColumnInfo(name = "c_sector")
	public String sector;
	@ColumnInfo(name = "c_size")
	public String size;
	@ColumnInfo(name = "c_located")
	public String located;
	@ColumnInfo(name = "c_web")
	public String web;
	@ColumnInfo(name = "c_introduction")
	public String introduction;
}
