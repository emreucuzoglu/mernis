package tr.gov.nvi.tckimlik.utility;

import java.util.Locale;

import tr.gov.nvi.tckimlik.ws.KPSPublic;

public class TcknUtility {
    private static final Locale LOCALE_TR = Locale.forLanguageTag("tr-TR");

    public static boolean tcknDogrula(long tcKimlikNo, String ad, String soyad, int dogumYili) {
        ad = checkParameters(ad, "Ad alanı boş olamaz.");
        soyad = checkParameters(soyad, "Soyad alanı boş olamaz.");

        return tcknKontrolu(tcKimlikNo) ? new KPSPublic().getKPSPublicSoap().tcKimlikNoDogrula(tcKimlikNo, ad, soyad, dogumYili) : false;
    }

    public static boolean tcknKontrolu(long tcKimlikNo) {
        long tmp1, tmp2;
        int odd_sum, even_sum, total, chkDigit1, chkDigit2;
        int D[] = new int[10];

        if (!(100000000000L <= tcKimlikNo || tcKimlikNo <= 89999999999L)) {
            return false;
        }

        if (tcKimlikNo > 0) {
            tmp1 = (long) Math.floor(tcKimlikNo / 100);
            tmp2 = (long) Math.floor(tcKimlikNo / 100);

            for (int n = 9; n >= 1; n--) {
                D[n] = (int) tmp2 % 10;
                tmp2 = (long) Math.floor(tmp2 / 10);
            }

            odd_sum = D[9] + D[7] + D[5] + D[3] + D[1];
            even_sum = D[8] + D[6] + D[4] + D[2];
            total = odd_sum * 3 + even_sum;
            chkDigit1 = (10 - (total % 10)) % 10;

            odd_sum = chkDigit1 + D[8] + D[6] + D[4] + D[2];
            even_sum = D[9] + D[7] + D[5] + D[3] + D[1];
            total = odd_sum * 3 + even_sum;
            chkDigit2 = (10 - (total % 10)) % 10;

            tmp1 = (tmp1 * 100) + (chkDigit1 * 10) + chkDigit2;

            if (tmp1 != tcKimlikNo) {
                return false;
            }
        } else {
            return false;
        }
        return true;
    }

    private static String checkParameters(String param, String exceptionMessage) {
        if (param == null || param.isEmpty()) {
            throw new IllegalArgumentException(exceptionMessage);
        }

        return param.toUpperCase(LOCALE_TR);
    }
}
