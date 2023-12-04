package com.example.demo.application.port.in.command;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.SelfValidating;

@Data
@Builder @NoArgsConstructor
public class FindMemberByAddressCommand extends SelfValidating<FindMemberByAddressCommand> {

    private String addressName;

    public FindMemberByAddressCommand(String addressName){
        this.addressName = addressName;
        this.validateSelf();
    }

}
