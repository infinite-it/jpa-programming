package com.github.moregorenine.ch02_jpa_start1;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {

        //엔티티 매니저 팩토리 생성
        //애플리케이션 전체에서 딱 한 번만 생성하고 공유해서 사용해야 합니다.
        //persistence.xml에서 이름이 'jpa-programming'인 persistence-unit을 찾아서 엔티티 매니저 팩토리를 생성합니다.
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-programming");
        //엔티티 매니저 생성
        //엔티티를 DB에 CRUD 할 수 있습니다.
        //DB connection과 밀접한 관련이 있으므로 스레드간에 공유하거나 재사용하면 안됩니다.
        EntityManager em = emf.createEntityManager();
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
            emf.close(); //엔티티 매니저 팩토리 종료
        }
    }

    public static void logic(EntityManager em) {

        String id = "id1";
        MemberCh02 memberCh02 = new MemberCh02();
        memberCh02.setId(id);
        memberCh02.setUsername("moregorenine");
        memberCh02.setAge(2);

        //등록
        em.persist(memberCh02);

        //수정
        memberCh02.setAge(20);

        //한 건 조회
        MemberCh02 findMemberCh02 = em.find(MemberCh02.class, id);
        System.out.println("findMember=" + findMemberCh02.getUsername() + ", age=" + findMemberCh02.getAge());

        //목록 조회
        //JPA는 SQL을 추상화한 JPQL(Java Persistence Query Language)이라는 객체지향 쿼리 언어를 제공합니다.
        List<MemberCh02> memberCh02s = em.createQuery("select m from MemberCh02 m", MemberCh02.class).getResultList();
        System.out.println("members.size=" + memberCh02s.size());

        //삭제
        em.remove(memberCh02);
    }

}
