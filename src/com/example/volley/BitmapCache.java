package com.example.volley;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader.ImageCache;

public class BitmapCache implements ImageCache{
	private LruCache<String, Bitmap> mCache;
	
	
	public BitmapCache() {
		// TODO Auto-generated constructor stub
		int size=10*1024*1024;
		mCache=new LruCache<String, Bitmap>(size){
			@Override
			protected int sizeOf(String key, Bitmap value) {
				// TODO Auto-generated method stub
				return value.getRowBytes() * value.getHeight();
			}
		};
	}

	@Override
	public Bitmap getBitmap(String url) {
		return mCache.get(url);
	}

	@Override
	public void putBitmap(String url, Bitmap bitmap) {
		mCache.put(url, bitmap);
		
	}

}
