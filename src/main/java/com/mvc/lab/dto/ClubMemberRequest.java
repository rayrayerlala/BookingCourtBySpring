package com.mvc.lab.dto;

import lombok.Data;

//請求中的 JSON 資料模型
@Data
public class ClubMemberRequest {
 private String clubMemberUsername;
 private String clubMemberPassword;
 private String clubMemberName;
 private String clubMemberBirth;
}
