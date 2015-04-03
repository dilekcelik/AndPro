package dlk.clk.andpro;

import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ourViewClient extends WebViewClient {
	
	
	public boolean shouldOverrideUtlLoading(WebView v, String url){
		v.loadUrl(url);
		return true;
		
	}
}
