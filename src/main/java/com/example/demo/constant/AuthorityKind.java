package com.example.demo.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AuthorityKind {

	/** 商品情報の確認が可能 */
	ITEM_WATCHER("1"),
	
	/** 商品情報の確認、更新が可能 */
	ITEM_MANAGER("2"),
	
	/** 商品情報の確認、更新、全ユーザ情報の管理が可能 */
	ITEM_AND_USER_MANAGER("3");
	
	private String AuthorityKind;
}
