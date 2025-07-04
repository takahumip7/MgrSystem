package com.example.demo.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.UserInfo;
import com.example.demo.repository.UserInfoRepository;

import lombok.RequiredArgsConstructor;

/**
 * ログイン画面 Service
 */
@Service
@RequiredArgsConstructor
public class LoginService {

	private final UserInfoRepository repository;
	
	/**
	 * ユーザ情報テーブル 主キー検索
	 * @param loginId ログインID
	 * @return 検索結果（１件）
	 */
	public Optional<UserInfo> searchUserById(String loginId){
		return repository.findById(loginId);
	}
}
