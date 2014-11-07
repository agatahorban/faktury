package com.assen.faktury.dao;

import com.assen.faktury.encje.*;
import com.assen.faktury.remoteinterfaces.IFakturyDAORemote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Arek
 */
@Stateless
public class FakturyDAO implements IFakturyDAORemote {

    @PersistenceContext
    private EntityManager em;

    //<editor-fold defaultstate="collapsed" desc="SELECTS">
    @Override
    public Adres selectAdres(int id) {
        return em.find(Adres.class, id);
    }

    @Override
    public Bank selectBank(int id) {
        return em.find(Bank.class, id);
    }

    @Override
    public FakturaFZ selectFakturaFZ(int id) {
        return em.find(FakturaFZ.class, id);
    }

    @Override
    public FakturaVAT selectFakturaVAT(int id) {
        return em.find(FakturaVAT.class, id);
    }

    @Override
    public Grupa selectGrupa(int id) {
        return em.find(Grupa.class, id);
    }

    @Override
    public JednostkaMiary selectJednostkaMiary(int id) {
        return em.find(JednostkaMiary.class, id);
    }

    @Override
    public Kontrahent selectKontrahent(int id) {
        return em.find(Kontrahent.class, id);
    }

    @Override
    public OpakowanieZbiorcze selectOpakowanieZbiorcze(int id) {
        return em.find(OpakowanieZbiorcze.class, id);
    }

    @Override
    public PrzychodZewnetrzny selectPrzychodZewnetrzny(int id) {
        return em.find(PrzychodZewnetrzny.class, id);
    }

    @Override
    public StawkaVAT selectStawkaVAT(int id) {
        return em.find(StawkaVAT.class, id);
    }

    @Override
    public TerminPlatnosci selectTerminPlatnosci(int id) {
        return em.find(TerminPlatnosci.class, id);
    }

    @Override
    public Towar selectTowar(int id) {
        return em.find(Towar.class, id);
    }

    @Override
    public Uzytkownik selectUzytkownik(int id) {
        return em.find(Uzytkownik.class, id);
    }

