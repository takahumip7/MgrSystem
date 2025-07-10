package com.example.demo.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.demo.constant.UrlConst;

import lombok.RequiredArgsConstructor;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig {
	
	/** パスワードエンコーダー */
	private final PasswordEncoder passwordEncoder;
	
	/** ユーザ情報取得 */
	private final UserDetailsService userDetailsService;
	
	/** メッセージソース */
	private final MessageSource messageSource;
	
	/** ユーザ名のname属性 */
	private final String USERNAME_PARAMETER = "loginId";

	/**
	 * Spring Securityカスタマイズ用
	 * 
	 * @param http カスタマイズパラメータ
	 * @return カスタマイズ結果
	 * @throws Exception 予期せぬ例外
	 */
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
		.authorizeHttpRequests(
				authorize -> authorize.requestMatchers(UrlConst.NO_AUTHENTICATION).permitAll().anyRequest().authenticated())
		.formLogin(
				login -> login.loginPage(UrlConst.LOGIN) //自作ログイン画面(Controller)を作るための設定
				.usernameParameter(USERNAME_PARAMETER) //ユーザ名パラメータのname属性
				.defaultSuccessUrl(UrlConst.MENU)); //ログイン成功後のリダイレクトURL
//		.logout(logout -> logout.logoutSuccessUrl(UrlConst.SIGNUP)); //ログアウト遷移先設定
		return http.build();
	}
	
	/**
	 * Provider定義
	 * 
	 * @return カスタマイズProvider情報
	 */
	@Bean
	AuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(passwordEncoder);
		provider.setMessageSource(messageSource);

		return provider;
	}
}
