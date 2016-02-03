package com.shuttle.square;

import com.android.volley.toolbox.HurlStack;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.OkUrlFactory;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.net.ssl.SSLContext;
/**
 * Created by Deepak Pawar on 1/19/2016.
 */

/**
 * An {@link com.android.volley.toolbox.HttpStack HttpStack} implementation which
 * uses OkHttp as its transport.
 */
public class OkHttpStack extends HurlStack
{
    private final OkUrlFactory mFactory;

    public OkHttpStack()
    {
        this(new OkHttpClient());
    }

    public OkHttpStack(OkHttpClient client)
    {
        if (client == null)
        {
            throw new NullPointerException("Client must not be null.");
        }
        /**
         * Comment at first when without https
         * advance ssl https://gist.github.com/tbruyelle/0729aef4df2c11b21fdf
         * https://gist.github.com/bryanstern/4e8f1cb5a8e14c202750
         * http://www.jianshu.com/p/e58161cbc3a4
         * @todo workaround for OkHttp changes the global SSL context, breaks other HTTP clients
         * **/
        try
        {
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, null, null);
            client.setSslSocketFactory(sslContext.getSocketFactory());
        }
        catch (Exception e)
        {
            throw new AssertionError(); // The system has no TLS. Just give up.
        }

        /**/
        mFactory = new OkUrlFactory(client);
    }

    @Override
    protected HttpURLConnection createConnection(URL url) throws IOException
    {
        return mFactory.open(url);
    }
}
//@Todo Read it
//OkHttp works as the transport layer for Volley, which on top of OkHttp is a handy way of making network
// requests that are parsed to Java objects by Gson just before delivering the response to the main thread