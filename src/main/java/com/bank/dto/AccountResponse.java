package com.bank.dto;

import java.util.List;

public class AccountResponse {
	
    private List<AccountDto> content;
    
    public AccountResponse(List<AccountDto> content) {
        this.content = content;
    }

    // Getter and Setter
    public List<AccountDto> getContent() {
        return content;
    }

    public void setContent(List<AccountDto> content) {
        this.content = content;
    }
    

}
