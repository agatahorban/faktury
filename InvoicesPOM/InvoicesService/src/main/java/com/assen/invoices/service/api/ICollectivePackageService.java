package com.assen.invoices.service.api;

import com.assen.invoices.dto.CollectivePackageListDto;
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
    boolean removeCollectivePackage(CollectivePackageListDto collectivePackageToDelete);
    CollectivePackage insertNewCollectivePackage(CollectivePackage collectivePackage);
    CollectivePackage updateCollectivePackage(CollectivePackage collectivePackage);
}
