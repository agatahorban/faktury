package com.assen.faktury.dao;

import com.assen.faktury.dao.interfaces.*;
import com.assen.faktury.encje.*;
import com.assen.faktury.model.KodDokumentu;
import com.assen.faktury.model.TypUzytkownika;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.FileAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.jboss.shrinkwrap.resolver.api.maven.MavenFormatStage;
import org.jboss.shrinkwrap.resolver.api.maven.MavenResolverSystem;
import org.jboss.shrinkwrap.resolver.api.maven.MavenStrategyStage;
import org.jboss.shrinkwrap.resolver.api.maven.PomEquippedResolveStage;
import org.jboss.shrinkwrap.resolver.api.maven.ScopeType;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author Arek
 */
@RunWith(Arquillian.class)
public class JpaCrudTest {

    @Deployment
    public static JavaArchive createArchive() {
        JavaArchive arch = ShrinkWrap.create(JavaArchive.class, "FakturyDAO.jar")
                .addPackages(true, "com.assen.faktury")
                .addAsManifestResource(new FileAsset(new File("src/test/resources/META-INF/beans.xml")), "beans.xml")
                .addAsManifestResource(new FileAsset(new File("src/test/resources/META-INF/persistence.xml")), "persistence.xml");
        MavenResolverSystem mrs = Maven.resolver();
        PomEquippedResolveStage stage = mrs.loadPomFromFile("pom.xml");
        stage = stage.importRuntimeAndTestDependencies();
        stage = stage.importDependencies(ScopeType.COMPILE);
        MavenStrategyStage mss = stage.resolve();
        MavenFormatStage mfs = mss.withTransitivity();
        File[] jars = mfs.asFile();
        List<JavaArchive> jarsFiles = new ArrayList<>();
        for (File jar1 : jars) {
            jarsFiles.add(ShrinkWrap.createFromZipFile(JavaArchive.class, jar1));
        }
        for (JavaArchive jarsFile : jarsFiles) {
            arch.merge(jarsFile);
        }
        return arch;
    }
    
//    @Deployment
//    public static EnterpriseArchive createArchive() {
//        EnterpriseArchive ear = ShrinkWrap.create(EnterpriseArchive.class);
//        JavaArchive jar = ShrinkWrap.create(JavaArchive.class);
//        MavenResolverSystem mrs = Maven.resolver();
//        PomEquippedResolveStage stage = mrs.loadPomFromFile("pom.xml");
//        stage = stage.importRuntimeAndTestDependencies();
//        stage = stage.importDependencies(ScopeType.COMPILE);
//        MavenStrategyStage mss = stage.resolve();
//        MavenFormatStage mfs = mss.withTransitivity();
//        File[] jars = mfs.asFile();
//        List<JavaArchive> jarsFiles = new ArrayList<>();
//       
//        for (File jar1 : jars) {
//            jarsFiles.add(ShrinkWrap.createFromZipFile(JavaArchive.class, jar1));
////            ear.addAsModule(jar1);
//        }
//        
//        jar.addPackages(true, "com.assen.faktury")
//                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
//                .addAsManifestResource(new FileAsset(new File("src/test/resources/META-INF/persistence.xml")), "persistence.xml");
//        ear.addAsModule(jar);
//        return ear;
//    }

    public JpaCrudTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @EJB
    private IAdresDao adresDao;
    @EJB
    private IBankDao bankDao;
    @EJB
    private IFakturaFZDao fakturaFZDao;
    @EJB
    private IFakturaVATDao fakturaVATDao;
    @EJB
    private IGrupaDao grupaDao;
    @EJB
    private IJednostkaMiaryDao jednostkaMiaryDao;
    @EJB
    private IKontrahentDao kontrahentDao;
    @EJB
    private IOpakowanieZbiorczeDao opakowanieZbiorczeDao;
    @EJB
    private IPrzychodZewnetrznyDao przychodZewnetrznyDao;
    @EJB
    private IStawkaVATDao stawkaVATDao;
    @EJB
    private ITerminPlatnosciDao terminPlatnosciDao;
    @EJB
    private ITowarDao towarDao;
    @EJB
    private IUzytkownikDao uzytkownikDao;
    @EJB
    private IWydanieZewnetrzneDao wydanieZewnetrzneDao;

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

        List<JednostkaMiary> jednostki = jednostkaMiaryDao.selectAll();
        List<StawkaVAT> stawki = stawkaVATDao.selectAll();
        List<OpakowanieZbiorcze> opakowania = opakowanieZbiorczeDao.selectAll();
        List<Grupa> grupy = grupaDao.selectAll();

        Towar towar = new Towar();
        towar.setCena(23.45);
        towar.setCzyCenaWyzsza(true);
        towar.setIndex1("123432");
        towar.setIndex2("2323232");
        towar.setGrupa(grupy.get(0));
        towar.setJednostka(jednostki.get(0));
        towar.setOpakowanie(opakowania.get(0));
        towar.setStawka(stawki.get(0));
        towarDao.insert(towar);

