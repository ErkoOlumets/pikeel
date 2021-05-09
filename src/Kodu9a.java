/****************************************
 * Programmeerimisharjutused. LTAT.03.007
 * 2020/2021 kevadsemester
 *
 * Kodutöö. Ülesanne nr 9a
 *
 * Teema: Sõned
 *
 * Autor: Erko Olumets
 ***************************************/
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Kodu9a {

    static String piKodeeridaSõna(String s) {
        // Antud: sõna s, mis ei sisalda tähepaari "pi"
        // Tulemus: tagastatakse p(s) – piikeelde teisendatud sõna s
        // Kasutab klassi StringBuilder
        if (s.length() <3) return s;
        else {
            boolean eelmineVokaal = false;
            StringBuilder pisõna = new StringBuilder(s);
            for (int i = 0; i < pisõna.length() - 1; ) {
                if (onVokaal(pisõna.charAt(i))&&!eelmineVokaal) {
                    pisõna.insert(i + 1, "pi");
                    i += 3;
                    eelmineVokaal=true;
                } else {
                    i += 1;
                    eelmineVokaal=false;
                }
            }
            return pisõna.toString();
        }
    }

    static String piKodeeridaLause(String s) {
        // Antud: lause (sõnena), mis ei sisalda tähepaari "pi",
        // võib sisaldada mittetähti ".,;:-?!\n\t\b *()"
        // Tulemus: tagastatakse lause, mis saadud antud lauses kõigi sõnade
        // teisendamisel piikeelde
        // Kasutab klasse StringBuilder
        String[] lause = s.split("[.,;:?!\n\t\b *()\\-\"]+");
        String[] m2rgid = s.split("[qwertyuiopasdfghjklzxcvbnmüõöäQWERTYUIOPÜÕASDFGHJKLÖÄZXCVBNMŠŽšž]+");
        StringBuilder pilause = new StringBuilder();
        for (int i = 0; i < lause.length; i++)  {
            if (String.valueOf(s.charAt(0)).matches("[.,;:?!\n\t\b *()\\-\"]+")){
                pilause.append(piKodeeridaSõna(lause[i]));
                if (i < m2rgid.length) pilause.append(m2rgid[i]);
            }else{
                if (i < m2rgid.length) pilause.append(m2rgid[i]);
                pilause.append(piKodeeridaSõna(lause[i]));
            }
        }
        if (m2rgid.length> lause.length) pilause.append(m2rgid[m2rgid.length-1]);
        return pilause.toString();
    }

    static boolean onVokaal(char c) {
        // Antud: sümbol c
        // Tulemus: tagastatakse väite "c on täishäälik" tõeväärtus
        //Kasutab klassi String isendimeetodit contains või indexOf
        String täishäälikud = "aeiouüõöäAEIUOÜÕÖÄ";
        return täishäälikud.contains(String.valueOf(c));
    }

    static String piDekodeeridaLause(String s) {
        // Antud: Antud piikeelne lause s
        // Tulemus: lause, mis saadud antud lausest kõigi tähepaaride "pi" eemaldamisel
        // Kasutab klassi String isendimeetodit replaceAll
        return s.replaceAll("pi", "");
    }

    static void piRidadeKaupa(String fNimi) {
        // Antud: vaadeldava tekstifaili nimi
        // Tulemus: vaadeldava faili iga rea r korral, mis ei sisaldab tähepaari "pi",
        // väljastatakse r
        // väljastatakse r' -- piikeelde kodeeritud r, ning tühirida
        // kontrollitakse, kas r == dekodeeritud r'
        // kui ei, siis teatatakse veast ja väljutakse
        // kui jah, siis tehakse peatus ning seejärel jätkatakse;
        // kui rida r sisaldab tähepaari "pi", siis vaid väljastatakse rida r ja
        // teade vahelejätmisest
        try (Scanner sc = new Scanner(new java.io.File(fNimi), StandardCharsets.UTF_8)) {
            while (sc.hasNextLine()) {
                String r = sc.nextLine();
                if (!r.equals("")){
                    if (!r.contains("pi")){
                        System.out.println(r);
                        String piRida = piKodeeridaLause(r);
                        System.out.println(piRida+"\n");
                        if (!piDekodeeridaLause(piRida).equals(r)) {
                            System.out.println("Kodeerimisel tekkis viga, lõpetan kodeerimise.");
                            break;
                        }
                    }
                    else {
                        System.out.println(r);
                        System.out.println("Rida sisaldab juba pi-d, rida ei kodeerita"+"\n");
                    }
                }
            }
        }
        catch (Exception e){

        }
    }

    public static void main(String[] args){
        System.out.println("Ülesanne 9a. Programmi väljund");
        System.out.println("=====================================================:");
        /*
        String[] laused = {
                "kodeeritud", "luuletuseks", "on",
                "Juhan Viidingu \"Ma olen pannud segast\"."
        };//laused
        for (String lause : laused) {// kodeerimine ja dekodeerimine
            if (lause.contains("pi"))
                System.out.println(lause + "\nsisaldab \"pi\", ei käsitleta");
            else {
                System.out.println(lause + " -> " + piKodeeridaLause(lause));
                System.out.println(piDekodeeridaLause(piKodeeridaLause(lause)));
            }
            System.out.println();
        }//for
        */
        //String suur = str.toUpperCase();
        //char märk = str.charAt(2);
        //
        System.out.println("Lugemine failist:");
        // tekstiridade kodeerimise näide:
        piRidadeKaupa("LuguSõprusest.txt");
        System.out.println("\n=================================================.");
        System.out.println("Erko Olumets              " + new java.sql.Timestamp(System.currentTimeMillis()));
    }//main
}
