package com.nigma.uktfinancialaidapp.model.response;

import com.nigma.uktfinancialaidapp.constant.ERole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class RoleResponse {
    private String id;
    private ERole role;
}