        Uzytkownik uzytkownik = new Uzytkownik();
        uzytkownik.setAdres(adresy.get(0));
        uzytkownik.setHaslo("haslo");
        uzytkownik.setLogin("login");
        uzytkownik.setNIP("123-56-89-11");
        uzytkownik.setNazwaPelna("Nazwa pelna");
        uzytkownik.setTypUzytkownika(TypUzytkownika.ADMINISTRATOR);
        uzytkownikDao.insert(uzytkownik);

        List<Towar> towary = towarDao.selectAll();
        List<Uzytkownik> uzytkownicy = uzytkownikDao.selectAll();
        List<Kontrahent> kontrahenci = kontrahentDao.selectAll();

        WydanieZewnetrzne wyd = new WydanieZewnetrzne();
        wyd.setDataWystawienia(new Date(System.currentTimeMillis()));
        wyd.setKodDokumentu(KodDokumentu.WZ);
        wyd.setListaTowarow(towary);
        wyd.setMaska("12/12/12");
        wyd.setNumer(2);
        wyd.setOdbiorca(kontrahenci.get(0));
        wyd.setSprzedawca(uzytkownicy.get(0));
        wyd.setWaluta("PLN");
        wydanieZewnetrzneDao.insert(wyd);

        PrzychodZewnetrzny przychod = new PrzychodZewnetrzny();
        przychod.setDataWystawienia(new Date(System.currentTimeMillis()));
        przychod.setDostawca(kontrahenci.get(0));
        przychod.setKodDokumentu(KodDokumentu.PZ);
        przychod.setListaTowarow(towary);
        przychod.setMaska("12/12/12");
        przychod.setNumer(3);
        przychod.setNumerWZdostawcy(1234);
        przychod.setOdbiorca(uzytkownicy.get(0));
        przychodZewnetrznyDao.insert(przychod);

        FakturaFZ fz = new FakturaFZ();
        fz.setDataWystawienia(new Date(System.currentTimeMillis()));
        fz.setDostawca(kontrahenci.get(0));
        fz.setKodDokumentu(KodDokumentu.FZ);
        fz.setListaTowarow(towary);
        fz.setMaska("12/12/12");
        fz.setNumer(3);
        fz.setNumerFakturyVATdostawcy(345);
        fz.setOdbiorca(uzytkownicy.get(0));
        fz.setSygnatura("sygnatura");
        fakturaFZDao.insert(fz);

        FakturaVAT vat = new FakturaVAT();
        vat.setDataSprzedazy(new Date(System.currentTimeMillis()));
        vat.setDataWystawienia(new Date(System.currentTimeMillis()));
        vat.setKodDokumentu(KodDokumentu.FS);
        vat.setListaTowarow(towary);
        vat.setMaska("12/12/12");
        vat.setNumer(6);
        vat.setOdbiorca(kontrahenci.get(0));
        vat.setSprzedawca(uzytkownicy.get(0));
        vat.setSygnatura("sygnatura");
        fakturaVATDao.insert(vat);
    }

    private void removeData() {
        List<Adres> adresy = adresDao.selectAll();
        List<TerminPlatnosci> terminy = terminPlatnosciDao.selectAll();
        List<Bank> banki = bankDao.selectAll();

        List<JednostkaMiary> jednostki = jednostkaMiaryDao.selectAll();
        List<StawkaVAT> stawki = stawkaVATDao.selectAll();
        List<OpakowanieZbiorcze> opakowania = opakowanieZbiorczeDao.selectAll();
        List<Grupa> grupy = grupaDao.selectAll();

        List<Towar> towary = towarDao.selectAll();
        List<Uzytkownik> uzytkownicy = uzytkownikDao.selectAll();
        List<Kontrahent> kontrahenci = kontrahentDao.selectAll();

        List<WydanieZewnetrzne> wz = wydanieZewnetrzneDao.selectAll();
        List<PrzychodZewnetrzny> pz = przychodZewnetrznyDao.selectAll();
        List<FakturaFZ> fz = fakturaFZDao.selectAll();
        List<FakturaVAT> vaty = fakturaVATDao.selectAll();

        fakturaVATDao.remove(vaty.get(0));
        fakturaFZDao.remove(fz.get(0));
        przychodZewnetrznyDao.remove(pz.get(0));
        wydanieZewnetrzneDao.remove(wz.get(0));

        uzytkownikDao.remove(uzytkownicy.get(0));
        kontrahentDao.remove(kontrahenci.get(0));
        towarDao.remove(towary.get(0));

        grupaDao.remove(grupy.get(0));
        opakowanieZbiorczeDao.remove(opakowania.get(0));
        stawkaVATDao.remove(stawki.get(0));
        jednostkaMiaryDao.remove(jednostki.get(0));

        bankDao.remove(banki.get(0));
        terminPlatnosciDao.remove(terminy.get(0));
        adresDao.remove(adresy.get(0));
    }

    @Test
    public void crudAll__test_isOk() {
        System.out.println("Metoda");
        generateData();
        removeData();
    }
}
