package com.ticketevent.auth.service;

import com.ticketevent.entity.dto.request.AuthRequestDto;
import com.ticketevent.entity.dto.response.AuthResponseDto;

public interface IAuthService {
    AuthResponseDto authenticate(AuthRequestDto authRequestDto);


}
