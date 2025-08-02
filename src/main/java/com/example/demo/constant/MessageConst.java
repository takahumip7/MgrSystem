package com.example.demo.constant;

/**
 * エラーメッセージID定数クラス
 */
public class MessageConst {

	/** 共通：入力内容誤り */
	public static final String FORM_ERROLR = "common.formError";
	
	/** ログイン画面：入力内容誤り */
	public static final String LOGIN_WRONG_INPUT = "login.wrongInput";
	
	/** ユーザ登録画面：既に登録されているログインID */
	public static final String Signup_Exsited_Login_Id = "signup.exsitedLoginId";
	
	/** ユーザ登録画面：ユーザ登録完了 */
	public static final String Signup_Regist_Succeed = "signup.registSucceed";
	
	/** ユーザー一覧画面：存在しないログインID */
	public static final String USERLIST_NON_EXISTED_LOGIN_ID = "userList.nonExistedLoginId";

	/** ユーザー一覧画面：ユーザー削除完了 */
	public static final String USERLIST_DELETE_SUCCEED = "userList.deleteSucceed";
	
}
