package org.corodiak.capstonelibrary.type.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QIsbnData is a Querydsl query type for IsbnData
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QIsbnData extends EntityPathBase<IsbnData> {

    private static final long serialVersionUID = 1194929033L;

    public static final QIsbnData isbnData = new QIsbnData("isbnData");

    public final QBaseTimeEntity _super = new QBaseTimeEntity(this);

    public final StringPath author = createString("author");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final StringPath description = createString("description");

    public final StringPath isbn = createString("isbn");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedDate = _super.lastModifiedDate;

    public final DatePath<java.time.LocalDate> publishDate = createDate("publishDate", java.time.LocalDate.class);

    public final StringPath publisher = createString("publisher");

    public final NumberPath<Long> Seq = createNumber("Seq", Long.class);

    public final StringPath title = createString("title");

    public QIsbnData(String variable) {
        super(IsbnData.class, forVariable(variable));
    }

    public QIsbnData(Path<? extends IsbnData> path) {
        super(path.getType(), path.getMetadata());
    }

    public QIsbnData(PathMetadata metadata) {
        super(IsbnData.class, metadata);
    }

}

