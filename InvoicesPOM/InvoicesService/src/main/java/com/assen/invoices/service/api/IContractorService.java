package com.assen.invoices.service.api;

import com.assen.invoices.dto.ContractorListDto;
import com.assen.invoices.entities.Contractor;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Agata
 */

@Local
public interface IContractorService {
    List<Contractor> findAllContractors();
    Contractor insertNewContractors(Contractor contractor);
    Contractor updateContractors(Contractor contractor);
    boolean removeContractors(ContractorListDto contractorList);
    Contractor findByCutName(String cutName);
}
