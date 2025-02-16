package org.likelion.newsfactbackend.auth.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class SignUpRequestDto {
    private String userName;
    private String nickName;
    private String password;
    private String phoneNumber;
    private String email;
    private String profileUrl;
    private String loginType;
    private Boolean useAble;
}
