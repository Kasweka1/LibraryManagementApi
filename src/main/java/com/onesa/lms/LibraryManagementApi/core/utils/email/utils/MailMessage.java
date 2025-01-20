package com.onesa.lms.LibraryManagementApi.core.utils.email.utils;


import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@NoArgsConstructor
@AllArgsConstructor
@Data
public class MailMessage implements Serializable{
    private String to;
    private String subject;
    private String content;
}
