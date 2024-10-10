package com.pickle.pickledemo.mapper;

import com.pickle.pickledemo.dto.AccountDto;
import com.pickle.pickledemo.entity.Account;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    private final ModelMapper mapper;

    public AccountMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public Account convertToEntity(AccountDto dto) {
        return mapper.map(dto,Account.class);
    }

    public AccountDto convertToDto(Account account) {
        return mapper.map(account,AccountDto.class);
    }

}
