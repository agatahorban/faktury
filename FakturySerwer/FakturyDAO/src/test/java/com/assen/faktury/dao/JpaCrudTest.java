package com.assen.faktury.dao;

import com.assen.faktury.encje.*;
import java.io.File;
import java.util.List;
import javax.ejb.EJB;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.asset.FileAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.runner.RunWith;

/**
 *
 * @author Arek
 */
@RunWith(Arquillian.class)
public class JpaCrudTest {

    @Deployment
    public static JavaArchive createArchive() {
        JavaArchive arch = ShrinkWrap.create(JavaArchive.class, "FaktoryDAO.jar")
                .addPackages(true, "com.assen.faktury")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsManifestResource(new FileAsset(new File("FakturyDAO/src/main/resources/META-INF/persistence.xml")), "persistence.xml");
        return arch;
    }
    
    @EJB
    private AdresDao adresDao;
    @EJB
    private BankDao bankDao;
    @EJB
    private FakturaFZDao fakturaFZDao;
    @EJB
    private FakturaVATDao fakturaVATDao;
    @EJB
    private GrupaDao grupaDao;
    @EJB
    private JednostkaMiaryDao jednostkaMiaryDao;
    @EJB
    private KontrahentDao kontrahentDao;
    @EJB
    private OpakowanieZbiorczeDao opakowanieZbiorczeDao;
    @EJB
    private PrzychodZewnetrznyDao przychodZewnetrznyDao;
    @EJB
    private StawkaVATDao stawkaVATDao;
    @EJB
    private TerminPlatnosciDao terminPlatnosciDao;
    @EJB
    private TowarDao towarDao;
    @EJB
    private UzytkownikDao uzytkownikDao;
    @EJB
    private WydanieZewnetrzneDao wydanieZewnetrzneDao;
    
    private void generateData() {
        TerminPlatnosci terminPlatnosci = new TerminPlatnosci();
        terminPlatnosci.setIloscDni(15);
        terminPlatnosci.setOpis("Czas na zaplate to 15 dni roboczych.");
        terminPlatnosciDao.insert(terminPlatnosci);
        
        Bank bank = new Bank();
        bank.setNazwaBanku("Nazwa");
        bank.setNumer("1234588909043243243232443");
        bank.setWaluta("PLN");
        bankDao.insert(bank);
        
        Adres adres = new Adres();
        adres.setDom(2);
        adres.setGmina("Gmina");
        adres.setKodPocztowy("11-111");
        adres.setLokal(2);
        adres.setMiejscowosc("Miejscowosc");
        adres.setPanstwo("Panstwo");
        adres.setPowiat("Powiat");
        adres.setUlica("ulica");
        adres.setWojewodztwo("Wojewodztwo");
        adresDao.insert(adres);
        
        List<Adres> adresy = adresDao.selectAll();
        List<TerminPlatnosci> terminy = terminPlatnosciDao.selectAll();
        List<Bank> banki = bankDao.selectAll();
        
        Kontrahent kontrahent = new Kontrahent();
        kontrahent.setCzyDostawca(true);
        kontrahent.setCzyOdbiorca(true);
        kontrahent.setCzyPlatnikVAT(true);
        kontrahent.setNIP("12-3456-765-12-3");
        kontrahent.setNazwaPelna("Nazwa");
        kontrahent.setNazwaSkrocona("skrot");
        kontrahent.setTerminPlatnosci(terminy.get(0));
        kontrahent.setAdres(adresy.get(0));
        kontrahent.setBank(banki.get(0));
        kontrahentDao.insert(kontrahent);
        
        JednostkaMiary jednostkaMiary = new JednostkaMiary();
        jednostkaMiary.setNazwa("Nazwa");
        jednostkaMiary.setSkrot("skrot");
        jednostkaMiaryDao.insert(jednostkaMiary);
        
        StawkaVAT stawka = new StawkaVAT();
        stawka.setNazwa("Nazwa");
        stawka.setOpis("opis");
        stawka.setWartosc(25);
        stawkaVATDao.insert(stawka);
        
        OpakowanieZbiorcze opakowanie = new OpakowanieZbiorcze();
        opakowanie.setNazwaPelna("Nazwa");
        opakowanie.setNazwaSkrocona("skrot");
        opakowanie.setPojemnosc(30);
        opakowanieZbiorczeDao.insert(opakowanie);
        
        Grupa grupa = new Grupa();
        grupa.setNazwa("Nazwa");
        grupaDao.insert(grupa);
    }
}
