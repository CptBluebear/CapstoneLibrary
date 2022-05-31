package org.corodiak.capstonelibrary.repository;

import java.util.List;
import java.util.Optional;

import org.corodiak.capstonelibrary.type.entity.OAuthUser;
import org.corodiak.capstonelibrary.type.etc.OAuthProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OAuthUserRepository extends JpaRepository<OAuthUser, Long> {

	Optional<OAuthUser> findByProviderUserIdAndOap(String providerUserId, OAuthProvider oap);
	List<OAuthUser> findByUserSeq(Long userSeq);

}
