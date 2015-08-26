package com.assen.invoices.gui.validators;

import com.assen.invoices.entities.CollectivePackage;
import com.assen.invoices.entities.Contractor;
import com.assen.invoices.entities.Group;
import com.assen.invoices.entities.UnitOfMeasure;
import com.assen.invoices.entities.VATRate;
import com.assen.invoices.gui.model.wrappers.CollectivePackageWrapper;
import com.assen.invoices.gui.model.wrappers.ContractorWrapper;
import com.assen.invoices.gui.model.wrappers.GoodsWrapper;
import com.assen.invoices.gui.model.wrappers.GroupWrapper;
import com.assen.invoices.gui.model.wrappers.UnitOfMeasureWrapper;
import com.assen.invoices.gui.model.wrappers.VATRateWrapper;
import java.util.Map;

/**
 *
 * @author Arek
 */
public class GoodsValidator {

    public String validateData(GoodsWrapper goods, GoodsValidationData validationData,
            GoodsMapReferences mapReferences) {
        StringBuilder errors = new StringBuilder();

        if (nullOrEmptyValue(goods.getIndex1())) {
            errors.append("Index1 nie może być pusty.\n");
        }

        if (nullOrEmptyValue(goods.getIndex2())) {
            goods.setIndex2("");
        }

        if (nullOrEmptyValue(goods.getName())) {
            errors.append("Nazwa nie może być pusta.\n");
        }

        if (mapReferences.getContractors().get(validationData.getConractor()) != null) {
            goods.setSupplierWrapper(new ContractorWrapper(
                    mapReferences.getContractors().get(validationData.getConractor())));
        } else {
            errors.append("Proszę wybrać dostawcę.\n");
        }

        if (mapReferences.getCollectivePackages().get(validationData.getCollectivePackage()) != null) {
            goods.setCollectivePackageWrapper(new CollectivePackageWrapper(
                    mapReferences.getCollectivePackages().get(validationData.getCollectivePackage())));
        } else {
            errors.append("Proszę wybrać opakowanie.\n");
        }

        if (mapReferences.getGroups().get(validationData.getGroup()) != null) {
            goods.setGroupWrapper(new GroupWrapper(
                    mapReferences.getGroups().get(validationData.getGroup())));
        }

        if (mapReferences.getUnitsOfMeasure().get(validationData.getUnitOfMeasure()) != null) {
            goods.setUnitOfMeasureWrapper(new UnitOfMeasureWrapper(
                    mapReferences.getUnitsOfMeasure().get(validationData.getUnitOfMeasure())));
        } else {
            errors.append("Proszę wybrać jednostkę miary.\n");
        }

        if (mapReferences.getVatRates().get(validationData.getVatRate()) != null) {
            goods.setVatRateWrapper(new VATRateWrapper(
                    mapReferences.getVatRates().get(validationData.getVatRate())));
        } else {
            errors.append("Proszę wybrać stawkę vat.\n");
        }

        return errors.toString();
    }

    private boolean nullOrEmptyValue(String value) {
        return value == null || value.equals("");
    }

    public static class GoodsValidationData {

        private final String conractor;
        private final String collectivePackage;
        private final String group;
        private final String unitOfMeasure;
        private final String vatRate;

        public GoodsValidationData(String conractor, String collectivePackage,
                String group, String unitOfMeasure, String vatRate) {
            this.conractor = conractor;
            this.collectivePackage = collectivePackage;
            this.group = group;
            this.unitOfMeasure = unitOfMeasure;
            this.vatRate = vatRate;
        }

        public String getConractor() {
            return conractor;
        }

        public String getCollectivePackage() {
            return collectivePackage;
        }

        public String getGroup() {
            return group;
        }

        public String getUnitOfMeasure() {
            return unitOfMeasure;
        }

        public String getVatRate() {
            return vatRate;
        }
    }

    public static class GoodsMapReferences {

        private Map<String, CollectivePackage> collectivePackages;
        private Map<String, Group> groups;
        private Map<String, VATRate> vatRates;
        private Map<String, Contractor> contractors;
        private Map<String, UnitOfMeasure> unitsOfMeasure;

        public GoodsMapReferences(Map<String, CollectivePackage> collectivePackages, 
                Map<String, Group> groups, Map<String, VATRate> vatRates, 
                Map<String, Contractor> contractors, 
                Map<String, UnitOfMeasure> unitsOfMeasure) {
            this.collectivePackages = collectivePackages;
            this.groups = groups;
            this.vatRates = vatRates;
            this.contractors = contractors;
            this.unitsOfMeasure = unitsOfMeasure;
        }
        
        public Map<String, CollectivePackage> getCollectivePackages() {
            return collectivePackages;
        }

        public void setCollectivePackages(Map<String, CollectivePackage> collectivePackages) {
            this.collectivePackages = collectivePackages;
        }

        public Map<String, Group> getGroups() {
            return groups;
        }

        public void setGroups(Map<String, Group> groups) {
            this.groups = groups;
        }

        public Map<String, VATRate> getVatRates() {
            return vatRates;
        }

        public void setVatRates(Map<String, VATRate> vatRates) {
            this.vatRates = vatRates;
        }

        public Map<String, Contractor> getContractors() {
            return contractors;
        }

        public void setContractors(Map<String, Contractor> contractors) {
            this.contractors = contractors;
        }

        public Map<String, UnitOfMeasure> getUnitsOfMeasure() {
            return unitsOfMeasure;
        }

        public void setUnitsOfMeasure(Map<String, UnitOfMeasure> unitsOfMeasure) {
            this.unitsOfMeasure = unitsOfMeasure;
        }
    }
}
