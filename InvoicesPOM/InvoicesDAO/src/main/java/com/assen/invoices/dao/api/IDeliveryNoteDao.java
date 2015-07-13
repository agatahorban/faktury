package com.assen.invoices.dao.api;

import com.assen.invoices.entities.DeliveryNote;
import javax.ejb.Local;

/**
 *
 * @author Arek
 */
@Local
public interface IDeliveryNoteDao extends ICrudDao<DeliveryNote> {

}
