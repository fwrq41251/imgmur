package com.winry.util;

import com.winry.dto.ResultDto;

public final class ResultDtoFactory {

	private ResultDtoFactory() {
	}

	public static ResultDto toSuccess(Object data) {
		ResultDto resultDto = new ResultDto(data);
		resultDto.setCode("200");
		return resultDto;
	}
}
