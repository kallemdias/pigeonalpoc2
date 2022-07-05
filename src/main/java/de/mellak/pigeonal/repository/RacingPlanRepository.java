package de.mellak.pigeonal.repository;

import de.mellak.pigeonal.domain.RacingPlan;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the RacingPlan entity.
 */
@Repository
public interface RacingPlanRepository extends JpaRepository<RacingPlan, Long> {
    @Query("select racingPlan from RacingPlan racingPlan where racingPlan.user.login = ?#{principal.username}")
    List<RacingPlan> findByUserIsCurrentUser();

    default Optional<RacingPlan> findOneWithEagerRelationships(Long id) {
        return this.findOneWithToOneRelationships(id);
    }

    default List<RacingPlan> findAllWithEagerRelationships() {
        return this.findAllWithToOneRelationships();
    }

    default Page<RacingPlan> findAllWithEagerRelationships(Pageable pageable) {
        return this.findAllWithToOneRelationships(pageable);
    }

    @Query(
        value = "select distinct racingPlan from RacingPlan racingPlan left join fetch racingPlan.user",
        countQuery = "select count(distinct racingPlan) from RacingPlan racingPlan"
    )
    Page<RacingPlan> findAllWithToOneRelationships(Pageable pageable);

    @Query("select distinct racingPlan from RacingPlan racingPlan left join fetch racingPlan.user")
    List<RacingPlan> findAllWithToOneRelationships();

    @Query("select racingPlan from RacingPlan racingPlan left join fetch racingPlan.user where racingPlan.id =:id")
    Optional<RacingPlan> findOneWithToOneRelationships(@Param("id") Long id);
}
