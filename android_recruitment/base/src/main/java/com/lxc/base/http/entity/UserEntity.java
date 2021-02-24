package com.lxc.base.http.entity;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

/**
 * @author lxc
 */
@Entity(tableName = "user_info")
public class UserEntity implements Serializable {

	@PrimaryKey
	@ColumnInfo(name = "u_id")
	public Long id;
	
	@ColumnInfo(name = "u_name")
	public String account;
	
	@ColumnInfo(name = "u_pass")
	public String password;
	
	@ColumnInfo(name = "u_problem")
	public String problem;
	
	@ColumnInfo(name = "u_answer")
	public String answer;
	
	/**
	 * 1：管理员
	 * 2：企业
	 * 3：个人
	 */
	@ColumnInfo(name = "u_type")
	public String type;
	
	public static String ADMIN = "1";
	
	public static String COMPANY = "2";
	
	public static String PERSONAL = "3";
}
