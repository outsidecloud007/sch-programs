package com.cht.sch.programs.model;

import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Program {

	@JsonProperty("節目")
	@Pattern(regexp = ".+")	
	private String name;
	
	@JsonProperty("描述")
	private String description;
	
	@JsonProperty("內容")
    private String content;
	
	@JsonProperty("頻道")
	private int channel;
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getChannel() {
		return channel;
	}

	public void setChannel(int channel) {
		this.channel = channel;
	}
}