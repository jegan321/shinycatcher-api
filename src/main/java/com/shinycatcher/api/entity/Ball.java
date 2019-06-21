package com.shinycatcher.api.entity;

import org.springframework.data.annotation.Id;

public class Ball {

	@Id
	public Long ballId;
	public String ballName;
	public String ballImage;

}
