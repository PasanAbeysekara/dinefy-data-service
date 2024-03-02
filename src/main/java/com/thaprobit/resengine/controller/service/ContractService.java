package com.thaprobit.resengine.controller.service;

import com.thaprobit.global.SystemOperation;
import com.thaprobit.resengine.controller.assembler.ContractModelAssembler;
import com.thaprobit.resengine.controller.service.functionality.PropAvailDataAsyncExecutor;
import com.thaprobit.resengine.dao.Contract;
import com.thaprobit.resengine.dao.ContractAvailability;
import com.thaprobit.resengine.dao.Seasons;
import com.thaprobit.resengine.dao.sys.Tags;
import com.thaprobit.resengine.facade.dto.ContractModel;
import com.thaprobit.resengine.facade.dto.TagsModel;
import com.thaprobit.resengine.repo.ContractsRepository;
import com.thaprobit.service.AbstractService;
import com.thaprobit.util.ResponseWrapper;
import com.thaprobit.util.SystemMessages;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author Tharinda Wickramaarachchi
 */
@Service
@Slf4j
public class ContractService extends AbstractService<Contract> {
    @Autowired
    private ContractsRepository contractsRepository;

    @Autowired
    private PagedResourcesAssembler<Contract> pagedResourcesAssembler;

    @Autowired
    private ContractModelAssembler contractModelAssembler;

    @Autowired
    private PropAvailDataAsyncExecutor availDataAsyncExecutor;


    public ResponseEntity<List<Contract>> getProperty() {

        List<Contract> orgList = contractsRepository.findAll();

        ResponseEntity<List<Contract>> responseEntity = null;
        if (orgList.isEmpty()) {
            responseEntity = ResponseEntity.notFound().headers(addCommonHeaders(new HttpHeaders())).build();
        } else {
            responseEntity = ResponseEntity.ok().headers(addCommonHeaders(new HttpHeaders())).body(orgList);
        }


        return responseEntity;
    }

    public ResponseEntity<ResponseWrapper<PagedModel<ContractModel>>> getContracts(Pageable pageable) {
        Page<Contract> contractedPage = contractsRepository.findAll(pageable);
        PagedModel<ContractModel> collModel = pagedResourcesAssembler.toModel(contractedPage, contractModelAssembler);

        return ResponseEntity.ok()
                .headers(addCommonHeaders(new HttpHeaders()))
                .body(new ResponseWrapper<>(SystemOperation.READ.withSuccess(), SystemMessages.SUCCESSFULLY_LOADED, collModel));
    }

    /**
     * Get Single Contract
     *
     * @param id contract ID
     * @return The Contract
     */
    public ResponseEntity<ResponseWrapper<Contract>> getContract(long id) {
        Optional<Contract> contractOptional = contractsRepository.findById(id);

        ResponseEntity<ResponseWrapper<Contract>> response;

        if (contractOptional.isPresent()) {
            Contract contract = contractOptional.get();
            Link selfRel = HATEOASProvider.contractSelfLinkProvider(contract.getContractId());
            contract.add(selfRel);

            response = ResponseEntity.ok()
                    .headers(addCommonHeaders(new HttpHeaders()))
                    .body(new ResponseWrapper<>(SystemOperation.READ.withSuccess(), SystemMessages.SUCCESSFULLY_LOADED, contract));

        } else {
            response = buildNotFoundResponseWrapped();
        }

        return response;
    }


    /**
     * Create a new Contract
     *
     * @param draft
     * @param contract Contract
     * @return Saved Contract Response wrapper
     */
    @Transactional
    public ResponseEntity<ResponseWrapper<Contract>> createContract(boolean draft, Contract contract) {
        ResponseEntity<ResponseWrapper<Contract>> response;

        try {
            Long contractNextVal = contractsRepository.getNextVal();
            contract.setContractId(contractNextVal);

            preProcess(contract);

            Contract savedContract = contractsRepository.saveAndFlush(contract);

            Link selfRel = HATEOASProvider.contractSelfLinkProvider(contract.getContractId());
            savedContract.add(selfRel);

            response = ResponseEntity.status(HttpStatus.CREATED)
                    .headers(addCommonHeaders(new HttpHeaders()))
                    .body(new ResponseWrapper<>(SystemOperation.CREATE.withSuccess(), SystemMessages.CONTRACT_CREATE_SUCCESS, savedContract));

            if (draft) // TODO change this
            {
                availDataAsyncExecutor.executeAsynchronouslyTx(contract);
            }

        } catch (Exception e) {
            log.error("Error Occurred during contract saving : ", e);
            response = buildExceptionErrorResponse(SystemOperation.CREATE, SystemMessages.CONTRACT_CREATE_FAILED, e);
        }

        return response;
    }

    /**
     * Create a new Contract
     *
     * @param contract Contract
     * @return Saved Contract Response wrapper
     */
    public ResponseEntity<ResponseWrapper<Contract>> updateContract(long id, Contract contract) {
        ResponseEntity<ResponseWrapper<Contract>> response;

        try {
            contract.setContractId(id);
            preProcess(contract);

            Contract savedContract = contractsRepository.save(contract);

            Link selfRel = HATEOASProvider.contractSelfLinkProvider(contract.getContractId());
            savedContract.add(selfRel);

            response = ResponseEntity.status(HttpStatus.CREATED)
                    .headers(addCommonHeaders(new HttpHeaders()))
                    .body(new ResponseWrapper<>(SystemOperation.MODIFY.withSuccess(), SystemMessages.CONTRACT_UPDATE_SUCCESS, savedContract));
        } catch (Exception e) {
            log.error("Error Occurred during contract updating : ", e);
            response = buildExceptionErrorResponse(SystemOperation.MODIFY, SystemMessages.CONTRACT_UPDATE_FAILED, e);
        }

        return response;
    }


    private void preProcess(Contract contract) {
        if (contract.getSeasons() != null) {
            long contractId = contract.getContractId();

            //contract.getSeasons().forEach( seasons -> seasons.setWeekDefinitions( null ) ); // TODO Do a proper fix  Issue : Hibernate generate unnecessary insert query  : insert into hngout.contract_availability (contract_id, season_id, contract_version, week_def_id) values (?, ?, ?, ?)

            for (Seasons season : contract.getSeasons()) {
                season.getSeasonId().setContractId(contractId);

                if (season.getAvailabilities() != null) {
                    for (ContractAvailability availability : season.getAvailabilities()) {
                        availability.getAvailabilityID().setContractId(contractId);
                        availability.getAvailabilityID().setSeasonId(season.getSeasonId().getSeasonId());
                    }
                }
            }
        }

    }

    /**
     * Delete contract
     *
     * @param id contract ID
     * @return
     */
    public ResponseEntity<ResponseWrapper<Contract>> deleteContract(long id) {
        ResponseEntity<ResponseWrapper<Contract>> response;

        try {
            contractsRepository.deleteById(id);

            response = ResponseEntity.ok()
                    .headers(addCommonHeaders(new HttpHeaders()))
                    .body(new ResponseWrapper<>(SystemOperation.DELETE.withSuccess(), SystemMessages.CONTRACT_DELETE_SUCCESS, ""));
        } catch (Exception e) {
            log.error("Error Occurred during contract deleting : ", e);
            response = buildExceptionErrorResponse(SystemOperation.DELETE, SystemMessages.CONTRACT_DELETE_FAILED, e);
        }

        return response;
    }
}
