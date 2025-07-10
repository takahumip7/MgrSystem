package com.example.demo.service;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.constant.AuthorityKind;
import com.example.demo.entity.UserInfo;
import com.example.demo.form.SignupForm;
import com.example.demo.repository.UserInfoRepository;

import lombok.RequiredArgsConstructor;

/**
 * ユーザ登録画面 Service
 */
@Service
@RequiredArgsConstructor
public class SignupService {

	private final UserInfoRepository repository;
	
	/** PasswordEncoder */
	private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
	/**
	 * ユーザ情報テーブル 新規登録
	 * @param form 入力情報
	 * @return 登録情報（ユーザ情報Entity、既に同じユーザIDで登録がある場合はEmpty
	 */
	public Optional<UserInfo> registUserInfo(SignupForm form){
		Optional<UserInfo> userInfoExisted = repository.findById(form.getLoginId());
		if (userInfoExisted.isPresent()) {
			return Optional.empty();
		}
		
		UserInfo userInfo = new UserInfo();
		userInfo.setLoginId(form.getLoginId());
		userInfo.setPassword(form.getPassword());
		String encodedPassword = passwordEncoder.encode(form.getPassword()); //ハッシュ化パスワードの生成
		userInfo.setPassword(encodedPassword);
		userInfo.setAuthority(AuthorityKind.ITEM_WATCHER.getAuthorityKind());

		return Optional.of(repository.save(userInfo));
	}
}
