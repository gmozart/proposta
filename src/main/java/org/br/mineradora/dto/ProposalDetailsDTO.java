package org.br.mineradora.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;
import org.br.mineradora.entity.ProposalEntity;

import java.math.BigDecimal;

@Builder
@Data
@Jacksonized
@AllArgsConstructor
public class ProposalDetailsDTO {

    private Long proposalId;
    private String customer;
    private BigDecimal priceTonne;
    private Integer tonnes;
    private String country;
    private Integer proposalValidityDays;

    public static ProposalDetailsDTO of(ProposalEntity proposalEntity){
        return ProposalDetailsDTO.builder()
                .proposalId(proposalEntity.getId())
                .customer(proposalEntity.getCustomer())
                .priceTonne(proposalEntity.getPriceTonne())
                .tonnes(proposalEntity.getTonnes())
                .country(proposalEntity.getCountry())
                .proposalValidityDays(proposalEntity.getProposalValidityDays())
                .build();
    }

    public static ProposalEntity of(ProposalDetailsDTO proposalDetailsDTO){
        return ProposalEntity.builder()
                .id(proposalDetailsDTO.getProposalId())
                .customer(proposalDetailsDTO.getCustomer())
                .priceTonne(proposalDetailsDTO.getPriceTonne())
                .tonnes(proposalDetailsDTO.getTonnes())
                .country(proposalDetailsDTO.getCountry())
                .proposalValidityDays(proposalDetailsDTO.getProposalValidityDays())
                .build();
    }


}