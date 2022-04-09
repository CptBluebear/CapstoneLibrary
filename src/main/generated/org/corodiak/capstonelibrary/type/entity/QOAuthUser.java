package org.corodiak.capstonelibrary.type.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOAuthUser is a Querydsl query type for OAuthUser
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOAuthUser extends EntityPathBase<OAuthUser> {

    private static final long serialVersionUID = -925537159L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOAuthUser oAuthUser = new QOAuthUser("oAuthUser");

    public final QBaseTimeEntity _super = new QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final StringPath email = createString("email");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedDate = _super.lastModifiedDate;

    public final StringPath name = createString("name");

    public final EnumPath<org.corodiak.capstonelibrary.type.etc.OAuthProvider> oap = createEnum("oap", org.corodiak.capstonelibrary.type.etc.OAuthProvider.class);

    public final StringPath providerUserId = createString("providerUserId");

    public final NumberPath<Long> seq = createNumber("seq", Long.class);

    public final QUser user;

    public QOAuthUser(String variable) {
        this(OAuthUser.class, forVariable(variable), INITS);
    }

    public QOAuthUser(Path<? extends OAuthUser> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOAuthUser(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOAuthUser(PathMetadata metadata, PathInits inits) {
        this(OAuthUser.class, metadata, inits);
    }

    public QOAuthUser(Class<? extends OAuthUser> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

