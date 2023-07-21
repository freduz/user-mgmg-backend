package com.decadis.task.usermgmt.payload;


import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class ErrorResponse {
  private Date timestamp;
  private String message;
  private String details;
}
