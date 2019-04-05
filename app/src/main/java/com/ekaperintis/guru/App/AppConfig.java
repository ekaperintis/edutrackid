package com.ekaperintis.guru.App;

public class AppConfig {

    //public static String HOST = "http://104.196.49.46/index.php/";
    //public static String HOST_IMAGE = "http://104.196.49.46/";

    public static String HOST_IMAGE = "http://edu.smkruhulbayan.sch.id/";
    public static String HOST = "http://edu.smkruhulbayan.sch.id/index.php/";
    public static String HOST_UPLOAD = "http://edu.smkruhulbayan.sch.id/";

    public static String LOGIN          = HOST + "users/login";
    public static String LOGOUT         = HOST + "users/logout";

    public static String DAFTAR_SISWA    = HOST + "data/getSiswa";
    public static String DAFTAR_ESKUL    = HOST + "siswa/getEskul";
    public static String DAFTAR_KELAS    = HOST + "siswa/getKelas";
    public static String SIMPAN_KEHADIRAN    = HOST + "siswa/simpan_kehadiran";
    public static String DAFTAR_HADIR    = HOST + "siswa/daftar_hadir";

    /* URL UNTUK SISWA */
    public static String URL_LOGIN = HOST + "api/cek_user";
    public static String URL_REGISTER = HOST +"api/register";

    public static String URL_TEACHER_LEARNING = HOST +"api/getdata";
    public static String URL_UPLOAD_TEACHER = HOST +"api/uploadimg";

    public static String URL_JAM_MASUK = HOST +"siswa/saveJamMasuk";
    public static String URL_JAM_PULANG = HOST +"siswa/saveJamPulang";
    public static String URL_KEHADIRAN_BY_NIPD = HOST +"kehadiran/getDataKehadiranNIPD";
    public static String URL_DAFTAR_TABUNGAN    = HOST + "tabungan/getTabungan";
    public static String URL_SIMPAN_TABUNGAN =  HOST + "tabungan/simpan_tabungan/";
    public static String URL_INFORMASI =  HOST + "informasi/getInformasi/";
    public static String URL_POINT =  HOST + "data/getPoint/";
    public static String URL_POINT_PELANGGARAN    = HOST + "pelanggaran/getPointPelanggaran";
    public static String URL_KIRIM_POINT_PELANGGARAN    = HOST + "pelanggaran/simpan_pelanggaran";
    public static String URL_SIMPAN_GPS =  HOST + "gps/savegps";

    /* UNTUK PEGAWAI */
    public static final String LOGIN_URL =  HOST + "pegawai/verifikasi_data/";
    public static final String SIMPAN_KEBERSIHAN =  HOST + "siswa/simpan_kebersihan/";
    public static final String URL_PORTAL =  "http://kesiswaan.smkbpm.sch.id/home";

}
