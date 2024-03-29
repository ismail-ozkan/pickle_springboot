package com.pickle.pickledemo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClaimDto {

    private int id;

    private String claimName;

    private String claimType;

    public ClaimDto(String claimName, String claimType) {
        this.claimName = claimName;
        this.claimType = claimType;
    }

    @Override
    public String toString() {
        return "Claim{" + "id=" + id +
                ", claimName='" + claimName + '\'' +
                ", claimType='" + claimType + '\'' +
                '}';
    }
}
