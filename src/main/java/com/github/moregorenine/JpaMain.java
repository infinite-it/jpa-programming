package com.github.moregorenine;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {

        //엔티티 매니저 팩토리 생성
        //애플리케이션 전체에서 딱 한 번만 생성하고 공유해서 사용해야 합니다.
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

        String id = "id2";
        Member member = new Member();
        member.setId(id);
        member.setUsername("moregorenine");
        member.setAge(40);

        //등록
        em.persist(member);

        //수정
        member.setAge(20);

        //한 건 조회
        Member findMember = em.find(Member.class, id);
        System.out.println("findMember=" + findMember.getUsername() + ", age=" + findMember.getAge());

        //목록 조회
        List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList();
        System.out.println("members.size=" + members.size());

        //삭제
        em.remove(member);
    }

}
