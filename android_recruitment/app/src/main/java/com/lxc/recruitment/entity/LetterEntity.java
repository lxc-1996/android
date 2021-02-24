package com.lxc.recruitment.entity;


import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(tableName ="letter")
public class LetterEntity {
	
	@ColumnInfo(name = "l_id")
	public Long id;
	
	@ColumnInfo(name = "u_send_id")
	public Long sender;
	
	@ColumnInfo(name = "u_receive_id")
	public Long receiver;
	
	@ColumnInfo(name = "l_title")
	public String title;
	
	@ColumnInfo(name = "l_content")
	public String content;
	
	@ColumnInfo(name = "l_type")
	public String type;

	public String senderName;

	public static final String DELIVERY = "delivery";

	public static final String INTERVIEW = "interview";
}
