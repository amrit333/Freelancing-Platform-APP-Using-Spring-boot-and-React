package com.freelancing.platform.dto.response;

import com.freelancing.platform.entity.enums.Role;
import lombok.Data;

@Data
public class UserDto {
    private String id;
    private String name;
    private String email;
    private Role role;
}
