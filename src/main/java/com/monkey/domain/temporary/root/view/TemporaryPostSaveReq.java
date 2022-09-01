package com.monkey.domain.temporary.root.view;

import com.monkey.view.UserSession;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class TemporaryPostSaveReq extends UserSession {
    private String content;
}
