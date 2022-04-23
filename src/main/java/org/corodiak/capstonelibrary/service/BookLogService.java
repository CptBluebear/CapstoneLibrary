package org.corodiak.capstonelibrary.service;

import java.util.List;

import org.corodiak.capstonelibrary.type.etc.BookLogStatus;
import org.corodiak.capstonelibrary.type.vo.BookLogVo;

public interface BookLogService {
	List<BookLogVo> findAll();

	BookLogVo findBySeq(Long seq);

	boolean addBookLog(BookLogStatus bookLogStatus, Long userSeq, Long bookSeq, Long groupSeq);

	List<BookLogVo> findByUserSeq(Long seq);

	List<BookLogVo> findByGroupSeq(Long seq);

	List<BookLogVo> findByBookSeq(Long seq);

	List<BookLogVo> findByGroupAndUserSeq(Long groupSeq, Long userSeq);

	boolean deleteBySeq(Long seq);
}
