package org.corodiak.capstonelibrary.type.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = 33420308L;

    public static final QUser user = new QUser("user");

    public final QBaseTimeEntity _super = new QBaseTimeEntity(this);

    public final ListPath<Group, QGroup> adminList = this.<Group, QGroup>createList("adminList", Group.class, QGroup.class, PathInits.DIRECT2);

    public final ListPath<Book, QBook> bookList = this.<Book, QBook>createList("bookList", Book.class, QBook.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final ListPath<GroupUser, QGroupUser> groups = this.<GroupUser, QGroupUser>createList("groups", GroupUser.class, QGroupUser.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedDate = _super.lastModifiedDate;

    public final StringPath nickname = createString("nickname");

    public final EnumPath<org.corodiak.capstonelibrary.type.etc.Role> role = createEnum("role", org.corodiak.capstonelibrary.type.etc.Role.class);

    public final NumberPath<Long> seq = createNumber("seq", Long.class);

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

