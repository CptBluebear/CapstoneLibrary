package org.corodiak.capstonelibrary.service;

import java.util.List;

import org.corodiak.capstonelibrary.type.vo.GroupVo;

public interface GroupService {

	GroupVo findBySeq(long seq);

	List<GroupVo> findOpenGroup();

	boolean removeGroup(long seq);

	List<GroupVo> findByUserSeq(long userSeq);

	Long findByAuthenticationCode(String authenticationCode);

	boolean addGroup(String name, boolean isOpen, String thumbnail, long userSeq, double longtitude, double latitude);

	boolean authorizeAdmin(Long groupSeq, Long userSeq);
}
