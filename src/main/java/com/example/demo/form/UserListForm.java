package com.example.demo.form;

import org.hibernate.validator.constraints.Length;

import com.example.demo.constant.AuthorityKind;
import com.example.demo.constant.UserStatusKind;

import lombok.Data;

/**
 * ユーザー登録画面Formクラス
 */
@Data
public class UserListForm {
	
	/** ログインID */
	@Length(min = 8, max = 20)
	private String loginId;

	/** アカウント状態種別 */
	private UserStatusKind userStatusKind;
	
	/** ユーザ権限種別 */
	private AuthorityKind authorityKind;
	
	/** ユーザ一覧情報から選択されたログインID */
	private String selectedLoginId;
	
	/** 
	 * ユーザ一覧情報から選択されたログインIDをクリアします。
	 * 
	 * @return ユーザ一覧情報から選択されたログインIDクリア後のインスタンス
	 */
	public UserListForm clearSelectedLoginId() {
		this.selectedLoginId = null;
		return this;
	}
}
