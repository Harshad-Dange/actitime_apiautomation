package com.cybersuccess.actitime.entities;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerInfo {
    private String name;
    private String description;
    private boolean archived;
}
