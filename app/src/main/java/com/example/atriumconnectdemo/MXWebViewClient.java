package com.example.atriumconnectdemo;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.net.Uri;
import android.net.http.SslError;
import android.webkit.SslErrorHandler;

import org.json.JSONObject;

public class MXWebViewClient extends WebViewClient {
    private Activity activity;

    public MXWebViewClient(Activity mainActivity) {
        activity = mainActivity;
    }

    /**
     * Handle all navigation events from the webview. Cancel all navigation events that start with
     * your `appScheme` or `atriumScheme`. Instead of post messages, we send that data via
     * navigation events since webviews don't have a reliable postMessage API.
     *
     * See the post message documentation for more details:
     * https://atrium.mx.com/docs#postmessage-events
     */
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        /*
        `appScheme` needs to match the `ui_message_webview_url_scheme` configuration value, if set.
        Most navigation events will use that scheme instead of `atrium://`. It is also used to redirect
        the user back to your app from the oauth flows. (See example in AndroidManifest.xml)

        See the documentation for more details
        https://atrium.mx.com/docs#embedding-in-webviews
        */
        boolean isMessageFromConnect = url.startsWith("appscheme://") || url.startsWith("atrium://");

        if (isMessageFromConnect) {
            try {
                Uri mxURI = Uri.parse(url);

                // Handle the oauth url redirect. Send the user to the URL given.
                if (mxURI.getPath().equals("/oauthRequested")) {
                    JSONObject mxMetaData = new JSONObject(mxURI.getQueryParameter("metadata"));
                    String oauthURL = mxMetaData.getString("url");
                    Uri oauthPage = Uri.parse(oauthURL);
                    Intent intent = new Intent(Intent.ACTION_VIEW, oauthPage);
                    activity.startActivity(intent);
                }
            } catch (Exception err) {
                Log.e("MX:Error with OAuth URL", err.getMessage());
            }
            return true;
        }

        return false;
    }
}
