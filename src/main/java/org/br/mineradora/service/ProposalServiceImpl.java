package org.br.mineradora.service;

import org.br.mineradora.dto.ProposalDTO;
import org.br.mineradora.dto.ProposalDetailsDTO;
import org.br.mineradora.entity.ProposalEntity;
import org.br.mineradora.message.KafkaEvent;
import org.br.mineradora.repository.ProposalRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@ApplicationScoped
public class ProposalServiceImpl implements  ProposalService {

    @Inject
    ProposalRepository proposalRepository;

    @Inject
    KafkaEvent kafkaMenssages;


    @Override
    public ProposalDetailsDTO findFullProposal(long id) {
        ProposalEntity proposal = proposalRepository.findById(id);
        return ProposalDetailsDTO.of(proposal);
    }

    @Override
    @Transactional
    public void createNewProposal(ProposalDetailsDTO proposalDetailsDTO) {
        ProposalDTO proposal = buildAndSaveNewProposal(proposalDetailsDTO);
        kafkaMenssages.sendNewKafkaEvent(proposal);
    }

    @Override
    public void removeProposal(long id) {
        proposalRepository.deleteById(id);
    }

    @Transactional
    private ProposalDTO buildAndSaveNewProposal(ProposalDetailsDTO proposalDetailsDTO) {

        proposalRepository.persist(ProposalDetailsDTO.of(proposalDetailsDTO));

        return ProposalDTO.builder()
                .proposalId(proposalRepository.findByCustomer(proposalDetailsDTO.getCustomer()).get().getId())
                .priceTonne(proposalDetailsDTO.getPriceTonne())
                .customer(proposalDetailsDTO.getCustomer())
                .build();
    }
}
