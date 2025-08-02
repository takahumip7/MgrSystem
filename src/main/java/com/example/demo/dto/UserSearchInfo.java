package com.example.demo.dto;

import com.example.demo.constant.AuthorityKind;
import com.example.demo.constant.UserStatusKind;

import lombok.Data;

/**
 * ユーザー一覧画面検索用DTOクラス
 */
@Data
public class UserSearchInfo {
	
	/** ログインID */
	private String loginId;
	
	/** アカウント状態種別 */
	private UserStatusKind userStatusKind;
	
	/** ユーザ権限種別 */
	private AuthorityKind authorityKind;
}
