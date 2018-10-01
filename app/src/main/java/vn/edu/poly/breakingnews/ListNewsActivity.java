package vn.edu.poly.breakingnews;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.Xml;
import android.view.MotionEvent;
import android.view.View;
import android.view.MenuItem;
import android.support.v4.app.NavUtils;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import vn.edu.poly.breakingnews.model.News;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class ListNewsActivity extends AppCompatActivity {


    private RecyclerView lvListNews;

    private String rssLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_news);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        lvListNews = findViewById(R.id.lvListNews);
        rssLink = getIntent().getStringExtra("data");

        LoadRssFromInternetTask loadRssFromInternetTask = new LoadRssFromInternetTask(this);
        loadRssFromInternetTask.execute(rssLink);

    }


    class LoadRssFromInternetTask extends AsyncTask<String, Long, List<News>> {


        private Context context;

        public LoadRssFromInternetTask(Context context) {
            this.context = context;
            Log.e("START", "START");

        }


        // ham xu ly ngam
        @Override
        protected List<News> doInBackground(String... strings) {


            try {

                ArrayList<News> newsArrayList = new ArrayList();
                URL url = new URL(strings[0]);

                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                factory.setNamespaceAware(false);
                XmlPullParser xpp = factory.newPullParser();

                // We will get the XML from an input stream
                xpp.setInput(getInputStream(url), "UTF_8");


                /* We will parse the XML content looking for the "<title>" tag which appears inside the "<item>" tag.
                 * However, we should take in consideration that the rss feed name also is enclosed in a "<title>" tag.
                 * As we know, every feed begins with these lines: "<channel><title>Feed_Name</title>...."
                 * so we should skip the "<title>" tag which is a child of "<channel>" tag,
                 * and take in consideration only "<title>" tag which is a child of "<item>"
                 *
                 * In order to achieve this, we will make use of a boolean variable.
                 */
                boolean insideItem = false;

                // Returns the type of current event: START_TAG, END_TAG, etc..
                int eventType = xpp.getEventType();

                News news = null;
                while (eventType != XmlPullParser.END_DOCUMENT) {

                    switch (eventType) {
                        case XmlPullParser.START_TAG:
                            String nameTag = xpp.getName();
                            Log.e("NAME", nameTag);
                            if (nameTag.equalsIgnoreCase("item")) {
                                news = new News();
                            } else if (news != null) {
                                if (nameTag.equals("title")) {
                                    news.title = xpp.nextText();
                                    Log.e("title", xpp.nextText());
                                }
                            }

                            break;
                        case XmlPullParser.END_TAG:

                            break;

                    }
                    eventType = xpp.next(); //move to next element
                }

                Log.e("SIZE", newsArrayList.size() + "");

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        private InputStream getInputStream(URL url) {
            try {
                return url.openConnection().getInputStream();
            } catch (IOException e) {
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<News> news) {
            super.onPostExecute(news);
        }
    }


}
