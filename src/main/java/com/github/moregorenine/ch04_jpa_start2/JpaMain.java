package com.github.moregorenine.ch04_jpa_start2;

import javax.persistence.*;
import java.util.List;

/**
 * @author holyeye
 */
public class JpaMain {

    public static void main(String[] args) {

        //엔티티 매니저 팩토리 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager(); //엔티티 매니저 생성

        EntityTransaction tx = em.getTransaction(); //트랜잭션 기능 획득

        try {


            tx.begin(); //트랜잭션 시작
            logic(em);  //비즈니스 로직
            tx.commit();//트랜잭션 커밋

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        } finally {
            em.close(); //엔티티 매니저 종료
        }

        emf.close(); //엔티티 매니저 팩토리 종료
    }

    public static void logic(EntityManager em) {

        String id = "id1";
        MemberCh04 memberCh04 = new MemberCh04();
        memberCh04.setId(id);
        memberCh04.setUsername("지한");
        memberCh04.setAge(2);

        //등록
        em.persist(memberCh04);

        //수정
        memberCh04.setAge(20);

        //한 건 조회
        MemberCh04 findMemberCh04 = em.find(MemberCh04.class, id);
        System.out.println("findMember=" + findMemberCh04.getUsername() + ", age=" + findMemberCh04.getAge());

        //목록 조회
        List<MemberCh04> memberCh04s = em.createQuery("select m from Member m", MemberCh04.class).getResultList();
        System.out.println("members.size=" + memberCh04s.size());

        //삭제
        em.remove(memberCh04);

    }
}