    @Override
    public WydanieZewnetrzne selectWydanieZewnetrzne(int id) {
        return em.find(WydanieZewnetrzne.class, id);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="INSERTS">
    @Override
    public void insertAdres(Adres adres) {
        em.persist(adres);
    }

    @Override
    public void insertBank(Bank bank) {
        em.persist(bank);
    }

    @Override
    public void insertFakturaFZ(FakturaFZ fakturaFZ) {
        em.persist(fakturaFZ);
    }

    @Override
    public void insertFakturaVAT(FakturaVAT fakturaVAT) {
        em.persist(fakturaVAT);
    }

    @Override
    public void insertGrupa(Grupa grupa) {
        em.persist(grupa);
    }

    @Override
    public void insertJednostkaMiary(JednostkaMiary jednostkaMiary) {
        em.persist(jednostkaMiary);
    }

    @Override
    public void insertKontrahent(Kontrahent kontrahent) {
        em.persist(kontrahent);
    }

    @Override
    public void insertOpakowanieZbiorcze(OpakowanieZbiorcze opakowanieZbiorcze) {
        em.persist(opakowanieZbiorcze);
    }

    @Override
    public void insertPrzychodZewnetrzny(PrzychodZewnetrzny przychodZewnetrzny) {
        em.persist(przychodZewnetrzny);
    }

    @Override
    public void insertStawkaVAT(StawkaVAT stawkaVAT) {
        em.persist(stawkaVAT);
    }

    @Override
    public void insertTerminPlatnosci(TerminPlatnosci terminPlatnosci) {
        em.persist(terminPlatnosci);
    }

    @Override
    public void insertTowar(Towar towar) {
        em.persist(towar);
    }

    @Override
    public void insertUzytkownik(Uzytkownik uzytkownik) {
        em.persist(uzytkownik);
    }

    @Override
    public void insertWydanieZewnetrzne(WydanieZewnetrzne wydanieZewnetrzne) {
        em.persist(wydanieZewnetrzne);
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="UPDATES">
    @Override
    public void updateAdres(Adres adres) {
        if(selectAdres(adres.getId()) == null) {
            return;
        }
        em.merge(adres);
    }

    @Override
    public void updateBank(Bank bank) {
        if(selectBank(bank.getId()) == null) {
            return;
        }
        em.merge(bank);
    }

    @Override
    public void updateFakturaFZ(FakturaFZ fakturaFZ) {
        if(selectFakturaFZ(fakturaFZ.getId()) == null) {
            return;
        }
        em.merge(fakturaFZ);
    }

    @Override
    public void updateFakturaVAT(FakturaVAT fakturaVAT) {
        if(selectFakturaVAT(fakturaVAT.getId()) == null) {
            return;
        }
        em.merge(fakturaVAT);
    }

    @Override
    public void updateGrupa(Grupa grupa) {
        if(selectGrupa(grupa.getId()) == null) {
            return;
        }
        em.merge(grupa);
    }

    @Override
    public void updateJednostkaMiary(JednostkaMiary jednostkaMiary) {
        if(selectJednostkaMiary(jednostkaMiary.getId()) == null) {
            return;
        }
        em.merge(jednostkaMiary);
    }

    @Override
    public void updateKontrahent(Kontrahent kontrahent) {
        if(selectKontrahent(kontrahent.getId()) == null) {
            return;
        }
        em.merge(kontrahent);
    }

    @Override
    public void updateOpakowanieZbiorcze(OpakowanieZbiorcze opakowanieZbiorcze) {
        if(selectOpakowanieZbiorcze(opakowanieZbiorcze.getId()) == null) {
            return;
        }
        em.merge(opakowanieZbiorcze);
    }

    @Override
    public void updatePrzychodZewnetrzny(PrzychodZewnetrzny przychodZewnetrzny) {
        if(selectPrzychodZewnetrzny(przychodZewnetrzny.getId()) == null) {
            return;
        }
        em.merge(przychodZewnetrzny);
    }

    @Override
    public void updateStawkaVAT(StawkaVAT stawkaVAT) {
        if(selectStawkaVAT(stawkaVAT.getId()) == null) {
            return;
        }
        em.merge(stawkaVAT);
    }

    @Override
    public void updateTerminPlatnosci(TerminPlatnosci terminPlatnosci) {
        if(selectTerminPlatnosci(terminPlatnosci.getId()) == null) {
            return;
        }
        em.merge(terminPlatnosci);
    }

    @Override
    public void updateTowar(Towar towar) {
        if(selectTowar(towar.getId()) == null) {
            return;
        }
        em.merge(towar);
    }

    @Override
    public void updateUzytkownik(Uzytkownik uzytkownik) {
        if(selectUzytkownik(uzytkownik.getId()) == null) {
            return;
        }
        em.merge(uzytkownik);
    }

    @Override
    public void updateWydanieZewnetrzne(WydanieZewnetrzne wydanieZewnetrzne) {
        if(selectWydanieZewnetrzne(wydanieZewnetrzne.getId()) == null) {
            return;
        }
        em.merge(wydanieZewnetrzne);
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="DELETES">
    @Override
    public void deleteAdres(Adres adres) {
        em.remove(em.merge(adres));
    }

    @Override
    public void deleteBank(Bank bank) {
        em.remove(em.merge(bank));
    }

    @Override
    public void deleteFakturaFZ(FakturaFZ fakturaFZ) {
        em.remove(em.merge(fakturaFZ));
    }

    @Override
    public void deleteFakturaVAT(FakturaVAT fakturaVAT) {
        em.remove(em.merge(fakturaVAT));
    }

    @Override
    public void deleteGrupa(Grupa grupa) {
        em.remove(em.merge(grupa));
    }

    @Override
    public void deleteJednostkaMiary(JednostkaMiary jednostkaMiary) {
        em.remove(em.merge(jednostkaMiary));
    }

    @Override
    public void deleteKontrahent(Kontrahent kontrahent) {
        em.remove(em.merge(kontrahent));
    }

    @Override
    public void deleteOpakowanieZbiorcze(OpakowanieZbiorcze opakowanieZbiorcze) {
        em.remove(em.merge(opakowanieZbiorcze));
    }

    @Override
    public void deletePrzychodZewnetrzny(PrzychodZewnetrzny przychodZewnetrzny) {
        em.remove(em.merge(przychodZewnetrzny));
    }

    @Override
    public void deleteStawkaVAT(StawkaVAT stawkaVAT) {
        em.remove(em.merge(stawkaVAT));
    }

    @Override
    public void deleteTerminPlatnosci(TerminPlatnosci terminPlatnosci) {
        em.remove(em.merge(terminPlatnosci));
    }

    @Override
    public void deleteTowar(Towar towar) {
        em.remove(em.merge(towar));
    }

    @Override
    public void deleteUzytkownik(Uzytkownik uzytkownik) {
        em.remove(em.merge(uzytkownik));
    }

    @Override
    public void deleteWydanieZewnetrzne(WydanieZewnetrzne wydanieZewnetrzne) {
        em.remove(em.merge(wydanieZewnetrzne));
    }
//</editor-fold>
}
