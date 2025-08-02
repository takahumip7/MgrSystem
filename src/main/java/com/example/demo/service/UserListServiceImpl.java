package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.constant.ExecuteResult;
import com.example.demo.dto.UserListInfo;
import com.example.demo.dto.UserSearchInfo;
import com.example.demo.entity.UserInfo;
import com.example.demo.form.UserListForm;
import com.example.demo.repository.UserInfoRepository;
import com.example.demo.util.AppUtil;

import lombok.RequiredArgsConstructor;

/**
 * ユーザー一覧画面Service実装クラス
 */
@Service
@RequiredArgsConstructor
public class UserListServiceImpl implements UserListService{
	
	/** ユーザー情報テーブルDAO */
	private final UserInfoRepository repository;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UserListInfo> editUserList() {
		return toUserListInfos(repository.findAll());
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UserListInfo> editUserListByParam(UserSearchInfo dto) {
		return toUserListInfos(findUserInfoByParam(dto));
	}

	/**
	 * ユーザー情報EntityのListをユーザー一覧情報DTOのListに変換します。
	 * 
	 * @param userInfos ユーザー情報EntityのList
	 * @return ユーザ一覧情報DTOのList
	 */
	private List<UserListInfo> toUserListInfos(List<UserInfo> userInfos) {
		ArrayList<UserListInfo> userListInfos = new ArrayList<UserListInfo>();
		for (UserInfo userInfo : userInfos) {
			UserListInfo userListInfo = new UserListInfo();
			userListInfo.setLoginId(userInfo.getLoginId());
			userListInfo.setLoginFailureCount(userInfo.getLoginFailureCount());
			userListInfo.setAccountLockedTime(userInfo.getAccountLockedTime());
			userListInfo.setStatus(userInfo.getUserStatusKind().getDisplayValue());
			userListInfo.setAuthority(userInfo.getAuthorityKind().getDisplayValue());
			userListInfo.setCreateTime(userInfo.getCreateTime());
			userListInfo.setUpdateTime(userInfo.getUpdateTime());
			userListInfo.setUpdateUser(userInfo.getUpdateUser());
			userListInfos.add(userListInfo);
		}
		return userListInfos;
	}
	
	private UserInfo convertFormToUserInfo(UserListForm form) {
		UserInfo userInfo = new UserInfo();
		userInfo.setLoginId(form.getLoginId());
		userInfo.setUserStatusKind(form.getUserStatusKind());
		userInfo.setAuthorityKind(form.getAuthorityKind());
		return userInfo;
	}
	
	/**
	 * ユーザー情報EntityのListをユーザー一覧情報DTOのListに変換します。
	 * 
	 * @param userInfos ユーザー情報EntityのList
	 * @return ユーザ一覧情報DTOのList
	 */
	private List<UserInfo> findUserInfoByParam(UserSearchInfo dto) {
		String loginIdParam = AppUtil.addWildcard(dto.getLoginId());
		
		if (dto.getUserStatusKind() != null && dto.getAuthorityKind() != null) {
			return repository.findByLoginIdLikeAndUserStatusKindAndAuthorityKind(loginIdParam, dto.getUserStatusKind(), dto.getAuthorityKind());
		} else if (dto.getUserStatusKind() != null) {
			return repository.findByLoginIdLikeAndUserStatusKind(loginIdParam, dto.getUserStatusKind());
		} else if (dto.getAuthorityKind() != null) {
			return repository.findByLoginIdLikeAndAuthorityKind(loginIdParam, dto.getAuthorityKind());
		} else {
			return repository.findByLoginIdLike(loginIdParam);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ExecuteResult deleteUserInfoById(String loginId) {
		Optional<UserInfo> userInfo = repository.findById(loginId);
		if(userInfo.isEmpty()) {
			return ExecuteResult.ERROR;
		}
		repository.deleteById(loginId);
		
		return ExecuteResult.SUCCEED;
	}
}
