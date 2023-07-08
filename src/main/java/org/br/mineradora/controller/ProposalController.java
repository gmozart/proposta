package org.br.mineradora.controller;

import org.br.mineradora.dto.ProposalDetailsDTO;
import org.br.mineradora.service.ProposalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/api/proposal")
public class ProposalController {


    @Inject
    ProposalService proposalSerice;

    private final Logger LOG = LoggerFactory.getLogger(ProposalController.class);

    @GET
    @Path("/{id}")
    public ProposalDetailsDTO findDetailsProposal(@PathParam("id") long id){
        return proposalSerice.findFullProposal(id);
    }

    @POST
    public Response createProposal(ProposalDetailsDTO proposalDetailsDTO){
        LOG.info("-- Recebendo proposta de compra --");
        proposalSerice.createNewProposal(proposalDetailsDTO);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response removeProposal(@PathParam("id") long id){
        LOG.info("-- Removendo proposta --");
        proposalSerice.removeProposal(id);
        return Response.ok().build();
    }

}
