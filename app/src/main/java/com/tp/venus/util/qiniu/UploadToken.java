package com.tp.venus.util.qiniu;

import android.util.Base64;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * 七牛云存储上传凭证
 * <p/>
 * UploadToken.build(PutPolicy.newPicture(uniqueName));
 * UploadToken.build(PutPolicy.newVideo(uniqueName));
 *
 * @see <url>http://developer.qiniu.com/docs/v6/api/reference/security/upload-token.html</url>
 * <p/>
 * Created by xu on 2015-12-15.
 */
class UploadToken {
    static String makeSafeB64(String putPolicyJson) {
        return Base64.encodeToString(putPolicyJson.getBytes(),
                Base64.URL_SAFE | Base64.NO_WRAP);
    }

    static String makeSafeB64(byte[] sign) {
        return Base64.encodeToString(sign,
                Base64.URL_SAFE | Base64.NO_WRAP);
    }

    static String test(String encodedPutPolicy) {
        String encodedSign;
        try {
            SecretKey secretKey = new SecretKeySpec("MY_SECRET_KEY".getBytes(), "HmacSHA1");
            Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(secretKey);
            byte[] sign = mac.doFinal(encodedPutPolicy.getBytes());
//			Log.e("sign=", Base64.encode(sign,
//					Base64.URL_SAFE | Base64.NO_WRAP));
            encodedSign = makeSafeB64(sign);
            return "MY_ACCESS_KEY" + ":" + encodedSign + ":" + encodedPutPolicy;
        } catch (Exception x) {
            return null;
        }
    }

    //	@NonNull
    static String build(String policy) {
        String encodedPolicy = makeSafeB64(policy);
        String encodedSign;
        try {
            SecretKey secretKey = new SecretKeySpec(Definitions.SecretKey, "HmacSHA1");
            Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(secretKey);
            byte[] sign = mac.doFinal(encodedPolicy.getBytes());
            encodedSign = makeSafeB64(sign);
            return Definitions.AccessKey + ":" + encodedSign + ":" + encodedPolicy;
        } catch (Exception x) {
            return null;
        }
    }
}