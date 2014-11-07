package com.assen.faktury.remoteinterfaces;

import com.assen.faktury.encje.*;
import javax.ejb.Remote;

/**
 *
 * @author Arek
 */
@Remote
public interface IFakturyDAORemote {

    //<editor-fold defaultstate="collapsed" desc="SELECTS">
    Adres selectAdres(int id);

    Bank selectBank(int id);

    FakturaFZ selectFakturaFZ(int id);

    FakturaVAT selectFakturaVAT(int id);

    Grupa selectGrupa(int id);

    JednostkaMiary selectJednostkaMiary(int id);

    Kontrahent selectKontrahent(int id);

    OpakowanieZbiorcze selectOpakowanieZbiorcze(int id);

    PrzychodZewnetrzny selectPrzychodZewnetrzny(int id);

    StawkaVAT selectStawkaVAT(int id);

    TerminPlatnosci selectTerminPlatnosci(int id);

    Towar selectTowar(int id);

    Uzytkownik selectUzytkownik(int id);

    WydanieZewnetrzne selectWydanieZewnetrzne(int id);
//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="INSERTS">
    void insertAdres(Adres adres);

    void insertBank(Bank bank);

    void insertFakturaFZ(FakturaFZ fakturaFZ);

    void insertFakturaVAT(FakturaVAT fakturaVAT);

    void insertGrupa(Grupa grupa);

    void insertJednostkaMiary(JednostkaMiary jednostkaMiary);

    void insertKontrahent(Kontrahent kontrahent);

    void insertOpakowanieZbiorcze(OpakowanieZbiorcze opakowanieZbiorcze);

    void insertPrzychodZewnetrzny(PrzychodZewnetrzny przychodZewnetrzny);

    void insertStawkaVAT(StawkaVAT stawkaVAT);

    void insertTerminPlatnosci(TerminPlatnosci terminPlatnosci);

    void insertTowar(Towar towar);

    void insertUzytkownik(Uzytkownik uzytkownik);

    void insertWydanieZewnetrzne(WydanieZewnetrzne wydanieZewnetrzne);
//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="UPDATES">
    void updateAdres(Adres adres);

    void updateBank(Bank bank);

    void updateFakturaFZ(FakturaFZ fakturaFZ);

    void updateFakturaVAT(FakturaVAT fakturaVAT);

    void updateGrupa(Grupa grupa);

    void updateJednostkaMiary(JednostkaMiary jednostkaMiary);

    void updateKontrahent(Kontrahent kontrahent);

    void updateOpakowanieZbiorcze(OpakowanieZbiorcze opakowanieZbiorcze);

    void updatePrzychodZewnetrzny(PrzychodZewnetrzny przychodZewnetrzny);

    void updateStawkaVAT(StawkaVAT stawkaVAT);

    void updateTerminPlatnosci(TerminPlatnosci terminPlatnosci);

    void updateTowar(Towar towar);

    void updateUzytkownik(Uzytkownik uzytkownik);

    void updateWydanieZewnetrzne(WydanieZewnetrzne wydanieZewnetrzne);
//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="DELETES">
    void deleteAdres(Adres adres);

    void deleteBank(Bank bank);

    void deleteFakturaFZ(FakturaFZ fakturaFZ);

    void deleteFakturaVAT(FakturaVAT fakturaVAT);

    void deleteGrupa(Grupa grupa);

    void deleteJednostkaMiary(JednostkaMiary jednostkaMiary);

    void deleteKontrahent(Kontrahent kontrahent);

    void deleteOpakowanieZbiorcze(OpakowanieZbiorcze opakowanieZbiorcze);

    void deletePrzychodZewnetrzny(PrzychodZewnetrzny przychodZewnetrzny);

    void deleteStawkaVAT(StawkaVAT stawkaVAT);

    void deleteTerminPlatnosci(TerminPlatnosci terminPlatnosci);

    void deleteTowar(Towar towar);

    void deleteUzytkownik(Uzytkownik uzytkownik);

    void deleteWydanieZewnetrzne(WydanieZewnetrzne wydanieZewnetrzne);
//</editor-fold>
}
