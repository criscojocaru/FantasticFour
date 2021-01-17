package com.mps.reserveme.dto;

import com.mps.reserveme.service.ServiceMessages;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthDto {
    private String uid;
    private String timeStamp;
    private String message;

    /**
     * @param userId
     * @param timeStamp
     * @return
     */
    public String registerUserDtoMessage(String userId, String timeStamp) {
        return String.format(ServiceMessages.REGISTER_USER_SUCCESS.getValue(), userId, timeStamp);
    }
}
