package com.example.demo.constant;

/**
 * URL定数クラス
 */
public class UrlConst {

	/** ログイン画面 */
	public static final String LOGIN = "/login";
	
	/** ユーザ登録画面 */
	public static final String SIGNUP = "/signup";

	/** メニュー画面 */
	public static final String MENU = "/menu";
	
	/** ユーザ一覧画面 */
	public static final String USER_LIST = "/userList";
	
	/** 認証不要画面 */
	public static final String[] NO_AUTHENTICATION = { LOGIN, SIGNUP, "/webjars/**", "/css/**" };
}
