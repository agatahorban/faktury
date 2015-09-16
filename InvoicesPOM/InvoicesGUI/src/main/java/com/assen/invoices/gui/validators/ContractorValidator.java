package com.assen.invoices.gui.validators;

import com.assen.invoices.entities.Bank;
import com.assen.invoices.entities.PaymentDate;
import com.assen.invoices.gui.model.wrappers.BankWrapper;
import com.assen.invoices.gui.model.wrappers.ContractorWrapper;
import com.assen.invoices.gui.model.wrappers.PaymentDateWrapper;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Agata
 */
public class ContractorValidator {
    public static List<String> provinceList;

    public ContractorValidator() {
        provinceList = new ArrayList<>();
        String[] provinceArray = {
            "dolnośląske","kujawsko-pomorskie","lubelskie","lubuskie",
            "łódzkie","małopolskie","mazowieckie","opolskie",
            "podkarpackie","podlaskie","pomorskie","śląskie", 
            "świętokrzyskie", "warmińsko-mazurskie", "wielkopolskie", "zachodnio-pomorskie"};
        
        Collections.addAll(provinceList,provinceArray);
    }
    
    

    public String validateData(ContractorWrapper contractor, ContractorValidationData validationData,
            ContractorMapReferences mapReferences) {
        StringBuilder errors = new StringBuilder();

        if (nullOrEmptyValue(contractor.getFullName())) {
            errors.append("Nazwa pełna nie może być pusty.\n");
        }

        if (nullOrEmptyValue(contractor.getCutName())) {
            contractor.setCutName("");
        }

        if (nullOrEmptyValue(contractor.getNIP())) {
            errors.append("NIP nie może być pusty.\n");
        }

        if (nullOrEmptyValue(contractor.getAddressWrapper().getStreet())) {
            errors.append("Ulica nie może być pusta.\n");
        }

        if (nullOrEmptyValue(contractor.getAddressWrapper().getTown())) {
            errors.append("Miejscowość nie może być pusta.\n");
        }

        if (nullOrEmptyValue(contractor.getAddressWrapper().getCounty())) {
            errors.append("Powiat nie może być pusty.\n");
        }
        
        if (nullOrEmptyValue(contractor.getAddressWrapper().getBorough())) {
            errors.append("Gmina nie może być pusta.\n");
        }

        if (mapReferences.getBanks().get(validationData.getBank()) != null) {
            contractor.setBankWrapper(new BankWrapper(
                    mapReferences.getBanks().get(validationData.getBank())));
        } else {
            errors.append("Proszę wybrać bank.\n");
        }

        if (mapReferences.getPaymentDates().get(validationData.getPaymentDate()) != null) {
            contractor.setPaymentDateWrapper(new PaymentDateWrapper(mapReferences.getPaymentDates().get(validationData.getPaymentDate())));
        } else {
            errors.append("Proszę wybrać datę płatności.\n");
        }
        
        if(provinceList.contains(validationData.getProvince())){
            contractor.getAddressWrapper().setProvince(validationData.getProvince());
        }

        return errors.toString();
    }

    public static class ContractorValidationData {

        private String province;
        private String bank;
        private String paymentDate;

        public ContractorValidationData(String province, String bank, String vatRate) {
            this.province = province;
            this.bank = bank;
            this.paymentDate = vatRate;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getBank() {
            return bank;
        }

        public void setBank(String bank) {
            this.bank = bank;
        }

        public String getPaymentDate() {
            return paymentDate;
        }

        public void setPaymentDate(String paymentDate) {
            this.paymentDate = paymentDate;
        }

    }

    public static class ContractorMapReferences {

        private Map<String, Bank> banks;
        private Map<String, PaymentDate> paymentDates;

        public ContractorMapReferences(Map<String, Bank> banks, Map<String, PaymentDate> paymentDates) {
            this.banks = banks;
            this.paymentDates = paymentDates;
        }

        public Map<String, Bank> getBanks() {
            return banks;
        }

        public void setBanks(Map<String, Bank> banks) {
            this.banks = banks;
        }

        public Map<String, PaymentDate> getPaymentDates() {
            return paymentDates;
        }

        public void setPaymentDates(Map<String, PaymentDate> paymentDates) {
            this.paymentDates = paymentDates;
        }
    }

    private boolean nullOrEmptyValue(String value) {
        return value == null || value.equals("");
    }
    
}
