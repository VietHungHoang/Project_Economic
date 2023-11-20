package com.example.project_economic.repository;

import com.example.project_economic.entity.CartItemEntity;
import com.example.project_economic.entity.ProductEntity;
import com.example.project_economic.entity.UserEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItemEntity,Long> {

    List<CartItemEntity>findByUser(UserEntity user);
    CartItemEntity findByUserAndProduct(UserEntity user, ProductEntity product);
    @Query(value = "select count(c.id) from cart_items as c where c.user_id=?1",nativeQuery = true)
    Long countCart(Long userId);
    @Query(value = "select count(c.id) from cart_items as c where c.product_id=?1 and c.user_id =?2",nativeQuery = true)
    Long countCartByProductIdAndUserId(Long productId,Long userId);
    
    @Modifying
    @Transactional
    @Query(value = "delete from cart_items as c where c.user_id=:userId",nativeQuery = true)
    void deleteAllCartByUserId(@Param("userId") Long userId);

}
