//package com.assen.faktury.dao;
//
//import com.assen.faktury.encje.*;
//import com.assen.faktury.model.KodDokumentu;
//import com.assen.faktury.model.TypUzytkownika;
//import java.io.File;
//import java.util.Date;
//import java.util.List;
//import javax.ejb.EJB;
//import org.jboss.arquillian.container.test.api.Deployment;
//import org.jboss.arquillian.junit.Arquillian;
//import org.jboss.shrinkwrap.api.ShrinkWrap;
//import org.jboss.shrinkwrap.api.asset.FileAsset;
//import org.jboss.shrinkwrap.api.spec.WebArchive;
//import org.jboss.shrinkwrap.resolver.api.maven.Maven;
//import org.jboss.shrinkwrap.resolver.api.maven.ScopeType;
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
///**
// *
// * @author Arek
// */
//@RunWith(Arquillian.class)
//public class JpaCrudTest {
//    
//    @Deployment
//    public static WebArchive createArchive() {
//        File[] jars = Maven.resolver().loadPomFromFile("pom.xml").importRuntimeAndTestDependencies()
//                .importDependencies(ScopeType.COMPILE).resolve().withTransitivity().asFile();
//        
//        WebArchive warch = ShrinkWrap.create(WebArchive.class)
//                .addPackages(true, "com.assen.faktury")
//                .addAsWebInfResource(new FileAsset(new File("src/test/resources/META-INF/beans.xml")), "beans.xml")
//                .addAsResource(new FileAsset(new File("src/test/resources/META-INF/persistence.xml")), "META-INF/persistence.xml")
//                .addAsLibraries(jars);
//        return warch;
//    }
//
//    public JpaCrudTest() {
//    }
//
//    @BeforeClass
//    public static void setUpClass() {
//    }
//
//    @AfterClass
//    public static void tearDownClass() {
//    }
//
//    @Before
//    public void setUp() {
//    }
//
//    @After
//    public void tearDown() {
//    }
//
//    @EJB
//    private AdresDao adresDao;
//    @EJB
//    private BankDao bankDao;
//    @EJB
//    private FakturaFZDao fakturaFZDao;
//    @EJB
//    private FakturaVATDao fakturaVATDao;
//    @EJB
//    private GrupaDao grupaDao;
//    @EJB
//    private JednostkaMiaryDao jednostkaMiaryDao;
//    @EJB
//    private KontrahentDao kontrahentDao;
//    @EJB
//    private OpakowanieZbiorczeDao opakowanieZbiorczeDao;
//    @EJB
//    private PrzychodZewnetrznyDao przychodZewnetrznyDao;
//    @EJB
//    private StawkaVATDao stawkaVATDao;
//    @EJB
//    private TerminPlatnosciDao terminPlatnosciDao;
//    @EJB
//    private TowarDao towarDao;
//    @EJB
//    private UzytkownikDao uzytkownikDao;
//    @EJB
//    private WydanieZewnetrzneDao wydanieZewnetrzneDao;
//    @EJB
//    private FakturaFZTowaryDao fakturaFZTowaryDao;
//    @EJB
//    private FakturaVATTowaryDao fakturaVATTowaryDao;
//    @EJB
//    private MagazynDao magazynDao;
//    @EJB
//    private MagazynTowaryDao magazynTowaryDao;
//    @EJB
//    private PrzychodZewnetrznyTowaryDao przychodZewnetrznyTowaryDao;
//    @EJB
//    private WydanieZewnetrzneTowaryDao wydanieZewnetrzneTowaryDao;
//
//    private void generateData() {
//        System.out.println("-");
//        System.out.println("------------------------INSERTS TEST--------------------------------");
//        System.out.println("-");
//        
//        TerminPlatnosci terminPlatnosci = new TerminPlatnosci();
//        terminPlatnosci.setIloscDni(15);
//        terminPlatnosci.setOpis("Czas na zaplate to 15 dni roboczych.");
//        
//        System.out.println("Inserting: TerminPlatnosci");
//        terminPlatnosciDao.insert(terminPlatnosci);
//        System.out.println("Done");
//
//        Bank bank = new Bank();
//        bank.setNazwaBanku("Nazwa");
//        bank.setNumer("12345889090432432432324436");
//        bank.setWaluta("PLN");
//        
//        System.out.println("Inserting: Bank");
//        bankDao.insert(bank);
//        System.out.println("Done");
//
//        Adres adres = new Adres();
//        adres.setDom(2);
//        adres.setGmina("Gmina");
//        adres.setKodPocztowy("11-111");
//        adres.setLokal(2);
//        adres.setMiejscowosc("Miejscowosc");
//        adres.setPanstwo("Panstwo");
//        adres.setPowiat("Powiat");
//        adres.setUlica("ulica");
//        adres.setWojewodztwo("Wojewodztwo");
//        
//        System.out.println("Inserting: Adres");
//        adresDao.insert(adres);
//        System.out.println("Done");
//
//        List<Adres> adresy = adresDao.selectAll();
//        List<TerminPlatnosci> terminy = terminPlatnosciDao.selectAll();
//        List<Bank> banki = bankDao.selectAll();
//
//        Kontrahent kontrahent = new Kontrahent();
//        kontrahent.setCzyDostawca(true);
//        kontrahent.setCzyOdbiorca(true);
//        kontrahent.setCzyPlatnikVAT(true);
//        kontrahent.setNIP("12-3456-765-1");
//        kontrahent.setNazwaPelna("Nazwa");
//        kontrahent.setNazwaSkrocona("skrot");
//        kontrahent.setTerminPlatnosci(terminy.get(0));
//        kontrahent.setAdres(adresy.get(0));
//        kontrahent.setBank(banki.get(0));
//        
//        System.out.println("Inserting: Kontrahent");
//        kontrahentDao.insert(kontrahent);
//        System.out.println("Done");
//
//        JednostkaMiary jednostkaMiary = new JednostkaMiary();
//        jednostkaMiary.setNazwa("Nazwa");
//        jednostkaMiary.setSkrot("skrot");
//        
//        System.out.println("Inserting: JednostkaMiary");
//        jednostkaMiaryDao.insert(jednostkaMiary);
//        System.out.println("Done");
//
//        StawkaVAT stawka = new StawkaVAT();
//        stawka.setNazwa("Nazwa");
//        stawka.setOpis("opis");
//        stawka.setWartosc(25);
//        
//        System.out.println("Inserting: StawkaVAT");
//        stawkaVATDao.insert(stawka);
//        System.out.println("Done");
//
//        OpakowanieZbiorcze opakowanie = new OpakowanieZbiorcze();
//        opakowanie.setNazwaPelna("Nazwa");
//        opakowanie.setNazwaSkrocona("skrot");
//        opakowanie.setPojemnosc(30);
//        
//        System.out.println("Inserting: OpakowanieZbiorcze");
//        opakowanieZbiorczeDao.insert(opakowanie);
//        System.out.println("Done");
//
//        Grupa grupa = new Grupa();
//        grupa.setNazwa("Nazwa");
//        
//        System.out.println("Inserting: Grupa");
//        grupaDao.insert(grupa);
//        System.out.println("Done");
//
//        List<JednostkaMiary> jednostki = jednostkaMiaryDao.selectAll();
//        List<StawkaVAT> stawki = stawkaVATDao.selectAll();
//        List<OpakowanieZbiorcze> opakowania = opakowanieZbiorczeDao.selectAll();
//        List<Grupa> grupy = grupaDao.selectAll();
//        List<Kontrahent> kontrahenci = kontrahentDao.selectAll();
//
//        Towar towar = new Towar();
//        towar.setCena(23.45);
//        towar.setCzyCenaWyzsza(true);
//        towar.setIndex1("123432");
//        towar.setIndex2("2323232");
//        towar.setGrupa(grupy.get(0));
//        towar.setJednostka(jednostki.get(0));
//        towar.setOpakowanie(opakowania.get(0));
//        towar.setStawka(stawki.get(0));
//        towar.setDostawca(kontrahenci.get(0));
//        towar.setIlosc(150);
//        
//        System.out.println("Inserting: Towar");
//        towarDao.insert(towar);
//        System.out.println("Done");
//
//        Uzytkownik uzytkownik = new Uzytkownik();
//        uzytkownik.setAdres(adresy.get(0));
//        uzytkownik.setHaslo("haslo");
//        uzytkownik.setLogin("login");
//        uzytkownik.setNIP("123-56-89-11");
//        uzytkownik.setNazwaPelna("Nazwa pelna");
//        uzytkownik.setTypUzytkownika(TypUzytkownika.ADMINISTRATOR);
//        
//        System.out.println("Inserting: Uzytkownik");
//        uzytkownikDao.insert(uzytkownik);
//        System.out.println("Done");
//
//        List<Towar> towary = towarDao.selectAll();
//        List<Uzytkownik> uzytkownicy = uzytkownikDao.selectAll();
//        
//        Magazyn magazyn = new Magazyn();
//        magazyn.setNazwa("Magazyn 1");
//        
//        MagazynTowary magazynTowary = new MagazynTowary();
//        magazynTowary.setMagazyn(magazyn);
//        magazynTowary.setTowar(towary.get(0));
//        magazynTowary.setIlosc(120);
//        
//        magazyn.getMagazynTowary().add(magazynTowary);
//        
//        System.out.println("Inserting: Magazyn & MagazynTowary");
//        magazynDao.insert(magazyn);
//        System.out.println("Done");
//
//        WydanieZewnetrzne wyd = new WydanieZewnetrzne();
//        wyd.setDataWystawienia(new Date(System.currentTimeMillis()));
//        wyd.setKodDokumentu(KodDokumentu.WZ);
//        wyd.setMaska("12/12/12");
//        wyd.setNumer(2);
//        wyd.setOdbiorca(kontrahenci.get(0));
//        wyd.setSprzedawca(uzytkownicy.get(0));
//        wyd.setWaluta("PLN");
//        
//        WydanieZewnetrzneTowary listaTowarowWZ = new WydanieZewnetrzneTowary();
//        listaTowarowWZ.setIlosc(120);
//        listaTowarowWZ.setTowar(towary.get(0));
//        listaTowarowWZ.setWydanieZewnetrzne(wyd);
//        
//        wyd.getListaTowarow().add(listaTowarowWZ);
//        
//        System.out.println("Inserting: WydanieZewnetrzne & WydanieZewnetrzneTowary");
//        wydanieZewnetrzneDao.insert(wyd);
//        System.out.println("Done");
//
//        PrzychodZewnetrzny przychod = new PrzychodZewnetrzny();
//        przychod.setDataWystawienia(new Date(System.currentTimeMillis()));
//        przychod.setDostawca(kontrahenci.get(0));
//        przychod.setKodDokumentu(KodDokumentu.PZ);
//        przychod.setMaska("12/12/12");
//        przychod.setNumer(3);
//        przychod.setNumerWZdostawcy(1234);
//        przychod.setOdbiorca(uzytkownicy.get(0));
//        
//        PrzychodZewnetrznyTowary listaTowarowPZ = new PrzychodZewnetrznyTowary();
//        listaTowarowPZ.setIlosc(10);
//        listaTowarowPZ.setPrzychodZewnetrzny(przychod);
//        listaTowarowPZ.setTowar(towary.get(0));
//        
//        przychod.getListaTowarow().add(listaTowarowPZ);
//        
//        System.out.println("Inserting: PrzychodZewnetrzny & PrzychodZewnetrznyTowary");
//        przychodZewnetrznyDao.insert(przychod);
//        System.out.println("Done");
//
//        FakturaFZ fz = new FakturaFZ();
//        fz.setDataWystawienia(new Date(System.currentTimeMillis()));
//        fz.setDostawca(kontrahenci.get(0));
//        fz.setKodDokumentu(KodDokumentu.FZ);
//        fz.setMaska("12/12/12");
//        fz.setNumer(3);
//        fz.setNumerFakturyVATdostawcy(345);
//        fz.setOdbiorca(uzytkownicy.get(0));
//        fz.setSygnatura("sygnatura");
//        
//        FakturaFZTowary listaTowarowFZ = new FakturaFZTowary();
//        listaTowarowFZ.setIlosc(23);
//        listaTowarowFZ.setFakturaFZ(fz);
//        listaTowarowFZ.setTowar(towary.get(0));
//        
//        fz.getListaTowarow().add(listaTowarowFZ);
//        
//        System.out.println("Inserting: FakturaFZ & FakturaFZTowary");
//        fakturaFZDao.insert(fz);
//        System.out.println("Done");
//
//        FakturaVAT vat = new FakturaVAT();
//        vat.setDataSprzedazy(new Date(System.currentTimeMillis()));
//        vat.setDataWystawienia(new Date(System.currentTimeMillis()));
//        vat.setKodDokumentu(KodDokumentu.FS);
//        vat.setMaska("12/12/12");
//        vat.setNumer(6);
//        vat.setOdbiorca(kontrahenci.get(0));
//        vat.setSprzedawca(uzytkownicy.get(0));
//        vat.setSygnatura("sygnatura");
//        
//        FakturaVATTowary listaTowarowVAT = new FakturaVATTowary();
//        listaTowarowVAT.setIlosc(10);
//        listaTowarowVAT.setFakturaVAT(vat);
//        listaTowarowVAT.setTowar(towary.get(0));
//        
//        vat.getListaTowarow().add(listaTowarowVAT);
//        
//        System.out.println("Inserting: FakturaVAT & FakturaVATTowary");
//        fakturaVATDao.insert(vat);
//        System.out.println("Done");
//    }
//    
//    private void updateData() {
//        System.out.println("-");
//        System.out.println("------------------------UPDATES TEST--------------------------------");
//        System.out.println("-");
//        List<WydanieZewnetrzne> wzi = wydanieZewnetrzneDao.selectAll();
//        List<PrzychodZewnetrzny> pzi = przychodZewnetrznyDao.selectAll();
//        List<FakturaFZ> fzi = fakturaFZDao.selectAll();
//        List<FakturaVAT> vaty = fakturaVATDao.selectAll();
//        List<Magazyn> magazyny = magazynDao.selectAll();
//        
//        Kontrahent kontrahent = kontrahentDao.select(vaty.get(0).getOdbiorca().getId());
//        Uzytkownik uzytkownik = uzytkownikDao.select(vaty.get(0).getSprzedawca().getId());
//        Towar towar = towarDao.selectAll().get(0);
//        
//        Grupa grupa = grupaDao.select(towar.getGrupa().getId());
//        OpakowanieZbiorcze opakowanieZbiorcze = opakowanieZbiorczeDao.select(towar.getOpakowanie().getId());
//        StawkaVAT stawka = stawkaVATDao.select(towar.getStawka().getId());
//        JednostkaMiary jednostkaMiary = jednostkaMiaryDao.select(towar.getJednostka().getId());
//        
//        Bank bank = bankDao.select(kontrahent.getBank().getId());
//        TerminPlatnosci terminPlatnosci = terminPlatnosciDao.select(kontrahent.getTerminPlatnosci().getId());
//        Adres adres = adresDao.select(uzytkownik.getAdres().getId());
//        
//        FakturaVAT fs = vaty.get(0);
//        FakturaFZ fz = fzi.get(0);
//        PrzychodZewnetrzny pz = pzi.get(0);
//        WydanieZewnetrzne wz = wzi.get(0);
//        
//        WydanieZewnetrzneTowary wzTowary = wz.getListaTowarow().get(0);
//        PrzychodZewnetrznyTowary pzTowary = pz.getListaTowarow().get(0);
//        FakturaFZTowary fzTowary = fz.getListaTowarow().get(0);
//        FakturaVATTowary fsTowary = fs.getListaTowarow().get(0);
//        
//        Magazyn magazyn = magazyny.get(0);
//        MagazynTowary magazynTowary = magazyn.getMagazynTowary().get(0);
//        
//        adres.setGmina("Nowa gmina");
//        adres.setLokal(567);
//        
//        System.out.println("Updating: Adres");
//        adresDao.update(adres);
//        System.out.println("Done");
//        
//        terminPlatnosci.setIloscDni(90);
//        terminPlatnosci.setOpis("Zmieniony opis");
//        
//        System.out.println("Updating: TerminPlatnosci");
//        terminPlatnosciDao.update(terminPlatnosci);
//        System.out.println("Done");
//        
//        bank.setNazwaBanku("Nowa nazwa banku");
//        bank.setWaluta("USD");
//        
//        System.out.println("Updating: Bank");
//        bankDao.update(bank);
//        System.out.println("Done");
//        
//        jednostkaMiary.setNazwa("Nowa nazwa");
//        jednostkaMiary.setSkrot("nowy");
//        
//        System.out.println("Updating: JednostkaMiary");
//        jednostkaMiaryDao.update(jednostkaMiary);
//        System.out.println("Done");
//        
//        stawka.setNazwa("nowa nazwa");
//        stawka.setWartosc(45);
//        
//        System.out.println("Updating: StawkaVAT");
//        stawkaVATDao.update(stawka);
//        System.out.println("Done");
//        
//        opakowanieZbiorcze.setNazwaPelna("nowa nazwa pelna");
//        
//        System.out.println("Updating: OpakowanieZbiorcze");
//        opakowanieZbiorczeDao.update(opakowanieZbiorcze);
//        System.out.println("Done");
//        
//        grupa.setNazwa("nowa grupa");
//        
//        System.out.println("Updating: Grupa");
//        grupaDao.update(grupa);
//        System.out.println("Done");
//        
//        towar.setCena(123);
//        towar.setIndex2("nowy indeks");
//        
//        System.out.println("Updating: Towar");
//        towarDao.update(towar);
//        System.out.println("Done");
//        
//        uzytkownik.setLogin("nowy login");
//        uzytkownik.setTypUzytkownika(TypUzytkownika.PRACOWNIK);
//        
//        System.out.println("Updating: Uzytkownik");
//        uzytkownikDao.update(uzytkownik);
//        System.out.println("Done");
//        
//        kontrahent.setNazwaPelna("nowa nazwa pelna");
//        kontrahent.setCzyPlatnikVAT(false);
//        
//        System.out.println("Updating: Kontrahent");
//        kontrahentDao.update(kontrahent);
//        System.out.println("Done");
//        
//        magazyn.setNazwa("Nowy magazyn");
//        
//        System.out.println("Updating: Magazyn");
//        magazynDao.update(magazyn);
//        System.out.println("Done");
//        
//        magazynTowary.setIlosc(10);
//        
//        System.out.println("Updating: MagazynTowary");
//        magazynTowaryDao.update(magazynTowary);
//        System.out.println("Done");
//        
//        pzTowary.setIlosc(13);
//        
//        System.out.println("Updating: PrzychodZewnetrznyTowary");
//        przychodZewnetrznyTowaryDao.update(pzTowary);
//        System.out.println("Done");
//        
//        pz.setNumer(2234);
//        pz.setDataWystawienia(new Date(System.currentTimeMillis()));
//        
//        System.out.println("Updating: PrzychodZewnetrzny");
//        przychodZewnetrznyDao.update(pz);
//        System.out.println("Done");
//        
//        wzTowary.setIlosc(177);
//        
//        System.out.println("Updating: WydanieZewnetrzneTowary");
//        wydanieZewnetrzneTowaryDao.update(wzTowary);
//        System.out.println("Done");
//        
//        wz.setDataWystawienia(new Date(System.currentTimeMillis()));
//        wz.setNumer(21);
//        
//        System.out.println("Updating: WydanieZewnetrzne");
//        wydanieZewnetrzneDao.update(wz);
//        System.out.println("Done");
//        
//        fzTowary.setIlosc(123);
//        
//        System.out.println("Updating: FakturaFZTowary");
//        fakturaFZTowaryDao.update(fzTowary);
//        System.out.println("Done");
//        
//        fz.setSygnatura("nowa sygnatura");
//        fz.setMaska("22/L/22/2222");
//        
//        System.out.println("Updating: FakturaFZ");
//        fakturaFZDao.update(fz);
//        System.out.println("Done");
//        
//        fsTowary.setIlosc(123);
//        
//        System.out.println("Updating: FakturaVATTowary");
//        fakturaVATTowaryDao.update(fsTowary);
//        System.out.println("Done");
//        
//        fs.setNumer(321);
//        fs.setDataWystawienia(new Date(System.currentTimeMillis()));
//        
//        System.out.println("Updating: FakturaVAT");
//        fakturaVATDao.update(fs);
//        System.out.println("Done");
//    }
//
//    private void removeData() {
//        System.out.println("-");
//        System.out.println("------------------------DELETES TEST--------------------------------");
//        System.out.println("-");
//        
//        List<Adres> adresy = adresDao.selectAll();
//        List<TerminPlatnosci> terminy = terminPlatnosciDao.selectAll();
//        List<Bank> banki = bankDao.selectAll();
//
//        List<JednostkaMiary> jednostki = jednostkaMiaryDao.selectAll();
//        List<StawkaVAT> stawki = stawkaVATDao.selectAll();
//        List<OpakowanieZbiorcze> opakowania = opakowanieZbiorczeDao.selectAll();
//        List<Grupa> grupy = grupaDao.selectAll();
//
//        List<Towar> towary = towarDao.selectAll();
//        List<Uzytkownik> uzytkownicy = uzytkownikDao.selectAll();
//        List<Kontrahent> kontrahenci = kontrahentDao.selectAll();
//        
//        List<MagazynTowary> magazynTowary = magazynTowaryDao.selectAll();
//        List<Magazyn> magazyny = magazynDao.selectAll();
//
//        List<WydanieZewnetrzne> wz = wydanieZewnetrzneDao.selectAll();
//        List<PrzychodZewnetrzny> pz = przychodZewnetrznyDao.selectAll();
//        List<FakturaFZ> fz = fakturaFZDao.selectAll();
//        List<FakturaVAT> vaty = fakturaVATDao.selectAll();
//        
//        List<WydanieZewnetrzneTowary> wzTowary = wydanieZewnetrzneTowaryDao.selectAll();
//        List<PrzychodZewnetrznyTowary> pzTowary = przychodZewnetrznyTowaryDao.selectAll();
//        List<FakturaFZTowary> fzTowary = fakturaFZTowaryDao.selectAll();
//        List<FakturaVATTowary> fsTowary = fakturaVATTowaryDao.selectAll();
//        
//        System.out.println("Deleting: FakturaVATTowary");
//        fakturaVATTowaryDao.remove(fsTowary.get(0));
//        System.out.println("Done");
//        
//        System.out.println("Deleting: FakturaFZTowary");
//        fakturaFZTowaryDao.remove(fzTowary.get(0));
//        System.out.println("Done");
//        
//        System.out.println("Deleting: WydanieZewnetrzneTowary");
//        wydanieZewnetrzneTowaryDao.remove(wzTowary.get(0));
//        System.out.println("Done");
//        
//        System.out.println("Deleting: PrzychodZewnetrznyTowary");
//        przychodZewnetrznyTowaryDao.remove(pzTowary.get(0));
//        System.out.println("Done");
//
//        System.out.println("Deleting: FakturaVAT");
//        fakturaVATDao.remove(vaty.get(0));
//        System.out.println("Done");
//        
//        System.out.println("Deleting: FakturaFZ");
//        fakturaFZDao.remove(fz.get(0));
//        System.out.println("Done");
//        
//        System.out.println("Deleting: PrzychodZewnetrzny");
//        przychodZewnetrznyDao.remove(pz.get(0));
//        System.out.println("Done");
//        
//        System.out.println("Deleting: WydanieZewnetrzne");
//        wydanieZewnetrzneDao.remove(wz.get(0));
//        System.out.println("Done");
//
//        System.out.println("Deleting: Uzytkownik");
//        uzytkownikDao.remove(uzytkownicy.get(0));
//        System.out.println("Done");
//        
//        System.out.println("Deleting: MagazynTowary");
//        magazynTowaryDao.remove(magazynTowary.get(0));
//        System.out.println("Done");
//        
//        System.out.println("Deleting: Magazyn");
//        magazynDao.remove(magazyny.get(0));
//        System.out.println("Done");
//        
//        System.out.println("Deleting: Towar");
//        towarDao.remove(towary.get(0));
//        System.out.println("Done");
//        
//        System.out.println("Deleting: Kontrahent");
//        kontrahentDao.remove(kontrahenci.get(0));
//        System.out.println("Done");
//        
//        System.out.println("Deleting: Grupa");
//        grupaDao.remove(grupy.get(0));
//        System.out.println("Done");
//        
//        System.out.println("Deleting: OpakowanieZbiorcze");
//        opakowanieZbiorczeDao.remove(opakowania.get(0));
//        System.out.println("Done");
//        
//        System.out.println("Deleting: StawkaVAT");
//        stawkaVATDao.remove(stawki.get(0));
//        System.out.println("Done");
//        
//        System.out.println("Deleting: JednostkaMiary");
//        jednostkaMiaryDao.remove(jednostki.get(0));
//        System.out.println("Done");
//
//        System.out.println("Deleting: Bank");
//        bankDao.remove(banki.get(0));
//        System.out.println("Done");
//        
//        System.out.println("Deleting: TerminPlatnosci");
//        terminPlatnosciDao.remove(terminy.get(0));
//        System.out.println("Done");
//        
//        System.out.println("Deleting: Adres");
//        adresDao.remove(adresy.get(0));
//        System.out.println("Done");
//    }
//
//    @Test
//    public void crudAll__test_isOk() {
//        System.out.println("--------------------------------------------------------------------");
//        System.out.println("--------------------------------------------------------------------");
//        System.out.println("-----------------------CRUD TEST START------------------------------");
//        System.out.println("--------------------------------------------------------------------");
//        System.out.println("--------------------------------------------------------------------");
//        generateData();
//        updateData();
//        removeData();
//        System.out.println("--------------------------------------------------------------------");
//        System.out.println("--------------------------------------------------------------------");
//        System.out.println("-----------------------CRUD TEST END--------------------------------");
//        System.out.println("--------------------------------------------------------------------");
//        System.out.println("--------------------------------------------------------------------");
//    }
//}
