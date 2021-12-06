package io.github.nguyenhaminhtuan.springbootsocialapi.mapper;

import io.github.nguyenhaminhtuan.springbootsocialapi.dto.response.UserResponse;
import io.github.nguyenhaminhtuan.springbootsocialapi.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserResponse toUserResponse(User user);
}
