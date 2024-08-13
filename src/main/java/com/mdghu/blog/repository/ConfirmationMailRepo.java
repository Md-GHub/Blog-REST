package com.mdghu.blog.repository;

import com.mdghu.blog.entity.ConfirmationMail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ConfirmationMailRepo extends JpaRepository<ConfirmationMail, Long> {

    @Query(
            value = "SELECT count(*) FROM CONFIRMATION_MAIL WHERE MAIL = :mail AND OTP =:OTP",
            nativeQuery = true
    )
    public Long check(String mail , String OTP);

    public void deleteByMail(String mail);
}
