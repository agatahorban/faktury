package com.assen.invoices.dao.impl;

import com.assen.invoices.entities.DeliveryNote;
import com.assen.invoices.dao.api.IDeliveryNoteDao;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 *
 * @author Arek
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class DeliveryNoteDao extends CrudDao<DeliveryNote> implements IDeliveryNoteDao {

}
