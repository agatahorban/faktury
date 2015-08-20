package com.assen.invoices.service.api;

import com.assen.invoices.entities.CollectivePackage;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Arek
 */
@Local
public interface ICollectivePackageService {

    List<CollectivePackage> findAllCollectivePackages();
}
