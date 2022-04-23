package org.corodiak.capstonelibrary.type.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBookLog is a Querydsl query type for BookLog
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBookLog extends EntityPathBase<BookLog> {

    private static final long serialVersionUID = -596131150L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBookLog bookLog = new QBookLog("bookLog");

    public final QBaseTimeEntity _super = new QBaseTimeEntity(this);

    public final QBook book;

    public final EnumPath<org.corodiak.capstonelibrary.type.etc.BookLogStatus> bookLogStatus = createEnum("bookLogStatus", org.corodiak.capstonelibrary.type.etc.BookLogStatus.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final QGroup group;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedDate = _super.lastModifiedDate;

    public final NumberPath<Long> seq = createNumber("seq", Long.class);

    public final QUser user;

    public QBookLog(String variable) {
        this(BookLog.class, forVariable(variable), INITS);
    }

    public QBookLog(Path<? extends BookLog> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBookLog(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBookLog(PathMetadata metadata, PathInits inits) {
        this(BookLog.class, metadata, inits);
    }

    public QBookLog(Class<? extends BookLog> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.book = inits.isInitialized("book") ? new QBook(forProperty("book"), inits.get("book")) : null;
        this.group = inits.isInitialized("group") ? new QGroup(forProperty("group"), inits.get("group")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

