package OddeleniAKrizovySimulator;

import java.util.ArrayList;

public class Main {
    interface IVymlouvac {
        String vymysliVymluvu();
    }
    // tady abstract
    static abstract class SkolniPostava {
        protected String jmeno;
        protected boolean pritomen;
        public SkolniPostava(String jmeno, boolean pritomen) {
            this.jmeno = jmeno;
            this.pritomen = pritomen;
        }
 // tu Main
        public static void main(String[] args) {
            EvidencePostav evidence = new EvidencePostav();
            evidence.pridejPostavu(new Ucitel("Jitka", true , false)); // strily po zacich = true;

            evidence.pridejPostavu(new Student("Adam", true, 3));


            evidence.pridejPostavu(new Student("Vojta", false, 1));


//            evidence.pridejPostavu(new Ucitel("Pan Promitac", true, true));


            evidence.pridejPostavu(new Ucitel("Pan Papir", false, false));
            evidence.pridejPostavu(new Skolnik("Mistr Klic", true));


            evidence.pridejPostavu(new Skolnik("Pan Zasuvka", true));


            evidence.vypisPostavy();

            // V Y M L U V A
            System.out.println("Vymluva");



            IVymlouvac student = new Student("Adam", true, 3);

            IVymlouvac ucitel = new Ucitel("Ucitel", true, true);


            System.out.println(student.vymysliVymluvu());            System.out.println("..........................................................................................................................................");

            System.out.println(ucitel.vymysliVymluvu());            System.out.println("..........................................................................................................................................");


            int pocetPokusu = 3; // klidne zmenit i na 120

            evidence.vypisZtraceneMinuty(pocetPokusu);


            int soucet = evidence.spocitejZdrzeniPritomnych(pocetPokusu);


            System.out.println("Pritomny(celk..)" + pocetPokusu + " POKUSY ");

            System.out.println("..........................................................................................................................................");

            System.out.println(soucet + " min");

            if (soucet > 20) {
                System.out.println("Na pisemku nebyl cas musim se ucit na javu ");
                System.out.println("..........................................................................................................................................");
            } else {
                System.out.println("Nestihla se jupi skoda ze jsem se tak pracne ucil");
            }}

        public String getJmeno() {
            return jmeno;
        }

        public boolean isPritomen() {
            return pritomen;
        }

        public abstract void vypisInformace();
        public int spocitejZtraceneMinuty(int pocetPokusu) {
            return pocetPokusu;
        }

    }
    static class Student extends SkolniPostava
            implements IVymlouvac {


        private int pocetZapomenutychPomucek;

        public Student(String jmeno, boolean pritomen,
                       int pocetZapomenutychPomucek) {
            super(jmeno, pritomen);
            if (pocetZapomenutychPomucek < 0) {
                throw new IllegalArgumentException(
                        "Pocet pomucek nesmi byt zaporny.");
            }
            this.pocetZapomenutychPomucek = pocetZapomenutychPomucek;}
        @Override
        public void vypisInformace() {
            System.out.println("Student: " + jmeno
                    + ", pritomen: " + (pritomen ? "ano" : "ne")
                    + ", zapomenute pomucky: "
                    + pocetZapomenutychPomucek);
        }

        @Override
        public int spocitejZtraceneMinuty(int pocetPokusu) {
            int minuty = pocetPokusu * 2;
            if (pocetZapomenutychPomucek > 2) {
                minuty += 2;
            }
            return minuty;}
        @Override
        public String vymysliVymluvu() {
            return "Co je to ukol(Ale 1 u Javy prosím :)).";
        }

    }
    static class Ucitel extends SkolniPostava
            implements IVymlouvac {


        private boolean pouzivaProjektor;

        public Ucitel(String jmeno, boolean pritomen,
                      boolean pouzivaProjektor) {
            super(jmeno, pritomen);
            this.pouzivaProjektor = pouzivaProjektor;
        }

        @Override
        public void vypisInformace() {
            System.out.println("Ucitel: " + jmeno
                    + ", pritomen: " + (pritomen ? "ano" : "ne")
                    + ", projektor: "
                    + (pouzivaProjektor ? "ano" : "ne"));
        }

        @Override
        public int spocitejZtraceneMinuty(int pocetPokusu) {
            int minuty = pocetPokusu * 3;

            if (pouzivaProjektor) {
                minuty += 4;
            }

            return minuty;
        }
        @Override
        public String vymysliVymluvu() {
            return "Vsaj to fungovalo.";
        }
    }
    static class Skolnik extends SkolniPostava {


        public Skolnik(String jmeno, boolean pritomen) {
            super(jmeno, pritomen);
        }
        @Override
        public void vypisInformace() {
            System.out.println("Skolnik: " + jmeno
                    + ", pritomen: " + (pritomen ? "ano" : "ne"));
        }
    }
    static class EvidencePostav {


        private ArrayList<SkolniPostava> postavy =
                new ArrayList<SkolniPostava>();

        public void pridejPostavu(SkolniPostava postava) {
            postavy.add(postava);
        }

        public void vypisPostavy() {
            System.out.println("(:- VSECHNY POSTAVY -:)");

            for (SkolniPostava postava : postavy) {
                postava.vypisInformace();
            }
        }

        public void vypisZtraceneMinuty(int pocetPokusu) {
            System.out.println(" ZTRACENE MINUTY: "
                    + pocetPokusu + " POKUSY ===");

            for (SkolniPostava postava : postavy) {
                int minuty =
                        postava.spocitejZtraceneMinuty(pocetPokusu);

                System.out.println(postava.getJmeno()
                        + " se zdrzi " + minuty + " min.");
            }
        }
        public int spocitejZdrzeniPritomnych(int pocetPokusu) {
            int soucet = 0;

            for (SkolniPostava postava : postavy) {
                if (postava.isPritomen()) {
                    soucet +=
                            postava.spocitejZtraceneMinuty(pocetPokusu);
                }
            }

            return soucet;
        }
    }
}



