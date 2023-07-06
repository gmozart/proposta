package org.br.mineradora.message;

import org.br.mineradora.dto.ProposalDTO;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class KafkaEvent {


    private final Logger  LOG = LoggerFactory.getLogger(KafkaEvent.class);

    @Channel("proposal")
    Emitter<ProposalDTO> proposalRequestEmmiter;

    public void sendNewKafkaEvent(ProposalDTO proposalDTO){
        LOG.info("-- Enviando nova proposta para o tópico Kafka --");
        proposalRequestEmmiter.send(proposalDTO).toCompletableFuture().join();
    }

    @Incoming("proposal")
    public void NewKafkaEvent(ProposalDTO proposalDTO){
        LOG.info("-- Enviando nova proposta para o tópico Kafka --");
        LOG.info(proposalDTO.toString());
    }


}
