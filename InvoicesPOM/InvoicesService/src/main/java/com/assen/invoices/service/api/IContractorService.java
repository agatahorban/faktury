/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.assen.invoices.service.api;

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
}
