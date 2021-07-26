package com.github.moregorenine.ch02_jpa_start1;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MEMBER_CH02")
@Data
public class MemberCh02 {
    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "NAME")
    private String username;

    //매핑정보 없을 경우 필드명을 사용해서 컬럼명으로 자동 매핑합니다.
    private Integer age;
}
