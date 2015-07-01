package danielworld.compassproject.util.app;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.security.auth.x500.X500Principal;

import danielworld.compassproject.util.Logger;
import danielworld.compassproject.util.StringUtil;


/**
 * The Utilities of Application
 * <br><br>
 * Created by Daniel Park on 2015-05-28.
 */
public class AppUtil {

    private static final String TAG = AppUtil.class.getSimpleName();
    private static final Logger LOG = Logger.getInstance();

    private static final X500Principal DEBUG_DN = new X500Principal("CN=Android Debug,O=Android,C=US");

    /**
     * Get application version code
     * @param context
     * @return current App versionCode <br>
     *     return 0 if you failed to get App versionCode
     */
    public static int getAppVersionCode(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * Get application version name
     * @param context
     * @return current App versionName <br>
     *     return "" is you failed to get App versionCode
     */
    public static String getAppVersionName(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * Get application package name
     * @param context
     * @return
     */
    public static String getAppPackageName(Context context){
        return context.getApplicationContext().getPackageName();
    }

    /**
     * Get installed packages list
     * @param context
     * @param permission <code>true</code> Get Installed packages with permission granted <br>
     *                   <code>false</code> Get Installed packages with permission denied
     */
    public static List<PackageInfo> getInstalledPackages(Context context, boolean permission){

        PackageManager pm = context.getPackageManager();

        if(permission) {
            List<PackageInfo> grantedPacks = pm.getInstalledPackages(PackageManager.PERMISSION_GRANTED);

            Collections.sort(grantedPacks, new Comparator<PackageInfo>() {
                @Override
                public int compare(PackageInfo lhs, PackageInfo rhs) {
                    return lhs.packageName.compareTo(rhs.packageName);
                }
            });
            for (PackageInfo pack : grantedPacks) {
                LOG.d(TAG, "Permission Granted : " + pack.packageName);
            }

            return grantedPacks;
        }
        else {

            List<PackageInfo> deniedPacks = pm.getInstalledPackages(PackageManager.PERMISSION_DENIED);
            Collections.sort(deniedPacks, new Comparator<PackageInfo>() {
                @Override
                public int compare(PackageInfo lhs, PackageInfo rhs) {
                    return lhs.packageName.compareTo(rhs.packageName);
                }
            });

            for (PackageInfo pack : deniedPacks) {
                LOG.d(TAG, "Permission Denied : " + pack.packageName);
            }

            return deniedPacks;
        }
    }

    public enum APP_TYPE{
        MARKET_APP, SYSTEM_APP, THIRD_APP
    }

    /**
     * Get specific package name's app type
     * @param context
     * @param packageName
     * @return App type (Market app / system app / third app)
     * @throws Exception
     */
    public static APP_TYPE getInstalledAppType(Context context, String packageName) throws Exception {
        if(StringUtil.isNullorEmpty(packageName))
            throw new Exception("PackageName should be neither null nor empty!");

        ApplicationInfo am = null;
        try {
            am = context.getPackageManager().getApplicationInfo(packageName, 0);

            if((am.flags & ApplicationInfo.FLAG_SYSTEM) != 0){
                if((am.flags & ApplicationInfo.FLAG_UPDATED_SYSTEM_APP) != 0){
                    // The app which is updated from Market
                    return APP_TYPE.MARKET_APP;
                }
                else{
                    // System app
                    return APP_TYPE.SYSTEM_APP;
                }
            }
            else{
                // 3rd party app
                return APP_TYPE.THIRD_APP;
            }

        } catch (PackageManager.NameNotFoundException e) {
            throw new Exception("Failed to detect app type");
        }
    }

    /**
     * Check if the application is signed by Debug keystore.
     *
     * @param ctx
     * @return <code>true</code> : if app was signed by debug.keystore
     */
    public static boolean isDebuggable(Context ctx) {
        try {
            PackageInfo pinfo = ctx.getPackageManager().getPackageInfo(ctx.getPackageName(), PackageManager.GET_SIGNATURES);
            Signature signatures[] = pinfo.signatures;

            CertificateFactory cf = CertificateFactory.getInstance("X.509");

            for (int i = 0; i < signatures.length; i++) {
                ByteArrayInputStream stream = new ByteArrayInputStream(signatures[i].toByteArray());
                X509Certificate cert = (X509Certificate) cf.generateCertificate(stream);
                if (cert.getSubjectX500Principal().equals(DEBUG_DN)) {
                    return true;
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            // debuggable variable will remain false
        } catch (CertificateException e) {
            // debuggable variable will remain false
        }
        return false;
    }

    /**
     * Get key hash like e.g) MoOnjObCBRe$nfa42kdoeMdie4=
     * @param context
     * @return return "" if you failed to get key-hash
     */
    public static String getAppKeyHash(Context context){
        PackageInfo packageInfo;
        String keyHash = null;
        try{
            // Getting application package name
            String packageName = getAppPackageName(context);

            // Retriving package info
            packageInfo = context.getPackageManager().getPackageInfo(packageName, PackageManager.GET_SIGNATURES);

            for(Signature signature: packageInfo.signatures){
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                keyHash = new String(Base64.encode(md.digest(), 0));

                return keyHash;
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
