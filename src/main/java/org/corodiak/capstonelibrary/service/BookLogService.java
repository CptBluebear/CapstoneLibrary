package org.corodiak.capstonelibrary.service;

import java.util.List;

import org.corodiak.capstonelibrary.type.etc.BookLogStatus;
import org.corodiak.capstonelibrary.type.vo.BookLogVo;
import org.corodiak.capstonelibrary.type.vo.BookVo;

public interface BookLogService {
	List<BookLogVo> findAll();

	BookLogVo findBySeq(Long seq);

	boolean addBookLog(BookLogStatus bookLogStatus, Long userSeq, Long bookSeq, Long groupSeq);

	List<BookLogVo> findByUserSeq(Long seq);

	List<BookLogVo> findByGroupSeq(Long seq);

	List<BookLogVo> findByBookSeq(Long seq);

	List<BookLogVo> findByGroupAndUserSeq(Long groupSeq, Long userSeq);

	List<BookVo> findMyBorrow(Long userSeq, Long start, Long display);

	boolean deleteBySeq(Long seq);

	boolean returnBookLog(Long userSeq, Long bookSeq);
}
