package org.corodiak.capstonelibrary.service;

import java.util.List;

import org.corodiak.capstonelibrary.type.vo.GroupVo;

public interface GroupService {

	GroupVo findBySeq(long seq);

	List<GroupVo> findOpenGroup();

	boolean removeGroup(long seq);

	List<GroupVo> findByUserSeq(long userSeq);

	Long findByAuthenticationCode(String authenticationCode);

	boolean addGroup(String name, boolean isOpen, String thumbnail, String description, long userSeq, double longtitude,
		double latitude);

	boolean authorizeAdmin(Long groupSeq, Long userSeq);

	List<GroupVo> searchGroup(String keyword);

	List<GroupVo> searchGroupByKeywordAndLocation(String keyword, double longitude, double latitude, int distance);

	String getAuthenticationCode(Long seq, Long userSeq);
}
