package org.corodiak.capstonelibrary.repository;

import javax.persistence.EntityManager;

import org.corodiak.capstonelibrary.type.entity.QBookLog;
import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class BookLogRepositoryImpl implements BookLogRepository {
	private final EntityManager entityManager;
	private final JPAQueryFactory jpaQueryFactory;
	private QBookLog qBookLog = QBookLog.bookLog;


}
