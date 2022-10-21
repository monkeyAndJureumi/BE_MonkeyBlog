package com.monkey.context.post.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.monkey.context.temp_post.domain.TempPostId;
import com.monkey.dto.UserSessionDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PostSaveDto extends UserSessionDto {
    @JsonProperty(value = "temp_post_id")
    @Schema(description = "임시 게시글 삭제를 위한 아이디 값")
    private String tempPostId;

    @JsonProperty(value = "content")
    @Schema(description = "게시글 내용")
    private String content;

    @JsonProperty(value = "is_secret")
    @Schema(description = "게시글 숨김 여부")
    private boolean isSecrete;

    PostSaveDto(String tempPostId, String content, boolean isSecrete) {
        this.tempPostId = tempPostId;
        this.content = content;
        this.isSecrete = isSecrete;
    }

    public TempPostId getTempPostId() {
        return new TempPostId(tempPostId);
    }

    public String getContent() {
        return content;
    }

    public boolean getIsSecrete() {
        return isSecrete;
    }

}
