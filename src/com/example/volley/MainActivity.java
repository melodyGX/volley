package com.example.volley;

import org.json.JSONObject;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageCache;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends Activity {
	private Button button;
	private ImageView imageView;

	private RequestQueue queue;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		button = (Button) findViewById(R.id.button1);
		imageView = (ImageView) findViewById(R.id.imageView1);
		queue = Volley.newRequestQueue(this);

		// StringRequestTest();
		// JsonRequestTest();
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// ImageRequestTest();
//				ImageLoaderTest();
				NetworkImageViewTest();

			}
		});

	}

	// 与网络通信返回一个字符创
	public void StringRequestTest() {
		StringRequest stringRequest = new StringRequest(
				"http://www.baidu.com/", new Listener<String>() {

					@Override
					public void onResponse(String response) {
						// 在这个方法里可以对UI进行更新
						// TODO Auto-generated method stub
						Log.i("----StringRequestTest", response);

					}
				}, new ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						// TODO Auto-generated method stub

					}
				});
		queue.add(stringRequest);

	}

	// 与网络通信返回json
	public void JsonRequestTest() {
		JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
				"http://www.weather.com.cn/adat/sk/101010100.html", null,
				new Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {
						Log.i("----JsonRequestTest", response.toString());

					}
				}, new ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {

					}
				});
		queue.add(jsonObjectRequest);
	}

	/**
	 * 加载网络图片的三种方法
	 */

	// ImageRequest 与网络通信返回一个Bitmap对象
	public void ImageRequestTest() {
		ImageRequest imageRequest = new ImageRequest(
				"https://www.baidu.com/img/bd_logo1.png",
				new Listener<Bitmap>() {

					@Override
					public void onResponse(Bitmap response) {
						// TODO Auto-generated method stub
						imageView.setImageBitmap(response);

					}
				}, 0, 0, Config.RGB_565, new ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						// TODO Auto-generated method stub

					}
				});

		queue.add(imageRequest);
	}

	/**
	 * ImageLoader 1. 创建一个RequestQueue对象。
	 * 
	 * 2. 创建一个ImageLoader对象。
	 * 
	 * 3. 获取一个ImageListener对象。
	 * 
	 * 4. 调用ImageLoader的get()方法加载网络上的图片。
	 */
	public void ImageLoaderTest() {
		ImageLoader imageLoader = new ImageLoader(queue, new BitmapCache());
		ImageListener imageListener = ImageLoader.getImageListener(imageView,
				R.drawable.default_pic, R.drawable.wrong_pic);
		// imageLoader.get("http://cdn.duitang.com/uploads/item/201112/17/20111217144319_WW5Mj.thumb.600_0.jpg",
		// imageListener);

		imageLoader
				.get("http://cdn.duitang.com/uploads/item/201112/17/20111217144319_WW5Mj.thumb.600_0.jpg",
						imageListener, 50, 50);
	}

	/**
	 * 1. 创建一个RequestQueue对象。
	 * 
	 * 2. 创建一个ImageLoader对象。
	 * 
	 * 3. 在布局文件中添加一个NetworkImageView控件。 
	 * 4. 在代码中获取该控件的实例。
	 * 5. 设置要加载的图片地址。
	 */
	public void NetworkImageViewTest() {
		ImageLoader imageLoader=new ImageLoader(queue, new BitmapCache());
		NetworkImageView networkImageView=(NetworkImageView) findViewById(R.id.network_image_view);
		networkImageView.setDefaultImageResId(R.drawable.default_pic);
		networkImageView.setErrorImageResId(R.drawable.wrong_pic);
		networkImageView.setImageUrl("http://cdn.duitang.com/uploads/item/201112/17/20111217144319_WW5Mj.thumb.600_0.jpg", imageLoader);

	}
}
