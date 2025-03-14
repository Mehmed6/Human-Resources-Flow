package com.doganmehmet.app.mapper;

import com.doganmehmet.app.dto.tokens.JwtTokenDTO;
import com.doganmehmet.app.entity.JwtToken;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(implementationName = "JwtMapperImpl", componentModel = "spring")
public interface IJwtTokenMapper {

    @Mapping(target = "refreshToken", source = "refreshToken.refreshToken")
    @Mapping(target = "username", source = "user.username")
    @Mapping(target = "accessToken", source = "jwtToken")
    JwtTokenDTO toJwtTokenDTO(JwtToken jwtToken);
}
