package org.corodiak.capstonelibrary.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.corodiak.capstonelibrary.Exception.SearchResultNotExistException;
import org.corodiak.capstonelibrary.repository.BookLogRepository;
import org.corodiak.capstonelibrary.type.entity.Book;
import org.corodiak.capstonelibrary.type.entity.BookLog;
import org.corodiak.capstonelibrary.type.entity.Group;
import org.corodiak.capstonelibrary.type.entity.User;
import org.corodiak.capstonelibrary.type.etc.BookLogStatus;
import org.corodiak.capstonelibrary.type.vo.BookLogVo;
import org.corodiak.capstonelibrary.type.vo.BookVo;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookLogServiceImpl implements BookLogService {

	private final BookLogRepository bookLogRepository;

	@Override
	public boolean addBookLog(BookLogStatus bookLogStatus, Long userSeq, Long bookSeq, Long groupSeq) {
		BookLog bookLog = BookLog.builder()
			.bookLogStatus(bookLogStatus)
			.user(User.builder().seq(userSeq).build())
			.book(Book.builder().seq(bookSeq).build())
			.group(Group.builder().seq(groupSeq).build())
			.build();
		bookLog = bookLogRepository.save(bookLog);
		return true;
	}

	@Override
	public List<BookLogVo> findAll() {
		List<BookLog> bookLogList = bookLogRepository.findAll();
		List<BookLogVo> results = bookLogList.stream()
			.map(e -> new BookLogVo.BookLogVoWithUserAndBookAndGroup(e))
			.collect(Collectors.toList());
		return results;
	}

	@Override
	public BookLogVo findBySeq(Long seq) {
		Optional<BookLog> bookLog = bookLogRepository.findBySeq(seq);
		if (bookLog.isPresent()) {
			return new BookLogVo.BookLogVoWithUserAndBookAndGroup(bookLog.get());
		}
		throw new SearchResultNotExistException();
	}

	@Override
	public List<BookLogVo> findByUserSeq(Long seq) {
		List<BookLog> bookLogList = bookLogRepository.findByUserSeq(seq);
		List<BookLogVo> results = bookLogList.stream()
			.map(e -> new BookLogVo.BookLogVoWithUserAndBookAndGroup(e))
			.collect(Collectors.toList());
		return results;
	}

	@Override
	public List<BookLogVo> findByBookSeq(Long seq) {
		List<BookLog> bookLogList = bookLogRepository.findByBookSeq(seq);
		List<BookLogVo> results = bookLogList.stream()
			.map(e -> new BookLogVo.BookLogVoWithUserAndBookAndGroup(e))
			.collect(Collectors.toList());
		return results;
	}

	@Override
	public List<BookLogVo> findByGroupSeq(Long seq) {
		List<BookLog> bookLogList = bookLogRepository.findByGroupSeq(seq);
		List<BookLogVo> results = bookLogList.stream()
			.map(e -> new BookLogVo.BookLogVoWithUserAndBookAndGroup(e))
			.collect(Collectors.toList());
		return results;
	}

	@Override
	public List<BookLogVo> findByGroupAndUserSeq(Long groupSeq, Long userSeq) {
		List<BookLog> bookLogList = bookLogRepository.findByGroupAndUserSeq(groupSeq, userSeq);
		List<BookLogVo> results = bookLogList.stream()
			.map(e -> new BookLogVo.BookLogVoWithUserAndBookAndGroup(e))
			.collect(Collectors.toList());
		return results;
	}

	@Override
	public List<BookVo> findMyBorrow(Long userSeq, Long start, Long display) {
		List<BookLog> bookLogList = bookLogRepository.findMyBorrow(userSeq, start, display);
		List<BookVo> results = bookLogList.stream()
			.map(e -> new BookVo.BookVoWithUserAndGroup(e.getBook()))
			.collect(Collectors.toList());
		return results;
	}

	@Override
	public boolean deleteBySeq(Long seq) {
		Long result = bookLogRepository.deleteBySeq(seq);
		return result == 1;
	}

	@Override
	public boolean returnBookLog(Long userSeq, Long bookSeq) {
		Long result = bookLogRepository.returnBookLog(userSeq, bookSeq);
		if (result == 0) {
			throw new SearchResultNotExistException();
		}
		return result == 1;
	}
}
