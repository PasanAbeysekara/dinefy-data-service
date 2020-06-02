package com.solution.x.repo;

import com.solution.x.dao.Promotion;
import com.solution.x.dao.key.PromoID;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Tharinda Wickramaarachchi
 * @since 6/2/2020 1:34 PM
 */
public interface PromotionRepository extends JpaRepository<Promotion, PromoID>
{
}
