package com.pickle.pickledemo.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RoleDto {

    private int id;

    private String roleName;

    private List<ClaimDto> roleClaims;

    public RoleDto(String roleName, List<ClaimDto> roleClaims) {
        this.roleName = roleName;
        this.roleClaims = roleClaims;
    }

    public RoleDto(String roleName) {
        this.roleName = roleName;
    }

    public RoleDto(int id, List<ClaimDto> roleClaims) {
        this.id = id;
        this.roleClaims = roleClaims;
    }

    @Override
    public String toString() {
        return "Role{" + "id=" + id +
                ", roleName='" + roleName + '\'' +
                ", roleClaims=" + roleClaims +
                '}';
    }
}
