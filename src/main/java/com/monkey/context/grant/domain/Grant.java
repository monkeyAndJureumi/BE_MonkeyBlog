package com.monkey.context.grant.domain;

import com.monkey.context.member.domain.MemberId;
import com.monkey.context.grant.enums.GrantGroup;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity @Table(name = "member_grant")
@SequenceGenerator(name = "MEMBER_ROLE_GEN", sequenceName = "ROLE_SEQ", initialValue = 1, allocationSize = 50)
public class Grant {
    @Id
    @Column(name = "authority_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_ROLE_GEN")
    private Long id;

    @AttributeOverride(name = "id", column = @Column(name = "member_id", unique = true, nullable = false))
    private MemberId memberId;

    @Enumerated(EnumType.STRING)
    private GrantGroup grantGroup;
}
