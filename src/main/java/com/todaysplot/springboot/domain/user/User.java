package com.todaysplot.springboot.domain.user;

import com.todaysplot.springboot.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Table(name = "users") // 'user' 대신 'users' 사용
@Entity
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String picture;

    @Enumerated(EnumType.STRING)  // JPA로 데이터 저장할 때 Enum 값을 어떤 형태로 저장할지 결정. 기본은 int
    @Column(nullable = false)
    private Role role;

    @Builder
    public User(String name, String email, String picture, Role role) {
        this.name=name;
        this.email=email;
        this.picture=picture;
        this.role=role;
    }

    public User update(String name, String picture) {
        this.name=name;
        this.picture=picture;

        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }
}
