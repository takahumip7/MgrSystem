package com.example.demo.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SignupMessage {
	
	SUCCEED(MessageConst.Signup_Regist_Succeed, false),

	EXITED_LOGIN_ID(MessageConst.Signup_Exsited_Login_Id, true);
	
	private String messageId;
	
	private boolean isError;
	
}
