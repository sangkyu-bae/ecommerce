package org.example.ranking.application.port.in.command;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.example.SelfValidating;

import javax.validation.constraints.NotNull;

@Builder
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class BulkRegisterRankingCommand extends SelfValidating<BulkRegisterRankingCommand> {

    @NotNull
    private Long productId;

    private int clickNum;

    private int saleNum;

    public BulkRegisterRankingCommand(long productId, int clickNum, int saleNum){
        this.productId= productId;
        this.clickNum = clickNum;
        this.saleNum = saleNum;
        this.validateSelf();
    }
}
