package com.example.FutoVoteBackend.dto;

import java.util.List;

import lombok.Data;

@Data
public class Mail
{
	private String from;

	private String to;

	private String cc;

	private String bcc;

	private String subject;

	private String content;

	private String contentType;

	private List<Object> attachments;
}
